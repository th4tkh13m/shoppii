package net.codejava.aws;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://lh3.googleusercontent.com/a/ALm5wu1Ex2ClttSkDixNSiYS8K6KVr5ZRX9qoCDrHZc=s96-c";
        InputStream inputStream = S3Util.getImageInputStream(url);
        File f = new File("test.png");
        OutputStream os = new FileOutputStream(f);
        byte[] buf = new byte[1024];
        int len;

        while ((len = inputStream.read(buf)) > 0) {
            os.write(buf, 0, len);
        }

        os.close();
        // S3Util.uploadFileViaGoogle("test12.jpg", url);
    }
}
