package befs;

import java.util.*;

class Node implements Comparator<Node>{
	int n;
	int h;
	Node p;
	
	Node(){
		
	}
	
	Node(int n, int h, Node p){
		this.n = n;
		this.h = h;
		this.p = p;
	}

	@Override
	public int compare(Node o1, Node o2) {
		if(o1.h < o2.h) return -1;
		if(o1.h > o2.h) return 1;
		return 0;
	}
}

public class BestFirstSearch {
	static int N;
	static int adjMat[][];
	static int hVals[];
	static int src, goal;
	static final int INF = 999;
	static Scanner sc = new Scanner(System.in);
	
	static void printPath(Node g) {
		System.out.println("\nGoal found!");
		Stack<Node> S = new Stack<>();
		Node cur = g;
		int cost = 0;
		
		while(cur != null) {
			S.push(cur);
			if(cur.p != null)
				cost += adjMat[cur.p.n][cur.n];
			cur = cur.p;
		}
		
		System.out.print("Path = "+S.pop().n);
		while(!S.isEmpty())
			System.out.print(" -> "+S.pop().n);
		System.out.println("\nCost = "+cost);
	}
	
	static void input() {
		System.out.println("Enter no. of vertices: ");
		N = sc.nextInt();
		
		adjMat = new int[N][N];
		hVals = new int[N];
		
		System.out.println("Enter weighted graph: ");
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				adjMat[i][j] = sc.nextInt();
				if(i != j && adjMat[i][j] == 0)
					adjMat[i][j] = INF;
			}
		}
		
		System.out.println("Enter heuristic values: ");
		for(int i=0; i<N; i++)
			hVals[i] = sc.nextInt();
		
		System.out.println("Enter source & goal nodes: ");
		src = sc.nextInt();
		goal = sc.nextInt();
		
		System.out.println("\nAdjacency matrix: ");
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) 
				System.out.print(adjMat[i][j]+"\t");
			System.out.println();
		}
	}
	
	static void BeFS() {
		PriorityQueue<Node> O = new PriorityQueue<Node>(N, new Node());
		ArrayList<Integer> C = new ArrayList<>();
		
		Node goalNode = null;
		boolean visited[] = new boolean[N];
		Node cur = new Node(src, hVals[src], null);
		O.offer(cur);
		visited[cur.n] = true;
		
		while(!O.isEmpty()) {
			cur = O.poll();
			C.add(cur.n);
			
			for(int i=0; i<N; i++) {
				if(i!=cur.n && adjMat[cur.n][i]!=INF && !visited[i] ) {
					O.add(new Node(i, hVals[i], cur));
					visited[i] = true;
				}
			}
			
			Node tmp = O.peek();
			if(tmp!=null && tmp.n==goal) {
				goalNode = tmp;
				break;
			}
		}
		if(goalNode == null) {
			System.out.println("\nGoal not found!");
			return;
		}
		printPath(goalNode);
	}
	
	public static void main(String[] args) {
		input();
		BeFS();
	}

}

/*
7

0	4	3	0	0	0	0
0	0	0	12	6	0	0
0	0	0	10	0	7	0
0	0	0	0	0	0	5
0	0	0	0	0	0	16
0	0	0	2	0	0	0
0	0	0	0	0	0	0

14	12	11	4	11	6	0

0 6

Enter no. of vertices: 
7
Enter weighted graph: 
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

Adjacency matrix: 
0	4	3	999	999	999	999	
999	0	999	12	6	999	999	
999	999	0	10	999	7	999	
999	999	999	0	999	999	5	
999	999	999	999	0	999	16	
999	999	999	2	999	0	999	
999	999	999	999	999	999	0	

Goal found!
Path = 0 -> 2 -> 3 -> 6
Cost = 18

*/