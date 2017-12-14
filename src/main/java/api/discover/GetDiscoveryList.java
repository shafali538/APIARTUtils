package api.discover;

import api.BaseAPI;

import static io.restassured.RestAssured.given;

public class GetDiscoveryList extends BaseAPI {

    String apiPath="/apis";
    String fields;

    public GetDiscoveryList(String baseURI) {
        super(baseURI);
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
        apiResponse = given().spec(requestSpecification).get();

    }

    @Override
    protected void validateResponse() {
        responseSpecBuilder.expectStatusCode(expectedStatusCode);
        responseSpecification=responseSpecBuilder.build();
        apiResponse.then().spec(responseSpecification);
    }
}