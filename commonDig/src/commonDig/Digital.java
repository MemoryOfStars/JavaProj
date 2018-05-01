package commonDig;

import java.io.*;

public class Digital {

	public  static boolean isCommon(int dig){
		boolean flag = true;
		for(int i = 2;i <= Math.sqrt(dig);i++)
		{
			if(dig%i==0){
				flag = false;
				break;
			}
		}
		return flag;
	}
	
	public static void FileWriteSystem(String message) throws IOException
	{
		try{
			File myFilePath = new File("F:\\大二小学期\\File.txt");
			if(!myFilePath.exists()){
				myFilePath.createNewFile();
			}
			FileWriter fw = new FileWriter("F:\\大二小学期\\File.txt");
			fw.write(message);
			fw.flush();
			fw.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}//写message进文件
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
			String mes = "";
			for(int i = 2;i < 10000;i++)
			{
				if(isCommon(i))
				{
					mes = mes + "\n" + i;
				}
			}
			FileWriteSystem(mes);
		}
}
