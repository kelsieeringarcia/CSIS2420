package finalCode;

import java.util.Scanner;

import edu.princeton.cs.algs4.DijkstraUndirectedSP;
import edu.princeton.cs.algs4.Edge;

public class ExerciseFinal {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		String filename = "src/finalCode/TrainConnections.csv";
		String delimiter = ",";
		String depart = "";
		String dest;
		boolean temp = false;
		Scanner sc = new Scanner(System.in);
		SymbolEdgeWeightedGraph sg = new SymbolEdgeWeightedGraph(filename, delimiter);

		System.out.println("Part A:");
		System.out.println("=======");
		do {
			do {
				System.out.print("Departure: ");
				depart = sc.nextLine();
				if (!sg.contains(depart)) {
					System.out.printf("There is no train connection to %s. %n", depart);
				}
			} while (!sg.contains(depart));
			do {
				System.out.print("Destination: ");
				dest = sc.nextLine();
				if (!sg.contains(dest)) {
					System.out.printf("There is no train connection to %s. %n", dest);
				}
			} while (!sg.contains(dest));
			if (!sg.contains(depart) && !sg.contains(dest)) {
				System.out.printf("There is neither a train to %s nor to %s.", depart, dest);
			} else {
				temp = true;
			}
		} while (temp == false);

		DijkstraUndirectedSP sp = new DijkstraUndirectedSP(sg.graph(), sg.indexOf(depart));

		System.out.println();
		System.out.println("Part B:");
		System.out.println("=======");
		System.out.println("Fastest Connection:");
		double tw = 0;
		for (Edge edge : sp.pathTo(sg.indexOf(dest))) {
			System.out.printf("%s - %s %.1f min %n", sg.nameOf(edge.either()), sg.nameOf(edge.other(edge.either())),
					edge.weight());
			tw += edge.weight();
		}
		System.out.println();
		System.out.printf("Total amount of time from %s to %s: %.1f", depart, dest, tw);
	}

}
