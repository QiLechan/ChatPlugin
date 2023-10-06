package org.yuezhikong.ChatPlugin;

import org.yuezhikong.newServer.plugin.Plugin.JavaPlugin;
import org.yuezhikong.newServer.plugin.Tools;
import org.yuezhikong.newServer.plugin.configuration.PluginConfig;

import java.util.Objects;
import java.util.Properties;

import static org.yuezhikong.newServer.plugin.configuration.PluginConfig.SaveDefaultConfiguration;

public class PluginMain extends JavaPlugin {
    @Override
    public void onLoad() {
        Tools.getServerInstance().getLogger().info("Chat插件正在被加载");
        SaveDefaultConfiguration(this,"key.properties");
        Properties prop = PluginConfig.getConfiguration(this,"key.properties");
        String APIKey = prop.getProperty("APIKey");
        String SecretKey = prop.getProperty("SecretKey");
        String token = Post.getToken(APIKey,SecretKey);
    }

    @Override
    public void onUnload() {
        Tools.getServerInstance().getLogger().info("Chat插件正在被卸载");
    }
}
