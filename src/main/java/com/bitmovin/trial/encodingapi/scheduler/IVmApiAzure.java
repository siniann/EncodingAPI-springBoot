package com.bitmovin.trial.encodingapi.scheduler;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * User: sini_ann
 * Date: 17/10/20 10:12 pm
 */
// Task 3
public class IVmApiAzure implements IVmApi {

    private List<String> azureInstances = new ArrayList<>();

    public List<String> getAzureInstances() {
        return azureInstances;
    }

    public void setAzureInstances(List<String> azureInstances) {
        this.azureInstances = azureInstances;
    }

    /**
     * @return Returns a generated id which represents the VM instance
     */
    @Override
    public String start() {
        System.out.println("Start acquiring  Azure Instance");
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String id = UUID.randomUUID().toString();
        System.out.println("Acquired Azure Instance with id: " +id);
        azureInstances.add(id);
        return id ;

    }


    @Override
    public void delete(String id) {
        if(id == null || id.isEmpty()){
            throw new IllegalArgumentException("No input available");
        }
        if (azureInstances.size() == 0){
            System.out.println("No Azure Instances available");
            return;
        }
        System.out.println("Deleting  Azure Instance with id: " +id);
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (String instance : azureInstances) {
            if (instance.equals(id)) {
                azureInstances.remove(instance);
                System.out.println("Deleted  Azure Instance with id: " + id);
                System.out.println("...............................");
                return;
            }
        }
        System.out.println("No instance with id " + id +" available to delete");
        System.out.println("...............................");


    }
}
