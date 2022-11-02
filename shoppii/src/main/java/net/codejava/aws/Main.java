package net.codejava.aws;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<String> images = S3Util.listPhotos("profile/" + 1
                + "/user/avatar/");
                System.out.println("profile/" + 1
                + "/user/avatar/");
                for (String string : images) {
                    System.out.println(string);
                }
        // InputStream inputStream = S3Util.getImageInputStream(url);
        // File f = new File("test.png");
        // OutputStream os = new FileOutputStream(f);
        // byte[] buf = new byte[1024];
        // int len;

        // while ((len = inputStream.read(buf)) > 0) {
        //     os.write(buf, 0, len);
        // }

        // os.close();

    }
}
