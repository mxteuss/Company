package com.company.employees.infra;


    import com.company.employees.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    TokenService tokenService;
    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("=== SecurityFilter executado ===");
        System.out.println("Path: " + request.getRequestURI());

        var token = this.recoverToken(request);
        System.out.println("Token recuperado: " + token);

        String path = request.getRequestURI();

        if (path.contains("/auth/login")) {
            System.out.println("Pulando autenticação para /employees/save");
            filterChain.doFilter(request, response);
            return;
        }

        if (token != null) {
            System.out.println("Token não é null, validando...");
            var login = tokenService.validateToken(token);
            System.out.println("Login do token: " + login);

            if (login != null) {
                UserDetails user = userRepository.findByLogin(login);
                System.out.println("User encontrado: " + user);

                if (user != null) {
                    System.out.println("Authorities: " + user.getAuthorities());
                    UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContext context = SecurityContextHolder.getContext();
                    context.setAuthentication(authRequest);

                    System.out.println("Autenticação setada com sucesso");
                    HttpSession session = request.getSession(true);
                    session.setAttribute("SPRING_SECURITY_CONTEXT", context);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}