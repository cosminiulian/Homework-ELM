package homework.cosmin;

import java.util.Arrays;

public class Leverrier {

    public Leverrier(int[][] A, int size) {
        System.out.println("\nLEVERRIER METHOD:");
        int[][] auxM = A;

        int[] s = new int[size];
        for (int i = 0; i < size; i++) {
            s[i] = getSumOfPrincipalDiagonal(auxM, size);
            auxM = MatrixUtility.multiply(size, size, auxM, size, size, A);
        }
        System.out.println("\nValues of matrix s[]:");
        System.out.println(Arrays.toString(s));

        int[] p = new int[size];
        for (int i = 0; i < size; i++)
            p[i] = calculateP(s, p, i);
        System.out.println("\nValues of matrix p[]:");
        System.out.println(Arrays.toString(p));

        printEquation(p, size);
    }

    // Function to get the sum of Principal Diagonal
    private int getSumOfPrincipalDiagonal(int[][] A, int size) {
        int sum = 0;

        for (int i = 0; i < size; i++)
            sum += A[i][i];
        return sum;
    }

    private int calculateP(int[] s, int[] p, int n) {
        int pVal = s[n];

        for (int i = 1; i <= n; i++)
            pVal += s[n - i] * p[i - 1];

        return (int) (-1.0 / (n + 1) * pVal);
    }

    private void printEquation(int[] p, int size) {

        if (size == 2) {
            System.out.println("\nThe equation is:");
            System.out.println("x^2 + (" + p[0] + ") * x + (" + p[1] + ") = 0");
            System.out.println("\nand the eigenvalues are:");
            double[] roots = EquationUtility.solveQuadraticEquation(1, p[0], p[1]);
            for (double root : roots)
                System.out.printf("%.3f ", root);

        } else if (size == 3) {
            System.out.println("\nThe equation is:");
            System.out.println("x^3 + (" + p[0] + ") * x^2 + (" + p[1] + ") * x + " + p[2] + " = 0");
            System.out.println("\nand the eigenvalues are:");
            double[] roots = EquationUtility.solveCubicEquation(1, p[0], p[1], p[2]);
            for (double root : roots)
                System.out.printf("%.3f ", root);

        } else {
            System.out.println("\nThe method has a solution only for size: 2 or 3!");
        }
    }

}
