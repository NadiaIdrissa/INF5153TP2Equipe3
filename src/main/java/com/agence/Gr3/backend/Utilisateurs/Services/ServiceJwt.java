package com.agence.Gr3.backend.Utilisateurs.Services;

import org.springframework.stereotype.Service;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;

import com.agence.Gr3.backend.Utilisateurs.Model.Identifiant;

@Service
public class ServiceJwt {

    private static final String SECRET_KEY_BASE64 = "s/2kcb+7fq9i6lQ+9h/5O9u4uwOwT5O9u+pR5g9/XYY=";
    private static final long delai = 2 * 1800000; // 1800000 millisecondes ou 30 minutes

    private final SecretKey cleSecrete;

    public ServiceJwt() {
        this.cleSecrete = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY_BASE64));

    }

    public String creerJwt(Identifiant identifiant) {
        Date creation = new Date();
        Date expiration = new Date();
        expiration.setTime(creation.getTime() + delai);

        final String token = Jwts.builder().subject(identifiant.getCourriel()).issuedAt(creation).expiration(expiration)
                .signWith(this.cleSecrete).compact();

        return token;

    }

    public Claims validerJwt(String token) {

        try {
            Claims claims = Jwts.parser().verifyWith(this.cleSecrete).build().parseSignedClaims(token).getPayload();
            return claims;

        } catch (JwtException jwtException) {
            throw jwtException;

        }

    }

}
