package p2;
import java.util.*;

import monprojet.enums.*;
import p0.Utilisateur;
import p0.Etudiant;
import p0.ATS;
import p0.Enseignant;
import p1.Disponibilites;
import p1.Evaluation;
import p1.HistoriqueUtilisateur;
import p1.Profil;
import p1.Administration;
import p1.Course;
import p1.Itineraire;
import p1.Planning;
import p1.Preferences;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, String> admins = new HashMap<>();
        HashMap<String, String> etu = new HashMap<>();
        HashMap<String, String> ens = new HashMap<>();
        HashMap<String, String> ats = new HashMap<>();
        
        admins.put("232331531413", "nihel123");
        admins.put("232331531414", "nazim123");
        admins.put("232331531415", "malak123");
        admins.put("232331531416", "mouna123");
        
        Profil profil1 = new Profil();
        Profil profil2 = new Profil();
        Profil profil3 = new Profil();
        Utilisateur e = new Etudiant("leclerc", "charles", "3849", profil1, 2023, "informatique", "acad");
        etu.put("3849", "azerty");
        Utilisateur en = new Enseignant("hamilton", "lewis", "2343", profil2, 2020, "informatique");
        ens.put("2343", "azerty");
        Utilisateur a = new ATS("Wolf", "toto", "1293", profil3, 2021, "chef departement");
        ats.put("1293", "azerty");
        boolean continuer = true;

        while (continuer) {
            System.out.println("=== Menu Principal ===");
            System.out.println("1. Connexion Utilisateur");
            System.out.println("2. Connexion Administrateur");
            System.out.println("3. Quitter");
            System.out.print("Choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine();
            switch (choix) {
            case 1: 
                System.out.println("1. Connexion");
                System.out.println("2. Inscription");
                System.out.print("Votre choix : ");
                int sousChoix = scanner.nextInt();
                System.out.println("Choisissez votre type :");
            	System.out.println("1. Ãtudiant");
            	System.out.println("2. Enseignant");
            	System.out.println("3. ATS");
            	int choixtype = scanner.nextInt();
                scanner.nextLine();
                if (sousChoix == 1) { // Connexion
                    System.out.print("Entrez votre matricule : ");
                    String matricule = scanner.nextLine();
                    if (etu.containsKey(matricule)) {
                        System.out.print("Entrez votre mot de passe : ");
                        String mdp = scanner.nextLine();
                        if (choixtype ==1) {
                        if (etu.get(matricule).equals(mdp)) {
                            System.out.println("Connexion rÃ©ussie !");
                            //menuUtilisateur();
                        } else {
                            System.out.println("Mot de passe incorrect.");
                        }}
                        else if (choixtype == 2) {
                            if (ens.get(matricule).equals(mdp)) {
                                System.out.println("Connexion rÃ©ussie !");
                                //menuutilisateur(Utilisateur );
                            } else {
                                System.out.println("Mot de passe incorrect.");
                            }}
                       else if (choixtype == 3) {
                                if (ats.get(matricule).equals(mdp)) {
                                    System.out.println("Connexion rÃ©ussie !");
                                } else {
                                    System.out.println("Mot de passe incorrect.");
                                }}
                    }
                     else { System.out.println("Matricule inconnu. Veuillez vous inscrire.");} 
                    }
                   else if (sousChoix == 2) { // Inscription
                    System.out.print("Entrez votre nom : ");
                    String nom = scanner.nextLine();
                    System.out.print("Entrez votre prÃ©nom : ");
                    String prenom = scanner.nextLine();
                    System.out.print("Entrez votre matricule : ");
                    String matricule = scanner.nextLine();

                    if (etu.containsKey(matricule) && ens.containsKey(matricule) && ats.containsKey(matricule))  {
                        System.out.println("Ce matricule est dÃ©jÃ  utilisÃ©.");
                    } else {
                        System.out.print("Entrez votre mot de passe : ");
                        String mdp = scanner.nextLine();
                        if (choixtype ==1) {
                        	System.out.print("Entrez votre annee d'admission : ");
                            int anneeadmission = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Entrez votre faculte : ");
                            String faculte = scanner.nextLine();
                            System.out.print("Entrez votre specialite : ");
                            String specialite = scanner.nextLine();
                            Profil profil = new Profil(); 
                            Utilisateur nouveletudiant = new Etudiant(nom, prenom, matricule, profil,anneeadmission,faculte,specialite);
                            etu.put(matricule, mdp);
                        }
                        else if (choixtype == 2) {
                        	System.out.print("Entrez votre annee de recrutement : ");
                            int anneerecrutement = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Entrez votre faculte : ");
                            String faculte = scanner.nextLine();
                            Profil profil = new Profil(); 
                            Utilisateur nouvelEnseignant = new Enseignant(nom, prenom, matricule, profil,anneerecrutement,faculte);
                            ens.put(matricule, mdp);
                        }
                        else if (choixtype == 3) {
                        	System.out.print("Entrez votre annee de recrutement : ");
                            int anneerecrutement = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Entrez votre servicerattachement : ");
                            String servicerattachement = scanner.nextLine();
                            Profil profil = new Profil(); 
                            Utilisateur nouvelEnseignant = new Enseignant(nom, prenom, matricule, profil,anneerecrutement,servicerattachement);
                            ats.put(matricule, mdp);
                        }
                    System.out.println("Inscription rÃ©ussie !");}
                } 
                  else {System.out.println("Choix invalide."); }
                break;
                case 2:
                    System.out.print("ID admin : ");
                    String idAdmin = scanner.nextLine();
                    
                    System.out.print("Mot de passe : ");
                    String mdpAdmin = scanner.nextLine();
                    if (admins.containsKey(idAdmin) && admins.get(idAdmin).equals(mdpAdmin)) {
                        System.out.println("Connexion administrateur rÃ©ussie.");
                        menuAdministrateur();
                    } else {
                        System.out.println("Identifiants administrateur incorrects.");
                    }
                    break;

                case 3:
                    continuer = false;
                    System.out.println("Fermeture de l'application.");
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }
            scanner.close();
    }
    public static void menuUtilisateur(Utilisateur utilisateur, Administration admin, Scanner scanner) {
        boolean continuer = true;

        while (continuer) {
            System.out.println("\n=== Menu Utilisateur ===");
            System.out.println("1. Consulter mon profil");
            System.out.println("2. Modifier mon profil"); // statique ou dynamique
            System.out.println("3. Evaluer un utilisateur");
            System.out.println("4. Ajouter un commentaire");
            System.out.println("5. Rechercher une course");
            System.out.println("6. Se dÃ©connecter");
            System.out.print("Choix : ");
            
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    utilisateur.afficherProfil();;
                    break;

                case 2:
                    System.out.println("1. Modifier nom/prÃ©nom");
                    System.out.println("2. Modifier informations de profil");
                    int modif = scanner.nextInt();
                    scanner.nextLine();
                    if (modif == 1) {
                        System.out.print("Nouveau nom : ");
                        utilisateur.setNom(scanner.nextLine());
                        System.out.print("Nouveau prÃ©nom : ");
                        utilisateur.setPrenom(scanner.nextLine());
                    } else if (modif == 2) {
                        utilisateur.modifierProfilPersonnel(scanner, null, null)
                    }
                    break;

                case 3:
                    System.out.print("Matricule de l'utilisateur Ã  Ã©valuer : ");
                    String matricule = scanner.nextLine();
                    Utilisateur cible = admin.rechercherUtilisateurParMatricule(matricule);
                    if (cible != null) {
                        System.out.print("Note (0 Ã  5) : ");
                        double note = scanner.nextDouble();
                        scanner.nextLine();
                        Evaluation eval = new Evaluation(note, utilisateur.getNom() + " " + utilisateur.getPrenom());
                        cible.getHistorique().ajouterEvaluation(eval);
                        System.out.println("Ãvaluation ajoutÃ©e !");
                    } else {
                        System.out.println("Utilisateur non trouvÃ©.");
                    }
                    break;

                case 4:
                    System.out.print("Matricule de l'utilisateur ciblÃ© : ");
                    String matriculeComment = scanner.nextLine();
                    Utilisateur cibleComment = admin.rechercherUtilisateurParMatricule(matriculeComment);
                    if (cibleComment != null) {
                        System.out.print("Commentaire : ");
                        String commentaire = scanner.nextLine();
                        Evaluation com = new Evaluation(commentaire, utilisateur.getNom() + " " + utilisateur.getPrenom());
                        cibleComment.getHistorique().ajouterEvaluation(com);
                        System.out.println("Commentaire ajoutÃ© !");
                    } else {
                        System.out.println("Utilisateur non trouvÃ©.");
                    }
                    break;

                case 5:
                    System.out.println("Recherche de course : Ã  implÃ©menter selon critÃ¨res (itinÃ©raire, jour...)");
                    // Tu peux ajouter une recherche par jour, par lieu, etc.
                    break;

                case 6:
                    System.out.println("DÃ©connexion rÃ©ussie.");
                    continuer = false;
                    break;

                default:
                    System.out.println("Choix invalide.");
            }}}


    public static void menuAdministrateur(Scanner scanner, Administration admin) {
        boolean continuer = true;

        while (continuer) {
            System.out.println("\n=== Menu Administrateur ===");
            System.out.println("1. Lister tous les utilisateurs");
            System.out.println("2. Filtrer utilisateurs par type");
            System.out.println("3. Supprimer un utilisateur");
            System.out.println("4. Voir Ã©valuations dâun utilisateur");
            System.out.println("5. RÃ©initialiser rÃ©putation");
            System.out.println("6. Afficher les statistiques");
            System.out.println("7. Voir toutes les courses");
            System.out.println("8. Se dÃ©connecter");
            System.out.print("Choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    admin.afficherUtilisateurs();
                    break;

                case 2:
                    System.out.println("1. Ãtudiant  2. Enseignant  3. ATS");
                    int type = scanner.nextInt();
                    scanner.nextLine();
                    switch (type) {
                        case 1: admin.filtrerUtilisateursParStatut(Etudiant.class); break;
                        case 2: admin.filtrerUtilisateursParStatut(Enseignant.class); break;
                        case 3: admin.filtrerUtilisateursParStatut(ATS.class); break;
                        default: System.out.println("Type invalide."); break;
                    }
                    break;

                case 3:
                    System.out.print("Matricule de l'utilisateur Ã  supprimer : ");
                    String matriculeSup = scanner.nextLine();
                    Utilisateur u = admin.rechercherUtilisateurParMatricule(matriculeSup);
                    if (u != null) {
                        admin.supprimerUtilisateur(u);
                        System.out.println("Utilisateur supprimÃ©.");
                    } else {
                        System.out.println("Utilisateur introuvable.");
                    }
                    break;

                case 4:
                    System.out.print("Matricule de l'utilisateur : ");
                    String matEval = scanner.nextLine();
                    Utilisateur uEval = admin.rechercherUtilisateurParMatricule(matEval);
                    if (uEval != null) {
                        admin.afficherEvaluations(uEval);
                    } else {
                        System.out.println("Utilisateur introuvable.");
                    }
                    break;

                case 5:
                    System.out.print("Matricule de l'utilisateur : ");
                    String matRep = scanner.nextLine();
                    Utilisateur uRep = admin.rechercherUtilisateurParMatricule(matRep);
                    if (uRep != null) {
                        admin.reinitialiserReputation(uRep);
                        System.out.println("RÃ©putation rÃ©initialisÃ©e.");
                    } else {
                        System.out.println("Utilisateur introuvable.");
                    }
                    break;

                case 6:
                    admin.afficherStatistiquesGlobales();
                    break;

                case 7:
                    admin.afficherCoursesParStatut(Statut.EN_COURS);
                    admin.afficherCoursesParStatut(Statut.TERMINEE);
                    break;

                case 8:
                    continuer = false;
                    System.out.println("DÃ©connexion...");
                    break;

                default:
                    System.out.println("Choix invalide.");
            }
        }
    }

}
