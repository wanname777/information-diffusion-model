package team.ourteam;


/**
 * @author zhang
 * @version 2.1.0
 * @date 2020-11-07 14:38:16
 */
public class Main {
    public static void main(String[] args) {
        // 加载数据
        IndependentCascadeModel tempModel = new IndependentCascadeModel(20000);
        String path = "src/text/links.txt";
        tempModel.loadNodes(path);

        // 模拟并计时
        Long startTime = System.currentTimeMillis();
        tempModel.simulation(10);
        Long endTime = System.currentTimeMillis();
        System.out.println("耗时：" + (endTime - startTime) / 1000 + "秒");

/*
        // 随机选点测试
        Vector<Node> nodeVector=new Vector<>();
        nodeVector.add(tempModel.nodeVector.get(1218));
        nodeVector.add(tempModel.nodeVector.get(1193));
        nodeVector.add(tempModel.nodeVector.get(10272));
        nodeVector.add(tempModel.nodeVector.get(1254));
        nodeVector.add(tempModel.nodeVector.get(1165));
        nodeVector.add(tempModel.nodeVector.get(1222));
        nodeVector.add(tempModel.nodeVector.get(1174));
        nodeVector.add(tempModel.nodeVector.get(1249));
        nodeVector.add(tempModel.nodeVector.get(1229));
        nodeVector.add(tempModel.nodeVector.get(1228));
        Diffuse diffuse=new Diffuse(nodeVector,tempModel.nodeVector,20000);
        System.out.println(diffuse.cal());

*/

    }
}
