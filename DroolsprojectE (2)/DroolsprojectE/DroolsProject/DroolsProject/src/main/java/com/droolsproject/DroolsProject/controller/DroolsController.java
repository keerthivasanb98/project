package com.droolsproject.DroolsProject.controller;

import com.droolsproject.DroolsProject.model.PaymentOffer;
import com.droolsproject.DroolsProject.service.DroolsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DroolsController {
    @Autowired
   private DroolsService droolsService;

    @GetMapping("/apply-discount")
    public String applyDiscount(@RequestParam String channel, @RequestParam double amount) {

        PaymentOffer offer = new PaymentOffer(channel, amount);  // Use dynamic values


        droolsService.executeBusinessRules(offer);

        return "Applied Discount: " + offer.getDiscount();
    }

}
