
public class Catalan {
	
	public static int answers = 0;
	
	//请实现go函数
	public static void go(Deque from, Deque to, Stack s) {
		if(from.size() > 0) //若限制栈大小为4，则加入条件 && s.size() < 4
		{
			Deque f1 = from.clone();
			Deque t1 = to.clone();
			Stack s1 = s.clone();
			s1.push(f1.getFirst());
			f1.removeFirst();
			go(f1, t1, s1);
		}
		if(s.size() >0)
		{
			Deque f2 = from.clone();
			Deque t2 = to.clone();
			Stack s2 = s.clone();
			int pop = s2.pop();
			t2.addLast(pop);
			go(f2, t2, s2);
		}
		if(from.size()==0 && s.size()==0)
			answers++;
	}
	 /*public static void go(int deq, int sta) {
	        if(deq>0 &&sta<maxStackSize) {  //仅此行比问题1多了一个限制，maxStackSize=4
	            go(deq-1, sta+1);
	        }
	        if(sta>0) {
	            go(deq, sta-1);
	        }
	        if(deq==0&&sta==0) {
	            answers++;
	        }
	    }
	--------------------- 
	作者：jiangyu98 
	来源：CSDN 
	原文：https://blog.csdn.net/qq_35558510/article/details/81591974 
	版权声明：本文为博主原创文章，转载请附上博文链接！
	*/
	public static void main(String[] args) {
		Deque from = new Deque();
		Deque to = new Deque();
		Stack s = new Stack();
		
		for(int i=1;i<=7;i++) {
			from.addLast(i);
		}
		
		go(from, to, s);
		//go(7,0);
		
		System.out.println(answers);
		

	}

}
