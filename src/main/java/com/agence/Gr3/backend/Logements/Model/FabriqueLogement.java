
package com.agence.Gr3.backend.Logements.Model;

import com.agence.Gr3.Model.Adresse;
import org.springframework.stereotype.Component;

@Component
public class FabriqueLogement {

    public Logement creerLogement(int id, String idRepresentant, double nbrChambres, double nbrSallesDeBain,
            double superficie, String description, int dureeBail, Adresse adresse,
            boolean proprieteConfirmee, boolean disponible) {

        System.out.println("methode: fabriqueLogement creerLogement"); // DEBUG

        if (id <= 0 || idRepresentant == null || idRepresentant.isEmpty()) {
            System.out.println("methode: fabriqueLogement creerLogement WRONG ID"); // DEBUG
            throw new IllegalArgumentException("Arguments invalides pour créer un Logement");
        }

        if (description == null || description.isEmpty()) {
            description = "Description à venir";
        }

        return new Logement(id, idRepresentant, nbrChambres, nbrSallesDeBain, superficie, description, dureeBail,
                adresse,
                proprieteConfirmee, disponible);
    }
}
