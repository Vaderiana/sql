package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");
    private SelenideElement errorCode = $ ("[data-test-id=error-notification]");
    public void verifyVerificationPageVisibility() {
        codeField.shouldBe(Condition.visible);
    }

    public void verifyErrorNotificationVisibility() {
        errorCode.shouldBe(visible);    }

    public void verifyCode(String verificationCode) {
        codeField.setValue(verificationCode);
        verifyButton.click();
    }

    public DashboardPage validVerify(String verificationCode) {
        verifyCode(verificationCode);
        return new DashboardPage();
    }
}