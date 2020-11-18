package team.ourteam;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Vector;


/**
 * @author zhang
 * @version 1.0.0
 * @date 2020-11-07 14:38:16
 */
public class ReadFiles {
    /**
     * tempNum为模拟节点数量
     */

    int tempNum;

    public ReadFiles(int tempNum) {
        this.tempNum = tempNum;

    }

    /**
     * @param path       文件路径
     * @param nodeVector 节点容器
     */
    public void loadLinks(String path, Vector<Node> nodeVector) {
        // 初始化vector
        for (int i = 0; i < this.tempNum; i++) {
            nodeVector.add(new Node(i));
        }

        LineNumberReader reader = null;
        FileReader in = null;
        String[] pairs;

        // try-catch
        try {
            in = new FileReader(path);
            reader = new LineNumberReader(in);

            while (true) {
                String str = reader.readLine();
                if (str == null) {
                    break;
                }
                pairs = str.split(" ");

                int num1 = Integer.parseInt(pairs[0]);
                int num2 = Integer.parseInt(pairs[1]);
                double d1 = Double.parseDouble(pairs[2]);
                double d2 = Double.parseDouble(pairs[3]);

                // 计算当前点的出度和被指向点的入度
                nodeVector.get(num1).outDegree.put(num2, d1 * d2);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
