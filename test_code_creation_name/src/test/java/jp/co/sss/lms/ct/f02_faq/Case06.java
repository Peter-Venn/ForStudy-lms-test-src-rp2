package jp.co.sss.lms.ct.f02_faq;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

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
 * 結合テスト よくある質問機能
 * ケース06
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース06 カテゴリ検索 正常系")
public class Case06 {

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
		//ウェブページのインスタンスを生成
		String url = "http://localhost:8080/lms";//定数化
		WebDriverUtils.goTo(url);
		//画面内要素を確認
		assertEquals("ログイン | LMS", WebDriverUtils.webDriver.getTitle(), "ログイン画面に遷移する");
		assertNotNull(WebDriverUtils.webDriver.findElement(By.xpath("//input[@value='ログイン']")),
				"ログイン画面にログインボタンが存在している");
		//スクリーンショットを取得
		WebDriverUtils.getEvidence(new Object() {
		});
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	void test02() {
		// ログインしているアカウント情報を渡す
		String ID = "StudentAA01";
		String PW = "StudentAA1";
		int waitTime = 120;
		//ログインテスト正常系
		WebDriverUtils.webDriver.findElement(By.id("loginId")).sendKeys(ID);
		WebDriverUtils.webDriver.findElement(By.id("password")).sendKeys(PW);
		WebDriverUtils.webDriver.findElement(By.xpath("//input[@value='ログイン']")).click();
		WebDriverUtils.pageLoadTimeout(waitTime);//マジックナンバー防止
		//画面内要素を確認
		assertEquals("コース詳細 | LMS", WebDriverUtils.webDriver.getTitle(), "コース詳細画面に遷移する");
		//スクリーンショットを取得
		WebDriverUtils.getEvidence(new Object() {
		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 上部メニューの「ヘルプ」リンクからヘルプ画面に遷移")
	void test03() {
		//ヘルプ画面に遷移
		WebDriverUtils.webDriver.findElement(By.xpath("//a[contains(text(), '機能')]")).click();
		int waitTime = 120;
		WebDriverUtils.webDriver.findElement(By.xpath("//a[contains(text(), 'ヘルプ')]")).click();
		WebDriverUtils.pageLoadTimeout(waitTime);
		//画面内要素を確認
		assertEquals("ヘルプ | LMS", WebDriverUtils.webDriver.getTitle(), "ヘルプ画面に遷移する");
		//スクリーンショットを取得
		WebDriverUtils.getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「よくある質問」リンクからよくある質問画面を別タブに開く")
	void test04() {
		//よくある質問画面に遷移
		int waitTime = 120;
		WebDriverUtils.webDriver.findElement(By.xpath("//a[@href='/lms/faq']")).click();
		Set<String> allWindows = webDriver.getWindowHandles();
		String lastWindow = allWindows.toArray(new String[0])[allWindows.size() - 1];//タブを配列化
		WebDriverUtils.webDriver.switchTo().window(lastWindow);//タブを切り替える
		WebDriverUtils.pageLoadTimeout(waitTime);
		//画面内要素を確認
		assertEquals("よくある質問 | LMS", WebDriverUtils.webDriver.getTitle(), "よくある質問画面に遷移する");
		//スクリーンショットを取得
		WebDriverUtils.getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 カテゴリ検索で該当カテゴリの検索結果だけ表示")
	void test05() {
		//カテゴリ検索を実行
		int waitTime = 120;
		String url = "/lms/faq?frequentlyAskedQuestionCategoryId=";
		String no = "1";
		WebDriverUtils.webDriver.findElement(By.xpath("//a[@href='" + url + no + "']")).click();//変数ブロック化
		WebDriverUtils.pageLoadTimeout(waitTime);
		//画面内要素を確認
		String bottomPixel = "585.33";
		WebDriverUtils.scrollTo(bottomPixel);
		assertNotNull(WebDriverUtils.webDriver.findElement(By.xpath("//span")), "検索された質問が正しく表示");
		//スクリーンショットを取得
		WebDriverUtils.getEvidence(new Object() {
		});
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 検索結果の質問をクリックしその回答を表示")
	void test06() {
		//検索結果を検査
		WebDriverUtils.webDriver.findElement(By.xpath("//span[text()='Q.']")).click();
		assertNotNull(WebDriverUtils.webDriver.findElement(By.xpath("//span[text()='A.']")), "検索された質問の回答が正しく表示");
		//スクリーンショットを取得
		WebDriverUtils.getEvidence(new Object() {
		});
	}

}
