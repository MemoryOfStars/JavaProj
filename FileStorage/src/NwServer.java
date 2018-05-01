import java.net.*;


public class NwServer {
	public NwServer(int port,IOStrategy ios){
		try{
			ServerSocket ss = new ServerSocket(port);
			System.out.println("Server is Ready");
			new Thread(){
				public void run(){
					try{
						while(true){
							Socket socket = ss.accept();
							ios.service(socket);
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}.start();
			new Thread(){
				public void run(){
					try{//建立UDP协议,用新的线程进行与StorageNode的通信
						
						DatagramSocket dataSocket = null;
						DatagramPacket dataPacket = null;
						String message = null;
						StorageNode sn = null;
						
						
						
						for(StorageNode& sn : ){
							InetSocketAddress id = new InetSocketAddress(sn.getAddress(),sn.getPort());//获取节点的相关信息
							dataSocket = new DatagramSocket(id);
							byte buf[] = new byte[1024];
							dataPacket = new DatagramPacket(buf,buf.length);
							dataSocket.receive(dataPacket);//接收到客户端传送过来的信息
							buf = dataPacket.getData();
							InetAddress addr = dataPacket.getAddress();
							int port = dataPacket.getPort();
						
							//SocketAddress toAddress = dataPacket.getSocketAddress();
							this.sleep(300);
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}.start();
			
			
			
			
		}
		catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}
	}
}
