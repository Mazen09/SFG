import java.util.ArrayList;

public class SFG implements ISFG {
	private int size;
	private ArrayList<Link>[] nodes;
	private ArrayList<ArrayList<Integer>> loops = new ArrayList<ArrayList<Integer>>();
	private ArrayList<Double> loopsGains = new ArrayList<Double>();
	private ArrayList<Double> fpsGains = new ArrayList<Double>();
	private ArrayList<ArrayList<Integer>> forwardPaths = new ArrayList<ArrayList<Integer>>();
	private ArrayList<ArrayList<ArrayList<Integer>>> NonTouchedLoops = new ArrayList<ArrayList<ArrayList<Integer>>>();
	private ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>> NonTouchedLoopsWithFP = new ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>>();
	private double delta;
	private double overallTF;
	private ArrayList<Double> fpDeltas = new ArrayList<Double>();

	@SuppressWarnings("unchecked")
	public SFG(int size) {
		this.size = size;
		nodes = new ArrayList[this.size];
		for (int i = 0; i < nodes.length; i++) {
			nodes[i] = new ArrayList<Link>() ;			
		}
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public ArrayList<ArrayList<Integer>> getLoops() {
		return loops;
	}
	@Override
	public ArrayList<Double> getLoopsGains() {
		return loopsGains;
	}
	@Override
	public ArrayList<Double> getFpsGains() {
		return fpsGains;
	}

	@Override
	public ArrayList<ArrayList<Integer>> getForwardPaths() {
		return forwardPaths;
	}
	@Override
	public ArrayList<ArrayList<ArrayList<Integer>>> getNonTouchedLoops() {
		return NonTouchedLoops;
	}
	@Override
	public ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>> getNonTouchedLoopsWithFP() {
		return NonTouchedLoopsWithFP;
	}
	@Override
	public double getDelta() {
		return delta;
	}
	@Override
	public double getOverallTF() {
		return overallTF;
	}
	@Override
	public ArrayList<Double> getFpDeltas() {
		return fpDeltas;
	}
	@Override
	public ArrayList<Link>[] getNodes() {
		return this.nodes;
	}
	
	
	
	@Override
	public void addLink(int s, int d, double gain) {
		if (nodes[s] == null) {
			nodes[s] = new ArrayList<Link>();
			nodes[s].add(new Link(d, gain));
		} else {
			boolean notFound = true;
			for (Link link : nodes[s]) {
				if (link.isLinked(d) == false) {
				} else {
					link.addGain(gain);
					notFound = false;
				}
			}
			if (notFound) {
				nodes[s].add(new Link(d, gain));
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void findForwardPaths() {

		boolean[] isVisited = new boolean[this.size];
		ArrayList<Integer> pathList = new ArrayList<>();
		pathList.add(0);
		findAllPathsUtil(0, size - 1, isVisited, pathList);
		for(int i = 0; i < forwardPaths.size(); i++) {
			fpsGains.add(Gain(i, forwardPaths));
		}
	}

	private void findAllPathsUtil(Integer u, Integer d, boolean[] isVisited, ArrayList<Integer> pathList) {
		isVisited[u] = true;

		if (u.equals(d)) {
			@SuppressWarnings("unchecked")
			ArrayList<Integer> temp = (ArrayList<Integer>) pathList.clone();
			forwardPaths.add(temp);
		}
		
		for (Link link : nodes[u]) {
			int i = link.getD();
			if (!isVisited[i]) {
				pathList.add(i);
				findAllPathsUtil(i, d, isVisited, pathList);
				pathList.remove((Integer) i);
			}
		}
		isVisited[u] = false;
	}

	private void findLoops() {
		boolean[] isVisited = new boolean[this.size];
		findAllLoops(0, isVisited);
		ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < loops.size(); i++) {
			ArrayList<Integer> t = new ArrayList<Integer>();
			t.add(i);
			temp.add(t);
			loopsGains.add(Gain(i,loops));
		}
		NonTouchedLoops.add(temp);
	}

	private void findAllLoops(int i, boolean[] isVisited) {
		if (i == this.size) {
			return;
		}
		for (Link link : nodes[i]) {
			if (i >= link.getD() && isVisited[i] == false) {
				constructLoop(i, link.getD());
			} else if (i < link.getD()) {
				findAllLoops(link.getD(), isVisited);
			}
		}
		isVisited[i] = !isVisited[i];
	}

	private boolean isRepeated(ArrayList<Integer> a) {
		for (ArrayList<Integer> b : loops) {
			if (a.equals(b)) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	private void constructLoop(int i, int d) {
		ArrayList<Integer> temp;
		int e;
		for (ArrayList<Integer> f : forwardPaths) {
			if (f.contains(i) && f.contains(d)) {
				if (i == d) {
					temp = new ArrayList<Integer>();
					temp.add(d);
					temp.add(d);
				} else {
					int start = f.indexOf(d);
					int end = f.indexOf(i);
					temp = new ArrayList<Integer>();
					for (int j = start; j <= end; j++) {
						e = f.get(j);
						temp.add(e);
					}
					e = f.get(start);
					temp.add(e);
				}
				if (!isRepeated(temp)) {
					loops.add((ArrayList<Integer>) temp.clone());
				}
			}
		}
	}

	// main method to get non touched loops
	private void getAllNonTouchedLoops() {
		getNonTouchedLoops(1,NonTouchedLoops.get(0));
	}

	private void getNonTouchedLoops(int order,ArrayList<ArrayList<Integer>> a) {
		if(order == loops.size()) {
			return;
		}
		ArrayList<ArrayList<Integer>> bigList = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < loops.size(); i++) {
			for(ArrayList<Integer> b : a) {
				int count = 0;
				for(Integer c : b) {
					if(!isTouched(i, c) && i < c ) count++;
				}
				if(count == order) {
					count = 0;
					@SuppressWarnings("unchecked")
					ArrayList<Integer> smallList = (ArrayList<Integer>) b.clone();
					smallList.add(i);						
						bigList.add(smallList);	
				}
			}
		}
		if(bigList.size() != 0) {
			NonTouchedLoops.add(bigList);
			getNonTouchedLoops(order+1 ,bigList);			
		}
	}
	
	// main method to get non touched loops for all fps
	private void getNonTouchedLoopsforFps() { 
		for(int i = 0; i < forwardPaths.size(); i++) {
			fillFpLoopsList(i);
		}
	}
		
	private void fillFpLoopsList(int i) {
		ArrayList<ArrayList<ArrayList<Integer>>> megaList = new ArrayList<ArrayList<ArrayList<Integer>>>();
		ArrayList<ArrayList<Integer>> bigList = new ArrayList<ArrayList<Integer>>();
		
		for(ArrayList<ArrayList<Integer>> a : NonTouchedLoops) {
			bigList = new ArrayList<ArrayList<Integer>>();
			for(ArrayList<Integer> b : a) {
				int count = 0;
				for(Integer c : b) {
					if(!isTouchingfp(i,c)) count++;
				}
				if(count == b.size()) {
					bigList.add(b) ;
				}
			}
			if(!bigList.isEmpty()) megaList.add(bigList);
		}
		NonTouchedLoopsWithFP.add(megaList);
	}

	private boolean isTouchingfp(int fp, int i) {
		ArrayList<Integer> a = loops.get(i);
		ArrayList<Integer> forwardPath = forwardPaths.get(fp);
		
		for(Integer n : a) {
			if(forwardPath.contains(n)) return true;
		}
		
		return false;
	}

	private boolean isTouched(int i, int j) {	
		ArrayList<Integer> a = loops.get(i);
		ArrayList<Integer> b = loops.get(j);
		
		for(Integer x : a) {
			if(b.contains(x)) return true;
		}
		return false;
	}
	
	private double Gain(int l,ArrayList<ArrayList<Integer>> list) {
		double gain = 1;
		ArrayList<Integer> a = list.get(l);
		for(int i = 0; i < a.size() - 1; i++) {
			for(Link link : nodes[a.get(i)]) {
				int d = link.getD();
				if(d == a.get(i+1)) {
					gain *= link.getGain();
				}
			}
		}
		return gain;
	}
	
	private double calculateDeltaI(ArrayList<ArrayList<ArrayList<Integer>>> LoopList) {
		double value = 1;
		int sign = -1;
		double listSum = 0;
		for(ArrayList<ArrayList<Integer>> a : LoopList) {
			for(ArrayList<Integer> b : a) {
				double listProduct = 1;
				for(Integer c : b) {
					listProduct *= loopsGains.get(c);
				}
				listSum += listProduct;
			}
			value += listSum*sign;
			listSum = 0;
			sign = sign * -1;
		}
		return value;
	}

	private void calculateDeltasFPS() {
		double value;
		for(ArrayList<ArrayList<ArrayList<Integer>>> a : NonTouchedLoopsWithFP) {
			value = calculateDeltaI(a);
			fpDeltas.add(value);
		}
	}
	
	private void calculateOverallTF() {
		overallTF = 1;
		double summation = 0;
		if(fpDeltas.size() != fpsGains.size() ) {
			System.err.println("forward paths gain list length and their deltas list are not equal sizes");
			return;
		}
		for(int i = 0; i < fpsGains.size(); i++) {
			summation += (fpsGains.get(i)*fpDeltas.get(i));
		}
		overallTF *= (summation/delta);
	}
	
	@Override
	public void doProcessing() {
		findForwardPaths();
		findLoops();
		getAllNonTouchedLoops();
		getNonTouchedLoopsforFps();
		delta = calculateDeltaI(NonTouchedLoops);
		calculateDeltasFPS();
		calculateOverallTF();
	}
	
}
