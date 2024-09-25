package com.ebook.test;

import com.ebook.api.CommonAPI;
import com.ebook.api.StatusCodes;
import com.ebook.pojo.Users;
import com.ebook.utils.DataLoader;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.*;
import java.util.List;

import static com.ebook.api.Route.USERS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Epic("E-Book")
@Feature("User Feature")
public class usersTest extends BaseTest{
    @Test(description = "Should Be Able To Get List Of Users")
    public void shouldGetListOfUsers(){
        Response response = CommonAPI.get(USERS);
        assertThat(response.statusCode(),equalTo(200));
        assertThat(response.statusLine(),equalTo("HTTP/1.1 200 OK"));

        JsonPath jsonPath = response.jsonPath();
        List<Integer> ids = jsonPath.getList("id");

        //Ensuring that all IDs are unique by comparing the count of distinct IDs to the total count
        long uniqueCount = ids.stream().distinct().count();
        assertThat("IDs should be unique", uniqueCount, is((long) ids.size()));

    }

    @Test(description = "Should Be Able To Add New User")
    public void shouldAbleToAddANewUser(){
        Users requestUsers = new Users();
        requestUsers.setId(11);
        requestUsers.setUserName("User 11");
        requestUsers.setPassword("Password11");

        Response response = CommonAPI.post(USERS,requestUsers);
        assertThat(response.statusCode(),equalTo(StatusCodes.CODE_200.getStatusCode()));
        assertThat(response.statusLine(),equalTo(StatusCodes.CODE_200.getStatusLine()));

        Users responseUsers = response.as(Users.class);
        assertThat(responseUsers.getId(),equalTo(requestUsers.getId()));
        assertThat(responseUsers.getUserName(),equalTo(requestUsers.getUserName()));
        assertThat(responseUsers.getPassword(),equalTo(requestUsers.getPassword()));
    }

    @Test(description = "Should Be Able To Get User By ID")
    public void shouldAbleToGetUserByID(){
        Response response = CommonAPI.get(USERS+"/"+ DataLoader.getInstance().getUserId());
        assertThat(response.statusCode(),equalTo(StatusCodes.CODE_200.getStatusCode()));
        assertThat(response.statusLine(),equalTo(StatusCodes.CODE_200.getStatusLine()));

        Users responseUsers = response.as(Users.class);
        assertThat(responseUsers.getId(),equalTo(DataLoader.getInstance().getUserId()));
        assertThat(responseUsers.getUserName(),equalTo("User 10"));
        assertThat(responseUsers.getPassword(),equalTo("Password10"));
    }

    @Test(description = "Should Be Able To Update Existing User")
    public void shouldAbleToUpdateExistingUser(){
        Users requestUsers = new Users();
        requestUsers.setId(9);
        requestUsers.setUserName("NewUser 9");
        requestUsers.setPassword("Password9");

        Response response = CommonAPI.put(USERS+"/"+DataLoader.getInstance().getUpdateUserId(),requestUsers);
        assertThat(response.statusCode(),equalTo(StatusCodes.CODE_200.getStatusCode()));
        assertThat(response.statusLine(),equalTo(StatusCodes.CODE_200.getStatusLine()));

        Users responseUsers = response.as(Users.class);
        assertThat(responseUsers.getId(),equalTo(requestUsers.getId()));
        assertThat(responseUsers.getUserName(),equalTo(requestUsers.getUserName()));
        assertThat(responseUsers.getPassword(),equalTo(requestUsers.getPassword()));
    }

    @Test(description = "Should be Able to Delete Existing User")
    public void shouldAbleToDeleteExistingUser(){
        Response response = CommonAPI.delete(USERS+"/"+DataLoader.getInstance().getDeleteUserId());
        assertThat(response.statusCode(),equalTo(StatusCodes.CODE_200.getStatusCode()));
        assertThat(response.statusLine(),equalTo(StatusCodes.CODE_200.getStatusLine()));
    }

    @Test(description = "Should Not Update User Without ID")
    public void shouldNotUpdateUserWithoutID(){
        Users requestUsers = new Users();
        requestUsers.setId(9);
        requestUsers.setUserName("User 9");
        requestUsers.setPassword("Password9");

        Response response = CommonAPI.put(USERS,requestUsers);
        assertThat(response.statusCode(),equalTo(StatusCodes.CODE_405.getStatusCode()));
        assertThat(response.statusLine(),equalTo(StatusCodes.CODE_405.getStatusLine()));

    }

    @Test(description = "Should Not Delete User Without ID")
    public void shouldNotDeleteUserWithoutID(){
        Response response = CommonAPI.delete(USERS);
        assertThat(response.statusCode(),equalTo(StatusCodes.CODE_405.getStatusCode()));
        assertThat(response.statusLine(),equalTo(StatusCodes.CODE_405.getStatusLine()));

    }
}
