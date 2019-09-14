
public class BruteForceSearch {
	String pattern, text;
	
	public BruteForceSearch(String pattern, String text) {
		this.pattern = pattern;
		this.text = text;
	}

	/**
	 * Perform Brute Force search on the given text with the given pattern.
	 * 
	 * This should return the starting index of the first substring match if it
	 * exists, or -1 if it doesn't.
	 */
	public int search(String pattern, String text) {
		long start = System.currentTimeMillis();	//start timer
		int m = pattern.length();
		int n = text.length();
		char[] s = pattern.toCharArray();
		char[] t = text.toCharArray();
		for(int k = 0; k<n-m; k++) {	
			boolean found = true;
			for(int i = 0; i<m-1; i++) {
				if(s[i] != t[k+i]) {	
					found = false;
					break;
				}
			}
			if(found) {
				long end = System.currentTimeMillis();	//end timer
				long time = end - start;
				System.out.println("Brute Force: "+time);
				return k;	//starting index of pattern
			}
		}
		
		return -1;	//no matches
	}
}
