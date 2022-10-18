package net.codejava.aws;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
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

        public static void uploadFileViaGoogle(String fileName, String url)
                        throws S3Exception, AwsServiceException, SdkClientException, IOException {
                InputStream inputStream = getImageInputStream(url);
  
                // metadata.setContentLength();
                PutObjectRequest request = PutObjectRequest.builder()
                                .bucket(BUCKET)
                                .key(fileName)
                                .acl("public-read")
                                .contentType("image/jpeg")
                                .build();

                client.putObject(request,
                                RequestBody.fromInputStream(inputStream, getContentLength(url)));
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

        public static InputStream getImageInputStream(String imageURL) throws IOException {
                URL url = new URL(imageURL);
                String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36";

                // This socket type will allow to set user_agent
                URLConnection con = url.openConnection();

                // Setting the user agent
                con.setRequestProperty("User-Agent", USER_AGENT);

                // Getting content Length
                int contentLength = con.getContentLength();
                System.out.println("\n\n\n\n\n *****************File contentLength = " + contentLength
                                + " bytes ****************\n\n\n\n");

                // Requesting input data from server
                return con.getInputStream();
        }

        public static Long getContentLength(String urlStr) {
                Long contentLength = null;
                HttpURLConnection conn = null;
                try {
                        URL url = new URL(urlStr);
                        conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestProperty("User-Agent",
                                        "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.62 Safari/537.36");
                        conn.setRequestMethod("HEAD");
                        contentLength = conn.getContentLengthLong();
                        System.out.println("Content length {}" + contentLength);
                } catch (Exception e) {
                        System.out.println("Error getting content length: {}" + e.getMessage());
                } finally {
                        if (conn != null) {
                                conn.disconnect();
                        }
                }
                return contentLength;
        }
}