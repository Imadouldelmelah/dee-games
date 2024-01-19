
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;  //Ould Elmelah Imad Eddine . L3/isil/ G2/...
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

class JeuDeDesView implements Observer {
    private JeuDeDesModel model;
    private JTextField nomJoueur1, nomJoueur2;
    private JButton lancerButtonJoueur1, lancerButtonJoueur2;
    private JLabel resultatLabelJoueur1, resultatLabelJoueur2;
    private JSpinner nombreToursSpinner;
    private JLabel toursRestantsLabel, scoreLabelJoueur1, scoreLabelJoueur2, gagnantLabel;

    public JeuDeDesView(JeuDeDesModel model) {
        this.model = model;
        model.addObserver(this);

        JFrame frame = new JFrame("Jeu de Dés MVC");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel panel = new JPanel(new GridLayout(9, 2));

        // Section Joueur 1
        panel.add(new JLabel("Joueur 1"));
        nomJoueur1 = new JTextField("Joueur 1");
        lancerButtonJoueur1 = new JButton("Lancer les dés");
        resultatLabelJoueur1 = new JLabel("Résultats: Attente");
        panel.add(nomJoueur1);
        panel.add(lancerButtonJoueur1);
        panel.add(resultatLabelJoueur1);
        panel.add(new JLabel("")); // Ligne vide
        panel.add(new JLabel("")); // Ligne vide

        // Section Joueur 2
        panel.add(new JLabel("Joueur 2"));
        nomJoueur2 = new JTextField("Joueur 2");
        lancerButtonJoueur2 = new JButton("Lancer les dés");
        resultatLabelJoueur2 = new JLabel("Résultats: Attente");
        panel.add(nomJoueur2);
        panel.add(lancerButtonJoueur2);
        panel.add(resultatLabelJoueur2);
        panel.add(new JLabel("")); // Ligne vide
        panel.add(new JLabel("")); // Ligne vide

        // Section Paramètres
        panel.add(new JLabel("Nombre de tours:"));
        nombreToursSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        panel.add(nombreToursSpinner);

        JButton demarrerButton = new JButton("Démarrer le jeu");
        panel.add(demarrerButton);

        // Section Statistiques
        panel.add(new JLabel("Statistiques"));
        toursRestantsLabel = new JLabel("Tours restants : 0");
        panel.add(toursRestantsLabel);
        panel.add(new JLabel("")); // Ligne vide
        scoreLabelJoueur1 = new JLabel("Score Joueur 1: 0");
        scoreLabelJoueur2 = new JLabel("Score Joueur 2: 0");
        gagnantLabel = new JLabel("Gagnant: En attente");
        panel.add(scoreLabelJoueur1);
        panel.add(scoreLabelJoueur2);
        panel.add(gagnantLabel);

        // Ajout des actions
        lancerButtonJoueur1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.lancerDes(model.getJoueur1());
            }
        });

        lancerButtonJoueur2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.lancerDes(model.getJoueur2());
            }
        });

        demarrerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int nombreTours = (int) nombreToursSpinner.getValue();
                model.setNombreTours(nombreTours);
                toursRestantsLabel.setText("Tours restants : " + model.getNombreTours());
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        Joueur joueur = (Joueur) arg;

        if (joueur.equals(model.getJoueurActuel())) {
            afficherResultat(joueur);
            mettreAJourToursRestants();
            afficherScores();
            afficherGagnant();
        }
    }

    private void afficherResultat(Joueur joueur) {
        if (joueur.equals(model.getJoueur1())) {
            resultatLabelJoueur1.setText("Résultats de " + joueur.getNom() +
                    ": Dé 1 : " + joueur.getDe1() +
                    " | Dé 2 : " + joueur.getDe2() +
                    " | Total : " + joueur.getTotal());
        } else if (joueur.equals(model.getJoueur2())) {
            resultatLabelJoueur2.setText("Résultats de " + joueur.getNom() +
                    ": Dé 1 : " + joueur.getDe1() +
                    " | Dé 2 : " + joueur.getDe2() +
                    " | Total : " + joueur.getTotal());
        }
    }

    private void mettreAJourToursRestants() {
        toursRestantsLabel.setText("Tours restants : " + (model.getNombreTours() - model.getToursJoues()));
    }

    private void afficherScores() {
        scoreLabelJoueur1.setText("Score Joueur 1: " + model.getScoreJoueur1());
        scoreLabelJoueur2.setText("Score Joueur 2: " + model.getScoreJoueur2());
    }

    private void afficherGagnant() {
        gagnantLabel.setText("Gagnant: " + model.getGagnant());
    }
}