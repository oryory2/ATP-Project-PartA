package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.search.BestFirstSearch;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.DepthFirstSearch;

import java.io.*;
import java.util.Properties;


public class Configurations
{
    private static Configurations instance = null;

     /**
     * constructor
     * enter default values for the first time we boot the server
     * The file will contain settings for:
     * threadPoolSize, mazeGeneratingAlgorithm, mazeSearchingAlgorithm, CompressorType
     */
    private Configurations()
    {
        try (OutputStream output = new FileOutputStream("config.properties.txt"))
        {
            // set the Default Setting of the Program

            Properties prop = new Properties();
            prop.setProperty("threadPoolSize", "10");
            prop.setProperty("mazeGeneratingAlgorithm", "MyMazeGenerator");
            prop.setProperty("mazeSearchingAlgorithm", "BestFirstSearch");
            prop.setProperty("CompressorType", "MyCompressorOutputStream");

            // save properties to project root folder
            prop.store(output, null);

        }
        catch (IOException io)
        {
            io.printStackTrace();
        }


    }

     /**
     * check if there is no instance created for 'Configurations'
     * no instance ---> create and return
     * yes instance --> return the instance
     * (singleton design pattern)
     * @return the Only Instance(Configurations)
     */
    public static Configurations getInstance()
    {
        if (Configurations.instance == null)
        {
            Configurations.instance = new Configurations();
        }
        return Configurations.instance;
    }

     /**
     * write the properties obtained as parameters to the configuration file
     * override the existing properties
     * @param NumberOfThreads Number of threads to be opened
     * @param MGA The algorithm for creating a maze
     * @param MSA The algorithm for solving a maze
     * @param CompressorType the Compression algorithm
     */
    public void writeProp(String NumberOfThreads, String MGA ,String MSA, String CompressorType){
        try (OutputStream output = new FileOutputStream("config.properties.txt"))
        {
            Properties prop = new Properties();
            // set the properties value
            prop.setProperty("threadPoolSize", NumberOfThreads);
            prop.setProperty("mazeGeneratingAlgorithm", MGA);
            prop.setProperty("mazeSearchingAlgorithm", MSA);
            prop.setProperty("CompressorType", CompressorType);

            // save properties to project root folder
            prop.store(output, null);
        }
        catch (IOException io)
        {
            io.printStackTrace();
        }
    }

     /**
     * load the properties from the configuration file
     * @return array of the properties from the configuration file(Object[])
     */
    public Object[] LoadProp(){
        try (InputStream input = new FileInputStream("config.properties.txt")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);
            String[] PropArr = new String[4];

            PropArr[0] = prop.getProperty("threadPoolSize");
            PropArr[1] = prop.getProperty("mazeGeneratingAlgorithm");
            PropArr[2] = prop.getProperty("mazeSearchingAlgorithm");
            PropArr[3] = prop.getProperty("CompressorType");

            Object[] settingArr = new Object[4];
            settingArr[0] = Integer.parseInt(PropArr[0]); // Setting the number of threads will be in the program

            if(PropArr[1].equals("EmptyMazeGenerator")) // Setting the Generating algorithm for the program
                settingArr[1] = new EmptyMazeGenerator();
            else if (PropArr[1].equals("SimpleMazeGenerator"))
                settingArr[1] = new SimpleMazeGenerator();
            else
                settingArr[1] = new MyMazeGenerator();

            if(PropArr[2].equals("BestFirstSearch")) // Setting the Solving algorithm for the program
                settingArr[2] = new BestFirstSearch();
            else if (PropArr[2].equals("BreadthFirstSearch"))
                settingArr[2] = new BreadthFirstSearch();
            else
                settingArr[2] = new DepthFirstSearch();

            settingArr[3] = PropArr[3]; // Setting the Compressor type for the program

            return settingArr;

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }



}

