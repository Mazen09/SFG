import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main(String[] args) {
//		ArrayList<Link>[] ss = new ArrayList[20] ;
//		ss[0] = new ArrayList<Link>();
//		ss[0].add(new Link(4, 0));
//		
//		for(Link link : ss[0]) {
//			System.out.println(link.getD());
//		}
		
		


		
		 SFG f = new SFG(8);
		 f.addLink(0, 1, 1);
		 f.addLink(1, 2, 3);
		 f.addLink(2, 3, 6);
		 f.addLink(3, 4, 12);
		 f.addLink(3, 6, 5);
		 f.addLink(4, 5, 7);
		 f.addLink(5, 6, 1);
		 f.addLink(5, 4, 9);
		 f.addLink(5, 7, 2);
		 f.addLink(6, 2, 4);
		 f.addLink(6, 7, 9);
		 f.addLink(7, 5, 8);
		 f.addLink(7, 1, 11);
		 
		 


		
//		 System.out.println("list of nodes with links");
//		 int i = 0;
//		 for(ArrayList<Link> node : f.nodes) {
//			 System.out.println("Node "+i);
//			 for(Link k : node) {
//				 System.out.println("link to :" + k.getD());
//			 }
//			 i++;
//			 System.out.println();
//		 }
//		 System.out.println("end");
		 
		 
		 f.doProcessing();
		 System.out.println(f.getForwardPaths());
		 System.out.println(f.getLoops());
		 for(ArrayList<ArrayList<Integer>> a : f.getNonTouchedLoops()) {
			 System.out.println(a);
		 }
		 System.out.println("list of non touched loops with fps");
		 for(int i = 0; i < f.getForwardPaths().size(); i++) {
			 System.out.println("=======================================");
			 System.out.println("non touched loops for fp "+i+" :");
			 for(ArrayList<ArrayList<Integer>> a : f.getNonTouchedLoopsWithFP().get(i)) {
				 System.out.println(a);
			 }
		 }
		 System.out.println("loops gains :");
		 System.out.println(f.getLoopsGains());
		 System.out.println();
		 System.out.println("fps gains :");
		 System.out.println(f.getFpsGains());
		 System.out.println();
		 System.out.println("delta");		 
		 System.out.println(f.getDelta());
		 System.out.println("fps deltas");
		 System.out.println(f.getFpDeltas());
		 System.out.println("transfer function");
		 System.out.println(f.getOverallTF());
		 
		 
		 
		 
		 
//		 for(int j = 0; j < f.loops.size()-1; j++) {
//			 for(int k = 1; k < f.loops.size(); k++) {
//				 boolean g = f.isTouchingfp(2, j, k);
//				 System.out.println("is loop "+j+" and loop "+k+" touching fp 2 : "+g);
//			 }
//		 }
//		
		 

	}
}
