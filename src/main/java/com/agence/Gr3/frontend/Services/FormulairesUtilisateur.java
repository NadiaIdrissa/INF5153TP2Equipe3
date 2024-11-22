package com.agence.Gr3.frontend.Services;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import com.agence.Gr3.backend.Utilisateurs.Model.Permission;
import com.agence.Gr3.backend.Utilisateurs.Model.Role;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Enumeration;
import java.util.List;

@Service
public class FormulairesUtilisateur {

    // Injection d'un rest template pour l'envoi de requêtes HTTP
    private final RestTemplate restTemplate;
    private final ValidationFormulaire validationFormulaire;

    public FormulairesUtilisateur(RestTemplate restTemplate, ValidationFormulaire validationFormulaire) {
        this.restTemplate = restTemplate;
        this.validationFormulaire = validationFormulaire;

    }

    /*
     * COMMENTAIRE GÉNÉRAL
     * 
     * Toutes les méthodes de la classe sont construites de la même façon, dans le
     * but de simuler la complétion et l'envoi d'un formulaire au backend via une
     * requête HTTP ainsi que de gérer la réponse HTTP obtenue de celui-ci.
     * 
     * 
     * 
     * Chaque méthode:
     * 1) demande à l'utilisateur de saisir les données requises.
     * 2) affecte ces données aux variables
     * 3) ajoute les variables à une structure de type MultiValueMap, représentant
     * le body de la requête http.
     * 4) envoie au bon endpoint la requête http du type appropriée contenant le
     * body.
     * 5) retourne une réponse HTTP.
     * 6) les éléments de la réponse sont utilisés
     * 
     * Note: les paramètres ne sont pas tous nécessairement utilisés, l'objectif est
     * d'avoir des méthodes uniformes pour simplifier leur code d'invocation.
     * 
     * 
     */

    /**
     * Crée un compte utilisateur de type "LOCATAIRE" en envoyant une requête POST
     * à un serveur backend pour enregistrer l'utilisateur avec les informations
     * saisies dans la console (adresse courriel, mot de passe).
     * 
     * Cette méthode récupère les informations nécessaires (courriel, mot de passe)
     * auprès de l'utilisateur via un scanner, les encapsule dans un objet
     * `MultiValueMap` et les envoie au serveur via une requête POST.
     * 
     * @return La réponse du serveur sous forme de chaîne de caractères.
     * @throws RestClientException Si une erreur survient lors de l'exécution de la
     *                             requête HTTP.
     */
    public String creerLocataire(List<Permission> permissions, Scanner scanner, StringBuilder jwt)
            throws RestClientException {

        System.out.println("SERVICE CREER UTILISATEUR");

        String url = "http://localhost:8080/utilisateur/creer";

        // Récupération des informations et affectation aux variables.
        String courriel = validationFormulaire.validationNonNul("Veuillez saisir votre adresse courriel: \n", scanner);
        String mdp = validationFormulaire.validationNonNul("Veuillez saisir votre mot de passe: \n", scanner);

        // Construction du body (corps de la requête HTTP)
        HashMap<String, String> body = new HashMap<>();
        body.put("courriel", courriel);
        body.put("mdp", mdp);
        body.put("role", "locataire");

        // Requête HTTP
        return restTemplate.postForObject(url, body, String.class);

    }

    /**
     * Crée un compte utilisateur de type "REPRÉSENTANT" en envoyant une requête
     * POST à un serveur backend pour enregistrer l'utilisateur avec les
     * informations saisies dans la console (adresse courriel, mot de passe).
     * 
     * Cette méthode récupère les informations nécessaires (courriel, mot de passe)
     * auprès de l'utilisateur via un scanner, les encapsule dans un objet
     * `MultiValueMap` et les envoie au serveur via une requête POST.
     * 
     * @return La réponse du serveur sous forme de chaîne de caractères.
     * @throws RestClientException Si une erreur survient lors de l'exécution de la
     *                             requête HTTP.
     */
    public String creerRepresentant(List<Permission> permissions, Scanner scanner, StringBuilder jwt)
            throws RestClientException {
        String url = "http://localhost:8080/utilisateur/creer";

        // Récupération des informations et affectation aux variables.
        String courriel = validationFormulaire.validationNonNul("Veuillez saisir votre adresse courriel: \n", scanner);
        String mdp = validationFormulaire.validationNonNul("Veuillez saisir votre mot de passe: \n", scanner);

        // Construction du body (corps de la requête HTTP)
        HashMap<String, String> body = new HashMap<>();
        body.put("courriel", courriel);
        body.put("mdp", mdp);
        body.put("role", "representant");

        return restTemplate.postForObject(url, body, String.class);
    }

    /**
     * Crée un compte utilisateur de type "AGENT" en envoyant une requête POST
     * à un serveur backend pour enregistrer l'utilisateur avec les informations
     * saisies dans la console (adresse courriel, mot de passe).
     * 
     * Cette méthode récupère les informations nécessaires (courriel, mot de passe)
     * auprès de l'utilisateur via un scanner, les encapsule dans un objet
     * `MultiValueMap` et les envoie au serveur via une requête POST.
     * 
     * @return La réponse du serveur sous forme de chaîne de caractères.
     * @throws RestClientException Si une erreur survient lors de l'exécution de la
     *                             requête HTTP.
     */
    public String creerAgent(List<Permission> permissions, Scanner scanner, StringBuilder jwt)
            throws RestClientException {
        String url = "http://localhost:8080/utilisateur/creer";

        // Récupération des informations et affectation aux variables.
        String courriel = validationFormulaire.validationNonNul("Veuillez saisir votre adresse courriel: \n", scanner);
        String mdp = validationFormulaire.validationNonNul("Veuillez saisir votre mot de passe: \n", scanner);

        // Construction du body (corps de la requête HTTP)
        HashMap<String, String> body = new HashMap<>();
        body.put("courriel", courriel);
        body.put("mdp", mdp);
        body.put("role", "agent");

        return restTemplate.postForObject(url, body, String.class);
    }

