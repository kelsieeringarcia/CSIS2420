package graphDFSvsBFS;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;

public class DFSvsBFS {

	public static void main(String[] args) throws FileNotFoundException {

		// System.out.println("Working Directory = " + System.getProperty("user.dir"));

		File filename = new File(
				"//Users//kelsiegarcia//Documents//gitHub//CSIS-2420//ClassLab2420//src//graphDFSvsBFS//SimpleGraph.txt");
		In in = new In(filename);
		Graph g = new Graph(in);



        System.out.println("Adjacency List:");
        System.out.println("---------------");
        for(int i = 0; i < g.V(); i++) {
        	System.out.print(i+": ");
        	for(int h : g.adj(i)) {
        		System.out.print(h + " ->");
        	}
        	System.out.println();
        }
        System.out.println();

	}
}
