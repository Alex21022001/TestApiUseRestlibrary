package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.Specifications;

import static datadeleterequest.DeleteData.endPointForDelete;
import static generaldata.GeneralData.URL;
import static io.restassured.RestAssured.given;

public class DeleteRequestTests {
    @Test
    public void checkDeleteUser() {
        Specifications.installSpecification(Specifications.requestSpecification(URL), Specifications.responseOK204());
        given().when().delete(endPointForDelete)
                .then().log().body()
                .extract().statusCode();
    }
}
