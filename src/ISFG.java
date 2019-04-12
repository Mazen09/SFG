import java.util.ArrayList;

public interface ISFG {
	
	/*
	 * bos ya zmeily mn el a5er fokak mn kol el hary elly fi el class bta3 SFG wrkz m3 el interface da welly h2olo dlw2ti
	 * awel 7aga t3mlha lma ta5od mn el user 3dd el nodes w t3ml object mn el class da t7ot fi el constructor el size (ew3a tnsa :D)
	 * b3d kda tst3ml tany method addLink(from, to, gain) w hakaza l7d mt5ls el input bta3ak b3d kda t43'al awel method elly hya
	 * doProcessing() b3d kda el ms2alla 5lst a2y el methods 34an tgm3 el o/ps elly 3ayzha h4ra7 kol wa7da t7t 
	 * tb3n enta momkn mtst5dm4 mo3zam elly ana 7atto bs 34an tb2a 3arf bs m4 aktar :D  
	 * */
	
	/**
	 * 43'lha lma t5ls el i/ps w mlk4 da3wa :D
	 * */
	public void doProcessing();
	
	/**
	 * di lma t7b t7ot link bein 2 nodes
	 * @param s 
	 * da el source
	 * @param d
	 * da el distination
	 * @param gain 
	 * da el gain elly 3la el link
	 * */
	public void addLink(int s, int d, double gain);
	
	/**
	 * da lw 3ayz t3rf 3dd el nodes elly enta md5lo asln :D
	 * */
	public int getSize();
	
	/**
	 * tb3n wad7 en di el loops bs 5od balak mn el discription di 3bara 3n list of loops wkol loop 3bara 3n list of numbers
	 * elly hma arqam el nodes elly bt3dy fiha
	 * rkz fi el kalam 34an htdalm b3d kda :D
	 * */
	public ArrayList<ArrayList<Integer>> getLoops();
	
	/**
	 * di list fiha el gain bta3 kol loop mn el list bta3t el loops
	 * */
	public ArrayList<Double> getLoopsGains();
	
	/**
	 * tb3n wad7 en di el forward paths bs 5od balak mn el discription di 3bara 3n list of forward paths wkol
	 *  forward path 3bara 3n list of numbers
	 * elly hma arqam el nodes elly by3dy fiha
	 * rkz fi el kalam 34an htdalm b3d kda :D
	 * */
	public ArrayList<ArrayList<Integer>> getForwardPaths();
	
	/**
	 * di list fiha el gain bta3 kol forward path mn el list bta3t el forward paths
	 * */
	public ArrayList<Double> getFpsGains();
		
	/**
	 * el dalma htbd2 mn hna di b2a ya m3lm list bs 3 levels :D
	 * di list mn el non touched loops bs mtasema 7assab el index y3ny eih ?
	 * y3ny msln at index 0 : [ [L1] , [L2] , [L3] ] kol loop lw7dha
	 * at index 1 : [ [L1,L2] , [L2,L5] , [L3] ] kol 2 non touched loops lw7dhom
	 * at index 2 : [ [L1,L2,L3] , [L4,L5,L6] , [L7,L8,L9] ] kol 3 non touched loops lw7dhom
	 * w hakaza
	 * bs 5od balak
	 * L1 di el (index) bta3 el loop fi loops list :D 
	 * yeny loops.get(L1) = [3,4,5,6,8] hydeek el nodes bta3t el loop
	 * */
	public ArrayList<ArrayList<ArrayList<Integer>>> getNonTouchedLoops();
	
	/**
	 * 4ayef b2a elly 2olto mn 4waya 3n 2 non touched loops lw7dhom 3li 3lih level kman :D 
	 * nfs elklam elly fat bs lkol forward path list mn elly 4oftha foo2 :D 
	 * fhtla2eiha 4 levels 
	 * */
	public ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>> getNonTouchedLoopsWithFP();
	
	/**
	 * di el delta elly btb2a fi el mqam
	 * */
	public double getDelta();
	
	/**
	 * di list lil deltas bta3t kol forward path bl index bta3o fi el list bta3t el forward path
	 * */
	public ArrayList<Double> getFpDeltas();
	
	/**
	 * di btrg3 el list bta3t el nodes m4 m7taga 4ar7 :D 
	 * w 5od balak brdo en kol node hya list of links
	 * */
	public ArrayList<Link>[] getNodes();
	
	/**
	 * da el natg el nha2y elly el mfrood 3mlt kol el boleeka di 34ano :D
	 * */
	public double getOverallTF();

	
}
