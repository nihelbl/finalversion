package p1;
import p0.Utilisateur;
import monprojet.enums.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
public class Profil {
    private StatutUser statut;
    private TypeCourse typeCourse;
    private Itineraire itineraire;
    private Preferences preferences;
    private Disponibilites disponibilites;
    private Planning planning;

    public Profil() {}
    public Profil(StatutUser statut, TypeCourse typeCourse, Itineraire itineraire,
                  Preferences preferences, Disponibilites disponibilites, Planning planning) {
        this.statut = statut;
        this.typeCourse = typeCourse;
        this.itineraire = itineraire;
        this.preferences = preferences;
        this.disponibilites = disponibilites;
        this.planning = planning;}
    
    public void modifierProfildynamique(StatutUser nouveauStatut, TypeCourse nouveauTypeCourse,Itineraire nouvelItineraire, Preferences nouvellesPreferences,Disponibilites nouvellesDisponibilites) {
               if (nouveauStatut != null) {setStatut(nouveauStatut);}
               if (nouveauTypeCourse != null) {setTypeCourse(nouveauTypeCourse);}
               if (nouvelItineraire != null) {setItineraire(nouvelItineraire);}
               if (nouvellesPreferences != null) {setPreferences(nouvellesPreferences);}
               if (nouvellesDisponibilites != null) {setDisponibilites(nouvellesDisponibilites);}}

    public void modifierStatut(StatutUser nouveauStatut) {
        this.statut = nouveauStatut;}
 
    public void modifierTypeCourse(TypeCourse nouveauTypeCourse) {
        this.typeCourse = nouveauTypeCourse;}

    public void modifierItineraire(Itineraire nouvelItineraire) {
        this.itineraire = nouvelItineraire;}

    public void modifierPreferences(Preferences nouvellesPreferences) {
        this.preferences = nouvellesPreferences;}

    public void modifierDisponibilites(Disponibilites nouvellesDisponibilites) {
        this.disponibilites = nouvellesDisponibilites;}

    public boolean estDisponible(LocalDateTime dateHeure) {
    	JourSemaine jour = JourSemaine.from(dateHeure.getDayOfWeek());
        return disponibilites.getJoursDisponibles().contains(jour);} 
    
    public void afficherProfildynamique() {
        System.out.println("Profil{" +
            "statut=" + statut +
            ", typeCourse=" + typeCourse +
            ", itineraire=" + itineraire +
            ", preferences=" + preferences +
            ", disponibilites=" + disponibilites +
            '}');}

    // Getters (si besoin)
    public Preferences getPreferences() { return preferences; }
    public Disponibilites getDisponibilites() { return disponibilites;}
    public Planning getPlanning() {return planning;}
    
    public void setPreferences(Preferences preferences) { this.preferences = preferences;}
    public void setDisponibilites(Disponibilites disponibilites) { this.disponibilites = disponibilites;}
    public void setPlanning(Planning planning) {this.planning = planning; }
}
