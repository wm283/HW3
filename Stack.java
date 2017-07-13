package a3_1;

public class Stack implements S {
	private Node top;
	private int length;
	
	public Stack() { length = 0; }
	
	public void push(String s) {
		Node temp = new Node(s);
		if(isEmpty()) { top = temp; }
		else {
		temp.setNext(top);
		top = temp;
		}
		length++;
	}
	
	public String pop() throws WrongFormatException{
		if (isEmpty()) throw new WrongFormatException();
		String temp = top.getNode();
		top = top.getNext();
		length--;
		return temp;
	}
	
	public int getLength() {
		return this.length;
	}
	
	public boolean isEmpty() {
		return length == 0;
	}

}
