package jp.co.sss.lms.ct.f01_login1;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;

import jp.co.sss.lms.ct.util.WebDriverUtils;

/**
 * 結合テスト ログイン機能①
 * ケース03
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース03 受講生 ログイン 正常系")
public class Case03 {

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
	}

	/** 後処理 */
	@AfterAll
	static void after() {
		closeDriver();
	}

	@Test
	@Order(1)
	@DisplayName("テスト01 トップページURLでアクセス")
	void test01() {
		//サーバーにアクセス
		String url = "http://localhost:8080/lms";//定数化
		WebDriverUtils.goTo(url);
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	void test02() {
		// ログインしているアカウント情報を渡す
		String ID = "StudentAA01";
		String PW = "StudentAA1";
		int waitTime = 3;
		//ログインテスト正常系
		WebDriverUtils.webDriver.findElement(By.id("loginId")).sendKeys(ID);
		WebDriverUtils.webDriver.findElement(By.id("password")).sendKeys(PW);
		WebDriverUtils.webDriver.findElement(By.xpath("//input[@value='ログイン']")).click();
		WebDriverUtils.pageLoadTimeout(waitTime);//マジックナンバー防止
		//スクリーンショットを取得
		WebDriverUtils.getEvidence(new Object() {
		});
	}

}
