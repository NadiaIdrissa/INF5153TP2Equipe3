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

    public FormulairesUtilisateur() {
        this.restTemplate = new RestTemplate();

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
    public String creerLocataire(List<Permission> permissions, Scanner scanner, StringBuilder JWT)
            throws RestClientException {

        System.out.println("SERVICE CREER UTILISATEUR");

        String url = "http://localhost:8080/utilisateur/creer";

        // Récupération des informations et affectation aux variables.
        System.out.println("Saisir votre adresse courriel: ");
        String courriel = scanner.nextLine();
        System.out.println("Saisir votre mot de passe: ");
        String mdp = scanner.nextLine();

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
    public String creerRepresentant(List<Permission> permissions, Scanner scanner, StringBuilder JWT)
            throws RestClientException {
        String url = "http://localhost:8080/utilisateur/creer";

        System.out.println("Saisir votre adresse courriel: ");
        String courriel = scanner.nextLine();

        System.out.println("Saisir votre mot de passe: ");
        String mdp = scanner.nextLine();

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();

        body.add("courriel", courriel);
        body.add("mdp", mdp);
        body.add("role", "representant");

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
    public String creerAgent(List<Permission> permissions, Scanner scanner, StringBuilder JWT)
            throws RestClientException {
        String url = "http://localhost:8080/utilisateur/creer";

        System.out.println("Saisir votre adresse de courriel: ");
        String courriel = scanner.nextLine();

        System.out.println("Saisir votre mot de passe: ");
        String mdp = scanner.nextLine();

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();

        body.add("courriel", courriel);
        body.add("mdp", mdp);
        body.add("role", "agent");

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

    public String seConnecter(List<Permission> permissions, Scanner scanner, StringBuilder JWT)
            throws RestClientException {

        System.out.println("Connexion:");

        String message = null;
        String url = "http://localhost:8080/utilisateur/connexion";

        System.out.println("Saisir votre adresse de courriel: ");
        String courriel = scanner.nextLine();
        System.out.println("Saisir votre mot de passe: ");
        String mdp = scanner.nextLine();

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
                    JWT.setLength(0);
                    JWT.append(authorizationHeader.substring(7));
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

    public String seDeconnecter(List<Permission> permissions, Scanner scanner, StringBuilder JWT) {

        JWT.setLength(0);
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
    public String creerOuMettreAJourProfil(List<Permission> permissions, Scanner scanner, String jwt)
            throws RestClientException {
        String url = "http://localhost:8080/utilisateur/profil";

        // Informations de l'utilisateur

        System.out.print("Saisir le nom de l'utilisateur: ");
        String nom = scanner.nextLine();

        System.out.print("Saisir le prénom de l'utilisateur: ");
        String prenom = scanner.nextLine();

        System.out.print("Saisir le numéro de téléphone: ");
        String telephone = scanner.nextLine();

        // Adresse de l'utilisateur
        System.out.println("Saisir les détails de l'adresse de l'utilisateur");

        System.out.print("Saisir le numéro civique: ");
        String noCivique = scanner.nextLine();

        System.out.print("Saisir la suite de l'adresse (optionnel): ");
        String suite = scanner.nextLine();

        System.out.print("Saisir le nom de la rue: ");
        String rue = scanner.nextLine();

        System.out.print("Saisir le code postal: ");
        String codePostal = scanner.nextLine();

        System.out.print("Saisir la ville: ");
        String ville = scanner.nextLine();

        System.out.print("Saisir la province: ");
        String province = scanner.nextLine();

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("nom", nom);
        body.add("prenom", prenom);
        body.add("telephone", telephone);
        body.add("adresse.noCivique", noCivique);
        body.add("adresse.suite", suite);
        body.add("adresse.rue", rue);
        body.add("adresse.codePostal", codePostal);
        body.add("adresse.ville", ville);
        body.add("adresse.province", province);

        return restTemplate.postForObject(url, body, String.class);
    }

    Boolean validerFormatChiffre(String saisie) {

        try {

            if (saisie.isEmpty()) {
                System.out.println("Saisie vide! Veuillez recommencer.");
                return false;
            }

            Integer choix = Integer.parseInt(saisie);
            return true;

        } catch (NumberFormatException e) {
            System.out.println("Saisie invalide! Choisissez un chiffre.");
            return false;

        }

    }

}
