import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;


public class TuringMachineUniverselle extends TuringMachine {

	private int inithead;
	private final int s_bits;
	private final int e_bits;

	private int[] informationINIT;

	final static int B			= -1; // symbole blanc
	final static int cB			= 35; // index blanc
	final static int c0 		= 0;
	final static int c1 		= 1;
	final static int c2 		= 2;
	final static int cZ 		= 3;
	final static int cU 		= 4;
	final static int cEUtil 	= 5;
	final static int cENUtil 	= 6;
	final static int cSUtil 	= 7;
	final static int cSNUtil	= 8;
	final static int cIs		= 9;
	final static int cIe		= 10;
	final static int cFTs		= 11;
	final static int cFTe		= 12;
	final static int cECs		= 13;
	final static int cECe		= 14;
	final static int cESs		= 15;
	final static int cESe		= 16;
	final static int cTs		= 17;
	final static int cTe		= 18;
	final static int cTEs		= 19;
	final static int cTEe		= 20;
	final static int cTEDs		= 21;
	final static int cTEDe		= 22;
	final static int cTDs		= 23;
	final static int cTDe		= 24;
	final static int cTSs		= 25;
	final static int cTSe		= 26;
	final static int cTNEs		= 27;
	final static int cTNEe		= 28;
	final static int cTNSs		= 29;
	final static int cTNSe		= 30;
	final static int cTMs		= 31;
	final static int cTMe		= 32;
	final static int cSs		= 33;
	final static int cSe		= 34;
 	
