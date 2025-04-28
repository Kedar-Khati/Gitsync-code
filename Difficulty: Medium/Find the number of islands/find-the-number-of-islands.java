//{ Driver Code Starts
import java.util.*;

class GFG {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tc = scanner.nextInt();
        while (tc-- > 0) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            char[][] grid = new char[n][m];

            // Read the grid input
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    grid[i][j] = scanner.next().charAt(0);
                }
            }
            Solution obj = new Solution();
            int ans = obj.countIslands(grid);
            System.out.println(ans);
            System.out.println("~");
        }
        scanner.close();
    }
}

// } Driver Code Ends



class Pair{
    int x;
    int y;
    Pair(int x,int y){
        this.x = x;
        this.y = y;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair other = (Pair) o;
        return this.x == other.x && this.y == other.y;
    }
    @Override
    public int hashCode(){
        return Objects.hash(x,y);
    }
}
class Solution {
    public static boolean isSafe(int i,int j,int m,int n){
        if(i < 0 || j < 0 || i >=m || j >= n) return false;
        return true;
    }
    public static void mark(int i,int j,int m,int n,int[][] comb,char[][] grid,HashSet<Pair> s){
        if(s.contains(new Pair(i,j))) return ;
        s.add(new Pair(i,j));
        for(int[] c : comb){
            if(isSafe(i+c[0],j+c[1],m,n) && grid[i+c[0]][j+c[1]] == 'L'){
                mark(i+c[0],j+c[1],m,n,comb,grid,s);
            }
        }
    }
    public int countIslands(char[][] grid) {
        // Code here
        int m = grid.length,n = grid[0].length;
        int[][] comb = {{1,0},{0,1},{-1,0},{0,-1},{1,1},{-1,1},{1,-1},{-1,-1}};
        HashSet<Pair> s = new HashSet<>();
        int res = 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(!s.contains(new Pair(i,j)) && grid[i][j] == 'L'){
                    mark(i,j,m,n,comb,grid,s);
                    res++;
                }
            }
        }
        return res;
    }
}