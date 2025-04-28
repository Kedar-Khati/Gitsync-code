//{ Driver Code Starts
// Initial Template for Java
import java.io.*;
import java.util.*;

class GFG {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        while (tc-- > 0) {
            int V = sc.nextInt();
            int E = sc.nextInt();
            int[][] edges = new int[E][2];
            for (int i = 0; i < E; i++) {
                edges[i][0] = sc.nextInt();
                edges[i][1] = sc.nextInt();
            }

            Solution obj = new Solution();
            boolean ans = obj.isCycle(V, edges);
            System.out.println(ans ? "true" : "false");
            System.out.println("~");
        }
        sc.close();
    }
}

// } Driver Code Ends




class Solution {
    public static void dfs(int i,int prev,HashMap<Integer,List<Integer>> m,HashSet<Integer> v,boolean[] c){
        v.add(i);
        for(int j : m.computeIfAbsent(i,k->new ArrayList<>())){
            if(j != prev && v.contains(j)){
                c[0] = true;
                return ;
            }
            else if(j != prev){
                dfs(j,i,m,v,c);
            }
        }
    }
    public boolean isCycle(int V, int[][] edges) {
        // Code here
        HashMap<Integer,List<Integer>> m = new HashMap<>();
        for(int[] e : edges){
            m.computeIfAbsent(e[0],k -> new ArrayList<>()).add(e[1]);
            m.computeIfAbsent(e[1],k -> new ArrayList<>()).add(e[0]);
        }
        HashSet<Integer> v = new HashSet<>();
        boolean[] c = new boolean[1];
        c[0] = false;
        for(int i=0;i<V;i++){
            if(!v.contains(i)){
                dfs(i,-1,m,v,c);
            }
            if(c[0]) break;
        }
        return c[0];
    }
}