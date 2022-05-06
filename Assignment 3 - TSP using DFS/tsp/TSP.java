package tsp;

import java.util.*;

public class TSP {
	
	static int sv; 
	static int Nv;
	static int adjMat[][];
	static boolean visited[];
	static int minCost = Integer.MAX_VALUE;
	static ArrayList<Integer> minPath = new ArrayList<>();
	
	static void DFS(int cur, int num, int cost, ArrayList<Integer> path) {
		
		if(num==Nv && adjMat[cur][sv]>0) {
			int curCost = cost + adjMat[cur][sv];
			if(minCost > curCost) {
				minCost = curCost;
				minPath.clear();
				minPath.addAll(path);
			}
			return;
		}
		
		for(int i=0; i<Nv; i++) {
			if(adjMat[cur][i]>0 && !visited[i]) {
				visited[cur] = true;
				path.add(i);
				DFS(i, num+1, cost+adjMat[cur][i], path);
				visited[cur] = false;
				path.remove((Integer)i);
			}
		}
	}
	
	static void input() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of vertices:");
		Nv = sc.nextInt();
		adjMat = new int[Nv][Nv];
		visited = new boolean[Nv];
		
		System.out.println("Enter weight matrix of graph:");
		for(int i=0; i<Nv; i++) 
			for(int j=0; j<Nv; j++) 
				adjMat[i][j] = sc.nextInt();
		
		System.out.println("Enter start vertex:");
		sv = sc.nextInt();
		
		System.out.println("\nAdjacency Matrix:");
		for(int i=0; i<Nv; i++) {
			for(int j=0; j<Nv; j++) 
				System.out.print(adjMat[i][j]+"\t");
			System.out.println();
		}
		sc.close();
	}
	
	public static void main(String[] args) {
		input();
		ArrayList<Integer> path = new ArrayList<>();
		path.add(sv);
		DFS(sv, 1, 0, path);
		minPath.add(sv);
		
		System.out.println("TSP Path = "+minPath.toString());
		System.out.println("Min Cost = "+minCost);
	}
}

/*
Enter number of vertices:
4
Enter weight matrix of graph:
0 10 15 20 5 0 9 10 6 13 0 12 8 8 9 0
Enter start vertex:
0

Adjacency Matrix:
0	10	15	20	
5	0	9	10	
6	13	0	12	
8	8	9	0	
TSP Path = [0, 1, 3, 2, 0]
Min Cost = 35

*/
