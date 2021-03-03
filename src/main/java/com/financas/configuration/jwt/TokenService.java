package com.financas.configuration.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${financas.jwt.secret}")
	private String SECRET;

	@Value("${financas.jwt.expiration}")
	private String EXPIRATION;

	public String gerarToken(Authentication authetication) {

		return Jwts.builder().setSubject(authetication.getName()).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(EXPIRATION)))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();

	}

	public boolean validarToken(String token) {
		try {
			Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String getUsername(String token) {
		return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();

	}

}
