package graphSymbol;

import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class RouteFinder {

	public static void main(String[] args) {
		String filename = "/Users/kelsiegarcia/Documents/gitHub/CSIS-2420/ClassLab2420/src/graphSymbol/routes.txt";
		String delimiter = " ";
		SymbolGraph sg = new SymbolGraph(filename, delimiter);
		Graph graph = sg.graph();
		String source;
		do {
		StdOut.println("Enter a departure location: ");
			source = StdIn.readLine();
			if (sg.contains(source)) {
				int s = sg.indexOf(source);
				BreadthFirstPaths dfs = new BreadthFirstPaths(graph, s);
				for (int v = 0; v < graph.V(); v++) {
					if(dfs.hasPathTo(v)) {
						StdOut.printf("%s: ", sg.nameOf(s));
						for(int x : dfs.pathTo(v)) {
							if(x == s) {
								System.out.println(sg.nameOf(x));
							}else {
								System.out.println("-" + sg.nameOf(x));
							}
						}
						System.out.println();
					}else {
						System.out.printf("%d to %d : not connected \n", s,v);
					}
				}
			} else {
				StdOut.println("The following destinations can be reached from '" + source + "'");
			}
		}while(sg.contains(source));
	}
}
