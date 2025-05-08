package p2;
import java.util.*;

import monprojet.enums.StatutUser;
import monprojet.enums.TypeCourse;
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

/**
 * Classe principale qui lance l'application de covoiturage universitaire
 */
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Administration admin = Administration.getInstance();
    private static Utilisateur utilisateurConnecte = null;
    private static boolean estAdmin = false;

    public static void main(String[] args) {
        System.out.println("=======================================");
        System.out.println("   COVOITURAGE UNIVERSITAIRE");
        System.out.println("=======================================");
        
        // Initialiser quelques données de test
        initialiserDonneesTest();
        
        boolean continuer = true;
        while (continuer) {
            if (utilisateurConnecte == null) {
                afficherMenuConnexion();
                int choix = lireEntier("Votre choix: ");
                
                switch (choix) {
                    case 1:
                        connexionUtilisateur();
                        break;
                    case 2:
                        connexionAdmin();
                        break;
                    case 3:
                        creerCompteUtilisateur();
                        break;
                    case 0:
                        System.out.println("Au revoir !");
                        continuer = false;
                        break;
                    default:
                        System.out.println("Option invalide. Veuillez réessayer.");
                }
            } else if (estAdmin) {
                gererMenuAdmin();
            } else {
                gererMenuUtilisateur();
            }
        }
        
        scanner.close();
    }
    
    /**
     * Initialiser des données de test pour démonstration
     */
    
    
    /**
     * Afficher le menu de connexion
     */
    private static void afficherMenuConnexion() {
        System.out.println("\n--- MENU PRINCIPAL ---");
        System.out.println("1. Se connecter en tant qu'utilisateur");
        System.out.println("2. Se connecter en tant qu'administrateur");
        System.out.println("3. Créer un compte utilisateur");
        System.out.println("0. Quitter");
    }
    
    
     //Afficher tous les utilisateurs enregistrés
     
    private static void afficherTousLesUtilisateurs() {
        System.out.println("\n--- LISTE DES UTILISATEURS ---");
        List<Utilisateur> utilisateurs = admin.getUtilisateurs();
        
        if (utilisateurs.isEmpty()) {
            System.out.println("Aucun utilisateur enregistré.");
        } else {
            for (Utilisateur u : utilisateurs) {
                System.out.println("- " + u.getNom() + " " + u.getPrenom() + " (Matricule: " + u.getMatricule() + ")");
                if (u instanceof Etudiant) {
                    Etudiant e = (Etudiant) u;
                    System.out.println("  Type: Étudiant");
                    System.out.println("  Faculté: " + e.getFaculte() + ", Spécialité: " + e.getSpecialite());
                } else if (u instanceof Enseignant) {
                    Enseignant e = (Enseignant) u;
                    System.out.println("  Type: Enseignant");
                    System.out.println("  Faculté: " + e.getFaculte());
                } else if (u instanceof ATS) {
                    ATS a = (ATS) u;
                    System.out.println("  Type: Personnel ATS");
                    System.out.println("  Service: " + a.getServiceRattachement());
                }
                System.out.println("  Réputation: " + String.format("%.2f", u.getReputation()) + "/5");
                System.out.println();
            }
        }
    }
    
    /**
     * Bannir un utilisateur du système
     */
    private static void bannirUtilisateur() {
        System.out.println("\n--- BANNIR UN UTILISATEUR ---");
        String matricule = lireChaine("Matricule de l'utilisateur à bannir: ");
        
        Utilisateur utilisateur = admin.rechercherUtilisateur(matricule);
        if (utilisateur == null) {
            System.out.println("Erreur: Utilisateur non trouvé.");
            return;
        }
        
        System.out.println("Vous êtes sur le point de bannir: " + utilisateur.getNom() + " " + utilisateur.getPrenom());
        String confirmation = lireChaine("Confirmer? (O/N): ");
        
        if (confirmation.equalsIgnoreCase("O")) {
            admin.bannirUtilisateur(utilisateur);
            System.out.println("L'utilisateur a été banni avec succès.");
        } else {
            System.out.println("Opération annulée.");
        }
    }
    
    /**
     * Ajouter un nouveau compte administrateur
     */
    private static void ajouterCompteAdmin() {
        System.out.println("\n--- AJOUTER UN ADMINISTRATEUR ---");
        String id = lireChaine("Nouvel identifiant admin: ");
        String motDePasse = lireChaine("Mot de passe: ");
        
        if (admin.ajouterCompteAdmin(id, motDePasse)) {
            System.out.println("Compte administrateur créé avec succès.");
        } else {
            System.out.println("Erreur: Cet identifiant existe déjà.");
        }
    }
    
    /**
     * Changer le mot de passe utilisateur
     */
    private static void changerMotDePasse() {
        System.out.println("\n--- CHANGER MOT DE PASSE ---");
        String ancienMdp = lireChaine("Ancien mot de passe: ");
        String nouveauMdp = lireChaine("Nouveau mot de passe: ");
        
        if (estAdmin) {
            String id = utilisateurConnecte.getPrenom(); // Dans notre implémentation, on utilise le prénom pour stocker l'id admin
            if (admin.modifierMotDePasseAdmin(id, ancienMdp, nouveauMdp)) {
                System.out.println("Mot de passe modifié avec succès.");
            } else {
                System.out.println("Erreur: Ancien mot de passe incorrect.");
            }
        } else {
            if (admin.modifierMotDePasseUtilisateur(utilisateurConnecte.getMatricule(), ancienMdp, nouveauMdp)) {
                System.out.println("Mot de passe modifié avec succès.");
            } else {
                System.out.println("Erreur: Ancien mot de passe incorrect.");
            }
        }
    }
    
    /**
     * Afficher le planning des courses
     */
    private static void afficherPlanningCourses() {
        System.out.println("\n--- PLANNING DES COURSES ---");
        System.out.println("1. Courses en cours");
        System.out.println("2. Courses à venir");
        System.out.println("3. Historique des courses");
        int choix = lireEntier("Votre choix: ");
        
        switch (choix) {
            case 1:
                afficherCoursesEnCours();
                break;
            case 2:
                afficherCoursesAvenir();
                break;
            case 3:
                afficherHistoriqueCourses();
                break;
            default:
                System.out.println("Option invalide.");
        }
    }
    
    /**
     * Afficher les courses en cours
     */
    private static void afficherCoursesEnCours() {
        List<Course> courses = admin.getPlanning().getCoursesEnCours();
        if (courses.isEmpty()) {
            System.out.println("Aucune course en cours actuellement.");
            return;
        }
        
        System.out.println("\n=== COURSES EN COURS ===");
        for (Course c : courses) {
            afficherDetailsCourse(c);
        }
    }
    
    /**
     * Afficher les courses à venir
     */
    private static void afficherCoursesAvenir() {
        List<Course> courses = admin.getPlanning().getCoursesAvenir();
        if (courses.isEmpty()) {
            System.out.println("Aucune course à venir actuellement.");
            return;
        }
        
        System.out.println("\n=== COURSES À VENIR ===");
        for (Course c : courses) {
            afficherDetailsCourse(c);
        }
    }
    
    /**
     * Afficher l'historique des courses
     */
    private static void afficherHistoriqueCourses() {
        List<Course> courses = admin.getPlanning().getHistoriqueCourses();
        if (courses.isEmpty()) {
            System.out.println("Aucune course dans l'historique.");
            return;
        }
        
        System.out.println("\n=== HISTORIQUE DES COURSES ===");
        for (Course c : courses) {
            afficherDetailsCourse(c);
        }
    }
    
    /**
     * Afficher les détails d'une course
     */
    private static void afficherDetailsCourse(Course course) {
        System.out.println("------------------------------");
        System.out.println("Chauffeur: " + course.getChauffeur().getNom() + " " + course.getChauffeur().getPrenom());
        System.out.println("Itinéraire: " + course.getItineraire().getPointDepart() + " -> " + course.getItineraire().getPointArrivee());
        System.out.println("Points d'arrêt: " + course.getItineraire().getPointsIntermediaires());
        System.out.println("Type: " + course.getTypeCourse());
        System.out.println("Disponibilité: " + course.getDisponibilite().getJoursDisponibles() + " à " + course.getDisponibilite().getHeureDepart());
        System.out.println("Places: " + course.getPassagers().size() + "/" + course.getNombrePlacesDisponibles());
        
        if (!course.getPassagers().isEmpty()) {
            System.out.println("Passagers:");
            for (Utilisateur p : course.getPassagers()) {
                System.out.println("- " + p.getNom() + " " + p.getPrenom());
            }
        }
        System.out.println("------------------------------");
    }
    
    /**
     * Gérer le menu administrateur
     */
    private static void gererMenuAdmin() {
        System.out.println("\n--- MENU ADMINISTRATEUR ---");
        System.out.println("1. Afficher tous les utilisateurs");
        System.out.println("2. Afficher les courses en cours");
        System.out.println("3. Afficher les statistiques");
        System.out.println("4. Afficher les meilleurs chauffeurs");
        System.out.println("5. Afficher les utilisateurs à faible réputation");
        System.out.println("6. Bannir un utilisateur");
        System.out.println("7. Ajouter un compte administrateur");
        System.out.println("8. Afficher le planning des courses");
        System.out.println("0. Se déconnecter");
        
        int choix = lireEntier("Votre choix: ");
        
        switch (choix) {
            case 1:
                afficherTousLesUtilisateurs();
                break;
            case 2:
                admin.afficherCoursesEnCours();
                break;
            case 3:
                admin.afficherStatistiques();
                break;
            case 4:
                admin.afficherTopChauffeurs(5);
                break;
            case 5:
                admin.afficherPiresUtilisateurs(2.5);
                break;
            case 6:
                bannirUtilisateur();
                break;
            case 7:
                ajouterCompteAdmin();
                break;
            case 8:
                afficherPlanningCourses();
                break;
            case 0:
                System.out.println("Déconnexion...");
                utilisateurConnecte = null;
                estAdmin = false;
                break;
            default:
                System.out.println("Option invalide. Veuillez réessayer.");
        }
    }
    
    /**
     * Gérer le menu utilisateur
     */
    private static void gererMenuUtilisateur() {
        System.out.println("\n--- MENU UTILISATEUR ---");
        System.out.println("1. Afficher mon profil");
        System.out.println("2. Modifier mon profil");
        System.out.println("3. Rechercher une course");
        System.out.println("4. Proposer une course");
        System.out.println("5. Mes courses");
        System.out.println("6. Changer mon mot de passe");
        System.out.println("0. Se déconnecter");
        
        int choix = lireEntier("Votre choix: ");
        
        switch (choix) {
            case 1:
                utilisateurConnecte.afficherProfil();
                break;
            case 2:
                // TODO: Implémenter la modification du profil
                System.out.println("Fonctionnalité en cours de développement...");
                break;
            case 3:
                // TODO: Implémenter la recherche de course
                System.out.println("Fonctionnalité en cours de développement...");
                break;
            case 4:
                // TODO: Implémenter la proposition de course
                System.out.println("Fonctionnalité en cours de développement...");
                break;
            case 5:
                // TODO: Implémenter l'affichage des courses de l'utilisateur
                System.out.println("Fonctionnalité en cours de développement...");
                break;
            case 6:
                changerMotDePasse();
                break;
            case 0:
                System.out.println("Déconnexion...");
                utilisateurConnecte = null;
                break;
            default:
                System.out.println("Option invalide. Veuillez réessayer.");
        }
    }
    
    /**
     * Connexion en tant qu'utilisateur
     */
    private static void connexionUtilisateur() {
        System.out.println("\n--- CONNEXION UTILISATEUR ---");
        String matricule = lireChaine("Matricule: ");
        String motDePasse = lireChaine("Mot de passe: ");
        
        if (admin.connexionUtilisateur(matricule, motDePasse)) {
            utilisateurConnecte = admin.rechercherUtilisateur(matricule);
            if (utilisateurConnecte != null) {
                System.out.println("Connexion réussie ! Bienvenue " + utilisateurConnecte.getPrenom() + " " + utilisateurConnecte.getNom());
            } else {
                System.out.println("Erreur: Utilisateur non trouvé malgré identifiants valides.");
                utilisateurConnecte = null;
            }
        } else {
            System.out.println("Erreur: Matricule ou mot de passe incorrect.");
        }
    }
    
    /**
     * Connexion en tant qu'administrateur
     */
    private static void connexionAdmin() {
        System.out.println("\n--- CONNEXION ADMINISTRATEUR ---");
        String id = lireChaine("Identifiant: ");
        String motDePasse = lireChaine("Mot de passe: ");
        
        if (admin.connexionAdmin(id, motDePasse)) {
            estAdmin = true;
            utilisateurConnecte = new Utilisateur() {
                @Override
                public String getNom() {
                    return "Admin";
                }
                
                @Override
                public String getPrenom() {
                    return id;
                }
            };
            System.out.println("Connexion administrateur réussie !");
        } else {
            System.out.println("Erreur: Identifiant ou mot de passe incorrect.");
        }
    }
    
    /**
     * Créer un compte utilisateur
     */
    private static void creerCompteUtilisateur() {
        System.out.println("\n--- CRÉATION DE COMPTE ---");
        String matricule = lireChaine("Matricule: ");
        
        if (admin.utilisateurExiste(matricule)) {
            System.out.println("Erreur: Ce matricule existe déjà.");
            return;
        }
        
        String motDePasse = lireChaine("Mot de passe: ");
        String nom = lireChaine("Nom: ");
        String prenom = lireChaine("Prénom: ");
        
        System.out.println("Type d'utilisateur:");
        System.out.println("1. Étudiant");
        System.out.println("2. Enseignant");
        System.out.println("3. Personnel ATS");
        int typeUtilisateur = lireEntier("Votre choix: ");
        
        Utilisateur nouvelUtilisateur = null;
        
        switch (typeUtilisateur) {
            case 1:
                int anneeAdmission = lireEntier("Année d'admission: ");
                String faculte = lireChaine("Faculté: ");
                String specialite = lireChaine("Spécialité: ");
                nouvelUtilisateur = new Etudiant(nom, prenom, matricule, anneeAdmission, faculte, specialite);
                break;
            case 2:
                int anneeRecrutementEns = lireEntier("Année de recrutement: ");
                String faculteEns = lireChaine("Faculté: ");
                nouvelUtilisateur = new Enseignant(nom, prenom, matricule, anneeRecrutementEns, faculteEns);
                break;
            case 3:
                int anneeRecrutementATS = lireEntier("Année de recrutement: ");
                String service = lireChaine("Service de rattachement: ");
                nouvelUtilisateur = new ATS(nom, prenom, matricule, anneeRecrutementATS, service);
                break;
            default:
                System.out.println("Type d'utilisateur invalide.");
                return;
        }
        
        