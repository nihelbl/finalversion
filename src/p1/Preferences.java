package p1;
import p0.Utilisateur;
import java.util.Map;
import java.util.HashMap;

public class Preferences {
    private Map<String, String> preferences;
    public Preferences() {
        this.preferences = new HashMap<>();
    }
    public String getPreference(String cle) {
        return preferences.get(cle);
    }
    public void ajouterPreference(String cle, String valeur) {
        preferences.put(cle, valeur);
    }
    public void modifierPreference(String cle, String nouvelleValeur) {
        preferences.put(cle, nouvelleValeur);
    }
    public void supprimerPreference(String cle) {
        preferences.remove(cle);
    }
    public void afficherPreferences() {
        for (String cle : preferences.keySet()) {
            System.out.println(cle + ": " + preferences.get(cle));
        }
    }
}
