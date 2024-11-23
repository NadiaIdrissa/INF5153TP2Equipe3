package com.agence.Gr3.backend.Logements.Model;

import com.agence.Gr3.Model.Adresse;
import org.springframework.stereotype.Component;

@Component
public class FabriqueLogementDeBase {

    public LogementDeBase creerLogement(int id, String idRepresentant, Adresse adresse) {

        System.out.println("methode: fabriqueLogement creerLogement"); // DEBUG

        // Validate required fields
        if (id <= 0 || idRepresentant == null || idRepresentant.isEmpty()) {
            throw new IllegalArgumentException("Arguments invalides pour créer un Logement");
        }

        return new LogementDeBase(id, idRepresentant, adresse);

    }
}
