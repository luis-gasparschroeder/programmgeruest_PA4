package ode;

/**
 * Der klassische Runge-Kutta der Ordnung 4
 *
 * @author braeckle
 *
 */
public class RungeKutta4 implements Einschrittverfahren {

    @Override
    /**
     * {@inheritDoc}
     * Bei der Umsetzung koennen die Methoden addVectors und multScalar benutzt werden.
     */
    public double[] nextStep(double[] y_k, double t, double delta_t, ODE ode) {
        // TODO: diese Methode ist zu implementieren
        int n = y_k.length;
        double[] k1 = ode.auswerten(t, y_k);
        if (k1 == null) {
            return null;
        }
        double[] y_tmp1 = addVectors(y_k, multScalar(k1, delta_t / 2));
        double[] k2 = ode.auswerten(t + (delta_t / 2), y_tmp1);
        if (k2 == null) {
            return null;
        }
        double[] y_tmp2 = addVectors(y_k, multScalar(k2, delta_t / 2));
        double[] k3 = ode.auswerten(t + (delta_t / 2), y_tmp2);
        if (k3 == null) {
            return null;
        }
        double[] y_tmp3 = addVectors(y_k, multScalar(k3, delta_t));
        double[] k4 = ode.auswerten(t + delta_t, y_tmp3);
        if (k4 == null) {
            return null;
        }
        double[] y_k_plus_1 = new double[n];
        for (int i = 0; i < n; i++) {
            y_k_plus_1[i] = y_k[i] + (delta_t / 6) * (k1[i] + 2 * k2[i] + 2 * k3[i] + k4[i]);
        }
        return y_k_plus_1;
    }

    /**
     * addiert die zwei Vektoren a und b
     */
    private double[] addVectors(double[] a, double[] b) {
        double[] erg = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            erg[i] = a[i] + b[i];
        }
        return erg;
    }

    /**
     * multipliziert den Skalar scalar auf den Vektor a
     */
    private double[] multScalar(double[] a, double scalar) {
        double[] erg = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            erg[i] = scalar * a[i];
        }
        return erg;
    }

}
