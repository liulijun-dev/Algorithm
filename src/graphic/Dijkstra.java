package graphic;

public class Dijkstra {

    public static final int INFINITE = Integer.MAX_VALUE;
    private final int[][] data;
    private final int startVertex;
    private int[] visitedVertex;

    public Dijkstra(int[][] data, int startVertex) {
        this.data = data;
        this.startVertex = startVertex;

        this.visitedVertex = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            visitedVertex[i] = 0;
        }
        this.visitedVertex[startVertex] = 1;
    }

    public int[] getShortestPath() {
        for (int i = 0; i < data.length; i++) {
            int position = 0;
            int minDistance = INFINITE;
            //找出一个未标记的离出发点最近的节点（更新后的距离）
            for (int j = 0; j < data.length; j++) {
                if (visitedVertex[j] == 0 && data[startVertex][j] < minDistance && j != startVertex) {
                    minDistance = data[startVertex][j];
                    position = j;
                }
            }
            System.out.println("选出一个最短的路径：" + minDistance + "它是位置是：" + position);
            //标记该节点为已经访问过
            visitedVertex[position] = 1;

            for (int k = 0; k < data.length; k++) {
                //>0是防止两个正整数相加导致整形溢出
                if (data[startVertex][position] + data[position][k] > 0 && data[startVertex][position] + data[position][k] < data[startVertex][k] && k != position && k != startVertex) {
                    System.out.println("原先的最短路径到" + k + "距离是:" + data[startVertex][k]);
                    System.out.println("两者分别是" + data[startVertex][position] + "," + data[position][k]);
                    data[startVertex][k] = data[startVertex][position] + data[position][k]; //更新最短路径
                    System.out.println("更新最短路径到" + k + "距离是:" + data[startVertex][k]);
                }
            }
        }

        return data[startVertex];
    }

    public static void main(String[] args) {
        int[][] weight = {
                {0, 1, 12, INFINITE, INFINITE, INFINITE},
                {INFINITE, 0, 9, 3, INFINITE, INFINITE},
                {INFINITE, INFINITE, 0, INFINITE, 5, INFINITE},
                {4, 0, 13, 15, INFINITE, INFINITE},
                {INFINITE, INFINITE, INFINITE, INFINITE, 0, 4},
                {INFINITE, INFINITE, INFINITE, INFINITE, INFINITE, 0}
        };

        Dijkstra dijkstra = new Dijkstra(weight, 0);
        int[] result = dijkstra.getShortestPath();
        for (int distance : result) {
            System.out.println(distance);
        }
    }
}


