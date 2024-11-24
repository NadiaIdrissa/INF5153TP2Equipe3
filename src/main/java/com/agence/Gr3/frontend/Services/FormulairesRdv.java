package com.agence.Gr3.frontend.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.agence.Gr3.backend.Utilisateurs.Model.Permission;

@Service
public class FormulairesRdv {

    private final RestTemplate restTemplate;
    private final ValidationFormulaire validationFormulaire;

    public FormulairesRdv(RestTemplate restTemplate, ValidationFormulaire validationFormulaire) {
        this.restTemplate = restTemplate;
        this.validationFormulaire = validationFormulaire;

    }

    public String demanderVisite(List<Permission> permissions, Scanner scanner, StringBuilder jwt)
            throws RestClientException {

        String url = "http://localhost:8080/rdv/creer";

        // Construction du body (corps de la requête HTTP)
        HashMap<String, Object> body = new HashMap<>();
        body.put("idLogement",
                validationFormulaire.validationNombrePositif(
                        "Veuillez saisir l'identifiant du logement que vous voulez visiter: \n", scanner));
        body.put("dateHeurePropose",
                validationFormulaire.validationLocalDateTime(
                        "Veuillez saisir la date proposée selon le format suivant jj/MM/aaaa HH:mm (ex: 23/11/2024 15:30): \n",
                        scanner));
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwt);

        // Le constructeur attend le body comme premier paramètre
        HttpEntity<HashMap<String, Object>> requete = new HttpEntity<>(body, headers);

        try {

            ResponseEntity<String> reponse = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    requete,
                    String.class);

            System.out.println("Requete fonctionne.");

            if (reponse.getStatusCode() == HttpStatus.OK) {
                return "ajout visite réussie";

            } else {
                return "ajout visite non réussie";

            }

        } catch (RestClientException e) {
            return "ajout visite non réussie";

        }

    }

    public String confirmerVisite(List<Permission> permissions, Scanner scanner, StringBuilder jwt)
            throws RestClientException {

        String url = "http://localhost:8080/rdv/confirmer";

        int id = validationFormulaire.validationNombrePositif(
                "Veuillez saisir l'identifiant du rdv à annuler: \n",
                scanner);

        // Ajout du paramètre à l'URL
        String urlFinal = UriComponentsBuilder.fromHttpUrl(url).path("/{id}").buildAndExpand(id).toUriString();

        // Construction du body (corps de la requête HTTP)
        HashMap<String, Object> body = new HashMap<>();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwt);

        // Le constructeur attend le body comme premier paramètre
        HttpEntity<HashMap<String, Object>> requete = new HttpEntity<>(body, headers);

        try {

            ResponseEntity<String> reponse = restTemplate.exchange(
                    urlFinal,
                    HttpMethod.PUT,
                    requete,
                    String.class);

            if (reponse.getStatusCode() == HttpStatus.OK) {
                return "Annulation réussie";

            } else {
                return "Annulation non réussie";

            }

        } catch (RestClientException e) {
            return "Annulation non réussie";

        }

    }

    public String annulerVisite(List<Permission> permissions, Scanner scanner, StringBuilder jwt)
            throws RestClientException {

        String url = "http://localhost:8080/rdv/annuler";

        int id = validationFormulaire.validationNombrePositif(
                "Veuillez saisir l'identifiant du rdv à annuler: \n",
                scanner);

        // Ajout du paramètre à l'URL
        String urlFinal = UriComponentsBuilder.fromHttpUrl(url).path("/{id}").buildAndExpand(id).toUriString();

        // Construction du body (corps de la requête HTTP)
        HashMap<String, Object> body = new HashMap<>();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwt);

        // Le constructeur attend le body comme premier paramètre
        HttpEntity<HashMap<String, Object>> requete = new HttpEntity<>(body, headers);

        try {

            ResponseEntity<String> reponse = restTemplate.exchange(
                    urlFinal,
                    HttpMethod.PUT,
                    requete,
                    String.class);

            if (reponse.getStatusCode() == HttpStatus.OK) {
                return "Annulation réussie";

            } else {
                return "Annulation non réussie";

            }

        } catch (RestClientException e) {
            return "Annulation non réussie";

        }

    }

    public String modifierVisite(List<Permission> permissions, Scanner scanner, StringBuilder jwt)
            throws RestClientException {

        String url = "http://localhost:8080/rdv/modifier";

        int id = validationFormulaire.validationNombrePositif(
                "Veuillez saisir l'identifiant du rdv à modifier: \n",
                scanner);

        // Ajout du paramètre à l'URL
        String urlFinal = UriComponentsBuilder.fromHttpUrl(url).path("/{id}").buildAndExpand(id).toUriString();

        // Construction du body (corps de la requête HTTP)
        HashMap<String, Object> body = new HashMap<>();
        body.put("dateHeurePropose",
                validationFormulaire.validationLocalDateTime(
                        "Veuillez saisir la nouvelle date proposée selon le format suivant jj/MM/aaaa HH:mm (ex: 23/11/2024 15:30): \n",
                        scanner));

        // Construction de l'en-tête de la requête (header)
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwt);

        // Construction de la requête
        HttpEntity<HashMap<String, Object>> requete = new HttpEntity<>(body, headers);

        try {

            ResponseEntity<String> reponse = restTemplate.exchange(
                    urlFinal,
                    HttpMethod.PUT,
                    requete,
                    String.class);

            if (reponse.getStatusCode() == HttpStatus.OK) {
                return "Modification réussie";

            } else {
                return "Modification non réussie";

            }

        } catch (RestClientException e) {
            return "Modification non réussie";

        }

    }

}
