package com.agence.Gr3.frontend.Commandes;

public interface Commande<T, R> {
    R execute(T parametre);

}
