package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.*;
import testData.TestData;
import java.util.Map;
import static com.codeborne.selenide.Selenide.*;

public class BaseTest {

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    MainPage mainPage = new MainPage();
    SearchPage searchPage = new SearchPage();
    CartPage cartPage = new CartPage();
    QuickOrderPage quickOrderPage = new QuickOrderPage();
    AuthPage authPage = new AuthPage();
    TestData testData = new TestData();

    @BeforeAll
    static void setupConfig(){
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserSize = System.getProperty("browserResolution", "1920x1080");
        Configuration.headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        Configuration.baseUrl = System.getProperty("baseUrl","https://rus-buket.ru");

        String browserVersion = System.getProperty("browserVersion");
        if (browserVersion != null && !browserVersion.isBlank()) {
            Configuration.browserVersion = browserVersion;
        }

        String remoteUrl = System.getProperty("remoteUrl");
        if (remoteUrl != null && !remoteUrl.isBlank()) {
            Configuration.remote = remoteUrl;
        }

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

    }

    @AfterEach
    void tearDown(){
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}