	public void exec_step2step(int s)
	/* pre : s appartient ￜ S
	 * post : current_state peut avoir ￜtￜ changￜ.
	 * Un pas de la machine de turing a ￜtￜ exￜcutￜ ￜ partir de l'ￜtat s :
	 * Le ruban et la tￜte de lecture peuvent avoir ￜtￜ modifiￜs.
	 */
	{
		if(accept[s]==false)
		{
			int[] rub = this.ruban.getArray();
			int head = this.ruban.getHead();

			/*if (current_state == 1) { //probleme 1

				if (rub[head] != cECs) System.out.println("PRE pb1 non respecté");
				head++;
				if (rub[head] == c1) {
					while (rub[head] != cECe) head++;
					head++;
					rub[head] = c1;
					current_state = 0;
				} else { // == c0
					while (rub[head] != cECe) head++;
					head++;
					current_state = 2;
				}

				this.ruban = new RubanTuring(rub, head, this.m, this.alpha);
			}*/
			if (current_state == 2) { //probleme 2
				head--;
                if(rub[head] != cECe) System.out.println("PRE pb2 non respectee");
                // SP1
				int[] etat = new int[e_bits];
				head--;
				
				for (int i=e_bits-1; rub[head] != cECs; i--, head--) {
					etat[i] = rub[head];
				}
				while (rub[head] != cIs) {
					if (rub[head] == cEUtil) rub[head] = cENUtil;
					head--;
				}
				
				while (rub[head] != cEUtil) {
					head++;
					while (rub[head] != cENUtil) head++;
					boolean b = true;
					for (int i=head+2, j=0; rub[i] != cTEe; i++, j++) {
						if (rub[i] != etat[j]) {
							b = false;
							break;
						}
					}
					if (b == true) rub[head] = cEUtil;
				}

				// SP2
				int head_cEUtil = head;
				int[] symbole = new int[s_bits];
				
				while (rub[head] != cIe) head++;
				head--;
				
				
				if (rub[head] == c1) { // IO DROITE
					while(rub[head] != c1 || rub[head+1] != cSs) head++;
					head+=2;

					for (int i=0; rub[head] != cSe; i++, head++) {
						symbole[i] = rub[head];
					}

				}
				else { // IO GAUCHE
					while(rub[head] != c1 || rub[head+1] != cSs) head--;
					head+=2;
					
					for (int i=0; rub[head] != cSe; i++, head++) {
						symbole[i] = rub[head];
					}
				}
				
				head = head_cEUtil;

				while (rub[head] != cSUtil) {
					head++;
					while (rub[head] != cSNUtil) head++;
					boolean b = true;
					for (int i=head+2, j=0; rub[i] != cTSe; i++, j++) {
						if (rub[i] != symbole[j]) {
							b = false;
							break;
						}
					}
					if (b == true) rub[head] = cSUtil;
				}

				while (rub[head] != cFTs) head--;
				
				current_state = 3;
				this.ruban = new RubanTuring(rub, head, this.m, this.alpha);

			}
		/*	if (current_state == 3) { //probleme 3
				int[] tab = new int[s_bits]; 

				while(rub[head] != cIe) head++;
				while(rub[head] != cSUtil) head--;                             
				while(rub[head] != cTNSs)head++;
				head++;

                for(int i=0;i<tab.length;i++){
                    tab[i]=rub[head];
                    head++;
                }          

				while(rub[head] != cIe) head++;
				//IO valant 1
				if(rub[--head] == c1){                              
					while(rub[head] != c1 || rub[head+1] != cSs) head++;
					head+=2;
                    for(int i=0;i<tab.length;i++){
                        rub[head]=tab[i];
                        head++;
                    }
					while(rub[head] != cTNSe) head--;
				}else{                                                                
					while(rub[head] != c1 || rub[head+1] != cSs) head--;
					head+=2;
                    for(int i=0;i<tab.length;i++){
                        rub[head]=tab[i];
                        head++;
                    }
					while(rub[head] != cTNSe) head++;
				}                             
				current_state = 4;
				this.ruban = new RubanTuring(rub, head, this.m, this.alpha);
			}*/

			else if (current_state == 4) { //probleme 4
				// deplacer la tête de lecture de la MTP

				// aller sur IO valant 1
				while (rub[head] != cIe) head++;
				head--;
				if (rub[head] == c1) { // IO droite
					// deplacer la tête de lecture à droite
					// rechercher le mouvement à effectuer
					while (rub[head] != cSUtil) head--;
					while (rub[head] != cTMs) head++;
					head++; //mouvement à effectuer

					if (rub[head] == c0) {
						//mouvement à gauche
						// aller à la tête de lecture
						while (rub[head] != c1 || rub[head+1] != cSs) head++;
						// deplacer la tête à droite 
						rub[head] = c0;
						
						head--;
						
						while (rub[head] != cIe && (rub[head] != c0 || rub[head+1] != cSs)) head--;

						if (rub[head] == cIe) { // aller sur le rubangauche
							head--;
							rub[head] = c0; // IO droite = 0
							while (rub[head] != cIs) head--;
							head++;
							rub[head] = c1; // IO gauche = 1
							while (rub[head] != c0 || rub[head+1] != cSs) head--;
							rub[head] = c1;
							while(rub[head] != cFTe) head++;
						}
						else rub[head] = c1;
					}
					else if (rub[head] == c1) {
						//mouvement sur place => ne rien faire => aller à FTe
						while(rub[head] != cFTe) head++;
					}
					else if (rub[head] == c2) {
						//movement à droite
						// aller à la tête de lecture
						while (rub[head] != c1 || rub[head+1] != cSs) head++;
						// deplacer la tête à droite
						rub[head] = c0;
						head++;

						while (head < rub.length && (rub[head] != c0 || rub[head+1] != cSs)) head++;

						if (head >= rub.length) { // creer une nouvelle case
							head = rub.length-1;
							int[] nvCase = generate_case(true, B);
							int[] nvRub = new int[rub.length + nvCase.length];
							System.arraycopy(rub, 0, nvRub, 0, rub.length);
							System.arraycopy(nvCase, 0, nvRub, rub.length, nvCase.length);
							rub = nvRub;
						}
						else rub[head] = c1;
					}
					else {
						System.out.println("erreur mouvement inconnu!");
					}
					// aller sur FTe
					while(rub[head] != cFTe) head--;

				} else {
					while (rub[head] != cIs) head--;
					head++; // IO gauche
					// deplacer la tête de lecture à gauche 
					// rechercher le mouvement à effectuer 
					while (rub[head] != cSUtil) head++;
					while (rub[head] != cTMs) head++;
					head++; //mouvement à effectuer

					if (rub[head] == c0) {
						//mouvement à gauche
						// aller à la tête de lecture
						while (rub[head] != c1 || rub[head+1] != cSs) head--;
						// deplacer la tête à gauche 
						rub[head] = c0;
						
						head--;
						
						while (head < 0 && (rub[head] != c0 || rub[head+1] != cSs)) head--;

						if (head < 0) { // creer une nouvelle case
							int[] nvCase = generate_case(true, B);
							int[] nvRub = new int[rub.length + nvCase.length];
							System.arraycopy(rub, 0, nvRub, nvCase.length, rub.length);
							System.arraycopy(nvCase, 0, nvRub, 0, nvCase.length);
							rub = nvRub;
							head = 0;
						}
						else rub[head] = c1;

					}
					else if (rub[head] == c1) {
						//mouvement sur place => ne rien faire => aller à FTe
						while(rub[head] != cFTe) head++;
					}
					else if (rub[head] == c2) {
						//mouvement à droite
						// aller à la tête de lecture
						while (rub[head] != c1 || rub[head+1] != cSs) head--;
						// deplacer la tête à droite
						rub[head] = c0;

						head++;
						
						while (rub[head] != cIs && (rub[head] != c0 || rub[head+1] != cSs)) head++;

						if (rub[head] == cIs) { // aller sur le rubandroite
							head++;
							rub[head] = c0; // IO gauche = 0
							while (rub[head] != cIe) head++;
							head--;
							rub[head] = c1; // IO droite = 1

							
							while (rub[head] != c0 || rub[head+1] != cSs) head++;
							rub[head] = c1;
							while(rub[head] != cFTe) head--;
						}
						else rub[head] = c1;
					}
					else {
						System.out.println("erreur mouvement inconnu!");
					}
					// aller sur FTe
					while(rub[head] != cFTe) head++;
				}

				this.ruban = new RubanTuring(rub, head, this.m, this.alpha);	 
				current_state =  5;
			}

			/*else if (current_state == 5) { //probleme 5
				int[] tab = new int[e_bits];                             
				while(rub[head] != cSUtil) head--;                             
				while(rub[head] != cTNEs )head++;
				head++;
				//copie de l'etat dans tab(tmp)
				
                for(int i=0;i<tab.length;i++){
                    tab[i]=rub[head];
                    head++;
                }
				//copie de l'etat dans ECs
				while(rub[head] != cECs) head++;
				head++;
                for(int i=0;i<tab.length;i++){
                    rub[head]=tab[i];
                    head++;
                }

				//post_condition pb5 / pre_condition pb6
				while(rub[head] != cIe) head++;


				current_state = 6;
				this.ruban = new RubanTuring(rub, head, this.m, this.alpha);         
				current_state = 6;
			}*/
			/*else if (current_state == 6) { //probleme 6
				//2.6.1 Nettoyer
				if (rub[head] != cIe) System.out.println("PRE pb5 non respectÃ©");
				while (rub[head] != cIs) {
					switch(rub[head]){
					case cU:
						rub[head] = c1;
						break;
					case cZ:
						rub[head] = c0;
						break;
					case cENUtil:
						rub[head] = cEUtil;
						break;
					case cSUtil:
						rub[head] = cSNUtil;
					default:
					}                                    

					head--;
				}

				//2.6.2 Deplacer la tete de lecture
				while (rub[head] != cECs) head++;
				current_state = 1;
				this.ruban = new RubanTuring(rub, head, this.m, this.alpha);
				current_state = 1;
			}*/
			else {
				int x = ruban.getVal();
				if(x==-1) x=alpha-1;
				current_state = trans[s][x][0];
				int newVal = trans[s][x][1];
				int direction = trans[s][x][2];
				ruban.operation(newVal,direction);
			}
		}
						/*Scanner spraz = new Scanner(System.in);
				spraz.nextLine();*/

		//print_ruban();
		//checkInv();
	}

