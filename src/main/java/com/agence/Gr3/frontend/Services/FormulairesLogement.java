package com.agence.Gr3.frontend.Services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.agence.Gr3.backend.Utilisateurs.Model.Permission;

@Service
public class FormulairesLogement {

    // Injection d'un rest template pour l'envoi de requêtes HTTP
    private final RestTemplate restTemplate;
    private final ValidationFormulaire validationFormulaire;

    public FormulairesLogement(RestTemplate restTemplate, ValidationFormulaire validationFormulaire) {
        this.restTemplate = restTemplate;
        this.validationFormulaire = validationFormulaire;

    }

    public String creerLogement(List<Permission> permissions, Scanner scanner, StringBuilder jwt)
            throws RestClientException {

        String url = "http://localhost:8080/logement/creer";

        // Construction du body (corps de la requête HTTP)
        HashMap<String, Object> body = new HashMap<>();
        body.put("decorateurs", saisirDecorateurs(scanner));
        body.put("adresse", saisirAdresse(scanner));

        // Construction de l'en-tête de la requête (header)
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
                return "ajout logement réussie";

            } else {
                return "ajout logement non réussie";

            }

        } catch (RestClientException e) {
            return "ajout logement non réussie";

        }

    }

    public String modifierLogement(List<Permission> permissions, Scanner scanner, StringBuilder jwt)
            throws RestClientException {

        String url = "http://localhost:8080/logement/modifier";

        int id = validationFormulaire.validationNombrePositif(
                "Veuillez saisir l'identifiant du logement à modifier: \n",
                scanner);

        // Ajout du paramètre à l'URL
        String urlFinal = UriComponentsBuilder.fromHttpUrl(url).path("/{id}").buildAndExpand(id).toUriString();

        // Construction de l'en-tête de la requête (header)
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwt);

        // Construction du body (corps de la requête HTTP)
        HashMap<String, Object> body = new HashMap<>();
        body.put("decorateurs", saisirDecorateurs(scanner));
        body.put("adresse", saisirAdresse(scanner));

        // Construction de la requête
        HttpEntity<HashMap<String, Object>> requete = new HttpEntity<>(body, headers);

        System.out.println("requete est terminée");

        try {

            ResponseEntity<String> reponse = restTemplate.exchange(
                    urlFinal,
                    HttpMethod.PUT,
                    requete,
                    String.class);

            System.out.println("requete envoyee");

            if (reponse.getStatusCode() == HttpStatus.OK) {
                return "ajout logement réussie";

            } else {
                return "ajout logement non réussie";

            }

        } catch (RestClientException e) {
            return "ajout logement non réussie";

        }

    }

    public String afficherLogement(List<Permission> permissions, Scanner scanner, StringBuilder jwt)
            throws RestClientException {

        System.out.println("Afficher Logements");

        String url = "http://localhost:8080/logement/afficher/utilisateur";

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
                    return "Il n'y a pas de logements à afficher";
                }

                return reponse.getBody();

            } else {
                return "Affichage logement non réussi";

            }

        } catch (RestClientException e) {
            return "Affichage logement non réussi";

        }

    }

    public String afficherTousLogements(List<Permission> permissions, Scanner scanner, StringBuilder jwt)
            throws RestClientException {

        System.out.println("Afficher tous les Logements");

        String url = "http://localhost:8080/logement/afficher";

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
                    return "Il n'y a pas de logements à afficher";
                }

                return reponse.getBody();

            } else {
                return "Affichage logement non réussi";

            }

        } catch (RestClientException e) {
            return "Affichage logement non réussi";

        }

    }

    /**
     * Cette méthode permet de rassembler dans une map les données relatives à un
     * logement saisies au clavier par l'utilisateur.
     * 
     * @param scanner le Scanner utilisé pour saisir les données à partir de
     *                l'entrée standard
     * @return une HashMap contenant les données saisies par l'utilisateur.
     */
    private HashMap<String, Object> saisirLogement(Scanner scanner) {

        HashMap<String, Object> logement = new HashMap<>();

        // Saisie des informations et affectation aux variables.
        double nbrChambres = validationFormulaire.validationNombrePositif("Veuillez saisir le nombre de chambres: \n",
                scanner);
        double nbrSallesDeBain = validationFormulaire
                .validationNombrePositif("Veuillez saisir le nombre de salles de bain: \n", scanner);
        double superficie = validationFormulaire.validationNombrePositif("Veuillez saisir la superficie: \n", scanner);
        String description = validationFormulaire.validationNonNul("Veuillez saisir la description: \n", scanner);
        int dureeBail = validationFormulaire.validationNombrePositif("Veuillez saisir la durée du bail (en mois): \n",
                scanner);
        int noCivique = validationFormulaire.validationNombrePositif("Veuillez saisir le no civique: \n", scanner);
        int suite = validationFormulaire.validationNombreOptionnel("Veuillez saisir la suite (optionnel): \n", scanner);
        String rue = validationFormulaire.validationNonNul("Veuillez saisir votre la rue: \n", scanner);
        String codePostal = validationFormulaire.validationNonNul("Veuillez saisir le code postal: \n", scanner);
        String ville = validationFormulaire.validationNonNul("Veuillez saisir la ville: \n", scanner);
        String province = validationFormulaire.validationNonNul("Veuillez saisir la province: \n", scanner);

        // Ajout à la Map
        logement.put("nbrChambres", nbrChambres);
        logement.put("nbrSallesDeBain", nbrSallesDeBain);
        logement.put("superficie", superficie);
        logement.put("description", description);
        logement.put("dureeBail", dureeBail);
        logement.put("noCivique", noCivique);
        logement.put("suite", suite);
        logement.put("rue", rue);
        logement.put("codePostal", codePostal);
        logement.put("ville", ville);
        logement.put("province", province);

        return logement;

    }

    private HashMap<String, Object> saisirDecorateurs(Scanner scanner) {

        HashMap<String, Object> decorateurs = new HashMap<>();
        int tailleLogementMin = 2;
        int tailleLogementMax = 5;

        decorateurs.put("chauffage", validationFormulaire.validationBooleen("Inclure le chauffage o/n?", scanner));
        decorateurs.put("climatisation",
                validationFormulaire.validationBooleen("Inclure la climatisation o/n?", scanner));
        decorateurs.put("electromenagers",
                validationFormulaire.validationBooleen("Inclure les electromenagers o/n?", scanner));
        decorateurs.put("wifi", validationFormulaire.validationBooleen("Inclure le wifi o/n?", scanner));
        decorateurs.put("semiMeuble", validationFormulaire.validationBooleen("Logement semi-meublé o/n?", scanner));

        if (!((boolean) decorateurs.get("semiMeuble"))) {
            decorateurs.put("meuble", validationFormulaire.validationBooleen("Logement meublé o/n?", scanner));
        }

        int taille = validationFormulaire.validationNombrePositifRange("Taille du logement (2,3,4 ou 5)", scanner,
                tailleLogementMin, tailleLogementMax);

        decorateurs.put("taille" + taille, true);

        return decorateurs;

    }

    private HashMap<String, Object> saisirAdresse(Scanner scanner) {

        HashMap<String, Object> adresse = new HashMap<>();

        adresse.put("noCivique",
                validationFormulaire.validationNombrePositif("Veuillez saisir le no civique: \n", scanner));
        adresse.put("suite",
                validationFormulaire.validationNombreOptionnel("Veuillez saisir la suite (optionnel): \n", scanner));
        adresse.put("rue", validationFormulaire.validationNonNul("Veuillez saisir votre la rue: \n", scanner));
        adresse.put("codePostal", validationFormulaire.validationNonNul("Veuillez saisir le code postal: \n", scanner));
        adresse.put("ville", validationFormulaire.validationNonNul("Veuillez saisir la ville: \n", scanner));
        adresse.put("province", validationFormulaire.validationNonNul("Veuillez saisir la province: \n", scanner));

        return adresse;

    }

}
