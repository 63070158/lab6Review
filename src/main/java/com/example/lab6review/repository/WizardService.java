package com.example.lab6review.repository;

import com.example.lab6review.pojo.Wizard;
import com.example.lab6review.repository.WizardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WizardService {
    @Autowired
    private WizardRepository repository;

    public WizardService(WizardRepository repository) {
        this.repository = repository;
    }

    public List<Wizard> retrieveWizard() {
        return this.repository.findAll();
    }

    public Wizard createWizard(Wizard wizard) {
        return this.repository.save(wizard);
    }

    public Wizard retrieveByName(String name){
        return repository.findByName(name);
    }

    public Wizard updateWizard(Wizard wizard) {
        return repository.save(wizard);
    }
    public boolean deleteWizard(Wizard wizard) {
        try{
            repository.delete(wizard);
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}