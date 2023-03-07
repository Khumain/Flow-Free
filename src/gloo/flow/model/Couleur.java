package gloo.flow.model;

/**
 * Enumérations des couleurs pour le jeu FlowFree
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
     * Méthode ajoutant une direction dans la liste des directions
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
     * Méthode réinitialisant les directions d'une couleur pour l'IHM
     */
	public void resetDirections() {
		this.directions = new Direction[] {};
	}
	
    /**
     * Méthode initialisant (ou supprimant) le tuyau des couleurs énumérées
     * 
     * @param case : case de départ (plot initial)
     * @return un tuyau, qui sera utilisé comme tuyau courant par le controleur
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
	 * Il est IMPORTANT de noter qu'une Ã©numÃ©ration en Java est 
	 * aussi une classe, et peut donc avoir aussi des attributs, 
	 * des mÃ©thodes... Il est donc possible de respecter le
	 * modÃ¨le mÃ©tier du jeu avec cette Ã©numÃ©ration.
	 * La seule contrainte est qu'il n'est pas possible de crÃ©er
	 * d'autres instances que celles nommÃ©es ci-dessus, ce qui
	 * implique en particulier qu'un Ã©ventuel constructeur doit
	 * Ãªtre privÃ©.
	 */
	
}
