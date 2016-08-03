package dijkstra;

import java.util.Arrays;

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
	
	public void findShortestPaths(String v){
		boolean[] visit = new boolean[size];
		int[] dist = new int[size];
		Arrays.fill(dist, Integer.MAX_VALUE);
		String[] prev = new String[size];	
		dist[index(v)] = 0;
		prev[index(v)] = null;
		
		while (!isAllVisit(visit)) {
			String x = findSmallestDist(dist,visit);
			for (Node p = adjacencyList[index(x)].next; p != null; p = p.next) {
				if (!visit[index(p.vertex)] && (dist[index(x)] + p.weigth < dist[index(p.vertex)])) {
					dist[index(p.vertex)] = dist[index(x)] + p.weigth;
					prev[index(p.vertex)] = x;
				}
			}

			visit[index(x)] = true;
		}
		
		printPath(prev, dist);
	}
	
	private void printPath(String[] prev, int[] dist) {
		for(int i = 0; i < prev.length; i++){
			if(dist[i] == 0){
				System.out.println(vertices[i] + " : " +"weight" + dist[i] + " / start");
			}
			else{
				StringBuffer buf = new StringBuffer(vertices[i]);
				String prevVertex = prev[i];
				
				while(prevVertex != null){
					buf.append("<-" + prevVertex);
					prevVertex = prev[index(prevVertex)];
				}
				
				System.out.println(vertices[i] + " : " +"weight" + dist[i] + " / " + buf);
			}
		}
		
	}

	private String findSmallestDist(int[] dist, boolean[] visit) {
		int smallestDist = 0;
		for(int i = 0; i < dist.length; i++)
			if(!visit[i]){
				smallestDist = i;
				break;
			}
		
		for(int i = 0; i < dist.length; i++)
			if((dist[i] < dist[smallestDist]) && !visit[i])
				smallestDist = i;
		
		return vertices[smallestDist];
	}

	private boolean isAllVisit(boolean[] visit) {
		for(int i = 0; i < visit.length; i++)
			if(!visit[i]) return false;
		
		return true;
	}

	public String toString(){
		if(size == 0) return "{}";
		StringBuffer buf = new StringBuffer("{" + adjacencyList[0].vertex + adjacencyList[0].weigth + ":");
		
		for(Node p = adjacencyList[0].next; p != null; p = p.next)
			buf.append(p.vertex + p.weigth);
		
		for(int i = 1; i < size; i++){
			buf.append(", " + adjacencyList[i].vertex + adjacencyList[i].weigth + ":");
			for(Node p = adjacencyList[i].next; p != null; p = p.next)
				buf.append(p.vertex + p.weigth);
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
