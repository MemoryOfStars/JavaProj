
//下面的程序为一个整数栈，请实现整数出栈功能
public class Stack {
	
	private SingleLinkedList list = new SingleLinkedList();
	
	public boolean empty() {
		return list.getSize()==0;
	}
	
	public int size() {
		return list.getSize();
	}
	
	//请实现pop函数，从栈顶返回数据(弹出数据)，要求必须是先进后出，FILO结构
	public int pop() {
		if(list.size == 0)return -1;
		int result = 0;
		Node n = list.head;
		for(;n != null;n = n.next);
		if(n!=null)result = n.data;
		list.remove(list.size-1);
		return result;
	}
	
	//数据进栈操作
	public void push(int data) {  
		list.add(data);//链表尾部位栈顶
		
	}
	
	//栈数据复制
	public Stack clone() {
		Stack s = new Stack();
		for(int i=0;i<list.getSize();i++) {
			s.list.add(list.get(i));
		}
		return s;
	}
	
	public static void main(String[] args) {
		Stack s1 = new Stack();
		s1.push(1);
		s1.push(2);
		s1.push(3);
		
		Stack s2 = s1.clone();
		
		System.out.println(s1.pop());
		System.out.println(s1.pop());
		System.out.println(s1.pop());
		
		System.out.println(s1.size());
		
	}
}
