package test;

import api.discover.GetDiscoveryList;
import api.discover.pojo.DiscoveryItems;
import api.discover.pojo.Item;
import api.drive.PostFileToDrive;
import api.drive.pojo.PostFileRequest;
import api.drive.pojo.PostFileResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.CSVAnnotation;
import utils.GenericDataProvider;
import utils.PropertiesManager;
import java.io.IOException;
import java.util.Map;

public class TestDriveAPI extends BaseTest {

    @Test(groups = { "Smoke","Regression","login" },
            dataProvider = "dataproviderForTestCase", dataProviderClass = GenericDataProvider.class)
    @CSVAnnotation.CSVFileParameters(path = "test-data\\drive\\PostFile.csv", delimiter = "###")
    public void PostFileInDrive(int rowNo,Map<String, String> testData) throws IOException {

        PostFileToDrive postFileToDrive = new PostFileToDrive(PropertiesManager.getProperty("baseURI"),accessToken);
        PostFileRequest postFileRequest=new PostFileRequest();
        postFileRequest.setDescription(testData.get("description"));
        postFileRequest.setName(testData.get("name"));

        postFileToDrive.setRequest(postFileRequest);
        postFileToDrive.setExpectedStatusCode(200);
        postFileToDrive.perform();
        PostFileResponse postFileResponse = postFileToDrive.getAPIResponseAsPOJO(PostFileResponse.class);
        Assert.assertEquals(testData.get("name"),postFileResponse.getName());
    }


}
