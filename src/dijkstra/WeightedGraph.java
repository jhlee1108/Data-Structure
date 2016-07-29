package dijkstra;

public class WeightedGraph {
	private int size;
	private String[] vertices;
	private Node[] adjacencyList;
	
	public WeightedGraph(String[] args){
		size = args.length;
		vertices = new String[size];
		System.arraycopy(args, 0, vertices, 0, size);
		adjacencyList = new Node[size];
		for(int i = 0; i < size; i++)
			adjacencyList[i] = new Node(vertices[i],null,0);
	}
	
	public void add(String v, String w, int weight){
		adjacencyList[index(v)].next = new Node(w,adjacencyList[index(v)].next,weight);
		adjacencyList[index(w)].next = new Node(v,adjacencyList[index(w)].next,weight);
	}
	
	public String toString(){
		if(size == 0) return "{}";
		StringBuffer buf = new StringBuffer("{" + adjacencyList[0].vertex + adjacencyList[0].weigth + ":");
		for(Node p = adjacencyList[0].next; p != null; p = p.next){
			buf.append(p.vertex + p.weigth);
		}
		for(int i = 1; i < size; i++){
			buf.append(", " + adjacencyList[i].vertex + adjacencyList[i].weigth + ":");
			for(Node p = adjacencyList[i].next; p != null; p = p.next){
				buf.append(p.vertex + p.weigth);
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
		int weigth;
		
		public Node(String vertex, Node next, int weigth) {
			this.vertex = vertex;
			this.next = next;
			this.weigth = weigth;
		}
	}
	
}
