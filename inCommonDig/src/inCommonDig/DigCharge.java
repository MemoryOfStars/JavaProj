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
			int o = dig/100;//��λ
			int g = dig/10;//��λ��ʮλ
			int m = dig%100;//ʮλ�͸�λ
			int n = dig%10;//��λ
			int l = m/10;//ʮλ
			if(isCommon(o)&&isCommon(l)&&isCommon(n))return true;//��λ��ʮλ����λ
			if(isCommon(g)&&isCommon(n))return true;//��λʮλ����λ
			if(isCommon(o)&&isCommon(m)&&(l!=0))return true;//��λ��ʮλ��λ
			return false;
			
		}
		else if(isCommon(dig)&&dig > 1000&&dig < 10000)
		{
			int s = dig/1000;//ǧλ
			int o = dig/100;//ǧλ�Ͱ�λ
			int g = dig/10;//ǧλ�Ͱ�λ��ʮλ
			int j = dig%1000;//��λʮλ��λ
			int b = j/100;//��λ
			int m = dig%100;//ʮλ�͸�λ
			int n = dig%10;//��λ
			int l = m/10;//ʮλ
			int a = j/10;//��λʮλ
			if(isCommon(s)&&isCommon(b)&&isCommon(n)&&isCommon(l))return true;//ǧλ����λ��ʮλ����λ
			if(isCommon(s)&&isCommon(a)&&isCommon(n)&&(b!=0))return true;//ǧλ����λʮλ����λ
			if(isCommon(s)&&isCommon(b)&&isCommon(m)&&(l!=0))return true;//ǧλ����λ��ʮλ��λ
			if(isCommon(o)&&isCommon(l)&&isCommon(n))return true;//ǧλ��λ��ʮλ����λ
			if(isCommon(g)&&isCommon(n))return true;//ǧλ��λʮλ����λ
			if(isCommon(o)&&isCommon(m)&&(l!=0))return true;//ǧλ��λ��ʮλ��λ
			if(isCommon(s)&&isCommon(j)&&(b!=0))return true;//ǧλ����λʮλ��λ
			return false;
		}
		return false;
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File f = new File("F:\\���Сѧ��\\common.txt");
		if(!f.exists())f.createNewFile();
		FileWriter fw = new FileWriter("F:\\���Сѧ��\\common.txt");
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
