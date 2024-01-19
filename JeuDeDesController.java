
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;                        //Ould Elmelah Imad Eddine . L3/isil/ G2/...
import java.util.Observer;
import java.util.Random;
class JeuDeDesController {
    private JeuDeDesModel model;
    private JeuDeDesView view;

    public JeuDeDesController(JeuDeDesModel model, JeuDeDesView view) {
        this.model = model;
        this.view = view;
    }
}