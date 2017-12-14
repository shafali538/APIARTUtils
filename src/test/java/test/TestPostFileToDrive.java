package test;

import api.discover.GetDiscoveryList;
import api.discover.pojo.DiscoveryItems;
import api.discover.pojo.Item;
import api.drive.PostFileToDrive;
import api.drive.pojo.PostFileResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PropertiesManager;

import java.io.IOException;
import java.util.HashMap;

public class TestPostFileToDrive extends BaseTest {

    @Test
    public void PostFileInDrive() throws IOException {

        //Get DiscoverY API
        PostFileToDrive postFileToDrive = new PostFileToDrive(PropertiesManager.getProperty("baseURI"),accessToken);
        HashMap<String,String> params=new HashMap<>();
        //params.put("fields","capabilities(canCopy,canEdit),createdTime");
        postFileToDrive.setFields(params);
        postFileToDrive.setRequest("{" +
                " \"description\": \"FirstFile\"" +","+
                " \"name\": \"Test2.jpg\"" +
                "}");
        postFileToDrive.setExpectedStatusCode(200);
        postFileToDrive.perform();
        PostFileResponse postFileResponse = postFileToDrive.getAPIResponseAsPOJO(PostFileResponse.class);
        Assert.assertEquals("Test2.jpg",postFileResponse.getName());
    }


}
