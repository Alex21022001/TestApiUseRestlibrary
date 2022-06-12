package tests;

import dataforpostrequest.UnSuccessRegister;
import generaldata.GeneralData;
import service.Specifications;
import dataforpostrequest.Register;
import dataforpostrequest.SuccessRegister;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static dataforpostrequest.PostData.*;
import static io.restassured.RestAssured.given;

public class PostRequestTests {

    @Test
    public void checkRegisterUseCorrectData() {
        Specifications.installSpecification(Specifications.requestSpecification(GeneralData.URL),Specifications.responseOK200());
        Register user = new Register(email,password);
        SuccessRegister register = given()
                .body(user)
                .when()
                .post(endPointForRegister)
                .then().log().body()
                .extract().body().as(SuccessRegister.class);
        Assertions.assertEquals(4,register.getId());
        Assertions.assertEquals("QpwL5tke4Pnpja7X4",register.getToken());


    }
    @Test
    public void checkRegisterUseInCorrectData() {
        Specifications.installSpecification(Specifications.requestSpecification(GeneralData.URL),Specifications.responseError400());
        Register user = new Register(email,InCorrectPassword);
        UnSuccessRegister register = given()
                .body(user)
                .when()
                .post(endPointForRegister)
                .then().log().body()
                .extract().as(UnSuccessRegister.class);
        Assertions.assertEquals("Missing password",register.getError());


    }
}
