package p1;
import java.util.Scanner;
class Etudiant extends Utilisateur {
    private int anneeAdmission;
    private String faculté;
    private String specialité;
    
    @Override
    public void modifierProfil(Profil nouveauProfil) {
        super.modifierProfil(nouveauProfil);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Nouvelle année d'admission : ");
        this.anneeAdmission = scanner.nextInt();
        scanner.nextLine(); // Pour consommer le retour à la ligne

        System.out.print("Nouvelle faculté : ");
        this.faculté = scanner.nextLine();

        System.out.print("Nouvelle spécialité : ");
        this.specialité = scanner.nextLine();
    }

    @Override
    public void afficherProfil() {
        super.afficherProfil(); // Affiche nom, prénom, etc.
        System.out.println("Année d'admission: " + anneeAdmission);
        System.out.println("Faculté: " + faculté);
        System.out.println("Spécialité: " + specialité);
    }
    }
}
