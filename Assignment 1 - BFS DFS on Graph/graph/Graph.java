package graph;

import java.util.*;

public class Graph {
	static int sv;
	static int Nv, Ne;
	static int adjMat[][];
	static boolean directed;
	static Scanner sc = new Scanner(System.in);
	
	static void input() {
		System.out.println("Enter no. of vertices & edges: ");
		Nv = sc.nextInt();
		Ne = sc.nextInt();
		
		adjMat = new int[Nv][Nv];
		System.out.println("Is the graph directed? (y/n)");
		String ch = sc.next();
		if(ch.equalsIgnoreCase("y"))
			directed = true;
		
		System.out.println("Enter start & end for each edge:");
		for(int i=0; i<Ne; i++) {
			System.out.print("Edge "+i+": ");
			int s = sc.nextInt();
			int e = sc.nextInt();
			adjMat[s][e] = 1;
			if(!directed)
				adjMat[e][s] = 1;
		}
		
		System.out.println("Enter start vertex: ");
		sv = sc.nextInt();
		
		System.out.println("Adjacency matrix:");
		for(int i=0; i<Nv; i++) {
			for(int j=0; j<Nv; j++)
				System.out.print(adjMat[i][j]+"\t");
			System.out.println();
		}
	}
	
	static void DFS() {
		System.out.println("\nDFS = ");
		Stack<Integer> S = new Stack<>();
		boolean visited[] = new boolean[Nv];
		S.add(sv);
		visited[sv] = true;
		
		while(!S.isEmpty()) {
			int cur = S.pop();
			System.out.print(cur+"\t");
			
			for(int i=Nv-1; i>0; i--) {
				if(adjMat[cur][i]!=0 && !visited[i]) {
					S.push(i);
					visited[i] = true;
				}
			}
		}
	}
	
	static void BFS() {
		System.out.println("\n\nBFS = ");
		Queue<Integer> Q = new LinkedList<>();
		boolean visited[] = new boolean[Nv];
		Q.add(sv);
		visited[sv] = true;
		
		while(!Q.isEmpty()) {
			int cur = Q.poll();
			System.out.print(cur+"\t");
			
			for(int i=0; i<Nv; i++) {
				if(adjMat[cur][i]!=0 && !visited[i]) {
					Q.add(i);
					visited[i] = true;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		input();
		DFS();
		BFS();
	}
}

/*
Enter no. of vertices & edges: 
5	5
Is the graph directed? (y/n)
n
Enter start & end for each edge:
Edge 0: 0	1
Edge 1: 0	2
Edge 2: 0	3
Edge 3: 2	1
Edge 4: 2	4
Enter start vertex: 
0
Adjacency matrix:
0	1	1	1	0	
1	0	1	0	0	
1	1	0	0	1	
1	0	0	0	0	
0	0	1	0	0	

DFS = 
0	1	2	4	3	

BFS = 
0	1	2	3	4	
*/