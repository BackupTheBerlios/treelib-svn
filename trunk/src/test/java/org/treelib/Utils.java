package org.treelib;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;




public class Utils{

	/**
     * Properties loaded from the properties file.
     */
    private static Properties testProperties = null;

    /**
     * Return the properties from the test properties file.
     * @return the properties from the test properties file
     * @throws IOException If the file cannot be read.
     */
    public static Properties getTestProperties() throws IOException {
        if (testProperties == null) { // load the properties only once
            InputStream is = Utils.class
                    .getResourceAsStream(File.separator + "test.properties");
            testProperties = new Properties();
            testProperties.load(is);
            // configure log4j only once too
            PropertyConfigurator.configure(testProperties
                    .getProperty("test.log4j.file"));
        }

        return testProperties;
    }
    
    public static String getStringValue(String key) throws IOException{
    	return getTestProperties().getProperty(key);
    }
    
    public static int getIntegerValue(String key) throws NumberFormatException, IOException{
    	return Integer.parseInt(getStringValue(key));
    }
    
    public static int getMaxNumberOfNodes() throws NumberFormatException, IOException{
    	return getIntegerValue("max.tree.size");
    }
    
    
    /**
     * Get max # of trees that will be tested.
     * @return
     * @throws IOException 
     * @throws NumberFormatException 
     */
    public static int getTestTreesMax() throws NumberFormatException, IOException{
    	return getIntegerValue("max.trees");
    }
    
    public static File getTreesFile() throws IOException{
    	return new File(getStringValue("test.db.input"));
    }

}