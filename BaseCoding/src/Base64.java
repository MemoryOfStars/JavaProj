public class Base64 {
	static char[] BASE64TABLE = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T',
			'U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n',
			'o','p','q','r','s','t','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9','+','/'};
	public static String encode(byte[] binaryData) {
		StringBuilder []baseCode = new StringBuilder[(binaryData.length%3 == 0)?((binaryData.length/3)*4):((binaryData.length/3 + 1)*4)];
		//申请字节空间
		String temp = bytes2Hex(binaryData);
		//bytes[]转换成字符串
		String s = "";
		for(int i = 0;i < baseCode.length;i++)
		{
			baseCode[i] = new StringBuilder("00000000");
		}
		for(int i = 0;i < baseCode.length;i ++)
		{
			for(int j = 2;j < 8&&(6*i+j-2)<temp.length();j++)
			{
				baseCode[i].setCharAt(j,temp.charAt(6*i+j-2));
			}
		}//baseCode是编码之后的内容
		for(int i = 0;i < baseCode.length; i++)
		{
			int x = 0;
			if(baseCode[i].charAt(7)=='1')x+=1;
			if(baseCode[i].charAt(6)=='1')x+=2;
			if(baseCode[i].charAt(5)=='1')x+=4;
			if(baseCode[i].charAt(4)=='1')x+=8;
			if(baseCode[i].charAt(3)=='1')x+=16;
			if(baseCode[i].charAt(2)=='1')x+=32;
			s += BASE64TABLE[x];
		}
		return s;
	}
	
	public static byte[] decode(String s) {
		byte []res = new byte[s.length()];
		for(int i = 0,j = 0;i < s.length();i++)
		{
			byte x = indexOfTable(s.charAt(i));
			res[j++] = x;
		}
		String oriCoder = bytes2Hex(res);//未还原的编码字符串
		StringBuilder []rCoder = new StringBuilder[s.length()];
		for(int i = 0;i < rCoder.length;i++)
		{
			rCoder[i] = new StringBuilder("00000000");
		}
		int counter = 2;
		for(int i = 0;(i < rCoder.length)&&(counter < oriCoder.length());i++)
		{
			for(int j = 0;(j < 8)&&(counter < oriCoder.length());j++)
			{
				rCoder[i].setCharAt(j, oriCoder.charAt(counter));
				counter++;
				if((counter%8 == 0))
				{
					counter += 2;
				}
			}
		}
		byte []result = new byte[((rCoder.length/4))*3];
		//rCoder的每一个元素是一个Byte
		for(int i = 0;(i < rCoder.length)&&(i<result.length);i++)
		{
			result[i] = stringToByte(rCoder[i].toString());
		}
		return result;
	}
	public static byte indexOfTable(char c)
	{
		for(byte i = 0;i < BASE64TABLE.length;i++)
		{
			if(BASE64TABLE[i] == c)return i;
		}
		return -1;
	}
	public static byte stringToByte(String s)
	{
		byte x = 0;
		if(s.charAt(0) == '1')
		{
			if(s == "10000000"){x=-1;}
			else 
			{
			if(s.charAt(7)=='0')x+=1;
			if(s.charAt(6)=='0')x+=2;
			if(s.charAt(5)=='0')x+=4;
			if(s.charAt(4)=='0')x+=8;
			if(s.charAt(3)=='0')x+=16;
			if(s.charAt(2)=='0')x+=32;
			if(s.charAt(1)=='0')x+=64;
			x = (byte)~x;
			}
		}//负数
		else
		{
			if(s.charAt(7)=='1')x+=1;
			if(s.charAt(6)=='1')x+=2;
			if(s.charAt(5)=='1')x+=4;
			if(s.charAt(4)=='1')x+=8;
			 if(s.charAt(3)=='1')x+=16;
			 if(s.charAt(2)=='1')x+=32;
			 if(s.charAt(1)=='1')x+=64;
		}//正数
		return x;
	}
	public static String bytes2Hex(byte[] src) {
        if (src == null || src.length <= 0) {   
            return null;   
        } 

        char[] res = new char[src.length * 8]; // 每个byte对应两个字符
        final char hexDigits[] = { '0', '1'};
       for(int i = 0,j=0;i < src.length;i++)
       {
    	   res[j++] = hexDigits[src[i] >> 7 & 0x01];
           res[j++] = hexDigits[src[i] >> 6 & 0x01];
           res[j++] = hexDigits[src[i] >> 5 & 0x01];
           res[j++] = hexDigits[src[i] >> 4 & 0x01];
           res[j++] = hexDigits[src[i] >> 3 & 0x01];
           res[j++] = hexDigits[src[i] >> 2 & 0x01];
    	   res[j++] = hexDigits[src[i] >> 1 & 0x01];
           res[j++] = hexDigits[src[i] & 0x01];
       }
        return new String(res);
    }
	public static void main(String[] args) {
		byte[] a = {1};
		String s = encode(a);
		
		System.out.println(s);
		byte[] b = decode(s);
		for(int i=0;i<b.length;i++) {
			System.out.print(b[i] + " ");
		}
		System.out.println();

	}

}
