package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.http.Part;

import model.Token;

public class Utils {
    private static String generateRandomString(int length) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
            .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
            .limit(length)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();

        return generatedString;
    }

    public static String generateName() {
        return generateRandomString(10);
    }

    public static String generateCode() {
        return generateRandomString(12);
    }

    public static String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        int beginIndex = contentDisposition.indexOf("filename=") + 10;
        int endIndex = contentDisposition.length() - 1;
         
        return contentDisposition.substring(beginIndex, endIndex);
    }

    public static String generateToken() {
        return generateRandomString(50);
    }

    public static boolean writeTokenInfoToFile(int tokenId, int customerId, String token) throws IOException {
        String fileName = "token.txt";
        File tokenFile = new File(fileName);
        try {
            
            if (tokenFile.createNewFile()) {
              System.out.println("File created: " + tokenFile.getName());
            } else {
              System.out.println("File already exists.");
            }
        } catch (IOException e) {

        }

        FileWriter fileWriter = new FileWriter(tokenFile, true);
        PrintWriter writer = new PrintWriter(fileWriter);
        writer.write((new Token(tokenId, customerId, token)).toString());
        writer.close();
        fileWriter.close();
        return true;

    }
}
