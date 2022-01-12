package com.changing.party.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.changing.party.common.GlobalVariable;
import com.changing.party.common.JWTUtil;
import com.changing.party.common.ServerConstant;
import com.changing.party.response.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    boolean isNotOpenCanAccess(HttpServletRequest request) {
        if (request.getServletPath().equals("/rest/api/user/login") ||
                request.getServletPath().equals("/rest/api/user/uploadUsers"))
            return true;
        if (request.getServletPath().matches("/rest/api/user/\\d+"))
            return true;
        return false;
    }

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!GlobalVariable.getGlobalVariableService().isOpen() && !isNotOpenCanAccess(request)) {
            response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(),
                    Response.builder()
                            .errorCode(ServerConstant.SERVER_FAIL_CODE)
                            .errorMessage("Time not yet.")
                            .build());
            return;
        }
        if (request.getServletPath().equals("/rest/api/user/login")
                || request.getServletPath().contains("h2-console")
                || request.getServletPath().contains("uploadUsers")) {
            filterChain.doFilter(request, response);
        } else {
            response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                try {
                    DecodedJWT decodedJWT = JWTUtil.verifyJWTToken(authorizationHeader.substring("Bearer ".length()));
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(decodedJWT.getSubject(), null, null);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request, response);
                } catch (Exception ex) {
                    log.error("Verify JWT token fail.", ex);
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    new ObjectMapper().writeValue(response.getOutputStream(),
                            Response.builder()
                                    .errorCode(ServerConstant.SERVER_FAIL_CODE)
                                    .errorMessage("JWT token verify fail.")
                                    .build());
                }
            } else {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                new ObjectMapper().writeValue(response.getOutputStream(),
                        Response.builder()
                                .errorCode(ServerConstant.SERVER_FAIL_CODE)
                                .errorMessage("No authorization.")
                                .build());
            }
        }
    }
}
