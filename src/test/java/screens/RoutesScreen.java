package screens;

import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class RoutesScreen {

    final Logger logger = LoggerFactory.getLogger(RoutesScreen.class);

    @Step("Проверить что в текстах англ. локализации не пропущено русских букв")
    public void checkThatThereAreNoRussianWords() {
        // ожидаем полной загрузки экрана, некоторые элементы не имеющие четких признаков долго прогружаются
        sleep(3000);

        String allTexts = $$(AppiumBy.className("android.widget.TextView")).texts().toString();
        String allTextsReplaced = allTexts.replaceAll("\\s", " ");
        logger.info("Тексты элементов для проверки: " + allTextsReplaced);

        // Проверяем по регулярному выражению присутствуют ли эти буквы в тексте
        boolean result = allTextsReplaced.matches(".*[А-Яа-я]+.*");
        assertFalse(result, "В английской локализации пропущены русские буквы");
    }
}
