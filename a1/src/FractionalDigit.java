// 13/17小数点后第100位的数字是几？
public class FractionalDigit {

	public static void main(String[] args) {
		int d = 13;
		int q = 17;
		int a = 0;
		a = d / q; //整数位
		d = d % q * 10;
		for(int i=1; i<=100; i++)//循环i输出的是小数点后第i位
		{
			a = d / q;
			d = d % q * 10;
			System.out.print(a);
		}
		//System.out.println(a);

	}

}
