package domain.travel.travel_itinerary.security;

import domain.travel.travel_itinerary.domain.enums.RoleEnum;
import jakarta.persistence.EntityManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final UserDetailServiceImpl userService;
    private final EntityManager em;

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            String authToken = request.getHeader("Authorization");
            if (authToken != null && authToken.startsWith("Bearer ")) {
                String token = authToken.substring(7);
                if ( jwtProvider.validateToken(token)) {
                    String phoneNumber = jwtProvider.getUserByPhoneNumber(token);
                    UserDetails result = userService.loadUserByUsername(phoneNumber);
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(result, null, result.getAuthorities());

//                    Check role if role == client then get data no soft delete
                    if(jwtProvider.getRole(token).equals(RoleEnum.CLIENT.toString())){
                        Session session = em.unwrap(Session.class);
                        session.enableFilter("deleteFilter").setParameter("isDeleted", false);
                    } else {
                        Session session = em.unwrap(Session.class);
                        session.disableFilter("deleteFilter");
                    }
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (RuntimeException e) {
            log.error("Error while processing request {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            SecurityContextHolder.clearContext();
            response.getWriter().write("Unauthorized: " + e.getMessage());
            return;
        }

//        Session session = em.unwrap(Session.class);
//        session.enableFilter("deleteFilter").setParameter("isDeleted", false);

        filterChain.doFilter(request, response);
    }
}
