package model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Token {
    private int tokenId, customerId;
    private String token;
    private LocalDateTime time;
    private boolean isExpired;
    public Token(int tokenId, int customerId, String token) {
        this.tokenId = tokenId;
        this.customerId = customerId;
        this.token = token;
        this.time = LocalDateTime.now();
        this.isExpired = false;
    }

    

    public Token(int tokenId, int customerId, String token, LocalDateTime time, boolean isExpired) {
        this.tokenId = tokenId;
        this.customerId = customerId;
        this.token = token;
        this.time = time;
        this.isExpired = isExpired;
    }



    public int getTokenId() {
        return tokenId;
    }
    public void setTokenId(int tokenId) {
        this.tokenId = tokenId;
    }
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public LocalDateTime getTime() {
        return time;
    }
    public void setTime(LocalDateTime time) {
        this.time = time;
    }
    public boolean isExpired() {
        return isExpired;
    }
    public void setExpired(boolean isExpired) {
        this.isExpired = isExpired;
    }

    public void checkIsExpired() {
        LocalDateTime now = LocalDateTime.now();
    
        long diffInMin = ChronoUnit.MINUTES.between(time, now);
        if (diffInMin > 10) {
            this.isExpired = true;
        }
    }
    @Override
    public String toString() {
        return tokenId + "," +  customerId + "," + token + "," + time
                + "," + isExpired;
    }

    
}
