package gloo.flow.model;

/**
 * Case du modèle pour le jeu FlowFree
 * 
 * @author Laura Marina Campodonico Ruggiero, Afnan Khan
 * @version 1.0
 *
 */
public class Case {
	private int ligne;
	private int colonne;
	private Plot plot;
	private Tuyau tuyau;
	
	public Case(int ligne, int colonne, Plot plot) {
		super();
		this.ligne = ligne;
		this.colonne = colonne;
		this.plot = plot;
	}
	
	public String toString() {
		String resultat = "Case de la ligne " + ligne + ", colonne " + colonne;
		return resultat;
	}

	public int getLigne() {
		return ligne;
	}

	public int getColonne() {
		return colonne;
	}

	public Plot getPlot() {
		return plot;
	}

	public Tuyau getTuyau() {
		return tuyau;
	}

	public void setPlot(Plot plot) {
		this.plot = plot;
	}
	
	public void setTuyau(Tuyau tuyau) {
		this.tuyau = tuyau;
	}
	
    /**
     * Méthode renvoyant la case voisine en fonction de la direction
     */
	public Case getCaseVoisine( Direction direction ) {
		Case caseVoisine = Plateau.getMaCaseVoisine(this, direction);
		return caseVoisine;
	}
	
    /**
     * Méthode vérifiant si un tuyau peut se déplacer
     * vers la case correspondante.
     * 
     * @param tuyau : tuyau qui souhaite ajouter la case en question
     * @param direction : direction choisie par le joueur
     */
	public void accepteTuyau( Tuyau tuyau, Direction direction ) {
		if( this.plot == null && this.tuyau == null ) {
			tuyau.ajouteCase(this);
			this.setTuyau(tuyau);
			tuyau.getCouleur().addDirections(direction);
		}
		else if(this.plot != null) {
			Couleur couleurPlot = this.plot.getCouleur();
			Couleur couleurTuyau = tuyau.getCouleur();
			if( couleurPlot == couleurTuyau ) {
				if( !tuyau.estDansTuyau(this) ) {
					tuyau.ajouteCase(this);
					tuyau.getCouleur().addDirections(direction);
				}
				else {
					System.out.println( "Impossible : retour au point initial" );
				}
			}
		}
	}
	
    /**
     * Méthode vérifiant si la case est vide.
     */
	public boolean valideFinJeu() {
		if( this.getPlot() == null && this.getTuyau() == null ) {
			return false;
		}
		return true;
	}
}
