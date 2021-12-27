package homework.cosmin;

public class MatrixUtility {

    // Function to print a quadratic matrix
    static void printMatrix(int[][] M, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                System.out.print(M[i][j] + " ");
            System.out.println();
        }
    }

    // Function to print a quadratic matrix
    static void printMatrix(float[][] M, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                System.out.printf("%.5f ", M[i][j]);
            System.out.println();
        }
    }

    // Function that add the two quadratic matrices
    // and store in matrix C
    static int[][] add(int[][] A, int[][] B, int size) {
        int[][] C = new int[size][size];

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                C[i][j] = A[i][j] + B[i][j];

        return C;
    }

    // Function that substract the two quadratic matrices
    // and store in matrix C
    static int[][] substract(int[][] A, int[][] B, int size) {
        int[][] C = new int[size][size];

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                C[i][j] = A[i][j] - B[i][j];

        return C;
    }

    // Function to multiply
    // two matrices
    static int[][] multiply(int rows1, int columns1, int[][] A, int rows2, int columns2, int[][] B) {
        // Check if multiplication is Possible
        if (rows2 != columns1) {
            System.out.println("\nMultiplication Not Possible");
            return new int[0][0];
        }

        // Matrix to store the result
        // The product matrix will
        // be of size rows1 x columns2
        int[][] C = new int[rows1][columns2];

        // Multiply the two matrices
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < columns2; j++) {
                for (int k = 0; k < rows2; k++)
                    C[i][j] += A[i][k] * B[k][j];
            }
        }
        return C;
    }

    // Function to multiply
    // two matrices
    static float[][] multiply(int rows1, int columns1, float[][] A, int rows2, int columns2, int[][] B) {
        // Check if multiplication is Possible
        if (rows2 != columns1) {
            System.out.println("\nMultiplication Not Possible");
            return new float[0][0];
        }

        // Matrix to store the result
        // The product matrix will
        // be of size rows1 x columns2
        float[][] C = new float[rows1][columns2];

        // Multiply the two matrices
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < columns2; j++) {
                for (int k = 0; k < rows2; k++)
                    C[i][j] += A[i][k] * B[k][j];
            }
        }
        return C;
    }

    // Function to multiply
    // two matrices
    static float[][] multiply(int rows1, int columns1, float[][] A, int rows2, int columns2, float[][] B) {
        // Check if multiplication is Possible
        if (rows2 != columns1) {
            System.out.println("\nMultiplication Not Possible");
            return new float[0][0];
        }

        // Matrix to store the result
        // The product matrix will
        // be of size rows1 x columns2
        float[][] C = new float[rows1][columns2];

        // Multiply the two matrices
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < columns2; j++) {
                for (int k = 0; k < rows2; k++)
                    C[i][j] += A[i][k] * B[k][j];
            }
        }
        return C;
    }

    // Function to multiply
    // a vector with a matrix
    static int[] multiply(int rows, int columns, int[][] A, int size, int[] B) {
        // Check if multiplication is Possible
        if (size != columns) {
            System.out.println("\nMultiplication Not Possible");
            return new int[0];
        }

        // Matrix to store the result
        // The product matrix will
        // be of size rows1 x columns2
        int[] C = new int[rows];

        // Multiply the two matrices
        for (int i = 0; i < rows; i++)
            for (int k = 0; k < size; k++)
                C[i] += A[i][k] * B[k];
        return C;
    }

    // Function to multiply
    // a vector with a matrix
    static double[] multiply(int rows, int columns, float[][] A, int size, double[] B) {
        // Check if multiplication is Possible
        if (size != columns) {
            System.out.println("\nMultiplication Not Possible");
            return new double[0];
        }

        // Matrix to store the result
        // The product matrix will
        // be of size rows1 x columns2
        double[] C = new double[rows];

        // Multiply the two matrices
        for (int i = 0; i < rows; i++)
            for (int k = 0; k < size; k++)
                C[i] += (double) A[i][k] * B[k];
        return C;
    }

    // Transpose of a quadratic matrix
    static int[][] transpose(int[][] M, int size) {
        int[][] B = new int[size][size];

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                B[i][j] = M[j][i];

        return B;
    }

    // Recursive function for finding determinant of matrix.
    //  n is current dimension of M[][].
    static int determinant(int[][] M, int size) {
        int D = 0; // Initialize result

        // Base case : if matrix contains single element
        if (size == 1)
            return M[0][0];

        int[][] temp = new int[size][size]; // To store cofactors

        int sign = 1; // To store sign multiplier

        // Iterate for each element of first row
        for (int f = 0; f < size; f++) {
            // Getting Cofactor of M[0][f]
            getCofactor(M, temp, 0, f, size);
            D += sign * M[0][f] * determinant(temp, size - 1);

            // terms are to be added with alternate sign
            sign = -sign;
        }

        return D;
    }

    // Function to calculate and store inverse,
    // returns false if matrix is singular
    static boolean inverse(int[][] M, int size, float[][] inverse) {
        // Find determinant of M[][]
        int det = determinant(M, size);
        if (det == 0) {
            System.out.print("Singular matrix, can't find its inverse");
            return false;
        }

        // Find adjoint
        int[][] adj = new int[size][size];
        adjoint(M, size, adj);

        // Find Inverse using formula "inverse(M) = adj(M)/det(M)"
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                inverse[i][j] = adj[i][j] / (float) det;

        return true;
    }

    // Function to get adjoint of M[][] in adj[][].
    static void adjoint(int[][] M, int size, int[][] adj) {
        if (size == 1) {
            adj[0][0] = 1;
            return;
        }

        // temp is used to store cofactors of A[][]
        int sign = 1;
        int[][] temp = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // Get cofactor of M[i][j]
                getCofactor(M, temp, i, j, size);

                // sign of adj[j][i] positive if sum of row
                // and column indexes is even.
                sign = ((i + j) % 2 == 0) ? 1 : -1;

                // Interchanging rows and columns to get the
                // transpose of the cofactor matrix
                adj[j][i] = (sign) * (determinant(temp, size - 1));
            }
        }
    }

    // Function to get cofactor of M[p][q] in temp[][].
    // n is current dimension of M[][]
    static void getCofactor(int[][] M, int[][] temp, int p, int q, int n) {
        int i = 0, j = 0;

        // Looping for each element of the matrix
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                // Copying into temporary matrix only those element
                // which are not in given row and column
                if (row != p && col != q) {
                    temp[i][j++] = M[row][col];

                    // Row is filled, so increase row index and
                    // reset col index
                    if (j == n - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }

}
