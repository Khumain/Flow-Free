package gloo.flow.control;

import gloo.flow.model.*;

/**
 * Contrôleur pour l'IHM du jeu FlowFree 
 * 
 * @author Laura Marina Campodonico Ruggiero, Afnan Khan
 * @version 1.0
 *
 */
public class Controleur implements IControleur {
	private int niveau = (int) (Math.random()*2);
    private Plateau plateau = new Plateau( this.getNbLignes(), this.getNbColonnes(), this);
    private Plot plotCourant;
    private Tuyau tuyauCourant;
	
    @Override
    public boolean selectionCase( int ligne, int colonne ) {
    	if( niveau == 0 ) {
            if( (( ligne == 0 ) && ( colonne % 2 == 0 )) || (( ligne == 1 ) && (( colonne == 2 ) || ( colonne == 4 ))) ) {
            	plotCourant = plateau.getPlot(ligne, colonne);
            	tuyauCourant = plotCourant.nouveauTuyau();
            	return true;
            }
    	}
    	else {
            if( (( ligne == 0 ) && ( colonne == 1 || colonne == 3 || colonne == 4 )) || (( ligne == 3 ) && ( colonne == 2 || colonne == 3 )) ) {
            	plotCourant = plateau.getPlot(ligne, colonne);
            	tuyauCourant = plotCourant.nouveauTuyau();
            	return true;
            }
    	}
        return false;
    }

    @Override
    public boolean action( Direction direction ) {
        if( tuyauCourant != null) {
        	try {
    	        tuyauCourant.modifier(direction);
    	        int[] secondPlot = this.getPositionSecondPlot(tuyauCourant.getCouleur());
    	        Case derniereCaseTuyau = tuyauCourant.getCasesTuyau()[tuyauCourant.getCasesTuyau().length-1];
    	        if( secondPlot[0]==derniereCaseTuyau.getLigne() && secondPlot[1]==derniereCaseTuyau.getColonne()) {
    	        	tuyauCourant = null;
    	        }
    	        return plateau.plateauComplet();
        	}
        	catch( ArrayIndexOutOfBoundsException e ) {
        		System.out.println("Vous ne pouvez pas dépasser le cadre du jeu !");
        	}

        }
        return false;
    }

    @Override
    public int getNbLignes() {
        return 5;
    }

    @Override
    public int getNbColonnes() {
        return 5;
    }

    @Override
    public int[] getPositionPlotDepartTuyau( Couleur couleur ) {
    	if( niveau == 0 ) {
            return switch (couleur) {
                case ROUGE -> new int[] { 0, 0 };
                case ORANGE -> new int[] { 1, 4 };
                case BLEU -> new int[] { 1, 2 };
                case VERT -> new int[] { 0, 2 };
                case JAUNE -> new int[] { 0, 4 };
            };
    	}
    	else {
    		return switch (couleur) {
	            case ROUGE -> new int[] { 0, 1 };
	            case ORANGE -> new int[] { 0, 3 };
	            case BLEU -> new int[] { 0, 4 };
	            case VERT -> new int[] { 3, 2 };
	            case JAUNE -> new int[] { 3, 3 };
	        };
    	}
    }

    @Override
    public int[] getPositionSecondPlot( Couleur couleur ) {
    	if( niveau == 0 ) {
            return switch( couleur ) {
	            case ROUGE  -> new int[] { 4, 1 };
	            case ORANGE -> new int[] { 4, 3 };
	            case BLEU   -> new int[] { 4, 2 };
	            case VERT   -> new int[] { 3, 1 };
	            case JAUNE  -> new int[] { 3, 3 };
	        };
    	}
    	else {
	        return switch( couleur ) {
	            case ROUGE  -> new int[] { 3, 0 };
	            case ORANGE -> new int[] { 3, 1 };
	            case BLEU   -> new int[] { 1, 3 };
	            case VERT   -> new int[] { 4, 4 };
	            case JAUNE  -> new int[] { 4, 0 };
	        };
    	}
    }

    @Override
    public Direction[] getDirections(Couleur couleur) {
        return couleur.getDirections();
    }
}
