package com.ebook.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.ebook.api.Route.*;

public class SpecBuilder {

    public static RequestSpecification getRequestSpec(){
       return new RequestSpecBuilder()
                .setBaseUri("https://fakerestapi.azurewebsites.net")
                .setBasePath(API+BASE_PATH)
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL).build();
    }

    public static ResponseSpecification getResponseSpec(){
       return new ResponseSpecBuilder()
                .log(LogDetail.ALL).build();

    }
}
