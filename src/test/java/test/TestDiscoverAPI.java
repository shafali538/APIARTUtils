package test;

import api.discover.GetDiscoveryList;
import api.discover.pojo.DiscoveryItems;
import api.discover.pojo.Item;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PropertiesManager;

import java.io.IOException;

public class TestDiscoverAPI extends BaseTest {

    @Test
    public void checkCountOfDiscoveryAPI() throws IOException {

        //Get DiscoverY API
        GetDiscoveryList getDiscoveryList = new GetDiscoveryList(PropertiesManager.getProperty("baseURI"),accessToken);
        getDiscoveryList.setFields("items(title,version)");
        getDiscoveryList.setExpectedStatusCode(200);
        getDiscoveryList.perform();
        System.out.println(getDiscoveryList.getApiResponseAsString());

        //Verify that count and get title and version of API
        DiscoveryItems discoveryItems = getDiscoveryList.getAPIResponseAsPOJO(DiscoveryItems.class);
        for(Item item: discoveryItems.items){
         System.out.println(item.title);
         System.out.println(item.version);
        }
        Assert.assertEquals(193,discoveryItems.items.size());
    }
}
