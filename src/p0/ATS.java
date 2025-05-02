package p0;
import p1.Profil;
public class ATS extends Utilisateur {
    private int anneeRecrutement;
    private String serviceRattachement;

    public ATS(String nom, String prenom, String matricule, Profil profil,
               int anneeRecrutement, String serviceRattachement) {
        super(nom, prenom, matricule, profil);
        this.anneeRecrutement = anneeRecrutement;
        this.serviceRattachement = serviceRattachement;
    }

    @Override
    public void modifierProfil(Profil nouveauProfil) {
        super.modifierProfil(nouveauProfil);
        // Mettre à jour anneeRecrutement et service si nécessaire
    }

    @Override
    public void afficherProfil() {
        super.afficherProfil();
        System.out.println("ATS - Année de recrutement : " + anneeRecrutement);
        System.out.println("Service : " + serviceRattachement);
    }
}