    /**
     * Cette méthode permet à un utilisateur de se connecter en envoyant une requête
     * HTTP GET au serveur. La requête est envoyée au serveur avec les identifiants
     * encodés dans l'en-tête "Authorization".
     * 
     * @return La réponse du serveur sous forme de chaîne de caractères.
     * @throws RestClientException Si une erreur survient lors de l'exécution de la
     *                             requête HTTP.
     */

    public String seConnecter(List<Permission> permissions, Scanner scanner, StringBuilder jwt)
            throws RestClientException {

        System.out.println("Connexion:");

        String message = null;
        String url = "http://localhost:8080/utilisateur/connexion";

        String courriel = validationFormulaire.validationNonNul("Veuillez saisir votre adresse courriel: \n", scanner);
        String mdp = validationFormulaire.validationNonNul("Veuillez saisir votre mot de passe: \n", scanner);

        // Création d'un header d'autorisation basé sur les données de l'utilisateur
        String identifiant = courriel + ":" + mdp;
        String identifiantEncode = Base64.getEncoder().encodeToString(identifiant.getBytes());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + identifiantEncode);
        HttpEntity<String> entite = new HttpEntity<>(headers);

        try {

            ResponseEntity<List<Permission>> reponse = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    entite,
                    new ParameterizedTypeReference<List<Permission>>() {
                    });

            if (reponse.getStatusCode() == HttpStatus.OK) {

                HttpHeaders responseHeaders = reponse.getHeaders();
                String authorizationHeader = responseHeaders.getFirst(HttpHeaders.AUTHORIZATION);

                if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                    // Modification des objets JWT et permissions
                    jwt.setLength(0);
                    jwt.append(authorizationHeader.substring(7));
                    permissions.clear();
                    permissions.addAll(reponse.getBody());
                    message = "Connexion réussie!";

                }

            } else {

                System.out.println("Le status n'est pas OK");
                message = "Erreur de connexion";

            }

        } catch (RestClientException e) {
            System.out.println("EXCEPTION");
            message = "Erreur de connexion";

        }

        return message;

    }

    public String seDeconnecter(List<Permission> permissions, Scanner scanner, StringBuilder jwt) {

        jwt.setLength(0);
        permissions.clear();
        permissions.addAll(Role.INVITE.getPermissions());

        return "Déconnexion réussie";

    }

    // TODO UTILISER LE JWT.

    /**
     * Envoie une requête POST pour mettre à jour le profil utilisateur sans créer
     * d'objets Java. Les données sont envoyées directement dans un MultiValueMap
     * sous forme de paramètres de formulaire.
     *
     * @return La réponse du serveur sous forme de chaîne de caractères (par
     *         exemple, un message de confirmation ou d'erreur).
     * @throws RestClientException Si une erreur survient lors de l'exécution de la
     *                             requête HTTP.
     */
    public String modifierProfil(List<Permission> permissions, Scanner scanner, StringBuilder jwt)
            throws RestClientException {

        String message = null;
        String url = "http://localhost:8080/utilisateur/profil";

        // Informations de l'utilisateur
        String nom = validationFormulaire.validationNonNul("Veuillez saisir votre nom: \n", scanner);
        String prenom = validationFormulaire.validationNonNul("Veuillez saisir votre prénom: \n", scanner);
        String telephone = validationFormulaire.validationNonNul("Veuillez saisir votre no de téléphone: \n", scanner);
        int noCivique = validationFormulaire.validationNombrePositif("Veuillez saisir le no civique: \n", scanner);
        int suite = validationFormulaire.validationNombreOptionnel("Veuillez saisir la suite (optionnel): \n", scanner);
        String rue = validationFormulaire.validationNonNul("Veuillez saisir votre la rue: \n", scanner);
        String codePostal = validationFormulaire.validationNonNul("Veuillez saisir le code postal: \n", scanner);
        String ville = validationFormulaire.validationNonNul("Veuillez saisir la ville: \n", scanner);
        String province = validationFormulaire.validationNonNul("Veuillez saisir la province: \n", scanner);

        // Construction du body (corps de la requête HTTP)
        HashMap<String, Object> body = new HashMap<>();
        body.put("nom", nom);
        body.put("prenom", prenom);
        body.put("telephone", telephone);
        body.put("noCivique", noCivique);
        body.put("suite", suite);
        body.put("rue", rue);
        body.put("codePostal", codePostal);
        body.put("ville", ville);
        body.put("province", province);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwt);

        // Le constructeur attend le body comme premier paramètre
        HttpEntity<HashMap<String, Object>> entite = new HttpEntity<>(body, headers);

        try {

            ResponseEntity<String> reponse = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    entite,
                    String.class);

            if (reponse.getStatusCode() == HttpStatus.OK) {

                return "modification réussie";

            } else {

                return "modification non réussie";

            }

        } catch (RestClientException e) {

            return "modification non réussie";

        }

    }

}
