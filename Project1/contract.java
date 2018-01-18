import java.util.ArrayList;
import java.util.Arrays; 

public class contract {
	//the job contract pair
	worker adam;
	int eve;
	ArrayList<Integer> epreference;
	
	public contract(int eve, ArrayList<Integer> epreference){
		this.eve = eve;
		this.epreference = epreference;
	}
	public void firstProposal(worker adam){
		this.adam = adam;
	}
	public worker proposal(worker adam){
		int compare1 = adam.getOrd();
		int compare2 = this.adam.getOrd();
		boolean decision = preferred(compare1, compare2);
		if(decision){
			worker toreturn = new worker(this.adam);
			this.adam = adam;
			return toreturn;
		}else{
			return adam;
		}
	}
	public boolean isHitched(){
		return this.adam != null;
	}
	
	public int getWorker(){
		return this.adam.getOrd();
	}
	
	public int getJob(){
		return eve;
	}
	
	private boolean preferred(int compare1, int compare2){
		int pos1 = epreference.indexOf(compare1);
		int pos2 = epreference.indexOf(compare2);
		if(pos1 < pos2){
			return true;
		}else{
			return false;
		}
	}
	
}
