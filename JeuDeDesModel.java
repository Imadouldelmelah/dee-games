import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
                                     //Ould Elmelah Imad Eddine . L3/isil/ G2/...
class JeuDeDesModel extends Observable {
    private Joueur joueur1;
    private Joueur joueur2;
    private Joueur joueurActuel;
    private boolean estTourJoueur1;
    private int nombreTours;
    private int toursJoues;
    private int scoreJoueur1;
    private int scoreJoueur2;

    public JeuDeDesModel(Joueur joueur1, Joueur joueur2) {
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        this.joueurActuel = joueur1;
        this.estTourJoueur1 = true;
        this.nombreTours = 0;
        this.toursJoues = 0;
        this.scoreJoueur1 = 0;
        this.scoreJoueur2 = 0;
    }

    public void setNombreTours(int nombreTours) {
        this.nombreTours = nombreTours;
    }

    public int getNombreTours() {
        return nombreTours;
    }

    public int getScoreJoueur1() {
        return scoreJoueur1;
    }

    public int getScoreJoueur2() {
        return scoreJoueur2;
    }

    private void mettreAJourScores() {
        scoreJoueur1 += joueur1.getTotal();
        scoreJoueur2 += joueur2.getTotal();
    }

    public String getGagnant() {
        if (scoreJoueur1 > scoreJoueur2) {
            return joueur1.getNom();
        } else if (scoreJoueur2 > scoreJoueur1) {
            return joueur2.getNom();
        } else {
            return "Match nul";
        }
    }

    public Joueur getJoueur1() {
        return joueur1;
    }

    public Joueur getJoueur2() {
        return joueur2;
    }

    public Joueur getJoueurActuel() {
        return joueurActuel;
    }

    public void lancerDes(Joueur joueur) {
        if (toursJoues < nombreTours && ((joueur.equals(joueur1) && estTourJoueur1) || (joueur.equals(joueur2) && !estTourJoueur1))) {
            Random random = new Random();
            int de1 = random.nextInt(6) + 1;
            int de2 = random.nextInt(6) + 1;

            joueur.setDe1(de1);
            joueur.setDe2(de2);
            joueur.setTotal(de1 + de2);

            mettreAJourScores();

            setChanged();
            notifyObservers(joueur);

            passerAuJoueurSuivant();

            if (estTourJoueur1) {
                toursJoues++;
            }
        }
    }

    private void passerAuJoueurSuivant() {
        if (estTourJoueur1) {
            estTourJoueur1 = false;
            joueurActuel = joueur2;
        } else {
            estTourJoueur1 = true;
            joueurActuel = joueur1;
        }

        if (toursJoues == nombreTours) {
            toursJoues = 0;
        }
    }

    public int getToursJoues() {
        return toursJoues;
    }
}