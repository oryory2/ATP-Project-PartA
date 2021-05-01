package Server;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class Configurations
{
    private static Configurations instance = null;

    private Configurations()
    {
        //
    }

    public static Configurations getInstance()
    {
        if(Configurations.instance == null)
        {
            Configurations.instance = new Configurations();
        }
        return Configurations.instance;
    }

    public void setProperty(String a, String b)
    {
        try
        {
            FileOutputStream outputStream = new FileOutputStream("config.properties.txt");
            byte[] str1 = a.getBytes();
            byte[] str2 = b.getBytes();
            outputStream.write(str1);
            outputStream.write(str2);


        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void writeProperties()
    {
        {
            try (OutputStream output = new FileOutputStream("config.properties.txt")
            {

                Configurations prop
                // set the properties value
                c.setProperty("db.url", "localhost");
                prop.setProperty("db.user", "mkyong");
                prop.setProperty("db.password", "password");

                // save properties to project root folder
                prop.store(output, null);

                System.out.println(prop);

            } catch (IOException io) {
                io.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
}
}

