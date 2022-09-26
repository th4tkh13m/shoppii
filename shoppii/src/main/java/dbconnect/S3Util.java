package dbconnect;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Delete;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectResponse;
import software.amazon.awssdk.services.s3.model.DeleteObjectsRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectsResponse;
import software.amazon.awssdk.services.s3.model.ObjectIdentifier;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

public class S3Util {
    private static final String BUCKET = "photo-shoppii";

    private static S3Client client = S3Client.builder().build();

    public static void uploadObject(String objectName, InputStream inputStream)
            throws S3Exception, AwsServiceException, SdkClientException, IOException {

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(BUCKET)
                .key(objectName)
                .acl("public-read")
                .build();
        client.putObject(request,
                RequestBody.fromInputStream(inputStream, inputStream.available()));
    }

    // Wrapper method to easily create folder-like object
    public static void createFolder(String folderName) {
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(BUCKET).key(folderName + "/").build();
        client.putObject(request, RequestBody.empty());
    }

    public static void deleteBucketObjects(String objectName) {
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
    } catch (S3Exception e) {
        System.err.println(e.awsErrorDetails().errorMessage());
        System.exit(1);
    }
   
            

        
        
    }
}