package avengers;

import org.w3c.dom.Node;

/**
 * 
 * Using the Adjacency Matrix of n vertices and starting from Earth (vertex 0),
 * modify the edge weights using the functionality values of the vertices that
 * each edge
 * connects, and then determine the minimum cost to reach Titan (vertex n-1)
 * from Earth (vertex 0).
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * LocateTitanInputFile name is passed through the command line as args[0]
 * Read from LocateTitanInputFile with the format:
 * 1. g (int): number of generators (vertices in the graph)
 * 2. g lines, each with 2 values, (int) generator number, (double) funcionality
 * value
 * 3. g lines, each with g (int) edge values, referring to the energy cost to
 * travel from
 * one generator to another
 * Create an adjacency matrix for g generators.
 * 
 * Populate the adjacency matrix with edge values (the energy cost to travel
 * from one
 * generator to another).
 * 
 * Step 2:
 * Update the adjacency matrix to change EVERY edge weight (energy cost) by
 * DIVIDING it
 * by the functionality of BOTH vertices (generators) that the edge points to.
 * Then,
 * typecast this number to an integer (this is done to avoid precision errors).
 * The result
 * is an adjacency matrix representing the TOTAL COSTS to travel from one
 * generator to another.
 * 
 * Step 3:
 * LocateTitanOutputFile name is passed through the command line as args[1]
 * Use Dijkstra’s Algorithm to find the path of minimum cost between Earth and
 * Titan.
 * Output this number into your output file!
 * 
 * Note: use the StdIn/StdOut libraries to read/write from/to file.
 * 
 * To read from a file use StdIn:
 * StdIn.setFile(inputfilename);
 * StdIn.readInt();
 * StdIn.readDouble();
 * 
 * To write to a file use StdOut (here, minCost represents the minimum cost to
 * travel from Earth to Titan):
 * StdOut.setFile(outputfilename);
 * StdOut.print(minCost);
 * 
 * Compiling and executing:
 * 1. Make sure you are in the ../InfinityWar directory
 * 2. javac -d bin src/avengers/*.java
 * 3. java -cp bin avengers/LocateTitan locatetitan.in locatetitan.out
 * 
 * @author Yashas Ravi
 * 
 */

public class LocateTitan {
    public static void main(String[] args) {

        if (args.length < 2) {
            StdOut.println("Execute: java LocateTitan <INput file> <OUTput file>");
            return;
        }
        String inputFile = args[0];
        String outputFile = args[1];

        // Set the input file.
        StdIn.setFile(inputFile);
        int g = StdIn.readInt();
        double[] a = new double[g];
        int[][] adj = new int[g][g];
        int index;
        for (int i = 0; i < g; i++) {
            index = StdIn.readInt();
            a[index] = StdIn.readDouble();
        }
        for (int i = 0; i < g; i++) {
            for (int j = 0; j < g; j++) {
                adj[i][j] = StdIn.readInt();
                if (adj[i][j] != 0) {
                    adj[i][j] = (int) Math.floor(adj[i][j] / (a[i] * a[j]));
                } else {
                    adj[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        int[] minCost = new int[g];
        boolean[] b = new boolean[g];
        for (int i = 0; i < g; i++) {
            minCost[i] = Integer.MAX_VALUE;
        }
        minCost[0] = 0;
        for (int i = 0; i < g - 1; i++) {
            int min_v = Integer.MAX_VALUE;
            int index3 = -1;
            for (int j = 0; j < g; j++) {
                if (!b[j] && minCost[j] < min_v) {
                    min_v = minCost[j];
                    index3 = j;
                }
            }
            b[index3] = true;
            for (int j = 0; j < g; j++) {
                if (minCost[index3] + adj[index3][j] < minCost[j] && adj[index3][j] != Integer.MAX_VALUE) {
                    minCost[j] = minCost[index3] + adj[index3][j];
                }
            }
        }
        StdOut.setFile(outputFile);
        StdOut.println(minCost[g - 1]);
    }
}
