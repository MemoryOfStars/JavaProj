package inCommonDig;

import java.io.*;

public class DigCharge {
	public static boolean isCommon(int dig){
		boolean flag = true;
		if(dig==0||dig==1)return false;
		for(int i = 2;i <= Math.sqrt(dig);i++)
		{
			if(dig%i==0){
				flag = false;
				break;
			}
		}
		return flag;
	}
	
	public static boolean isTrueCommon(int dig){
		if(isCommon(dig)&&dig > 10&&dig < 100)
		{
			int o = dig/10;
			int g = dig%10;
			if(isCommon(o)&&isCommon(g))return true;
		}
		else if(isCommon(dig)&&dig > 100&&dig < 1000)
		{
			int o = dig/100;//百位
			int g = dig/10;//百位和十位
			int m = dig%100;//十位和个位
			int n = dig%10;//个位
			int l = m/10;//十位
			if(isCommon(o)&&isCommon(l)&&isCommon(n))return true;//百位、十位、个位
			if(isCommon(g)&&isCommon(n))return true;//百位十位、个位
			if(isCommon(o)&&isCommon(m)&&(l!=0))return true;//百位、十位个位
			return false;
			
		}
		else if(isCommon(dig)&&dig > 1000&&dig < 10000)
		{
			int s = dig/1000;//千位
			int o = dig/100;//千位和百位
			int g = dig/10;//千位和百位和十位
			int j = dig%1000;//百位十位个位
			int b = j/100;//百位
			int m = dig%100;//十位和个位
			int n = dig%10;//个位
			int l = m/10;//十位
			int a = j/10;//百位十位
			if(isCommon(s)&&isCommon(b)&&isCommon(n)&&isCommon(l))return true;//千位、百位、十位、个位
			if(isCommon(s)&&isCommon(a)&&isCommon(n)&&(b!=0))return true;//千位、百位十位、个位
			if(isCommon(s)&&isCommon(b)&&isCommon(m)&&(l!=0))return true;//千位、百位、十位个位
			if(isCommon(o)&&isCommon(l)&&isCommon(n))return true;//千位百位、十位、个位
			if(isCommon(g)&&isCommon(n))return true;//千位百位十位、个位
			if(isCommon(o)&&isCommon(m)&&(l!=0))return true;//千位百位、十位个位
			if(isCommon(s)&&isCommon(j)&&(b!=0))return true;//千位、百位十位个位
			return false;
		}
		return false;
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File f = new File("F:\\大二小学期\\common.txt");
		if(!f.exists())f.createNewFile();
		FileWriter fw = new FileWriter("F:\\大二小学期\\common.txt");
		String mes = "";
		for(int i = 2;i < 10000;i++)
		{
			if(isTrueCommon(i))mes += i+"\n" ;
		}
		fw.write(mes);
		fw.flush();
		fw.close();
	}

}
