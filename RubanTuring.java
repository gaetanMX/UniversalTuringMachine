import java.util.LinkedList;
import java.util.ListIterator;

public class RubanTuring {
        
        private LinkedList<Integer> ruban;
        int head;
        final int m;
        final int alpha;
        final int b = -1;
        
        public RubanTuring(int m, int alpha)
        /* pre : m >= 0
         * post : On a un objet qui reprￜsente un ruban comprenant
         * seulement des caractￜres blanc d'une machine de turing.
         * ruban.size =  1
         * ruban.get(0) = b
         * head = 0 
         */
        {
                this.m=m;
                this.alpha=alpha;
                ruban = new LinkedList<Integer>();
                ruban.add(b);
                head=0;
                //checkInv();
        }
        
        public RubanTuring(int[] ruban, int inithead, int m, int alpha)
        {
                this.m=m;
                this.alpha=alpha;
                this.ruban = new LinkedList<Integer>();
                if(inithead >= 0 && inithead < ruban.length)
                {
                        head = inithead;
                        for(int i=0; i<ruban.length; i++)
                        {
                                this.ruban.add(ruban[i]);
                        }
                }
                else
                {         
                        if(inithead < 0)
                        {
                                head = 0;
                                int x = inithead;
                                while(x<0)
                                {
                                        this.ruban.add(b);
                                        x++;
                                }        
                                for(int i=0; i<ruban.length; i++)
                                {
                                        this.ruban.add(ruban[i]);
                                }
                        }
                        else
                        {
                                head=inithead;
                                for(int i=0; i<ruban.length; i++)
                                {
                                        this.ruban.add(ruban[i]);
                                }
                                int x = inithead-ruban.length+1;
                                while(x>0)
                                {
                                        this.ruban.add(b);
                                        x--;
                                }
                        }
                }
                //checkInv();
        }
        
        public int getVal()
        {
        	//checkInv();
            return ruban.get(head);
        }
        
        public int[] getResult()
        /* pre : 
         * post : 
         */
        {
                int[] temp;
                if(ruban.get(this.head) == b) return new int[]{-1};
                int length = 1;
                for (int i = this.head-1; i > -1 && ruban.get(i) != b; i--) {
                        length++;
                } 
                temp = new int[length];
                for (int i= length-1; i >= 0; i--) {
                        temp[i] = ruban.get(this.head - ((length-1) - i));
                }
                //checkInv();
                return temp;
        }
        
        public void operation(int caractere, int move)
        /* pre : -1 <= caractere < m. 0 <= move < 3.
         * post : head, et le ruban peuve,t ￜtre modifiￜ.
         */
        {
                ruban.set(head, caractere);
                switch(move)
                {        
                        case 0:
                                if(head==0) {
                                        ruban.addFirst(b);
                                }
                                else
                                {
                                        head--;
                                }
                        break;
                        case 1:
                        break;
                        default:
                                if(head==ruban.size()-1) 
                                        {
                                        ruban.addLast(-1);
                                        head += 1;
                                        }
                                else
                                {
                                        head++;
                                }
                }
                //checkInv();
        }
        
        public ListIterator<Integer> getIterator() {
        	return this.ruban.listIterator(0);
        }

        public int getHead() { return head; }
        
        public int[] getArray() {
        	int[] rub = new int[ruban.size()];
        	ListIterator<Integer> iter = getIterator();
        	
        	for (int i=0; iter.hasNext(); i++) rub[i] = iter.next();
        	
        	return rub;
        }
        
        public String toString()
        {
                String temp = new String();
                for(int i = 0; i<ruban.size(); i++)
                {
                        if(i!=head) temp = (""+temp+" "+ruban.get(i));
                        else temp = (""+temp+" ["+ruban.get(i)+"]");
                }
                //checkInv();
                return temp;
        }
        
        public void checkInv(){
    		int size = ruban.size();
    		if(size < 1)
    			System.out.println("La taille du ruban est inf�rieure � 1 !");
    		if(head < 0 || head >= size)
    			System.out.println("La tete de lecture est sur une partie du ruban non g�n�r�e. Index : " + head);
    		for (int i = 0; i < size; i++){
    			if (ruban.get(i) < -1 || ruban.get(i) >= alpha)
    				System.out.println("Caract�re invalide sur le ruban : " + ruban.get(i) +" a la position : " + i);
    		}
        }
        
}
