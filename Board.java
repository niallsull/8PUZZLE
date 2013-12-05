import java.util.ArrayList;


public class Board {

	int[] tiles;      // store tile/ piece number
	int size;               // #colums || # rows
	int numberMoves;         // moves taken to get to this board
	Board prevBoard;
	
	
	public Board(int[] newTile,int s,int moves, Board prev)
	{
		
		this.tiles=newTile;
		this.size=s;
		this.numberMoves=moves;
		this.prevBoard=prev;
	}
	
	public Board()
	{
	
	}
	
	public Board copy()        // need a copy method, for deep copy instead of shallow copy
	{
		int[] newTiles=new int[this.tiles.length];
		for(int i=0;i<this.tiles.length;i++)
		{
			newTiles[i]=this.tiles[i];    // copy our array
		}
		
		int copySize=this.size;
		int copyMoves=this.numberMoves;
		
		Board copy = new Board(newTiles,copySize,copyMoves,null);  // create the board copy
		copy.prevBoard=this.prevBoard;          // other wise my stack won't work
		
		return copy;
	}
	
	public Iterable<Board> neighbours()
	{
		ArrayList<Board> collection = new ArrayList<Board>(4);
		
		int blank=0;
		
		
		
		Boolean leftColumn=false;
		Boolean rightColumn=false;
		Boolean topRow=false;
		Boolean bottomRow=false;
		
		// System.out.println("currentBoard");
		// System.out.println(currentBoard.toString());
		 
		for(int i=0;i<this.size*this.size;i++)
		{
			if(this.tiles[i]==0)
			{
				if (i%this.size==0)  
				{
					leftColumn=true;
					 //System.out.println("left");
				}
				
				if (i%this.size==(this.size-1))  
				{
					rightColumn=true;
					//System.out.println("right");
				}
				
				if( i/this.size==0)
				{
					topRow=true;
					//System.out.println("top");
				}
				
				if(i/this.size==(this.size-1))
				{
					bottomRow=true;
					//System.out.println("bottom");
				}
				
				blank=i;    // record the blank tile
				i=this.size*this.size+2;
			}	
			
		}
				//make new boards / moves
				
				if( (bottomRow==true) )
				{
					//swap above
					
					Board b=this.copy();
					b.prevBoard=this;   // for stack, overwrites the copy method
					
					
					int temp=b.tiles[blank];
					b.tiles[blank]=b.tiles[blank-this.size];        // swap tiles around
					b.tiles[blank-this.size]=temp;
					
					b.numberMoves++;
					
					collection.add(b);
				
					
					
					 
				}
				
				if(topRow==true)
				{
					//swap below
					
		
					Board b=this.copy();
					b.prevBoard=this;
					
					int temp=b.tiles[blank];
					b.tiles[blank]=b.tiles[blank+this.size];
					b.tiles[blank+this.size]=temp;
					b.numberMoves++;
					collection.add(b);
					
					
					
				}
				
				if(rightColumn==true)
				{
					//swap  left
					
					Board b=this.copy();
					b.prevBoard=this;
					
					int temp=b.tiles[blank];
					b.tiles[blank]=b.tiles[blank-1];
					b.tiles[blank-1]=temp;
					b.numberMoves++;
					collection.add(b);
					
					
					
				}
				
				if(leftColumn==true)
				{
					//swap right
					
					Board b=this.copy();
					b.prevBoard=this;
					
					int temp=b.tiles[blank];
					b.tiles[blank]=b.tiles[blank+1];
					b.tiles[blank+1]=temp;
					b.numberMoves++;
					collection.add(b);
				
					
					
				}
				
				
				
				if( (topRow==false) && (bottomRow==false)  )    // neither top or bottom, so go up and down, redundant for 2x2, never gonna happen
				{
					//System.out.println("middle");
					
					Board b=this.copy();
					b.prevBoard=this;
					
					int temp=b.tiles[blank];
					b.tiles[blank]=b.tiles[blank-this.size];
					b.tiles[blank-this.size]=temp;
					b.numberMoves++;
					collection.add(b);
					
					
					
					
					Board a=this.copy();
					a.prevBoard=this;
					
					temp=a.tiles[blank];
					a.tiles[blank]=a.tiles[blank+this.size];
					a.tiles[blank+this.size]=temp;
					a.numberMoves++;
					collection.add(a);
					
					
					
					
				}
				
				if((rightColumn==false) && (leftColumn==false) )  // not on left or right, so go left and right
				{
					
					//System.out.println("middle");
					Board b=this.copy();
					b.prevBoard=this;
				
					int temp=b.tiles[blank];
					b.tiles[blank]=b.tiles[blank-1];
					b.tiles[blank-1]=temp;
					b.numberMoves++;
					collection.add(b);
					
					
					
					Board a=this.copy();
					a.prevBoard=this;
					
					temp=a.tiles[blank];
					a.tiles[blank]=a.tiles[blank+1];
					a.tiles[blank+1]=temp;
					a.numberMoves++;
					collection.add(a);
					
					
					
					
				}
		
		
		return collection;
		
		
	}
	
	
	public String toString()
	{
		String out="";
		for(int i=0;i<size*size;i++)
		{
			      // create a string to print out the board positions and values
			if(i%size==0)
			{
				out+="\n";
			}
		out+=" "+tiles[i] + " ";
		}
		
		return out;
	}
	
	public int manhattan()
	{
		int distance=0;
		
		
		for(int i=0;i<this.tiles.length;i++)
		{
			if(tiles[i]==0)
			{
				i++;  // ignore the blank tile
			}
			
			else
			{
			int currentColumn=(i%size)+1;      //column we are now at
			int currentRow=(i/size)+1;     // row we are now at
			
			int correctColumn=(tiles[i]%size);    // the column this piece should be in
			int correctRow=(tiles[i]/size)+1;    // the row this piece should be in
			
			
			
			if(correctColumn==0)  // no such thing as a zero column
			{                          //3%3=0  4%4=0 etc
				correctColumn=size;
				//correctRow--;
			}
			
			if(tiles[i]%size==0)
			{                                  // as the above correctRow 
				correctRow=tiles[i]/size;
			}
			
			distance+=mod(currentColumn-correctColumn)+mod(currentRow-correctRow);  // keep adding her up lad
			}
		}
		
		
		
		return distance;
	}
	
	public int hamming()
	{
		int distance=0;
		
		
		
		
		for(int i=0;i<this.tiles.length;i++)
		{
			
			
			if(tiles[i]!=(i+1) )
				distance++;
			             // straightforward stuff
			if(tiles[i]==0)
			{
				distance--;   // cancel it out
			}
		}
		
		
		
		return distance;
	}
	
	
	public boolean equals(Object y)
	{
		boolean equals=true;
		
		if(y==null)
		{
			equals=false;
			
		}
		
		else{
		
		for(int i=0;i<tiles.length;i++)
		{
			if(tiles[i]!=((Board)y).tiles[i])  // only checking the tile values/positions in regards to equality
				equals=false;
		}
		}
		
		return equals;
	}
	
	
	
	private int mod(int number)
	{
		if(number<0)
			number*=-1;
		                   // just for the manhattan stuff, can't go negative spaces now
		return number;
	}
	
}
