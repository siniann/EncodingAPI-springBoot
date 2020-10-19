package com.bitmovin.trial.encodingapi.scheduler;

/**
 * User: sini_ann
 * Date: 17/10/20 10:09 pm
 */
public interface IVmApi {

    /** @return Returns a generated id which represents the VM instance */
    String start();
    void delete(String id);
}
