package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    @Autowired
//    JwtTokenProvider jwtTokenProvider;
//
//    @Autowired
//    UsDetailsService usDetailsService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest httpServletRequest,
//                                    HttpServletResponse httpServletResponse,
//                                    FilterChain filterChain) throws ServletException, IOException {
//        try {
//            String jwt = getJwtFromRequest(httpServletRequest);
//            if (StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {
//                Integer clientId = jwtTokenProvider.getJwtFromId(jwt);
//                UserDetails userDetails = usDetailsService.loadUserById(clientId);
//                UsernamePasswordAuthenticationToken authenticationToken =
//                        new UsernamePasswordAuthenticationToken(userDetails,
//                                null, userDetails.getAuthorities());
//                authenticationToken.setDetails(new WebAuthenticationDetailsSource().
//                        buildDetails(httpServletRequest));
//                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//            }
//        } catch (Exception e) {
//            logger.error("failed authentication", e);
//            filterChain.doFilter(httpServletRequest, httpServletResponse);
//        }
//
//    }
//
//    public String getJwtFromRequest(HttpServletRequest request) {
//        String bearerToken = request.getHeader("Authorization");
//        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
//            return bearerToken.substring(7, bearerToken.length());
//        }
//        return null;
//    }
//
//}
