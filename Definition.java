
public class Definition {
	final static int alpha = 36; // nombre de symboles

	public Transition transition;
	public int nv_symbole;
	public int mouvement;
	
	Definition(Transition transition, int nv_symbole, int mouvement) {
		modifier(transition, nv_symbole, mouvement);
	}
	
	void modifier(Transition transition, int nv_symbole, int mouvement) {
		setTransition(transition);
		setNvSymbole(nv_symbole);
		setMouvement(mouvement);
	}
	void setTransition(Transition transition) {
		this.transition = transition;
	}
	void setNvSymbole(int nv_symbole) {
		if (nv_symbole >= alpha-1) { throw new ArrayIndexOutOfBoundsException(); }
		this.nv_symbole = nv_symbole;
	}
	void setMouvement(int mouvement) {
		if (mouvement < 0 || mouvement > 2) { throw new ArrayIndexOutOfBoundsException(); }
		this.mouvement = mouvement;
	}

	Transition getTransition() {
		return this.transition;
	}
	int getNvSymbole() {
		return this.nv_symbole;
	}
	int getMouvement() {
		return this.mouvement;
	}
}