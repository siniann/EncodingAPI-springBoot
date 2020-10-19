package com.bitmovin.trial.encodingapi.scheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * User: sini_ann
 * Date: 17/10/20 10:12 pm
 */
public class IVmApiGoogle implements IVmApi{

    private List<String> googleInstances = new ArrayList<>();

    public List<String> getGoogleInstances() {
        return googleInstances;
    }

    public void setGoogleInstances(List<String> azureInstances) {
        this.googleInstances = azureInstances;
    }
    /**
     * @return Returns a generated id which represents the VM instance
     */
    @Override
    public String start() {
        System.out.println("Start acquiring instance of Google ");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Acquired Google Instance");

        String id = UUID.randomUUID().toString();
        System.out.println("Acquired Azure Instance with id: " +id);
        googleInstances.add(id);
        return id ;
    }

    @Override
    public void delete(String id) {
        if(id == null || id.isEmpty()){
            throw new IllegalArgumentException("No input available");
        }
        System.out.println("Start to delete instance of Google with id: " + id);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (String instance : googleInstances) {
            if (instance.equals(id)) {
                googleInstances.remove(instance);
                System.out.println("Deleted Google Instance with id: " + id);
                System.out.println("...............................");
                return;
            }
        }
        System.out.println("Deleted Google Instance with id: " +id);
        System.out.println("...............................");


    }
}
