package p1;
import p0.Utilisateur;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import p0.ATS;
import p0.Enseignant;
import p0.Etudiant;


/**
 * Classe Administration qui gère l'ensemble des utilisateurs et des courses.
 * Implémente également un système d'authentification pour les administrateurs.
 */
public class Administration {
    private List<Utilisateur> utilisateurs;
    private Planning planning;
    private Map<String, String> compteAdmins; // <id, motDePasse>
    private Map<String, String> compteUtilisateurs; // <matricule, motDePasse>
    private static Administration instance; // Pattern Singleton

    /**
     * Constructeur privé pour le pattern Singleton
     */
    private Administration() {
        this.utilisateurs = new ArrayList<>();
        this.planning = new Planning();
        this.compteAdmins = new HashMap<>();
        this.compteUtilisateurs = new HashMap<>();
        
        // Ajouter un compte admin par défaut
        this.compteAdmins.put("ADMIN", "2025POO");
    }

    /**
     * Méthode pour obtenir l'instance unique d'Administration
     * @return L'instance unique d'Administration
     */
    public static Administration getInstance() {
        if (instance == null) {
            instance = new Administration();
        }
        return instance;
    }

    /**
     * Ajouter un utilisateur au système
     * @param utilisateur L'utilisateur à ajouter
     */
    public void ajouterUtilisateur(Utilisateur utilisateur) {
        this.utilisateurs.add(utilisateur);
    }

    /**
     * Supprimer un utilisateur du système
     * @param utilisateur L'utilisateur à supprimer
     */
    public void supprimerUtilisateur(Utilisateur utilisateur) {
        this.utilisateurs.remove(utilisateur);
        // Supprimer également son compte utilisateur
        this.compteUtilisateurs.remove(utilisateur.getMatricule());
    }

    /**
     * Bannir un utilisateur du système (suppression + log)
     * @param utilisateur L'utilisateur à bannir
     */
    public void bannirUtilisateur(Utilisateur utilisateur) {
        System.out.println("ALERTE: L'utilisateur " + utilisateur.getNom() + " " 
                          + utilisateur.getPrenom() + " (Matricule: " 
                          + utilisateur.getMatricule() + ") a été banni.");
        this.supprimerUtilisateur(utilisateur);
        // On pourrait ajouter un log ou enregistrer cette action dans une base de données
    }

    /**
     * Vérifier si un admin peut se connecter
     * @param id Identifiant de l'admin
     * @param motDePasse Mot de passe de l'admin
     * @return true si l'authentification est réussie, false sinon
     */
    public boolean connexionAdmin(String id, String motDePasse) {
        String mdpStocke = this.compteAdmins.get(id);
        return mdpStocke != null && mdpStocke.equals(motDePasse);
    }

    /**
     * Vérifier si un utilisateur peut se connecter
     * @param matricule Matricule de l'utilisateur
     * @param motDePasse Mot de passe de l'utilisateur
     * @return true si l'authentification est réussie, false sinon
     */
    public boolean connexionUtilisateur(String matricule, String motDePasse) {
        String mdpStocke = this.compteUtilisateurs.get(matricule);
        return mdpStocke != null && mdpStocke.equals(motDePasse);
    }

    /**
     * Ajouter un compte administrateur
     * @param id Identifiant du nouvel admin
     * @param motDePasse Mot de passe du nouvel admin
     * @return true si l'ajout est réussi, false si l'id existe déjà
     */
    public boolean ajouterCompteAdmin(String id, String motDePasse) {
        if (this.compteAdmins.containsKey(id)) {
            return false;
        }
        this.compteAdmins.put(id, motDePasse);
        return true;
    }

    /**
     * Ajouter un compte utilisateur
     * @param matricule Matricule de l'utilisateur
     * @param motDePasse Mot de passe de l'utilisateur
     * @return true si l'ajout est réussi, false si le matricule existe déjà
     */
    public boolean ajouterCompteUtilisateur(String matricule, String motDePasse) {
        if (this.compteUtilisateurs.containsKey(matricule)) {
            return false;
        }
        this.compteUtilisateurs.put(matricule, motDePasse);
        return true;
    }

    /**
     * Modifier le mot de passe d'un administrateur
     * @param id Identifiant de l'admin
     * @param ancienMdp Ancien mot de passe
     * @param nouveauMdp Nouveau mot de passe
     * @return true si la modification est réussie, false sinon
     */
    public boolean modifierMotDePasseAdmin(String id, String ancienMdp, String nouveauMdp) {
        if (connexionAdmin(id, ancienMdp)) {
            this.compteAdmins.put(id, nouveauMdp);
            return true;
        }
        return false;
    }

