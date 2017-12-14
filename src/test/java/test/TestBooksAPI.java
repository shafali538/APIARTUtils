package test;

import api.volumes.GetVolumes;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.bookshelf.Books;
import pojo.bookshelf.Item;
import utils.PropertiesManager;
import api.mylibraries.bookshelf.*;

/**
 * Created by kohlih on 12-11-2017.
 */
public class TestBooksAPI extends BaseTest {

    public static final Logger logger = Logger.getLogger(TestBooksAPI.class);

    @Test(groups={"Regression"})
    public void addFirstSearchResultToReadingNow() throws Exception{
        //Get Books by searching Chetan Bhagat
        GetVolumes getVolumes = new GetVolumes(PropertiesManager.getProperty("booksBaseURI"),"Chetan Bhagat");
        getVolumes.setExpectedStatusCode(200);
        getVolumes.perform();
        Books searchedBooks = getVolumes.getAPIResponseAsPOJO(Books.class);

        //Add it to the reading now
        PostAddVolume postAddVolume = new PostAddVolume(PropertiesManager.getProperty("booksBaseURI"),accessToken);
        postAddVolume.setShelfId(3);
        postAddVolume.setVolumeId(searchedBooks.getItems().get(0).getId());
        postAddVolume.setExpectedStatusCode(204);
        postAddVolume.perform();
        logger.info( searchedBooks.getItems().get(0).getVolumeInfo().getTitle() + " added to 'Reading Now' shelf");

        //Get Volumes from Reading Now
        GetBookShelfVolumes getBookShelfVolumes = new GetBookShelfVolumes(PropertiesManager.getProperty("booksBaseURI"),accessToken);
        getBookShelfVolumes.setShelfId(3);
        getBookShelfVolumes.setExpectedStatusCode(200);
        getBookShelfVolumes.perform();
        Books recommendedBooks = getBookShelfVolumes.getAPIResponseAsPOJO(Books.class);

        //Check if the added book is present in the shelf
        Boolean bookFound=false;
        for(Item item : recommendedBooks.getItems()){
            if(item.getVolumeInfo().getTitle().equals(searchedBooks.getItems().get(0).getVolumeInfo().getTitle())){
                bookFound=true;
            }
        }
        Assert.assertTrue(bookFound,"Added book should be present in the Reading now shelf");
    }
}