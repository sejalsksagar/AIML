package astar;

import java.util.*;

class Node implements Comparator<Node>{
	int n; //vertex number
	int g; //cost from src to n
	int h; //estimated cost from n to goal
	int f;
	Node p;
	
	Node(){
		
	}
	
	Node(int n, int g, int h, Node p) {
		super();
		this.n = n;
		this.g = g;
		this.h = h;
		this.f = g + h;
		this.p = p;
	}

	@Override
	public int compare(Node o1, Node o2) {
		if(o1.f < o2.f) return -1;
		if(o1.f > o2.f) return  1;
		return 0;
	}
}

public class Astar {
	static int N;
	static int src, goal;
	static int hVals[];
	static int adjMat[][];
	static int INF = 999;
	static int minCost = Integer.MAX_VALUE;
	static Scanner sc = new Scanner(System.in);
	
	static void printPath(Node cur) {
		if(cur == null) {
			System.out.println("Goal node not found!");
			return;
		}
		Stack<Integer> S = new Stack<>();
		while(cur!=null) {
			S.push(cur.n);
			cur = cur.p;
		}
		System.out.print("\nPath = "+S.pop());
		while(!S.isEmpty())
			System.out.print(" -> "+S.pop());
		System.out.println("\nMin Cost = "+minCost);
	}
	
	static void astar() {
		PriorityQueue<Node> Q = new PriorityQueue<Node>(N, new Node());
		Node cur = new Node(src, 0, hVals[src], null);
		boolean visited[] = new boolean[N];
		Node goalNode = null;
		Q.add(cur);
		visited[cur.n] = true;
		
		while(!Q.isEmpty()) {
			cur = Q.poll();
			if(cur.f > minCost) continue;
			
			for(int i=0; i<N; i++) {
				if(i!=cur.n && adjMat[cur.n][i]!=INF && !visited[i]) {
					Q.add(new Node(i, cur.g+adjMat[cur.n][i], hVals[i], cur));
					visited[cur.n] = true;
				}
			}
			
			if(cur.n==goal && cur.f<minCost) {
				goalNode = cur;
				minCost = cur.f;
			}
		}
		printPath(goalNode);
	}
	
	static void input() {
		System.out.println("Enter no. of vertices: ");
		N = sc.nextInt();
		
		hVals = new int[N];
		adjMat = new int[N][N];
		System.out.println("Enter weighted matrix of graph: ");
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				adjMat[i][j] = sc.nextInt();
				if(i!=j && adjMat[i][j]==0)
					adjMat[i][j] = INF;
			}
		}
		System.out.println("Enter heuristic values: ");
		for(int i=0; i<N; i++) 
			hVals[i] = sc.nextInt();
		
		System.out.println("Enter source & goal nodes:");
		src = sc.nextInt();
		goal = sc.nextInt();
		
		System.out.println("\nAdjacency Matrix: ");
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++)
				System.out.print(adjMat[i][j]+"\t");
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		input();
		astar();
	}
}

/*
Enter no. of vertices: 
7
Enter weighted matrix of graph: 
0	4	3	0	0	0	0
0	0	0	12	6	0	0
0	0	0	10	0	7	0
0	0	0	0	0	0	5
0	0	0	0	0	0	16
0	0	0	2	0	0	0
0	0	0	0	0	0	0
Enter heuristic values: 
14	12	11	4	11	6	0
Enter source & goal nodes:
0	6

Adjacency Matrix: 
0	4	3	999	999	999	999	
999	0	999	12	6	999	999	
999	999	0	10	999	7	999	
999	999	999	0	999	999	5	
999	999	999	999	0	999	16	
999	999	999	2	999	0	999	
999	999	999	999	999	999	0	

Path = 0 -> 2 -> 5 -> 3 -> 6
Min Cost = 17

 */
