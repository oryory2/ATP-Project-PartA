package Server;

import java.io.*;
import java.util.Properties;


public class Configurations
{
    private static Configurations instance = null;

    private Configurations() {
        //
    }

    public static Configurations getInstance() {
        if (Configurations.instance == null) {
            Configurations.instance = new Configurations();
        }
        return Configurations.instance;
    }

    public static void writeProp(String NumberOfThreads , String MGA  ,String MSA ){
        try (OutputStream output = new FileOutputStream("config.properties.txt")) {

            Properties prop = new Properties();
            // set the properties value
            prop.setProperty("threadPoolSize", NumberOfThreads);
            prop.setProperty("mazeGeneratingAlgorithm", MGA);
            prop.setProperty("mazeSearchingAlgorithm", MSA);

            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public static String[] LoadProp(){
        try (InputStream input = new FileInputStream("config.properties.txt")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);
            String[] PropArr = new String[3];
            PropArr[0] = prop.getProperty("threadPoolSize");
            PropArr[1] = prop.getProperty("mazeGeneratingAlgorithm");
            PropArr[2] = prop.getProperty("mazeSearchingAlgorithm");
            return PropArr;

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}

