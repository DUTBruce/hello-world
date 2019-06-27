public class Fibo {
	public static void main(String[] args) {
		Fibo f = new Fibo();
		System.out.println(f.fibo1(9)); // �����ַ�������Ч�ʸ��ߣ�
		System.out.println(f.fibo2(9)); // ѭ����Ч�ʸ��ߣ�����С
	}

	public int fibo1(int n) { // ʹ�÷������������ݹ���ʵ��
		if(n <= 0)
			return 0;
		return (n==1 || n==2)? 1: fibo1(n-1)+fibo1(n-2);
	}

	public int fibo2(int n) { // ʹ��ѭ����ʵ��
		if(n <= 0)
			return 0; 
		int a = 1;//f(n-2)
		int b = 1;//f(n-1)
		int f = 1;//f(n)
		for(int i=3; i<=n; i++)
		{
			f = a+b;
			a = b;
			b = f;
		}
		return f;
	}
}
