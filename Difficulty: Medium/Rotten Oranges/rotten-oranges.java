//{ Driver Code Starts
import java.io.*;
import java.lang.*;
import java.util.*;

class GFG {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            int mat[][] = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) mat[i][j] = sc.nextInt();
            }
            Solution obj = new Solution();
            int ans = obj.orangesRotting(mat);
            System.out.println(ans);
            System.out.println("~");
        }
    }
}
// } Driver Code Ends




class Pair {
    int r;
    int c;
    int t;
    Pair(int r, int c, int t) {
        this.r = r;
        this.c = c;
        this.t = t;
    }
}

class Solution {
    public static boolean isSafe(int i, int j, int m, int n) {
        if (i >= m || i < 0 || j >= n || j < 0) return false;
        return true;
    }

    public int orangesRotting(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] v = new int[m][n];
        Queue<Pair> q = new LinkedList<>();
        int fresh = 0;

        // Count fresh oranges and add initial rotten oranges to queue
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) fresh++;
                else if (mat[i][j] == 2) {
                    q.add(new Pair(i, j, 0));
                    v[i][j] = 1; // mark as visited
                }
            }
        }

        int ans = 0, cnt = 0;

        while (!q.isEmpty()) {
            int i = q.peek().r;
            int j = q.peek().c;
            int t = q.peek().t;
            q.poll();
            ans = Math.max(t, ans);

            // Check 4 directions
            if (isSafe(i + 1, j, m, n) && mat[i + 1][j] == 1 && v[i + 1][j] != 1) {
                q.add(new Pair(i + 1, j, t + 1));
                v[i + 1][j] = 1;
                cnt++;
            }
            if (isSafe(i - 1, j, m, n) && mat[i - 1][j] == 1 && v[i - 1][j] != 1) {
                q.add(new Pair(i - 1, j, t + 1));
                v[i - 1][j] = 1;
                cnt++;
            }
            if (isSafe(i, j + 1, m, n) && mat[i][j + 1] == 1 && v[i][j + 1] != 1) {
                q.add(new Pair(i, j + 1, t + 1));
                v[i][j + 1] = 1;
                cnt++;
            }
            if (isSafe(i, j - 1, m, n) && mat[i][j - 1] == 1 && v[i][j - 1] != 1) {
                q.add(new Pair(i, j - 1, t + 1));
                v[i][j - 1] = 1;
                cnt++;
            }
        }

        // Uncomment and fix this to properly return -1 when not all fresh oranges are rotted
        if (cnt != fresh) return -1;

        return ans;
    }
}