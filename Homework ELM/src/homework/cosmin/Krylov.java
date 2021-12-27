package homework.cosmin;

import java.util.Arrays;

public class Krylov {

    public Krylov(int[][] A, int size, int[] yZero) {
        System.out.println("\nKRYLOV METHOD:");

        if (yZero.length == size) {
            int[][] B = calculateB(A, size, yZero);
            System.out.println("\nValues of matrix B:");
            MatrixUtility.printMatrix(B, size);

            float[][] invOfB = new float[size][size]; // To store the inverse of B[][]
            if (MatrixUtility.inverse(B, size, invOfB)) {
                System.out.println("\nInverse of matrix B:");
                MatrixUtility.printMatrix(invOfB, size);

                int[] Q = calculateQ(A, B, size, invOfB);
                printEquation(Q, size);
            }
        } else {
            System.out.println("\ny^0 length is invalid!");
        }
    }

    private int[][] calculateB(int[][] A, int size, int[] yZero) {
        int[][] B = new int[size][size];
        int[] yZeroAux = yZero;
        System.out.println("\nValues of y^0: " + (Arrays.toString(yZero)));

        if (size == 2) {
            B[0][1] = yZero[0];
            B[1][1] = yZero[1];

            int y0 = A[0][0] * yZeroAux[0] + A[0][1] * yZeroAux[1];
            int y1 = A[1][0] * yZeroAux[0] + A[1][1] * yZeroAux[1];

            B[0][0] = y0;
            B[1][0] = y1;

            yZeroAux[0] = y0;
            yZeroAux[1] = y1;

        } else if (size == 3) {
            B[0][2] = yZero[0];
            B[1][2] = yZero[1];
            B[2][2] = yZero[2];

            for (int index = 1; index >= 0; index--) {
                int y0 = A[0][0] * yZeroAux[0] + A[0][1] * yZeroAux[1] + A[0][2] * yZeroAux[2];
                int y1 = A[1][0] * yZeroAux[0] + A[1][1] * yZeroAux[1] + A[1][2] * yZeroAux[2];
                int y2 = A[2][0] * yZeroAux[0] + A[2][1] * yZeroAux[1] + A[2][2] * yZeroAux[2];

                B[0][index] = y0;
                B[1][index] = y1;
                B[2][index] = y2;

                yZeroAux[0] = y0;
                yZeroAux[1] = y1;
                yZeroAux[2] = y2;
            }
        } else {
            System.out.println("\nThe method has a solution only for size: 2 or 3!");
        }

        return B;
    }

    private int[] calculateQ(int[][] A, int[][] B, int size, float[][] invOfB) {
        int[] Q = new int[size];

        if (size == 2) {
            int y0 = -(A[0][0] * B[0][0] + A[0][1] * B[1][0]);
            int y1 = -(A[1][0] * B[0][0] + A[1][1] * B[1][0]);

            Q[0] = Math.round(invOfB[0][0] * y0 + invOfB[0][1] * y1);
            Q[1] = Math.round(invOfB[1][0] * y0 + invOfB[1][1] * y1);

        } else if (size == 3) {
            int y0 = -(A[0][0] * B[0][0] + A[0][1] * B[1][0] + A[0][2] * B[2][0]);
            int y1 = -(A[1][0] * B[0][0] + A[1][1] * B[1][0] + A[1][2] * B[2][0]);
            int y2 = -(A[2][0] * B[0][0] + A[2][1] * B[1][0] + A[2][2] * B[2][0]);

            Q[0] = Math.round(invOfB[0][0] * y0 + invOfB[0][1] * y1 + invOfB[0][2] * y2);
            Q[1] = Math.round(invOfB[1][0] * y0 + invOfB[1][1] * y1 + invOfB[1][2] * y2);
            Q[2] = Math.round(invOfB[2][0] * y0 + invOfB[2][1] * y1 + invOfB[2][2] * y2);

        } else {
            System.out.println("\nThe method has a solution only for size: 2 or 3!");
        }

        return Q;
    }

    private void printEquation(int[] Q, int size) {
        if (size == 2) {
            System.out.println("\nThe equation is:");
            System.out.println("x^2 + (" + Q[0] + ") * x + (" + Q[1] + ") = 0");
            System.out.println("\nand the eigenvalues are:");
            double[] roots = EquationUtility.solveQuadraticEquation(1, Q[0], Q[1]);
            for (double root : roots)
                System.out.printf("%.3f ", root);

        } else if (size == 3) {
            System.out.println("\nThe equation is:");
            System.out.println("x^3 + (" + Q[0] + ") * x^2 + (" + Q[1] + ") * x + " + Q[2] + " = 0");
            System.out.println("\nand the eigenvalues are:");
            double[] roots = EquationUtility.solveCubicEquation(1, Q[0], Q[1], Q[2]);
            for (double root : roots)
                System.out.printf("%.3f ", root);

        } else {
            System.out.println("\nThe method has a solution only for size: 2 or 3!");
        }
    }

}
