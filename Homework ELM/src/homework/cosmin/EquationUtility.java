package homework.cosmin;

public class EquationUtility {

    // Function to find the roots
    // of quadratic equation
    public static double[] solveQuadraticEquation(double a, double b, double c) {
        double[] equationRoots = new double[2];

        // delta (b^2 - 4ac)
        double d = b * b - 4 * a * c;

        // check if delta is greater than 0
        if (d > 0) {
            // two real and distinct roots
            equationRoots[0] = (-b + Math.sqrt(d)) / (2 * a);
            equationRoots[1] = (-b - Math.sqrt(d)) / (2 * a);
        }

        // check if delta is equal to 0
        else if (d == 0) {
            // two real and equal roots
            equationRoots[0] = equationRoots[1] = -b / (2 * a);
        }

        // if delta is less than zero
        else {
            System.out.println("The roots of the equation are complex and distinct numbers!");
        }

        return equationRoots;
    }

    public static double[] solveCubicEquation(double a, double b, double c, double d) {
        double[] result;

        if (d != 0) {

            if (a != 1) {
                b = b / a;
                c = c / a;
                d = d / a;
            }

            double p = c / 3 - b * b / 9;
            double q = b * b * b / 27 - b * c / 6 + d / 2;
            double D = p * p * p + q * q;

            if (Double.compare(D, 0) >= 0) {
                if (Double.compare(D, 0) == 0) {
                    double r = Math.cbrt(-q);
                    result = new double[2];
                    result[0] = 2 * r;
                    result[1] = -r;
                } else {
                    double r = Math.cbrt(-q + Math.sqrt(D));
                    double s = Math.cbrt(-q - Math.sqrt(D));
                    result = new double[1];
                    result[0] = r + s;
                }
            } else {
                double ang = Math.acos(-q / Math.sqrt(-p * p * p));
                double r = 2 * Math.sqrt(-p);
                result = new double[3];
                for (int k = -1; k <= 1; k++) {
                    double theta = (ang - 2 * Math.PI * k) / 3;
                    result[k + 1] = r * Math.cos(theta);
                }

            }
            for (int i = 0; i < result.length; i++) {
                result[i] = result[i] - b / 3;
            }

        } else {
            result = new double[3];
            double[] roots = solveQuadraticEquation(a, b, c);
            result[0] = roots[0];
            result[1] = roots[1];
            result[2] = 0;
        }
        return result;

    }

}
