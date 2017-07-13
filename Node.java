package a3_1;

public class Node {
	private String node;
	private Node next;
	
	public Node(String node, Node next) {
		this.node = node;
		this.next = next;
	}
	public Node(String node) {
		this(node, null);
	}
	public Node() {
		this(null, null);
	}
	
	public String getNode() { return this.node; }
	public void setNode(String node) { this.node = node; }
	public Node getNext() { return this.next; }
	public void setNext(Node next) { this.next = next; }

}
