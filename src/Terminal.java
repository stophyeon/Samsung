import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Terminal {
    static int[][] map;
    static int n;
    static int m;
    static int[] dr={-1,1,0,0};
    static int[] dc={0,0,-1,1};
    public static class Node{
        int r;
        int c;
        int l;
        Path type;
        public Node(int r,int c,int l,Path type){
            this.r=r;
            this.c=c;
            this.l=l;
            this.type=type;
        }
    }
    public static class Path{
        int type;
        int[] in;
        int[] out;
        public Path(int type){
            this.type=type;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            map = new int[n][m];
            boolean[][] visited = new boolean[n][m];
            visited[r][c] = true;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            Queue<Node> q = new LinkedList<>();
            q.add(new Node(r,c,1,makeDir(new Path(map[r][c]))));
            while (!q.isEmpty()) {
                Node node = q.poll();
                if (node.l==l) continue;
                for (int j : node.type.out) {
                    int nr = node.r + dr[j];
                    int nc = node.c + dc[j];
                    if (!inRange(nr, nc) || visited[nr][nc] || map[nr][nc] == 0) continue;
                    if (!isPossible(nr,nc,j)) continue;
                    q.add(new Node(nr,nc,node.l+1,makeDir(new Path(map[nr][nc]))));
                    visited[nr][nc] = true;
                }

            }
            int answer=0;
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < m; j++) {
//                    if (visited[i][j]) System.out.print("1 ");
//                    else System.out.print("0 ");
//                }
//                System.out.println();
//            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (visited[i][j]) answer++;
                }
            }
            System.out.printf("#%d %d\n",t+1,answer);
        }
    }
    public static boolean isPossible(int r, int c, int d) {
        Path p = makeDir(new Path(map[r][c]));
        boolean possible=false;
        for(int in : p.in){
            if (d==in){
                possible=true;
                break;
            }
        }
        return possible;
    }
    public static Path makeDir(Path p) {
        if (p.type==1){
            p.in=new int[]{0,1,2,3};
            p.out=new int[]{0,1,2,3};
        }
        else if (p.type==2){
            p.in=new int[]{0,1};
            p.out=new int[]{0,1};
        }
        else if (p.type==3){
            p.in=new int[]{2,3};
            p.out=new int[]{2,3};
        }
        else if (p.type==4){
            p.out=new int[]{0,3};
            p.in=new int[]{1,2};
        }
        else if (p.type==5){
            p.out=new int[]{1,3};
            p.in=new int[]{0,2};
        }
        else if (p.type==6){
            p.out=new int[]{1,2};
            p.in=new int[]{0,3};
        }
        else{
            p.out=new int[]{0,2};
            p.in=new int[]{1,3};
        }
        return p;
    }
    public static boolean inRange(int r, int c) {
        return r>=0&&r<n && c>=0&&c<m;
    }
}
