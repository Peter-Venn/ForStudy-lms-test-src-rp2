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
 * ケース05
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース05 キーワード検索 正常系")
public class Case05 {

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
	@DisplayName("テスト05 キーワード検索で該当キーワードを含む検索結果だけ表示")
	void test05() {
		//検索欄にキーワードを入力
		String keyword = "キャンセル";
		int waitTime = 120;
		WebDriverUtils.webDriver.findElement(By.id("form")).sendKeys(keyword);
		WebDriverUtils.webDriver.findElement(By.xpath("//input[@value='検索']")).click();
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
	@DisplayName("テスト06 「クリア」ボタン押下で入力したキーワードを消去")
	void test06() {
		//検索欄にキーワードを消去
		int waitTime = 120;
		WebDriverUtils.webDriver.findElement(By.xpath("//input[@value='クリア']")).click();
		WebDriverUtils.pageLoadTimeout(waitTime);
		//画面内要素を確認
		String upPixel = "0";
		WebDriverUtils.scrollTo(upPixel);
		assertNull(WebDriverUtils.webDriver.findElement(By.id("form")).getText(), "キーワードを消去済み");
		//スクリーンショットを取得
		WebDriverUtils.getEvidence(new Object() {
		});
	}

}
