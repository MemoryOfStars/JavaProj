import java.io.*;
import java.util.ArrayList;

public class SameFileSearch {
	public static boolean Oflag = true;
	public static ArrayList<File> filelist = new ArrayList<File>();
	public static void Find(File ff,String path){
		File file = new File(path);
		if(file.exists()){
			File [] files = file.listFiles();
			if(files.length == 0)return;
			else{
				for(File it:files){
					if(it.isDirectory()){
						Find(ff,it.getAbsolutePath());
					}
					else{
						boolean flag1 = true;
						boolean flag2 = true;
						if((!ff.equals(it))&&ff.length()==it.length()&&(ff.getAbsolutePath().trim()).substring((ff.getAbsolutePath().trim()).lastIndexOf("\\")+1).equals((it.getAbsolutePath().trim()).substring((it.getAbsolutePath().trim()).lastIndexOf("\\")+1))){
							
							for(File fl:filelist){
								if(it.equals(fl)){
									flag1 = false;

								}
								if(ff.equals(fl)){
									flag2 = false;

								}
							}
							if(flag1){
								System.out.print(it.getAbsolutePath()+' '+ it.length() + 'B' + "    ");
								
								filelist.add(it);
								Oflag = false;
							}
							if(flag2){
								System.out.print(ff.getAbsolutePath()+' ' + ff.length() +'B'+ "    ");
								filelist.add(ff);
								Oflag = false;
							}
						}
					}
				}
			}
		}
	}//查找相同文件
	public static void findSame(File ff,String path){
		if(ff.exists()){
			File[] files = ff.listFiles();
			if(files.length == 0)return;
			else{
				for(File it : files){
					if(it.isDirectory())findSame(it,path);
					else{
						Find(it,path);
						System.out.print('\n');
					}
				}
			}
		}
	}
	public static void main(String [] args){
		File file = new File(args[0]);
		findSame(file,args[0]);
		if(Oflag)System.out.println("并没有相同的文件夹");
	}
}