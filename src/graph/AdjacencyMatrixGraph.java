package graph;

import java.util.ArrayList;
import java.util.Stack;

public class AdjacencyMatrixGraph {
	private int size;
	private String[] vertices;
	private boolean[][] adjacencyMatrix;
	
	public AdjacencyMatrixGraph(String[] args) {
		size = args.length;
		vertices = new String[size];
		System.arraycopy(args, 0, vertices, 0, size);
		adjacencyMatrix = new boolean[size][size];
	}
	
	public void add(String v, String w){
		int i = index(v), j = index(w);
		adjacencyMatrix[i][j] = adjacencyMatrix[j][i] = true;
	}
	
	public String toString(){
		if(size == 0) return "{}";
		StringBuffer buf = new StringBuffer("{" + vertex(0));
		for(int i = 1; i < size; i++)
			buf.append("," + vertex(i));
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
			for(int i = 0; i < size; i++){
				if(adjacencyMatrix[index(x)][i] && !visit[i]){
					visit[i] = true;
					stack.push(vertices[i]);
				}
			}
		}
		
		StringBuffer buf = new StringBuffer("{" + list.get(0));
		for(int i = 1; i < list.size(); i++)
			buf.append(" -> " + list.get(i));
		System.out.println( buf + "}");
	}

	private String vertex(int i) {
		StringBuffer buf = new StringBuffer(vertices[i] + ":");
		for(int j = 0; j < size; j++)
			if(adjacencyMatrix[i][j]) buf.append(vertices[j]);
		return buf + "";
	}

	private int index(String v) {
		for(int i = 0; i < size; i++)
			if(vertices[i].equals(v)) return i;
		return adjacencyMatrix.length;
	}
}
