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

    public static String generateTemplate(final String vmFolder,
                                          final String vmFileName,
                                          final VelocityContext context) {
        try {
            final Properties properties = new Properties();
            properties.setProperty("file.resource.loader.class",
                    "org.apache.velocity.runtime.resource.loader.FileResourceLoader");
            properties.setProperty("file.resource.loader.path", vmFolder);
            properties.setProperty("resource.loader", "file");

            VelocityEngine velocityEngine = new VelocityEngine(properties);
            velocityEngine.init();

            StringWriter writer = new StringWriter();
            velocityEngine.mergeTemplate(vmFileName, "UTF-8", context, writer);

            return writer.toString();
        } catch (final Exception e) {
            log.error("Error while generating template", e);
            throw new RuntimeException("Template generation failed", e);
        }
    }
}


