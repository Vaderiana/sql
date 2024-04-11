package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
public class DashboardPage {
    private final SelenideElement head = $("[data-test-id=dashboard]");

    public DashboardPage() {
        head.shouldHave(text("Личный кабинет")).shouldBe(visible);
    }
}