import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class pw_check {
	static boolean empty = true;
	private static DLBnode root;

	public pw_check() {

	}

	private static class DLBnode {
		public char val;
		public DLBnode rightSib;
		public DLBnode child;

		private DLBnode(char letter, DLBnode sib, DLBnode child) {
			this.val = letter;
			this.rightSib = sib;
			this.child = child;
		}

	}

	public static void buildTrie(DLBnode newDLB) throws IOException {
		// TODO Auto-generated method stub

		/*
		 * BufferedReader read = new BufferedReader(new FileReader(
		 * "dictionary.txt"));
		 */
		DLBnode rootNode = root;
		BufferedReader read = new BufferedReader(new FileReader(
				"C:/Users/maurice/cs1501/DLB/src/small_dict.txt")); // for
																	// testing
																	// purposes
		String s;
		File file = new File(
				"C:/Users/maurice/cs1501/DLB/src/my_dictionary.txt");
		file.createNewFile();
		FileWriter writer = new FileWriter(file);
		while ((s = read.readLine()) != null) {
			if (s.length() < 6) {
				writer.write(s + "\n");
				writer.write(System.getProperty("line.separator"));

				s += "'"; // the character ' signifies the end of the string in
							// the trie
				add(s);
			}
		}
		// writer.flush();
		writer.close();
		read.close();
		printTrie(rootNode, "");

	}

	static void printTrie(DLBnode node, String prefix) {
		// System.out.println(node.child.val);

		if (node.val == ('\'')) {
			System.out.println(prefix);
			return;
		}
		if (node.rightSib != null) {
			printTrie(node.rightSib, prefix);
		}
		if (node.child != null) {
			prefix += node.val;
			printTrie(node.child, prefix);
		}
	}

	static void add(String word) {
		if (empty) {

			root = new DLBnode(word.charAt(0), null, null);
			System.out.println("0: " + root.val);
			root.child=new DLBnode(' ', null, null);
			DLBnode currNode = root.child;
			for (int i = 1; i < word.length(); ++i) {

				currNode = new DLBnode(word.charAt(i), null, null);
				System.out.println(i + ": " + currNode.val);
				currNode = currNode.child;
			}

			empty = false;
			return;
		} else {
			if (root == null) {
				System.out.println("yes"); 									// for testing purposes
			}
			DLBnode currentNode = root;
			for (int i = 0; i < word.length();) {
				//boolean check = false;
				//System.out.println(word+"----------"+word.charAt(i));
				//System.out.println("val: "+currentNode.val);
				if (currentNode.val==word.charAt(i)){						//checks if char matches the node's val
					currentNode=currentNode.child;
					i++;
				}else{
					if (currentNode.rightSib==null){						// if char doesnt match val & rightSib doesnt exist
						currentNode.rightSib=new DLBnode (word.charAt(i), null, null);
						currentNode=currentNode.rightSib;
						currentNode.child=new DLBnode(' ', null, null);
						for (int a=i; a<word.length(); a++){
							currentNode = new DLBnode(word.charAt(a), null, null);
							System.out.println(a + ": " + currentNode.val);
							if (word.charAt(a)!='\''){
								currentNode.child=new DLBnode(' ', null, null);
								currentNode = currentNode.child;
								
							}
						}
						break;
					}else if (currentNode.rightSib!=null){
																		//if rightSib exists then move currentNode to rightSib and repeat for loop
						currentNode=currentNode.rightSib;
					}
					
				}
				//System.out.println(i + ": " + currentNode.val);
				
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		DLBnode DLB = new DLBnode(' ', null, null);
		/*
		 * if (args.length > 0) { if (args[0].equals("-g")) { buildTrie(DLB); }
		 * }
		 */
		buildTrie(DLB);
	}

}
