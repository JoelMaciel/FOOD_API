package com.joel.food;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RegistrationKitchenIntegrationE2EIT {

    @LocalServerPort
    private int port;

    @Autowired
    private Flyway flyway;

    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/kitchens";
        flyway.migrate();
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
    public void mustContain4Kitchens_WhenConsultKitchen() {
                given()
                     .accept(ContentType.JSON)
                .when()
                     .get()
                .then()
                      .body("", hasSize(4))
                      .body("name", hasItems("Indian", "Thai"));
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
}
