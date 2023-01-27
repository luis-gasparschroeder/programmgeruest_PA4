package ode;

/**
 * Das Einschrittverfahren von Heun
 *
 * @author braeckle
 *
 */
public class Heun implements Einschrittverfahren {

    @Override
    /**
     * {@inheritDoc}
     * Nutzen Sie dabei geschickt den Expliziten Euler.
     */
    public double[] nextStep(double[] y_k, double t, double delta_t, ODE ode) {
        // TODO: diese Methode ist zu implementieren

        // Edge Cases
        if(y_k == null || y_k.length == 0) {
            throw new IllegalArgumentException("y_k cannot be null or empty");
        }
        if(ode == null) {
            throw new IllegalArgumentException("ODE cannot be null");
        }
        if(delta_t <= 0) {
            throw new IllegalArgumentException("delta_t must be greater than 0");
        }

        // Implementierung
        int n = y_k.length;
        double[] k1 = ode.auswerten(t, y_k);
        double[] y_tmp = new double[n];
        for (int i = 0; i < n; i++) {
            y_tmp[i] = y_k[i] + delta_t * k1[i];
        }

        double[] k2 = ode.auswerten(t + delta_t, y_tmp);
        double[] y_k_plus_1 = new double[n];
        for (int i = 0; i < n; i++) {
            y_k_plus_1[i] = y_k[i] + (delta_t / 2) * (k1[i] + k2[i]);
        }

        return y_k_plus_1;
    }

}
