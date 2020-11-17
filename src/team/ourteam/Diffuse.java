package team.ourteam;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

/**
 * @author tang
 * @version 2.0.0
 * @date 2020-11-07 14:38:16
 */
public class Diffuse {
    Vector<Node> candidateNodeVector;
    Vector<Node> nodeVector;
    int num;

    public Diffuse(Vector<Node> candidateNodeVector, Vector<Node> nodeVector
            , int num) {
        // nodeVector copy
        this.candidateNodeVector = candidateNodeVector;
        this.nodeVector = nodeVector;
        this.num = num;
    }

    public double cal() {

        double tempCount = 0;
        double initCount = candidateNodeVector.size();

        Queue<Integer> list1 = new LinkedList<>();
        boolean[] beActivated = new boolean[num];
        double[] flag = new double[num];
        // 如果在vec中有的，就已开始改成激活状态
        for (Node node : this.candidateNodeVector) {
            beActivated[node.num] = true;
            flag[node.num] = 1;
            list1.offer(node.num);
        }
        // 如果没有被激活，则加入vec1进行模拟

        // 单点模拟
        while (!list1.isEmpty()) {

            for (int m = 0; m < list1.size(); m++) {
                Node pNode = nodeVector.get(list1.poll());
                pNode.outDegree.forEach((k, v) -> {
                    if (!beActivated[k] && Math.abs(v) >= Math.random() * 0.05) {
                        beActivated[k] = true;
                        flag[k] = flag[pNode.num] * v > 0 ? 1 : -1;
                        list1.offer(k);

                    }
                });

            }
        }
        // 统计单点影响力，当然这里算上了vec中的点
        for (int k = 0; k < num; k++) {
            if (beActivated[k] && flag[k] == 1) {
                tempCount++;
            }
        }

        return tempCount - initCount;
    }
}
