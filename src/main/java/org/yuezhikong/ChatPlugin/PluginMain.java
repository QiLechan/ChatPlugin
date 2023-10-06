package org.yuezhikong.ChatPlugin;

import org.jetbrains.annotations.NotNull;
import org.yuezhikong.newServer.plugin.Plugin.JavaPlugin;
import org.yuezhikong.newServer.plugin.Tools;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Properties;

import static org.yuezhikong.ChatPlugin.ConfigFileManager.LoadProperties;

public class PluginMain extends JavaPlugin {
    public static void CreateProperties(){
        ConfigFileManager prop = new ConfigFileManager();
        prop.CreateProperties();
    }

    @Override
    public void onLoad() {
        Tools.getServerInstance().getLogger().info("Chat插件正在被加载");
        if (Files.isDirectory(Path.of("./chat")))
        if (!(new File("key.properties").exists())){
            Tools.getServerInstance().getLogger().info("目录下没有检测到配置文件，正在创建");
            CreateProperties();
        }
        @NotNull Properties prop = LoadProperties();
        String APIKey = prop.getProperty("API Key");
        String SecretKey = prop.getProperty("Secret Key");
        if (Objects.equals(APIKey, "") || Objects.equals(SecretKey, "")){
            Tools.getServerInstance().getLogger().info("请在目录下的“key.properties”中填写API Key和Secret Key");
        }
        else{
            Tools.getServerInstance().getLogger().info("Chat插件加载完成");
        }
    }

    @Override
    public void onUnload() {
        Tools.getServerInstance().getLogger().info("Chat插件正在被卸载");
    }
}
