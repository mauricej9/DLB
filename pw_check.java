import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

public class pw_check {
	static boolean empty = true;
	private static DLBnode root = new DLBnode(' ', null, null);
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

		// DLBnode rootNode = root;
		BufferedReader read = new BufferedReader(new FileReader(
				"C:/Users/maurice/cs1501/DLB/src/dictionary.txt")); // for
																	// testing
																	// purposes
		String s;
		/*File file = new File(
				"C:/Users/maurice/cs1501/DLB/src/my_dictionary.txt");
		file.createNewFile();
		FileWriter writer = new FileWriter(file);*/
		while ((s = read.readLine()) != null) {
			if (s.length() < 5&&s.length()>0) {
//				writer.write(s + "\n");
//				writer.write(System.getProperty("line.separator"));
				// badPass.add(s);
				s += "'"; // the character ' signifies the end of the string in
							// the trie
				add(s);
				badPass.add(s);
				leetAdd(s);
			}
		}
		// writer.flush();
		//writer.close();
		//read.close();
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
		DLBnode node = root.child;
		// boolean found=false;
		for (int i = 0; i < s.length();) {
			//System.out.println("node val: " + node.val);
			if (node.rightSib == null) {
				//System.out.println("no right sib");
			}
			//System.out.println("char: " + s.charAt(i));

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
		// System.out.println("word: " + word);
		/*
		 * File file = new File(
		 * "C:/Users/maurice/cs1501/DLB/src/my_dictionary.txt"); if
		 * (file.exists()) { System.out.println("file exists"); } FileWriter fw
		 * = new FileWriter(file.getAbsoluteFile()); BufferedWriter bw = new
		 * BufferedWriter(fw); bw.write(word); bw.close();
		 */
		if (!badPass.contains(word)) {
			badPass.add(word);
			add(word);
		}
		return word;
	}

	static void add(String word) {
		//System.out.println("added word: " + word);
		DLBnode node = root;
		for (int i = 0; i < word.length();i++) {
			if (node.child != null) {
				node = node.child;
			} else {
				node.child = new DLBnode(word.charAt(i), null, null);
				
				node = node.child;
			}
			while (node.val != word.charAt(i)) {

				if (node.rightSib != null) {
					node = node.rightSib;
				} else {
					node.rightSib = new DLBnode(word.charAt(i), null, null);
					node=node.rightSib;
				}
			}
		}
	}
	
	static void generateGoodPasswords(){
		char[] chars= {'b','c','d','e','f','g','h','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','1','2','3','4','5','6','7','8','9','0','!','@','$','%','&','*'};
		for (int a=0; a<chars.length; a++){
			for (int b=0; b<chars.length; b++){
				for (int c=0; c<chars.length; c++){
					for (int d=0; d<chars.length; d++){
						for (int e=0; e<chars.length; e++){
							char[] pass={chars[a],chars[b],chars[c],chars[d],chars[e]};
							System.out.println(Character.toString(pass[0])+Character.toString(pass[1])+Character.toString(pass[2])+Character.toString(pass[3])+Character.toString(pass[4]));
							String password=pass.toString();
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		DLBnode DLB = new DLBnode(' ', null, null);
		buildTrie(DLB);
		
		 try {
	            //Whatever the file path is.
	            File statText = new File("C:/Users/maurice/cs1501/DLB/src/my_dictionary.txt");
	            FileOutputStream is = new FileOutputStream(statText);
	            OutputStreamWriter osw = new OutputStreamWriter(is);    
	            Writer w = new BufferedWriter(osw);
	            for (int i = 0; i < badPass.size(); i++) {
	    			//System.out.println(badPass.get(i));
	    			w.write(badPass.get(i));
	    			w.write(System.getProperty("line.separator"));
	    		}
	            w.close();
	        } catch (IOException e) {
	            System.err.println("Problem writing to the file statsTest.txt");
	        }
		 generateGoodPasswords();
		
	}

}
