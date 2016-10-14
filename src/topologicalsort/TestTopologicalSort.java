package topologicalsort;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestTopologicalSort {
	
	public static void main(String[] args) throws FileNotFoundException{
		Scanner sc = new Scanner(new File("curriculum.txt"));
		int size = sc.nextInt();
		String[] vertices = new String[size];
		
		for(int i = 0; i < size; i++){
			vertices[i] = sc.next();
		}
		
		Digraph diGraph = new Digraph(vertices);
		System.out.println(diGraph);
		
		while(sc.hasNext()){
			diGraph.add(sc.next(), sc.next());
		}
		
		sc.close();
		
		System.out.println(diGraph);
		
		diGraph.topologicalSort();
	}
}
