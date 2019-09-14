
public class huffmanNode implements Comparable<huffmanNode>{
	char letter;
	int freq;
	String binary;
	huffmanNode left, right;
	
	huffmanNode(char letter, int freq, huffmanNode left, huffmanNode right){
		this.letter = letter;
		this.freq = freq;
		this.left = left;
		this.right = right;
	}

	@Override
	public int compareTo(huffmanNode o) {
		// TODO Auto-generated method stub
		return this.freq - o.freq;
	}

	public int getFrequency() {
		return this.freq;
	}

	public void setFrequency(int freq) {
		this.freq = freq;
	}
	
	public boolean isLeafNode() { 
		return (this.left == null) && (this.right == null);
	}
	
	public String getBinary() {
		return this.binary;
	}

	public void setBinary(String binary) {
		this.binary = binary;
	}
	
	public char getLetter() {
		return this.letter;
	}

	public void setLetter(char letter) {
		this.letter = letter;
	}

	public huffmanNode getLeft() {
		return this.left;
	}
	
	public huffmanNode getRight() {
		return this.right;
	}
}
