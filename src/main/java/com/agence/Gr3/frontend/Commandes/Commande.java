package com.agence.Gr3.frontend.Commandes;

import com.agence.Gr3.backend.Utilisateurs.Model.Permission;
import java.util.Scanner;
import java.util.List;

public interface Commande<T, R> {
    R execute(List<Permission> permissions, Scanner scanner, T parametre);

}
