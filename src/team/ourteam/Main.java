package team.ourteam;


/**
 * @author zhang
 * @version 2.0.1
 * @date 2020-11-07 14:38:16
 */
public class Main {


    public static void main(String[] args) {
        // write your code here
        ICModel tempModel = new ICModel(20000);
        String path = "src/text/links.txt";
        tempModel.loadNodes(path);

        System.out.println(tempModel.candidateNodeVector);
        Long startTime = System.currentTimeMillis();
        tempModel.simulation(10);
        Long endTime = System.currentTimeMillis();
        System.out.println(tempModel.candidateNodeNumberMap);
        System.out.println((endTime - startTime) / 1000);

        //十分钟后见
    }
}
