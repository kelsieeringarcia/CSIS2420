package graphShortestPath;

import edu.princeton.cs.algs4.DijkstraSP;
import edu.princeton.cs.algs4.DirectedEdge;

public class ShortestPathCE {

	public static void main(String[] args) {
        String filename  = "src/graphShortestPath/airports.txt";
        String delimiter = " ";
        EdgeWeightedSymbolDigraph ewsg = new EdgeWeightedSymbolDigraph(filename, delimiter);
        
        DijkstraSP sp = new DijkstraSP(ewsg.digraph(),ewsg.indexOf("START"));
        
        System.out.println("Shortest path from Start to End:");
        System.out.println("--------------------------------");
        double tw = 0;
        for(DirectedEdge edge : sp.pathTo(ewsg.indexOf("END"))) {
        	System.out.printf("%s %s (%.1f) %n", ewsg.nameOf(edge.from()),ewsg.nameOf(edge.to()), edge.weight() );
        	tw += edge.weight();
        }
        System.out.println();
        System.out.println("Total length from Start to End: " + tw);
	}

}
