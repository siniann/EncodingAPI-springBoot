package com.bitmovin.trial.encodingapi.scheduler;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * User: sini_ann
 * Date: 17/10/20 10:10 pm
 */

public class IVmApiAws implements IVmApi {

    private List<String> awsInstances = new ArrayList<>();

    public void setAwsInstances(List<String> awsInstances) {
        this.awsInstances = awsInstances;
    }

    public List<String> getAwsInstances() {
        return awsInstances;
    }


    public Double getPrice(String id) {
        double random = Math.random() * 99 + 1;
        return random;
    }

    /**
     * @return Returns a generated id which represents the VM instance
     */
    @Override
    public String start() {
        System.out.println("Start acquiring  Aws Instance");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String id = UUID.randomUUID().toString();
        System.out.println("Acquired Aws Instance with id : " + id);
        System.out.println("Price for the acquired Aws instance is : " + this.getPrice(id));
        awsInstances.add(id);
        System.out.println("...............................");

        return id;
    }

    @Override
    public void delete(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("No input available");
        }
        System.out.println("Deleting Aws Instance with id: " + id);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (String instance : awsInstances) {
            if (instance.equals(id)) {
                awsInstances.remove(instance);
                System.out.println("Deleted  Aws Instance with id: " + id);
                System.out.println("...............................");
                return;
            }
        }
        System.out.println("No instance with id " + id +" available to delete");
        System.out.println("...............................");

    }
}
