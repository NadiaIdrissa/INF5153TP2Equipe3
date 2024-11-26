
Comment démarrer l’application:
Se déplacer dans le répertoire inf5153tp2equipe3:
Lancer la commande suivante: ./gradlew bootRun --console=plain

Frontend:

Frontend.java

Ne contient qu’une méthode qui lance le menu. Cette méthode est appelée une fois que le contexte de l’application Springboot a été initialisé.

La méthode consiste en une boucle qui ne fait qu’afficher un menu (tant que le processus est actif).  Les choix de ce menu correspondent aux permissions associées au role de l’utilisateur actif et remplacent d’une certaine façon les boutons et autre liens présent sur une page web conventionnelle. Ce menu change dynamiquement en fonction du type d’utilisateur connecté.

L’objectif de cette méthode est de simuler le frontend:
    • L’utilisateur interagis avec l’application en saisissant au clavier les fonctionnalités désirées. 
    • Les ressources sélectionnées sont quant à elles affichées à la console.

Variables globales:
List<Permission> permissions: liste de permissions associées à l’utilisateur actif
StringBuilder JWT: Json web token associé à l’utilisateur actif.
Ces variables sont modifiées au moment de la connexion ou de la déconnexion de l’utilisateur.

Utilisation d’un patron de commande:
Un patron de commande a été mis en place pour permettre à l'utilisateur de sélectionner les opérations via la console. Chaque permission est associée à une classe qui implémente l'interface Commande. Lorsqu'une permission est sélectionnée, la méthode correspondant à la fonctionnalité associée est exécutée. Chacune de ces commandes est associée à une méthode des classes “Formulaires”.

Note importante:
Afin de simplifier le code d’éxécution de ces commandes, celles-ci ont toutes la même signature. Les paramètres passés dans celles-ci ne sont pas nécessairement toujours utilisés. 

Formulaires:

FormulairesLogement.java
FormulairesRdv.java
Formulaires.Utilisateur.java

Les méthodes contenues dans ces classes servent à simuler l’envoi de requête et la réception de réponses à partir du frontend. Chaque méthode a les objectifs suivants:

1) Permettre saisie de donnée à la console (analogue à la complétion d’un formulaire)
2) Envoyer les données saisie via une requête HTTP au endpoint approprié
3) Gérer la réponse HTTP obtenue du serveur (changement de contexte, affichage etc.)

Repository:

DaoUtilisateur.java
DaoRdv.java
DaoHistoriqueNotification.java
DaoLogement.java

Les tables de la base de données sont représentées uniquement sous forme de structures de données en mémoire. Pour simplifier la base de code, ces structures ont été directement intégrées dans les classes de DAO (Data Access Object). Cette approche a été jugée acceptable dans le cadre académique de l'exercice, mais il convient de préciser qu'il ne s'agit pas d'une implémentation valide pour une application en production.

Simplifications principales:

Modèles:
Les modèles (de données) n’existent que dans le backend. 

Fonctionnalité : validation des permissions
L’implémentation prévoyait la vérification des permissions de l’utilisateur à chaque requête HTTP à partir du contrôleur. Les permissions associées à l’identifiant contenu dans le jwt aurait été comparé à/aux permissions requises pour effectuer l’opération. Faute de temps, cela n’aura pas été implémenté.

Classe: permissions et rôles
Les permissions et les rôles sont sont listées dans une énumération. Les rôles encapsulent les permissions. Il n’y a pas de mécanisme de gestion de permissions.

Fonctionnalité : validation des entrées
Il n’y a pas de validation d’entrées effectuée dans le backend pour s’assurer de la cohérence des données. La validation est uniquement effectuée dans le frontend dans le but de garantir le type attendu par les méthodes.

Classe: notifications:
Les notifications sont représentées par une chaîne de caractère uniquement. 

Classe: Annonce
L’annonce n’existe pas dans l’implémentation. Une annonce encapsule un logement, ainsi, le logement  est un hybride des deux dans la 

Utilisateurs:
Il n’y a pas de services utilisateurs. Toutes les méthodes sont contenues dans le controleur, incluant la connexion et déconexion. Cela avait été initialement fait pour économiser du temps, mais nous avons jugé préférable de faire de façon plus structuée pour les autres fonctionnalités.




Limites:


Les sujets et les observateurs ont été configurés mais comme l’implémentation est en spribgboot et que c’est le cadriciel qui gère l’exécution de l’application, incluant le cycle de vie des composant comme les services, les repository et les contrôleurs.  Je ne peux pas manuellement instancier des l’observateur qui est un service (service de notification).

La seule approche que j’ai trouvé serait simplement d’injecter le service de notification dans le service de rendez-vous et de faire des appels à la méthode de création des notification à chaque modification de rendez-vous.



