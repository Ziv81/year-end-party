package com.changing.party.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.changing.party.constant.ServerConstant;
import com.changing.party.model.response.GetTokenResponseModel;
import com.changing.party.model.response.ResponseModel;
import com.changing.party.shared.JWTUtil;
import com.changing.party.user.model.LoginUser;
import com.changing.party.user.model.User;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(new String(IOUtils.toByteArray(request.getInputStream()), StandardCharsets.UTF_8));
        String userName = jsonNode.get("account").asText();
        String password = jsonNode.get("pwd").asText();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName, password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        LoginUser user = (LoginUser) authentication.getPrincipal();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ResponseModel responseModel = ResponseModel.builder()
                .errorCode(ServerConstant.SERVER_SUCCESS_CODE)
                .errorMessage(ServerConstant.SERVER_SUCCESS_MESSAGE)
                .data(GetTokenResponseModel.builder()
                        .accessToken(JWTUtil.getJWTAccessToken(user))
                        .refreshToken(JWTUtil.getJWTRefreshToken(user))
                        .expiresIn(900)
                        .tokenType("Bearer")
                        .userInfo(User.builder()
                                .title(user.getTitle())
                                .userName(user.getUsername())
                                .userId(user.getUserId())
                                .userPoint(user.getUserPoint())
                                .build())
                        .build())
                .build();
        new ObjectMapper().writeValue(response.getOutputStream(), responseModel);
    }
}
