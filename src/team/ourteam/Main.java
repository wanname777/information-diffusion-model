package team.ourteam;


/**
 * @author zhang
 * @version 1.0.0
 * @date 2020-11-07 14:38:16
 */
public class Main {

    public static void main(String[] args) {
        // write your code here

        String extensionType = ".txt";
        String linksPath = "links";
        String path = "src/text/";

        ReadFiles test = new ReadFiles();
        test.loadLinks(path + linksPath + extensionType);

        // 用普通迭代计算每个结点的影响力和被影响力
        for (int i = 0; i < test.nodeVector.size(); i++) {
            Node pNode = test.nodeVector.get(i);

            pNode.computeInfluence();
            pNode.computeBeInfluenced();

            // 如果该节点的入度图为空，则被影响力应为1.
            if (pNode.degree.isEmpty()) {
                pNode.beInfluenced = 1.;
            }

        }
        // 换了一种更好玩的迭代方式计算每个节点的相对影响力
        for (Node i : test.nodeVector) {
            i.outDegree.forEach((k, v) -> i.relativeInfluence += v / test.nodeVector.get(k).beInfluenced);
        }


        //依靠test.nodeVector来访问不同的结点
        System.out.println(test.nodeVector.get(1146).toString());


    }
}
