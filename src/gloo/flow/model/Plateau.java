package gloo.flow.model;

/**
 * Plateau du modèle pour le jeu FlowFree
 * 
 * @author Laura Marina Campodonico Ruggiero, Afnan Khan
 * @version 1.0
 *
 */
import gloo.flow.control.*;

public class Plateau {
	
	private int nbLignes;
	private int nbColonnes;
	private Controleur controleur;
	private static Case[][] cases;
	
    /**
     * Constructeur du plateau qui initialise les cases
     * et les couleurs (avec les tuyaux correspondants)
     * 
     * @param lignes : nombre de lignes du plateau
     * @param colonnes : nombre de colonnes du plateau
     * @param cont : controleur du plateau
     */
	public Plateau( int lignes, int colonnes, Controleur cont ) {
		this.nbLignes = lignes;
		this.nbColonnes = colonnes;
		this.controleur = cont;
		this.cases = new Case[lignes][colonnes];
		for( int i = 0; i < nbLignes; i++ ) {
			for( int j = 0; j < nbColonnes; j++ ) {
				this.cases[i][j] = new Case(i, j, null);
			}
		}
		for( Couleur couleur : Couleur.class.getEnumConstants() ) {
            int[] posDepart = this.controleur.getPositionPlotDepartTuyau( couleur );
            this.cases[posDepart[0]][posDepart[1]].setPlot( new Plot( this.cases[posDepart[0]][posDepart[1]], couleur ) );
            int[] posArrivee = this.controleur.getPositionSecondPlot( couleur );
            this.cases[posArrivee[0]][posArrivee[1]].setPlot( new Plot( this.cases[posArrivee[0]][posArrivee[1]], couleur ) );
		}
	}
	
    /**
     * Méthode retournant le plot correspondant à la case voulue
     */
	public Plot getPlot( int ligne, int colonne ) {
		return this.cases[ligne][colonne].getPlot();
	}
	
    /**
     * Méthode donnant la case voisine d'une case donnée suivant une direction.
     * 
     * @param caseInitiale : case de départ
     * @param direction : direction choisie par le joueur
     * @return renvoie la case voisine correspondante
     */
	public static Case getMaCaseVoisine( Case caseInitiale, Direction direction ) {
		int ligneInitiale = caseInitiale.getLigne();
		int colonneInitiale = caseInitiale.getColonne();
		return switch(direction) {
		case HAUT -> cases[ligneInitiale-1][colonneInitiale];
		case BAS -> cases[ligneInitiale+1][colonneInitiale];
		case DROITE -> cases[ligneInitiale][colonneInitiale+1];
		case GAUCHE -> cases[ligneInitiale][colonneInitiale-1];
		};
	}

    /**
     * Méthode vérifiant si le plateau est complet, c'est-à-dire
     * si toutes les cases sont remplies et les tuyaux reliés à leurs 
     * plots correspondants.
     */
	public boolean plateauComplet() {
		for( int i = 0; i < nbLignes; i++ ) {
			for( int j = 0; j < nbColonnes; j++ ) {
				if( !this.cases[i][j].valideFinJeu() ) {
					return false;
				}
			}
		}
		for( Couleur couleur : Couleur.class.getEnumConstants() ) {
			int[] secondPlot = this.controleur.getPositionSecondPlot(couleur);
			Case derniereCaseTuyau = couleur.getTuyau().getCasesTuyau()[couleur.getTuyau().getCasesTuyau().length-1];
			if( secondPlot[0]!=derniereCaseTuyau.getLigne() || secondPlot[1]!=derniereCaseTuyau.getColonne()) {
	        	return false;
	        }
		}
		return true;
	}
	
}
