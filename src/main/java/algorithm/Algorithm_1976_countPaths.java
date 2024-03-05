package algorithm;

import java.util.*;

/**
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 * 示例 1：
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 */
class Algorithm_1976_countPaths{

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] roads = {{0,6,7},{0,1,2},{1,2,3},{1,3,3},{6,3,3},{3,5,1},{6,5,1},{2,5,1},{0,4,5},{4,6,2}};
        int i = solution.countPaths(7, roads);
        System.out.println(i);
    }
}
class Solution {
    long max=(long)1e12;
    long[] sum;
    boolean[] isVisitedGet;
    long mod=(long)1e9+7;
    int c=0;
    int n;
    public  int countPaths(int n, int[][] roads) {
        this.n=n;
        long[][] weight=new long[n][n];
        //初始化无穷大
        for(int i=0;i<n;i++){
            Arrays.fill(weight[i],max);
        }
        //初始化weight，双向图
        for(int[] road:roads){
            weight[road[0]][road[1]]=road[2];
            weight[road[1]][road[0]]=road[2];
        }
        //flag数组用于记录每个节点得到最短路径的前驱节点
        long[][] flag=new long[n][n];
        //初始化消耗数组
        long[] arr=new long[n];
        Arrays.fill(arr,max);
        arr[0]=0;
        // dijkstra(arr,flag,weight);
        dijkstraCommon(arr,weight,flag);
        sum=new long[n];
        isVisitedGet=new boolean[n];
        //最后一步求值也分为一个函数
        return (int)(GetNum(arr,flag,n-1)%mod);
        //return (int)(GetNumByKahn(flag,arr)%mod);
    }
    void dijkstra(long[]arr,long[][]flag,long[][]weight){
        boolean[] isVisited=new boolean[n];
        Queue<Integer> queue = new PriorityQueue<>((a, b)->Long.compare(arr[a],arr[b]));
        //以0为源节点，所以添加以0为基础的值
        for(int i=1;i<n;i++){
            if(weight[0][i]<max){
                arr[i]=weight[0][i];
                flag[i][0]=arr[i];
                queue.offer(i);
            }
        }
        isVisited[0]=true;
        while(!queue.isEmpty()){
            int cur=queue.poll();
            if(!isVisited[cur]){
                isVisited[cur]=true;
                for(int i=0;i<n;i++){
                    //这里就是链接cur后，访问cur的所有节点
                    if(weight[cur][i]<max){
                        long tem=arr[cur]+weight[cur][i];
                        if(tem<=arr[i]){
                            arr[i]=tem;
                            flag[i][cur]=arr[i];
                        }
                        if(!isVisited[i]){
                            queue.offer(i);
                        }
                    }
                }
            }
        }
    }
    void dijkstraCommon(long[]dist,long[][]g,long[][]flag) {
        boolean[] vis=new boolean[n];
        //初始化无穷大
        Arrays.fill(dist, max);
        dist[0] = 0;
        for (int i = 0; i < n; i++) {
            //一样的，i表示添加的节点个数
            int t = -1;
            //这里找出最小值，就是遍历一遍dist数组
            for (int j = 0; j < n; j++) {
                if (!vis[j] && (t == -1 || dist[j] < dist[t])) t = j;
            }
            vis[t] = true;
            //接下来遍历t的所有节点
            for (int j = 0; j < n; j++) {
                if (g[t][j] >= max) continue;
                long tem = dist[t] + g[t][j];
                if(tem<=dist[j]){
                    dist[j]=tem;
                    flag[j][t]=dist[j];
                }
            }
        }
    }

    //递归加上动态规划，flag就是记录前驱节点
    public long GetNum(long[] min,long[][] flag,int i){
        if(i==0){
            return 1;
        }
        //如果i被访问了，说明它的sum值已经被计算过
        if(isVisitedGet[i]){
            return sum[i];
        }

        for(int j=0;j<min.length;j++){
            //找到前驱节点时，往下递归
            if(flag[i][j]==min[i]){
                sum[i]=(GetNum(min,flag,j)+sum[i])%mod;
            }
        }
        isVisitedGet[i]=true;
        return sum[i]%mod;
    }


    //使用拓扑排序和动态规划的get
    public long GetNumByKahn(long[][]flag,long[]arr){
        //通过flag函数来初始化入度和重新构建一个图
        //新的图就是只有有效边（最短路）且单向图
        int[] inde=new int[n];
        int[][] graph=new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                //当找到最短路的时候，且最短路的值不为0时，入度加一
                if(arr[i]==flag[i][j]&&flag[i][j]!=0){
                    inde[i]++;
                    graph[j][i]=1;
                }
            }
        }
        Deque<Integer> queue=new LinkedList<>();
        for(int i=0;i<n;i++){
            if(inde[i]==0){
                queue.offer(i);
            }
        }
        long[] result=new long[n];
        result[0]=1;
        while(!queue.isEmpty()){
            int cur=queue.poll();
            for(int i=0;i<n;i++){
                //当有路径的时候
                if(graph[cur][i]!=0){
                    //cur->i，所以更新i的值
                    result[i]+=result[cur];
                    result[i]%=mod;
                    inde[i]--;
                    if(inde[i]==0){
                        queue.offer(i);
                    }
                }
            }
        }
        return result[n-1];
    }
}
