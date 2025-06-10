import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Math;


/*********************************************************
 * Author: Sauban Kidwai                                 *
 * Date: 17 May 2023                                     *
 * Purpose: An object class for the project part of the  *
 * assignment                                            *
 *********************************************************/

public class Project {

    private String[] province;
    private String[] beneficiary;
    private String[] beneficiaryNum;
    private String[] assetClass;
    private String[] name;
    private String[] stage;

    /*********************************************************
     * IMPORT: String filename                               *
     * EXPORT: None                                          *
     *                                                       *
     * ASSERT: The file is opened and parsed. Each           *
     * appropriate column is split and assigned to its       *
     * respective variable fields to be used later when      *
     * called.                                               *
     *********************************************************/

     public void readDataFromFile(String filename) {
        try {
            File file = new File(filename);
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
    
            // Count the number of rows in the CSV file
            int numRows = 0;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                numRows++;
            }
            bufferedReader.close();
    
            // Create arrays to hold data from the columns of interest
            province = new String[numRows - 1];
            beneficiary = new String[numRows - 1];
            beneficiaryNum = new String[numRows - 1];
            assetClass = new String[numRows - 1];
            name = new String[numRows - 1];
            stage = new String[numRows - 1];
            int index = 0;
    
            fileInputStream = new FileInputStream(file);
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            
            // Ignore the first line containing headers
            bufferedReader.readLine();
    
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
    
                // Save the data into the corresponding arrays
                province[index] = data[0];
                beneficiary[index] = data[1];
                beneficiaryNum[index] = data[2];
                assetClass[index] = data[3];
                name[index] = data[4];
                stage[index] = data[6];
    
                index++;
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    

    /*********************************************************
     * IMPORT: None                                          *
     * EXPORT: Total projects in canada                      *
     *                                                       *
     * ASSERT: The file is opened and the province array is  *
     * read to figure out the total length and then it is    *
     * outputted to the terminal.                            *
     *********************************************************/
    
    public int totalProjects() {
        return province.length;
    }

    /*********************************************************
     * IMPORT: None                                          *
     * EXPORT: Integer totOngoing                            *
     *                                                       *
     * ASSERT: This method checks to see how many rows       * 
     * say "Ongoing" and returns the count of it.            *
     *********************************************************/

    public int totalOngoing() {
        int totOngoing = 0;
        for (String projectStage : stage) {
            if (projectStage.equals("Ongoing")) {
                totOngoing++;
            }
        }
        return totOngoing;
    }

    /*********************************************************
     * IMPORT: None                                          *
     * EXPORT: Integer totComp                                       *
     *                                                       *
     * ASSERT: This method checks to see how many rows       * 
     * say "Completed" and returns the count of it.          *
     *********************************************************/

    public int totalComp() {
        int totComp = 0;
        for (String projectStage : stage) {
            if (projectStage.equals("Completed")) {
                totComp++;
            }
        }
        return totComp;
    }

    /*********************************************************
     * IMPORT: None                                          *
     * EXPORT: double percentage                             *
     *                                                       *
     * ASSERT: This method returns the percentage of         *
     * completed projects against the total amount of        *
     * projects.                                             *
     *********************************************************/

    public double compPercentage() {
        int totalProjects = stage.length;
        int completedProjects = 0;
        for (String projectStage : stage) {
            if (projectStage.equals("Completed")) {
                completedProjects++;
            }
        }
        double percentage = (completedProjects / (double) totalProjects) * 100.0;
        return Math.round(percentage * 100.0) / 100.0;
    }
    
    /*********************************************************
     * IMPORT: String provinceName                           *
     * EXPORT: double percentage                             *
     *                                                       *
     * ASSERT: This method returns the percentage of         *
     * projects for a specific province                      *
     * against the total amount of projects.                 *
     *********************************************************/    

    public double projectPercentage(String provinceName) {
        int count = 0;
        for (int i = 0; i < province.length; i++) {
            if (province[i].equals(provinceName)) {
                count++;
            }
        }
        double percentage = (count / (double) province.length) * 100.0;
        return Math.round(percentage * 100.0) / 100.0;
    }

    /*********************************************************
     * IMPORT: String provinceName                           *
     * EXPORT: Integer count                                 *
     *                                                       *
     * ASSERT: This method returns the percentage of         *
     * projects for a specific province                      *
     * against the total amount of projects.                 *
     *********************************************************/ 

    public int countProjectsInProvince(String provinceName) {
        int count = 0;
        for (int i = 0; i < province.length; i++) {
            if (province[i].equals(provinceName)) {
                count++;
            }
        }
        return count;
    }

    /*********************************************************
     * IMPORT: String provinceName                           *
     * EXPORT: double percentage                             *
     *                                                       *
     * ASSERT: This method returns the percentage of         *
     * Ongoing projects for a specific province              *
     * against the total amount of projects in that province *
     *********************************************************/ 

    public double ongoingPercentage(String provinceName) {
        int totalProjects = 0;
        int ongoingProjects = 0;
        for (int i = 0; i < province.length; i++) {
            if (province[i].equals(provinceName)) {
                totalProjects++;
                if (stage[i].equals("Ongoing")) {
                    ongoingProjects++;
                }
            }
        }
        double percentage = (ongoingProjects / (double) totalProjects) * 100.0;
        return Math.round(percentage * 100.0) / 100.0;
    }

    /*********************************************************
     * IMPORT: String provinceName                           *
     * EXPORT: Integer ongoing Projects                      *
     *                                                       *
     * ASSERT: This method returns the number of             *
     * Ongoing projects for a specific province              *
     *********************************************************/ 

    public int ongoingNumber(String provinceName) {

        int ongoingProjects = 0;
        for (int i = 0; i < province.length; i++) {
            if (province[i].equals(provinceName)) {
                if (stage[i].equals("Ongoing")) {
                    ongoingProjects++;
                }
            }
        }
        return ongoingProjects;
    }

    /*********************************************************
     * IMPORT: String provinceName                           *
     * EXPORT: double percentage                             *
     *                                                       *
     * ASSERT: This method returns the percentage of         *
     * completed projects for a specific province            *
     * against the total amount of projects in that province *
     *********************************************************/ 

    public double completedPercentage(String provinceName) {
        int totalProjects = 0;
        int completedProjects = 0;
        for (int i = 0; i < province.length; i++) {
            if (province[i].equals(provinceName)) {
                totalProjects++;
                if (stage[i].equals("Completed")) {
                    completedProjects++;
                }
            }
        }

        double percentage = (completedProjects / (double) totalProjects) * 100.0;
        return Math.round(percentage * 100.0) / 100.0;
    }


    /*********************************************************
     * IMPORT: String provinceName                           *
     * EXPORT: Integer completedProjects                     *
     *                                                       *
     * ASSERT: This method returns the number of             *
     * projects for a specific province                      *
     * against the total amount of projects in that province *
     *********************************************************/ 

    public int completedNumber(String provinceName) {

        int completedProjects = 0;
        for (int i = 0; i < province.length; i++) {
            if (province[i].equals(provinceName)) {
                if (stage[i].equals("Completed")) {
                    completedProjects++;
                }
            }
        }
        return completedProjects;
    }


    private Location projectLocation;

    public Project(double[] latitude, double[] longitude, String[] coordsys) {
        this.projectLocation = new Location(latitude, longitude, coordsys);
    }

    public Project() {
    }


    public Location getProjectLocation() {
        return projectLocation;
    }

    // Accessors for the arrays
    public String[] getProvince() {
        return province;
    }

    public String[] getBeneficiary() {
        return beneficiary;
    }

    public String[] getBeneficiaryNum() {
        return beneficiaryNum;
    }

    public String[] getAssetClass() {
        return assetClass;
    }

    public String[] getName() {
        return name;
    }

    public String[] getStage() {
        return stage;
    }

    // Mutators for the arrays
    public void setProvince(String[] province) {
        this.province = province;
    }

    public void setBeneficiary(String[] beneficiary) {
        this.beneficiary = beneficiary;
    }

    public void setBeneficiaryNum(String[] beneficiaryNum) {
        this.beneficiaryNum = beneficiaryNum;
    }

    public void setAssetClass(String[] assetClass) {
        this.assetClass = assetClass;
    }

    public void setName(String[] name) {
        this.name = name;
    }

    public void setStage(String[] stage) {
        this.stage = stage;
    }
}