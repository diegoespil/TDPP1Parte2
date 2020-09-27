import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.Filter;

public class Graph {
	
	private Map<Integer,Integer> nodes;
	private HashMap<String, Edge> edges;
	private static Logger logger;
	
	public Graph() {
		nodes = new HashMap<Integer,Integer>();
		edges = new HashMap<String,Edge>();
		if (logger == null){
			
			logger = Logger.getLogger(Graph.class.getName());
			
			Handler hnd = new ConsoleHandler();
			hnd.setLevel(Level.FINE);
			
			logger.addHandler(hnd);
			
			logger.setLevel(Level.WARNING);
			
			Logger rootLogger = logger.getParent();
			for (Handler h : rootLogger.getHandlers()){
				h.setLevel(Level.OFF);
			}
		}
	}
	
	/*
	 Agrega un nodo al mapeo nodes. Si el nodo ya existe envia un mensaje al logger de warning
	*/
	public void addNode(int node) {
		boolean esta = this.nodes.containsKey(node);
		if (!esta) {
			Integer value = this.nodes.put(node, node);
			logger.info("El nodo "+node+" ah sido agregado");
		} else logger.warning("El nodo "+node+" ya se encuentra en el grafo");
	}
	
	public void addEdge(int node1, int node2) {
		boolean estaN1 = this.nodes.containsKey(node1);
		boolean estaN2 = this.nodes.containsKey(node2);
		String key = node1+","+node2;
		if (estaN1 && estaN2) {
			logger.fine("Los nodos se encuentran en el grafo");
			Edge edge = new Edge(node1,node2);
			Edge value = this.edges.put(key, edge);
			if (value != null) {
				logger.warning("Ya existe un arco entre el nodo "+node1+" y "+node2);
			} else {
				logger.info("Se agrego un arco entre el nodo "+node1+" y "+node2);
			}
				
		} else 
			if (!estaN1 && !estaN2) 
				logger.warning("Los nodos "+node1+" y "+node2+" no pertenecen al grafo");
			else 
				if (!estaN1) 
					logger.warning("El nodo "+node1+" no pertenece al grafo");
				else logger.warning("El nodo "+node2+" no pertenece al grafo");
				
		
	}
	
	public void removeNode(int node) {
		boolean estaN = this.nodes.containsKey(node);
		if (!estaN) 
			logger.warning("El nodo "+node+" no pertenece al grafo");
		else {
			List<String> toRemoves = new LinkedList<String>();
			for (Map.Entry<String, Edge> entry : this.edges.entrySet()) {
			    logger.fine("Arco de " + entry.getValue().getNodo1()+", "+entry.getValue().getNodo2());
			    Edge edge = entry.getValue();
			    String key = entry.getKey();
			    boolean comp = (node == edge.getNodo1()) || node == edge.getNodo2();
			    if (comp) {
			    	toRemoves.add(key);
			    	logger.fine("Agregue el arco con clave "+key+" para remover");
			    }  	
			}
			for ( String key : toRemoves) {
				this.edges.remove(key);
			}
			logger.info("Elimine los arcos incidentes y emergentes del nodo "+node);
			this.nodes.remove(node);
			logger.info("Elimine el nodo "+node);
		}
	}
	
	public void removeEdge(int node1, int node2) {
		boolean estaN1 = this.nodes.containsKey(node1);
		boolean estaN2 = this.nodes.containsKey(node2);
		if (!estaN1 && !estaN2) 
			logger.warning("El nodo "+node1+" y "+node2+" no pertenece al grafo");
		else 
			if (!estaN1) 
				logger.warning("El nodo "+node1+" no pertenece al grafo");
			else 
				if (!estaN2) 
					logger.warning("El nodo "+node2+" no pertenece al grafo");
				else{
					boolean hayArco = this.edges.containsKey(node1+","+node2);
					if (!hayArco) 
						logger.warning("No existe arco entre el nodo "+node1+" y "+node2);
					else {
						this.edges.remove(node1+","+node2);
						logger.info("El arco entre el nodo "+node1+" y "+node2+" fue removido.");
					}
			}
		
	}
	
	public String toString(){
		String nodos = "";
		String arcos = "";
		for (Map.Entry<Integer, Integer> entry: nodes.entrySet()) {
			nodos = nodos +", "+ entry.getValue();
		}
		for (Map.Entry<String, Edge> entry: edges.entrySet()) {
			arcos = arcos +", "+ entry.getKey();
		}
		return "Nodos: "+nodos+" arcos: "+arcos;
	}
}
