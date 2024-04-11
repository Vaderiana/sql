import data.DataHelper;
import data.SQLHelper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static data.SQLHelper.cleanDatabase;

class AuthTest {

    @BeforeEach
    void login() {
        open("http://localhost:9999");
    }
    @AfterAll
    static void tearDown(){
        cleanDatabase();
    }

    @Test
    void testValidLogin() {
        var loginPage = new LoginPage();
        var autoInfo = DataHelper.getAuthInfoWithTestData();
        var verificationPage = loginPage.validLogin(autoInfo);
        verificationPage.verifyVerificationPageVisibility();
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode.getCode());
    }

    @Test
    void testErrorOnVerificationCode() {
        var loginPage = new LoginPage();
        var autoInfo = DataHelper.getAuthInfoWithTestData();
        var verificationPage = loginPage.validLogin(autoInfo);
        verificationPage.verifyVerificationPageVisibility();
        var verificationCode = DataHelper.generateRandomVerificationCode();
        verificationPage.verifyCode(verificationCode.getCode());
        verificationPage.verifyErrorNotificationVisibility();
    }

    @Test
    void testErrorOnWrongUser(){
        var login = new LoginPage();
        var autoInfo = DataHelper.generateRandomUser();
        login.validLogin(autoInfo);
        login.verifyErrorNotification("Ошибка! \nНеверно указан логин или пароль");
    }
}