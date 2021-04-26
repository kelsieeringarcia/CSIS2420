package graphInternet;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.KruskalMST;


public class InternetCE {

	public static void main(String[] args) {
        String filename  = "/Users/kelsiegarcia/Documents/gitHub/CSIS-2420/ClassLab2420/src/graphInternet/GraphInternet.txt";
        In in = new In(filename);
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(in);
        KruskalMST mst = new KruskalMST(ewg);
        
        System.out.print("Offices needing to be connected: ");
        for(Edge e :mst.edges()) {
        	int eitherTemp = e.either();
        	if(eitherTemp != 8 )
        	System.out.print(e.toString().substring(0,3) + " ");
        }
        System.out.println();

        System.out.print("Offices needing a router: ");
        for(Edge e :mst.edges()) {
        	int eitherTemp = e.either();
        	if(eitherTemp == 8 )
        	System.out.print(e.toString().substring(2,3) + " ");
        }
        System.out.println();
        
        System.out.print("Total cost: ");
        double res = 0;
        for(Edge e :mst.edges()) {
            String[] arr = e.toString().split(" ");
            res += Double.parseDouble(arr[1]);
        }
        System.out.printf("$%.2f ",res);

	}

}
