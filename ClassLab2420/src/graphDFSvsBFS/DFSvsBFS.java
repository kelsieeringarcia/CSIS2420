package graphDFSvsBFS;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.princeton.cs.algs4.Graph;

public class DFSvsBFS {

	public static void main(String[] args) throws FileNotFoundException {

		// System.out.println("Working Directory = " + System.getProperty("user.dir"));

		File filename = new File(
				"//Users//kelsiegarcia//Documents//gitHub//CSIS-2420//ClassLab2420//src//graphDFSvsBFS//SimpleGraph.txt");
		Scanner sc = new Scanner(filename);
		Graph g = new Graph(sc.nextInt());
		while (sc.hasNextLine()) {

			g.addEdge(sc.nextInt(), sc.nextInt());

		}
		sc.close();

		System.out.println("Adjacency List:");
		System.out.println("---------------");
		System.out.println(g);

	}
}
