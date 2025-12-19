package com.kb.users.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class HttpRequestLoggingFilter extends OncePerRequestFilter {

    private static final String START_TIME = "startTime";

    @Override
    protected void doFilterInternal(
            final HttpServletRequest request,
            @NonNull final HttpServletResponse response,
            final FilterChain filterChain) throws IOException, ServletException {
        request.setAttribute(START_TIME, System.currentTimeMillis());
        try {
            filterChain.doFilter(request, response);
        } finally {
            long startTime = (long) request.getAttribute(START_TIME);
            long duration = System.currentTimeMillis() - startTime;

            log.info("{} {} -> {} ({} ms)",
                    request.getMethod(),
                    request.getRequestURI(),
                    response.getStatus(),
                    duration);
        }
    }
}
