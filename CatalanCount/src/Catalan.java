
public class Catalan {
	
	public static int answers = 0;
	
	//ÇëÊµÏÖgoº¯Êý
	public static void go(Deque from, Deque to, Stack s) {
			if(from.size()>0)
			{
				s.push(from.getFirst());
			
				from.removeFirst();
				go(from,to,s);

				from.addFirst(s.pop());

			}
		
			if(!s.empty())
			{
				to.addLast(s.pop());
				go(from,to,s);
				s.push(to.getLast());
				to.removeLast();

			}
			if(s.empty()&&(from.size()==0))answers++;
		
	}

	public static void main(String[] args) {
		Deque from = new Deque();
		Deque to = new Deque();
		Stack s = new Stack();
		
		for(int i=1;i<=7;i++) {
			from.addLast(i);
		}
		
		go(from, to, s);
		
		System.out.println(answers);
		

	}

}
