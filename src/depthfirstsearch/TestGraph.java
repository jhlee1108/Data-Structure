package depthfirstsearch;

public class TestGraph {

	public static void main(String[] args) {
		AdjacencyMatrixGraph adjacencyMatrixGraph = new AdjacencyMatrixGraph(new String[]{"SE","UK","DE","FR","CZ","CH","AT","IT"});
		System.out.println(adjacencyMatrixGraph);
		adjacencyMatrixGraph.add("SE", "UK");
		adjacencyMatrixGraph.add("SE", "DE");
		adjacencyMatrixGraph.add("UK", "FR");
		adjacencyMatrixGraph.add("DE", "FR");
		adjacencyMatrixGraph.add("DE", "IT");
		adjacencyMatrixGraph.add("DE", "CZ");
		adjacencyMatrixGraph.add("CH", "FR");
		adjacencyMatrixGraph.add("CH", "IT");
		adjacencyMatrixGraph.add("CH", "AT");
		System.out.println(adjacencyMatrixGraph);
		
		adjacencyMatrixGraph.DFS("SE");

		AdjacencyListGraph adjacencyListGraph = new AdjacencyListGraph(new String[]{"SE","UK","DE","FR","CZ","CH","AT","IT"});
		System.out.println(adjacencyListGraph);
		adjacencyListGraph.add("SE", "UK");
		adjacencyListGraph.add("SE", "DE");
		adjacencyListGraph.add("UK", "FR");
		adjacencyListGraph.add("DE", "FR");
		adjacencyListGraph.add("DE", "IT");
		adjacencyListGraph.add("DE", "CZ");
		adjacencyListGraph.add("CH", "FR");
		adjacencyListGraph.add("CH", "IT");
		adjacencyListGraph.add("CH", "AT");
		System.out.println(adjacencyListGraph);
		
		adjacencyListGraph.DFS("SE");

	}

}
