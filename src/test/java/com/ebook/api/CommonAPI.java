package com.ebook.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.ebook.api.Route.API;
import static com.ebook.api.Route.TOKEN;
import static com.ebook.api.SpecBuilder.getRequestSpec;
import static com.ebook.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

public class CommonAPI {

    public static Response get(String endpoint){
        return given(getRequestSpec())
                .when().get(endpoint)
                .then().spec(getResponseSpec())
                .extract().response();
    }

    public static Response post(String endpoint, Object requestPayload){
        return given(getRequestSpec())
                .header("Content-Type","application/json")
                .body(requestPayload)
                .when().post(endpoint)
                .then().spec(getResponseSpec())
                .extract().response();
    }

    public static Response put(String endpoint, Object requestPayload){
        return given(getRequestSpec())
                .contentType("application/json")
                .accept("application/json")
                .body(requestPayload)
                .when().put(endpoint)
                .then().spec(getResponseSpec())
                .extract().response();
    }

    public static Response delete(String endpoint){
       return given(getRequestSpec())
                .when().delete(endpoint)
                .then().spec(getResponseSpec())
                .extract().response();
    }

    public static Response postAccount(HashMap<String, String> formParams){
        return given()
                .baseUri("https://fakerestapi.azurewebsites.net/accounts")
                .contentType(ContentType.URLENC)
                .formParams(formParams)
                .when().post(API+TOKEN)
                .then().spec(getResponseSpec())
                .extract()
                .response();
    }
}
