package tests;

import dataforgetrequest.ResourceListData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.Specifications;
import dataforgetrequest.UserData;

import java.util.List;
import java.util.stream.Collectors;

import static dataforgetrequest.GetData.endPointForListData;
import static generaldata.GeneralData.URL;
import static dataforgetrequest.GetData.endPointForPages;
import static io.restassured.RestAssured.given;

public class GetRequestTests {


    @Test
    public void checkListOfUsersAndAvatars() {
        Specifications.installSpecification(Specifications.requestSpecification(URL), Specifications.responseOK200());
        List<UserData> listOfUsers = given()
                .when()
                .get(endPointForPages)
                .then().log().body()
                .extract().body().jsonPath().getList("data", UserData.class);
        listOfUsers.stream().forEach(x -> Assertions.assertTrue(x.getAvatar().contains(x.getId().toString())));
        Assertions.assertTrue(listOfUsers.stream().allMatch(x -> x.getEmail().endsWith("@reqres.in")));


    }

    @Test
    public void checkListOfUsersEmails() {
        Specifications.installSpecification(Specifications.requestSpecification(URL), Specifications.responseOK200());
        List<UserData> listOfUsers = given()
                .when()
                .get(endPointForPages)
                .then().log().body()
                .extract().body().jsonPath().getList("data", UserData.class);
        Assertions.assertTrue(listOfUsers.stream().allMatch(x -> x.getEmail().endsWith("@reqres.in")));
    }

    @Test
    public void checkListOfResourceInOrder() {
        Specifications.installSpecification(Specifications.requestSpecification(URL), Specifications.responseOK200());
        List<ResourceListData> listOfResource = given()
                .when()
                .get(endPointForListData)
                .then().log().body()
                .extract().body().jsonPath().getList("data", ResourceListData.class);
        List<Integer> years =  listOfResource.stream().map(ResourceListData::getYear).collect(Collectors.toList());
        List<Integer> sortedYears = years.stream().sorted().collect(Collectors.toList());
        Assertions.assertEquals(sortedYears,years);
    }
}
