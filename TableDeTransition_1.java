import java.util.LinkedList;
import java.util.ListIterator;


public class TableDeTransition {
	
	private LinkedList<Transition> transitions;
		
	private final Transition transition_stop;
	
	TableDeTransition() {
		transition_stop = new Transition(null, Transition.cCOPIE, Transition.mSURPLACE);
		transition_stop.setComment("etat stop");
		transitions = new LinkedList<Transition>();		
		add(transition_stop);
	}
	
	public Transition transitionSTOP() {
		return transition_stop;
	}
	
	public Transition newTransition() {
		Transition transition = new Transition();
		add(transition);
		return transition;
	}
	public Transition newTransition(Transition transition, int nv_symbole, int mouvement) {
		Transition new_transition = new Transition(transition, nv_symbole, mouvement);
		add(new_transition);

		return new_transition;
	}
	
	public void add(Transition transition) {
		transitions.add(transition);
	}
	
	public String toString() {
		String result = "new int[][][]{";
		ListIterator<Transition> iter = transitions.listIterator(0);
		/*for (int e=0; iter.hasNext(); e++) iter.next().setEtat(e);*/
		for (int e=0; iter.hasNext(); e++) {
			Transition t = iter.next();
			//if (t.getComment() == null) result += "/* Etat "+ e +" */\n";
			//else result += "/* Etat "+ e +": "+t.getComment()+" */\n";
			if (e != 0) result += ",";
			result += "{";
			for (int s=0; s < Definition.alpha; s++) {
				Definition d = t.getDefinition(s);
				//result += "t["+ e +"]["+ s +"] = new int[]{"+ transitions.indexOf(d.getTransition()) +","+ d.getNvSymbole() +","+ d.getMouvement() +"};\n";
				if (s != 0) result += ",\n";
				result += "{"+transitions.indexOf(d.getTransition()) +","+ d.getNvSymbole() +","+ d.getMouvement()+"}";
			}
			result += "}\n";
		}
		return result+"}";
	}
	
	public int[][][] toTAB() {
		int[][][] tab = new int[131][36][3];
		ListIterator<Transition> iter = transitions.listIterator(0);
		for (int e=0; iter.hasNext(); e++) {
			Transition t = iter.next();
			for (int s=0; s < Definition.alpha; s++) {
				Definition d = t.getDefinition(s);
				tab[e][s] = new int[] {transitions.indexOf(d.getTransition()), d.getNvSymbole(), d.getMouvement()};
			}
		}
		return tab;
	}
}