package p1;
import java.util.Scanner;
class ATS extends Utilisateur {
    private int anneeRecrutement;
    private String serviceRattachement;
    @Override
    public void modifierProfil(Profil nouveauProfil) {
        super.modifierProfil(nouveauProfil);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Nouvelle année de recrutement : ");
        this.anneeRecrutement = scanner.nextInt();
        scanner.nextLine(); // Pour consommer le retour à la ligne

        System.out.print("Nouveau service de rattachement : ");
        this.serviceRattachement = scanner.nextLine();
    }
    @Override
    public void afficherProfil() {
        super.afficherProfil();
        System.out.println("Année de recrutement: " + anneeRecrutement);
        System.out.println("Service de rattachement: " + serviceRattachement);
    }
    }
}