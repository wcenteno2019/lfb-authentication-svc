package com.loyalty.authentication.encrypt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

public class EncryptionHelper {
    private Logger log;
    private String aesKey;
    private String initVector;
    public EncryptionHelper(Environment env){
        this.aesKey = env.getProperty("configuration.encrypt.key");
        this.initVector = env.getProperty("configuration.encrypt.initv");
        this.log = LoggerFactory.getLogger(this.getClass());
    }
    public EncryptionHelper(Environment env, String aesKey, String initVector){
        this.aesKey = aesKey;
        this.initVector = initVector;
        this.log = LoggerFactory.getLogger(this.getClass());
    }
    public String encryptObjectToText(Object object) {
        try{
            AesEncryptor encryptor = new AesEncryptor();
            String jsonObject = getJsonFromObject(object);
            return encryptor.aesEncrypt(jsonObject, aesKey, initVector.getBytes());
        }
        catch (Exception e){
            e.printStackTrace();
            return  null;
        }

    }
    public <T>T decryptTextToObject(String encryptedText, Class<T>expectedReturnClass){
        try{
            AesEncryptor encryptor = new AesEncryptor();
            String decryptedJson = encryptor.aesDecrypt(encryptedText, aesKey, initVector.getBytes());
            return getObjectFromJson(decryptedJson, expectedReturnClass);
        }
        catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }
    private String getJsonFromObject(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
    @SuppressWarnings("unchecked")
    private <T> T getObjectFromJson(String jsonObject, Class<T> expectedReturnClass){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonObject, expectedReturnClass);
        }
        catch (Exception e){
            return null;
        }
    }

    public String getAesKey() {
        return aesKey;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }

    public String getInitVector() {
        return initVector;
    }

    public void setInitVector(String initVector) {
        this.initVector = initVector;
    }
}
