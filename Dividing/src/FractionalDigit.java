// 13/17小数点后第100位的数字是几？
public class FractionalDigit {

	public static void main(String[] args) {
		int d = 13;
		int q = 17;
		int a = 0;
		
		for(int i = 0;i < 99;i++)
		{
			if(d < q)
				d *= 10;
			d %= q;
		}
		if(d < q)
			d *= 10;
		a = d/q;
		System.out.println(a);

	}

}
