package gloo.flow.model;

/**
 * Tuyau du mod�le pour le jeu FlowFree
 * 
 * @author Laura Marina Campodonico Ruggiero, Afnan Khan
 * @version 1.0
 *
 */
public class Tuyau {
	private Case[] casesTuyau;
	private Couleur couleur;
	
	public Tuyau(Case[] cases, Couleur couleur) {
		this.casesTuyau = cases;
		this.couleur = couleur;
	}
	
	public Case[] getCasesTuyau() {
		return casesTuyau;
	}
	public Couleur getCouleur() {
		return couleur;
	}
	
	public void setCasesTuyau(Case[] casesTuyau) {
		this.casesTuyau = casesTuyau;
	}
	public void setCouleur(Couleur couleur) {
		this.couleur = couleur;
	}
	
    /**
     * M�thode v�rifiant si le tuyau actuel peut aller dans 
     * la direction choisie.
     * 
     * @param direction : direction souhait�e
     */
	public void modifier( Direction direction ) {
		Case[] tuyau = this.casesTuyau;
		Case caseVoisine = tuyau[tuyau.length-1].getCaseVoisine(direction);
		caseVoisine.accepteTuyau(this, direction);
	}
	
    /**
     * M�thode ajoutant une case au tuyau
     * 
     * @param case : case de d�part (plot initial)
     * @return un tuyau, qui sera utilis� comme tuyau courant par le controleur
     */
	public void ajouteCase(Case casePlot) {
		int n = this.casesTuyau.length;
		Case[] resultTuyau = new Case[n+1];
		for( int i = 0; i < n; ++i ) {
			resultTuyau[i] = this.casesTuyau[i];
		}
		resultTuyau[n] = casePlot;
		this.casesTuyau = resultTuyau;
	}
	
    /**
     * M�thode v�rifiant si la case donn�e en argument est d�j�
     * dans le tuyau courant.
     * 
     * @param case : case � analyser
     * @return vrai si oui, non sinon
     */
	public boolean estDansTuyau( Case c ) {
		if( this.casesTuyau[0] == c ) {
			return true;
		}
		return false;
	}
	
}
