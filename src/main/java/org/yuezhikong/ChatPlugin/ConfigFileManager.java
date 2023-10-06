package org.yuezhikong.ChatPlugin;

import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileManager {
    public void CreateProperties() {
        Properties prop = new Properties();
        try {
            prop.setProperty("API Key", "");
            prop.setProperty("Secret Key", "");
            prop.store(new FileOutputStream("./chat/key.properties"), null);
        } catch (IOException ex) {
        }
    }

    public static @NotNull Properties LoadProperties() {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("./chat/key.properties"));
        } catch (IOException ex) {
        }
        return prop;
    }
}
