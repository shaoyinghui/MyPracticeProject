package zuoye;

interface List{
   boolean search(int x);
   boolean insert(int x);
   int delete(int x);
   int successor(int x);//��ø����Ա���xԪ�ص�ֱ�Ӻ��Ԫ��
   int predecessor(int x);//��ø����Ա���xԪ�ص�ֱ��ǰ��Ԫ��
   int minimum();//��ø����Ա����СԪ��
   int maximum();//��ø����Ա�����Ԫ��

  }
public class IntSLList implements List{
 
 private IntNode head,tail;  
 
 public IntSLList(){        
  head=tail=null;
 }
 public boolean isEmpty(){    
  return head==null;           }
 
 public boolean search(int el){                 
	 IntNode temp;                                 
	  for(temp=head;temp!=null && temp.info!=el;temp=temp.next);  
	  return temp!=null;             
	 }
 
 public boolean insert(int el){    
	 if(!isEmpty()){                       
   tail.next=new IntNode(el);    
   tail=tail.next;                       
  }else{
   head=tail=new IntNode(el);     
  }
  return true;
 }
 
 public int deleteFromHead(){   
  int el=head.info;                      
  if(head==tail){                      
   head=tail=null;                  
  }else{
   head=head.next;                 
  }
  return el;                     
 }
 
 public int deleteFromTail(){   
  int el=tail.info;                    
  if(head==tail){                   
   head=tail=null;               
  }else{
   IntNode temp;                
   for(temp=head;temp.next!=tail;temp=temp.next);                                                                         
   tail=temp;                                            
   tail.next=null;                                       
  }
  return el;                                                                  
 }
 public void printAll(){                                                   
  if(isEmpty()){                                                        
   System.out.println("This list is empty!");             
   return;                                                             
   }
  if(head==tail){                                                
   System.out.println(head.info);                    
   return;
  }
  IntNode temp;                                                  
  for(temp=head;temp!=null;temp=temp.next){      
   System.out.print(temp.info+" ");                            
  }
  System.out.println();                       
 }
 
public int delete(int x){                     
  if(head.info==x && head==tail){      
   head=tail=null;                             
  }else if(head.info==x){                   
   head=head.next;                        
  }else{
   IntNode pred,temp;                      
   for(pred=head,temp=head.next;temp.info!=x && temp.next!=null;pred=pred.next,temp=temp.next);
   pred.next=temp.next;                 
   if(temp==tail){                           
    tail=pred;                              
   }
  }
  return 0;
 }
public int successor(int x){
	 IntNode pred,temp;                     
	   for(pred=head,temp=head.next;temp.info!=x && temp.next!=null;pred=pred.next,temp=temp.next);
	  return temp.next.info;
			 
 }
 
public int predecessor(int x){
	 IntNode pred,temp;                      
	   for(pred=head,temp=head.next;temp.info!=x && temp.next!=null;pred=pred.next,temp=temp.next);
	  return pred.info;
 }
 
public  int minimum(){
	 int mini=head.info;
	 IntNode temp; 
	 for(temp=head ;temp!=tail;temp=temp.next){
	   if(temp.info<mini)
       mini=temp.info; 
	 }
	   return mini;
	   }
public  int maximum(){
	 int maxi=head.info;
	 IntNode temp; 
	 for(temp=head;temp!=null;temp=temp.next){
	   if(temp.info>maxi)
      maxi=temp.info;
	 }
	  return maxi;
 }
public static void main(String[] args){
  IntSLList test=new IntSLList(); 

  System.out.println(test.insert(2));
  System.out.println(test.insert(9));
  System.out.println(test.insert(5));
  System.out.println(test.insert(3));
  System.out.println(test.insert(7));
  System.out.println("��ӡ�������£�");
  test.printAll();
  System.out.println(test.search(6));
  System.out.println("���ֵ��"+test.maximum());
  System.out.println("��Сֵ: "+test.minimum());
 }
}
