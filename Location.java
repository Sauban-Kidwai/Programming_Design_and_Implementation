import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/*********************************************************
 * Author: Sauban Kidwai                                 *
 * Date: 17 May 2023                                     *
 * Purpose: An location class for the project part of    *
 * the assignment                                        *
 *********************************************************/

public class Location {
    private double[] latitude;
    private double[] longitude;
    private String[] coordSys;


    /*********************************************************
     * IMPORT: String filename                               *
     * EXPORT: None                                          *
     *                                                       *
     * ASSERT: The file is opened and parsed. Each           *
     * appropriate column is split and assigned to its       *
     * respective variable fields to be used later when      *
     * called.                                               *
     *********************************************************/

    public void readDataFromFile(String filePath) {
        int numRows = 0;
        int numCols = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            if ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");
                numCols = columns.length;
                numRows++;
            }
            while ((line = reader.readLine()) != null) {
                numRows++;
            }

        } catch (IOException e) {
            System.out.println("Error reading file.");
            e.printStackTrace();
        }

        double[] latitude = new double[numRows - 1];
        double[] longitude = new double[numRows - 1];
        String[] coordSys = new String[numRows - 1];

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            if ((line = reader.readLine()) != null) {
                // Skip the first line (column headers)
            }

            int row = 0;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");

                latitude[row] = Double.parseDouble(columns[8]); // Latitude is column 9 (index 8)
                longitude[row] = Double.parseDouble(columns[9]); // Longitude is column 10 (index 9)
                coordSys[row] = columns[10]; // CoordSys is column 11 (index 10)

                row++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error reading file.");
            e.printStackTrace();
        }

        setLatitude(latitude);
        setLongitude(longitude);
        setCoordSys(coordSys);
    }

    /*********************************************************
     * IMPORT: double [] latitude, longitude                 *
     *         string [] coordSys                            *
     * EXPORT: None                                          *
     *                                                       *
     * ASSERT: sets latitude, longitude and coordsys         *
     * getters and setters                                   *
     *********************************************************/

    public Location(double[] latitude, double[] longitude, String[] coordSys) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.coordSys = coordSys;
    }

    public Location() {
        // Default constructor
    }

    public double[] getLatitude() {
        return latitude;
    }

    public void setLatitude(double[] latitude) {
        this.latitude = latitude;
    }

    public double[] getLongitude() {
        return longitude;
    }

    public void setLongitude(double[] longitude) {
        this.longitude = longitude;
    }

    public String[] getCoordSys() {
        return coordSys;
    }

    public void setCoordSys(String[] coordSys) {
        this.coordSys = coordSys;
    }

}