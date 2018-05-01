import java.io.*;
import java.util.*;

public class Dictionary {
	
	static Properties p = new Properties();
	public static void Input(String voc,String meaning) throws IOException{
		/*File f = new File("F:\\大二小学期\\Dic.txt");
		if(!f.exists())f.createNewFile();*/
		
		try{
			InputStream in = new BufferedInputStream(new FileInputStream(new File("F:/大二小学期/Dic.properties")));
			p.load(in);
			p.setProperty(voc, meaning);
			FileOutputStream out = new FileOutputStream(new File("F:/大二小学期/Dic.properties"));//输出到指定文件
			p.store(out,"");
			out.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static String Output(String index) throws IOException{
		/*File f = new File("F:\\大二小学期\\Dic.txt");
		char[] result;
		int offset = 0,length = 0;
		if(!f.exists())return null;
		else{
			FileReader fr = new FileReader("F:\\大二小学期\\");
			for(int i = 0;result[offset + length - 1] != '\n';)
			fr.read(result, offset, length);
		}*/
		try{
			InputStream in = new BufferedInputStream(new FileInputStream(new File("F:/大二小学期/Dic.properties")));
			p.load(in);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}

		return p.getProperty(index);

	}
	

	
	public static void main(String args[])throws IOException{
		String result;
		//System.out.println(args[0]);
		if(args[0].equals("input")){Input(args[1],args[2]);}
		else if(args[0].equals("search")){
			result = Output(args[1]);
			System.out.print(result);
		}
	}
	
}
