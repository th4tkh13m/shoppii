package dbconnect;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Delete;
import software.amazon.awssdk.services.s3.model.DeleteObjectsRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectsResponse;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;
import software.amazon.awssdk.services.s3.model.ObjectIdentifier;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.model.S3Object;

public class S3Util {
    private static String BUCKET = "photo-shoppii";

    private static S3Client client = S3Client.builder().build();
    private static S3AsyncClient asyncClient = S3AsyncClient.builder().build();

    public static boolean uploadObject(String objectName, InputStream inputStream)
            throws S3Exception, AwsServiceException, SdkClientException, IOException {

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(BUCKET)
                .key(objectName)
                .acl("public-read")
                .build();
        client.putObject(request,
                RequestBody.fromInputStream(inputStream, inputStream.available()));
        return true;
    }

    // Wrapper method to easily create folder-like object
    public static boolean createFolder(String folderName) {
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(BUCKET).key(folderName + "/").build();
        client.putObject(request, RequestBody.empty());
        return true;
    }

    public static boolean deleteBucketObjects(String objectName) {
        try {
            S3AsyncClient asyncClient = S3AsyncClient.builder().build();
            ArrayList<ObjectIdentifier> toDelete = new ArrayList<>();
            toDelete.add(ObjectIdentifier.builder()
                    .key(objectName)
                    .build());
            DeleteObjectsRequest dor = DeleteObjectsRequest.builder()
                    .bucket(BUCKET)
                    .delete(Delete.builder()
                            .objects(toDelete).build())
                    .build();

            CompletableFuture<DeleteObjectsResponse> future = asyncClient.deleteObjects(dor);
            future.whenComplete((resp, err) -> {
                try {
                    if (resp != null) {
                        System.out.println("Done!");
                    } else {
                        err.printStackTrace();
                    }
                } finally {
                    // Only close the client when you are completely done with it.
                    asyncClient.close();
                }
            });
            future.join();
            return true;
        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            return false;
        }
    }

    public static ArrayList<String> getObject(String folderName) {
        ArrayList<String> results = new ArrayList<String>();
        try {

            ListObjectsRequest objectRequest = ListObjectsRequest.builder()
                    .bucket(BUCKET)
                    .prefix(folderName).build();

            CompletableFuture<ListObjectsResponse> futureGet = asyncClient.listObjects(objectRequest);

            futureGet.whenComplete((resp, err) -> {
                try {
                    if (resp != null) {
                        List<S3Object> objects = resp.contents();
                        ListIterator<S3Object> listIterator = objects.listIterator();

                        while (listIterator.hasNext()) {
                            S3Object object = listIterator.next();
                            results.add(object.key().split("/")[1]);
                        }
                        System.out.println(results);

                    } else {
                        err.printStackTrace();
                    }
                } finally {
                    // Only close the client when you are completely done with it.
                    asyncClient.close();
                }
            });

            futureGet.join();
            return results;

        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            return results;
        }
    }

    public static ArrayList<String> listPhotos(String folderName) {
        ArrayList<String> results = new ArrayList<String>();
        ListObjectsRequest request = ListObjectsRequest.builder()
                .bucket(BUCKET)
                .prefix(folderName).build();

        ListObjectsResponse response = client.listObjects(request);
        List<S3Object> objects = response.contents();

        ListIterator<S3Object> listIterator = objects.listIterator();

        while (listIterator.hasNext()) {
            S3Object object = listIterator.next();
            results.add(object.key().split("/")[1]);
        }
        System.out.println(results);
        return results;
    }

    // Helper function to get bucket name from web.xml
    private static void getBucketNameFromWeb() {
        try {
            // Get environment variables saved in web.xml
            Context ctx = new InitialContext();
            Context env = (Context) ctx.lookup("java:comp/env");
            BUCKET = (String) env.lookup("bucket");

            System.out.println("CONNECT OK");

        } catch (NamingException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Use For Debug and Testing
    protected static void setBucketName(String name) {
        BUCKET = name;
    }
}