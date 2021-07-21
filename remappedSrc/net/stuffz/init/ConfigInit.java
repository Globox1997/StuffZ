package net.stuffz.init;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.stuffz.config.StuffzConfig;

public class ConfigInit {
    public static StuffzConfig CONFIG = new StuffzConfig();

    public static void init() {
        AutoConfig.register(StuffzConfig.class, JanksonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(StuffzConfig.class).getConfig();
    }

}
