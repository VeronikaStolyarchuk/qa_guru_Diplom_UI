package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;
import static com.codeborne.selenide.Selenide.*;

@DisplayName("Страница быстрого заказа")
public class QuickOrderPage {
    private final ElementsCollection searchResults = $$(".rb-product-card");
    private final SelenideElement addToOrderButton = $(".product-card__hidden-el");
    private final SelenideElement userPhone = $(".tingle-modal input[name='phone']");
    private final SelenideElement sendApplicationButton  = $(".tingle-modal [name='js_btn_submit']");
    private final SelenideElement modalMessage = $(".tingle-modal-box__content");

    @Step("Добавить первый товар из результатов поиска в быстрый заказ")
    public QuickOrderPage addToQuickOrder(){
        searchResults.first().hover();
        addToOrderButton.click();
        return this;
    }

    @Step("Ввести номер телефона в модальном окне: {value}")
    public QuickOrderPage typeUserNumber(String value){
        executeJavaScript(
                "arguments[0].value = arguments[1]; " +
                        "arguments[0].dispatchEvent(new Event('input', { bubbles: true })); " +
                        "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                userPhone, value
        );
        return this;
    }

    @Step("Отправить заявку на быстрый заказ")
    public QuickOrderPage sendOrderRequest(){
        sendApplicationButton.click();
        return this;
    }

    @Step("Проверить текст сообщения в модальном окне: {value}")
    public void verifyModalMessage(String value){
        modalMessage.shouldHave(Condition.text(value));
    }
}