	public static int[][][] getTransTable(String fichier){
	    int[][][] result;
	    try {
	        BufferedReader br = new BufferedReader(new FileReader(fichier));
	        String nbEtat = br.readLine();
	        if (nbEtat == null) return null;
	        String nbSymb = br.readLine();
	        if (nbSymb == null) return null;
	        
	        int nSymb = Integer.parseInt(nbSymb);
	        int nEtat = Integer.parseInt(nbEtat);
	        result = new int[nEtat][nSymb][3];
	        String line = null;
	        for (int e=0; e < nEtat; e++) {
	        	for (int s=0; s < nSymb;) {
	        		line = br.readLine();
	        		if (line == null) break;
	        		int length = line.length();
		        	if (line.charAt(0) != '/' && length > 0) {
		        		int d1 = line.indexOf('#'),
		        			d2 = line.lastIndexOf('#');
		      
		        		result[e][s][0] = Integer.parseInt(line.substring(0, d1));
		        		result[e][s][1] = Integer.parseInt(line.substring(d1+1, d2));
		        		result[e][s][2] = Integer.parseInt(line.substring(d2+1, length));
		        		s++;
		        	}
	        	}
	        	if (line == null) break;
	        }
	    } catch (Exception e){
	        throw new IllegalStateException("Impossible de charger le fichier");
	    }
	    
	    return result;
	}
	
