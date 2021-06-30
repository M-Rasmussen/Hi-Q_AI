
public class Peg implements Comparable<Peg>{
	int location;
	int time;
	boolean full=true;
	int dpl;
	
	public Peg(int location, int time, int dpl) {
		super();
		this.location = location;
		this.time = time;
		this.dpl=dpl;
	}
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public boolean isFull() {
		return full;
	}
	public void setFull(boolean full) {
		this.full = full;
	}
	
	public int getDpl() {
		return dpl;
	}
	public void setDpl(int dpl) {
		this.dpl = dpl;
	}
	@Override
	public String toString() {
	return dpl+". Peg(" + location + "," + time +")";
	
	}
	public int compareTo(Peg comparestu){
		int comparetime=((Peg)comparestu).getTime();
		return this.time-comparetime;
		// TODO Auto-generated method stub
	
	}
	
}
