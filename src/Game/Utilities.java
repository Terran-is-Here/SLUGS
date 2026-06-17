/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game;

/**
 * Utility class for math and search functions. 
 * @author plcau
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Math; 
import java.util.ArrayList; 
import java.util.Iterator; 
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;
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
        
        // If r = 1; then calculate it as the difference between the lower and upper bound (inclusive). 
        if (commonRatio == 1.0) {
            output = (upperBound-lowerBound + 1.0) * commonRatio; 
        }
        
        // General formula for calculating the sum of a geometric series. 
        // Given lower bound a, upper bound b, and common ratio r, this evaluates as (r^a - r^b+1) / (1-r) 
        else {
            output = (Math.pow(commonRatio, lowerBound) - Math.pow(commonRatio, upperBound+1))/(1-commonRatio); 
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
    
    public static String getSign(double number) {
        String buffer; 
        buffer = Double.toString(Math.signum(number));
        if (buffer.equals("1.0")) {
            return "+";
        }
        else {
            return ""; 
        }
    }
    
    public static String getDate(int dateValue) {
        int totalValue = dateValue + 360; 
        int year = totalValue/365; 
        int day = (totalValue%365) + 1;
        String output = "Cycle: " + String.valueOf(year) + " Day: " + String.valueOf(day); 
        return output;
    }
    
    /**
     * Searches for an object with identifierName within inputArrayList using linear search; provided that this object extends the AbstractGameObject class. 
     * @param <T> Any object which inherits the AbstractGameObject class.
     * @param inputArrayList An input array of objects which inherit the AbstractGameObject class.
     * @param identifierName The identifier to search for. 
     * @return Returns the first match found if it exists. If not, returns null. 
     */
    @SuppressWarnings("unchecked")
    public static <T extends AbstractGameObject> T findObject(ArrayList<T> inputArrayList, String identifierName) {
        Iterator inputArrayListIterator = inputArrayList.iterator(); 
        T currentObject; 
        while (inputArrayListIterator.hasNext()) {
            currentObject = (T) inputArrayListIterator.next(); 
            if (currentObject.getIdentifier().equals(identifierName)) {
                return (T) currentObject; }
        }
        
        return null; 
    }
    
    public static double round(double value, int places) {
        BigDecimal bigDecimalBuffer = BigDecimal.valueOf(value); 
        bigDecimalBuffer = bigDecimalBuffer.setScale(places, RoundingMode.HALF_UP); 
        return bigDecimalBuffer.doubleValue(); 
    }
    
    public static void writeTxtFile(String fileName, ArrayList<String> textToWrite) {
        try {
            
            // Creates a new FileWriter for a .txt file with a fileName file name. 
            FileWriter currentFileWriter = new FileWriter(fileName, true); 
            
            // Iterates through every entry within textToWrite. 
            Iterator currentLine = textToWrite.iterator(); 
            
            while (currentLine.hasNext()) {
                // Writes current entry within currentLine into fileName
                currentFileWriter.write((String) currentLine.next());
                
                // Adds line break (\n) in between every line
                currentFileWriter.write(System.lineSeparator()); 
            }
            // Adds line break to separate multiple instances of writeTxtFile being called. 
            currentFileWriter.write(System.lineSeparator()); 
            
            // Close FileWriter after writing operation is done.
            currentFileWriter.close();
            
        }
        // Catches general IO exceptions possibly raised by other factors. 
        catch (IOException e) {
            System.out.println("An IO error occured.");
            e.printStackTrace(); 
        }
    }
    
    public static ArrayList<String> readTxtFile (String fileName) {
        
        //Makes buffer ArrayList for output. 
        ArrayList<String> outputArray = new ArrayList<String>();
        try {
            
            // Opens up .txt file with fileName filename. 
            File openedFile = new File(fileName); 
            
            // Creates a new Scanner object for openedFile
            Scanner fileScanner = new Scanner(openedFile); 
            
            // Iterates through every line of the .txt file. 
            while (fileScanner.hasNextLine()) {
                
            // For every line within the .txt file; adds it to the end of the outputArray ArrayList object. 
                outputArray.add(fileScanner.nextLine());
            }
            
            // Close fileScanner to save on memory
            fileScanner.close(); 
            
            // Return outputArray as output. 
            return outputArray; 
        }
        
        // Runs in case where the file does not exist; print error stack + error message and return a null value. 
        catch(FileNotFoundException e) {
            System.out.println("File not found."); 
            e.printStackTrace(); 
            return null;
        }
        
    }
}
