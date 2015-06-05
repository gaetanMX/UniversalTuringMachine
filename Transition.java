
public class Transition {
	final static int alpha = 36; // nombre de symboles

	final static int cCOPIE		= -123456789;

	final static int mGAUCHE = 0;
	final static int mSURPLACE = 1;
	final static int mDROITE = 2;
	
	private Definition[] definitions;
	private String comment = null;
	
	
	Transition() {
		definitions = new Definition[alpha];
		for (int s = 0; s < 36; s++) {
			definitions[s] = new Definition(this, s == alpha-1? -1:s, mSURPLACE);
		}
	}
	Transition(Transition transition, int nv_symbole, int mouvement) {
		definitions = new Definition[alpha];
		for (int s = 0; s < 36; s++) {
			definitions[s] = new Definition(transition == null? this : transition, nv_symbole == cCOPIE ? (s == alpha-1? -1:s) : nv_symbole , mouvement);
		}
	}
	
	void definition(int symbole, Transition transition, int nv_symbole, int mouvement) {
		definitions[symbole].modifier(transition == null? this : transition, nv_symbole == cCOPIE ? (symbole == alpha-1? -1:symbole) : nv_symbole, mouvement);
	}
	
	Definition getDefinition(int s) {
		return definitions[s];
	}
	
	void setComment(String comment) {
		this.comment = comment;
	}
	String getComment() {
		return this.comment;
	}
}
