package screens;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$$;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProfileScreen {

    ElementsCollection textElements = $$(AppiumBy.className("android.widget.TextView"));

    final Logger logger = LoggerFactory.getLogger(ProfileScreen.class);

    @Step("Проверить тексты основных элементов меню страницы Profile")
    public void checkScreenMainMenuItems() {
        textElements.shouldHave(sizeGreaterThan(0));

        String allTextsWhitespaceReplaced = textElements.texts().toString().replaceAll("\\s", " ");

        logger.info("Тексты перед проверкой: " + allTextsWhitespaceReplaced);

        textElements.shouldHave(CollectionCondition.containExactTextsCaseSensitive(
                "Orders",
                "Passengers",
                "Notifications",
                "Leave us feedback",
                "Application language"
        ));

        assertTrue(allTextsWhitespaceReplaced.contains("Personal data processing policy"));
        assertTrue(allTextsWhitespaceReplaced.contains("Legal info"));

    }

}