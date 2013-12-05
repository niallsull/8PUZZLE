
public class PriorityQueue {

	
	// from what read essentially a heap
	// we'll use manhattan + # moves as the value, min heap
	// could just search through the entire array 
	// but would result in huge search time,resulting in probably an 
	// extra two searches on each new pass
	
	
	Board[] priorityQueue;
	SearchList l;
	int currentSize;
	boolean ham;
	
	public PriorityQueue(int size, boolean hamming,int boardSize)
	{
		
		priorityQueue=new Board[size+1];
		for(int i=0;i<=size;i++){
			priorityQueue[i]=null;
		}
		currentSize=0;
		
		 l=new SearchList(boardSize);
		 
	}

	
	
	public boolean addBoard(Board b)
	{
		boolean seen=l.checkBoard(b);
		
		
		
		if(seen==false)
		{
		
			currentSize++;
		
		
		
		
			priorityQueue[currentSize]=b.copy();
		
			if(currentSize==(priorityQueue.length-2))             // create more space if close to running out
			{
				Board[] sub = new Board[priorityQueue.length];
				System.arraycopy(priorityQueue, 0, sub, 0, priorityQueue.length);
			
				priorityQueue=new Board[priorityQueue.length*2];
			
				System.arraycopy(sub, 0, priorityQueue, 0, sub.length);
			
			}
		
		
		
			int subSize=currentSize;
		
			if(ham==false)         // working on manhattan
			{
			
				while(  (subSize/2>0) && (priorityQueue[subSize].manhattan()<priorityQueue[subSize/2].manhattan())  )
				{
					Board temporory=priorityQueue[subSize].copy();
					priorityQueue[subSize]=priorityQueue[subSize/2].copy();
					priorityQueue[subSize/2]=temporory.copy();
			
					subSize=subSize/2;;
				}
		
		
				//System.out.println("board added");
				}
			
		
			else
			{
				while(  (subSize/2>0) && (priorityQueue[subSize].hamming()<priorityQueue[subSize/2].hamming())  )
				{
					Board temporory=priorityQueue[subSize].copy();
					priorityQueue[subSize]=priorityQueue[subSize/2].copy();
					priorityQueue[subSize/2]=temporory.copy();
			
					subSize=subSize/2;;
				}
			
			}
		}
		
		return !seen;
	}
	
	
	public Board nextBoard()
	{
		Board min=priorityQueue[1];
		
		priorityQueue[1]=priorityQueue[currentSize];
		priorityQueue[currentSize]=null;
		currentSize--;
		
		int subSize=1;
		
		
		if(ham==false)
		{
		          // while the parent is greater than one of the children, swap and move 
		while( (subSize*2<currentSize) && (  (priorityQueue[subSize].manhattan()>priorityQueue[subSize*2].manhattan()) || ( priorityQueue[subSize].manhattan()>priorityQueue[(subSize*2)+1].manhattan() )  ) )
		{
			
			
			if( (priorityQueue[(subSize*2)+1].manhattan()>=priorityQueue[subSize*2].manhattan() ) && ((subSize*2)+1 <=currentSize) )
			{
				Board temp=priorityQueue[subSize].copy();
				priorityQueue[subSize]=priorityQueue[subSize*2].copy();
				priorityQueue[subSize*2]=temp.copy();
				subSize=subSize*2;
				
			}
			
			
			else if( (priorityQueue[(subSize*2)+1].manhattan()<priorityQueue[subSize*2].manhattan() ) && ((subSize*2)+1 <=currentSize) )
			{
				Board temp=priorityQueue[subSize].copy();
				priorityQueue[subSize]=priorityQueue[(subSize*2)+1].copy();
				priorityQueue[(subSize*2)+1]=temp.copy();
				subSize=(subSize*2)+1;
				
			}
			
			if(subSize*2==currentSize)
			{
				if(priorityQueue[subSize].manhattan()>priorityQueue[currentSize].manhattan())
				{
					Board temp=priorityQueue[subSize].copy();
					priorityQueue[subSize]=priorityQueue[currentSize].copy();
					priorityQueue[currentSize]=temp.copy();
					subSize=currentSize;
				}
				subSize=currentSize+2;
				
			}
			
			
			
		}
		
	}
		
		else
		{
			while( (subSize*2<currentSize) && (  (priorityQueue[subSize].hamming()>priorityQueue[subSize*2].hamming()) || ( priorityQueue[subSize].hamming()>priorityQueue[(subSize*2)+1].hamming() )  ) )
			{
				
				
				if( (priorityQueue[(subSize*2)+1].hamming()>=priorityQueue[subSize*2].hamming() ) && ((subSize*2)+1 <=currentSize) )
				{
					Board temp=priorityQueue[subSize].copy();
					priorityQueue[subSize]=priorityQueue[subSize*2].copy();
					priorityQueue[subSize*2]=temp.copy();
					subSize=subSize*2;
					
				}
				
				
				else if( (priorityQueue[(subSize*2)+1].hamming()<priorityQueue[subSize*2].hamming() ) && ((subSize*2)+1 <=currentSize) )
				{
					Board temp=priorityQueue[subSize].copy();
					priorityQueue[subSize]=priorityQueue[(subSize*2)+1].copy();
					priorityQueue[(subSize*2)+1]=temp.copy();
					subSize=(subSize*2)+1;
					
				}
				
				if(subSize*2==currentSize)
				{
					if(priorityQueue[subSize].hamming()>priorityQueue[currentSize].hamming())
					{
						Board temp=priorityQueue[subSize].copy();
						priorityQueue[subSize]=priorityQueue[currentSize].copy();
						priorityQueue[currentSize]=temp.copy();
						subSize=currentSize;
					}
					subSize=currentSize+2;
					
				}
				
				
				
			}
			
		}
		//System.out.println("Board taken off");
		//System.out.println(min.toString());
		
		return min;
	}
	
	
	public void printQueue()
	{
		
		System.out.println("Queue contents");
		for(int i=1;i<=currentSize;i++)
		{
			System.out.println(priorityQueue[i].toString());
			System.out.println("hamming: "+priorityQueue[i].hamming() + " manhattan "+ priorityQueue[i].hamming());
		}
		System.out.println("end of queue");
	}
}
