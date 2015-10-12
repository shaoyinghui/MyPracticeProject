package pkg2;

public class MinHeap {
    private int[] Heap;
    private int size;
    private int number;
  
    public MinHeap(int[] h,int n,int max){
    	Heap = h;
    	number = n;
    	size = max;
    	buildheap();
        }

    public int heapsize(){
    	return number;
    }
    public boolean isLeaf(int pos){
    	return (pos >= number/2)&&(pos < number);
    }
    public int leftchild(int pos){
    	assert pos < number/2:"Position has no left child";
    	return Heap[2*pos + 1];
    }
    public int rightchild(int pos){
    	assert pos < (number-1/2):"Position has no right child"; 
    	return Heap[2*pos + 2];
    }
    public int parent(int pos){
    	assert pos > 0:"Position has no parrent";
    	return (pos - 1)/2;
    }
    public void buildheap(){
    	for(int i=number/2 - 1 ; i>=0 ; i--)
    		siftdown(i);
    }
    private void siftdown(int pos){
    	assert (pos < number)&&(pos >= 0):"illegal heap position";
    	while(!isLeaf(pos)){
    		int j = leftchild(pos);
    		if(j<(number-1)&&(Heap[j]<Heap[j+1]))
    			j++;
    		if(Heap[pos]>=Heap[j])
    			return;
    		swap(Heap,pos,j);
    		pos = j;
    	}
    }
    public void insert(int val){
    	assert number<size:"Heap is full";
    	int curr=number++;
    	Heap[curr]=val;
    	while(curr!=0 &&
    	Heap[curr] < (Heap[parent(curr)])){
    	swap(Heap,curr,parent(curr));
    	curr=parent(curr);
    	}
    }
    public int remove(){
    	if(number==0) return 0;
    	assert number>0:"Removing from empty heap";
    	swap(Heap,0,--number);
    	if(number!=0)
    	siftdown(0);
    	return Heap[number];
    	}
    public void swap(int[] h,int c,int p){
    	int a = h[c];
    	h[c] = h[p];
    	h[p] = a;
        }
    public void lessthan(int key){
    	assert number>0:"Removing from empty heap";
    	    System.out.println("堆中比"+key+"小的数为");
    	    for(int i=0;i<=number-1;i++){
    		if((number!=0)&&(Heap[i]<key))
    			System.out.println(Heap[i]);
    	}

    }
	public static void main(String args[]){
		int a[]=new int[20];
		MinHeap minHeap = new MinHeap(a,0,20);
		minHeap.insert(2);
		minHeap.insert(3);
		minHeap.insert(11);
		minHeap.insert(9);
		minHeap.insert(6);
		minHeap.insert(13);
		System.out.println(minHeap.isLeaf(3));
		System.out.println(minHeap.parent(13));
		minHeap.lessthan(5);
	}
}
