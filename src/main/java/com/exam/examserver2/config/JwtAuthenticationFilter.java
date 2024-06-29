package com.exam.examserver2.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.exam.examserver2.service.Impl.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private JwtUtils jwtUtils;


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String requestTokenHeader = request.getHeader( "Authorization");
		System.out.println(requestTokenHeader);
		String username=null;
		String jwtToken= null;
		
		if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer")) {
			//yes
			jwtToken=requestTokenHeader.substring(7);
			try {
				username= this.jwtUtils.extractUsername(jwtToken);
				
			}
			catch(ExpiredJwtException e){
				e.printStackTrace();
				System.out.println("jwt token has expired");
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("error");
				
			}
			
		}
		else {
		System.out.println("invalid token , not start with Bearer string ");
		}
		
		
		//validate
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			final UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(username);
			
			if(this.jwtUtils.validateToken(jwtToken, userDetails)) {
				//token is valid
				
				UsernamePasswordAuthenticationToken usernamePasswodAuthentication=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities()); 
				usernamePasswodAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswodAuthentication);				
			}
		}
		else {
			System.out.println("Token is not valid ");
		}
		
		filterChain.doFilter(request, response);
		
	}

}
