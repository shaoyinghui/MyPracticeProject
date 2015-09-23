package list;

interface List{
    boolean search(int x);
    boolean insert(int x);
    int delete(int x);
    int successor(int x);//��ø����Ա���xԪ�ص�ֱ�Ӻ��Ԫ��
    int predecessor(int x);//��ø����Ա���xԪ�ص�ֱ��ǰ��Ԫ��
    int minimum();//��ø����Ա����СԪ��
    int maximum();//��ø����Ա�����Ԫ��
      }

public class DoubleLinkedList implements List{
    protected DoubleNode head;
    protected DoubleNode tail;
    protected DoubleNode previous;
    protected DoubleNode next;
 
    public boolean isEmpty() {
        return head == null;
    }
    
    public boolean search(int x) {
    	DoubleNode node = new DoubleNode(x);
       for(node=head;node==tail;node=node.next){
    	   if(node.info==x)
    		   return true;
       }	   
       return false;
    }
    public void addFirst(int e) {
        DoubleNode node = new DoubleNode(e);
        head.next = node;
        node.previous = head;
        head.previous = null;
        if (tail == null) {
            tail = node;
        }
    }
    public boolean insert(int x) {
        if (head == null) {
     head = new DoubleNode(x);
        }
        if (head.next == null) {
            addFirst(x);
        } else {
            DoubleNode node = new DoubleNode(x);
            tail.next = node;
            node.previous = tail;
            tail = node;
 
        }
       return true;
 
    }
    public void deleteFirst() {
        if (head == null) {
            head.next = null;
            tail = null;
        } else {
            DoubleNode fitst = head.next;
            head.next = fitst.next;
            fitst = null;
        }
 
    }
   public void deleteLast() {
        if (head == null) {
            deleteFirst();
        } else {
            DoubleNode pre = tail.previous;
            pre.next = null;
            tail = pre;
        }
    }
 
    public int delete(int x) {
       
        if (search(x)==false) {
           System.out.println("�������");
        } 
        else if (x==tail.info) {
            deleteLast();
 
        } else {
            DoubleNode prior = head.next;
            while (search(x)) {
                prior = prior.next;
            }
            DoubleNode delete = prior;
            DoubleNode pre = delete.previous;
            DoubleNode after = delete.next;
            pre.next = delete.next;
            after.previous = pre;
            delete = null;
 
        }
        return 0;
    }
 
   public void print() {
        DoubleNode first = head.next;
        if (first == null) {
            System.out.println("null");
        } else {
 
            while (first != null) {
                System.out.println(first.info);
                first = first.next;
            }
        }
     }
 
    public void reverse() {
        if (tail == null) {
            System.out.println("null");
        } else {
 
            while (tail.previous != null) {
                System.out.println(tail.info);
                tail = tail.previous;
            }
        }
   }

    public int successor(int x){
    	//��ø����Ա���xԪ�ص�ֱ�Ӻ��Ԫ��
    	DoubleNode node = new DoubleNode(x);
        for(node=head;node!=tail;node=node.next){
     	 while(node.info==x)
     		 break;}
        return node.info;
            }
  
    public int predecessor(int x){
    	//��ø����Ա���xԪ�ص�ֱ��ǰ��Ԫ��
    	DoubleNode node = new DoubleNode(x);
        for(node=head;node!=tail;node=node.next){
     	 while(node.info==x)
     		 break;}
        return node.info;
            }
   
   public  int minimum(){
    	//��ø����Ա����СԪ��
    	DoubleNode temp;
    	int mini=head.info;
        for(temp=head;temp!=null;temp=temp.next){
     	   if(temp.info<mini)
     		 mini=temp.info;}
        return mini;
            }
       public int maximum(){       //��ø����Ա�����Ԫ��
    	DoubleNode temp;
    	int maxi=head.info;
        for(temp=head;temp!=null;temp=temp.next){
     	   if(temp.info>maxi)
     		 maxi=temp.info;}
        return maxi;
    }


        public static void main(String[] args) {
        DoubleLinkedList list = new DoubleLinkedList();
        list.insert(7);
        list.insert(5);
        list.insert(8);
        list.insert(3);
        list.insert(11);
        System.out.println("�����ӡ�������£�");
        list.reverse();
        System.out.println("���ֵ"+list.maximum());
        System.out.println("��Сֵ"+list.minimum());
    }
}