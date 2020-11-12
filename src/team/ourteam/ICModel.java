package team.ourteam;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Vector;

/**
 * @author zhang
 */
public class ICModel {

    Vector<Node> candidateNodeVector = new Vector<>();
    Vector<Node> nodeVector = new Vector<>();
    HashSet<Integer> candidateNodeNumberSet = new HashSet<>();
    HashMap<Double, Double> candidateNodeNumberMap = new HashMap<>();
    int num;

    public ICModel(int num) {
        this.num = num;
    }


    public void loadNodes(String path) {


        ReadFiles readFiles = new ReadFiles(this.num);
        readFiles.loadLinks(path, nodeVector);

        // 用普通迭代计算每个结点的影响力和被影响力
        for (Node pNode : nodeVector) {
            pNode.computeInfluence();
            pNode.computeBeInfluenced();

            // 如果该节点的入度图为空，则被影响力应为1.
            if (pNode.degree.isEmpty()) {
                pNode.beInfluenced = 1;
            }

        }
        // 计算相对影响力，必须是计算完影响力和被影响力后才能计算
        for (Node pNode : nodeVector) {
            pNode.outDegree
                    .forEach((k, v) -> pNode.relativeInfluence += v / nodeVector.get(k).beInfluenced);
        }

        //依靠nodeVector来访问不同的结点
//        System.out.println(nodeVector.get(1146));
    }

    public void simulation(int number) {


        Vector<Node> nodes = new Vector<>();
        Vector<Node> nodes1 = new Vector<>();
        Random random = new Random(25);

        for (int i = 0; i < 2000; i++) {

            Node pNode = nodeVector.get(random.nextInt(20000));
            nodes.add(pNode);
            Diffuse diffuse = new Diffuse(nodes, nodeVector, this.num);
            if (diffuse.cal() > 1500 && !nodes1.contains(pNode)) {
                nodes1.add(pNode);
            }
            nodes.remove(pNode);
        }
        System.out.println(nodes1.size());
        greedySimulation(number, nodes1);
    }

    public void greedySimulation(int number, Vector<Node> nodes) {

        for (int i = 0; i < number; i++) {
            //simulate and choose the best

            double maxValue;
            double maxIndex;
            maxIndex = maxValue = -1;
            double tempNum = 5;

            // 贪心
            // todo 把参数完全隔离似乎也并不完全是好事，diffuse的算法似乎还有一些问题
            for (Node pn : nodes) {
                // tempNum此测试
                double count = 0;
                Vector<Node> nodes1 = new Vector<>();
                nodes1.add(pn);
                Diffuse diffuse = new Diffuse(nodes1, nodeVector, this.num);
                for (int n = 0; n < tempNum; n++) {

                    double tempCount = diffuse.cal();
                    // 去除vec中点的影响
                    count += tempCount;

                }
                if (count / tempNum > maxValue) {
                    maxValue = count / tempNum;
                    maxIndex = pn.num;
                }
            }
            candidateNodeNumberMap.put(maxIndex, maxValue);
            Node pNode = nodeVector.get((int) maxIndex);
            candidateNodeVector.add(pNode);

            // 清空

        }


        Diffuse diffuse = new Diffuse(candidateNodeVector, nodeVector,
                this.num);
        System.out.println(diffuse.cal());
    }
}


