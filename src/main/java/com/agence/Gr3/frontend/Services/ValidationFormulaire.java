package com.agence.Gr3.frontend.Services;

import java.util.Scanner;

import org.springframework.stereotype.Service;

@Service
public class ValidationFormulaire {

    public String validationNonNul(String message, Scanner scanner) {
        System.out.print(message);
        String input = scanner.nextLine();
        while (input.trim().isEmpty()) {
            System.out.print("Ce champ ne peut pas être vide. \n" + message);
            input = scanner.nextLine();
        }
        return input;
    }

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

}
