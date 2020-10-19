package com.bitmovin.trial.encodingapi;

import com.bitmovin.trial.encodingapi.downloadmanager.FileDownloader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * User: sini_ann
 * Date: 19/10/20 3:55 pm
 */
public class FileDownloaderTest {

    @Test
    public void getFileSizeTest() throws IOException {

        URL url = new URL("http://speed.hetzner.de/100MB.bin");
        int result = FileDownloader.getFileSize(url);

        assertNotNull(result, "Size calculated");
    }
}