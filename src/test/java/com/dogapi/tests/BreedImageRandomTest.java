package com.dogapi.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@DisplayName("GET /breeds/image/random - Imagem aleatória")
class BreedImageRandomTest extends BaseTest {

    private static final String ENDPOINT = "/breeds/image/random";

    @Test
    @DisplayName("Deve retornar uma URL de imagem válida")
    void deveRetornarImagemValidaComSucesso() {
        given()
        .when()
            .get(ENDPOINT)
        .then()
            .statusCode(200)
            .body("status", equalTo("success"))
            .body("message", startsWith("https://images.dog.ceo/breeds/"))
            .body("message", matchesPattern(".*\\.(jpg|jpeg|png|gif)$"));
    }

    @Test
    @DisplayName("Deve retornar imagens diferentes em chamadas consecutivas")
    void deveRetornarImagensDiferentes() {
        String primeiraUrl =
            given()
            .when()
                .get(ENDPOINT)
            .then()
                .extract()
                .path("message");

        boolean encontrouDiferente = false;

        for (int i = 0; i < 5; i++) {
            String outraUrl =
                given()
                .when()
                    .get(ENDPOINT)
                .then()
                    .extract()
                    .path("message");

            if (!primeiraUrl.equals(outraUrl)) {
                encontrouDiferente = true;
                break;
            }
        }

        org.junit.jupiter.api.Assertions.assertTrue(
            encontrouDiferente,
            "Após 5 chamadas, todas retornaram a mesma URL. O endpoint pode não ser aleatório."
        );
    }
}
