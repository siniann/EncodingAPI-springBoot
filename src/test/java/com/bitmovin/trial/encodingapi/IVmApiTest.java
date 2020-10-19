package com.bitmovin.trial.encodingapi;

import com.bitmovin.trial.encodingapi.scheduler.IVmApiAws;
import com.bitmovin.trial.encodingapi.scheduler.IVmApiAzure;
import com.bitmovin.trial.encodingapi.scheduler.IVmApiGoogle;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * User: sini_ann
 * Date: 19/10/20 12:02 pm
 */
public class IVmApiTest {


    @Test
    public void startAwsTest() {
        IVmApiAws aws = new IVmApiAws();
        String aws1 = aws.start();

        assertEquals(1, aws.getAwsInstances().size(), "Total instances after start :1");
        assertNotNull(aws1, "start method returns a string");
        assertTrue(!aws1.isEmpty());

        String aws2 = aws.start();
        assertEquals(2, aws.getAwsInstances().size(), "Total instance after start : 2");
        assertNotNull(aws2, "start method returns a string");
        assertTrue(!aws2.isEmpty());

    }


    @Test
    public void startGoogleTest() {
        IVmApiGoogle google = new IVmApiGoogle();

        String google1 = google.start();

        assertNotNull(google1, "start method returns a string");
        assertTrue(!google1.isEmpty());
        assertEquals(1, google.getGoogleInstances().size(), "Total instance after start : 1");


    }


    @Test
    public void startAzureTest() {
        IVmApiAzure azure = new IVmApiAzure();
        String azure1 = azure.start();

        assertNotNull(azure1, "start method returns a string");
        assertTrue(!azure1.isEmpty());
        assertEquals(1, azure.getAzureInstances().size(), "Total instance after start : 1");

    }

    @Test
    public void deleteAwsTest() {
        IVmApiAws aws = new IVmApiAws();
        ArrayList<String> str = new ArrayList<String>();
        str.add("id1");
        str.add("id2");
        str.add("id3");

        aws.setAwsInstances(str);
        assertEquals(3, aws.getAwsInstances().size(), "Initial list set to size 3 ");

        aws.delete("id2");
        assertEquals(2, aws.getAwsInstances().size(), "Total instance after delete: 2 ");
        assertFalse(aws.getAwsInstances().contains("id2"), "Instance no longer exist");


        aws.delete("aaaa");
        assertEquals(2, aws.getAwsInstances().size(), " Instance List remain unchanged for invalid id");

        assertThrows(IllegalArgumentException.class, () -> {
            aws.delete("");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            aws.delete(null);
        });

    }


    @Test
    public void deleteGoogleTest() {
        IVmApiGoogle google = new IVmApiGoogle();
        ArrayList<String> str = new ArrayList<String>();
        str.add("id1");
        str.add("id2");
        str.add("id3");

        google.setGoogleInstances(str);
        assertEquals(3, google.getGoogleInstances().size(), "Total instance initialized to 3");

        google.delete("id2");
        assertEquals(2, google.getGoogleInstances().size(), "Total instance after start : 2");


        google.delete("aaaa");
        assertEquals(2, google.getGoogleInstances().size(), " Instance List remain unchanged for invalid id");

        assertThrows(IllegalArgumentException.class, () -> {
            google.delete(null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            google.delete("");
        });

    }

    @Test
    public void deleteAzureTest() {
        IVmApiAzure azure = new IVmApiAzure();
        ArrayList<String> str = new ArrayList<String>();
        str.add("id1");
        str.add("id2");
        str.add("id3");

        azure.setAzureInstances(str);
        assertEquals(3, azure.getAzureInstances().size(), "Total instance initialized to 3");

        azure.delete("id2");
        assertEquals(2, azure.getAzureInstances().size(), "Total instance after start : 2");


        azure.delete("aaaa");
        assertEquals(2, azure.getAzureInstances().size(), " Instance List remain unchanged for invalid id");

        assertThrows(IllegalArgumentException.class, () -> {
            azure.delete(null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            azure.delete("");
        });

    }


}
