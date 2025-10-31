package game.ENTITY;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BrickMapLoader extends Brick {

    public static int[][] loadMap(String mapName) {
        int[][] brickMap = new int[10][19];

        try {
            String path = "/asset/brickMap/" + mapName + ".txt";
            InputStream is = BrickMapLoader.class.getResourceAsStream(path);
            //if (is == null) {
            //   throw new RuntimeException("Không tìm thấy file map: " + path);
            //}

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            for (int i = 0; i < 10; ++i) {
                String line = br.readLine();

                for (int j = 0; j < 19; ++j) {
                    String number[] = line.split(" ");
                    int num = Integer.parseInt(number[j]); // string -> int
                    brickMap[i][j] = num;
                }
            }
            br.close();
        } catch (Exception e) {
            throw new RuntimeException(mapName);
        }

        return brickMap;
    }
}
