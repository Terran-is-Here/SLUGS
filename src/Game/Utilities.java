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
    
    public static void quickSortReferenceData(ArrayList<ReferenceDataEntry> _arrayListToSort) {
        quickSortStep(_arrayListToSort, 0, (_arrayListToSort.size()-1));
        
    };
            
    private static void quickSortStep(ArrayList<ReferenceDataEntry> _arrayListToSort, int lowIndex, int highIndex) {
        if (lowIndex < highIndex) {
            int parititonIndex = quickSortParition(_arrayListToSort, lowIndex, highIndex); 
                
            quickSortStep(_arrayListToSort, lowIndex, parititonIndex-1); 
            quickSortStep(_arrayListToSort, parititonIndex+1, highIndex); 
        }
    }; 
    
    private static int quickSortParition (ArrayList<ReferenceDataEntry> inputArrayList, int lowIndex, int highIndex) {
        // We use the internalIdentifier as our pivot tester. 
        ReferenceDataEntry pivotMainObject = inputArrayList.get(highIndex); 
        String pivot = pivotMainObject.getIdentifierName(); 
        int small = lowIndex-1; 
        
        ReferenceDataEntry bufferReferenceDataEntry; 
        int comparison; 
        for (int j = lowIndex; j < highIndex; j++) {
            comparison = inputArrayList.get(j).getIdentifierName().compareTo(pivot); 
            if (comparison < 0) {
                small += 1; 
                
                bufferReferenceDataEntry = inputArrayList.get(small); 
                inputArrayList.set(small, inputArrayList.get(j));
                inputArrayList.set(j, bufferReferenceDataEntry); 
            }
        }
        bufferReferenceDataEntry = inputArrayList.get(small+1); 
        inputArrayList.set(small+1, pivotMainObject); 
        inputArrayList.set(highIndex, bufferReferenceDataEntry); 
    
        return (small+1); 
    }
}
