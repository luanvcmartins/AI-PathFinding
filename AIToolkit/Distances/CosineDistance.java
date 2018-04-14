/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AIToolkit.Distances;

import AIToolkit.Distances.Distance;

/**
 *
 * @author luan
 */
public class CosineDistance extends Distance {

    @Override
    public double getDistance(double[] dist1, double[] dist2) {
        double distance = 0, sum1 = 0, sum2 = 0;
        for (int index = 0; index < dist1.length; index++) {
            distance = dist1[index] * dist2[index];
            sum1 += Math.pow(dist1[index], 2);
            sum2 += Math.pow(dist2[index], 2);
        }

        sum1 = Math.sqrt(sum1);
        sum2 = Math.sqrt(sum2);
        return 1 - (distance / (sum1 * sum2));
    }

}