	public TuringMachineUniverselle(int mMTP, int alphaMTP, int s0MTP, int[][][] transMTP, boolean[] acceptMTP) {
		//super(m, alpha, s0, trans, accept);
		super(36, 36, 1, null, TransitionMTU.getSTOP());
		
		this.trans = getTransTable("tabletransition.txt");
		// generate_information
		s_bits = Integer.toBinaryString(alphaMTP-1).length() + 1;
		e_bits = Integer.toBinaryString(acceptMTP.length).length() + 1;
		LinkedList<Integer> information = new LinkedList<Integer>();

		int[] symb_bin, etat_bin;

		int length;

		information.add(cFTs);
		for (int e=0; e < acceptMTP.length; e++) {
			information.add(cTs);
			information.add(cEUtil);
			information.add(cTEs);

			etat_bin = generate_etat(acceptMTP[e], e);
			/* ajouter l'état dans information */
			for (int i=0; i < e_bits; i++) information.add(etat_bin[i]);

			information.add(cTEe);
			information.add(cTEDs);
			for (int s=0; s < alphaMTP; s++) {
				information.add(cTDs);
				information.add(cSNUtil);				
				information.add(cTSs);

				symb_bin = generate_symbole(s == alphaMTP-1? -1 : s);
				/* ajouter le symbole dans information */
				for (int i=0; i < s_bits; i++) information.add(symb_bin[i]);

				information.add(cTSe);
				information.add(cTNEs);

				etat_bin = generate_etat(acceptMTP[transMTP[e][s][0]], transMTP[e][s][0]);
				/* ajouter l'état dans information */
				for (int i=0; i < e_bits; i++) information.add(etat_bin[i]);


				information.add(cTNEe);
				information.add(cTNSs);
				symb_bin = generate_symbole(transMTP[e][s][1]);
				/* ajouter le symbole dans information */
				for (int i=0; i < s_bits; i++) information.add(symb_bin[i]);


				information.add(cTNSe);
				information.add(cTMs);

				switch (transMTP[e][s][2]) {
				case 0: information.add(c0); break;
				case 1: information.add(c1); break;
				default: information.add(c2); break;
				}

				information.add(cTMe);
				information.add(cTDe);
			}
			information.add(cTEDe);
			information.add(cTe);
		}
		information.add(cFTe);


		information.add(cECs);
		etat_bin = generate_etat(acceptMTP[s0MTP], s0MTP);
		/* ajouter l'état dans information */
		for (int i=0; i < e_bits; i++) information.add(etat_bin[i]);
		information.add(cECe);

		information.addFirst(c0); // IO de gauche
		information.addLast(c0); // INT
		information.addLast(c1); // IO de droite
		information.addFirst(cIs);
		information.addLast(cIe);

		informationINIT = new int[information.size()];
		ListIterator<Integer> iter = information.listIterator(0);
		for (int k = 0; iter.hasNext(); k++) {
			informationINIT[k] = iter.next();
			if (informationINIT[k] == cECs) this.inithead = k;
		}

		// [ruban_gauche][information][ruban_droite]
		// [ruban_gauche] & [ruban_droite] == représente une case -1
		write_ruban(null, 0);
	}

	private int[] generate_etat(boolean stop, int etat) {
		int[] result = new int[e_bits];
		String etat_bin = Integer.toBinaryString(etat);
		int length = etat_bin.length();
		int e = e_bits-1;
		for (int i = length-1; i >= 0; e--, i--) {
			result[e] = etat_bin.charAt(i) == '1' ? c1 : c0;
		}
		for (; e >=0; e--) result[e] = c0;
		if (stop == true) result[0] = c1;

		return result;
	}

