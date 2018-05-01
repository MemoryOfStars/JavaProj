import java.net.*;

public class IOThread extends Thread{
	private Socket socket = null;
	private IOStrategy ios = null;
	public IOThread(IOStrategy ios){
		this.ios = ios;
	}
	
	public boolean isIdle(){
		return socket==null;
	}
	
	public synchronized void setSocket(Socket socket){
		this.socket = socket;
		notify();
	}
	
	public synchronized void run(){
		while(true){
			try{
				wait();
				ios.service(socket);
				socket = null;
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
