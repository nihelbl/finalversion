package p1;
import p0.Utilisateur;
import java.util.List;
import monprojet.enums.JourSemaine;
import monprojet.enums.DisponibiliteType;

public class Disponibilites {
    private List<JourSemaine> joursDisponibles;
    private String heureDepart;
    private DisponibiliteType type;

    public Disponibilites(List<JourSemaine> joursDisponibles, String heureDepart, DisponibiliteType type) {
        this.joursDisponibles = joursDisponibles;
        this.heureDepart = heureDepart;
        this.type = type;
    }

    public List<JourSemaine> getJoursDisponibles() { return joursDisponibles; }
    public void setJoursDisponibles(List<JourSemaine> joursDisponibles) { this.joursDisponibles = joursDisponibles; }
    public String getHeureDepart() { return heureDepart; }
    public void setHeureDepart(String heureDepart) { this.heureDepart = heureDepart; }

    public void ajouterJourDisponible(JourSemaine jour) {
        if (!joursDisponibles.contains(jour)) {
            joursDisponibles.add(jour);
        }
    }

    public void supprimerJourDisponible(JourSemaine jour) {
        joursDisponibles.remove(jour);
    }

    public void afficherDisponibilite() {
        System.out.println("Disponibilité à " + heureDepart + " les jours : " + joursDisponibles);
    }
}