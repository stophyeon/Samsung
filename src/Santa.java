import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Santa {
    static int n;
    static int m;
    static int[] ids;
    static int[] ws;
    static HashMap<Integer, Boolean> out=new HashMap<>();
    static boolean[] broken;
    static HashMap<Integer, Boolean> exists=new HashMap<>();
    public static class Box{
        int id;
        int w;
        public Box(int id, int w){
            this.id = id;
            this.w = w;
        }
    }
    static HashMap<Integer,Integer> beltLoc=new HashMap<>();
    static HashMap<Integer,Integer>[] boxLoc;
    static HashMap<Integer,Integer> box=new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int  q = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int type= Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        ids = new int[n];
        ws = new int[n];
        broken = new boolean[m+1];
        boxLoc = new HashMap[m+1];
        for (int i = 0; i < n; i++) {
            ids[i] = Integer.parseInt(st.nextToken());
            exists.put(ids[i],true);
        }
        for (int i = 0; i < n; i++) {
            ws[i] = Integer.parseInt(st.nextToken());
            box.put(ids[i],ws[i]);
        }
        int idx=0;
        for (int i = 1; i <= m; i++) {
            boxLoc[i] = new LinkedHashMap<>();
            for (int j = 0; j < n/m; j++) {
                boxLoc[i].put(ids[j+idx],j);
                beltLoc.put(ids[j+idx],i);
            }
            idx+=n/m;
        }


        for (int i = 1; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            type = Integer.parseInt(st.nextToken());
            if(type==200){
                outBox(Integer.parseInt(st.nextToken()));
            }
            else if(type==300){
                removeBox(Integer.parseInt(st.nextToken()));
            }
            else if(type==400){
                findBox(Integer.parseInt(st.nextToken()));
            }
            else{
                breakBelt(Integer.parseInt(st.nextToken()));

            }
        }
    }
    public static void outBox(int max){
        int total=0;
        for (int i = 1; i <= m; i++) {
            if(broken[i]||boxLoc[i].isEmpty()) continue;
            List<Integer> index = new ArrayList<>(boxLoc[i].keySet());
            int idx=index.get(0);
            //System.out.println(idx);
            if(box.get(idx)<=max) {
                total+=box.get(idx);
                out.put(idx,true);
                boxLoc[i].remove(idx);
            }
            else{
                boxLoc[i].remove(index.get(0));
                boxLoc[i].put(idx,boxLoc[i].size());
            }
        }
        System.out.println(total);
    }

    public static void removeBox(int id){
        if(out.containsKey(id)||!exists.containsKey(id)) {System.out.println(-1); return;}
        int i=beltLoc.get(id);
        boxLoc[i].remove(id);
        out.put(id,true);
        System.out.println(id);
    }

    public static void findBox(int id){
        if(out.containsKey(id)||!exists.containsKey(id)) {System.out.println(-1); return;}
        int i=beltLoc.get(id);
        List<Integer> index = new ArrayList<>(boxLoc[i].keySet());
        for(int j=0; j<index.size(); j++){
            if(index.get(j)==id) break;
            boxLoc[i].remove(index.get(j));
            boxLoc[i].put(index.get(j),boxLoc[i].size());
        }

        System.out.println(beltLoc.get(id));
    }

    public static void breakBelt(int id){
        if(broken[id]) {System.out.println(-1); return;}
        broken[id]=true;
        int idx=id;

        while(true){
            //System.out.println(idx);
            idx=(idx+1)%(m+1);
            if(idx==0) continue;
            if(!broken[idx]) break;
        }
        //System.out.println(idx);
        List<Integer> rest=new ArrayList<>(boxLoc[id].keySet());
        int len = boxLoc[idx].size();
        for(int r : rest){
            beltLoc.put(r,idx);
            boxLoc[idx].put(r,len);
            len++;
        }
        boxLoc[id].clear();
        System.out.println(id);
    }
}
