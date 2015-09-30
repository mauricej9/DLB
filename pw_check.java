import java.io.IOException;



public class pw_check {

	private DLBnode root;
	
	private class DLBnode{
		public char val;
		public DLBnode rightSib;
		public DLBnode child;
		
		private DLBnode(char letter,  DLBnode sib,  DLBnode child) {
            this.val = letter;
            this.rightSib = sib;
            this.child = child;
        }
		
		
		
		
	}
	public static void buildTrie(DLBnode newDLB) throws IOException {
			// TODO Auto-generated method stub
			
		}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		  DLBnode DLB = new DLBnode();
		if (args.length>0){
			if (args[0].equals("-g")){
				buildTrie(DLB);
			}
		}
	}

}
