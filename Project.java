import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Project {

	public static void main(String[] args) {
		if(args.length!=1) {
			System.out.println("Did not enter the commandline argument");
			System.exit(1);
		}
		String filePath=args[0];
		ArrayList<moves> movetime = new ArrayList<moves>();
		ArrayList<Peg> pegtime = new ArrayList<Peg>();
	  	  int numberofActualMoves=0;
	  	  int startposition=0;
	  	  ArrayList<Integer> pegState=new ArrayList<Integer>();
	  	  int failtimes=0;
	  	  String solidPrint="";
		try {
		      File myObj = new File(filePath);
		      Scanner myReader = new Scanner(myObj);
		      String firstLine = myReader.nextLine();
		      //System.out.println(firstLine);
		      String[] boardsplitted=firstLine.split(" ");
		     // board startBoard=new board(Integer.valueOf(boardsplitted[0]));
		      startposition=Integer.valueOf(boardsplitted[1]);
		  	  ArrayList<Integer> startSpotsempty= new ArrayList<Integer>();
		  	  ArrayList<Integer> startSpotsfilled= new ArrayList<Integer>();
		  	  int counter=1;
		  	  int numberofPossibleMoves=0;
		      for(int y=1;y<=Integer.valueOf(boardsplitted[0]);++y){
		    	  pegState.add(y);
		    	  startSpotsfilled.add(y);
		    	  numberofActualMoves=Integer.valueOf(boardsplitted[0])-1;
		    	  for (int i=1;i<=numberofActualMoves;++i) {
		    		  if(i==1 && y==Integer.valueOf(boardsplitted[1])) {
		    			Peg emptystart= new Peg(y,i,counter);
		    			emptystart.setFull(false);
		    			pegtime.add(emptystart);
		    		  }else {
		    		  pegtime.add(new Peg(y,i,counter));
		    		  }
		    		  counter++;
		    	  }
		      }
		      int emptySpot=startSpotsfilled.indexOf(Integer.valueOf(boardsplitted[1]));
		      startSpotsempty.add(startSpotsfilled.remove(emptySpot));
		     // startBoard.setSpotsempty(startSpotsempty);
		     // startBoard.setSpotsfilled(startSpotsfilled);
		      numberofPossibleMoves=Integer.valueOf(boardsplitted[0])-2;
		      
		      while (myReader.hasNextLine()){
		        String data = myReader.nextLine();
		        String[] splited = data.split(" ");
		        for(int z=1;z<=numberofPossibleMoves;++z){
		        movetime.add(new moves(Integer.valueOf(splited[0]),Integer.valueOf(splited[1]),Integer.valueOf(splited[2]),z,counter));
		        counter++;
		        }
		        for(int w=1;w<=numberofPossibleMoves;++w){
		        	movetime.add(new moves(Integer.valueOf(splited[2]),Integer.valueOf(splited[1]),Integer.valueOf(splited[0]),w,counter));
		        	counter++;
		      } 

		        if(Integer.valueOf(splited[2])==startposition|| Integer.valueOf(splited[0])==startposition){
		        	failtimes++;
		        }
		        }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
				System.out.println("File not found");
				System.exit(1);
		    }
		Collections.sort(movetime);
		Collections.sort(pegtime);    	
			for(int i=0; i<pegtime.size();i++){
				pegtime.get(i).setDpl(i+1);
	    	System.out.println(pegtime.get(i));
	     
	      }
	    Map<Integer, ArrayList<Integer>>basedoftime = new HashMap<Integer, ArrayList<Integer>>();

	    //SORT MOVES BY TIME
	    for (moves i : movetime) {
	        i.CNFMOVE(pegtime);
			if(basedoftime.containsKey(i.getTime())){
				basedoftime.get(i.getTime()).add(i.getDplu());
			}else {
				basedoftime.put(i.getTime(),new ArrayList<Integer>());
				basedoftime.get(i.getTime()).add(i.getDplu());
			}
			System.out.println(i);
	      }
	    System.out.println("--------------------");
	    String s="-"+String.valueOf(startposition);
	    pegState.set(startposition-1, startposition*-1);
	    MakeMove start=new MakeMove(1,s,pegState);
	    Node<MakeMove> root = new Node<MakeMove>(start);
	    Node<MakeMove> temp=root;
    	ArrayList<Integer>pastmoves=new ArrayList<Integer>(); 
    	boolean alreadyDone=true;
    	int tempMoveHolder;
    	int failCounter=0;

	    while(true){

    		if(failCounter==failtimes) {
    			System.out.println("FAILED");
    			break;
    		}
	    	if(temp.getData().getMove()>basedoftime.keySet().size()) {
	    		
	    		System.out.println("SUCCESS "+ temp.getData().getCnf());
	    		break;
	    	}
	    	ArrayList<Integer>base=basedoftime.get(temp.getData().getMove());
	    	tempMoveHolder=temp.getData().getMove();

	    	alreadyDone=true;
	        for (Integer val : base) {
	        	if(alreadyDone) {
	        	for(moves i : movetime) {
	        		if(val==i.getDplu()) {
	        			List<Integer> mpaparr = Arrays.stream(i.getPreAndPostIntArray()).boxed().collect(Collectors.toList());
	        			List<Integer> addmpaparr = Arrays.stream(temp.getData().getCnfasInt()).boxed().collect(Collectors.toList());
	        			
	        			if(validMove(mpaparr, temp.getData().getBoardState())) {
	        				
	        			addmpaparr.addAll(mpaparr);
	        			if(checkdouble(addmpaparr)) {
	        				String joinedList = addmpaparr.stream().map(String::valueOf).collect(Collectors.joining(" "));
	        				if(pastmoves.contains(i.getDplu())) {
	        				}else {
		        		    	if(temp==root) {
		        		    		System.out.println(i.getDplu()+" (-) "+temp.getData().getCnf());
		        		    	}else {
		        			    	System.out.println(i.getDplu()+" (-) "+temp.getData().getCnf().replaceFirst(temp.getParent().getData().getCnf(),""));
		        		    	}
	        				pastmoves.add(i.getDplu());
	        				
	        				ArrayList<Integer> newboard= Changeboard(mpaparr, temp.getData().getBoardState());
	        				MakeMove current=new MakeMove(temp.getData().getMove()+1, joinedList,newboard);
	        			    Node<MakeMove> child = new Node<MakeMove>(current);
	        				temp.addChild(child);
	        		    	temp=temp.getChildAt(temp.getData().getVisitedChild());
	        		    	int visited=temp.getParent().getData().getVisitedChild();
	        		    	temp.getParent().getData().setVisitedChild(visited+1);
	        		    	alreadyDone=false;
	        					}
	        			}
	        				}
	        			}
	        		}
	        	}
	       }
	        if(tempMoveHolder==temp.getData().getMove()) {
        	//System.out.println("WILL THIS BE ONCE PER MOVE");
        	temp=temp.getParent();
        	if(temp==root) {
    	        failCounter++;
        	}
	        }
	    }
	    
	}	
	public static boolean checkdouble(List<Integer> mp) {
		Set<Integer> set = new LinkedHashSet<>();
		set.addAll(mp);
		mp.clear();
		mp.addAll(set);
		//System.out.println(mp);
		HashMap<Integer,Integer> cnt = new HashMap<Integer,Integer>();
		for(int i =0; i<mp.size();i++){
	        if (cnt.containsKey(Math.abs(mp.get(i)))) {
	            cnt.put(Math.abs(mp.get(i)) , cnt.get(Math.abs(mp.get(i))) + 1);
	        }else{ 
	            cnt.put(Math.abs(mp.get(i)), 1);
	        }
	        if( cnt.get(Math.abs(mp.get(i))) == 2 ){
	          return false;
	        }
	    }
		return true;
	}
	public static boolean validMove(List<Integer> pm, List<Integer>boardState) {
		boolean sign=false;
		for(int i=0; i<3;i++){
			sign=false;
			int val=pm.get(i);
			if(val<0){
				sign=true;
				val=val*-1;
			}
			
			val=val%boardState.size();
			if(val==0){
				val=boardState.size();
			}
			if(sign) {
				val=val*-1;
			}
			if(!boardState.contains(val)) {
				return false;
			}
		}
		return true;
	}
	public static ArrayList<Integer> Changeboard(List<Integer> pm, List<Integer>boardState) {
		ArrayList<Integer> newBoard=new ArrayList<Integer>();
		newBoard.addAll(boardState);
		boolean sign=false;
		int alwaysPos;
		for(int i=3; i<6;i++){
			sign=false;
			int val=pm.get(i);
			if(val<0){
				sign=true;
				val=val*-1;
			}
			val=val%boardState.size();
			if(val==0){val=boardState.size();}
			alwaysPos=val;
			
			if(sign) {
				val=val*-1;
			}
			newBoard.set(alwaysPos-1, val);
		}
		return newBoard;
	}	

}
