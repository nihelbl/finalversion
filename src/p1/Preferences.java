package p1;
import p0.Utilisateur;
import monprojet.enums.PreferenceType;
import java.util.Map;
import java.util.HashMap;

public class Preferences {
    private Map<PreferenceType, String> preferences;
    
    public Preferences() {
        this.preferences = new HashMap<>();}
    
    public void ajouterPreference(PreferenceType cle, String valeur) {
        preferences.put(cle, valeur);}
    
    public void modifierPreference(PreferenceType cle, String nouvelleValeur) {
        preferences.put(cle, nouvelleValeur);}
    
    public void supprimerPreference(PreferenceType cle) {
        preferences.remove(cle);}
    
    public void afficherPreferences() {
        for (PreferenceType cle : preferences.keySet()) {
            System.out.println(cle + ": " + preferences.get(cle));}}
    
    public String getPreference(PreferenceType cle) {
        return preferences.get(cle);}
    
    public void setPreferences(Map<PreferenceType, String> nouvellesPreferences) {
        this.preferences = nouvellesPreferences;}
}
