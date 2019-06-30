package ru.otus;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.cloud.config.client.ConfigClientProperties;
import org.springframework.cloud.config.client.ConfigServicePropertySourceLocator;
import org.springframework.core.env.PropertySource;
import org.springframework.mock.env.MockEnvironment;

import static org.junit.Assert.assertEquals;

public class MyTest {

    private static PropertySource propertySource;

    @BeforeClass
    public static void beforeClass() {
        MockEnvironment env = new MockEnvironment();
        env.setProperty("spring.cloud.config.uri", "http://localhost:8888");
        env.setProperty("spring.cloud.config.label", "master");
        env.setProperty("spring.application.name", "otus-configs");
        env.setActiveProfiles("common", "preprod");

        ConfigClientProperties configClientProperties = new ConfigClientProperties(env);
        ConfigServicePropertySourceLocator locator = new ConfigServicePropertySourceLocator(configClientProperties);
        propertySource = locator.locate(env);
    }

    @Test
    public void test() {
        assertEquals("30", propertySource.getProperty("age"));
    }
}
