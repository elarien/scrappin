import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.internal.connection.Time;
import org.bson.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class Scrapper extends TimerTask {
    @Override
    public void run() {
        System.out.println("Begin scrapping: " + LocalDateTime.now());
        HomePage homePage = new HomePage();
        MongoDatabase mongoDb = connectToMongo();
        initialiseBrowser();

        homePage.goTo();
        homePage.listTopArticles();
        List<WebElement> elements = homePage.getAllArticles();
        List<String> subreddits = new ArrayList<>();
        for (WebElement element : elements) {
            String subReddit;
            try {
                subReddit = element.findElement(By.xpath(".//a[@data-click-id='subreddit']")).getAttribute("href");
            } catch (NoSuchElementException ex) {
                System.out.println("SubReddit: PROMOTION!");
                continue;
            }

            subreddits.add(subReddit);
        }
        MongoCollection<Document> mongoCollection = mongoDb.getCollection("test");
        for (String subreddit : subreddits) {
            Document document = new Document(String.valueOf(Time.nanoTime()), subreddit);
            mongoCollection.insertOne(document);
        }

        closeBrowser();
        System.out.println("End scrapping: " + LocalDateTime.now());
    }

    private static MongoDatabase connectToMongo() {
        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://elarien:blabla2012@subreddits.a1ige.mongodb.net/subreddits?retryWrites=true&w=majority");

        MongoClient mongoClient = new MongoClient(uri);
        return mongoClient.getDatabase("subreddits");
    }

    private static void initialiseBrowser() {
        DriverManager.setWebDriver(new ChromeDriver());
    }

    private static void closeBrowser() {
        DriverManager.quitDriver();
    }


}
