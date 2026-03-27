package com.dogapi.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@DisplayName("GET /breed/{breed}/images - Imagens por raça")
class BreedImagesTest extends BaseTest {

    @Test
    @DisplayName("Deve retornar imagens válidas para raça existente")
    void deveRetornarImagensParaRacaValida() {
        given()
        .when()
            .get("/breed/{breed}/images", "hound")
        .then()
            .statusCode(200)
            .body("status", equalTo("success"))
            .body("message.size()", greaterThan(0))
            .body("message", everyItem(startsWith("https://images.dog.ceo/breeds/")));
    }

    @Test
    @DisplayName("URLs das imagens devem corresponder à raça buscada")
    void urlsDevemConterNomeDaRaca() {
        given()
        .when()
            .get("/breed/{breed}/images", "labrador")
        .then()
            .body("message", everyItem(containsString("labrador")));
    }

    @Test
    @DisplayName("Deve retornar erro 404 para raça inexistente")
    void deveRetornar404ParaRacaInexistente() {
        given()
        .when()
            .get("/breed/{breed}/images", "racainexistente999")
        .then()
            .statusCode(404)
            .body("status", equalTo("error"))
            .body("message", containsStringIgnoringCase("not found"));
    }
}
