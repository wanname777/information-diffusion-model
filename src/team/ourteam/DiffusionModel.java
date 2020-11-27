package team.ourteam;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

/**
 * @author tang
 * @version 2.0.0
 * @date 2020-11-07 14:38:16
 */
public class DiffusionModel {

    /**
     * candidateNodeVector表示选出来的点集
     * <p>
     * nodeVector表示总点集
     * <p>
     * num表示总点数
     */
    Vector<Node> candidateNodeVector;
    Vector<Node> nodeVector;
    int num;

    public DiffusionModel(Vector<Node> candidateNodeVector,
                          Vector<Node> nodeVector
            , int num) {
        this.candidateNodeVector = candidateNodeVector;
        this.nodeVector = nodeVector;
        this.num = num;
    }

    /**
     * @return 节点正向影响力
     * @apiNote 模拟已知点集的扩散并获得最终结果
     */
    public double calculate() {

        double tempCount = 0;
        double initCount = candidateNodeVector.size();

        Queue<Integer> list1 = new LinkedList<>();
        boolean[] beActivated = new boolean[num];
        double[] flag = new double[num];

        // 将原有节点加入队列
        // 如果在vec中有的，就已开始改成激活状态
        for (Node node : this.candidateNodeVector) {
            beActivated[node.num] = true;
            flag[node.num] = 1;
            list1.offer(node.num);
        }

        // 单点模拟
        while (!list1.isEmpty()) {

            Node pNode = nodeVector.get(list1.poll());
            pNode.outDegree.forEach((k, v) -> {
                if (!beActivated[k] && Math.abs(v) >= Math.random()) {
                    beActivated[k] = true;
                    flag[k] = flag[pNode.num] * v > 0 ? 1 : -1;
                    list1.offer(k);

                }
            });

        }
        // 统计单点影响力，当然这里算上了vec中的点
        for (int k = 0; k < num; k++) {
            if (beActivated[k] && flag[k] == 1) {
                tempCount++;
            }
        }

        // 去除vec中点的影响
        return tempCount - initCount;
    }
}
