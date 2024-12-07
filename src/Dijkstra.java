import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Dijkstra {
    static int[][] graph;
    static int n;
    static int max = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        int e=Integer.parseInt(st.nextToken());
        graph=new int[n+1][n+1];
        for(int i=0;i<=n;i++){
            for(int j=0;j<=n;j++){
                if(i==j){graph[i][j]=0;}
                else{graph[i][j]=max;}
            }
        }
        for (int i=0;i<e;i++) {
            st=new StringTokenizer(br.readLine());
            int u=Integer.parseInt(st.nextToken());
            int v=Integer.parseInt(st.nextToken());
            int w=Integer.parseInt(st.nextToken());
            graph[u][v]=w;
            graph[v][u]=w;
        }
        int[] dist=new int[n+1];
        Arrays.fill(dist, max);
        dijkstra(2,dist);
        for(int i=1;i<=n;i++){
            System.out.print(dist[i]+" ");
        }

    }
    public static void dijkstra(int start,int[] dist){
        boolean[] visited=new boolean[n+1];
        dist[start]=0;
        visited[start]=true;
        for(int i=0;i<n-1;i++){
            int v=start;
            int min=max;
            for(int j=1;j<=n;j++){
                if(!visited[j]&&dist[j]<min){
                    min=dist[j];
                    v=j;
                }
            }
            visited[v]=true;
            for(int j=1;j<=n;j++){
                if(!visited[j]&&dist[v]!=max&&graph[v][j]!=max&&dist[j]>dist[v]+graph[v][j]){
                    dist[j]=dist[v]+graph[v][j];
                }
            }
        }
    }
}
