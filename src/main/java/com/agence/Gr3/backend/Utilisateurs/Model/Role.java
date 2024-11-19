package com.agence.Gr3.backend.Utilisateurs.Model;

import java.util.List;

public enum Role {

    INVITE(List.of(Permission.CREER_UTILISATEUR, Permission.CONNEXION, Permission.QUITTER)),
    LOCATAIRE(List.of(Permission.DECONNEXION, Permission.REINITIALISER_MDP, Permission.MODIFIER_PROFIL,
            Permission.QUITTER)),
    AGENT(List.of(Permission.DECONNEXION, Permission.REINITIALISER_MDP, Permission.MODIFIER_PROFIL,
            Permission.QUITTER)),
    REPRESENTANT(List.of(Permission.DECONNEXION, Permission.REINITIALISER_MDP, Permission.MODIFIER_PROFIL,
            Permission.QUITTER)),
    ADMIN(List.of(Permission.DECONNEXION, Permission.REINITIALISER_MDP, Permission.MODIFIER_PROFIL,
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