    /**
     * Modifier le mot de passe d'un utilisateur
     * @param matricule Matricule de l'utilisateur
     * @param ancienMdp Ancien mot de passe
     * @param nouveauMdp Nouveau mot de passe
     * @return true si la modification est réussie, false sinon
     */
    public boolean modifierMotDePasseUtilisateur(String matricule, String ancienMdp, String nouveauMdp) {
        if (connexionUtilisateur(matricule, ancienMdp)) {
            this.compteUtilisateurs.put(matricule, nouveauMdp);
            return true;
        }
        return false;
    }

    /**
     * Afficher les statistiques d'utilisation de l'application
     */
    public void afficherStatistiques() {
        System.out.println("=============== STATISTIQUES ===============");
        System.out.println("Nombre total d'utilisateurs: " + this.utilisateurs.size());
        
        long nbEtudiants = this.utilisateurs.stream()
                .filter(u -> u instanceof Etudiant)
                .count();
        System.out.println("Nombre d'étudiants: " + nbEtudiants);
        
        long nbEnseignants = this.utilisateurs.stream()
                .filter(u -> u instanceof Enseignant)
                .count();
        System.out.println("Nombre d'enseignants: " + nbEnseignants);
        
        long nbATS = this.utilisateurs.stream()
                .filter(u -> u instanceof ATS)
                .count();
        System.out.println("Nombre de personnels ATS: " + nbATS);
        
        int nbCoursesEnCours = this.planning.getCoursesEnCours().size();
        System.out.println("Nombre de courses en cours: " + nbCoursesEnCours);
        
        int nbCoursesAvenir = this.planning.getCoursesAvenir().size();
        System.out.println("Nombre de courses à venir: " + nbCoursesAvenir);
        
        int nbCoursesHistorique = this.planning.getHistoriqueCourses().size();
        System.out.println("Nombre de courses terminées: " + nbCoursesHistorique);
        
        // Calcul du nombre moyen de passagers par course
        double moyennePassagers = this.planning.getHistoriqueCourses().stream()
                .mapToInt(c -> c.getPassagers().size())
                .average().orElse(0.0);
        System.out.println("Nombre moyen de passagers par course: " + String.format("%.2f", moyennePassagers));
        
        System.out.println("==========================================");
    }

    /**
     * Afficher les courses en cours à un instant donné
     */
    public void afficherCoursesEnCours() {
        System.out.println("=============== COURSES EN COURS ===============");
        List<Course> coursesEnCours = this.planning.getCoursesEnCours();
        
        if (coursesEnCours.isEmpty()) {
            System.out.println("Aucune course en cours actuellement.");
        } else {
            for (Course course : coursesEnCours) {
                System.out.println("Course: " + course.getChauffeur().getNom() + " " + course.getChauffeur().getPrenom());
                System.out.println("Itinéraire: " + course.getItineraire().getPointDepart() + " -> " + course.getItineraire().getPointArrivee());
                System.out.println("Nombre de passagers: " + course.getPassagers().size() + "/" + course.getNombrePlacesDisponibles());
                System.out.println("------------------------------------------");
            }
        }
        System.out.println("===============================================");
    }

    /**
     * Afficher le top des chauffeurs selon leur réputation
     * @param limit Nombre de chauffeurs à afficher
     */
    public void afficherTopChauffeurs(int limit) {
        System.out.println("=============== TOP " + limit + " CHAUFFEURS ===============");
        
        List<Utilisateur> chauffeurs = this.utilisateurs.stream()
                .filter(u -> u.getProfil() != null && u.getProfil().getStatut() == Statut.CHAUFFEUR)
                .sorted(Comparator.comparing(Utilisateur::getReputation).reversed())
                .limit(limit)
                .collect(Collectors.toList());
        
        if (chauffeurs.isEmpty()) {
            System.out.println("Aucun chauffeur enregistré.");
        } else {
            int rang = 1;
            for (Utilisateur chauffeur : chauffeurs) {
                System.out.println("#" + rang + ": " + chauffeur.getNom() + " " + chauffeur.getPrenom());
                System.out.println("Réputation: " + String.format("%.2f", chauffeur.getReputation()) + "/5");
                System.out.println("------------------------------------------");
                rang++;
            }
        }
        System.out.println("===============================================");
    }

