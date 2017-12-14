package api.discover;

import api.BaseAPI;

import static io.restassured.RestAssured.given;

public class GetDiscoveryList extends BaseAPI {

    String apiPath="/discovery/v1/apis";
    String accessToken;
    String fields;

    public GetDiscoveryList(String baseURI,String accessToken) {
        super(baseURI);
        this.accessToken = accessToken;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    @Override
    protected void createRequest() {
        requestSpecBuilder.setBaseUri(baseURI);
        requestSpecBuilder.setBasePath(apiPath);
        requestSpecBuilder.addParam("fields",fields);
        requestSpecification=requestSpecBuilder.build();
    }

    @Override
    protected void executeRequest() {
        apiResponse = given().spec(requestSpecification).auth().oauth2(accessToken).get();
    }

    @Override
    protected void validateResponse() {
        responseSpecBuilder.expectStatusCode(expectedStatusCode);
        responseSpecification=responseSpecBuilder.build();
        apiResponse.then().spec(responseSpecification);
    }
}
