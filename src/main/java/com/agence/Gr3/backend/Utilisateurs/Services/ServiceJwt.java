package com.agence.Gr3.backend.Utilisateurs.Services;

import org.springframework.stereotype.Service;
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

    /**
     * Génère un jeton JWT (JSON web token) signé (avec une clé secrète) à partir du
     * courriel de l'utilisateur.
     * 
     * @param identifiant l'objet contenant le courriel de l'utilisateur
     * @return le jeton JWT
     * @throws IllegalArgumentException si l'identifiant ne contient pas d'adresse
     *                                  courriel valide
     */
    public String creerJwt(Identifiant identifiant) {

        System.out.println("methode: creerJwt"); // DEBUG
        String courriel = identifiant.getCourriel();

        if (courriel == null || courriel.trim().isEmpty()) {
            throw new IllegalArgumentException("L'identifiant doit contenir une adresse courriel");

        }

        Date creation = new Date();
        Date expiration = new Date();
        expiration.setTime(creation.getTime() + delai);

        final String token = Jwts.builder().subject(identifiant.getCourriel()).issuedAt(creation).expiration(expiration)
                .signWith(this.cleSecrete).compact();

        return token;

    }

    /**
     * Extrait le jeton JWT de l'en-tête d'autorisation de la requête HTTP.
     * 
     * Vérifie si celle-ci possède le préfixe "Bearer". Si cela est le cas, la
     * méthode retournera le jeton sans le préfixe. Si l'en-tête est inexistante, ou
     * si le préfixe attendu n'existe pas, la méthode retournera null.
     * 
     * @param authorizationHeader l'en-tête d'autorisation
     * @return le jeton JWT extrait de l'en-tête ou null si celle-ci n'est pas
     *         conforme
     * @throws IllegalArgumentException si l'en-tête d'autorisation n'est pas
     *                                  conforme
     */

    public String extraireJwt(String authorizationHeader) {

        System.out.println("extraireJwt: creerJwt"); // DEBUG

        try {
            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                throw new IllegalArgumentException("L'en-tête d'autorisation n'est pas conforme.");
            }

            // Extraction du jeton JWT (Suppression de "Bearer ")
            return authorizationHeader.replace("Bearer ", "");

        } catch (IllegalArgumentException e) {

            throw e;
        }
    }

    /**
     * Effectue la validation du jeton au moyen de la cléSecrete prédéterminée. Si
     * le jeton est valide, les informations du jeton (claims) seront extraites et
     * retournées.
     * 
     * @param token le jeton JWT à valider
     * @return les informations (claims) extraites du jeton
     * @throws JwtException si le jeton est invalide ou si une erreur se produit
     *                      lors de la vérification
     */
    public Claims validerJwt(String token) {

        System.out.println("validerJwt: creerJwt"); // DEBUG

        try {
            Claims claims = Jwts.parser().verifyWith(this.cleSecrete).build().parseSignedClaims(token).getPayload();
            return claims;

        } catch (JwtException jwtException) {
            throw jwtException;

        }

    }

}
