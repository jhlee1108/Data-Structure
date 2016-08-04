package topologicalsort;

import java.util.Stack;

public class Digraph {
	private int size;
	private String[] vertices;
	private Node[] adjacencyList;
	private int[] predecessorCount;
	
	public Digraph(String[] args) {
		size = args.length;
		vertices = new String[size];
		System.arraycopy(args, 0, vertices, 0, size);
		adjacencyList = new Node[size];
		for(int i = 0; i < size; i++)
			adjacencyList[i] = new Node(vertices[i],null);
		predecessorCount = new int[size];
	}

	public void add(String v, String w){
		adjacencyList[index(v)].next = new Node(w,adjacencyList[index(v)].next);
		predecessorCount[index(w)] += 1;
	}
	
	public void topologicalSort() {
		Stack<String> stack = new Stack<String>();
		boolean[] check = new boolean[size];
		
		for (int i = 0; i < size; i++){
			if (predecessorCount[i] == 0 && !check[i]) {
				stack.push(vertices[i]);
				check[i] = true;
			}
		}

		while (!stack.isEmpty()) {
			String vertex = stack.pop();
			for (Node p = adjacencyList[index(vertex)].next; p != null; p = p.next) {
				predecessorCount[index(p.vertex)] += -1;
				if (predecessorCount[index(p.vertex)] == 0) {
					stack.push(p.vertex);
					check[index(p.vertex)] = true;
				}
			}
			System.out.print(vertex + " -> ");
		}

	}
	
	public String toString(){
		if(size == 0) return "{}";
		StringBuffer buf = new StringBuffer("{" + adjacencyList[0].vertex + ":");
		for(Node p = adjacencyList[0].next; p != null; p = p.next){
			buf.append(p.vertex);
		}
		for(int i = 1; i < size; i++){
			buf.append(", " + adjacencyList[i].vertex + ":");
			for(Node p = adjacencyList[i].next; p != null; p = p.next){
				buf.append(p.vertex);
			}
		}
		
		return buf + "}";
	}

	private int index(String v) {
		for(int i = 0; i < size; i++)
			if(vertices[i].equals(v)) return i;
		return adjacencyList.length;
	}

	private class Node{
		String vertex;
		Node next;
		
		public Node(String vertex, Node next){
			this.vertex = vertex;
			this.next = next;
		}
		
	}
}
