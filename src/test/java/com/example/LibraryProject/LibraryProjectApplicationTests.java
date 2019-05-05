package com.example.LibraryProject;

import com.example.LibraryProject.model.LibraryFromJson;
import com.example.LibraryProject.modelEndpoint.BookByISBN;
import com.example.LibraryProject.service.LibraryService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import static junit.framework.TestCase.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LibraryProjectApplicationTests {

	@Test
	public void JsonObjectIsNotEmpty() throws FileNotFoundException {

		Gson gson = new Gson();
		LibraryFromJson library = gson.fromJson(new FileReader("C:/Users/Admin/IdeaProjects/LibraryProject/books.json"), LibraryFromJson.class);
		assertFalse(library.equals(null));
	}

	@Test
	public void IsNotNullBookWithISBN() {
		LibraryService libraryService = new LibraryService();
		assertNotNull(libraryService.getBookByISBN("N1IiAQAAIAAJ", libraryService.creatOutputModel()));
	}

	@Test
	public void IsCorrectBookWithISBNnr1() {
		LibraryService libraryService = new LibraryService();
		BookByISBN bookByISBN = libraryService.getBookByISBN("N1IiAQAAIAAJ", libraryService.creatOutputModel());
		assertEquals(bookByISBN.getIsbn(), "N1IiAQAAIAAJ");
	}

	@Test
	public void IsNotNullComputersCategory() {
		LibraryService libraryService = new LibraryService();
		ArrayList<BookByISBN> bookByISBN = libraryService.getBookByCategory("Computers", libraryService.creatOutputModel());
		assertNotNull(bookByISBN);
	}

	@Test
	public void pingTest() {
		given().when().get("/api/all").then().statusCode(200);
	}

	@Test
	public void verifyTitleOfBook() {
		given()
				.when()
				.get("/api/byISBN?isbn=gJEC2q7DzpQC")
				.then()
				.statusCode(200)
				.body("title", equalTo("The History of Java"));
	}


}
