package test;

import api.discover.GetDiscoveryList;
import api.discover.pojo.DiscoveryItems;
import api.discover.pojo.Item;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PropertiesManager;

import java.io.IOException;

public class TestDiscoveryAPI extends BaseTest {

    public static final Logger logger = Logger.getLogger(TestDiscoveryAPI.class);

    @Test
    public void checkCountOfDiscoveryAPI() throws IOException {

        //Get API Title and Version from the Get Discovery API
        GetDiscoveryList getDiscoveryList = new GetDiscoveryList(PropertiesManager.getProperty("discoveryBaseURI"));
        getDiscoveryList.setFields("items(title,version)");
        getDiscoveryList.setExpectedStatusCode(200);
        getDiscoveryList.perform();

        //Verify the count
        DiscoveryItems discoveryItems = getDiscoveryList.getAPIResponseAsPOJO(DiscoveryItems.class);
        logger.info("Number of API's returned : " +  discoveryItems.items.size());
        Assert.assertEquals(193,discoveryItems.items.size());
    }
}