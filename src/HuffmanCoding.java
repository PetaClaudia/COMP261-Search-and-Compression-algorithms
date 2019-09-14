import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * A new instance of HuffmanCoding is created for every run. The constructor is
 * passed the full text to be encoded or decoded, so this is a good place to
 * construct the tree. You should store this tree in a field and then use it in
 * the encode and decode methods.
 */
public class HuffmanCoding {
	private Queue<huffmanNode>frequency = new PriorityQueue<huffmanNode>();
	private Map<Character, String> binary = new HashMap<Character, String>();
	private huffmanNode root;
	
	/**
	 * This would be a good place to compute and store the tree.
	 */
	public HuffmanCoding(String text) {
		Map<Character, Integer> tree = buildTree(text);

		for(Character ch : tree.keySet()) {	//add each entry in the tree to the priorityqueue as a huffmanNode
			frequency.add(new huffmanNode(ch, tree.get(ch), null, null));	
		}
		//for(huffmanNode n : frequency) {
		//	System.out.println(n.letter+": "+n.freq);
		//}
		//System.out.println(frequency.size());
		
		while (frequency.size()>1) {	//make the parents
			huffmanNode left = frequency.poll();
			huffmanNode right = frequency.poll();
			huffmanNode parent = new huffmanNode('\0', left.getFrequency()+right.getFrequency(),left, right);
			root = parent;
			frequency.add(parent);
		}
		assignCodes(root, "");
	}
	
	private Map<Character, Integer> buildTree(String text) {
		Map<Character, Integer> tree = new HashMap<Character, Integer>();
		for (int i = 0; i < text.length(); i++) {
			char letter = text.charAt(i);
			if (tree.containsKey(letter)) {	// if the tree already has letter, increment frequency
				int frequency = tree.get(letter);
				tree.remove(letter, frequency);
				tree.put(letter, frequency+1);
			}
			else {
				tree.put(letter, 1);	//else add it to the tree
			}
		}
		return tree;
		
	}

	public void assignCodes(huffmanNode node, String b){
		node.binary = b;
		if (node.isLeafNode()) {	//put the node with its complete code in the map
			binary.put(node.getLetter(), node.binary);		
		}
		else {
			assignCodes(node.getLeft(), node.binary + '0');
			assignCodes(node.getRight(), node.binary + '1');	
		}
	}

	/**
	 * Take an input string, text, and encode it with the stored tree. Should
	 * return the encoded text as a binary string, that is, a string containing
	 * only 1 and 0.
	 */
	public String encode(String text) {
		for(char ch : binary.keySet()) {
			System.out.println("char: " + ch + " code: " + binary.get(ch));
		}
		System.out.println(binary.size());
		StringBuilder build = new StringBuilder();
		for(int i = 0; i< text.length(); i++) {
			build.append(binary.get(text.charAt(i)));	//add the code for every char in text to the string
		}
		return build.toString();
		
	}

	/**
	 * Take encoded input as a binary string, decode it using the stored tree,
	 * and return the decoded text as a text string.
	 */
	public String decode(String encoded) {
		StringBuilder decodeString = new StringBuilder();
		huffmanNode node = root;
		for(int i = 0; i<encoded.length(); i++) {
			if(encoded.charAt(i) == '0'){	//if 0, left node
				node = node.getLeft();
				if(node.getLeft() == null ) {	//means it's a leaf
					decodeString.append(node.getLetter());	//add the letter to the string
					node = root; 
				}
			}
			else if(encoded.charAt(i) =='1'){	//if 1, right node
				node = node.getRight();
				if(node.getRight() == null){	//means it's a leaf
					decodeString.append(node.getLetter());	//add the letter to the string
					node = root;
				}
			}
		}
		
		return decodeString.toString();
	}

	/**
	 * The getInformation method is here for your convenience, you don't need to
	 * fill it in if you don't wan to. It is called on every run and its return
	 * value is displayed on-screen. You could use this, for example, to print
	 * out the encoding tree.
	 */
	public String getInformation() {
		return "";
	}
}
