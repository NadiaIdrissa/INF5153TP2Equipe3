package com.agence.Gr3.frontend.Commandes;

import java.util.Scanner;

public interface Commande<T, R> {
    R execute(Scanner scanner, T parametre);

}
