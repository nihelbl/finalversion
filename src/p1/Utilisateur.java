package p1;
import java.util.List;
import java.util.ArrayList;

abstract class Utilisateur {
    protected String nom;
    protected String prenom;
    protected String matricule;
    protected double reputation;
    protected List<String> commentaires = new ArrayList<>();
    protected Profil profil;
    protected HistoriqueUtilisateur historique;

    public void modifierProfil(Profil nouveauProfil) {
        this.profil = nouveauProfil;
    }

    public void noterUtilisateur(Utilisateur utilisateur, int note, String commentaire) {
        utilisateur.reputation = (utilisateur.reputation + note) / 2.0;
        utilisateur.commentaires.add(commentaire);
    }

    public void afficherProfil() {
        System.out.println("Nom: " + nom);
        System.out.println("Prénom: " + prenom);
        System.out.println("Matricule: " + matricule);
        System.out.println("Réputation: " + reputation);
        if (profil != null) {
            profil.afficherProfil();
        }

    public void afficherhistorique() {
        historique.afficherHistorique();
    }
}
