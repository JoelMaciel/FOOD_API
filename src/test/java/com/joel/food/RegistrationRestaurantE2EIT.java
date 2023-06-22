package com.joel.food;

import com.joel.food.domain.model.Kitchen;
import com.joel.food.domain.model.Restaurant;
import com.joel.food.domain.repository.KitchenRepository;
import com.joel.food.domain.repository.RestaurantRepository;
import com.joel.food.util.DatabaseCleaner;
import com.joel.food.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class RegistrationRestaurantE2EIT {

    private static final String BUSINESS_RULE_VIOLATION_PROBLEM_TYPE = "Business Rule Violation";
    private static final String INVALID_DATA_PROBLEM_TITLE = "Invalid Data";
    private static final int NON_EXISTENT_RESTAURANT_ID = 100;

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    private String jsonRestaurantCorrect;
    private String jsonRestaurantNoFreightRate;
    private String jsonRestaurantNoKitchen;
    private String jsonRestaurantWithKitchenNonexistent;

    private Restaurant burgerToRestaurant;

    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/restaurants";

        jsonRestaurantCorrect = ResourceUtils.getContentFromResource(
                "/json/correct/restaurant-maria-food.json"
        );
        jsonRestaurantNoFreightRate = ResourceUtils.getContentFromResource(
                "/json/incorrect/restaurant-no-freight-rate.json"
        );

        jsonRestaurantNoKitchen = ResourceUtils.getContentFromResource(
                "/json/incorrect/restaurant-no-kitchen.json"
        );
        jsonRestaurantWithKitchenNonexistent = ResourceUtils.getContentFromResource(
                "/json/incorrect/restaurant-non-existent-kitchen.json"
        );

        databaseCleaner.clearTables();
        prepareData();
    }

    @Test
    public void shouldReturnStatus200_WhenConsultRestaurants() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void shouldReturnStatus201_WhenRegisterRestaurant() {
        given()
                .body(jsonRestaurantCorrect)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value());

    }

    @Test
    public void shouldReturnStatus400_WhenRegisterRestaurantWithoutFreightRate() {
        given()
                .body(jsonRestaurantNoFreightRate)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("title", equalTo(INVALID_DATA_PROBLEM_TITLE));
    }

    @Test
    public void shouldReturnStatus400_WhenRegisterRestaurantNoKitchen() {
        given()
                .body(jsonRestaurantNoKitchen)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("title", equalTo(INVALID_DATA_PROBLEM_TITLE));
    }

    @Test
    public void shouldReturnStatus400_WhenRegisterRestaurantWithNonexistentKitchen() {
        given()
                .body(jsonRestaurantWithKitchenNonexistent)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("title",equalTo(BUSINESS_RULE_VIOLATION_PROBLEM_TYPE));
    }

    @Test
    public void shouldReturnAnswerIsCorrectStatus_WhenConsultingExistingRestaurant() {
        given()
                .pathParams("restaurantId", burgerToRestaurant.getId())
                .accept(ContentType.JSON)
                .when()
                .get("/{restaurantId}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", equalTo(burgerToRestaurant.getName()));
    }

    @Test
    public void shouldReturnStatus404_WhenConsultRestaurantNonexistent() {
        given()
                .pathParams("restaurantId", NON_EXISTENT_RESTAURANT_ID)
                .accept(ContentType.JSON)
                .when()
                .get("/{restaurantId}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    private void prepareData() {
        Kitchen brazilianKitchen = new Kitchen();
        brazilianKitchen.setName("Brazilian");
        kitchenRepository.save(brazilianKitchen);

        Kitchen americanKitchen = new Kitchen();
        americanKitchen.setName("American");
        kitchenRepository.save(americanKitchen);

        burgerToRestaurant = new Restaurant();
        burgerToRestaurant.setName("Burger Top");
        burgerToRestaurant.setFreightRate(new BigDecimal(10));
        burgerToRestaurant.setKitchen(americanKitchen);
        restaurantRepository.save(burgerToRestaurant);

        Restaurant restaurantFoodHomemade = new Restaurant();
        restaurantFoodHomemade.setName("Food Homemade");
        restaurantFoodHomemade.setFreightRate(new BigDecimal(23));
        restaurantFoodHomemade.setKitchen(brazilianKitchen);
        restaurantRepository.save(restaurantFoodHomemade);

    }


}






