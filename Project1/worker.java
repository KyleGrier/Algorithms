import java.util.ArrayList;
import java.util.Arrays;

public class worker {
	private int order;
	private ArrayList<Integer> prefer;
	private int prefersize;
	private int prefpoint;
	public worker(worker nwork){
		this.order = nwork.order;
		this.prefer = nwork.prefer;
		this.prefersize = nwork.prefersize;
		this.prefpoint =nwork.prefpoint;
	}
	public worker(int order, ArrayList<Integer> prefer){
		this.order = order;
		this.prefer = prefer;
		this.prefpoint = 0;
	}
	public int getSize(){
		return prefersize;
	}
	public void setSize(){
		prefersize = prefer.size();
	}
	public void removePref(){
		prefer.remove(0);
		setSize();
	}
	public int nextJob(){
		int value = prefer.get(prefpoint);
		prefpoint++;
		return value;
	}
	public int getOrd(){
		return order;
	}
	public boolean isEqual(worker ditto){
		return this.order == ditto.getOrd();
	}
}
