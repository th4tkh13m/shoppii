package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

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
        writer.write((new Token(tokenId, customerId, token)).toString() + "\n" );
        writer.close();
        fileWriter.close();
        return true;
    }

    private static void writeTokenInfoToFile(Token token) throws IOException {
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
        writer.write(token.toString() + "\n" );
        writer.close();
        fileWriter.close();
    }

    public static int getLastIndex() throws FileNotFoundException {
        String fileName = "token.txt";
        int lastIndex = 0;
        File tokenFile = new File(fileName);
        try {
            
            if (tokenFile.createNewFile()) {
              System.out.println("File created: " + tokenFile.getName());
              lastIndex = 1;
              return lastIndex;
            } else {
              System.out.println("File already exists.");
            }
        } catch (IOException e) {

        }
        
        Scanner reader = new Scanner(tokenFile);
        while (reader.hasNextLine()) {
            StringTokenizer tokenizer = new StringTokenizer(reader.nextLine(), ",");
            lastIndex = Integer.parseInt(tokenizer.nextToken());
        }
        reader.close();
        return lastIndex + 1;
    }


    public static int checkToken(int tokenId, String token) throws IOException {
        String fileName = "token.txt";
        int cusId = 0;
        File tokenFile = new File(fileName);
        ArrayList<Token> tokens = new ArrayList<>();
        try {
            
            if (tokenFile.createNewFile()) {
              System.out.println("File created: " + tokenFile.getName());
              return 0;
            } else {
              System.out.println("File already exists.");
            }
        } catch (IOException e) {

        }
        
        Scanner reader = new Scanner(tokenFile);
        while (reader.hasNextLine()) {
            StringTokenizer tokenizer = new StringTokenizer(reader.nextLine(), ",");
            int currentId = Integer.parseInt(tokenizer.nextToken());
            int currentCustomerId = Integer.parseInt(tokenizer.nextToken());
            String currentTokenStr = tokenizer.nextToken();
            LocalDateTime currentTime = LocalDateTime.parse(tokenizer.nextToken());
            boolean currentIsExpired = Boolean.parseBoolean(tokenizer.nextToken());

            

            Token currentToken = new Token(currentId, currentCustomerId, currentTokenStr, currentTime, currentIsExpired);
            currentToken.checkIsExpired();
            if (token.equals(currentToken.getToken()) && tokenId == currentToken.getTokenId() && !currentToken.isExpired()) {
                cusId = currentToken.getCustomerId();
            }
            tokens.add(currentToken);
        }
        reader.close();

        // Empty the file
        new PrintWriter(tokenFile).close();

        // Update file
        for (Token token2 : tokens) {
            writeTokenInfoToFile(token2);
        }
        return cusId;
    }
}
