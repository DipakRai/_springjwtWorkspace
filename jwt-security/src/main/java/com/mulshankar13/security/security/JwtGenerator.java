package com.mulshankar13.security.security;

import org.springframework.stereotype.Component;

import com.mulshankar13.security.model.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator {

	public String generate(JwtUser jwtUser) {
		String secret = "youtube";
		Claims claims = Jwts.claims().setSubject(jwtUser.getUserName());
		claims.put("userId", String.valueOf(jwtUser.getId()));
		claims.put("role", jwtUser.getRole());
		return Jwts.builder()
				.setClaims(claims)
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();

	}

}
