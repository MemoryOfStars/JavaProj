import java.io.*;


public class FileSys {
	
		public  static String FileInputSystemDemo(String path) throws IOException{
			File file = new File(path);
			if(!file.exists()||file.isDirectory())
				throw new FileNotFoundException();
			FileInputStream fis = new FileInputStream(file);
			byte[] buf = new byte[1024];
			StringBuffer sb = new StringBuffer();
			while((fis.read(buf))!=-1)
				
			{
				sb.append(new String(buf));
				buf = new byte[1024];
			}
			return sb.toString();
		}
	
		public static void main(String args[]) throws IOException{
			String message = FileInputSystemDemo("F:\\大二小学期\\a.txt");
			//每行结束有换行符
			String [] mesMatr = new String[8];
			for(int i = 0;i < 8;i++)
			{
				mesMatr[i] = message.substring(i*10, i*10 + 8);
			}
			int result = 0,counter = 0;
			//横行最多
			for(int i = 0;i < 8;i++)
			{
				for(int j = 0;j < 8;j++)
				{
					if(mesMatr[i].charAt(j) == '1')counter ++;
					else counter = 0;
					if(counter > result)result = counter;
				}
				counter = 0;
			}
			System.out.println("横行最多：" + result);
			result = 0;counter = 0;
			//纵列最多
			for(int j = 0;j < 8;j++)
			{
				for(int i = 0;i < 8;i++)
				{
					if(mesMatr[i].charAt(j) == '1')counter ++;
					else counter = 0;
					if(counter > result)result = counter;
				}
				counter = 0;
			}
			System.out.println("纵列最多：" + result);
			//斜行最多
			for(int i = 0;i < 8;i++)
			{
				for(int j = 0;(i +j) < 8;j++)
				{
					if(mesMatr[j].charAt(i + j) == '1')counter ++;
					else counter = 0;
					if(counter > result)result = counter;
				}
				counter = 0;
			}
			System.out.println("斜行最多：" + result);
		}
}
