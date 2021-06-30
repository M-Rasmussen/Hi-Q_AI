import java.util.ArrayList;

public class MakeMove {
	 int move;
	 int visitedChild;
	 String cnf;
	 ArrayList<Integer> boardState;
	public MakeMove(int move, String cnf,ArrayList<Integer> pegState) {
		super();
		this.move = move;
		this.cnf = cnf;
		this.boardState=pegState;
		this.visitedChild=0;
	}
	
	public int getVisitedChild() {
		return visitedChild;
	}

	public void setVisitedChild(int visitedChild) {
		this.visitedChild = visitedChild;
	}

	public int getMove() {
		return move;
	}
	public void setMove(int move) {
		this.move = move;
	}
	public String getCnf() {
		return cnf;
	}
	public void setCnf(String cnf) {
		this.cnf = cnf;
	}
	public int[] getCnfasInt() {
		String[] strarray=cnf.split(" ");
	      int size = strarray.length;
	      int [] arr = new int [size];
	      for(int i=0; i<size; i++) {
	         arr[i] = Integer.parseInt(strarray[i]);
	      }
	      return arr;
	}	
	public ArrayList<Integer> getBoardState() {
		return boardState;
	}
	public void setBoardState(ArrayList<Integer> boardState) {
		this.boardState = boardState;
	}
	@Override
	public String toString() {
		return "MakeMove [move=" + move + ", visitedChild=" + visitedChild + ", cnf=" + cnf + ", boardState="
				+ boardState + "]";
	}

	 
}
