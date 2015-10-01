import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class pw_check {
	static boolean empty = true;
	private static DLBnode root;
	static ArrayList<String> goodPass = new ArrayList<String>();
	static ArrayList<String> badPass = new ArrayList<String>();

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

		// BufferedReader read = new BufferedReader(new FileReader(
		// "C:/Users/maurice/cs1501/DLB/src/dictionary.txt"));

		//DLBnode rootNode = root;
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
				//badPass.add(s);
				s += "'"; // the character ' signifies the end of the string in
							// the trie
				add(s);
				badPass.add(s);
				leetAdd(s);
			}
		}
		// writer.flush();
		writer.close();
		read.close();
		// printTrie(rootNode, "");

	}

	/*
	 * static void printTrie(DLBnode node, String prefix) { //
	 * System.out.println(node.child.val);
	 * 
	 * if (node.val == ('\'')) { System.out.println(prefix); return; } if
	 * (node.rightSib != null) { printTrie(node.rightSib, prefix); } if
	 * (node.child != null) { prefix += node.val; printTrie(node.child, prefix);
	 * } }
	 */

	static boolean search(String s) {
		DLBnode node = root;
		// boolean found=false;
		for (int i = 0; i < s.length();) {
			// System.out.println("node val: " + node.val);
			// System.out.println("char: " + s.charAt(i));
			if (node.val == s.charAt(i)) {
				node = node.child;
				i++;
			} else if (node.rightSib != null) {
				node = node.rightSib;
			} else if (node.rightSib == null) {
				return false;
			}
		}
		return true;

	}

	static void leetAdd(String s) throws IOException {
		// System.out.println("s: "+s);
		String temp;
		for (int i = 0; i < s.length(); i++) {

			if (s.charAt(i) == 'i' || s.charAt(i) == 'a' || s.charAt(i) == 'l'
					|| s.charAt(i) == 'e' || s.charAt(i) == 's'
					|| s.charAt(i) == 'o' || s.charAt(i) == 't') {
				// System.out.println("i:"+i);
				temp = changeLetter(s, i);
				// System.out.println(temp);
				leetAdd(temp);

			}
		}
	}

	public static String changeLetter(String s, int i) throws IOException {
		String word = "";
		for (int j = 0; j < s.length(); j++) {
			// System.out.println("charAt(j): "+s.charAt(j));
			if (j == i) {
				if (s.charAt(j) == 'i' || s.charAt(j) == 'l')
					word += '1';
				if (s.charAt(j) == 'a')
					word += '4';
				if (s.charAt(j) == 't')
					word += '7';
				if (s.charAt(j) == 'e')
					word += '3';
				if (s.charAt(j) == 's')
					word += '5';
				if (s.charAt(j) == 'o')
					word += '0';

			} else {
				word += s.charAt(j);
			}
		}
		//System.out.println("word: " + word);
		/*
		 * File file = new File(
		 * "C:/Users/maurice/cs1501/DLB/src/my_dictionary.txt"); if
		 * (file.exists()) { System.out.println("file exists"); } FileWriter fw
		 * = new FileWriter(file.getAbsoluteFile()); BufferedWriter bw = new
		 * BufferedWriter(fw); bw.write(word); bw.close();
		 */
		if (!badPass.contains(word)) {
			try {
				String filename = "C:/Users/maurice/cs1501/DLB/src/my_dictionary.txt";
				FileWriter fw = new FileWriter(filename, true); // the true will
																// append the
																// new data
				fw.write(word);// appends the string to the file
				fw.write(System.getProperty("line.separator"));
				fw.close();
			} catch (IOException ioe) {
				System.err.println("IOException: " + ioe.getMessage());
			}
			badPass.add(word);
			add(word);
		}
		return word;
	}

	static void add(String word) {
		DLBnode node =root;
		for ( int i=0; i< word.length();){
			if (node.child==null){
				
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
		// leetAdd("the");
		buildTrie(DLB);
		for (int i=0; i<badPass.size(); i++){
			//System.out.println(badPass.get(i));
		}
		System.out.println(search("them"));
	}

}
