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
import com.agence.Gr3.backend.Logements.Model.Logement;

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

    @GetMapping("/")
    public ResponseEntity<String> rechercherTousLogement(@PathVariable int id,
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

    @PostMapping("modifier")
    public ResponseEntity<String> modifierLogement(@RequestBody Map<String, Object> requestBody) {

        // tester si le logement appartient à l'utilisateur

        return new ResponseEntity<String>("Logement modifié", HttpStatus.OK);

    }

    @PostMapping("activer")
    public ResponseEntity<String> activerLogement(@RequestBody Map<String, Object> requestBody) {

        // tester si le logement appartient à l'utilisateur
        // Activé ou désactivé en fonction du nouvel état

        return new ResponseEntity<String>("Logement activé", HttpStatus.OK);

    }

}