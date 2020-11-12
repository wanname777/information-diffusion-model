package team.ourteam;

import java.util.HashMap;


/**
 * @author zhang
 * @date 2020-11-07 14:38:16
 */
public class Node {

    /**
     * num表示自己当前的编号，当然再此次实验中我们将Vector的序号与num对应了
     * <p>
     * status表示当前的状态，由0（未激活）、1（正向传播）、-1（负向传播）组成
     * <p>
     * influence表示该节点的对外影响力，即出度和
     * <p>
     * relativeInfluence表示该节点的相对对外影响力
     * <p>
     * beInfluenced表示该节点的被影响力，即入度和
     * <p>
     * 两个HashMap分别表示出度表和入度表
     */

    int num;
    int status;
    double influence;
    double relativeInfluence;
    double beInfluenced;
    HashMap<Integer, Double> outDegree = new HashMap<>();
    HashMap<Integer, Double> degree = new HashMap<>();


    public Node(int num, int status, double influence,
                double relativeInfluence, double beInfluenced) {
        this.num = num;
        this.status = status;
        this.influence = influence;
        this.relativeInfluence = relativeInfluence;
        this.beInfluenced = beInfluenced;
    }

    @Override
    public String toString() {
        return "Node{" +
                "num=" + num +
                ", status=" + status +
                ", influence=" + influence +
                ", relativeInfluence=" + relativeInfluence +
                ", beInfluenced=" + beInfluenced +
                ", outDegree=" + outDegree +
                ", degree=" + degree +
                '}';
    }

    public HashMap<Integer, Double> getOutDegree() {
        return outDegree;
    }

    public void setOutDegree(HashMap<Integer, Double> outDegree) {
        this.outDegree = outDegree;
    }

    public double getInfluence() {
        return influence;
    }

    public void setInfluence(double influence) {
        this.influence = influence;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void computeInfluence() {
        for (double d : outDegree.values()) {
            this.influence += d;
        }
    }

    public void computeBeInfluenced() {
        for (double d : degree.values()) {
            this.beInfluenced += d;
        }
    }

}
