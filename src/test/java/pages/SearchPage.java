package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@DisplayName("Страница поиска")
public class SearchPage {
    private final SelenideElement searchInput = $("#js_header-middle__field-search");
    private final ElementsCollection searchResultCards = $$(".rb-product-card");
    private final SelenideElement descriptionSection = $("#js_accordion-content__composition");

    @Step("Выполнить поиск товара по запросу: {value}")
    public SearchPage searchForProduct(String value){
        searchInput.setValue(value).pressEnter();
        return this;
    }

    @Step("Проверить, что описание первого найденного товара содержит текст: {value}")
    public void verifyResultMatches(String value){
        searchResultCards.first().click();
        descriptionSection.shouldBe(Condition.visible).shouldHave(Condition.text(value));
    }
}
