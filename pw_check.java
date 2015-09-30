import java.io.BufferedReader;
import java.io.FileReader;
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
		BufferedReader read = new BufferedReader(new FileReader(
				"dictionary.txt"));
		String s;
		while ((s = read.readLine()) != null) {
			if (s.length() < 6) {
				s += "'"; 													// the character ' signifies the end of the string
				add(s);
			}
		}
		read.close();

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
			boolean check = false; // A FLAG TO CHECK IF THE LETTER WAS EVER
									// FOUND, IF NOT WE ADD THE LETTER AT THE
									// END
			if (currentNode.rightSib != null) { // NEED NEXTNODE TO EXIST TO
												// CHECK ITS LETTER
				if (currentNode.val != word.charAt(i)) { // CHECK ROW
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
		if (args.length > 0) {
			if (args[0].equals("-g")) {
				buildTrie(DLB);
			}
		}
	}

}
