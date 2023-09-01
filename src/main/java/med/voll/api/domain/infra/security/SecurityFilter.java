package med.voll.api.domain.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = retrieveToken(request);

        System.out.println(tokenJWT);

        filterChain.doFilter(request, response);

    }

    private String retrieveToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader == null){
            throw new RuntimeException("JWT token not sent in Authorization header!");
        }
        return authorizationHeader.replace("Bearer ", "");
    }
}
