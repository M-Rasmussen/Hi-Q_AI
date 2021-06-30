import java.util.ArrayList;

public class moves implements Comparable<moves>{
	int startFull;
	int middleFull;
	int endEpmty;
	int time;
	int dplu;
	String preAndPost;
	
	public moves(int startFull, int middleFull, int endEpmty, int time,int dplu) {
		super();
		this.startFull = startFull;
		this.middleFull = middleFull;
		this.endEpmty = endEpmty;
		this.time = time;
		this.dplu=dplu;
	}

	public int getStartFull() {
		return startFull;
	}

	public void setStartFull(int startFull) {
		this.startFull = startFull;
	}

	public int getMiddleFull() {
		return middleFull;
	}

	public void setMiddleFull(int middleFull) {
		this.middleFull = middleFull;
	}

	public int getEndEpmty() {
		return endEpmty;
	}

	public void setEndEpmty(int endEpmty) {
		this.endEpmty = endEpmty;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getDplu() {
		return dplu;
	}

	public void setDplu(int dplu) {
		this.dplu = dplu;
	}

	public String getPreAndPost() {
		return preAndPost;
	}

	public void setPreAndPost(String preAndPost) {
		this.preAndPost = preAndPost;
	}
	
	public int[] getPreAndPostIntArray() {
		String[] strarray=preAndPost.split(" ");
	      int size = strarray.length;
	      int [] arr = new int [size];
	      for(int i=0; i<size; i++) {
	         arr[i] = Integer.parseInt(strarray[i]);
	      }
	      return arr;
	   }
	

	@Override
	public String toString() {
		return dplu+". Jump(" + startFull + "," + middleFull + "," + endEpmty + ","+ time +")";
		
	}

	public int compareTo(moves comparestu){
		int comparetime=((moves)comparestu).getTime();
		return this.time-comparetime;
		// TODO Auto-generated method stub
	
	}	
	public void CNFMOVE(ArrayList<Peg>board) {
		int s=0, m=0, e=0, t=0,n=0,f=0;
		ArrayList<Peg>preboard=new ArrayList<Peg>();
		ArrayList<Peg>postboard=new ArrayList<Peg>();
		 int postTime=this.time+1;
		for(Peg i : board) {
			if(i.getTime()==this.time) {
				preboard.add(i);
			}else if(i.getTime()==postTime) {
				postboard.add(i);
			}
		}
		
	    for (Peg i : preboard) {
	        //System.out.println(i);
	        if(i.getLocation()==startFull) {
	        	s=i.getDpl();
	        }
	        else if(i.getLocation()==middleFull) {
	        	m=i.getDpl();
	        }
	        else if(i.getLocation()==endEpmty) {
	        	e=i.getDpl();
	        }
	      }	 
	    for (Peg i : postboard) {
	        //System.out.println(i);
	        if(i.getLocation()==startFull) {
	        	t=i.getDpl();
	        }
	        else if(i.getLocation()==middleFull) {
	        	n=i.getDpl();
	        }
	        else if(i.getLocation()==endEpmty) {
	        	f=i.getDpl();
	        }
	      }
	    this.preAndPost=Integer.toString(s) +" "+ Integer.toString(m) +" "+ Integer.toString(e*-1) +" "+Integer.toString(t*-1) +" "+Integer.toString(n*-1) +" "+Integer.toString(f);
	   // System.out.println(preAndPost);
	}
	public String printCNF() {
		return this.preAndPost;
	}
	public int printdplu() {
		return this.dplu;
	}
}
