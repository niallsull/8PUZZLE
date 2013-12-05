
public class ListNode {

	Board thisBoard;   // nodes for doubly linked list
	ListNode nextBoard;
	ListNode prevBoard;
	
	public ListNode(Board t,ListNode n,ListNode p)
	{
		thisBoard=t;
		nextBoard=n;
		prevBoard=p;
	}                                /// and two different constructors, you could just pass in the two nulls really
	
	public ListNode(Board t)
	{
		thisBoard=t;
		nextBoard=null;
		prevBoard=null;
	}
	
}
