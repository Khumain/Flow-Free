package gloo.flow;

import javax.swing.SwingUtilities;

import gloo.flow.control.Controleur;
import gloo.flow.hmi.Fenetre;

/**
 * Programme de test du controleur du jeu FlowFree
 * 
 * @author Laura Marina Campodonico Ruggiero, Afnan Khan
 * @version 1.0
 *
 */
public class TestControleur implements Runnable {

	public static void main( String[] args ) {
        SwingUtilities.invokeLater( new TestControleur() );
	}

    @Override
    public void run() {
        new Fenetre( new Controleur() );
    }
}
