package com.agence.Gr3.frontend.Services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import org.springframework.stereotype.Service;

@Service
public class ValidationFormulaire {

    /**
     * Valide que la saisie à la console n'est pas nulle. Demande à l'utilisateur
     * une nouvelle saisie tant que le critère n'est pas respecté.
     * 
     * @param message Le message d'instruction à l'utilisateur.
     * @param scanner L'objet scanner utilisé pour lire la saisie de l'utilisateur
     * @return La saisie de l'utilisateur respectant le critère.
     */
    public String validationNonNul(String message, Scanner scanner) {
        System.out.print(message);
        String input = scanner.nextLine();
        while (input.trim().isEmpty()) {
            System.out.print("Ce champ ne peut pas être vide. \n" + message);
            input = scanner.nextLine();
        }
        return input;
    }

    /**
     * Valide que la saisie à la console est un nombre positif. Demande à
     * l'utilisateur une nouvelle saisie tant que le critère n'est pas respecté.
     * 
     * @param message Le message d'instruction à l'utilisateur.
     * @param scanner L'objet scanner utilisé pour lire la saisie de l'utilisateur
     * @return La saisie de l'utilisateur respectant le critère.
     */
    public int validationNombrePositif(String message, Scanner scanner) {
        int result = -1;
        while (true) {
            try {
                System.out.print(message);
                result = Integer.parseInt(scanner.nextLine());
                if (result > 0)
                    break;
                else {
                    System.out.println("La saisie doit être un nombre positif. Veuillez réessayer.\n" + message + "\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("La saisie n'est pas un nombre valide.  Veuillez réessayer.\n" + message + "\n");
            }
        }
        return result;
    }

    /**
     * Valide que la saisie à la console est un nombre positif entre le minimum et
     * le maximum spécifié par l'utilisateur . Demande à l'utilisateur
     * une nouvelle saisie tant que le critère n'est pas respecté.
     * 
     * @param message Le message d'instruction à l'utilisateur.
     * @param scanner L'objet scanner utilisé pour lire la saisie de l'utilisateur
     * @param minimum Le minimum spécifié par l'utilisateur
     * @param maximum Le maximum spécifié par l'utilisateur
     * @return La saisie de l'utilisateur respectant le critère.
     */
    public int validationNombrePositifRange(String message, Scanner scanner, int minimum, int maximum) {
        int result = -1;
        while (true) {
            try {
                System.out.print(message);
                result = Integer.parseInt(scanner.nextLine());
                if (result >= minimum && result <= maximum)
                    break;
                else {
                    System.out.println("La saisie doit être un nombre positif entre " + minimum + " et " + maximum
                            + ". Veuillez réessayer.\n" + message + "\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("La saisie n'est pas un nombre valide.  Veuillez réessayer.\n" + message + "\n");
            }
        }
        return result;
    }

    /**
     * Valide que la saisie à la console est un nombre positif ou une valeur vide .
     * Demande à l'utilisateur une nouvelle saisie tant que le critère n'est pas
     * respecté.
     * 
     * @param message Le message d'instruction à l'utilisateur.
     * @param scanner L'objet scanner utilisé pour lire la saisie de l'utilisateur
     * @return L'entier saisi, ou -1 si l'utilisateur n'a rien saisi
     */
    public int validationNombreOptionnel(String message, Scanner scanner) {
        int result = -1;
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                return -1;
            } else {
                try {
                    result = Integer.parseInt(input);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("La saisie n'est pas un nombre valide. Veuillez réessayez.\n" + message + "\n");
                }
            }
        }
        return result;
    }

    /**
     * Valide que la saisie à la console est une valeur booléenne, vrai ou faux. La
     * méthode fait la conversion de o à true et de n à false. Demande à
     * l'utilisateur une nouvelle saisie tant que le critère n'est pas
     * respecté.
     * 
     * @param message Le message d'instruction à l'utilisateur.
     * @param scanner L'objet scanner utilisé pour lire la saisie de l'utilisateur
     * @return La saisie de l'utilisateur respectant le critère.
     */
    public boolean validationBooleen(String message, Scanner scanner) {

        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("o")) {
                return true;

            } else if (input.equals("n")) {
                return false;

            } else {
                System.out.println(
                        "La saisie doit soit o pour oui ou n pour non. Veuillez réessayer.\n" + message + "\n");
            }

        }

    }

    /**
     * Valide si l'entrée de l'utilisateur correspond à un format LocalDateTime
     * valide. Demande à l'utilisateur une nouvelle saisie tant que le critère n'est
     * pas respecté.
     *
     * @param message Le message d'instruction à l'utilisateur.
     * @param scanner L'objet scanner utilisé pour lire la saisie de l'utilisateur
     * @return La saisie de l'utilisateur respectant le critère en chaîne de
     *         caractères.
     */
    public String validationLocalDateTime(String message, Scanner scanner) {
        // Format attendu pour la date et l'heure
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        while (true) {
            System.out.print(message + " (Format attendu: jj/MM/aaaa HH:mm): ");
            String input = scanner.nextLine().trim();

            try {
                // Valide que la conversion peut se faire.
                LocalDateTime dateHeure = LocalDateTime.parse(input, format);
                return input;
            } catch (DateTimeParseException e) {
                System.out.println(
                        "Le format de la date et de l'heure est incorrect.");
            }
        }
    }

}
