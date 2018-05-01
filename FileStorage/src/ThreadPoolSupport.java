import java.net.*;
import java.util.*;

public class ThreadPoolSupport implements IOStrategy{
	private ArrayList threads = new ArrayList();
	private final int INIT_THREADS = 50;
	private final int MAX_THREADS = 100;
	private IOStrategy ios = null;
	public ThreadPoolSupport(IOStrategy ios){
		this.ios = ios;
		for(int i = 0;i < INIT_THREADS;i++){
			IOThread t = new IOThread(ios);
			t.start();
			threads.add(t);
		}
		try{
			Thread.sleep(300);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void service(Socket socket){
		IOThread t = null;
		boolean found = false;
		for(int i = 0;i < threads.size();i++){
			t = (IOThread)threads.get(i);
			if(t.isIdle()){
				found = true;
				break;
			}
		}
		if(!found){
			t = new IOThread(ios);
			t.start();
			try{
				Thread.sleep(300);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			threads.add(t);
		}
		t.setSocket(socket);
		
	}
}
