package p0;
import p1.Profil;
import p1.Evaluation;
public class Etudiant extends Utilisateur {
    private int anneeAdmission;
    private String faculte;
    private String specialite;
   
    public Etudiant(String nom, String prenom, String matricule, Profil profil,
                    int anneeAdmission, String faculte, String specialite) {
        super(nom, prenom, matricule, profil);
        this.anneeAdmission = anneeAdmission;
        this.faculte = faculte;
        this.specialite = specialite;
        
    }

    @Override
    public void modifierProfil(Profil nouveauProfil) {
        super.modifierProfil(nouveauProfil);
        // Mettre à jour anneeAdmission, faculte, specialite si nécessaire
    }

    @Override
    public void afficherProfil() {
        super.afficherProfil();
        System.out.println("Étudiant - Année d'admission : " + anneeAdmission);
        System.out.println("Faculté : " + faculte);
        System.out.println("Spécialité : " + specialite);
    }
    @Override
      public void ajouterEvaluation(Evaluation evaluation) {
        super.ajouterEvaluation(evaluation); // conserve logique de mise à jour + commentaires + réputation
    }
    @Override
    public void afficherHistorique() {
        super.afficherHistorique();
    }
}

