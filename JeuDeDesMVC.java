import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;                    //Ould Elmelah Imad Eddine . L3/isil/ G2/...
import java.util.Observer;
import java.util.Random;

class Joueur {
    private String nom;
    private int de1, de2, total;

    public Joueur(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public int getDe1() {
        return de1;
    }

    public void setDe1(int de1) {
        this.de1 = de1;
    }

    public int getDe2() {
        return de2;
    }

    public void setDe2(int de2) {
        this.de2 = de2;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Joueur joueur = (Joueur) obj;
        return nom.equals(joueur.nom);
    }

    @Override
    public int hashCode() {
        return nom.hashCode();
    }
}

public class JeuDeDesMVC {
    public static void main(String[] args) {
        Joueur joueur1 = new Joueur("Joueur 1");
        Joueur joueur2 = new Joueur("Joueur 2");

        JeuDeDesModel model = new JeuDeDesModel(joueur1, joueur2);
        JeuDeDesView view = new JeuDeDesView(model);
        JeuDeDesController controller = new JeuDeDesController(model, view);
    }
}