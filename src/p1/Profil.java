package p1;
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
        JourSemaine jour = fromDayOfWeek(dateHeure.getDayOfWeek());
        return disponibilites.getJoursDisponibles().contains(jour);
    }
    
    private JourSemaine fromDayOfWeek(DayOfWeek dayOfWeek) {
        switch (dayOfWeek) {
            case MONDAY: return JourSemaine.LUNDI;
            case TUESDAY: return JourSemaine.MARDI;
            case WEDNESDAY: return JourSemaine.MERCREDI;
            case THURSDAY: return JourSemaine.JEUDI;
            case FRIDAY: return JourSemaine.VENDREDI;
            case SATURDAY: return JourSemaine.SAMEDI;
            case SUNDAY: return JourSemaine.DIMANCHE;
            default: throw new IllegalArgumentException("Jour inconnu: " + dayOfWeek);
        }
    }

    public void afficherProfil() {
        System.out.println("Statut: " + statut);
        System.out.println("Type de course: " + typeCourse);
        itineraire.afficherItineraire();
        preferences.afficherPreferences();
        disponibilites.afficherDisponibilite();
    }

    // Getters (si besoin)
    public Statut getStatut() { return statut; }
    public TypeCourse getTypeCourse() { return typeCourse; }
    public Itineraire getItineraire() { return itineraire; }
    public Preferences getPreferences() { return preferences; }
    public Disponibilites getDisponibilites() { return disponibilites; }
}
