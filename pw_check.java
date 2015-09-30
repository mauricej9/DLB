import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class pw_check {
	static boolean empty = true;
	private static DLBnode root;

	
	public pw_check(){
		
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
		
		/*BufferedReader read = new BufferedReader(new FileReader(
				"dictionary.txt"));*/
		
		BufferedReader read = new BufferedReader(new FileReader("C:/Users/maurice/cs1501/DLB/src/small_dict.txt"));												//for testing purposes 
		String s;
		File file = new File("C:/Users/maurice/cs1501/DLB/src/my_dictionary.txt");
		file.createNewFile();
	    FileWriter writer = new FileWriter(file); 
		while ((s = read.readLine()) != null) {
			if (s.length() < 6) {
				writer.write(s+"\n");
				writer.write(System.getProperty( "line.separator" ));
				
				s += "'"; 													// the character ' signifies the end of the string in the trie
				add(s);
			}
		}
		//writer.flush();
	    writer.close();
		read.close();
		printTrie(root, "");
		

	}
	static void printTrie(DLBnode node, String prefix){
		if (node.rightSib!=null){
			printTrie()
		}
	}

	static void add(String word) {
		if (empty) {

			root = new DLBnode(word.charAt(0), null, null);
			for (int i = 1; i < word.length(); ++i) {
				root.child = new DLBnode(word.charAt(i), null, null);
				root = root.child;
			}

			empty = false;
			return;
		}

		DLBnode currentNode = root;
		for (int i = 0; i < word.length();) {
			boolean check = false; 
			if (currentNode.rightSib != null) { 
												
				if (currentNode.val != word.charAt(i)) {
					currentNode = currentNode.rightSib;
					check = true;
				} else { // CHECK COLUMN
					++i;
					currentNode = currentNode.child;
					check = true;
				}
			}
			if (currentNode.rightSib == null
					&& currentNode.val == word.charAt(i) && !check) {
				++i;
				currentNode = currentNode.child;
				check = true;
			}
			
			if (!check) {
				currentNode.rightSib = new DLBnode(word.charAt(i), null, null);
				currentNode = currentNode.rightSib;
				for (int a = i + 1; a < word.length(); ++a) {
					currentNode.child = new DLBnode(word.charAt(a), null, null);
					currentNode = currentNode.child;
				}

				break;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		DLBnode DLB = new DLBnode(' ', null, null);
		/*if (args.length > 0) {
			if (args[0].equals("-g")) {
				buildTrie(DLB);
			}
		}*/
		buildTrie(DLB); 
	}

}
