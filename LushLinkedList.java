
public class LushLinkedList {

	
	
	private final ListNode head=new ListNode(null,null,null);
	ListNode current;
	
	
	public LushLinkedList()
	{
		head.nextBoard=null;
		head.thisBoard=null;
	}
	
	public LushLinkedList(Board t)
	{
		head.thisBoard=t.copy();
		current=head;
		
	}
	
	
	public void add(Board b)
	{
		ListNode node=new ListNode(b.copy());
		node.prevBoard=current;       // whats behind
		node.prevBoard.nextBoard=node;     // flip back , what's next
		node.nextBoard=null;        // dunno ahead yet
		current=node;          // update current
	
	}
	
	public void reAlign()
	{
		current=head;          // point at the head node
	}
	
}
