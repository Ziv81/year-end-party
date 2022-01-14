package com.changing.party.filter;

import com.changing.party.common.enums.LogType;
import com.changing.party.model.ServiceLog;
import com.changing.party.model.UserModel;
import com.changing.party.service.ServiceLogService;
import com.changing.party.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@Component
@Order(1)
public class LogRequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("URL [{}]", request.getRequestURI());
        filterChain.doFilter(request, response);
    }
}
