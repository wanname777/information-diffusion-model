package team.ourteam;

import java.util.HashSet;
import java.util.Vector;

/**
 * @author zhang
 */
public class ICModel {
    Vector<Node> candidateNodeVector = new Vector<>();
    Vector<Node> nodeVector = new Vector<>();
    HashSet<Integer> candidateNodeSet = new HashSet<>();

    public void loadNodes() {

        String extensionType = ".txt";
        String linksPath = "links";
        String path = "src/text/";

        ReadFiles.loadLinks(path + linksPath + extensionType, nodeVector);

        // 用普通迭代计算每个结点的影响力和被影响力
        for (Node pNode : nodeVector) {
            pNode.computeInfluence();
            pNode.computeBeInfluenced();

            // 如果该节点的入度图为空，则被影响力应为1.
            if (pNode.degree.isEmpty()) {
                pNode.beInfluenced = 1.;
            }

        }
        // 计算相对影响力，必须是计算完影响力和被影响力后才能计算
        for (Node pNode : nodeVector) {
            pNode.outDegree.forEach((k, v) -> pNode.relativeInfluence += v / nodeVector.get(k).beInfluenced);
        }

        //依靠nodeVector来访问不同的结点
        System.out.println(nodeVector.get(1146));
    }

    public void greedySimulation(int number) {


        for (int i = 0; i < number; i++) {
            //simulate and choose the best

            int maxValue;
            int maxIndex;
            maxIndex = maxValue = -1;
            for (int j = 0; j < 20000; j++) {
                // if node j is not in candidate nodes
                // simulate j and get its output[j]
                // attention: when you are simulating this process you
                // should avoid candidate nodes
                // so you should set a bool array to get candidate nodes
                // and has visited notes

                if (!candidateNodeSet.contains(j)) {
//                    int tempValue = simulate(j);
                    int tempValue=0;
                    if (tempValue > maxValue) {
                        maxValue = tempValue;
                        maxIndex = j;
                    }

                }
            }

            // add the best node to candidate node which has the biggest
            // output[j]
            candidateNodeSet.add(maxIndex);

        }
    }
}


