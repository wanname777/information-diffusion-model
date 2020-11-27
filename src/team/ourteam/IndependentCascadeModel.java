package team.ourteam;

import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

/**
 * @author zhang
 * @version 3.0.0
 * @date 2020-11-18 21:26:15
 */
public class IndependentCascadeModel {

    /**
     * candidateNodeVector表示选出来的点集
     * <p>
     * nodeVector表示总点集
     * <p>
     * num表示总点数
     */
    Vector<Node> candidateNodeVector = new Vector<>();
    Vector<Node> nodeVector = new Vector<>();
    HashMap<Integer, Double> candidateNodeNumberMap = new HashMap<>();
    int num;

    public IndependentCascadeModel(int num) {
        this.num = num;
    }


    /**
     * @param path 文件路径
     */
    public void loadNodes(String path) {
        ReadFiles readFiles = new ReadFiles(this.num);
        readFiles.loadLinks(path, nodeVector);
    }

    /**
     * @param number 需要选出来的点数
     * @apiNote 通过模拟选出所需的number个点，该函数含内置超参数
     */
    public void simulation(int number) {
        int count = 0;
        double threshold = 0;
        Vector<Node> nodes = new Vector<>();
        Vector<Node> nodes1 = new Vector<>();
        Random random = new Random(25);

        // 随机模拟先选出一部分点
        for (int i = 1; i <= 100000; i++) {

            Node pNode = nodeVector.get(random.nextInt(this.num));
            if (!nodes1.contains(pNode)) {
                nodes.add(pNode);
                DiffusionModel diffusionModel = new DiffusionModel(nodes,
                        nodeVector, this.num);
                double imp = diffusionModel.calculate();
                if (imp > 10) {
                    nodes1.add(pNode);
                }
                nodes.remove(pNode);
            }
        }
        System.out.println("获得" + nodes1.size() + "个备选点");


        greedySimulation(number, nodes1);
    }

    /**
     * @param number 需要选出来的点数
     * @param nodes  simulation传入的节点集
     * @apiNote 贪心模拟，该函数含内置超参数
     */
    private void greedySimulation(int number, Vector<Node> nodes) {
        boolean[] vis = new boolean[this.num];

        for (int i = 0; i < number; i++) {
            //simulate and choose the best

            double maxValue;
            int maxIndex;
            maxIndex = -1;
            maxValue = -1.;
            double tempNum = 5000;

            System.out.println("Done" + i);
            // 贪心
            for (Node pn : nodes) {
                if (pn.outDegree.isEmpty() || vis[pn.num]) {
                    continue;
                }
                // tempNum次测试
                double count = 0;
                Vector<Node> nodes1 = new Vector<>();
                nodes1.add(pn);
                DiffusionModel diffusionModel = new DiffusionModel(nodes1,
                        nodeVector, this.num);
                for (int n = 0; n < tempNum; n++) {
                    double tempCount = diffusionModel.calculate();
                    count += tempCount;

                }
                // 计算平均值
                if (count / tempNum > maxValue) {
                    maxValue = count / tempNum;
                    maxIndex = pn.num;
                }
            }
            candidateNodeNumberMap.put(maxIndex, maxValue);
            Node pNode = nodeVector.get(maxIndex);
            candidateNodeVector.add(pNode);
            vis[maxIndex] = true;
        }

        // 总体模拟获得最终结果
        DiffusionModel diffusionModel =
                new DiffusionModel(candidateNodeVector, nodeVector,
                        this.num);

        System.out.println("单点正向扩散值：");
        System.out.println(this.candidateNodeNumberMap);
        System.out.println("总体正向扩散值：");
        System.out.println(diffusionModel.calculate());
    }
}


