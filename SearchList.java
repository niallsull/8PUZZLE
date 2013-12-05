
public class SearchList {           // like a dictionary of boards 

	Board[][][] searchList;
	int segmentLength;
	
	public SearchList(int size)
	{
		searchList=new Board[size*size][(size*size)][(size*size*size*size*size*size)];
		this.segmentLength=(size*size)-2;
	}
	
	public boolean checkBoard(Board b)
	{
		boolean seen=false;
		
		Board board=b.copy();
		int zeroP=0;
		int oneP=0;
		
		for(int i=0;i<board.tiles.length;i++)
		{
			if(board.tiles[i]==0)
			{
				zeroP=i;
			}
			
			if(board.tiles[i]==1)
			{
				oneP=i;
			}
		}
		
		for(int j=0;j<this.segmentLength;j++)
		{
			// look through, if we get to a null, we havent seen the board, add it, else well you've seen it obviously enough
			
			if( (board.equals(this.searchList[zeroP][oneP][j]))  )
			{
				seen=true;
				//System.out.println("this board has already been seen");
			}
			
			else if(this.searchList[zeroP][oneP][j]==(null))
			{
				this.searchList[zeroP][oneP%8][j]=board;
				j=this.segmentLength+1;
			}
			
		}
		
		
		
		return seen;
	}
	
	
}
