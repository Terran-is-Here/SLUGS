/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game;

/**
 * Utility class for math and search functions. 
 * @author plcau
 */
import java.lang.Math; 
public class Utilities {

    /**
     * Calculates the numerical sum of the range of [lowerBound, upperBound] present in a geometric series with a commonRatio common ratio. 
     * @param lowerBound
     * @param upperBound
     * @param commonRatio
     * @return 
     */
    public static double geometricSeriesInitialSum(double lowerBound, double upperBound, double commonRatio) {
        double output = 0; 
        for (int i =0; i >= upperBound; i++) {
            output += Math.pow(commonRatio, i);
        }
        return output; 
    }
}
