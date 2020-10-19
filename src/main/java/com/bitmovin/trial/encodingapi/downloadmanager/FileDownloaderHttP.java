package com.bitmovin.trial.encodingapi.downloadmanager;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * User: sini_ann
 * Date: 17/10/20 11:13 PM
 */
public class FileDownloader {
    static URL url;
    static int sourceFileSize;
    static String localFilePath;

    static {
        try {
            url = new URL("http://speed.hetzner.de/100MB.bin");
            sourceFileSize = getFileSize(url);
            localFilePath = System.getProperty("user.dir") + "/temp";
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Task 2.a
    public static void fileDownloadImplementation()
            throws IOException {

        String[] temp = url.toString().split("/");
        if (temp.length == 0) {
            throw new IllegalArgumentException("Invalid URL");
        }
        String filename = temp[temp.length - 1];
        System.out.println(".........................................................");

        File directory = new File(localFilePath);
        // add new folder temp, if it does not exist
        if (!directory.exists()) {
            boolean created = directory.mkdir();
            if (created)
                System.out.println("Folder created at" + localFilePath);
        }

        String localDownloadPath = localFilePath + "/" + filename;
        BufferedInputStream in = null;
        FileOutputStream fout = null;
        System.out.println("Downloading file to : " + localDownloadPath);
        try {
            InputStream is = url.openStream();
            in = new BufferedInputStream(is);
            fout = new FileOutputStream(localDownloadPath);
            final byte data[] = new byte[1024];
            int count;
            int timer = 0;
            while ((count = in.read(data, 0, 1024)) != -1) {
                timer++;
                if (timer % 3500 == 0) {
                    seeProgress(fout);
                }
                fout.write(data, 0, count);
            }
            System.out.println("Download Completed Successfully!");
        } finally {
            if (in != null) {
                in.close();
            }
            if (fout != null) {
                fout.close();
            }
        }
    }

    // Task 2.b
    public static void fileDownloadUsingAPI() throws Exception {
        System.out.println(".........................................................");
        System.out.println("File download & monitoring with Apache Commons IO library");
        File folder = new File(System.getProperty("user.dir") + "/temp");
        if (!folder.exists()) {
            folder.mkdir();
        }
        System.out.println("Downloading file to folder: " + folder.getAbsolutePath());

        // The monitor will perform polling on the folder every 2 seconds
        final long pollingInterval = 2 * 1000;
        FileAlterationObserver observer = new FileAlterationObserver(folder);
        FileAlterationMonitor monitor = new FileAlterationMonitor(pollingInterval);

        FileAlterationListener listener = new FileAlterationListenerAdaptor() {
            // Is triggered when a file is created in the monitored folder
            @Override
            public void onFileCreate(File file) {
                // "file" is the reference to the newly created file
                System.out.println("New File created : " + file.getAbsolutePath());
            }

            // Is triggered when a file is modified in the monitored folder
            @Override
            public void onFileChange(File file) {
                long percent = 100 - (sourceFileSize - file.length()) * (long) 100 / (long) sourceFileSize;
                System.out.println("File Download " + percent + " % completed");
            }
        };

        observer.addListener(listener);
        monitor.addObserver(observer);
        monitor.start();

        File file;
        try {
            file = new File(System.getProperty("user.dir") + "/temp/100MB.bin");
            FileUtils.copyURLToFile(url, file);
            System.out.println("Download Completed Successfully!");
            monitor.stop();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    public static void seeProgress(FileOutputStream fout) throws IOException {
        long percent = 100 - (sourceFileSize - fout.getChannel().size()) * 100 / (long) sourceFileSize;
        System.out.println("Downloaded " + percent + "%");
    }

    public static int getFileSize(URL url) throws IOException {
        InputStream is;
        URLConnection conn = url.openConnection();
        is = conn.getInputStream();
        int size = conn.getContentLength();

        if (size < 0) {
            System.out.println("Could not determine file size.");
            is.close();
            return 0;
        } else
            System.out.println("The size of file is = " + size + "bytes");
        is.close();
        return size;
    }

}








