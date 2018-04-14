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
public class EuclideanDistance extends Distance {

    @Override
    public double getDistance(double[] dist1, double[] dist2) {
        double distance = 0;
        for (int index = 0; index < dist1.length; index++) {
            distance += Math.pow(dist1[index] - dist2[index], 2);
        }
        return Math.sqrt(distance);
    }

}
