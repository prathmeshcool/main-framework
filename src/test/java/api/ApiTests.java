package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class ApiTests {

	@Test(groups = {"smoke", "api"})
    public void getUsersTest() {

        Response res = given()
                .spec(ApiSpec.getSpec())
                .get("/users");

        System.out.println("\nGET Response: " + res.asString());

        // Status + basic validation
        Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertNotNull(res.jsonPath().getString("[0].email"));
        Assert.assertTrue(res.asString().contains("username"));

        // Specific field check
        String firstUser = res.jsonPath().getString("[0].username");
        Assert.assertNotNull(firstUser);
    }

	@Test(groups = {"regression","api"})
    public void createUserTest() {

        String requestBody = """
                {
                    "name": "Prath",
                    "job": "SDET Trainee"
                }
                """;

        Response res = given()
                .spec(ApiSpec.getSpec())
                .body(requestBody)
                .post("/users");

        System.out.println("\nPOST Response: " + res.asString());

        // Status + basic checks
        Assert.assertEquals(res.getStatusCode(), 201);

        // Validating JSON response fields
        Assert.assertEquals(res.jsonPath().getString("name"), "Prath");
        Assert.assertTrue(res.asString().contains("SDET"));
        Assert.assertNotNull(res.jsonPath().getString("id"));
    }
}
