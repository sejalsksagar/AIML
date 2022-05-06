package waterjug;

import java.util.*;

class Pair{
	int j1, j2;
	ArrayList<Pair> path;
	
	Pair(int j1, int j2){
		this.j1 = j1;
		this.j2 = j2;
		path = new ArrayList<>();
	}
	
	Pair addPair(int j1, int j2) {
		Pair p = new Pair(j1, j2);
		p.path.addAll(this.path);
		p.path.add(p);
		return p;
	}
}

public class WaterJug {
	static int maxJ1, maxJ2, tarJ1, tarJ2;
	
	static void printPath(Pair cur) {
		System.out.println("\nStart state = (0, 0) & Goal state = ("+tarJ1+", "+tarJ2+")");
		System.out.println("\tJug1\tJug2");
		System.out.println("\t(0,\t0)");
		for(Pair p: cur.path) 
			System.out.println("\t("+p.j1+",\t"+p.j2+")");
	}
	
	static void BFS() {
		boolean visited[][] = new boolean[maxJ1+1][maxJ2+1];
		Queue<Pair> Q = new LinkedList<>();
		Pair cur = new Pair(0, 0);
		Q.add(cur);
		
		while(!Q.isEmpty()) {
			cur = Q.poll();
			if(visited[cur.j1][cur.j2]) 
				continue;
			visited[cur.j1][cur.j2] = true;
			
			//target reached
			if(cur.j1==tarJ1 && cur.j2==tarJ2) {
				printPath(cur);
				System.out.println("Target level reached!");
				return;
			}
			
			//Water jug conditions
			//1. Fill any one
			if(cur.j1 < maxJ1)
				Q.add(cur.addPair(maxJ1, cur.j2));
			if(cur.j2 < maxJ2)
				Q.add(cur.addPair(cur.j1, maxJ2));
			
			//2. Empty any one
			if(cur.j1 > 0 && cur.j2!=0)
				Q.add(cur.addPair(0, cur.j2));
			if(cur.j2 > 0 && cur.j1!=0)
				Q.add(cur.addPair(cur.j1, 0));
			
			//3. fill till one full
			if(cur.j1 + cur.j2 >= maxJ2 && cur.j2!=0)
				Q.add(cur.addPair(cur.j1-(maxJ2-cur.j2), maxJ2));
			if(cur.j1 + cur.j2 >= maxJ1 && cur.j1!=0)
				Q.add(cur.addPair(maxJ1, cur.j2-(maxJ1-cur.j1)));
			
			//4. fill till one empty
			if(cur.j1 + cur.j2 <= maxJ2 && cur.j1>0)
				Q.add(cur.addPair(0, cur.j2+cur.j1));
			if(cur.j1 + cur.j2 <= maxJ1 && cur.j2>0)
				Q.add(cur.addPair(cur.j1+cur.j2, 0));
		}
		System.out.println("Target level unreachable.");
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter max capacities of jug1 & jug2: ");
		maxJ1 = sc.nextInt();
		maxJ2 = sc.nextInt();
		System.out.println("Enter target level for jug1 & jug2: ");
		tarJ1 = sc.nextInt();
		tarJ2 = sc.nextInt();
		BFS();
		sc.close();
	}
}

/*
Enter max capacities of jug1 & jug2: 
6	7
Enter target level for jug1 & jug2: 
4	0

Start state = (0, 0) & Goal state = (4, 0)
	Jug1	Jug2
	(0,	0)
	(6,	0)
	(0,	6)
	(6,	6)
	(5,	7)
	(5,	0)
	(0,	5)
	(6,	5)
	(4,	7)
	(4,	0)
Target level reached!
*/