package team.ourteam;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Vector;


/**
 * @author zhang
 * ReadFiles在这作为了纯粹的工具类
 * 构造方法私有
 * 成员方法静态
 */
public class ReadFiles {
    public ReadFiles() {
    }

    public static void loadLinks(String path, Vector<Node> nodeVector) {
        int tempNum = 20000;
        // 初始化vector
        for (int i = 0; i < tempNum; i++) {
            nodeVector.add(new Node(i, 0, 0., 0., 0.));
        }

        LineNumberReader reader = null;
        FileReader in = null;
        String[] pairs;

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
                nodeVector.get(num2).degree.put(num1, d1 * d2);

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
