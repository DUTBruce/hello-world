// 13/17С������100λ�������Ǽ���
public class FractionalDigit {

	public static void main(String[] args) {
		int d = 13;
		int q = 17;
		int a = 0;
		a = d / q; //����λ
		d = d % q * 10;
		for(int i=1; i<=100; i++)//ѭ��i�������С������iλ
		{
			a = d / q;
			d = d % q * 10;
			System.out.print(a);
		}
		//System.out.println(a);

	}

}
