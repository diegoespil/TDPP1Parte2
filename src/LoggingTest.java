
public class LoggingTest {

	public static void main(String[] args) {
		Graph grafo =  new Graph();
		
		//Agrego los nodos
		grafo.addNode(1);
		grafo.addNode(2);
		grafo.addNode(3);
		grafo.addNode(4);
		grafo.addNode(5);
		
		//Agrego los arcos
		grafo.addEdge(1,2);
		grafo.addEdge(2,4);
		grafo.addEdge(1,3);
		grafo.addEdge(4,1);
		grafo.addEdge(5,2);
		
		//Agrego nodos que ya existen
		grafo.addNode(2);
		grafo.addNode(5);
		
		//Agrego arcos que ya existen
		grafo.addEdge(4,1);
		grafo.addEdge(1,2);
		
		//Agrego arcos en nodos que no existen
		grafo.addEdge(1, 8);
		grafo.addEdge(10, 5);
		grafo.addEdge(6, 9);
		
		//Remover nodos
		grafo.removeNode(9);
		grafo.removeNode(1);
		grafo.removeNode(5);
		
		//Agrego nuevamente los nodos eliminados enteriormente y sus respectivos arcos
		grafo.addNode(1);
		grafo.addNode(5);
		grafo.addEdge(1, 2);
		grafo.addEdge(1, 3);
		grafo.addEdge(4, 1);
		grafo.addEdge(5, 2);
		
		//Remover arcos
		grafo.removeEdge(1, 2);
		grafo.removeEdge(5, 2);
		grafo.removeEdge(1, 2);
		grafo.removeEdge(1, 6);
		grafo.removeEdge(8, 2);
		grafo.removeEdge(9, 7);
	}

}
