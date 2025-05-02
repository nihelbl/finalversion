package p1;
import p0.Utilisateur;
import monprojet.enums.Statut;
import monprojet.enums.TypeCourse;

import java.util.ArrayList;
import java.util.List;

public class HistoriqueUtilisateur {
    private List<Itineraire> anciensItineraires;
    private List<Statut> anciensStatuts;
    private List<TypeCourse> anciensTypesCourse;
    private List<Evaluation> evaluations;

    public HistoriqueUtilisateur() {
        this.anciensItineraires = new ArrayList<>();
        this.anciensStatuts = new ArrayList<>();
        this.anciensTypesCourse = new ArrayList<>();
        this.evaluations = new ArrayList<>();
    }

    public void ajouterItineraire(Itineraire itineraire) {
        if (itineraire != null)
            anciensItineraires.add(itineraire);
    }

    public void ajouterStatut(Statut statut) {
        if (statut != null)
            anciensStatuts.add(statut);
    }

    public void ajouterTypeCourse(TypeCourse type) {
        if (type != null)
            anciensTypesCourse.add(type);
    }

    public void ajouterEvaluation(Evaluation evaluation) {
        if (evaluation != null)
            evaluations.add(evaluation);
    }

    public List<Evaluation> getEvaluations() {
        return evaluations;
    }

    public void afficherHistorique() {
        System.out.println("— Itinéraires précédents —");
        for (Itineraire itin : anciensItineraires) {
            itin.afficherItineraire();
        }

        System.out.println("— Statuts précédents —");
        for (Statut s : anciensStatuts) {
            System.out.println("- " + s);
        }

        System.out.println("— Types de course précédents —");
        for (TypeCourse t : anciensTypesCourse) {
            System.out.println("- " + t);
        }

        System.out.println("— Évaluations reçues —");
        for (Evaluation e : evaluations) {
            System.out.println("- Note : " + e.getNoteGlobale() + " | Commentaire : " + e.getCommentaire());
        }
    }
}
