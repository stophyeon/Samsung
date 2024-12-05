import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Dessert {

    static int[][] map;
    static int n;
    static int[] dr = { 1, 1, -1, -1 };
    static int[] dc = { 1, -1, -1, 1 };
    static int answer=-1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            map = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            //직사각형으로 움직임
            answer=-1;
            for (int i = 0; i < n-2; i++) {
                for (int j = 1; j < n-1; j++) {
                    boolean[] visited = new boolean[101];
                    visited[map[i][j]] = true;
                    dfs(i,j,i,j,0,1,visited);
                    visited[map[i][j]] = false;
                }
            }

            System.out.printf("#%d %d\n",t+1,answer);
        }
    }
    public static void dfs(int sr,int sc,int r, int c, int d ,int cnt ,boolean[] visited) {

        for (int i = d; i <4; i++) {
            int nr = r+dr[i];
            int nc = c+dc[i];
            if(nr==sr&&nc==sc&&cnt>=4){
                if(answer<cnt) {answer=cnt;return;}
            }
            if(!inRange(nr,nc)||visited[map[nr][nc]]) continue;
            visited[map[nr][nc]] = true;
            dfs(sr,sc,nr,nc,i,cnt+1,visited);
            visited[map[nr][nc]] = false;

        }
    }
    public static boolean inRange(int r, int c){
        return r>=0&&r<n && c>=0&& c<n;
    }
}
