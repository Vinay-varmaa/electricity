package bill.management.electricity.filter;

import bill.management.electricity.service.JWTService;
import bill.management.electricity.service.UserInfoUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserInfoUserDetailService userInfoUserDetailService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
          String authHeader=request.getHeader("Authorization");
          String token=null;
          String username=null;
          // Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJub29rZXNoIiwiaWF0IjoxNjk1MTM3NzY0LCJleHAiOjE2OTUxMzk1NjR9.erzP5reAyoAkGu8DvWee8CT2-6g-lYwy3PGnQ0fbGTY
          if(authHeader!=null&&authHeader.startsWith("Bearer ")){
              token=authHeader.substring(7);
             username= jwtService.extractUserName(token);
          }
          if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){

             UserDetails userDetails= userInfoUserDetailService.loadUserByUsername(username);
             if(jwtService.validateToken(token,userDetails)){
                 UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken("userDetails",null,userDetails.getAuthorities());
                 authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                 SecurityContextHolder.getContext().setAuthentication(authenticationToken);
             }
          }
          filterChain.doFilter(request,response);
    }
}
