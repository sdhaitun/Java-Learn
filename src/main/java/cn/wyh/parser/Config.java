package cn.wyh.parser;

import java.io.*;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by Stan on 2015/8/11.
 */
public class Config {
    private static Config config = null;
    private Properties pop = new Properties();

    private Config() {
        loadConfig();
    }

    public static Config getInstance() {
        if (config == null) {
            config = new Config();
        }
        return config;
    }

    /**
     * ¼ÓÔØpropertiesÎÄ¼þ
     */
    private void loadConfig() {
        try {
            InputStream in = new BufferedInputStream(new FileInputStream("config.properties"));
            pop.load(in);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getByKey(String key){
        String value = pop.getProperty(key);
        return value;
    }
}
