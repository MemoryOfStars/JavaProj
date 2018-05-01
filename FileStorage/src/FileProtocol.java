import java.net.*;
import java.util.*;
import java.io.*;

//�ṩ��ÿ���ͻ�������Ĵ���
public class FileProtocol implements IOStrategy{
	FileService fs = new FileService();
	public static ThreadLocal tl = new ThreadLocal();
	/*ThreadLocal<String> FN = new ThreadLocal<String>();
	ThreadLocal<Integer> FZ = new ThreadLocal<Integer>();
	ThreadLocal<Boolean> signal = new ThreadLocal<Boolean>();*/
	//public static Map<Integer,Properties> file_number = new HashMap<Integer,Properties>();//�ļ�������ļ����Ķ��ձ�
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
				case 1://�ͻ��˴��ļ�ͷ��������
					/*FN.set(dis.readUTF());
					FZ.set(dis.readInt());
					String FileName = FN.get();//��ȡ�ļ���
					int FileSize = FZ.get();//��ȡ�ļ���С
					Properties nodePro =  fs.Upload();//����ڵ�洢���ļ������ؽڵ����Ϣ
					Properties filePro = new Properties();
					filePro.setProperty("FileName", FileName);
					
					
					filePro.setProperty("FileSize", String.valueOf(FileSize));
					file_number.put(Integer.parseInt(nodePro.getProperty("NodePort")),filePro);//���ļ���ź��ļ���������ձ�
					dos.writeInt(Integer.parseInt(nodePro.getProperty("NodePort")));//���ͽڵ�Ķ˿ڸ��ͻ���(�˿ںż��ļ��ı��)
					dos.writeUTF(nodePro.getProperty("NodeIP"));//���ͽڵ��IP��ַ
					dos.flush();*/
					try{
						temp = new StorageNode();
						FileServer.locateNewNode(temp);//���½����Ľڵ���������ļ�
						file_id = dis.readUTF();//��ȡ�ļ�����
						file = new File(FileServer.FileFolder,file_id);//�ø�Ŀ¼���ļ�������µ��ļ�ʵ��
						fos2 = new FileOutputStream(file);
						bos2 = new BufferedOutputStream(fos2);
						session.put("file_id", file_id);
						session.put("file",file);
						session.put("bos",bos2);
						session.put("fos",fos2);//�ļ���Ϣ���浽session
						
						temp.setProperties(session);//��session�����ݱ��浽StorageNode��Properties�ļ���
						
						tl.set(session);
						dos.writeInt(0);
						dos.flush();//�����Ѿ���ȡ����ļ�ͷ�Ļ�Ӧ
					}catch(Exception e){
						dos.writeInt(-1);
						dos.flush();
					}
					
					break;
				case 2://�ͻ��˴����ļ����ݵ�������
					/*String bNumber = dis.readLine();
					fs.Download(bNumber);*/
					try{
						len = dis.readInt();
						buffer = new byte[len];
						dis.readFully(buffer);
						
						temp.writeToFile(dis);//�������������������ڵ���ļ��洢λ��
						
						bos2.write(buffer);
						bos2.flush();
						
						dos.writeInt(0);
						dos.flush();	
					}catch(Exception e){
						dos.writeInt(-1);
						dos.flush();
					}
					break;
				case 3://�ͻ��˷����ļ�β��������(��β����)
					//fs.Delete();
					try{
						fos2.close();
						bos2.close();
						session.remove("fos");
						session.remove("bos");
						
						dos.writeInt(0);
						dos.writeUTF(file_id);//�����ļ���ID���ͻ���
						dos.flush();
					}catch(Exception e){
						dos.writeInt(-1);
						dos.flush();
					}
					break;
				case 4://�ӷ�������ȡ�ļ�
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
				case 5://�ӷ������õ�������
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
				case 6://ɾ��ָ���ļ�
					try{
						file_id = dis.readUTF();
						file = new File(FileServer.FileFolder,file_id);
						if(file.exists())file.delete();
					}catch(Exception e){
						dos.writeInt(-1);
						dos.flush();
					}
					break;
				case 7://�ļ�������
					try{
						
					}
					catch(Exception e){
						dos.writeInt(-1);
						dos.flush();
					}
					break;
				case 8://�����ļ�
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
