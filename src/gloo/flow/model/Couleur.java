package gloo.flow.model;

/**
 * Enum�rations des couleurs pour le jeu FlowFree
 * 
 * @author Laura Marina Campodonico Ruggiero, Afnan Khan
 * @version 1.0
 *
 */
public enum Couleur {
	
	ROUGE( new Direction[] {}, new Tuyau(new Case[0], null) ),
	ORANGE( new Direction[] {}, new Tuyau(new Case[0], null) ),
	BLEU( new Direction[] {}, new Tuyau(new Case[0], null) ),
	VERT( new Direction[] {}, new Tuyau(new Case[0], null) ),
	JAUNE( new Direction[] {}, new Tuyau(new Case[0], null) );
	
	private Direction[] directions;
	private Tuyau tuyau;

	private Couleur(Direction[] directions, Tuyau tuyau) {
		this.directions = directions;
		this.tuyau = tuyau;
	}
	
	public Direction[] getDirections() {
		return this.directions;
	} 

	public Tuyau getTuyau() {
		return this.tuyau;
	}
	
    /**
     * M�thode ajoutant une direction dans la liste des directions
     * d'une couleur pour l'IHM
     */
	public void addDirections(Direction direction) {
		int n = this.directions.length;
		Direction[] resultDirections = new Direction[n+1];
		for( int i = 0; i < n; ++i ) {
			resultDirections[i] = this.directions[i];
		}
		resultDirections[n] = direction;
		this.directions = resultDirections;
	}
	
    /**
     * M�thode r�initialisant les directions d'une couleur pour l'IHM
     */
	public void resetDirections() {
		this.directions = new Direction[] {};
	}
	
    /**
     * M�thode initialisant (ou supprimant) le tuyau des couleurs �num�r�es
     * 
     * @param case : case de d�part (plot initial)
     * @return un tuyau, qui sera utilis� comme tuyau courant par le controleur
     */
	public Tuyau nouveauTuyau(Case casePlot) {
		Couleur couleurTuyau = casePlot.getPlot().getCouleur();
		couleurTuyau.resetDirections();
		Tuyau tuyau = couleurTuyau.getTuyau();
		Case[] casesTuyau = tuyau.getCasesTuyau();
		int n = casesTuyau.length;
		if( n >= 2 ) {
			for( int c = 1; c < n; c++ ) {
				casesTuyau[c].setTuyau(null);
			}
		}
		tuyau.setCasesTuyau(new Case[] {casePlot});
		tuyau.setCouleur(couleurTuyau);
		return tuyau;
	}
	
	
	/*
	 * Il est IMPORTANT de noter qu'une énumération en Java est 
	 * aussi une classe, et peut donc avoir aussi des attributs, 
	 * des méthodes... Il est donc possible de respecter le
	 * modèle métier du jeu avec cette énumération.
	 * La seule contrainte est qu'il n'est pas possible de créer
	 * d'autres instances que celles nommées ci-dessus, ce qui
	 * implique en particulier qu'un éventuel constructeur doit
	 * être privé.
	 */
	
}
