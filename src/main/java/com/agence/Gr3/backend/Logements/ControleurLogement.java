package com.agence.Gr3.backend.Logements;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.http.HttpHeaders;

import com.agence.Gr3.backend.Utilisateurs.Services.ServiceJwt;

import io.jsonwebtoken.Claims;

import com.agence.Gr3.backend.Logements.Repository.DaoLogement;
import com.agence.Gr3.backend.Logements.Services.ServiceLogement;
import com.agence.Gr3.backend.Utilisateurs.Model.*;
import com.agence.Gr3.backend.Utilisateurs.Repository.DaoUtilisateurs;
import com.agence.Gr3.Model.Adresse;
import com.agence.Gr3.backend.Logements.Model.*;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/logement")
public class ControleurLogement {

    private ServiceJwt serviceJwt;
    private ServiceLogement serviceLogement;

    public ControleurLogement(ServiceJwt serviceJwt, ServiceLogement serviceLogement) {
        this.serviceJwt = serviceJwt;
        this.serviceLogement = serviceLogement;

    }

    /**
     * S'occupe de l'insertion d'un logement dans la BD.
     * Traite la requête HTTP de type POST sur le endpoint /logement/creer.
     * Valide le jwt et le corps (body) de la requête. Une réponse HTTP défavorable
     * est renvoyée en cas d'erreur. S'il n'y a pas d'erreurs, un
     * appel est fait à la méthode "creer" du ServiceLogement en passant comme
     * paramètres le corps de la requête HTTP et le courriel extrait du jwt.
     * Si cet appel n'est pas un succès, une réponse HTTP défavorable
     * sera renvoyée. En cas de succès, une réponse HTTP.Ok sera renvoyée.
     *
     * 
     * 
     * @param authorizationHeader l'en-tête d'autorisation contenant l'identifiant
     *                            de l'utilisateur (courriel)
     * @param requestBody         les données relatives au logement à insérer dans
     *                            la BD
     * @return une réponse HTTP indiquant si l'insertion du logement est un succès
     *         ou un
     *         échec.
     */
    @PostMapping("creer")
    public ResponseEntity<String> creerLogement(@RequestHeader("Authorization") String authorizationHeader,
            @RequestBody Map<String, Object> requestBody) {

        System.out.println("methode: controleurLogement creer"); // DEBUG

        try {
            // Extraction et validation du Jwt reçu
            String jwt = serviceJwt.extraireJwt(authorizationHeader);
            Claims claims = serviceJwt.validerJwt(jwt);
            String courriel = claims.getSubject();

            System.out.println("methode: controleurLogement creer: JWT OK"); // DEBUG

            // TODO Validation de la permission

            // Validation du corps de la requête
            if (requestBody == null || requestBody.isEmpty()) {
                return new ResponseEntity<String>("Le corps de la requête est vide", HttpStatus.BAD_REQUEST);
            }

            System.out.println("methode: controleurLogement creer: BODY OK"); // DEBUG

            // Si tout est conforme, la méthode du service est appelée
            Logement logement = serviceLogement.creer(courriel, requestBody);

            if (logement == null) {

                System.out.println("methode: controleurLogement creer: logement NULL"); // DEBUG
                return new ResponseEntity<String>("Logement non-enregistré", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<String>("Logement enregitstré", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<String>("Accès interdit", HttpStatus.FORBIDDEN);

        }

    }

    /**
     * S'occupe de la modification d'un logement dans la BD.
     * Traite la requête HTTP de type PUT sur le endpoint /logement/modifier/{id}.
     * Valide le jwt et le corps (body) de la requête. Une réponse HTTP défavorable
     * est renvoyée en cas d'erreur. S'il n'y a pas d'erreurs, un
     * appel est fait à la méthode "modifier" du ServiceLogement en passant comme
     * paramètres l'identifiant du logement à modifier, le corps de la requête HTTP
     * et le courriel extrait du jwt. Si cet appel n'est pas un succès, une réponse
     * HTTP défavorable sera renvoyée. En cas de succès, une réponse HTTP.Ok sera
     * renvoyée.
     * 
     * @param id                  identifiant du logement à modifier
     * @param authorizationHeader l'en-tête d'autorisation contenant l'identifiant
     *                            de l'utilisateur (courriel)
     * @param requestBody         les données relatives au rdv à insérer dans
     *                            la BD
     * @return une réponse HTTP indiquant si la modification du logement est un
     *         succès ou un échec.
     */
    @PutMapping("/modifier/{id}")
    public ResponseEntity<String> modifierLogement(@PathVariable int id,
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody Map<String, Object> requestBody) {

        System.out.println("methode: controleurLogement modifier"); // DEBUG

        try {
            // Extraction et validation du Jwt reçu
            String jwt = serviceJwt.extraireJwt(authorizationHeader);
            Claims claims = serviceJwt.validerJwt(jwt);
            String courriel = claims.getSubject();

            // TODO Validation de la permission

            // Validation du corps de la requête
            if (requestBody == null || requestBody.isEmpty()) {
                return new ResponseEntity<String>("Le corps de la requête est vide", HttpStatus.BAD_REQUEST);
            }

            if (id == 0) {
                return new ResponseEntity<String>("Le paramètre de recherche est vide", HttpStatus.BAD_REQUEST);

            }

            // Si tout est conforme, la méthode du service est appelée
            Logement logement = serviceLogement.modifier(id, courriel, requestBody);

            if (logement == null) {
                return new ResponseEntity<String>("Logement non-enregistré", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<String>("Logement enregitstré", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<String>("Accès interdit", HttpStatus.FORBIDDEN);

        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<String> rechercherLogement(@PathVariable int id,
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody Map<String, Object> requestBody) {

        try {
            // Extraction et validation du Jwt reçu
            String jwt = serviceJwt.extraireJwt(authorizationHeader);
            Claims claims = serviceJwt.validerJwt(jwt);
            String courriel = claims.getSubject();

            // TODO Validation de la permission

            // Validation du corps de la requête
            if (requestBody == null) {
                return new ResponseEntity<String>("Le corps de la requête est vide", HttpStatus.BAD_REQUEST);
            }

            // Si tout est conforme, la méthode du service est appelée
            Logement logement = serviceLogement.modifier(id, courriel, requestBody);

            if (logement == null) {
                return new ResponseEntity<String>("Logement non-enregistré", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<String>("Logement enregitstré", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<String>("Accès interdit", HttpStatus.FORBIDDEN);

        }
    }

    /**
     * S'occupe de la récupération, à partir de la BD ,des logements associés à un
     * utilisateur. Traite la requête HTTP de type GET sur le endpoint
     * /logement/afficher/utilisateur. Valide le jwt et le corps (body) de la
     * requête. Une réponse HTTP défavorable est renvoyée en cas d'erreur. S'il n'y
     * a pas d'erreurs, un appel est fait à la méthode "rechercher" du
     * ServiceLogement en passant comme paramètre le courriel extrait du jwt. Si cet
     * appel n'est pas un succès, une réponse HTTP défavorable sera renvoyée. En cas
     * de succès, une réponse HTTP.Ok sera renvoyée.
     * 
     * @param authorizationHeader l'en-tête d'autorisation contenant l'identifiant
     *                            de l'utilisateur (courriel)
     * @return une réponse HTTP indiquant si la récupération des logements est un
     *         succès ou un échec.
     */
    @GetMapping("/afficher/utilisateur")
    public ResponseEntity<Object> rechercherLogement(
            @RequestHeader("Authorization") String authorizationHeader) {

        try {
            // Extraction et validation du Jwt reçu
            String jwt = serviceJwt.extraireJwt(authorizationHeader);
            Claims claims = serviceJwt.validerJwt(jwt);
            String courriel = claims.getSubject();

            System.out.println("Le courriel recu: " + courriel);

            // TODO Validation de la permission

            // Si tout est conforme, la méthode du service est appelée
            List<Logement> logements = serviceLogement.rechercher(courriel);

            if (logements == null) {
                return new ResponseEntity<Object>(logements, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<Object>(logements, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<Object>("Accès interdit", HttpStatus.FORBIDDEN);

        }
    }

    /**
     * S'occupe de la récupération, à partir de la BD ,de tous les logements.
     * Traite la requête HTTP de type GET sur le endpoint /logement/afficher.
     * Valide le jwt et le corps (body) de la requête. Une réponse HTTP défavorable
     * est renvoyée en cas d'erreur. S'il n'y a pas d'erreurs, un appel est fait à
     * la méthode "rechercher" du ServiceLogement en passant comme paramètre le
     * courriel extrait du jwt. Si cet appel n'est pas un succès, une réponse HTTP
     * défavorable sera renvoyée. En cas de succès, une réponse HTTP.Ok sera
     * renvoyée.
     * 
     * @param authorizationHeader
     * @return
     */
    @GetMapping("/afficher")
    public ResponseEntity<Object> rechercherTousLogements(
            @RequestHeader("Authorization") String authorizationHeader) {

        try {
            // Extraction et validation du Jwt reçu
            String jwt = serviceJwt.extraireJwt(authorizationHeader);
            Claims claims = serviceJwt.validerJwt(jwt);

            // TODO Validation de la permission

            // Si tout est conforme, la méthode du service est appelée
            List<Logement> logements = serviceLogement.rechercherTous();

            if (logements == null) {
                return new ResponseEntity<Object>(logements, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<Object>(logements, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<Object>("Accès interdit", HttpStatus.FORBIDDEN);

        }
    }

}