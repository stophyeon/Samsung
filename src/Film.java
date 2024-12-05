import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Film {
    static int w;
    static int d;
    static int k;
    static int answer=0;
    static boolean check=true;
    //static int[][] f;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            int[][] film = new int [d][w];
            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    film[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            answer=0;
            check=true;
//            int[][] f1 = new int[film.length][film[0].length];
//            int[][] f2 = new int[film.length][film[0].length];
//            for(int i=0;i<film.length;i++){
//                f1[i]=Arrays.copyOf(film[i],w);
//                f2[i]=Arrays.copyOf(film[i],w);
//            }
//            Arrays.fill(f1[0],0);
//            Arrays.fill(f2[0],1);
//            for(int i=0;i<film.length;i++){
//                for(int j=0;j<film[0].length;j++){
//                    System.out.print(f2[i][j]+" ");
//                }
//                System.out.println();
//            }
            while (check) {
                boolean[] visited = new boolean[d];
                makeFilm(answer,film,visited);
                answer++;
            }
            System.out.printf("#%d %d\n",t+1,answer-1);
        }
    }
    public static void makeFilm(int cnt,int[][] film,boolean[] visited) {
        if(cnt==0){
            if(testFilm(film)){check=false;}
            return;
        }
        int[][] f1 = new int[film.length][film[0].length];
        int[][] f2 = new int[film.length][film[0].length];
        for(int i=0;i<film.length;i++){
            f1[i]=Arrays.copyOf(film[i],w);
            f2[i]=Arrays.copyOf(film[i],w);
        }
        for(int i=0;i<d;i++){
            if(visited[i]) continue;
            Arrays.fill(f1[i],0);
            Arrays.fill(f2[i],1);
            visited[i]=true;
            makeFilm(cnt-1,f1,visited);
            makeFilm(cnt-1,f2,visited);
            visited[i]=false;
            f1[i]=Arrays.copyOf(film[i],w);
            f2[i]=Arrays.copyOf(film[i],w);
        }

    }
    public static boolean testFilm(int[][] map) {
        int total=0;
        for (int i = 0; i < w; i++) {
            int cnt=1;
            int type=map[0][i];
            boolean check = false;
            for (int j = 1; j < d; j++) {
                if (map[j][i]==type) {cnt++;}
                if (map[j][i]!=type) {type=map[j][i]; cnt=1;}
                if (cnt==k) {total++; check=true; break;}
            }
            if (!check) {return false;}
        }
        return total == w;
    }
}
