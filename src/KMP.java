/**
 * A new KMP instance is created for every substring search performed. Both the
 * pattern and the text are passed to the constructor and the search method. You
 * could, for example, use the constructor to create the match table and the
 * search method to perform the search itself.
 */
public class KMP {
	int[] jumpTable;

	public KMP(String pattern, String text) {
		
		this.jumpTable = new int[pattern.length()];
		jumpTable[0] = -1;	//can't have a failed match at pos 0
		jumpTable[1] = 0;
		
		int j = 0; 	//position in prefix
		int pos = 2;	//position in table
		int m = pattern.length();
		char[] s = pattern.toCharArray();
		
		while(pos < m) {
			if(s[pos-1] == s[j]) {	//substrings ...pos-1 and 0...j match
				jumpTable[pos] = j+1;
				pos++;
				j++;
			}
			else if(j>0) {	//mismatch, restart the prefix
				j = jumpTable[j];
			}
			else {	//we have run out of candidate prefixes
				jumpTable[pos] = 0;
				pos++;
			}
		}
	}

	/**
	 * Perform KMP substring search on the given text with the given pattern.
	 * 
	 * This should return the starting index of the first substring match if it
	 * exists, or -1 if it doesn't.
	 */
	public int search(String pattern, String text) {
		long start = System.currentTimeMillis();	//timer for testing
		int k = 0;	//start of current match in T
		int i = 0;	//position of current character in S
		int n = text.length();
		int m = pattern.length();
		char[] s = pattern.toCharArray();
		char[] t = text.toCharArray();
		while(k+i < n) {
			if(s[i] == t[k+i]) {	//match at i
				i++;
				if(i == m) {
					long end = System.currentTimeMillis();	//end timer
					long time = end - start;
					System.out.println("KMP: "+ time);
					return k;	//found S
				}
			}
			else if (jumpTable[i] == -1) {	//mismatch, no self overlap
				k = k+i+1;
				i = 0;
			}
			else {	//mismatch, with self overlap
				k = k+i-jumpTable[i];	//match position jumps forward
				i = jumpTable[i];
			}
		}
		
		return -1;	//failed to find S
	}
}
