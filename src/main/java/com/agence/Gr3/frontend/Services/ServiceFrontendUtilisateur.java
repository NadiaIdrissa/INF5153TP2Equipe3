package com.agence.Gr3.frontend.Services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.agence.Gr3.backend.Utilisateurs.Model.Identifiant;

import org.springframework.http.HttpHeaders;
import java.util.Scanner;
import java.util.Base64;

@Service
public class ServiceFrontendUtilisateur {

    // Injection d'un rest template pour l'envoi de requêtes HTTP
    private final RestTemplate restTemplate;

    public ServiceFrontendUtilisateur() {
        this.restTemplate = new RestTemplate();

    }

    /*
     * COMMENTAIRE GÉNÉRAL
     * 
     * Toutes les méthodes de la classe sont construites de la même façon, dans le
     * but de simuler la complétion et l'envoi d'un formulaire au backend.
     * 
     * Chaque méthode:
     * 1) demande à l'utilisateur d'Saisir les données requises.
     * 2) affecte ces données aux variables
     * 3) ajoute les variables à une structure de type MultiValueMap, représentant
     * le body de la requête http.
     * 4) envoie au bon endpoint la requête http du type appropriée contenant le
     * body.
     * 5) retourne une réponse http sous forme de chaîne de caractères.
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
    public String creerLocataire() throws RestClientException {

        System.out.println("SERVICE CREER UTILISATEUR");

        String url = "http://localhost:8080/utilisateur/creer";

        Scanner scanner = new Scanner(System.in);

        // Récupération des informations et affectation aux variables.
        System.out.println("Saisir votre adresse courriel: ");
        String courriel = scanner.nextLine();
        System.out.println("Saisir votre mot de passe: ");
        String mdp = scanner.nextLine();

        // Construction du body (corps de la requête HTTP)
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("courriel", courriel);
        body.add("mdp", mdp);
        body.add("role", "locataire");

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
    public String creerRepresentant() throws RestClientException {
        String url = "http://localhost:8080/utilisateur/creer";

        Scanner scanner = new Scanner(System.in);

        System.out.println("Saisir votre adresse courriel: ");
        String courriel = scanner.nextLine();

        System.out.println("Saisir votre mot de passe: ");
        String mdp = scanner.nextLine();

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();

        body.add("courriel", courriel);
        body.add("mdp", mdp);
        body.add("role", "representant");

        Identifiant identifiant = new Identifiant(courriel, mdp);

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
    public String creerAgent() throws RestClientException {
        String url = "http://localhost:8080/utilisateur/creer";

        Scanner scanner = new Scanner(System.in);

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
    public String seConnecter() throws RestClientException {
        String url = "http://localhost:8080/utilisateur/connexion";

        Scanner scanner = new Scanner(System.in);

        System.out.println("Saisir votre adresse de courriel: ");
        String courriel = scanner.nextLine();
        System.out.println("Saisir votre mot de passe: ");
        String mdp = scanner.nextLine();

        // Construction de l'identifiant sous la forme "courriel:motdepasse"
        String identifiant = courriel + ":" + mdp;

        // Encodage de l'identifiant en Base64 pour l'authentification Basic
        String identifiantEncode = Base64.getEncoder().encodeToString(identifiant.getBytes());

        // Création de l'entête HTTP et ajout de l'identifiant encodé
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + identifiantEncode);

        // Création de l'entité HTTP avec les en-têtes
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> reponse = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return reponse.getBody();

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
    public String creerOuMettreAJourProfil() throws RestClientException {
        String url = "http://localhost:8080/utilisateur/profil";

        Scanner scanner = new Scanner(System.in);

        // Informations de l'utilisateur

        System.out.print("Saisir l'ID de l'utilisateur: ");
        String id = scanner.nextLine();

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
        body.add("id", id);
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

}
