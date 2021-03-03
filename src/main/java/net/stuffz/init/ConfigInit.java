package net.stuffz.init;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import net.stuffz.config.stuffzconfig;

public class ConfigInit {
    public static stuffzconfig CONFIG = new stuffzconfig();

    public static void init() {
        AutoConfig.register(stuffzconfig.class, JanksonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(stuffzconfig.class).getConfig();
    }

}
