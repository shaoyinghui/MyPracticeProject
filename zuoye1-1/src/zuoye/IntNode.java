package zuoye;


public class IntNode {  
	 
	 int info;             
	 IntNode next;  
	 
	 public IntNode(int i){   
	  this(i,null);
	 }
	 
	 public IntNode(int i,IntNode n){   
	  info=i;
	  next=n;
	 }
	} 