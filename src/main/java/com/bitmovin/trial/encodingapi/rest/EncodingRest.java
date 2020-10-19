package com.bitmovin.trial.encodingapi.rest;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * User: sini_ann
 * Date: 17/10/20 3:26 PM
 */

public class EncodingRest {
    private String id;
    private String name;
    private String provider;

    public EncodingRest(String id, String name, String provider) {
        this.id = id;
        this.name = name;
        this.provider = provider;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProvider() {
        return provider;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}
