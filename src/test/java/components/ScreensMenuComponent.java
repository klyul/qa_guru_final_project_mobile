package components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class ScreensMenuComponent {

    SelenideElement helpScreenButton = $(id("ru.tutu.tutu_emp:id/navigation_help")),
            profileScreenButton = $(id("ru.tutu.tutu_emp:id/navigation_profile"));

    @Step("Переходим по кнопке на Help")
    public void clickHelpButton() {
        helpScreenButton.click();
    }

    @Step("Переходим по кнопке на Profile")
    public void clickProfileButton() {
        profileScreenButton.click();
    }

}