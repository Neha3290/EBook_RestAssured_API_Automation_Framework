package com.ebook.utils;

import java.util.Properties;

public class DataLoader {
    private final Properties properties;
    private static DataLoader dataLoader;

    private DataLoader(){
        properties = PropertyUtils.propertyLoader("src/test/resources/data.properties");
    }

    public static DataLoader getInstance(){
        if(dataLoader == null){
            dataLoader = new DataLoader();
        }
        return dataLoader;
    }

    public int getBookId() {
        String prop = properties.getProperty("get_book_id");
        if(prop != null) return Integer.parseInt(prop);
        else throw new RuntimeException("property get_book_id is not specified in the data.properties file");
    }

    public int getUpdateBookId() {
        String prop = properties.getProperty("update_book_id");
        if(prop != null) return Integer.parseInt(prop);
        else throw new RuntimeException("property update_book_id is not specified in the data.properties file");
    }

    public int getDeleteBookId() {
        String prop = properties.getProperty("delete_book_id");
        if(prop != null) return Integer.parseInt(prop);
        else throw new RuntimeException("property delete_book_id is not specified in the data.properties file");
    }

    public int getAuthorId() {
        String prop = properties.getProperty("get_author_id");
        if(prop != null) return Integer.parseInt(prop);
        else throw new RuntimeException("property get_author_id is not specified in the data.properties file");
    }

    public int getUpdateAuthorId() {
        String prop = properties.getProperty("update_author_id");
        if(prop != null) return Integer.parseInt(prop);
        else throw new RuntimeException("property update_author_id is not specified in the data.properties file");
    }

    public int getDeleteAuthorId() {
        String prop = properties.getProperty("delete_author_id");
        if(prop != null) return Integer.parseInt(prop);
        else throw new RuntimeException("property delete_author_id is not specified in the data.properties file");
    }

    public int getUserId() {
        String prop = properties.getProperty("get_user_id");
        if(prop != null) return Integer.parseInt(prop);
        else throw new RuntimeException("property get_user_id is not specified in the data.properties file");
    }

    public int getUpdateUserId() {
        String prop = properties.getProperty("update_user_id");
        if(prop != null) return Integer.parseInt(prop);
        else throw new RuntimeException("property update_user_id is not specified in the data.properties file");
    }

    public int getDeleteUserId() {
        String prop = properties.getProperty("delete_user_id");
        if(prop != null) return Integer.parseInt(prop);
        else throw new RuntimeException("property delete_user_id is not specified in the data.properties file");
    }
}
