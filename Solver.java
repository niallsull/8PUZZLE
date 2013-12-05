import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


public class Solver {

	int moves;      // number of moves to solve
	boolean solvable;      // is she solvable
	PriorityQueue myQueue;          // initial queue of 20 
	Board currentBoard;         // board we are looking at
	LushLinkedList myList;
	Stack myStack;
	int queued=0;
	
	
	public int moves()
	{
		return this.moves;
	}
	
	public Solver(Board initial,boolean ham)
	{
		this.currentBoard=initial;
		//initialize the shit
		myList=new LushLinkedList(currentBoard);
		myQueue=new PriorityQueue(20,ham,initial.size); 
		myStack=new Stack(10);
	}
	
	public void solve()
	{
		
		
		int iterations=0;
		
		
		int s=currentBoard.size;
		int[] tiles = new int[s*s];
		for(int i=0;i<s*s;i++)
		{
			tiles[i]=i+1;   // make a goal board
			
		}
		tiles[(s*s)-1]=0;
		
		Board goalBoard=new Board(tiles,s,0,null);
		Boolean queue;	
		
		while(currentBoard.equals(goalBoard)==false)
		{
					Iterable<Board> collect = currentBoard.neighbours();
					
					Iterator<Board> gc=collect.iterator();
					
					
					while(gc.hasNext())
					{
						queue=myQueue.addBoard(gc.next());
						if(queue==true)
							queued++;
					}
		
		
					currentBoard=myQueue.nextBoard();   // pick out the next best board
				
					myList.add(currentBoard);
					iterations++;
					
				}
				
				
		
				
				
				
				
				
				while(currentBoard.prevBoard!=null)
				{
					myStack.Add(currentBoard.copy());
					currentBoard=currentBoard.prevBoard;
					//System.out.println("Board ADDED to stack");
					
				}
				
				
			}		
		
			
		
	public Iterable<Board> solution()
	{
		
		ArrayList<Board> solution=new ArrayList<Board>(this.myStack.theStack.length);
		
		for(int i=this.myStack.theStack.length-1;i>=0;i--)
		{
			
			if(this.myStack.theStack[i]!=null)
				solution.add(this.myStack.theStack[i]);
			
			
		}
		
		return solution;
		
	}
	

	
	
	
	
	
	public boolean isSolvable(Board b)
	{
		
		boolean canSolve=false;
		int blank=0;
		int numInversions=0;
		for(int i=0;i<(b.tiles.length)-1;i++)
		{
			
			if(b.tiles[i]==0)
				blank=i;
			
			for(int j=i+1;j<b.tiles.length;j++)
			{
				
				
				
				
				if((b.tiles[i]>b.tiles[j]) && (b.tiles[j]!=0))
					numInversions++;
			}
		}
		/*
		 * 
		 * http://www.cs.bham.ac.uk/~mdr/teaching/modules04/java2/TilesSolvability.html
		 * 
		 */
		
		
		if( (((b.size%2==1) && (numInversions%2==0))) || ((((b.size%2==0) && (((blank/b.size)+1)%2==1) && (numInversions%2==1) || (((b.size%2==0) && ((blank/b.size)+1)%2==0) && (numInversions%2==0))))) )
		{
			canSolve=true;
		}
		
		
		
		
		
		
		return canSolve;
	}
	
	public static void main(String[] args) throws IOException {
		
		String file="";
		BufferedReader kbd=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Name the file of the board");
		file=kbd.readLine();
		
		Scanner inputStream=null;
		
		try{
			inputStream = new Scanner(new FileInputStream(file));

		}
		catch(FileNotFoundException e){
			System.out.println("Problem opening files.");
			System.exit(0);
		}
		
		
		int size=inputStream.nextInt();
		
		int[] nums=new int[size*size];
		
		int i=0;
		while(inputStream.hasNextInt())
		{
			nums[i]=inputStream.nextInt(); // 9 looks redundant enough
			
			
			
			i++;
		}
		
		int[] tiles=new int[i];
		
		System.arraycopy(nums, 0,tiles,0,tiles.length);
		Board init=new Board(tiles,size,0,null);
		
		Solver boardSolver=new Solver(init,false);
		
		
		File f=new File(file+"Solution.txt");
		FileWriter fw=new FileWriter(f);
		
		PrintWriter write=new PrintWriter(fw);
		
		
		
		
		if(boardSolver.isSolvable(init)==true)
		{
			int num=0;
			
			boardSolver.solve();
			
			Iterable<Board> collect = boardSolver.solution();
			
			Iterator<Board> solution=collect.iterator();
			
			
			System.out.println(init.toString());
			
			while(solution.hasNext())
			{
				
				num++;
				Board c=solution.next();
				System.out.println(c);
				write.print(c.toString()+"\n");
				write.println("\n");
				
			}
			
			
			write.print("Number of states enqeued: " + boardSolver.queued +"\n");
			write.println("\n");
			System.out.println("Number of states enqeued: " + boardSolver.queued );  // ends up being 1 extra, as initial board isn't added 'til second iteration
			System.out.println("Minimum number of moves: "+num);
			write.print("Minimum number of moves: "+num +"\n");
			write.println("\n");
		}
		
		else
		{
			System.out.println("No solution Possible");
			write.print("No solution Possible");
		}
		
		write.close();
		
	}

}
