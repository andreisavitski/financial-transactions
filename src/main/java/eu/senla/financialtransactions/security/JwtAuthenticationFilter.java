package eu.senla.financialtransactions.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static eu.senla.financialtransactions.constant.AppConstants.*;
import static io.jsonwebtoken.io.Decoders.BASE64;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Value(JWT_KEY)
    private String jwtKey;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        final String header = request.getHeader(HEADER_NAME);
        final String token;
        String username = null;
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (header != null && header.startsWith(BEARER_PREFIX)) {
            token = header.substring(7);
            final SecretKey secret = Keys.hmacShaKeyFor(BASE64.decode(jwtKey));
            final Claims claims = Jwts.parser()
                    .verifyWith(secret)
                    .build()
                    .parseSignedClaims(token).getPayload();
            username = claims.getSubject();
            final String authorityString = claims.get(AUTHORITIES, String.class);
            authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authorityString);
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            final UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
