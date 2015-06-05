
public class CopyTuringMachine  {
    public static void main(String [] args) {
    	/* exemple d'execution */
        copy(130);
    }

	/**
	 * @Pre: inputN >= 0
	 */
	public static void copy(long inputN) {
		int m = 2;
		int alpha = 6;
		int s0 = 0;
		int S = 9;
		
		boolean accept[] = {false, false, false, false, false, false, false, true, false};

		
        
        int[][][] trans = new int[S][alpha][3];
		
        /* symbole => index
         * 0 => 0
         * 1 => 1
         * ; => 2
         * Z => 3
         * U => 4
         * B => 5
         */
        
        /* trans[Etat][Symb] = new int[]{Etat',Symb',Mvm} */
        /* Mvm = 0 => gauche */
        /* Mvm = 1 => sur place */
        /* Mvm = 2 => droite */
        
        /* Etat 0 */
        trans[0][0] = new int[]{2,3,2}; 
        trans[0][1] = new int[]{1,4,2};
        trans[0][2] = new int[]{6,2,0};
        trans[0][3] = new int[]{8,3,0};
        trans[0][4] = new int[]{8,4,0};
        trans[0][5] = new int[]{8,-1,2};
        
        /* Etat 1 */
        trans[1][0] = new int[]{1,0,2}; 
        trans[1][1] = new int[]{1,1,2};
        trans[1][2] = new int[]{3,2,2};
        trans[1][3] = new int[]{8,3,0};
        trans[1][4] = new int[]{8,4,0};
        trans[1][5] = new int[]{3,2,2};

        /* Etat 2 */
        trans[2][0] = new int[]{2,0,2}; 
        trans[2][1] = new int[]{2,1,2};
        trans[2][2] = new int[]{4,2,2};
        trans[2][3] = new int[]{8,3,0};
        trans[2][4] = new int[]{8,4,0};
        trans[2][5] = new int[]{4,2,2};
        
        /* Etat 3 */
        trans[3][0] = new int[]{3,0,2}; 
        trans[3][1] = new int[]{3,1,2};
        trans[3][2] = new int[]{8,2,0};
        trans[3][3] = new int[]{8,3,0};
        trans[3][4] = new int[]{8,4,0};
        trans[3][5] = new int[]{5,1,0};
        
        /* Etat 4 */
        trans[4][0] = new int[]{4,0,2}; 
        trans[4][1] = new int[]{4,1,2};
        trans[4][2] = new int[]{8,2,0};
        trans[4][3] = new int[]{8,3,0};
        trans[4][4] = new int[]{8,4,0};
        trans[4][5] = new int[]{5,0,0};

        /* Etat 5 */
        trans[5][0] = new int[]{5,0,0}; 
        trans[5][1] = new int[]{5,1,0};
        trans[5][2] = new int[]{5,2,0};
        trans[5][3] = new int[]{0,0,2};
        trans[5][4] = new int[]{0,1,2};
        trans[5][5] = new int[]{8,-1,0};

        /* Etat 6 */
        trans[6][0] = new int[]{6,0,0}; 
        trans[6][1] = new int[]{6,1,0};
        trans[6][2] = new int[]{8,2,0};
        trans[6][3] = new int[]{8,3,0};
        trans[6][4] = new int[]{8,4,0};
        trans[6][5] = new int[]{7,-1,2};
      
        /* Etat 7 */
        trans[7][0] = new int[]{7,0,0}; 
        trans[7][1] = new int[]{7,1,0};
        trans[7][2] = new int[]{7,2,0};
        trans[7][3] = new int[]{7,3,0};
        trans[7][4] = new int[]{7,4,0};
        trans[7][5] = new int[]{7,-1,0};
        
        /* Etat 8 */
        trans[8][0] = new int[]{8,-1,0}; 
        trans[8][1] = new int[]{8,-1,0};
        trans[8][2] = new int[]{8,-1,0};
        trans[8][3] = new int[]{8,-1,0};
        trans[8][4] = new int[]{8,-1,0};
        trans[8][5] = new int[]{8,-1,0};

        int head = 0;
        String rubanstr = TestMachineTuring.dec2bin(inputN);
        int[] ruban = TestMachineTuring.string2int(rubanstr);

        TuringMachineUniverselle machine = new TuringMachineUniverselle(m, alpha, s0, trans, accept);
        
        machine.write_ruban(ruban, head);
        machine.print_ruban();
        machine.print_rubanMTP();
        machine.exec();
        machine.print_rubanMTP();
	}

}