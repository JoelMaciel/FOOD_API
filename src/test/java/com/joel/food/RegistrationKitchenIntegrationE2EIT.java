package com.joel.food;

import com.joel.food.domain.model.Kitchen;
import com.joel.food.domain.repository.KitchenRepository;
import com.joel.food.util.DatabaseCleaner;
import com.joel.food.util.ResourceUtils;
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

    private static final int KITCHEN_ID_NOT_EXISTENT = 100;

    private Kitchen americanKitchen;
    private int quantityKitchensRegistered;
    private String jsonCorrectChineseKitchen;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private KitchenRepository kitchenRepository;

    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/kitchens";

        jsonCorrectChineseKitchen = ResourceUtils.getContentFromResource(
                "/json/correct/chinese-kitchen.json"
        );

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
    public void shouldReturnCorrectQuantityOfKitchens_WhenConsultingKitchens() {
                given()
                     .accept(ContentType.JSON)
                .when()
                     .get()
                .then()
                      .body("", hasSize(quantityKitchensRegistered));
    }

    @Test
    public void mustReturnStatus201_WhenRegisteringKitchen() {
        given()
                .body(jsonCorrectChineseKitchen)
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
                .pathParams("kitchenId", americanKitchen.getId())
                .accept(ContentType.JSON)
                .when()
                .get("/{kitchenId}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("name", equalTo(americanKitchen.getName()));
    }

    @Test
    public void mustReturnStatus404Correct_WhenQueryNonexistentKitchen() {
        given()
                .pathParams("kitchenId", KITCHEN_ID_NOT_EXISTENT)
                .accept(ContentType.JSON)
                .when()
                .get("/{kitchenId}")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    private void prepareData() {
        Kitchen kitchenThai = new Kitchen();
        kitchenThai.setName("Thai");
        kitchenRepository.save(kitchenThai);

        americanKitchen = new Kitchen();
        americanKitchen.setName("American");
        kitchenRepository.save(americanKitchen);

        quantityKitchensRegistered = (int) kitchenRepository.count();
    }
}
