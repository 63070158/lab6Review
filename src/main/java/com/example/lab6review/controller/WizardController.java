package com.example.lab6review.controller;

import com.example.lab6review.pojo.Wizard;
import com.example.lab6review.repository.WizardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WizardController {
    @Autowired
    private WizardService wizardService;
    @RequestMapping(value ="/wizards", method = RequestMethod.GET)
    public ResponseEntity<?> getWizard() {
        List<Wizard> wizard = wizardService.retrieveWizard();
        return ResponseEntity.ok(wizard);
    }
    @RequestMapping(value ="/addWizard", method = RequestMethod.POST)
    public ResponseEntity<?> addWizard(@RequestParam("sex") String sex,
                                       @RequestParam("name") String name,
                                       @RequestParam("school") String school,
                                       @RequestParam("house") String house,
                                       @RequestParam("money") String money,
                                       @RequestParam("position") String position){
        Wizard wizard = wizardService.createWizard(new Wizard(null, sex, name, school, house, money, position));
        return  ResponseEntity.ok(wizard);
    }
    @RequestMapping(value ="/updateWizard", method = RequestMethod.POST)
    public boolean updateWizard(@RequestParam("sex") String sex,
                                          @RequestParam("name") String name,
                                          @RequestParam("school") String school,
                                          @RequestParam("house") String house,
                                          @RequestParam("money") String money,
                                          @RequestParam("position") String position,
                                          @RequestParam("oldname") String oldname) {
        Wizard wizard = wizardService.retrieveByName(oldname);
        if(wizard != null) {
            wizardService.updateWizard(new Wizard(wizard.getId(), sex, name, school, house, money, position));
            return  true;
        }else {
            return false;
        }
    }
    @RequestMapping(value ="/deleteWizard", method = RequestMethod.POST)
    public boolean deleteWizard(@RequestParam("sex") String sex,
                                @RequestParam("name") String name,
                                @RequestParam("school") String school,
                                @RequestParam("house") String house,
                                @RequestParam("money") int money,
                                @RequestParam("position") String position,
                                @RequestParam("oldname") String oldname) {
        Wizard wizard = wizardService.retrieveByName(oldname);
        return wizardService.deleteWizard(wizard);
    }
}