	private int[] generate_symbole(int symbole) {
		int[] result = new int[s_bits];
		String symb_bin = Integer.toBinaryString(Math.abs(symbole));
		int length = symb_bin.length();
		int s = s_bits-1;
		for (int i = length-1; i >= 0; s--, i--)
			result[s] = symb_bin.charAt(i) == '1' ? c1 : c0;
		for (; s >=0; s--) result[s] = c0;
		if (symbole < 0) result[0] = c1;

		return result;
	}

	private int[] generate_case(boolean head, int symbole) {
		int[] result = new int[s_bits + 3];
		int[] symb = generate_symbole(symbole);

		System.arraycopy(symb, 0, result, 2, s_bits);

		result[0] = (head == true ? c1 : c0);
		result[1] = cSs;
		result[s_bits+2] = cSe;

		return result;
	}
	private int sizeOfCase() {
		return s_bits + 3;
	}

	public void print_rubanMTP() {
		int s, head, current_state = 0;
		String ruban2String = "";
		ListIterator<Integer> iter = this.ruban.getIterator();

		while ((head = iter.next()) != cIs) {
			String symb_bin = "0";
			iter.next(); // skip cSs
			int signe = iter.next();
			for (int i=1; i < s_bits; i++) symb_bin += iter.next();
			String symb = (signe==c1? "-":"") + Integer.parseInt(symb_bin, 2);
			if (head == c1) ruban2String += " ["+symb+"]";
			else ruban2String += " " + symb;
			iter.next(); // skip cSe
		}
		while (iter.next() != cIe);

		while (iter.hasNext()) {
			head = iter.next();
			String symb_bin = "";
			iter.next(); // skip cSs
			int signe = iter.next();
			for (int i=1; i < s_bits; i++) symb_bin += iter.next();
			String symb = (signe==c1? "-":"") + Integer.parseInt(symb_bin, 2);
			if (head == c1) ruban2String += " ["+symb+"]";
			else ruban2String += " " + symb;
			iter.next(); // skip cSe
		}

		System.out.println("("+current_state +")	" + ruban2String);
	}


	public void write_ruban(int [] ruban, int inithead) {
		int[] ruban_droite, ruban_gauche, result;
		if (inithead >= 0) ruban_gauche = generate_case(false, B);
		else {
			ruban_gauche = new int[Math.abs(inithead) * sizeOfCase()];
			for (int n=Math.abs(inithead)-1; n >= 0; n--) {
				System.arraycopy(generate_case(n==0, B), 0, ruban_gauche, n*sizeOfCase(), sizeOfCase());
			}
		}
		if (ruban == null || ruban.length == 0) {
			if (inithead <= 0) ruban_droite = generate_case(0==inithead, B);
			else {
				ruban_droite = new int[(inithead+1)*sizeOfCase()];
				for (int n=0; n <= inithead; n++) {
					System.arraycopy(generate_case(n==inithead, B), 0, ruban_droite, n*sizeOfCase(), sizeOfCase());
				}
			}
		}
		else {	
			ruban_droite = new int[(inithead+1 > ruban.length? inithead+1 : ruban.length) * sizeOfCase()];
			for (int n=0; n < ruban.length; n++) {
				System.arraycopy(generate_case(n==inithead, ruban[n]), 0, ruban_droite, n*sizeOfCase(), sizeOfCase());
			}
			for (int n=ruban.length; n <= inithead; n++) {
				System.arraycopy(generate_case(ruban.length+n-1==inithead, B), 0, ruban_droite, n*sizeOfCase(), sizeOfCase());
			}
		}
		result = new int[ruban_gauche.length + informationINIT.length + ruban_droite.length];

		System.arraycopy(ruban_gauche, 0, result, 0, ruban_gauche.length);
		System.arraycopy(informationINIT, 0, result, ruban_gauche.length, informationINIT.length);
		System.arraycopy(ruban_droite, 0, result, ruban_gauche.length + informationINIT.length, ruban_droite.length);

		this.ruban = new RubanTuring(result, ruban_gauche.length + this.inithead, this.m, this.alpha);
	}

}
