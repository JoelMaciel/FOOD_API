package com.joel.food;

import com.joel.food.domain.model.Kitchen;
import com.joel.food.domain.repository.KitchenRepository;
import com.joel.food.util.DatabaseCleaner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class RegistrationKitchenIntegrationE2EIT {

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private KitchenRepository kitchenRepository;

    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/kitchens";

        databaseCleaner.clearTables();
        prepareData();
    }


    @Test
    public void mustReturnStatus200_WhenConsultingKitchen() {

                given()
                    .accept(ContentType.JSON)
                .when()
                    .get()
                .then()
                    .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void mustContain2Kitchens_WhenConsultKitchen() {
                given()
                     .accept(ContentType.JSON)
                .when()
                     .get()
                .then()
                      .body("", hasSize(2))
                      .body("name", hasItems("Thai", "American"));
    }

    @Test
    public void mustReturnStatus201_WhenRegisteringKitchen() {
        given()
                .body("{ \"name\": \"Chinese\" }")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.SC_CREATED);

    }

    @Test
    public void mustReturnCorrectAnswerAndStatus_WhenQueryKitchen() {
        given()
                .pathParams("kitchenId", 2)
                .accept(ContentType.JSON)
                .when()
                .get("/{kitchenId}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("name", equalTo("American"));
    }

    @Test
    public void mustReturnStatus404Correct_WhenQueryNonexistentKitchen() {
        given()
                .pathParams("kitchenId", 100)
                .accept(ContentType.JSON)
                .when()
                .get("/{kitchenId}")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    private void prepareData() {
        Kitchen kitchen1 = new Kitchen();
        kitchen1.setName("Thai");
        kitchenRepository.save(kitchen1);

        Kitchen kitchen2 = new Kitchen();
        kitchen2.setName("American");
        kitchenRepository.save(kitchen2);
    }
}
