
public class pw_check {

	private DLBnode root;
	
	private class DLBnode{
		public char val;
		public DLBnode rightSib;
		public DLBnode child;
		
		
		public void buildTrie() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  DLBnode newDLB = new DLBnode();
		if (args.length>0){
			if (args[0].equals("-g")){
				newDLB.buildTrie();
			}
		}
	}

}
