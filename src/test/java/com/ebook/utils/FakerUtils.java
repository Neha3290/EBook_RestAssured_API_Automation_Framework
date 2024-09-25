package com.ebook.utils;

import com.github.javafaker.Faker;

public class FakerUtils {

    public static String generateExcerpt(){
        Faker faker = new Faker();
        return "Excerpt " + faker.regexify("[A-Za-z0-9]{10}");
    }

    public static String generateDescription(){
        Faker faker = new Faker();
        return "Description " + faker.regexify("[ A-Za-z0-9]{50}");
    }
}
