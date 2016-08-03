package kruskal;

import java.util.Arrays;
import java.util.LinkedList;

public class WeightedGraph {
	private int size;
	private String[] vertices;
	private Node[] adjacencyList;
	private LinkedList<Edge> edges;
	private int[] unionArray;
	
	public WeightedGraph(String[] args){
		size = args.length;
		vertices = new String[size];
		System.arraycopy(args, 0, vertices, 0, size);
		adjacencyList = new Node[size];
		for(int i = 0; i < size; i++)
			adjacencyList[i] = new Node(vertices[i],null,0);
		edges = new LinkedList<Edge>();
		unionArray = new int[size];
	}
	
	public void add(String v, String w, int weight){
		adjacencyList[index(v)].next = new Node(w,adjacencyList[index(v)].next,weight);
		adjacencyList[index(w)].next = new Node(v,adjacencyList[index(w)].next,weight);
		edges.add(new Edge(v,w,weight));
	}
	
	public void kruskal(){
		LinkedList<Edge> treeEdges = new LinkedList<Edge>();
		Arrays.fill(unionArray, -1);
		int i = 0;
		sortEdge();
		while(treeEdges.size() < size - 1){
			Edge e = edges.get(i);
			if(!checkCycle(e)){
				treeEdges.add(e);
				weightedUnion(e.left,e.right);
			}
			i++;
		}
		
		printTreeEdges(treeEdges);
	}
	
	private void printTreeEdges(LinkedList<Edge> treeEdges) {
		int minCost = treeEdges.getFirst().weight;
		StringBuffer buf = new StringBuffer(treeEdges.getFirst().left + "->" + treeEdges.getFirst().right);
		
		for(int i = 1; i < treeEdges.size(); i++){
			minCost += treeEdges.get(i).weight;
			buf.append(", " + treeEdges.get(i).left + "->" + treeEdges.get(i).right);
		}
		
		System.out.println("min cost = " + minCost);
		System.out.println("used edge = " + buf);
	}

	private void weightedUnion(String left, String right) {
		if(unionArray[index(left)] < unionArray[index(right)]){
			unionArray[index(left)] += (unionArray[index(right)] + 1);
			unionArray[index(right)] = index(left);
		}
		else{
			unionArray[index(right)] += (unionArray[index(left)] + 1);
			unionArray[index(left)] = index(right);
		}
		
	}
	
	private Object collapsingFind(String s) {
		int x = index(s);
		int count = 0;
		while(unionArray[x] >= 0){
			x = unionArray[x];
			count++;
		}
		int y = index(s);
		while(count > 0){
			int temp = unionArray[y];
			unionArray[y] = x;
			y = temp;
			count--;
		}
		return x;
	}

	private boolean checkCycle(Edge e) {
		if(collapsingFind(e.left) == collapsingFind(e.right))
			return true;
		return false;
	}

	private void sortEdge() {
		for(int i = 0; i < edges.size() - 1; i++){
			int min = i;
			for(int j = i + 1; j < edges.size(); j++){
				if(edges.get(min).weight > edges.get(j).weight) min = j;
			}
			Edge temp = edges.get(i);
			edges.set(i, edges.get(min));
			edges.set(min, temp);
		}
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
	
	private class Edge{
		String left;
		String right;
		int weight;
		
		public Edge(String left, String right, int weight) {
			super();
			this.left = left;
			this.right = right;
			this.weight = weight;
		}
		
	}
}
