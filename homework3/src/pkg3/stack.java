package pkg3;

public class stack {

private int size;
private int number;
private int top;
private int[] array;
private int[] time;
private static int TIME;

	stack(int size){
	setup(size);
}
	public void setup(int s){
	size = s; top = 0; number = 0;array = new int[s];time = new int[s];TIME = 0;
}
	public void clear(){
	top = 0;
}
	public void push(int it){
    	assert number<size:"Heap is full";
       	array[TIME]=it;

    	time[TIME]=TIME;
    	while(TIME!=0 &&
    	time[TIME] < (time[parent(TIME)])){
    	swap(time,TIME,parent(TIME));
    	}
    	System.out.println(array[TIME]+" time="+TIME);
    	TIME++;
}
    public int parent(int pos){
    	assert pos > 0:"Position has no parrent";
    	return (pos - 1)/2;
    }
    public void swap(int[] h,int c,int p){
    	int a = h[c];
    	h[c] = h[p];
    	h[p] = a;
        }
	public boolean isEmpty(){
	return top == 0;
}
	public int pop(){
    	assert !isEmpty():"Empty stack";
    	swap(time,0,--TIME);
    	if(TIME!=0)
    		siftdown(0);
    	return array[TIME];
    	
	}
    private void siftdown(int pos){
    	assert (pos < number)&&(pos >= 0):"illegal heap position";
    	while(pos < number/2){
    		int j = 2*pos + 1;
    		if(j<(number-1)&&(time[j]<time[j+1]))
    			j++;
    		if(time[pos]>=time[j])
    			return;
    		swap(time,pos,j);
    		pos = j;
    	}
    }
	public int getTop(){
    	assert !isEmpty():"Empty stack";
    	System.out.println(array[TIME-1]);
    	return array[TIME-1];
  	}

public static void main(String args[]){
	stack s = new stack(20);
	s.push(2);
	s.push(7);
	s.push(5);
	s.push(11);
	s.push(3);
	s.getTop();
	s.pop();
	s.getTop();
	}
}