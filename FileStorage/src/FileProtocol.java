import java.net.*;
import java.util.*;
import java.io.*;

//提供对每个客户端请求的处理
public class FileProtocol implements IOStrategy{
	FileService fs = new FileService();
	public static ThreadLocal tl = new ThreadLocal();
	/*ThreadLocal<String> FN = new ThreadLocal<String>();
	ThreadLocal<Integer> FZ = new ThreadLocal<Integer>();
	ThreadLocal<Boolean> signal = new ThreadLocal<Boolean>();*/
	//public static Map<Integer,Properties> file_number = new HashMap<Integer,Properties>();//文件编号与文件名的对照表
	//Properties storPro = new Properties();
	public void service(Socket s){
		try{
			Map<String,Object> session = new HashMap<String,Object>();
			tl.set(session);
			
			InputStream in = s.getInputStream();
			OutputStream out = s.getOutputStream();
			
			BufferedInputStream bis = new BufferedInputStream(in);
			BufferedOutputStream bos = new BufferedOutputStream(out);
			
			DataInputStream dis = new DataInputStream(bis);
			DataOutputStream dos = new DataOutputStream(bos);
			
			int command = 0;
			int len = 0;
			byte[] buffer = null;
			String file_id = null;
			File file = null;
			FileInputStream fis2 = null;
			BufferedOutputStream bos2 = null;
			FileOutputStream fos2 = null;
			BufferedInputStream bis2 = null;
			StorageNode temp = null;
			
			while(true){
				command = dis.readInt();
				switch(command){
				case 0:break;
				case 1://客户端传文件头给服务器
					/*FN.set(dis.readUTF());
					FZ.set(dis.readInt());
					String FileName = FN.get();//读取文件名
					int FileSize = FZ.get();//读取文件大小
					Properties nodePro =  fs.Upload();//申请节点存储该文件并返回节点的信息
					Properties filePro = new Properties();
					filePro.setProperty("FileName", FileName);
					
					
					filePro.setProperty("FileSize", String.valueOf(FileSize));
					file_number.put(Integer.parseInt(nodePro.getProperty("NodePort")),filePro);//将文件编号和文件名放入对照表
					dos.writeInt(Integer.parseInt(nodePro.getProperty("NodePort")));//发送节点的端口给客户端(端口号即文件的编号)
					dos.writeUTF(nodePro.getProperty("NodeIP"));//发送节点的IP地址
					dos.flush();*/
					try{
						temp = new StorageNode();
						FileServer.locateNewNode(temp);//将新建立的节点放入配置文件
						file_id = dis.readUTF();//获取文件名称
						file = new File(FileServer.FileFolder,file_id);//用父目录和文件名组成新的文件实例
						fos2 = new FileOutputStream(file);
						bos2 = new BufferedOutputStream(fos2);
						session.put("file_id", file_id);
						session.put("file",file);
						session.put("bos",bos2);
						session.put("fos",fos2);//文件信息保存到session
						
						temp.setProperties(session);//将session的数据保存到StorageNode的Properties文件中
						
						tl.set(session);
						dos.writeInt(0);
						dos.flush();//做出已经读取完毕文件头的回应
					}catch(Exception e){
						dos.writeInt(-1);
						dos.flush();
					}
					
					break;
				case 2://客户端传输文件数据到服务器
					/*String bNumber = dis.readLine();
					fs.Download(bNumber);*/
					try{
						len = dis.readInt();
						buffer = new byte[len];
						dis.readFully(buffer);
						
						temp.writeToFile(dis);//将读入的数据流输入进节点的文件存储位置
						
						bos2.write(buffer);
						bos2.flush();
						
						dos.writeInt(0);
						dos.flush();	
					}catch(Exception e){
						dos.writeInt(-1);
						dos.flush();
					}
					break;
				case 3://客户端发送文件尾给服务器(收尾工作)
					//fs.Delete();
					try{
						fos2.close();
						bos2.close();
						session.remove("fos");
						session.remove("bos");
						
						dos.writeInt(0);
						dos.writeUTF(file_id);//返回文件的ID给客户端
						dos.flush();
					}catch(Exception e){
						dos.writeInt(-1);
						dos.flush();
					}
					break;
				case 4://从服务器获取文件
					file_id = dis.readUTF();
					
					file = new File(FileServer.FileFolder,file_id);
					if(file.exists()){
						fis2 = new FileInputStream(file);
						bis2 = new BufferedInputStream(fis2);
						session.put("fis",fis2);
						session.put("bis",bis2);
						dos.writeInt(0);
						dos.flush();
					}else{
						dos.writeInt(-1);
						dos.flush();
					}
					break;
				case 5://从服务器得到数据流
					len = dis.readInt();
					buffer = new byte[len];
					len = bis2.read(buffer);
					
					dos.writeInt(0);
					dos.writeInt(len);
					if(len == -1){
						byte[] bd = new byte[len];
						System.arraycopy(buffer,0, bd, 0, len);
						dos.write(bd);
					}else{
						bis2.close();
						fis2.close();
						session.remove("bis");
						session.remove("fis");
					}
					dos.flush();
					
					break;
				case 6://删除指定文件
					try{
						file_id = dis.readUTF();
						file = new File(FileServer.FileFolder,file_id);
						if(file.exists())file.delete();
					}catch(Exception e){
						dos.writeInt(-1);
						dos.flush();
					}
					break;
				case 7://文件重命名
					try{
						
					}
					catch(Exception e){
						dos.writeInt(-1);
						dos.flush();
					}
					break;
				case 8://复制文件
					try{
						
					}catch(Exception e){
						dos.writeInt(-1);
						dos.flush();
					}
				}
			}
		}
		catch(Exception e){
			System.out.println("Client Disconnected!");
		}
		
	}
}
