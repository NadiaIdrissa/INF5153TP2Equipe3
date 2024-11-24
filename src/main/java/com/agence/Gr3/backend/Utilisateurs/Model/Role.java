package com.agence.Gr3.backend.Utilisateurs.Model;

import java.util.List;

public enum Role {

    INVITE(List.of(Permission.CREER_UTILISATEUR, Permission.CONNEXION, Permission.QUITTER)),
    LOCATAIRE(List.of(Permission.DECONNEXION, Permission.MODIFIER_PROFIL,
            Permission.QUITTER)),
    REPRESENTANT(List.of(Permission.DECONNEXION, Permission.MODIFIER_PROFIL, Permission.CREER_LOGEMENT,
            Permission.MODIFIER_LOGEMENT, Permission.AFFICHER_LOGEMENT, Permission.AFFICHER_TOUS_LOGEMENTS,
            Permission.CREER_VISITE, Permission.MODIFIER_VISITE, Permission.ANNULER_VISITE, Permission.CONFIRMER_VISITE,
            Permission.QUITTER)),
    ADMIN(List.of(Permission.DECONNEXION, Permission.MODIFIER_PROFIL,
            Permission.QUITTER));

    private List<Permission> permissions;

    private Role(List<Permission> permissions) {

        this.permissions = permissions;
    }

    public void addPermission(Permission permissions) {
        this.permissions.add(permissions);
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

}
