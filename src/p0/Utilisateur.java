package p0;
import p1.Profil;
import p1.HistoriqueUtilisateur;
import p1.Evaluation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects; // pour controller les null 
import monprojet.enums.CritereEvaluation;


public abstract class Utilisateur {
    private static int compteur = 0; //ttribut statique utilisé pour générer un ID unique automatiquement pour chaque utilisateur.
    private int id;
    private String nom;
    private String prenom;
    private String matricule;
    private double reputation; //Moyenne des notes reçues par l’utilisateur (de 1 à 5), initialisée à 5.0.
    private List<String> commentaires; //Liste de commentaires laissés par d'autres utilisateurs.
    private Profil profil;//Objet Profil contenant des détails supplémentaires (définis dans une autre classe Profil).
    private HistoriqueUtilisateur historique;//Historique des évaluations reçues (objet de la classe HistoriqueUtilisateur).


    //Initialise tous les champs, génère un ID automatiquement, et crée une liste vide de commentaires ainsi qu’un historique vide.
  public Utilisateur(String nom, String prenom, String matricule, Profil profil) {
    this.id = ++compteur;
    this.nom = Objects.requireNonNull(nom, "Le nom ne peut pas être null");
    this.prenom = Objects.requireNonNull(prenom, "Le prénom ne peut pas être null");
    this.matricule = Objects.requireNonNull(matricule, "Le matricule ne peut pas être null");
    this.profil = Objects.requireNonNull(profil, "Le profil ne peut pas être null");  // Validation sans copie
    this.reputation = 5.0;
    this.commentaires = new ArrayList<>();
    this.historique = new HistoriqueUtilisateur();
  }


    public void modifierProfil(Profil nouveauProfil) {
        this.profil = nouveauProfil;
    }

    // Méthode pour ajouter une évaluation reçue
    public void ajouterEvaluation(Evaluation evaluation) {
      if (evaluation != null && evaluation.getUtilisateurEvalue().equals(this)) {
        this.historique.ajouterEvaluation(evaluation);
        
        // Ajouter le commentaire à la liste des commentaires s'il existe
        if (evaluation.getCommentaire() != null && !evaluation.getCommentaire().isEmpty()) {
            this.commentaires.add(evaluation.getCommentaire());
        }
        
        mettreAJourReputation();
      }
    }

    public void evaluerUtilisateur(Utilisateur utilisateur, CritereEvaluation critere, int note, String commentaire) {
        Evaluation evaluation = new Evaluation(this, utilisateur, note, critere, commentaire);
        utilisateur.ajouterEvaluation(evaluation);
    }
    
    // Méthode pour calculer la réputation (moyenne des notes)
    private void mettreAJourReputation() {
      List<Evaluation> evaluations = historique.getEvaluations();
    
      if (evaluations == null || evaluations.isEmpty()) {
        this.reputation = 5.0;  // Note par défaut pour les nouveaux
        return;
      }
      int somme = 0;
      for (Evaluation e : evaluations) {
        somme += e.getNoteGlobale();
      }
    
      this.reputation = (double) somme / evaluations.size();
    }

    public void afficherProfil() {
        System.out.println("========== Affichage profil ==========");
        System.out.println("Nom: " + nom);
        System.out.println("Prénom: " + prenom);
        System.out.println("Matricule: " + matricule);
        System.out.println("Réputation: " + reputation);
        profil.afficherProfil();
    }

    public void afficherHistorique() {
        System.out.println("========== Affichage Historique ==========");
        System.out.println("Historique de " + nom + " " + prenom + ":");
        historique.afficherHistorique();
    }


    //getteur if needed
    public List<String> getCommentaires() {
        return Collections.unmodifiableList(commentaires);
    }
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public int getId() {
        return id;
    }
    public Profil getProfil() {
        return profil;}
    
    public String getMatricule() {
        return matricule;}
    public double getReputation() {
    	return reputation;}
    }

