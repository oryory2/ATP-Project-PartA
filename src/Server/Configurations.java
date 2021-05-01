package Server;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


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

}

