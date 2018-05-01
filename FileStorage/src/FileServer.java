import java.io.*;
import java.net.*;
import java.util.*;

public class FileServer {
	public static String FileFolder;
	public static String ServerAddress;
	public static int ServerPort;
	
	//try{
		InputStream in = new BufferedInputStream(new FileInputStream(new File("")));//从相应的目录读出配置文件（存储节点的相关信息）
		public static Properties nodeInfo = new Properties();
		nodeInfo.load(in);
	/*}catch(Exception e){
		e.printStackTrace();
	}*/
	
	public FileServer() throws SocketException{
		FileFolder = "";//文件存储目录
		ServerAddress = "";//服务器IP
		ServerPort = getPortSocket().getPort();//获取端口
	}
	
	
	
	
	
	
	public static DatagramSocket getPortSocket() throws SocketException{
		DatagramSocket s = new DatagramSocket(0);
		return s;
	}//获取空闲的端口号
	
	
	
	
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
