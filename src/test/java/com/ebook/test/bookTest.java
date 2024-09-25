package com.ebook.test;

import com.ebook.api.CommonAPI;
import com.ebook.api.StatusCodes;
import com.ebook.pojo.Books;
import com.ebook.utils.DataLoader;
import com.ebook.utils.FakerUtils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.List;

import static com.ebook.api.Route.BOOKS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

@Epic("E-Book")
@Feature("Book Feature")
public class bookTest extends BaseTest{
    String publishDate = "2024-09-24T12:37:45.5727061+00:00";

    @Test(description = "Should Be Able To Get List Of Books")
    public void shouldGetListOfBooks(){
        Response response = CommonAPI.get(BOOKS);
        assertThat(response.statusCode(),equalTo(StatusCodes.CODE_200.getStatusCode()));
        assertThat(response.statusLine(),equalTo(StatusCodes.CODE_200.getStatusLine()));

        JsonPath jsonPath = response.jsonPath();
        List<Integer> ids = jsonPath.getList("id");

        //Ensuring that all IDs are unique by comparing the count of distinct IDs to the total count
        long uniqueCount = ids.stream().distinct().count();
        assertThat("IDs should be unique", uniqueCount, is((long) ids.size()));

    }

    @Test(description = "Should Be Able To Add New Book")
    public void shouldAbleToAddANewBook(){
        Books requestBooks = new Books(201, "Book 201",
                FakerUtils.generateDescription(),100,FakerUtils.generateExcerpt(), publishDate);

        Response response = CommonAPI.post(BOOKS,requestBooks);
        assertThat(response.statusCode(),equalTo(StatusCodes.CODE_200.getStatusCode()));
        assertThat(response.statusLine(),equalTo(StatusCodes.CODE_200.getStatusLine()));

        Books responseBooks = response.as(Books.class);

        assertThat(responseBooks.getId(),equalTo(requestBooks.getId()));
        assertThat(responseBooks.getTitle(),equalTo(requestBooks.getTitle()));
        assertThat(responseBooks.getPageCount(),equalTo(requestBooks.getPageCount()));
    }


    @Test(description = "Should Be Able To Get Book By ID")
    public void shouldAbleToGetBookByID(){
        Response response = CommonAPI.get(BOOKS+"/"+DataLoader.getInstance().getBookId());
        assertThat(response.statusCode(),equalTo(StatusCodes.CODE_200.getStatusCode()));
        assertThat(response.statusLine(),equalTo(StatusCodes.CODE_200.getStatusLine()));

        Books responseBooks = response.as(Books.class);

        assertThat(responseBooks.getId(),equalTo(DataLoader.getInstance().getBookId()));
        assertThat(responseBooks.getTitle(),equalTo("Book 21"));
        assertThat(responseBooks.getPageCount(),equalTo(2100));
    }

    @Test(description = "Should Be Able To Update Existing Book")
    public void shouldAbleToUpdateExistingBook(){
        Books requestBooks = new Books(200, "New Book Title 200",
                FakerUtils.generateDescription(),500,FakerUtils.generateExcerpt(), publishDate);

        Response response = CommonAPI.put(BOOKS+"/"+DataLoader.getInstance().getUpdateBookId(),requestBooks);
        assertThat(response.statusCode(),equalTo(StatusCodes.CODE_200.getStatusCode()));
        assertThat(response.statusLine(),equalTo(StatusCodes.CODE_200.getStatusLine()));

        Books responseBooks = response.as(Books.class);

        assertThat(responseBooks.getId(),equalTo(requestBooks.getId()));
        assertThat(responseBooks.getTitle(),equalTo("New Book Title 200"));
        assertThat(responseBooks.getPageCount(),equalTo(500));
    }

    @Test(description = "Should be Able to Delete Existing Book")
    public void shouldAbleToDeleteExistingBook(){
        Response response = CommonAPI.delete(BOOKS+"/"+DataLoader.getInstance().getDeleteBookId());
        assertThat(response.statusCode(),equalTo(StatusCodes.CODE_200.getStatusCode()));
        assertThat(response.statusLine(),equalTo(StatusCodes.CODE_200.getStatusLine()));
    }

    @Test(description = "Should Not Update Book Without ID")
    public void shouldNotUpdateBookWithoutID(){
        Books requestBooks = new Books(200, "New Book Title 200",
                FakerUtils.generateDescription(),500,FakerUtils.generateExcerpt(), publishDate);

        Response response = CommonAPI.put(BOOKS,requestBooks);
        assertThat(response.statusCode(),equalTo(405));
        assertThat(response.statusLine(),equalTo("HTTP/1.1 405 Method Not Allowed"));

    }

    @Test(description = "Should Not Delete Book Without ID")
    public void shouldNotDeleteBookWithoutID(){
        Response response = CommonAPI.delete(BOOKS);
        assertThat(response.statusCode(),equalTo(StatusCodes.CODE_405.getStatusCode()));
        assertThat(response.statusLine(),equalTo(StatusCodes.CODE_405.getStatusLine()));

    }
}
