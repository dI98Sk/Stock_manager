package ru.sfedu.productturnover.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import static ru.sfedu.productturnover.ProductTurnoverClient.CONFIG_PATH;

/**
 * Configuration utility. Allows to get configuration properties from the
 * default configuration file
 *
 * @author Boris Jmailov
 */
public class ConfigurationUtil {

    private static final String DEFAULT_CONFIG_PATH = "./src/main/resources/enviroment.properties";
    private static final Properties configuration = new Properties();
    
    /**
     * Hides default constructor
     */
    public ConfigurationUtil() {
    }
    
    private static Properties getConfiguration() throws IOException {
        if(configuration.isEmpty()){
            loadConfiguration();
        }
        return configuration;
    }

    /**
     * Loads configuration from <code>DEFAULT_CONFIG_PATH</code>
     * @throws IOException In case of the configuration file read failure
     */
    private static void loadConfiguration() throws IOException{
        FileInputStream in;
        
        try {
            in = new FileInputStream(CONFIG_PATH);
        } catch (FileNotFoundException e) {
            in = new FileInputStream(DEFAULT_CONFIG_PATH);
        }
        
        try {
            configuration.load(in);
        } catch (IOException ex) {
            throw new IOException(ex);
        } finally{
            in.close();
        }
    }
    /**
     * Gets configuration entry value
     * @param key Entry key
     *  Entry value by key
     * @throws IOException In case of the configuration file read failure
     */
    public static String getConfigurationEntry(String key) throws IOException{
        try {
            return getConfiguration().getProperty(key);
        }catch(FileNotFoundException e){
            String r="";
            switch(key.toLowerCase()){
                case "db_driver":
                    r="org.postgresql.Driver";
                    break;
                case "db_user":
                    r="postgres";
                    break;
                case "db_pass":
                    r="12345678";
                    break;
                case "db_url":
                    r="jdbc:postgresql://localhost:5432/productturnover";
                    break;
            }
            return r;
        }
    }
    
}
