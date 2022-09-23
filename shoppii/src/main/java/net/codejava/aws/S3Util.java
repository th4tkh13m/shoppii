package net.codejava.aws;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.model.S3Object;

public class S3Util {
        private static final String BUCKET = "photo-shoppii";

        private static S3Client client = S3Client.builder().build();

        public static void uploadFile(String fileName, InputStream inputStream)
                        throws S3Exception, AwsServiceException, SdkClientException, IOException {

                PutObjectRequest request = PutObjectRequest.builder()
                                .bucket(BUCKET)
                                .key(fileName)
                                .acl("public-read")
                                .build();
                client.putObject(request,
                                RequestBody.fromInputStream(inputStream, inputStream.available()));
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

        public static void createFolder(String folderName) {
                PutObjectRequest request = PutObjectRequest.builder()
                                .bucket(BUCKET).key(folderName + "/").build();
                client.putObject(request, RequestBody.empty());
        }
}