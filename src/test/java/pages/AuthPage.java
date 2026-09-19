package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Страница авторизации")
public class AuthPage {
    private final SelenideElement openLoginFormLink = $("#js_header-login");
    private final SelenideElement loginField = $("input[name='login']");
    private final SelenideElement submitLoginButton = $("#js_btn-login");
    private final SelenideElement emptyPasswordField = $("input[name='password']");

    @Step("Открыть форму авторизации")
    public AuthPage clickLoginField() {
        openLoginFormLink.click();
        return this;
    }

    @Step("Ввести email: {value}")
    public AuthPage setEmailLogin(String value) {
        loginField.setValue(value);
        return this;
    }

    @Step("Нажать кнопку «Войти»")
    public AuthPage clickLogInButton() {
        submitLoginButton.click();
        return this;
    }

    @Step("Проверить сообщение валидации при пустом поле пароля")
    public void assertPasswordFieldValidationMessage(String value) {
        String validationMessage = executeJavaScript(
                "return arguments[0].validationMessage;",
                emptyPasswordField
        );
        assertThat(validationMessage).isNotBlank().contains(value);
    }
}
