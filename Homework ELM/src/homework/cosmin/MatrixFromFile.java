package homework.cosmin;

import java.io.File;
import java.util.Scanner;

// The class that reads a quadratic matrix from the file
public class MatrixFromFile {

    private int size = 0;
    private final int[][] matrix;

    public MatrixFromFile(String path) throws Exception {
        File file = new File(path);
        Scanner sc = new Scanner(file);

        if (sc.hasNextLine())
            size = Integer.parseInt(sc.nextLine());

        matrix = new int[size][size];
        while (sc.hasNextLine())
            for (int i = 0; i < matrix.length; i++) {
                String[] line = sc.nextLine().trim().split(" ");
                for (int j = 0; j < line.length; j++)
                    matrix[i][j] = Integer.parseInt(line[j]);
            }
    }

    public int getSize() {
        return size;
    }

    public int[][] getMatrix() {
        return matrix;
    }

}
