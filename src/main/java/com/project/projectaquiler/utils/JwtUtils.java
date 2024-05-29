package com.project.projectaquiler.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

  //variables en entorno del sistema 🔒
  private String privateKey = System.getenv("KEY_JWT");
  private String userGenarator = System.getenv("USER_GENERATOR");

  //created token
  public String createToken(Authentication authentication) {
    Algorithm algorithm = Algorithm.HMAC256(this.privateKey);
    String username = authentication.getPrincipal().toString();

    String authorities = authentication
      .getAuthorities()
      .stream()
      .map(GrantedAuthority::getAuthority)
      .collect(Collectors.joining(","));

    String token = JWT
      .create()
      .withIssuer(this.userGenarator)
      .withSubject(username)
      .withClaim("authorities", authorities)
      .withIssuedAt(new Date())
      .withExpiresAt(new Date(System.currentTimeMillis() + 1800000))
      .withJWTId(UUID.randomUUID().toString())
      .withNotBefore(new Date(System.currentTimeMillis()))
      .sign(algorithm);

    return token;
  }

  // validaded token
  public DecodedJWT validateToken(String token) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(this.privateKey);
      JWTVerifier verifier = JWT
        .require(algorithm)
        .withIssuer(this.userGenarator)
        .build();
        DecodedJWT decodedJWT =  verifier.verify(token);
        return decodedJWT;
    } catch (JWTVerificationException exception) {
      throw new JWTVerificationException("Token invalid, no authorized");
    }
  }

  public String extracUsername(DecodedJWT decodedJWT){
    return decodedJWT.getSubject().toString();
  }

  public Claim getSpecificClaim(DecodedJWT decodedJWT, String claimName){
    return decodedJWT.getClaim(claimName);
  }

  public Map<String, Claim> getAllClaims(DecodedJWT decodedJWT){
    return decodedJWT.getClaims();
  }

  

}
