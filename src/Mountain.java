import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Mountain {
    static int[][] map;
    static int n;
    static int maxLen=0;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            map = new int[n][n];
            int max=0;
            maxLen=0;
            List<int[]> start = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] > max) max=map[i][j];
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] == max) start.add(new int[]{i, j});
                }
            }

            for(int[] s : start){
                //System.out.println(s[0]+","+s[1]);
                boolean[][] visited = new boolean[n][n];
                visited[s[0]][s[1]] = true;
                findMax(visited,k,s[0],s[1],map[s[0]][s[1]],1);
            }
            System.out.printf("#%d %d\n",t+1,maxLen);
        }


    }
    public static void findMax(boolean[][] visited,int K,int r, int c, int h,int l) {

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(!inRange(nr,nc)||visited[nr][nc]) continue;
            if(K!=0){
                for (int k=1; k<=K; k++){
                    if(h<=Math.max(map[nr][nc]-k,0)) continue;
                    visited[nr][nc] = true;
                    findMax(visited,0,nr,nc,Math.max(map[nr][nc]-k,0),l+1);
                    visited[nr][nc] = false;
                }
            }

            if(h<=map[nr][nc]) continue;
            visited[nr][nc] = true;
            findMax(visited,K,nr,nc,map[nr][nc],l+1);
            visited[nr][nc] = false;

        }
        if(maxLen<l) maxLen=l;
    }
    public static boolean inRange(int r, int c){
        return r>=0&&r<n&&c>=0&&c<n;
    }
}
