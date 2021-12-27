package homework.cosmin;

import java.util.Arrays;

public class DirectPowerMethod {

    public DirectPowerMethod(int[][] A, int size, int[] yZero) {
        System.out.println("\nDIRECT POWER METHOD:");

        if (yZero.length == size) {
            calculateLambda(A, size, yZero);

        } else {
            System.out.println("\ny^0 length is invalid!");
        }
    }

    private void calculateLambda(int[][] A, int size, int[] yZero) {
        int[] yLast = yZero;
        int[] yBeforeLast = new int[size];

        for (int i = 0; i < 11; i++) {
            yLast = MatrixUtility.multiply(size, size, A, size, yLast);
            System.out.println("\ny(" + (i + 1) + "): " + Arrays.toString(yLast));

            if (i == 9) {
                yBeforeLast = yLast;
            }
        }

        float[] lambda = new float[size];
        float sum1Below = 0;
        float sum1Above = 0;
        float sum2 = 0;

        for (int i = 0; i < size; i++) {
            lambda[i] = (float) yLast[i] / yBeforeLast[i];
            System.out.println("\nlambda1: " + lambda[i]);

            sum1Above += yLast[i];
            sum1Below += yBeforeLast[i];
            sum2 += lambda[i];
        }

        float sum1 = sum1Above / sum1Below;
        sum2 /= size;
        System.out.println("\nlambda1: " + sum1);
        System.out.println("\nlambda1: " + sum2);

        if (valuesAreEqual(lambda, size)) {
            if ((int) sum1 == (int) sum2) {
                if (valuesAreEqual(lambda, size) == ((int) sum1 == (int) sum2)) {
                    System.out.println("\nIn conclusion, lambda1 = " + (int) sum1);
                    System.out.println("\nThe eigenvector is given by: " + Arrays.toString(yLast));
                }
            }
        }
    }

    private boolean valuesAreEqual(float[] lambda, int size) {
        for (int i = 0; i < size - 1; i++) {
            if ((int) lambda[i] == (int) lambda[i + 1]) {
                return true;
            }
        }
        return false;
    }

}
