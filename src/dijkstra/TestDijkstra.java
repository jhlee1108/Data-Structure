package dijkstra;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestDijkstra {
	
	public static void main(String[] args) throws FileNotFoundException{
		Scanner sc = new Scanner(new File("input.txt"));
		int size = sc.nextInt();
		String[] vertices = new String[size];
		
		for(int i = 0; i < size; i++){
			vertices[i] = sc.next();
		}
		
		WeightedGraph weightedGraph = new WeightedGraph(vertices);
		System.out.println(weightedGraph);
		
		while(sc.hasNext()){
			weightedGraph.add(sc.next(), sc.next(), sc.nextInt());
		}
		
		sc.close();
		
		System.out.println(weightedGraph);
		
		weightedGraph.findShortestPaths("A");
	}
}
