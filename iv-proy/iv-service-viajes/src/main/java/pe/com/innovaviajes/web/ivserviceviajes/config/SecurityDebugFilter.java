package pe.com.innovaviajes.web.ivserviceviajes.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class SecurityDebugFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(SecurityDebugFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        log.info(">>> MICROSERVICIO - Recibiendo: {} {}", request.getMethod(), request.getRequestURI());
        log.info(">>> MICROSERVICIO - Authorization Header: {}", request.getHeader("Authorization"));

        filterChain.doFilter(request, response);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            log.info(">>> MICROSERVICIO - Usuario Autenticado: {}", auth.getName());
            log.info(">>> MICROSERVICIO - Autoridades: {}", auth.getAuthorities());
        } else {
            log.warn(">>> MICROSERVICIO - No hay contexto de seguridad (Anónimo)");
        }
    }
}
