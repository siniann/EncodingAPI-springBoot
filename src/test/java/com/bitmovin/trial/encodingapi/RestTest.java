package com.bitmovin.trial.encodingapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.bitmovin.trial.encodingapi.rest.EncodingRest;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * User: sini_ann
 * Date: 19/10/20 5:52 pm
 */
public class RestTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getEncodings() throws Exception {
        String uri = "/encodings";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Object[] productList = super.mapFromJson(content, Object[].class);
        assertTrue(productList.length > 0);

    }

    @Test
    public void getEncodingsById() throws Exception {
        String uri = "/encoding/38400000-8cf0-11bd-b23e-10b96e4ef00d";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void createEncoding() throws Exception {
        String uri = "/encoding";
        EncodingRest encoding = new EncodingRest("encodingId", "encodingName", "AWS");
        String inputJson = super.mapToJson(encoding);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Product is created successfully");

    }

    @Test
    public void deleteEncoding() throws Exception {
        String uri = "/encoding/38400000-8cf0-11bd-b23e-10b96e4ef00d";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Product is deleted successsfully");

    }
}
