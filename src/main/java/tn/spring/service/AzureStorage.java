package tn.spring.service;

import com.microsoft.azure.storage.*;
import com.microsoft.azure.storage.blob.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AzureStorage {
    public static final String storageConnectionString =
            "DefaultEndpointsProtocol=https;"
                    + "AccountName=adimoss;"
                    + "AccountKey= DefaultEndpointsProtocol=https;AccountName=adimoss;AccountKey=CC4XqEs235eScLUecu7tufQdyIb3SFKxLv37QWUQnISIKUr7paOc0N0ZDeOkEtegRw3giFLm3Tz88oa6468azQ==;EndpointSuffix=core.windows.net";

    public static String Storage(String file) {
        String output = null;
        try {
            CloudStorageAccount account = CloudStorageAccount.parse(storageConnectionString);
            CloudBlobClient serviceClient = account.createCloudBlobClient();

            // Container name must be lower case.
            CloudBlobContainer container = serviceClient.getContainerReference("images");
            container.createIfNotExists();

            // Upload an image file.
            CloudBlockBlob blob = container.getBlockBlobReference("image5.jpg");
            File sourceFile = new File(file);

            try (FileInputStream sourceStream = new FileInputStream(sourceFile)) {
                blob.upload(sourceStream, sourceFile.length());
                output= (blob.getUri().toString());
            }


        }
        catch (FileNotFoundException fileNotFoundException) {
            System.out.print("FileNotFoundException encountered: ");
            System.out.println(fileNotFoundException.getMessage());
            System.exit(-1);
        }
        catch (StorageException storageException) {
            System.out.print("StorageException encountered: ");
            System.out.println(storageException.getMessage());
            System.exit(-1);
        }
        catch (Exception e) {
            System.out.print("Exception encountered: ");
            System.out.println(e.getMessage());
            System.exit(-1);

        }
        return output;
    }
}