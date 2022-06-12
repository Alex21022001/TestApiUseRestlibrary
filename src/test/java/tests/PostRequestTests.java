package tests;

import dataforpostrequest.*;
import service.Specifications;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static dataforpostrequest.PostData.*;
import static generaldata.GeneralData.URL;
import static io.restassured.RestAssured.given;

public class PostRequestTests {
    @Test
    public void checkCreateUser() {
        Specifications.installSpecification(Specifications.requestSpecification(URL), Specifications.responseOK201());
        CreateUser user = new CreateUser(nameForCreate, jobForCreate);
        CreatedUser createUser = given()
                .body(user)
                .when().post(endPointForCreateUser)
                .then().log().body()
                .extract().body()
                .as(CreatedUser.class);
        Assertions.assertEquals(user.getName(), createUser.getName());
        Assertions.assertEquals(user.getJob(), createUser.getJob());
    }

    @Test
    public void checkRegisterUseCorrectData() {
        Specifications.installSpecification(Specifications.requestSpecification(URL), Specifications.responseOK200());
        Register user = new Register(email, password);
        SuccessRegister register = given()
                .body(user)
                .when()
                .post(endPointForRegister)
                .then().log().body()
                .extract().body().as(SuccessRegister.class);
        Assertions.assertEquals(4, register.getId());
        Assertions.assertEquals("QpwL5tke4Pnpja7X4", register.getToken());


    }

    @Test
    public void checkRegisterUseInCorrectData() {
        Specifications.installSpecification(Specifications.requestSpecification(URL), Specifications.responseError400());
        Register user = new Register(email, InCorrectPassword);
        UnSuccessRegister register = given()
                .body(user)
                .when()
                .post(endPointForRegister)
                .then().log().body()
                .extract().as(UnSuccessRegister.class);
        Assertions.assertEquals("Missing password", register.getError());


    }
}
