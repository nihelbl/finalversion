package p1;
import p0.Utilisateur;
import monprojet.enums.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
public class Profil {
    private Statut statut;
    private TypeCourse typeCourse;
    private Itineraire itineraire;
    private Preferences preferences;
    private Disponibilites disponibilites;

    public Profil(Statut statut, TypeCourse typeCourse, Itineraire itineraire,
                  Preferences preferences, Disponibilites disponibilites) {
        this.statut = statut;
        this.typeCourse = typeCourse;
        this.itineraire = itineraire;
        this.preferences = preferences;
        this.disponibilites = disponibilites;
    }

    public void modifierStatut(Statut nouveauStatut) {
        this.statut = nouveauStatut;
    }

    public void modifierTypeCourse(TypeCourse nouveauTypeCourse) {
        this.typeCourse = nouveauTypeCourse;
    }

    public void modifierItineraire(Itineraire nouvelItineraire) {
        this.itineraire = nouvelItineraire;
    }

    public void modifierPreferences(Preferences nouvellesPreferences) {
        this.preferences = nouvellesPreferences;
    }

    public void modifierDisponibilites(Disponibilites nouvellesDisponibilites) {
        this.disponibilites = nouvellesDisponibilites;
    }

    public boolean estDisponible(LocalDateTime dateHeure) {
    	JourSemaine jour = JourSemaine.from(dateHeure.getDayOfWeek());
        return disponibilites.getJoursDisponibles().contains(jour);
    }
    
    
    public void afficherProfil() {
        System.out.println("Profil{" +
            "statut=" + statut +
            ", typeCourse=" + typeCourse +
            ", itineraire=" + itineraire +
            ", preferences=" + preferences +
            ", disponibilites=" + disponibilites +
            '}');
    }

    // Getters (si besoin)
    public Statut getStatut() { return statut; }
    public TypeCourse getTypeCourse() { return typeCourse; }
    public Itineraire getItineraire() { return itineraire; }
    public Preferences getPreferences() { return preferences; }
    public Disponibilites getDisponibilites() { return disponibilites; }
}
