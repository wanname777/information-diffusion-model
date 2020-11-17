package team.ourteam;

import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

/**
 * @author zhang
 */
public class ICModel {

    Vector<Node> candidateNodeVector = new Vector<>();
    Vector<Node> nodeVector = new Vector<>();

    HashMap<Integer, Double> candidateNodeNumberMap = new HashMap<>();
    int num;

    public ICModel(int num) {
        this.num = num;
    }


    public void loadNodes(String path) {


        ReadFiles readFiles = new ReadFiles(this.num);
        readFiles.loadLinks(path, nodeVector);
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
        System.out.println("获得" + nodes1.size() + "个备选点");
        greedySimulation(number, nodes1);
    }

    public void greedySimulation(int number, Vector<Node> nodes) {
        boolean[] vis = new boolean[this.num];

        for (int i = 0; i < number; i++) {
            //simulate and choose the best

            double maxValue;
            int maxIndex;
            maxIndex = -1;
            maxValue = -1.;
            double tempNum = 10;

            System.out.println("ddd");
            // 贪心
            for (Node pn : nodes) {
                if (vis[pn.num]) {
                    continue;
                }
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
            Node pNode = nodeVector.get(maxIndex);
            candidateNodeVector.add(pNode);
            vis[maxIndex] = true;

            // 清空

        }


        Diffuse diffuse = new Diffuse(candidateNodeVector, nodeVector,
                this.num);

        System.out.println("单点正向扩散值：");
        System.out.println(this.candidateNodeNumberMap);
        System.out.println("总体正向扩散值：");
        System.out.println(diffuse.cal());
    }
}


