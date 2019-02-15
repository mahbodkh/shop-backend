package app.store.service.util;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties = null;
    private static String configPath = null;
    private static final String fileName = "application.properties";

    public ConfigReader() {
    }

    private static String getConfigPath() {
        if (configPath == null) {
            initConfigPath();
            System.out.println("configPath=" + configPath);
        }
        return configPath;
    }

    private static synchronized void initConfigPath() {
        String config_name = fileName;
        String path = "";
        if (configPath == null) {
            path = getClassessDir();
            if (Files.exists(Paths.get(path + config_name), new LinkOption[0])) {
                configPath = path;
                return;
            }
            path = getJarRootDirectory();
            if (Files.exists(Paths.get(path + config_name), new LinkOption[0])) {
                configPath = path;
                return;
            }

            URL resource = ConfigReader.class.getClassLoader().getResource("");
            if (resource != null) {
                path = General.addSeparator(resource.getPath());
                if (Files.exists(Paths.get(path + config_name), new LinkOption[0])) {
                    configPath = path;
                    return;
                }
            }

            path = System.getProperty("user.dir") + File.separator;
            if (Files.exists(Paths.get(path + config_name), new LinkOption[0])) {
                configPath = path;
                return;
            }
        }
    }

    private static String getClassessDir() {
        String jarPath = "";

        try {
            String path = General.parseString(ConfigReader.class.getProtectionDomain().getCodeSource().getLocation().getPath());
            jarPath = URLDecoder.decode(path, "UTF-8");
            File f = new File(jarPath, fileName);
            if (!f.exists()) {
                Path pathJar = Paths.get(f.getParent()).getParent();
                jarPath = pathJar.toString();
            }
        } catch (UnsupportedEncodingException var4) {
            var4.printStackTrace();
        }

        return jarPath + "/../classes/";
    }

    private static String getJarRootDirectory() {
        String jarPath = "";

        try {
            String path = General.parseString(ConfigReader.class.getProtectionDomain().getCodeSource().getLocation().getPath());
            jarPath = URLDecoder.decode(path, "UTF-8");
            File f = new File(jarPath, fileName);
            if (!f.exists()) {
                Path pathJar = Paths.get(f.getParent()).getParent();
                jarPath = pathJar.toString();
            }
        } catch (UnsupportedEncodingException var4) {
            var4.printStackTrace();
        }

        return General.addSeparator(jarPath);
    }


    private static synchronized Properties getProperties(String conf) {
        String config_name = getConfigPath() + conf;
        if (properties == null)
            properties = new Properties();
        try {
            FileInputStream inputStream = new FileInputStream(config_name);
            Reader reader = new InputStreamReader(inputStream, "UTF-8");
            Throwable var5 = null;

            try {
                properties.load(reader);
            } catch (Throwable var15) {
                var5 = var15;
                throw var15;
            } finally {
                if (reader != null) {
                    if (var5 != null) {
                        try {
                            reader.close();
                        } catch (Throwable var14) {
                            var5.addSuppressed(var14);
                        }
                    } else {
                        reader.close();
                    }
                }
            }
        } catch (Exception var17) {
            var17.printStackTrace();
        }

        properties.put(config_name, properties);
        return properties;
    }


    public static synchronized String getString(String key) {
        if (properties == null) {
            properties = new Properties();
        }
        InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(fileName);
        if (inputStream != null) {
            try {
                properties.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                throw new FileNotFoundException("property file '" + fileName + "' not found in the classpath");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return General.parseString(properties.getProperty(key)).trim();
    }

    public static synchronized Properties getConfig() {
        return getProperties(fileName);
    }
}
