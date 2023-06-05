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

public class HelpScreen {

    ElementsCollection textElements = $$(AppiumBy.className("android.widget.TextView"));

    final Logger logger = LoggerFactory.getLogger(HelpScreen.class);

    @Step("Проверить тексты основных элементов меню страницы Help")
    public void checkScreenMainMenuItems() {

        textElements.shouldHave(sizeGreaterThan(0));

        String allTextsWhitespaceReplaced = textElements.texts().toString().replaceAll("\\s", " ");

        logger.info("Тексты перед проверкой: " + allTextsWhitespaceReplaced);

        textElements.shouldHave(CollectionCondition.containExactTextsCaseSensitive(
                "Questions about plane tickets",
                "Questions about train tickets",
                "Questions about bus tickets",
                "Coronavirus. What to do with tickets"
        ));

        assertTrue(allTextsWhitespaceReplaced.contains("Return or exchange your ticket"));
        assertTrue(allTextsWhitespaceReplaced.contains("on the Orders screen"));

    }

}

