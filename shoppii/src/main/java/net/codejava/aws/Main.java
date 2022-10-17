package net.codejava.aws;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = S3Util.getImageInputStream(
                "https://lh3.googleusercontent.com/a/ALm5wu1Ex2ClttSkDixNSiYS8K6KVr5ZRX9qoCDrHZc=s96-c");
        S3Util.uploadFile("test12.jpg", inputStream);
    }
}
