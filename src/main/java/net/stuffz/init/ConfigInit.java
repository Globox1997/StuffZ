package net.stuffz.init;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import net.stuffz.config.StuffzConfig;

public class ConfigInit {
    public static StuffzConfig CONFIG = new StuffzConfig();

    public static void init() {
        AutoConfig.register(StuffzConfig.class, JanksonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(StuffzConfig.class).getConfig();
    }

}
