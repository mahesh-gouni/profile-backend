package com.mahesh.website.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.Properties;

@UtilityClass
@Slf4j
public class VelocityUtility {

    public static String generateTemplate(final String vmPath,
                                          final VelocityContext context) {
        try {
            Properties properties = new Properties();
            properties.setProperty("resource.loader", "classpath");
            properties.setProperty("classpath.resource.loader.class",
                    "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

            VelocityEngine velocityEngine = new VelocityEngine(properties);
            velocityEngine.init();

            StringWriter writer = new StringWriter();
            // vmPath should be like "templates/Contactus.vm"
            velocityEngine.mergeTemplate(vmPath, "UTF-8", context, writer);

            return writer.toString();
        } catch (final Exception e) {
            log.error("Error while generating template", e);
            throw new RuntimeException("Template generation failed", e);
        }
    }
}
