package com.ebook.test;

import com.ebook.api.StatusCodes;
import com.ebook.utils.DataLoader;
import com.ebook.api.CommonAPI;
import com.ebook.pojo.Authors;
import io.qameta.allure.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.List;

import static com.ebook.api.Route.AUTHORS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Epic("E-Book")
@Feature("Author Feature")
public class authorsTest extends BaseTest{

    @Story("Create a author story")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @TmsLink("12345")
    @Issue("1234567")
    @Description("this is the description")
    @Test(description = "Should Be Able To Get List Of Authors")
    public void shouldGetListOfAuthors(){
        Response response = CommonAPI.get(AUTHORS);
        assertThat(response.statusCode(),equalTo(StatusCodes.CODE_200.getStatusCode()));
        assertThat(response.statusLine(),equalTo(StatusCodes.CODE_200.getStatusLine()));

        JsonPath jsonPath = response.jsonPath();
        List<Integer> ids = jsonPath.getList("id");

       // Ensuring that all IDs are unique by comparing the count of distinct IDs to the total count
        long uniqueCount = ids.stream().distinct().count();
        assertThat("IDs should be unique", uniqueCount, is((long) ids.size()));

    }

    @Test(description = "Should Be Able To Add New Author")
    public void shouldAbleToAddANewAuthor(){
        Authors requestAuthors = new Authors(593,93,"First Name 593","Last Name 593");

        Response response = CommonAPI.post(AUTHORS,requestAuthors);
        assertThat(response.statusCode(),equalTo(StatusCodes.CODE_200.getStatusCode()));
        assertThat(response.statusLine(),equalTo(StatusCodes.CODE_200.getStatusLine()));

        Authors responseAuthors = response.as(Authors.class);

        assertThat(responseAuthors.getId(),equalTo(requestAuthors.getId()));
        assertThat(responseAuthors.getIdBook(),equalTo(requestAuthors.getIdBook()));
        assertThat(responseAuthors.getFirstName(),equalTo(requestAuthors.getFirstName()));
        assertThat(responseAuthors.getFirstName(),equalTo(requestAuthors.getFirstName()));
    }

    @Test(description = "Should be Able to Get Author Book By Book ID")
    public void shouldAbleToGetAuthorBookByBookID(){
        Response response = CommonAPI.get(AUTHORS+"/authors/books/10");
        assertThat(response.statusCode(),equalTo(StatusCodes.CODE_200.getStatusCode()));
        assertThat(response.statusLine(),equalTo(StatusCodes.CODE_200.getStatusLine()));

        JsonPath jsonPath = response.jsonPath();
        assertThat(jsonPath.getList("id"),notNullValue());
        assertThat(jsonPath.getList("idBook"),notNullValue());
        assertThat(jsonPath.getList("firstName"),hasItem(startsWith("First Name")));
        assertThat(jsonPath.getList("lastName"),hasItem(startsWith("Last Name")));
    }

    @Test(description = "Should Be Able To Get Author By ID")
    public void shouldAbleToGetAuthorByID(){
        Response response = CommonAPI.get(AUTHORS+"/"+ DataLoader.getInstance().getAuthorId());
        assertThat(response.statusCode(),equalTo(StatusCodes.CODE_200.getStatusCode()));
        assertThat(response.statusLine(),equalTo(StatusCodes.CODE_200.getStatusLine()));

        Authors responseAuthors = response.as(Authors.class);

        assertThat(responseAuthors.getId(),equalTo(21));
        assertThat(responseAuthors.getFirstName(),equalTo("First Name 21"));
        assertThat(responseAuthors.getLastName(),equalTo("Last Name 21"));
    }

    @Test(description = "Should Be Able To Update Existing Author")
    public void shouldAbleToUpdateExistingAuthor(){
        Authors requestAuthors = new Authors(20,7,"New First Name 593","New Last Name 593");

        Response response = CommonAPI.put(AUTHORS+"/"+DataLoader.getInstance().getUpdateAuthorId(),requestAuthors);
        assertThat(response.statusCode(),equalTo(StatusCodes.CODE_200.getStatusCode()));
        assertThat(response.statusLine(),equalTo(StatusCodes.CODE_200.getStatusLine()));

        Authors responseAuthors = response.as(Authors.class);

        assertThat(responseAuthors.getId(),equalTo(requestAuthors.getId()));
        assertThat(responseAuthors.getIdBook(),equalTo(requestAuthors.getIdBook()));
        assertThat(responseAuthors.getFirstName(),equalTo(requestAuthors.getFirstName()));
        assertThat(responseAuthors.getFirstName(),equalTo(requestAuthors.getFirstName()));
    }

    @Test(description = "Should be Able to Delete Existing Author")
    public void shouldAbleToDeleteExistingAuthor(){
        Response response = CommonAPI.delete(AUTHORS+"/"+DataLoader.getInstance().getDeleteAuthorId());
        assertThat(response.statusCode(),equalTo(StatusCodes.CODE_200.getStatusCode()));
        assertThat(response.statusLine(),equalTo(StatusCodes.CODE_200.getStatusLine()));
    }

    @Test(description = "Should Not Update Author Without ID")
    public void shouldNotUpdateAuthorWithoutID(){
        Authors requestAuthors = new Authors(593,93,"First Name 593","Last Name 593");

        Response response = CommonAPI.put(AUTHORS,requestAuthors);
        assertThat(response.statusCode(),equalTo(StatusCodes.CODE_405.getStatusCode()));
        assertThat(response.statusLine(),equalTo(StatusCodes.CODE_405.getStatusLine()));
    }

    @Test(description = "Should Not Delete Author Without ID")
    public void shouldNotDeleteAuthorWithoutID(){
        Response response = CommonAPI.delete(AUTHORS);
        assertThat(response.statusCode(),equalTo(StatusCodes.CODE_405.getStatusCode()));
        assertThat(response.statusLine(),equalTo(StatusCodes.CODE_405.getStatusLine()));
    }
}
