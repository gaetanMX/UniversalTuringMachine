public class TestMachineTuring {
      
        public static String dec2bin(long l) {
        	/* PRE: l >= 0
        	 * POST: /
        	 * RETURN: string contenant la convertion de l en binaire
        	 */
                return Long.toBinaryString(l);
        }
        
        public static String addZero(String str, int n) {
        	/* PRE: str != NULL && n > 0
        	 * POST: n caractères '0' ont été rajoutés à l'avant de str 
        	 */
        	for (; n > 0; n--) str = "0" + str;
        	return str;
        }
        
        public static int[] string2int(String str) {
                /* PRE: str != null && 
                 *      str[i] est un caractere représentant un entier, 
                 *		pour tout i : 0 <= i < str.length() 
                 * POST: /
                 * RETURN: la conversion du string en un tableau d'entier. 
                 */
                int length = str.length();
                int[] inttab = new int[length];
                for (int i = 0; i < length ; i++) {
                        inttab[i] = Character.getNumericValue(str.charAt(i));
                }
                return inttab;
        }
        

}
