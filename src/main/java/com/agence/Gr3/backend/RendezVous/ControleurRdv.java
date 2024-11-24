package com.agence.Gr3.backend.RendezVous;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agence.Gr3.backend.Logements.Model.Logement;
import com.agence.Gr3.backend.RendezVous.Model.Rdv;
import com.agence.Gr3.backend.RendezVous.Services.ServiceRdv;
import com.agence.Gr3.backend.Utilisateurs.Services.ServiceJwt;

import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/rdv")
public class ControleurRdv {

    private ServiceJwt serviceJwt;
    private ServiceRdv serviceRdv;

    public ControleurRdv(ServiceJwt serviceJwt, ServiceRdv serviceRdv) {
        this.serviceJwt = serviceJwt;
        this.serviceRdv = serviceRdv;

    }

    /**
     * Traite la requête HTTP de type POST sur le endpoint /rdv/creer.
     * Valide le jwt et le corps (body) de la requête. Une réponse HTTP défavorable
     * est renvoyée en cas d'erreur. S'il n'y a pas d'erreurs, un
     * appel est fait à la méthode "creer" du ServiceRdv en passant comme
     * paramètres le corps de la requête HTTP et le courriel extrait du jwt.
     * Si cet appel n'est pas un succès, une réponse HTTP défavorable
     * sera renvoyée. En cas de succès, une réponse HTTP.Ok sera renvoyée.
     * 
     * 
     * 
     * @param authorizationHeader l'en-tête d'autorisation contenant l'identifiant
     *                            de l'utilisateur (courriel)
     * @param requestBody         les données relatives au rdv à insérer dans
     *                            la BD
     * @return une réponse HTTP indiquant si l'insertion du rdv est un succès ou un
     *         échec.
     */
    @PostMapping("creer")
    public ResponseEntity<String> creerRdv(@RequestHeader("Authorization") String authorizationHeader,
            @RequestBody Map<String, Object> requestBody) {

        System.out.println("methode: controleurRdv creer"); // DEBUG

        try {
            // Extraction et validation du Jwt reçu
            String jwt = serviceJwt.extraireJwt(authorizationHeader);
            Claims claims = serviceJwt.validerJwt(jwt);
            String courriel = claims.getSubject();
            System.out.println("methode: controleurRdv creer: JWT OK"); // DEBUG

            // TODO Validation de la permission

            // Validation du corps de la requête
            if (requestBody == null || requestBody.isEmpty()) {
                return new ResponseEntity<String>("Le corps de la requête est vide", HttpStatus.BAD_REQUEST);
            }
            System.out.println("methode: controleurRdv creer: BODY OK"); // DEBUG

            // Si tout est conforme, la méthode du service est appelée

            Rdv rdv = serviceRdv.creer(courriel, requestBody);
            System.out.println("methode: controleurRdv creer: appel au service"); // DEBUG

            if (rdv == null) {
                System.out.println("methode: controleurRdv creer: Rdv NULL"); // DEBUG
                return new ResponseEntity<String>("Rdv non-enregistré", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<String>("Rdv enregitstré", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<String>("Accès interdit", HttpStatus.FORBIDDEN);

        }

    }

    /**
     * Traite la requête HTTP de type PUT sur le endpoint /rdv/{id}/modifier.
     * Valide le jwt et le corps (body) de la requête. Une réponse HTTP défavorable
     * est renvoyée en cas d'erreur. S'il n'y a pas d'erreurs, un
     * appel est fait à la méthode "modifier" du ServiceRdv en passant comme
     * paramètres l'identifiant du rdv à modifier, le corps de la requête HTTP
     * et le courriel extrait du jwt.Si cet appel n'est pas un succès, une réponse
     * HTTP défavorable sera renvoyée. En cas de succès, une réponse HTTP.Ok sera
     * renvoyée.
     * 
     * @param id                  identifiant du rdv à modifier
     * @param authorizationHeader l'en-tête d'autorisation contenant l'identifiant
     *                            de l'utilisateur (courriel)
     * @param requestBody         les données relatives au rdv à insérer dans
     *                            la BD
     * @return une réponse HTTP indiquant si la modification du rdv est un
     *         succès ou un échec.
     */
    @PutMapping("/modifier/{id}")
    public ResponseEntity<String> modifierRdv(@PathVariable int id,
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody Map<String, Object> requestBody) {

        System.out.println("methode: controleurRdv modifier"); // DEBUG

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

            // Valider: le rendez-vous existe et l'utilisateur est un des participants
            Rdv rdv = serviceRdv.rechercher(id);

            if (rdv == null) {
                return new ResponseEntity<String>("Le rdv n'existe pas", HttpStatus.BAD_REQUEST);

            } else if (!rdv.getIdGerant().equals(courriel) || !rdv.getIdLocataire().equals(courriel)) {
                return new ResponseEntity<String>("Opération interdite", HttpStatus.FORBIDDEN);

            }

            // Si tout est conforme, la méthode du service est appelée
            rdv = serviceRdv.modifier(id, courriel, requestBody);

            if (rdv == null) {
                return new ResponseEntity<String>("Rdv non-enregistré", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<String>("Rdv enregitstré", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<String>("Accès interdit", HttpStatus.FORBIDDEN);

        }

    }

    /**
     * S'occupe de confirmer le rendez-vous sélectionné.
     * Traite la requête HTTP de type PUT sur le endpoint /rdv/{id}/confirmer.
     * Valide le jwt et s'assure de l'existance du rdv et confirme la participation
     * de l'utilisateur à celui-ci. Une réponse HTTP défavorable
     * est renvoyée en cas d'erreur. S'il n'y a pas d'erreurs, un
     * appel est fait à la méthode "confirmer" du ServiceRdv en passant comme
     * paramètres l'identifiant du rdv à confirmer, le corps de la requête HTTP
     * et le courriel extrait du jwt.Si cet appel n'est pas un succès, une réponse
     * HTTP défavorable sera renvoyée. En cas de succès, une réponse HTTP.Ok sera
     * renvoyée.
     * 
     * @param id                  identifiant du rdv à confirmer
     * @param authorizationHeader l'en-tête d'autorisation contenant l'identifiant
     *                            de l'utilisateur (courriel)
     * @param requestBody         les données relatives au rdv à insérer dans
     *                            la BD
     * @return une réponse HTTP indiquant si la confirmation du rdv est un
     *         succès ou un échec.
     */
    @PutMapping("/confirmer/{id}")
    public ResponseEntity<String> confirmerRdv(@PathVariable int id,
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody Map<String, Object> requestBody) {

        System.out.println("methode: controleurRdv confirmer"); // DEBUG

        try {
            // Extraction et validation du Jwt reçu
            String jwt = serviceJwt.extraireJwt(authorizationHeader);
            Claims claims = serviceJwt.validerJwt(jwt);
            String courriel = claims.getSubject();

            // TODO Validation de la permission

            if (id == 0) {
                return new ResponseEntity<String>("Le paramètre de recherche est vide", HttpStatus.BAD_REQUEST);

            }

            // Valider: le rendez-vous existe et l'utilisateur est un des participants
            Rdv rdv = serviceRdv.rechercher(id);

            if (rdv == null) {
                return new ResponseEntity<String>("Le rdv n'existe pas", HttpStatus.BAD_REQUEST);

            } else if (!rdv.getIdGerant().equals(courriel) || !rdv.getIdLocataire().equals(courriel)) {
                return new ResponseEntity<String>("Opération interdite", HttpStatus.FORBIDDEN);

            }

            // Si tout est conforme, la méthode du service est appelée
            rdv = serviceRdv.confirmer(id, courriel);

            if (rdv == null) {
                return new ResponseEntity<String>("Confirmation non-complétée", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<String>("Confirmation non-complétée", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<String>("Accès interdit", HttpStatus.FORBIDDEN);

        }

    }

    /**
     * S'occupe d'annuler le rendez-vous sélectionné.
     * Traite la requête HTTP de type PUT sur le endpoint /rdv/{id}/annuler.
     * Valide le jwt et s'assure de l'existance du rdv et confirme la participation
     * de l'utilisateur à celui-ci. Une réponse HTTP défavorable
     * est renvoyée en cas d'erreur. S'il n'y a pas d'erreurs, un
     * appel est fait à la méthode "annuler" du ServiceRdv en passant comme
     * paramètres l'identifiant du rdv à annuler et le courriel extrait du jwt.
     * Si cet appel n'est pas un succès, une réponse HTTP défavorable sera renvoyée.
     * En cas de succès, une réponse HTTP.Ok sera
     * renvoyée.
     * 
     * @param id                  identifiant du rdv à annuler
     * @param authorizationHeader l'en-tête d'autorisation contenant l'identifiant
     *                            de l'utilisateur (courriel)
     * @param requestBody         les données relatives au rdv à insérer dans
     *                            la BD
     * @return une réponse HTTP indiquant si l'annulation du rdv est un
     *         succès ou un échec.
     */
    @PutMapping("/annuler/{id}")
    public ResponseEntity<String> annulerRdv(@PathVariable int id,
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody Map<String, Object> requestBody) {

        System.out.println("methode: controleurRdv annuler"); // DEBUG

        try {
            // Extraction et validation du Jwt reçu
            String jwt = serviceJwt.extraireJwt(authorizationHeader);
            Claims claims = serviceJwt.validerJwt(jwt);
            String courriel = claims.getSubject();

            // TODO Validation de la permission

            if (id == 0) {
                return new ResponseEntity<String>("Le paramètre de recherche est vide", HttpStatus.BAD_REQUEST);

            }

            // Le rendez-vous existe et l'utilisateur est un des participants
            Rdv rdv = serviceRdv.rechercher(id);

            if (rdv == null) {
                return new ResponseEntity<String>("Le rdv n'existe pas", HttpStatus.BAD_REQUEST);

            } else if (!rdv.getIdGerant().equals(courriel) || !rdv.getIdLocataire().equals(courriel)) {
                return new ResponseEntity<String>("Opération interdite", HttpStatus.FORBIDDEN);

            }

            // Si tout est conforme, la méthode du service est appelée
            rdv = serviceRdv.annuler(id, courriel);

            if (rdv == null) {
                return new ResponseEntity<String>("Annulation non-complétée", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<String>("Annulation non-complétée", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<String>("Accès interdit", HttpStatus.FORBIDDEN);

        }

    }

    /**
     * S'occupe de la récupération, à partir de la BD ,des rdv associés à un
     * utilisateur. Traite la requête HTTP de type GET sur le endpoint
     * /rdv/afficher/utilisateur. Valide le jwt et le corps (body) de la
     * requête. Une réponse HTTP défavorable est renvoyée en cas d'erreur. S'il n'y
     * a pas d'erreurs, un appel est fait à la méthode "rechercher" du
     * ServiceRdv en passant comme paramètre le courriel extrait du jwt. Si cet
     * appel n'est pas un succès, une réponse HTTP défavorable sera renvoyée. En cas
     * de succès, une réponse HTTP.Ok sera renvoyée.
     * 
     * @param authorizationHeader l'en-tête d'autorisation contenant l'identifiant
     *                            de l'utilisateur (courriel)
     * @return une réponse HTTP contenant la liste des rendez-vous.
     */

    @GetMapping("/afficher/utilisateur")
    public ResponseEntity<Object> rechercherRdv(
            @RequestHeader("Authorization") String authorizationHeader) {

        try {
            // Extraction et validation du Jwt reçu
            String jwt = serviceJwt.extraireJwt(authorizationHeader);
            Claims claims = serviceJwt.validerJwt(jwt);
            String courriel = claims.getSubject();

            System.out.println("Le courriel recu: " + courriel);

            // TODO Validation de la permission

            // Si tout est conforme, la méthode du service est appelée
            List<Rdv> rdvs = serviceRdv.rechercher(courriel);

            if (rdvs == null) {
                return new ResponseEntity<Object>(rdvs, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<Object>(rdvs, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<Object>("Accès interdit", HttpStatus.FORBIDDEN);

        }
    }

}
