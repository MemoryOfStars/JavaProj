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
					try{//����UDPЭ��,���µ��߳̽�����StorageNode��ͨ��
						
						DatagramSocket dataSocket = null;
						DatagramPacket dataPacket = null;
						String message = null;
						StorageNode sn = null;
						
						
						
						for(StorageNode& sn : ){
							InetSocketAddress id = new InetSocketAddress(sn.getAddress(),sn.getPort());//��ȡ�ڵ�������Ϣ
							dataSocket = new DatagramSocket(id);
							byte buf[] = new byte[1024];
							dataPacket = new DatagramPacket(buf,buf.length);
							dataSocket.receive(dataPacket);//���յ��ͻ��˴��͹�������Ϣ
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
