package api.drive;

import api.BaseAPI;
import api.drive.pojo.PostFileRequest;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PostFileToDrive extends BaseAPI {

    String apiPath="/files";
    String accessToken;
    PostFileRequest request;

    public PostFileToDrive(String baseURI,String accessToken) {
        super(baseURI);
        this.accessToken = accessToken;
    }


    public void setRequest(PostFileRequest request){this.request=request;}

    @Override
    protected void createRequest() {
        requestSpecBuilder.setBaseUri(baseURI);
        requestSpecBuilder.setBasePath(apiPath);
        requestSpecBuilder.setContentType("application/json");
        requestSpecBuilder.setBody(request);
        requestSpecification=requestSpecBuilder.build();
    }

    @Override
    protected void executeRequest() {
        apiResponse = given().spec(requestSpecification).auth().oauth2(accessToken).post();
    }

    @Override
    protected void validateResponse() {
        responseSpecBuilder.expectStatusCode(expectedStatusCode);
        responseSpecification=responseSpecBuilder.build();
        apiResponse.then().spec(responseSpecification);
    }
}