
public class Stack {

	
	Board[] theStack;
	int length;
	
	
	public Stack(int l)
	{
		
		
		theStack=new Board[l];
		length=0;
	}
	
	public Boolean isEmpty()
	{
		Boolean empty=false;
		
		if(length==0)
			empty=true;
		
		return empty;
		
	}
	
	public void Add(Board b)
	{
		
		
		length++;
		
		if(length==theStack.length)   // if we start running out of space
		{
			Board[] sub=new Board[theStack.length];
			
			System.arraycopy(theStack, 0, sub, 0, theStack.length);
			
			theStack=new Board[sub.length+40];
			
			System.arraycopy(sub, 0, theStack, 0, sub.length);
			
			
		
		}
		
		theStack[length]=b.copy();
		
	}
	
	public Board Remove()
	{
		
		Board b=theStack[length].copy();
		theStack[length]=null;
		length--;
		return b;
		
		
	}
	
}