    /**
     * Afficher les utilisateurs avec une mauvaise réputation
     * @param seuilReputation Seuil en dessous duquel la réputation est considérée comme mauvaise
     */
    public void afficherPiresUtilisateurs(double seuilReputation) {
        System.out.println("=============== UTILISATEURS À SURVEILLER ===============");
        System.out.println("Utilisateurs ayant une réputation inférieure à " + seuilReputation + "/5:");
        
        List<Utilisateur> mauvaisUtilisateurs = this.utilisateurs.stream()
                .filter(u -> u.getReputation() < seuilReputation && u.getReputation() > 0) // Réputation > 0 pour exclure les nouveaux utilisateurs
                .sorted(Comparator.comparing(Utilisateur::getReputation))
                .collect(Collectors.toList());
        
        if (mauvaisUtilisateurs.isEmpty()) {
            System.out.println("Aucun utilisateur avec une mauvaise réputation.");
        } else {
            for (Utilisateur utilisateur : mauvaisUtilisateurs) {
                System.out.println(utilisateur.getNom() + " " + utilisateur.getPrenom() + " (Matricule: " + utilisateur.getMatricule() + ")");
                System.out.println("Réputation: " + String.format("%.2f", utilisateur.getReputation()) + "/5");
                System.out.println("Derniers commentaires:");
                
                List<String> commentaires = utilisateur.getCommentaires();
                int nbCommentaires = Math.min(3, commentaires.size()); // Afficher au maximum les 3 derniers commentaires
                
                for (int i = 0; i < nbCommentaires; i++) {
                    System.out.println("- " + commentaires.get(commentaires.size() - 1 - i));
                }
                System.out.println("------------------------------------------");
            }
        }
        System.out.println("====================================================");
    }

    /**
     * Consulter le planning des courses
     * @return Le planning des courses
     */
    public Planning getPlanning() {
        return this.planning;
    }

    /**
     * Consulter la liste des utilisateurs
     * @return La liste des utilisateurs
     */
    public List<Utilisateur> getUtilisateurs() {
        return this.utilisateurs;
    }
    
    /**
     * Rechercher un utilisateur par matricule
     * @param matricule Le matricule de l'utilisateur recherché
     * @return L'utilisateur trouvé ou null si non trouvé
     */
    public Utilisateur rechercherUtilisateur(String matricule) {
        return this.utilisateurs.stream()
                .filter(u -> u.getMatricule().equals(matricule))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Vérifier si un utilisateur existe par son matricule
     * @param matricule Le matricule à vérifier
     * @return true si l'utilisateur existe, false sinon
     */
    public boolean utilisateurExiste(String matricule) {
        return this.utilisateurs.stream()
                .anyMatch(u -> u.getMatricule().equals(matricule));
    }
    
    /**
     * Générer un rapport de l'utilisation de l'application entre deux dates
     * @param debut Date de début du rapport
     * @param fin Date de fin du rapport
     * @return Un rapport textuel
     */
    public String genererRapport(LocalDate debut, LocalDate fin) {
        StringBuilder rapport = new StringBuilder();
        rapport.append("RAPPORT D'UTILISATION: " + debut + " - " + fin + "\n\n");
        
        // Filtrer les courses dans la période
        List<Course> coursesFiltre = this.planning.getHistoriqueCourses().stream()
                .filter(c -> {
                    LocalDateTime dateHeure = c.getDisponibilite().getDateHeure();
                    LocalDate date = dateHeure.toLocalDate();
                    return !date.isBefore(debut) && !date.isAfter(fin);
                })
                .collect(Collectors.toList());
        
        rapport.append("Nombre de courses effectuées: " + coursesFiltre.size() + "\n");
        
        // Nombre total de passagers transportés
        int totalPassagers = coursesFiltre.stream()
                .mapToInt(c -> c.getPassagers().size())
                .sum();
        rapport.append("Nombre total de passagers transportés: " + totalPassagers + "\n");
        
        // Chauffeurs les plus actifs
        Map<Utilisateur, Long> chauffeursCounts = coursesFiltre.stream()
                .collect(Collectors.groupingBy(Course::getChauffeur, Collectors.counting()));
        
        rapport.append("\nChauffeurs les plus actifs:\n");
        chauffeursCounts.entrySet().stream()
                .sorted(Map.Entry.<Utilisateur, Long>comparingByValue().reversed())
                .limit(5)
                .forEach(entry -> {
                    Utilisateur chauffeur = entry.getKey();
                    Long count = entry.getValue();
                    rapport.append("- " + chauffeur.getNom() + " " + chauffeur.getPrenom() + 
                                 " (" + chauffeur.getMatricule() + "): " + count + " course(s)\n");
                });
        
        return rapport.toString();
    }
}