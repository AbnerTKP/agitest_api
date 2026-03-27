package com.dogapi.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@DisplayName("GET /breeds/list/all - Listar todas as raças")
class BreedsListAllTest extends BaseTest {

    private static final String ENDPOINT = "/breeds/list/all";

    @Test
    @DisplayName("Deve retornar lista de raças com sucesso")
    void deveRetornarListaDeRacasComSucesso() {
        given()
        .when()
            .get(ENDPOINT)
        .then()
            .statusCode(200)
            .body("status", equalTo("success"))
            .body("message.size()", greaterThan(0));
    }

    @Test
    @DisplayName("Deve conter raças conhecidas na lista")
    void deveConterRacasConhecidas() {
        given()
        .when()
            .get(ENDPOINT)
        .then()
            .body("message", hasKey("bulldog"))
            .body("message", hasKey("labrador"))
            .body("message", hasKey("poodle"));
    }

    @Test
    @DisplayName("Deve retornar sub-raças corretamente")
    void deveRetornarSubRacasCorretamente() {
        given()
        .when()
            .get(ENDPOINT)
        .then()
            .body("message.bulldog", hasItem("french"))
            .body("message.labrador", empty());
    }
}
