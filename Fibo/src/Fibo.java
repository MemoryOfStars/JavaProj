public class Fibo {
	public static void main(String[] args) {
		Fibo f = new Fibo();
		System.out.println(f.fibo1(9)); // 这两种方法哪种效率更高？
		System.out.println(f.fibo2(9));
	}

	public int fibo1(int n) { // 使用方法（函数）递归来实现
		if(n == 1||n == 2)
			return 1;
		else
			return fibo1(n-1)+fibo1(n-2);
	}

	public int fibo2(int n) { // 使用循环来实现
		int a = 2;
		int b = 3;
		if(n == 1||n == 2)
			return 1;
		else
		{
			for(;n>4;n--)
			{
				if(a < b)
				{a = a + b;continue;}
				if(a > b)
				{b = a + b;continue;}
			}
			if(a > b)return a;
			else return b;
		}
	}
}
