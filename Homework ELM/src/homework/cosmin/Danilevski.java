package homework.cosmin;

public class Danilevski {

    public Danilevski(int[][] A, int size) {
        System.out.println("\nDANILEVSKI METHOD:");

        if (size == 2 || size == 3) {
            float[][] AFinalValues = calculateAFinalValues(A, size);
            printEquation(AFinalValues, size);

        } else {
            System.out.println("\nThe method has a solution only for size: 2 or 3!");
        }
    }

    private float[][] calculateAFinalValues(int[][] A, int size) {
        float[][] AFinalValues = convertIntToFloatMatrix(A, size);
        System.out.println("\nValues of matrix A^0:");
        MatrixUtility.printMatrix(AFinalValues, size);

        float[][] M = initM(A, size);
        System.out.println("\nValues of matrix M1:");
        MatrixUtility.printMatrix(M, size);

        float[][] inverseOfM = initInverseOfM(A, size);
        System.out.println("\nInverse of M1:");
        MatrixUtility.printMatrix(inverseOfM, size);

        System.out.println("\nValues of matrix A^1:");
        AFinalValues = MatrixUtility.multiply(size, size, MatrixUtility.multiply(size, size, inverseOfM, size, size, A), size, size, M);
        MatrixUtility.printMatrix(AFinalValues, size);

        if (size == 3) {
            M = new float[][]{
                    {1 / AFinalValues[size - 2][0], -(AFinalValues[size - 2][1] / AFinalValues[size - 2][0]), -(AFinalValues[size - 2][2] / AFinalValues[size - 2][0])},
                    {0, 1, 0},
                    {0, 0, 1},
            };

            System.out.println("\nValues of matrix M2:");
            MatrixUtility.printMatrix(M, size);

            inverseOfM = new float[][]{
                    {AFinalValues[size - 2][0], AFinalValues[size - 2][1], AFinalValues[size - 2][2]},
                    {0, 1, 0},
                    {0, 0, 1},
            };

            System.out.println("\nInverse of M2:");
            MatrixUtility.printMatrix(inverseOfM, size);

            System.out.println("\nValues of matrix A^2:");
            AFinalValues = MatrixUtility.multiply(size, size, MatrixUtility.multiply(size, size, inverseOfM, size, size, AFinalValues), size, size, M);
            MatrixUtility.printMatrix(AFinalValues, size);
        }

        return AFinalValues;
    }

    // Initialize the values for M1
    private float[][] initM(int[][] A, int size) {
        float[][] M = new float[size][size];

        if (size == 2) {
            M = new float[][]{
                    {((float) 1 / A[size - 1][size - 2]), -((float) A[size - 1][1] / A[size - 1][size - 2])},
                    {0, 1},
            };

        } else if (size == 3) {
            M = new float[][]{
                    {1, 0, 0},
                    {-((float) A[size - 1][0] / A[size - 1][size - 2]), ((float) 1 / A[size - 1][size - 2]), -((float) A[size - 1][2] / A[size - 1][size - 2])},
                    {0, 0, 1},
            };
        }

        return M;
    }

    // Initialize the values for inverse of M1
    private float[][] initInverseOfM(int[][] A, int size) {
        float[][] inverseOfM = new float[size][size];

        if (size == 2) {
            inverseOfM = new float[][]{
                    {A[size - 1][0], A[size - 1][1]},
                    {0, 1},
            };

        } else if (size == 3) {
            inverseOfM = new float[][]{
                    {1, 0, 0},
                    {A[size - 1][0], A[size - 1][1], A[size - 1][2]},
                    {0, 0, 1},
            };
        }

        return inverseOfM;
    }

    // Convert a matrix of integers into a matrix of floats
    private float[][] convertIntToFloatMatrix(int[][] matrixInt, int size) {
        float[][] matrixFloat = new float[size][size];

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                matrixFloat[i][j] = matrixInt[i][j];

        return matrixFloat;
    }

    private void printEquation(float[][] A, int size) {
        if (size == 2) {
            System.out.println("\nThe equation is:");
            System.out.println("x^2 + (" + (-(int) A[0][0]) + ") * x + (" + (-(int) A[0][1]) + ") = 0");
            System.out.println("\nand the eigenvalues are:");
            double[] roots = EquationUtility.solveQuadraticEquation(1, (-(int) A[0][0]), (-(int) A[0][1]));
            for (double root : roots)
                System.out.printf("%.3f ", root);

        } else if (size == 3) {
            System.out.println("\nThe equation is:");
            System.out.println("x^3 + (" + (-(int) A[0][0]) + ") * x^2 + (" + (-(int) A[0][1]) + ") * x + " + (-(int) A[0][2]) + " = 0");
            System.out.println("\nand the eigenvalues are:");
            double[] roots = EquationUtility.solveCubicEquation(1, (-(int) A[0][0]), (-(int) A[0][1]), (-(int) A[0][2]));
            for (double root : roots)
                System.out.printf("%.3f ", root);

        } else {
            System.out.println("\nThe method has a solution only for size: 2 or 3!");
        }
    }

}
