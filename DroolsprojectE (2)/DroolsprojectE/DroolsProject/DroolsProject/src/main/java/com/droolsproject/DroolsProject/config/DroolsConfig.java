package com.droolsproject.DroolsProject.config;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DroolsConfig {

    @Value("${drools.decision-table-path}")
    private String decisionTablePath;

    @Bean
    public KieContainer kieContainer() {
        try {
            KieServices kServices = KieServices.Factory.get();
            KieFileSystem kfs = kServices.newKieFileSystem();

            // Load decision table
            kfs.write(ResourceFactory.newClassPathResource(decisionTablePath));

            KieBuilder kBuilder = kServices.newKieBuilder(kfs);
            kBuilder.buildAll();

            if (kBuilder.getResults().hasMessages(org.kie.api.builder.Message.Level.ERROR)) {
                throw new RuntimeException("Error in decision table: " + kBuilder.getResults().toString());
            }

            return kServices.newKieContainer(kServices.getRepository().getDefaultReleaseId());
        } catch (Exception e) {
            throw new RuntimeException("Error initializing KieContainer", e);
        }
    }
}
