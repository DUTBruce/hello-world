
public class Catalan {
	
	public static int answers = 0;
	
	//��ʵ��go����
	public static void go(Deque from, Deque to, Stack s) {
		if(from.size() > 0) //������ջ��СΪ4����������� && s.size() < 4
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
	        if(deq>0 &&sta<maxStackSize) {  //�����б�����1����һ�����ƣ�maxStackSize=4
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
	���ߣ�jiangyu98 
	��Դ��CSDN 
	ԭ�ģ�https://blog.csdn.net/qq_35558510/article/details/81591974 
	��Ȩ����������Ϊ����ԭ�����£�ת���븽�ϲ������ӣ�
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
