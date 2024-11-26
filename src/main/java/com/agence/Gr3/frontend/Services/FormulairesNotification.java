package com.agence.Gr3.frontend.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.agence.Gr3.backend.Utilisateurs.Model.Permission;

@Service
public class FormulairesNotification {

    RestTemplate restTemplate;

    public FormulairesNotification(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

    }

    public String afficherNotifications(List<Permission> permissions, Scanner scanner, StringBuilder jwt) {

        String url = "http://localhost:8080/notifications/utilisateur";

        // Construction de l'en-tête de la requête (header)
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwt);

        // Construction de la requête
        HttpEntity<HashMap<String, Object>> requete = new HttpEntity<>(headers);

        try {

            ResponseEntity<String> reponse = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    requete,
                    String.class);

            if (reponse.getStatusCode() == HttpStatus.OK) {

                if (reponse.getBody().equals("[]")) {
                    return "Il n'y a pas de notifications à afficher";
                }

                return reponse.getBody();

            } else {
                return "Affichage notifications non réussi";

            }

        } catch (RestClientException e) {
            return "Affichage notification non réussi";

        }

    }

}
