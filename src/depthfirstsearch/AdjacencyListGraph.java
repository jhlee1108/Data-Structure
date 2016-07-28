package depthfirstsearch;

import java.util.ArrayList;
import java.util.Stack;

public class AdjacencyListGraph {
	private int size;
	private String[] vertices;
	private Node[] adjacencyList;
	
	public AdjacencyListGraph(String[] args) {
		size = args.length;
		vertices = new String[size];
		System.arraycopy(args, 0, vertices, 0, size);
		adjacencyList = new Node[size];
		for(int i = 0; i < size; i++)
			adjacencyList[i] = new Node(vertices[i],null);
	}

	public void add(String v, String w){
		adjacencyList[index(v)].next = new Node(w,adjacencyList[index(v)].next);
		adjacencyList[index(w)].next = new Node(v,adjacencyList[index(w)].next);
	}
	
	public String toString(){
		if(size == 0) return "{}";
		StringBuffer buf = new StringBuffer("{" + adjacencyList[0].vertex);
		StringBuffer buf2 = new StringBuffer(":");
		for(Node p = adjacencyList[0].next; p != null; p = p.next){
			buf2.append(p.vertex);
		}
		buf.append(buf2);
		for(int i = 1; i < size; i++){
			buf.append(", " + adjacencyList[i].vertex);
			buf2 = new StringBuffer(":");
			for(Node p = adjacencyList[i].next; p != null; p = p.next){
				buf2.append(p.vertex);
			}
			buf.append(buf2);
		}
		
		return buf + "}";
	}
	
	public void DFS(String v){
		Stack<String> stack = new Stack<String>();
		ArrayList<String> list = new ArrayList<String>();
		boolean[] visit = new boolean[size];
		
		visit[index(v)] = true;
		stack.push(vertices[index(v)]);
		
		while(!stack.isEmpty()){
			String x = stack.pop();
			list.add(x);
			for(Node p = adjacencyList[index(x)]; p != null; p = p.next){
				if(!visit[index(p.vertex)]){
					visit[index(p.vertex)] = true;
					stack.push(p.vertex);
				}
			}
		}
		
		StringBuffer buf = new StringBuffer("{" + list.get(0));
		for(int i = 1; i < list.size(); i++)
			buf.append(" -> " + list.get(i));
		System.out.println( buf + "}");
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
