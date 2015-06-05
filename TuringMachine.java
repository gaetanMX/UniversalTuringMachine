public class TuringMachine {
        
		protected int m;
        protected int alpha;
        protected int s0;
        protected int current_state;
        protected int [][][] trans;
        protected boolean [] accept;
        protected RubanTuring ruban;
        protected int n; 
        
        public TuringMachine(int m, int alpha, int s0, int [][][] trans, boolean [] accept)
        /* pre : Les arguments passￜs en paramￜtre respectent l'invariant de classe.
         * post : on a un objet qui reprￜsente correctement une machine de Turing. 
         * Le ruban contient seulement des caractￜres "blanc", la tￜte de lecture
         * se trouve en 0.
         */
        {
                this.m=m;
                this.alpha=alpha;
                this.s0=s0;
                this.trans=trans;
                this.accept=accept;
                ruban=new RubanTuring(m, alpha);
                n=accept.length;
                //checkInv();
        }
        
        public void write_ruban(int [] ruban, int inithead)
        /* pre : ruban!= NULL. ruban[x] appartient ￜ T pour tout x :
         * 0 =< x < ruban.length
         * post : la tￜte de lecture est placￜe ￜ inithead
         * le ruban est reprￜsentￜ par celui passￜ en paramￜtre. 
         */
        {
                this.ruban = new RubanTuring(ruban,inithead,m,alpha);
                //checkInv();
        }
        
        public void exec()
        /* post : current_state peut avoir ￜtￜ changￜ
         * La machine de turing a ￜtￜ exￜcutￜe ￜ partir de son ￜtat initial :
         * le ruban et la tￜte de lecture peuvent avoir ￜtￜ modifiￜs.
         */
        {
                current_state=s0;
                while(accept[current_state]==false)
                {
                        exec_step2step(current_state);                        
                }
                //checkInv();
         }
        
        public void exec_step2step(int s)
        /* pre : s appartient ￜ S
         * post : current_state peut avoir ￜtￜ changￜ.
         * Un pas de la machine de turing a ￜtￜ exￜcutￜ ￜ partir de l'ￜtat s :
         * Le ruban et la tￜte de lecture peuvent avoir ￜtￜ modifiￜs.
         */
        {
                if(accept[s]==false)
                {
                        int x = ruban.getVal();
                        if(x==-1) x=alpha-1;
                        current_state = trans[s][x][0];
                        int newVal = trans[s][x][1];
                        int direction = trans[s][x][2];
                        ruban.operation(newVal,direction);
                }
                print_ruban();
                //checkInv();
        }
        
        public void print_ruban()
        /* post : affiche l'ￜtat courat ainsi que le ruban (sans les extrￜmitￜs
         * de blanc) avec la position de la tￜte de lecture.
         * Convention de reprￜsentation : Etat courant : s \n
         *                                                                   Ruban : ruban.toString() \n
         */
        {
               /* System.out.println("Etat courrant : "+current_state+"\n");
                System.out.println("Ruban : "+ruban.toString()+"\n");
                */
        		System.out.println("("+current_state +") 	" + ruban.toString());
                //checkInv();
        }
        public int[] getResult()
        {
                int[] x = ruban.getResult();
                //checkInv();
                return x;
        }
        public long exec_fct(long x)
        /* pre : x >= 0. {0,1} de (Somme)
         * post : une machine de turing est crￜer et est lancￜe selon la fonction.
         * x est inchangￜ.
         * resultat : -1 en cas d'erreur sinon le rￜsultat de la fonction.
         */
        {
                String bin = "";
                int length;
                
                bin = Long.toBinaryString(x);
                length = bin.length();
                int[] r = new int[length];
                for (int i = 0; i < length; i++) {
                        r[i] = bin.charAt(i) == '1' ? 1 : 0;
                }
                write_ruban(r,0);
                
                //Lancement de la machine de Turing:
                print_ruban();
                exec();
                print_ruban();
                bin = "";
                
                //rￜecriture depuis le binaire en decimal.
                int[] sol = getResult();
                length = sol.length;

                for (int i=0; i < length; i++) {
                        if (sol[i] != 0 && sol[i] != 1) return -1;
                        bin += sol[i];
                }
                //checkInv();
                return Long.parseLong(bin, 2);
        }
        
       
        public void checkInv(){
        	   if(m < 0)
        		   System.out.println("La valeur de m est incorrecte : " + m);
        	   if(accept.length < 1)
        		   System.out.println("La valeur de n est incorrecte : " + accept.length);
        	   if(s0 < 0 || s0 >= accept.length)
        		   System.out.println("La valeur de s0 est incorrecte : " + s0);
        	   if(trans == null)
        		   System.out.println("Fonction de transition non initialis�e");
        	   if(trans.length != accept.length)
        		   System.out.println("Le nombre d'etatd dans la fonction de transition est incorrect");
        	   for(int i = 0; i < trans.length; i++){
        		   if(trans[i].length != alpha)
        			   System.out.println("Le nombre de symboles possibles pour l'etat " + i + " n'est pas correct");
        		   if(trans[i] == null)
        			   System.out.println("Fonction de transition non initialis�e pour l'etat : " + i);
        		   for(int j = 0; j < trans[i].length; j++){
        			   if(trans[i][j] == null)
        				   System.out.println("Fonction de transition non initialis�e pour l'etat " + i + "et le symbole : " + j);
        			   if(trans[i][j].length != 3)
        				   System.out.println("Mauvaise fonction de transition pour l'etat " + i + "et le symbole : " + j);
        			   if(trans[i][j][0] < 0 || trans[i][j][0] >= accept.length)
        				   System.out.println("Mauvaise valeur d'�tat pour la transition : " + i + " " + j);
        			   if(trans[i][j][1] < -1 || trans[i][j][1] >= alpha)
        				   System.out.println("Mauvaise valeur de symbole pour la transition : " + i + " " + j);
        			   if(trans[i][j][2] < 0 || trans[i][j][2] > 2 )
        				   System.out.println("Mauvaise valeur de mouvement pour la transition : " + i + " " + j);
        		   }
        	   }
        	   if(accept == null)
        		   System.out.println("Tableau des �tats acceptants non initialis�");
        	   if(ruban == null)
        		   System.out.println("Ruban non initialis�");
        }
}
