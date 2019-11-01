/*
 * Lab 01 - Data structure and Algorithm
 * Student Id :000765796
 * 
 */
package comp10205_lab01_hardikkumar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Math.sqrt;

/**
 *
 * @author hardik
 */
public class Comp10205_Lab01_Hardikkumar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //calling Static methods 
       
        getMinimumElevation();
        findNumbersOfPeaks();
        findDistanceBetweenTwoPeaks();
        findMostCommonNumber();
        

//      getPeakArray();
    }

    /**
     * This method load data from ELEVATIONS.TXT file and return Array of data
     *
     * @return Value
     */
    public static String[] loadDataFromFile() {
        final String FILENAME = "ELEVATIONS.TXT";  //load file
        File file = new File(FILENAME); //open file
        byte[] fileData = new byte[(int) file.length()]; 
        try (FileInputStream input = new FileInputStream(file)) {
            input.read(fileData); //reading data of file
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }
        String[] value = new String(fileData).trim().split("\\s+"); //creating array of string

        return value;
        //System.out.println(Arrays.toString(value));
        //System.out.println("\n");
    }

//    public static void getPeakArray(){
//    String[] data = loadDataFromFile();
//        int row = Integer.parseInt(data[0]);
//        int col = Integer.parseInt(data[1]);
//       
//
//        int[][] peak = new int[row][col];
//        
//        for (int i = 0; i < row; i++) {
//            for (int j = 0; j < col; j++) {
//                peak[i][j] = Integer.parseInt(data[i * col + j + 3]);
//                System.out.println(peak[i][j]);
//            }
//        }
//     
//    } 
    
    
    /**
     * Finding Minimum Elevation Number in Data Task 1
     */
    public static void getMinimumElevation() {
        String[] data = loadDataFromFile(); //load data from file
        int row = Integer.parseInt(data[0]); // store number of row
        int col = Integer.parseInt(data[1]); //store number of column
        
        int[][] peak = new int[row][col]; // int array to store data from string array

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                peak[i][j] = Integer.parseInt(data[i * col + j + 3]); 
            }
        }

        int minimum = peak[0][0];
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (peak[i][j] == minimum) {
                    count++;  // to  get count of minimum number 
                } else if (peak[i][j] < minimum) {
                    minimum = peak[i][j];
                    count = 1;
                }
            }
        }
        //System.out.println("Part 1:Minimum Number Elevation in datafile ");
        System.out.println("The Lowest peak is : " + minimum + " , It occurs " + count + " times in map");
       
    }

    /**
     * Finding number of Peaks in Data Task 2
     */
    public static void findNumbersOfPeaks() {
       String[] data = loadDataFromFile(); //load data from file
        int row = Integer.parseInt(data[0]); // store number of row
        int col = Integer.parseInt(data[1]); //store number of column
        int radius = Integer.parseInt(data[2]);  // store radius value
        int[][] peak = new int[row][col]; // int array to store data from string array
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                peak[i][j] = Integer.parseInt(data[i * col + j + 3]);

            }
        }

        //System.out.println("Part 2:Find Peak Number in datafile");
        //setting counter using radius to get 10 * 10 matrix 
        int count=0;
        for (int i = radius; i < row - radius; i++) {
            for (int j = radius; j < col - radius; j++) {
                // require condition for peak number  
                if (peak[i][j] > 94850 && isPeakNumber(peak, i, j, radius)) {
                    //System.out.println(peak[i][j] + " Number is peak in datafile at (" + i + " , " + j + ")");
                    count++;
                }
            }
        }
        System.out.println("The Total Number Of Peaks :"+count);
       
    }

    /**
     *Part 3: Finding Distance between two local Peaks Number
     */
    public static void findDistanceBetweenTwoPeaks() {
       String[] data = loadDataFromFile(); //load data from file
        int row = Integer.parseInt(data[0]); // store number of row
        int col = Integer.parseInt(data[1]); //store number of column
        int radius = Integer.parseInt(data[2]);  // store radius value
        int[][] peak = new int[row][col]; // int array to store data from string array
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                peak[i][j] = Integer.parseInt(data[i * col + j + 3]);

            }
        }
        int count = -1;
        Peaks[] object = new Peaks[1]; //obj of Peaks class to hold peaks 
        for (int i = radius; i < row - radius; i++) {
            for (int j = radius; j < col - radius; j++) {
                //condition check for Peak
                if (peak[i][j] > 94850 && isPeakNumber(peak, i, j, radius)) {
                    count++;
                    if (count >= object.length) {
                        Peaks[] temp = object;  // using temp to hold objects
                        object = new Peaks[count + 2];

                        for (int k = 0; k < temp.length; k++) {
                            object[k] = new Peaks(temp[k].getRow(), temp[k].getCol()); // copy of peak
                        }
                        object[count] = new Peaks(i, j);

                    } else {
                        object[count] = new Peaks(i, j);
                    }
                }
            }
        }

        if (count >= 1) {
            //calculating distance using given formula 
            float distanceOfPeaks = (float) sqrt((object[0].getRow() - object[1].getRow()) * (object[0].getRow() - object[1].getRow()) + (object[0].getCol() - object[1].getCol()) * (object[0].getCol() - object[1].getCol()));
            float tempDistance;
            int r1 = object[0].getRow(), r2 = object[1].getRow(), c1 = object[0].getCol(), c2 = object[1].getCol();
            for (int i = 0; i < count; i++) {
                for (int j = i + 1; j < count; j++) {
                    tempDistance = (float) sqrt((object[i].getRow() - object[j].getRow()) * (object[i].getRow() - object[j].getRow()) + (object[i].getCol() - object[j].getCol()) * (object[i].getCol() - object[j].getCol()));
                    if (tempDistance < distanceOfPeaks) {
                        // getting index of peaks , rows and columns
                        r1 = object[i].getRow(); 
                        r2 = object[j].getRow();
                        c1 = object[i].getCol();
                        c2 = object[j].getCol();

                        distanceOfPeaks = tempDistance;
                    }
                }
            }

            //System.out.println("Part 3 :Distance Between 2 Peaks number ");
            System.out.print("The Minimum Distance Between Two Peaks ( " + r1 + " , " + c1 + " )and ( " + r2 + " , " + c2 + " ) is : ");
            System.out.printf("%.2f", distanceOfPeaks );
            System.out.printf(" m");
            System.out.println();
            
        }
    }

    /**
     * Finding Most Common number in Datafile. 
     * Part 4
     */
    public static void findMostCommonNumber() {
       String[] data = loadDataFromFile(); //load data from file
        int row = Integer.parseInt(data[0]); // store number of row
        int col = Integer.parseInt(data[1]); //store number of column
       
        int[][] peak = new int[row][col]; // int array to store data from string array
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                peak[i][j] = Integer.parseInt(data[i * col + j + 3]);

            }
        }
        long startTime = System.nanoTime();
        int index = 0;
        int[] commonNumber = new int[96000]; // creating array
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (peak[i][j] != 0) {
                    commonNumber[peak[i][j]] = commonNumber[peak[i][j]] + 1;
                    if (commonNumber[peak[i][j]] > commonNumber[index]) {
                        index = peak[i][j];
                    }
                }
            }
        }
        long stopTime = System.nanoTime();
        
        //System.out.println("Part 4: Most Common Number in DataFile");
        System.out.println("The Most Common height in the terrain is  " + index + " ,it occurs " + commonNumber[index] + " times");
        System.out.printf("Time to execute  = %d us]\n", (stopTime - startTime) / 1000);
        System.out.println();
    }

    /**
     * this method check number is peak or not 
     * @param peak
     * @param row
     * @param col
     * @param radius
     * @return boolean
     *
     */
    public static boolean isPeakNumber(int peak[][], int row, int col, int radius) {
        for (int i = row - radius; i <= row + radius; i++) {
            for (int j = col - radius; j <= col + radius; j++) {
                if (peak[i][j] >= peak[row][col] && (i != row || j != col)) {
                    return false;
                }
            }
        }
        return true;
    }

}
