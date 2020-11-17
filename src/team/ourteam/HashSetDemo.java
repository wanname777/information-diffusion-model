package team.ourteam;

import java.util.HashSet;

public class HashSetDemo {

    public static void main(String[] args) {
        HashSet<Node> nodeHashSet = new HashSet<>();
        nodeHashSet.add(new Node(1, 1, 1, 1, 1));
        nodeHashSet.add(new Node(2, 1, 1, 1, 1));
        for (Node node : nodeHashSet) {
            System.out.println(node.num);
            if (node.num == 1) {
                break;
            }
        }


    }
}
