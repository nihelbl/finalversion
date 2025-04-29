package p1;
import java.util.Scanner;
class Enseignant extends Utilisateur {
    private int anneeRecrutement;
    private String faculté;
    
    @Override
    public void modifierProfil(Profil nouveauProfil) {
        super.modifierProfil(nouveauProfil); // modifie les champs généraux du profil

        // Supposons que tu veux aussi modifier la faculté et l'année
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nouvelle faculté : ");
        this.faculté = scanner.nextLine();

        System.out.print("Nouvelle année de recrutement : ");
        this.anneeRecrutement = scanner.nextInt();
    }
    
    @Override
    public void afficherProfil() {
        super.afficherProfil();
        System.out.println("Année de recrutement: " + anneeRecrutement);
        System.out.println("Faculté: " + faculté);
    }
    }
}

