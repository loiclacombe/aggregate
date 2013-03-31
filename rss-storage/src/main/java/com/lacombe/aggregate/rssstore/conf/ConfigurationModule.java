package com.lacombe.aggregate.rssstore.conf;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: Damaki
 * Date: 30/03/13
 * Time: 14:35
 * To change this template use File | Settings | File Templates.
 */
public class ConfigurationModule extends AbstractModule {
    private static final String DEFAULT_CONF_FILE = "/conf.properties";
    private final Properties properties;

    public ConfigurationModule() throws IOException {
        this(DEFAULT_CONF_FILE);
    }

    public ConfigurationModule(String configurationFile) throws IOException {
        properties = new Properties();
        InputStream resourceAsStream = getClass().getResourceAsStream(configurationFile);
        checkNotNull(resourceAsStream, configurationFile + " is missing");
        properties.load(resourceAsStream);
    }

    @Override
    protected void configure() {
        Names.bindProperties(binder(), properties);
    }
}
