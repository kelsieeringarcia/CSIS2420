package m01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.princeton.cs.algs4.Selection;
import edu.princeton.cs.algs4.StdOut;

/**
 * CSIS-2420 Midterm1
 * 
 * @author StarterCode + Kelsie Garcia
 *
 */
public class Midterm1 {

	public static void main(String[] args) {
		Container[] containers = {
				new Container(1234, 20, 1.9, "China"),
				new Container(1235, 40, 3.97, "USA"),
				new Container(1236, 40, 4.22, "China"),
				new Container(1237, 20, 2.16, "Ghana"),
				new Container(1238, 20, 2.1, "USA"),
				new Container(1239, 40, 4.08, "Italy"),
				new Container(1240, 40, 3.81, "China"),
				new Container(1241, 40, 4.2, "USA"),
				new Container(1242, 20, 1.82, "Italy")
		};
		
		StdOut.println("Containers: ");
		StdOut.println("=========== ");
		for(Container c : containers) {
			StdOut.println(c);
		}	
		System.out.println();
		
		StdOut.println("= = = =    Part 1   = = = =\n");
		
		StdOut.println("Containers by natural order:");
		StdOut.println("============================");
		Selection.sort(containers);
		for(Container c : containers) {
			StdOut.println(c);
		}
		
		
		StdOut.println("Containers in reverse order:");
		StdOut.println("============================");
		List<Container> reversedSortedContainers = new ArrayList<>(); 
		for(int i = 0; i < containers.length; i++) {
			reversedSortedContainers.add(i, containers[i]);
		}
		Collections.reverse(reversedSortedContainers);
		for(Container c : reversedSortedContainers) {
			StdOut.println(c);
		}
		
		StdOut.println("= = = =    Part 2    = = = =\n");
		
		StdOut.println("Foreign Containers:");
		StdOut.println("===================");
		for(Container c : containers) {
			if(!c.getCountry().contains("USA"))
			 StdOut.println(c);
		}

	}

}
