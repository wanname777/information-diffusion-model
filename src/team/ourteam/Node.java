package team.ourteam;

import java.util.HashMap;
import java.util.Objects;


/**
 * @author tang
 * @version 2.0.0
 * @date 2020-11-07 14:38:16
 */
public class Node {
    /**
     * num表示自己当前的编号，当然再此次实验中我们将Vector的序号与num对应了
     * <p>
     * HashMap分别表示出度表和入度表
     */

    int num;
    HashMap<Integer, Double> outDegree = new HashMap<>();


    public Node(int num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node node = (Node) o;
        return num == node.num &&
                Objects.equals(outDegree, node.outDegree);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, outDegree);
    }

    @Override
    public String toString() {
        return "Node{" +
                "num=" + num +
                ", outDegree=" + outDegree +
                '}';
    }

    public HashMap<Integer, Double> getOutDegree() {
        return outDegree;
    }

    public void setOutDegree(HashMap<Integer, Double> outDegree) {
        this.outDegree = outDegree;
    }

}
