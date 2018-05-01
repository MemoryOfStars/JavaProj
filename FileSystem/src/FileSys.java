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
			String message = FileInputSystemDemo("F:\\���Сѧ��\\a.txt");
			//ÿ�н����л��з�
			String [] mesMatr = new String[8];
			for(int i = 0;i < 8;i++)
			{
				mesMatr[i] = message.substring(i*10, i*10 + 8);
			}
			int result = 0,counter = 0;
			//�������
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
			System.out.println("������ࣺ" + result);
			result = 0;counter = 0;
			//�������
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
			System.out.println("������ࣺ" + result);
			//б�����
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
			System.out.println("б����ࣺ" + result);
		}
}
