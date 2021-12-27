package homework.cosmin;

import java.util.Arrays;

public class InversePowerMethod {

    public InversePowerMethod(int[][] A, int size, int[] yZero) {
        System.out.println("\nINVERSE POWER METHOD:");

        if (yZero.length == size) {
            float[][] inverseOfA = new float[size][size];
            MatrixUtility.inverse(A, size, inverseOfA);
            System.out.println("\n The inverse of matrix:");
            MatrixUtility.printMatrix(inverseOfA, size);

            calculateLambda(inverseOfA, size, yZero);

        } else {
            System.out.println("\ny^0 length is invalid!");
        }
    }

    private void calculateLambda(float[][] A, int size, int[] yZero) {
        double[] yLast = convertIntToDoubleValues(yZero, size);
        double[] yBeforeLast = new double[size];

        for (int i = 0; i < 12; i++) {
            yLast = MatrixUtility.multiply(size, size, A, size, yLast);
            System.out.println("\ny(" + (i + 1) + "): " + Arrays.toString(yLast));

            if (i == 10) {
                yBeforeLast = yLast;
            }
        }

        double[] lambda = new double[size];
        double sum1Below = 0;
        double sum1Above = 0;
        double sum2 = 0;

        for (int i = 0; i < size; i++) {
            lambda[i] = yLast[i] / yBeforeLast[i];
            System.out.println("\nlambda1: " + lambda[i]);

            sum1Above += yLast[i];
            sum1Below += yBeforeLast[i];
            sum2 += lambda[i];
        }

        double sum1 = sum1Above / sum1Below;
        sum2 /= size;
        System.out.println("\nlambda1: " + sum1);
        System.out.println("\nlambda1: " + sum2);

        System.out.println("\nIn conclusion, lambda1 approx. = 0");
        System.out.println("The eigenvector is given by: " + Arrays.toString(yLast));
    }

    private double[] convertIntToDoubleValues(int[] yInt, int size) {
        double[] yDouble = new double[size];

        for (int i = 0; i < size; i++) {
            yDouble[i] = yInt[i];
        }

        return yDouble;
    }

}
