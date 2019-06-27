package it.polito.tdp.metroparis.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;


import it.polito.tdp.metroparis.db.MetroDAO;

public class Model {
	private Graph<Fermata,DefaultEdge> grafo;
	private	Map <Integer,Fermata> tutteLeFermate;
	public void creaGrafo(){

	 this.grafo= new SimpleDirectedGraph<>(DefaultEdge.class);
	MetroDAO dao= new MetroDAO();
	tutteLeFermate=new HashMap<Integer,Fermata>();
	dao.getAllFermate(tutteLeFermate);
	
	Graphs.addAllVertices(this.grafo, tutteLeFermate.values());
	for (Fermata partenza: tutteLeFermate.values()) {
		List <Integer> arrivi=dao.getDestinazioni(partenza);
		for (Integer arrivo:arrivi) {
			
			grafo.addEdge(partenza,tutteLeFermate.get(arrivo));
		}
		
	}
	return ;
	
	}

	public Graph<Fermata, DefaultEdge> getGrafo() {
		return grafo;
	}

	
	
}
