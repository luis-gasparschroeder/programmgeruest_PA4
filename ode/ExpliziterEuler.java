package ode;

import java.util.Arrays;

/**
 * Das Einschrittverfahren "Expliziter Euler"
 *
 * @author braeckle
 *
 */
public class ExpliziterEuler implements Einschrittverfahren {

    public double[] nextStep(double[] y_k, double t, double delta_t, ODE ode) {
        // TODO: diese Methode ist zu implementieren

        // Edge Cases
        if (y_k == null || ode == null) {
            throw new IllegalArgumentException("Input arrays and ODE cannot be null.");
        }
        if (delta_t <= 0) {
            throw new IllegalArgumentException("Step size must be positive.");
        }

        // Implementierung
        int n = y_k.length;
        double[] dy = ode.auswerten(t, y_k);
        double[] y_k_plus_1 = new double[n];
        for (int i = 0; i < n; i++) {
            y_k_plus_1[i] = y_k[i] + delta_t * dy[i];
        }
        return y_k_plus_1;
    }

}
