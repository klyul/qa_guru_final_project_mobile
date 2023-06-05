package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.BrowserstackMobileDriver;
import drivers.LocalMobileDriverOwnerConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBaseCommon {


    static Logger logger = LoggerFactory.getLogger(TestBaseCommon.class);

    static String runMode;

    @BeforeAll
    static void beforeAll() {

        runMode = System.getProperty("runMode", "local");
        logger.info("runMode is: " + runMode);

        switch (runMode) {
            case "local": {
                Configuration.browser = LocalMobileDriverOwnerConfig.class.getName();
                break;
            }
            case "browserstack": {
                Configuration.browser = BrowserstackMobileDriver.class.getName();
                break;
            }
            default:
                throw new IllegalArgumentException("Runmode is not recognized");
        }
        Configuration.browserSize = null;
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void afterEach() {
        if (runMode.equals("browserstack")) {
            try {
                Attach.screenshotAs("Last screenshot");
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                Attach.pageSource();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                String sessionId = Selenide.sessionId().toString();
                Attach.addVideo(sessionId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        closeWebDriver();
    }

}

