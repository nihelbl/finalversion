package p0;
import p1.Profil;
public class Enseignant extends Utilisateur {
    private int anneeRecrutement;
    private String faculte;

    public Enseignant(String nom, String prenom, String matricule, Profil profil,
                      int anneeRecrutement, String faculte) {
        super(nom, prenom, matricule, profil);
        this.anneeRecrutement = anneeRecrutement;
        this.faculte = faculte;
    }

    @Override
    public void modifierProfil(Profil nouveauProfil) {
        super.modifierProfil(nouveauProfil);
        // Mettre à jour anneeRecrutement et faculte si nécessaire
    }

    @Override
    public void afficherProfil() {
        super.afficherProfil();
        System.out.println("Enseignant - Année de recrutement : " + anneeRecrutement);
        System.out.println("Faculté : " + faculte);
    }
}




