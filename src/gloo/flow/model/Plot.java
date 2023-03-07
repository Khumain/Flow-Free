package gloo.flow.model;

/**
 * Plot du modèle pour le jeu FlowFree
 * 
 * @author Laura Marina Campodonico Ruggiero, Afnan Khan
 * @version 1.0
 *
 */
public class Plot {
	private Case casePlot;
	private Couleur couleur;
	
	public Plot(Case casePlot, Couleur couleur) {
		super();
		this.casePlot = casePlot;
		this.couleur = couleur;
	}

	public Case getCasePlot() {
		return casePlot;
	}

	public void setCasePlot(Case casePlot) {
		this.casePlot = casePlot;
	}

	public Couleur getCouleur() {
		return couleur;
	}
	
	public void setCouleur(Couleur couleur) {
		this.couleur = couleur;
	}

	public Tuyau nouveauTuyau () {
		return this.couleur.nouveauTuyau(this.casePlot);
	}

}
