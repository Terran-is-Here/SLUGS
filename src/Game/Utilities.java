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
import java.util.ArrayList; 


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
    
    public static <T extends AbstractGameObject> void quickSort(ArrayList<T> _arrayListToSort) {
        quickSortStep(_arrayListToSort, 0, (_arrayListToSort.size()-1));
        
    };
            
    private static <T extends AbstractGameObject> void quickSortStep(ArrayList<T> _arrayListToSort, int lowIndex, int highIndex) {
        if (lowIndex < highIndex) {
            int parititonIndex = quickSortParition(_arrayListToSort, lowIndex, highIndex); 
                
            quickSortStep(_arrayListToSort, lowIndex, parititonIndex-1); 
            quickSortStep(_arrayListToSort, parititonIndex+1, highIndex); 
        }
    }; 
    
    private static <T extends AbstractGameObject> int quickSortParition(ArrayList<T> inputArrayList, int lowIndex, int highIndex) {
        // We use the internalIdentifier as our pivot tester. 
        T pivotMainObject = inputArrayList.get(highIndex); 
        String pivot = pivotMainObject.getIdentifier(); 
        int small = lowIndex-1; 
        
        T bufferAbstractGameObject; 
        int comparison; 
        for (int j = lowIndex; j < highIndex; j++) {
            comparison = inputArrayList.get(j).getIdentifier().compareTo(pivot); 
            if (comparison < 0) {
                small += 1; 
                
                bufferAbstractGameObject = inputArrayList.get(small); 
                inputArrayList.set(small, inputArrayList.get(j));
                inputArrayList.set(j, bufferAbstractGameObject); 
            }
        }
        bufferAbstractGameObject = inputArrayList.get(small+1); 
        inputArrayList.set(small+1, pivotMainObject); 
        inputArrayList.set(highIndex, bufferAbstractGameObject); 
    
        return (small+1); 
    }
}
