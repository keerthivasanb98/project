package com.droolsproject.DroolsProject.service;

import com.droolsproject.DroolsProject.model.PaymentOffer;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class DroolsService {
    private final KieSession kSession;

    @Autowired
    public DroolsService(KieContainer kieContainer) {
        // Initialize KieSession from the KieContainer
        this.kSession = kieContainer.newKieSession();
    }

    public void executeBusinessRules(PaymentOffer offer) {
        try {
            kSession.insert(offer);
            System.out.println("Inserted PaymentOffer: " + offer.getChannel() + ", Amount: " + offer.getAmount());

            // Fire all rules and log the result
            int firedRulesCount = kSession.fireAllRules();
            System.out.println("Number of rules fired: " + firedRulesCount);

            // Print the applied discount
            System.out.println("Applied Discount: " + offer.getDiscount());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




    //outside drl file process
//    private KieSession kSession;
//
//    public DroolsService() {
//        try {
//            KieServices kieServices = KieServices.Factory.get();
//            KieFileSystem kFileSystem = kieServices.newKieFileSystem();
//
//            // Load the external DRL file
//            kFileSystem.write(ResourceFactory.newFileResource("C:/Users/abinayab/Documents/rules.drl"));
//
//            // Build the rules
//            KieBuilder kBuilder = kieServices.newKieBuilder(kFileSystem);
//            kBuilder.buildAll();
//
//            if (kBuilder.getResults().hasMessages(org.kie.api.builder.Message.Level.ERROR)) {
//                throw new RuntimeException("Error in DRL file: " + kBuilder.getResults());
//            }
//
//            // Create the KieContainer and session
//            KieContainer kContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
//            kSession = kContainer.newKieSession();
//        } catch (Exception e) {
//            System.err.println("Error initializing KieSession: " + e.getMessage());
//            e.printStackTrace();
//            throw new RuntimeException("Failed to initialize KieSession", e);
//        }
//    }
//
//    public void executeBusinessRules(PaymentOffer offer) {
//        if (kSession == null) {
//            System.err.println("KieSession is not initialized. Cannot execute rules.");
//            return;
//        }
//        try {
//            kSession.insert(offer);
//            kSession.fireAllRules();
//        } catch (Exception e) {
//            System.err.println("Error executing business rules: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }





// simple drools process
//    public DroolsService() {
//        KieServices ks = KieServices.Factory.get();
//        KieContainer kContainer = ks.getKieClasspathContainer();
//
//        KieSession tempSession = null;
//        try {
//            tempSession = kContainer.newKieSession("ksession-rules"); // Match with kmodule.xml
//        } catch (Exception e) {
//            System.err.println("Error initializing KieSession: " + e.getMessage());
//            e.printStackTrace();
//        }
//        this.kSession = tempSession; // Final assignment
//    }
//
//    // Method to execute business rules
//    public void executeBusinessRules(PaymentOffer offer) {
//        if (kSession == null) {
//            System.err.println("KieSession is not initialized. Cannot execute rules.");
//            return;
//        }
//        try {
//            kSession.insert(offer);
//            kSession.fireAllRules();
//            System.out.println("Channel: " + offer.getChannel() + ", Discount: " + offer.getDiscount());
//        } catch (Exception e) {
//            System.err.println("Error executing business rules: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    // Dispose the session explicitly when no longer needed
//    public void disposeSession() {
//        if (kSession != null) {
//            kSession.dispose();
//        }


