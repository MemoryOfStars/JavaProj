import java.io.*;
import java.net.*;
import java.util.*;

public class FileServer {
	public static String FileFolder;
	public static String ServerAddress;
	public static int ServerPort;
	
	//try{
		InputStream in = new BufferedInputStream(new FileInputStream(new File("")));//����Ӧ��Ŀ¼���������ļ����洢�ڵ�������Ϣ��
		public static Properties nodeInfo = new Properties();
		nodeInfo.load(in);
	/*}catch(Exception e){
		e.printStackTrace();
	}*/
	
	public FileServer() throws SocketException{
		FileFolder = "";//�ļ��洢Ŀ¼
		ServerAddress = "";//������IP
		ServerPort = getPortSocket().getPort();//��ȡ�˿�
	}
	
	
	
	
	
	
	public static DatagramSocket getPortSocket() throws SocketException{
		DatagramSocket s = new DatagramSocket(0);
		return s;
	}//��ȡ���еĶ˿ں�
	
	
	
	
	/*public FileServer(int port){
		try{
			
			System.out.println("Server is Ready");
			while(true){
				ServerSocket ss = new ServerSocket(port);
				Socket socket = ss.accept();
				DataInputStream dis = new DataInputStream(socket.getInputStream());
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			}
			
		}
		catch(Exception e){
			
		}
	}*/
	
	
	
	
	public static void main(String[] args) throws SocketException{
		new NwServer(ServerPort,new ThreadPoolSupport(new FileProtocol()));
	}
}
