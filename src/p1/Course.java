package p1;
import p0.Utilisateur;
import java.util.*;
import monprojet.enums.TypeCourse;
import monprojet.enums.Statut;

public class Course {
       private Utilisateur chauffeur ;
       private List<Utilisateur> passagers;
       private Itineraire itineraire;
       private Disponibilites disponibilite;
       private TypeCourse typecourse;
       private Statut statut;
       protected int nombrePlacesDisponibles;
     public Course( Utilisateur chauffeur ,Utilisateur passagers,Itineraire itineraire,Disponibilites disponibilite,TypeCourse typecourse, Statut status,int nombrePlacesDisponibles){
        this.chauffeur=chauffeur ;
        this.passagers = new ArrayList<>();
        this.itineraire=itineraire;
        this.disponibilite=disponibilite;
        this.statut = statut.en_cours;
        this.typecourse=typecourse;
        this.nombrePlacesDisponibles=nombrePlacesDisponibles;
     }
       public boolean estDisponible(){
        return (statut == statut.en_cours && passagers.size() < nombrePlacesDisponibles);
       }
       public void ajouterpassager(Utilisateur passager){
        if(estDisponible()){
            passagers.add(passager);
            System.out.println("Passager ajouté : "+ passager.getNom());}
            else{
                System.out.println("Impossible d’ajouter le passager !");
            }

       }
       public void retirerPassager(Utilisateur passager) {
        if (passagers.remove(passager)) {
            System.out.println("Passager retire : " + passager.getNom());
        } else {
            System.out.println("Ce passager n’est pas dans la course !");
        }
    }
    public void terminerCourse() {
        this.statut = statut.terminee;
        System.out.println("Course terminee.");
    }
    public int placesRestantes() {
        return nombrePlacesDisponibles - passagers.size();
    }
    public boolean correspond(Utilisateur passager, Utilisateur chauffeur) {
        Profil profilPassager = passager.getProfil();
        Profil profilChauffeur = chauffeur.getProfil();
        String arriveePassager = profilPassager.getItineraire().getPointArrivee();
        String arriveeChauffeur = profilChauffeur.getItineraire().getPointArrivee();

        return arriveePassager.equals(arriveeChauffeur);
    }

        public Statut getStatut() {
            return statut;
        }
    
        public List<Utilisateur> getPassagers() {
            return passagers;
        }
    
        public Utilisateur getChauffeur() {
            return chauffeur;
        }

        public void afficherDetails() {
            System.out.println("========== Details de la Course ==========");
            System.out.println("Chauffeur : " + chauffeur.getNom());
            System.out.println("Type de course : " + typecourse);
            itineraire.afficherItineraire();
            System.out.println("Passagers : ");
            for (Utilisateur p : passagers) {
                System.out.println(" - " + p.getNom());
            }
            System.out.println("Disponibilite : ");
            disponibilite.afficherDisponibilite();
            System.out.println("Places restantes : " + placesRestantes());
            System.out.println("Statut : " + statut);
            
        }
    
    }
