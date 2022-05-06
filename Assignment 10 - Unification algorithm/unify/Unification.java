package unify;

import java.util.*;

public class Unification {

	static ArrayList<String> subst;
	
	static String predicate(String E) {
		int idx = E.indexOf('(');
		if(idx != -1) return E.substring(0, idx);
		return E;
	}
	
	static boolean complete(String arg) {
		int i = 0, j = 0;
		for(char a: arg.toCharArray()) {
			if(a == '(') i++;
			if(a == ')') j++;
		}
		if(i == j) return true;
		return false;
	}
	
	static ArrayList<String> argsList(String E) {
		ArrayList<String> args = new ArrayList<>();
		int idx = E.indexOf('(');
		if(idx == -1) return args;
		
		E = E.substring(idx+1, E.length()-1);
		String A[] = E.split(",");
		
		for(int i=0; i<A.length; i++) {
			if(complete(A[i]))
				args.add(A[i]);
			else {
				String tmp = A[i];
				while(!complete(tmp)) 
					tmp += ","+A[++i];
				args.add(tmp);
			}
		}
		return args;
	}
	
	static boolean isVar(String E) {
		char e = E.toCharArray()[0];
		//Uppercase letter -> variable
		if(e>='A' && e<='Z')
			return true;
		return false;
	}
	
	static String unification(String E1, String E2) {
		String pred1 = predicate(E1);
		String pred2 = predicate(E2);
		ArrayList<String> args1 = argsList(E1);
		ArrayList<String> args2 = argsList(E2);
		
//		System.out.println("pred1: "+pred1);
//		System.out.println("args1: "+args1.toString());
//		System.out.println("pred2: "+pred2);
//		System.out.println("args2: "+args2.toString());
		
		if(E1.length()==1 || E2.length()==1) {
			if(E1.equals(E2)) 
				return "SUCCESS";
			if(E1.length()==1 && isVar(E1)) {
				if(args2.contains(E1))
					return "FAILURE";
				else
					return "{"+E2+"/"+E1+"}";
			}
			if(E2.length()==1 && isVar(E2)) {
				if(args1.contains(E2))
					return "FAILURE";
				else
					return "{"+E1+"/"+E2+"}";
			}
		}
		if(!pred1.equals(pred2))
			return "FAILURE";
		if(args1.size() != args2.size())
			return "FAILURE";
		
		for(int i=0; i<args1.size(); i++) {
			for(String s: subst) {
				String r[] = s.substring(1, s.length()-1).split("/");
				args1.set(i, args1.get(i).replace(r[1], r[0]));
				args2.set(i, args2.get(i).replace(r[1], r[0]));
			}
			String S = unification(args1.get(i), args2.get(i));
			if(S.equals("SUCCESS")) continue;
			if(S.equals("FAILURE")) return S;
			subst.add(S);
		}
		return "SUCCESS";
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter expression 1:");
		String E1 = sc.next();
		System.out.println("Enter expression 2:");
		String E2 = sc.next();
		
		subst = new ArrayList<>();
		String S = unification(E1, E2);
		if(S.equals("FAILURE"))
			System.out.println("Unification failure!");
		else
			System.out.println("Unifiers:\n"+subst.toString());
		
		sc.close();
	}
}

//Q(a,g(X,a),f(Y))        Q(a,g(f(b),a),X)
//grant_parent(X,parent(parent(X)))  grant_parent(john,parent(Y))
//p(b,X,f(g(Z)))	p(Z,f(Y),f(Y))
//p(f(a),g(Y))	p(X,X)
//p(f(a),g(b),Y)  	p(Z,g(w),c)