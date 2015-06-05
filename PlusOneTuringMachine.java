
public class PlusOneTuringMachine  {
	public static void main(String [] args)  {
		TuringMachineUniverselle t = getMT1();
		t.write_ruban(new int[]{1}, 0); // 110 - 1011
		t.print_rubanMTP();

		t.exec();
		t.print_rubanMTP();

	}
	 public static TuringMachineUniverselle getMT1() {
         // machine de turing calculant la fonction f(x) = x + 1
         int m = 2;
         int s0 = 0;
         boolean[] accept = new boolean[]{false,false,false,true};
         int[][][] trans = new int[4][3][3];
         trans[0][0] = new int[]{ 0,0,2 };
         trans[0][1] = new int[]{ 0,1,2 };
         trans[0][2] = new int[]{ 1,-1,0 };
         
         trans[1][0] = new int[]{ 2,1,1 };
         trans[1][1] = new int[]{ 1,0,0 };
         trans[1][2] = new int[]{ 2,1,1 };

         trans[2][0] = new int[]{ 2,0,2 };
         trans[2][1] = new int[]{ 2,1,2 };
         trans[2][2] = new int[]{ 3,-1,0 };
         
         trans[3][0] = new int[]{ 3,0,1 };
         trans[3][1] = new int[]{ 3,1,1 };
         trans[3][2] = new int[]{ 3,2,1 };

         return new TuringMachineUniverselle(m, m+1, s0, trans, accept);
 }        
}
