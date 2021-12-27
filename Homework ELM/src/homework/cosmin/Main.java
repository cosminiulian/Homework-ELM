package homework.cosmin;

public class Main {

    public static void main(String[] args) throws Exception {
        // Matrix 1
        MatrixFromFile matrix1 = new MatrixFromFile("files/B3.in");
        System.out.println("\nInitial Matrix:");
        MatrixUtility.printMatrix(matrix1.getMatrix(), matrix1.getSize());

//        // Matrix 2
//        MatrixFromFile matrix2 = new MatrixFromFile("files/B2.in");
//        System.out.println("\nSecond Matrix:");
//        MatrixUtility.printMatrix(matrix2.getMatrix(), matrix2.getSize());
//
//        System.out.println("\nTranspose of Initial Matrix:");
//        MatrixUtility.printMatrix(MatrixUtility.transpose(matrix1.getMatrix(), matrix1.getSize()), matrix1.getSize());
//
//        if (matrix1.getSize() == matrix2.getSize()) {
//            int[][] ADD = MatrixUtility.add(matrix1.getMatrix(), matrix2.getMatrix(), matrix1.getSize());
//            System.out.println("\nInitial Matrix + Second Matrix:");
//            MatrixUtility.printMatrix(ADD, matrix1.getSize());
//
//            int[][] SUB = MatrixUtility.substract(matrix1.getMatrix(), matrix2.getMatrix(), matrix1.getSize());
//            System.out.println("\nInitial Matrix - Second Matrix:");
//            MatrixUtility.printMatrix(SUB, matrix1.getSize());
//        }

        executeKrylovMethod(matrix1.getMatrix(), matrix1.getSize());
        executeLeverrierMethod(matrix1.getMatrix(), matrix1.getSize());
        executeDirectPowerMethod(matrix1.getMatrix(), matrix1.getSize());
        executeInversePowerMethod(matrix1.getMatrix(), matrix1.getSize());
        executeDanilevskiMethod(matrix1.getMatrix(), matrix1.getSize());
    }


    /*
         Uses of mathematical methods
     */

    private static void executeKrylovMethod(int[][] M, int size) {
        // The values of y^0
        int[] yZero = new int[size];
        if (size == 2) yZero = new int[]{1, 0};
        else if (size == 3) yZero = new int[]{1, 1, 1};
        // Execute Krylov Method
        new Krylov(M, size, yZero);
    }

    private static void executeLeverrierMethod(int[][] M, int size) {
        // Execute Leverrier Method
        new Leverrier(M, size);
    }

    private static void executeDirectPowerMethod(int[][] M, int size) {
        // The values of y^0
        int[] yZero = new int[size];
        if (size == 2) yZero = new int[]{1, 1};
        else if (size == 3) yZero = new int[]{1, 1, 1};
        // Execute Direct Power Method
        new DirectPowerMethod(M, size, yZero);
    }

    private static void executeInversePowerMethod(int[][] M, int size) {
        // The values of y^0
        int[] yZero = new int[size];
        if (size == 2) yZero = new int[]{1, 1};
        else if (size == 3) yZero = new int[]{1, 1, 1};
        // Execute Inverse Power Method
        new InversePowerMethod(M, size, yZero);
    }

    private static void executeDanilevskiMethod(int[][] M, int size) {
        // Execute Danilevski Method
        new Danilevski(M, size);
    }

}
