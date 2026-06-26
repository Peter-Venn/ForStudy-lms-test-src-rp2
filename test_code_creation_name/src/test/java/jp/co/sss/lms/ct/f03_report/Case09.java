package jp.co.sss.lms.ct.f03_report;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import jp.co.sss.lms.ct.util.WebDriverUtils;

/**
 * 結合テスト レポート機能
 * ケース09
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース09 受講生 レポート登録 入力チェック")
public class Case09 {

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
	@DisplayName("テスト03 上部メニューの「ようこそ○○さん」リンクからユーザー詳細画面に遷移")
	void test03() {
		//ユーザー詳細画面に遷移
		WebDriverUtils.webDriver.findElement(By.xpath("//a[@href='/lms/user/detail']")).click();
		//スクリーンショットを取得
		WebDriverUtils.getEvidence(new Object() {
		});

	}

	@Test
	@Order(4)
	@DisplayName("テスト04 該当レポートの「修正する」ボタンを押下しレポート登録画面に遷移")
	void test04() {
		//レポート編集画面に遷移
		String pixel = "700";
		WebDriverUtils.scrollBy(pixel);
		List<WebElement> elements = WebDriverUtils.webDriver.findElements(By.xpath("//input[@value='修正する']"));
		elements.get(1).click();
		//画面内要素を確認
		assertEquals("レポート登録 | LMS", WebDriverUtils.webDriver.getTitle(), "レポート修正画面に遷移する");//
		//スクリーンショットを取得
		WebDriverUtils.getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 報告内容を修正して「提出する」ボタンを押下しエラー表示：学習項目が未入力")
	void test05() {
		//学習項目を削除
		String pixel = "700";
		WebDriverUtils.webDriver.findElement(By.xpath("//input[@type='text']")).clear();
		WebDriverUtils.scrollBy(pixel);
		WebDriverUtils.webDriver.findElement(By.xpath("//button[@type='submit']")).click();
		//スクリーンショットを取得
		WebDriverUtils.getEvidence(new Object() {
		});
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 不適切な内容で修正して「提出する」ボタンを押下しエラー表示：理解度が未入力")
	void test06() {
		/*レポート編集画面に遷移
		String pixel = "700";
		WebDriverUtils.scrollBy(pixel);
		List<WebElement> elements = WebDriverUtils.webDriver.findElements(By.xpath("//input[@value='修正する']"));
		elements.get(1).click();
		//理解度をnullにする
		String pixel = "700";
		WebDriverUtils.webDriver.findElement(By.xpath("//option[@value=null]")).click();
		WebDriverUtils.scrollBy(pixel);
		WebDriverUtils.webDriver.findElement(By.xpath("//button[@type='submit']")).click();
		//スクリーンショットを取得
		WebDriverUtils.getEvidence(new Object() {
		});*/
	}

	@Test
	@Order(7)
	@DisplayName("テスト07 不適切な内容で修正して「提出する」ボタンを押下しエラー表示：目標の達成度が数値以外")
	void test07() {
		//レポート編集画面に遷移
		String pixel = "700";
		WebDriverUtils.scrollBy(pixel);
		List<WebElement> elements = WebDriverUtils.webDriver.findElements(By.xpath("//input[@value='修正する']"));
		elements.get(1).click();
		//目標の達成度を文字にする
		String text = "test";
		WebDriverUtils.webDriver.findElement(By.xpath("//textarea[@name='contentArray[0]']")).sendKeys(text);
		WebDriverUtils.scrollBy(pixel);
		WebDriverUtils.webDriver.findElement(By.xpath("//button[@type='submit']")).click();
		//スクリーンショットを取得
		WebDriverUtils.getEvidence(new Object() {
		});
	}

	@Test
	@Order(8)
	@DisplayName("テスト08 不適切な内容で修正して「提出する」ボタンを押下しエラー表示：目標の達成度が範囲外")
	void test08() {
		//レポート編集画面に遷移
		String pixel = "700";
		WebDriverUtils.scrollBy(pixel);
		List<WebElement> elements = WebDriverUtils.webDriver.findElements(By.xpath("//input[@value='修正する']"));
		elements.get(1).click();
		//目標の達成度を0にする
		String text = "0";
		WebDriverUtils.webDriver.findElement(By.xpath("//textarea[@name='contentArray[0]']")).sendKeys(text);
		WebDriverUtils.scrollBy(pixel);
		WebDriverUtils.webDriver.findElement(By.xpath("//button[@type='submit']")).click();
		//スクリーンショットを取得
		WebDriverUtils.getEvidence(new Object() {
		});
	}

	@Test
	@Order(9)
	@DisplayName("テスト09 不適切な内容で修正して「提出する」ボタンを押下しエラー表示：目標の達成度・所感が未入力")
	void test09() {
		//レポート編集画面に遷移
		String pixel = "700";
		WebDriverUtils.scrollBy(pixel);
		List<WebElement> elements = WebDriverUtils.webDriver.findElements(By.xpath("//input[@value='修正する']"));
		elements.get(1).click();
		//目標の達成度と所感を削除
		WebDriverUtils.webDriver.findElement(By.xpath("//textarea[@name='contentArray[0]']")).clear();
		WebDriverUtils.webDriver.findElement(By.xpath("//textarea[@name='contentArray[1]']")).clear();
		WebDriverUtils.scrollBy(pixel);
		WebDriverUtils.webDriver.findElement(By.xpath("//button[@type='submit']")).click();
		//スクリーンショットを取得
		WebDriverUtils.getEvidence(new Object() {
		});
	}

	@Test
	@Order(10)
	@DisplayName("テスト10 不適切な内容で修正して「提出する」ボタンを押下しエラー表示：所感・一週間の振り返りが2000文字超")
	void test10() {
		//レポート編集画面に遷移
		String pixel = "700";
		WebDriverUtils.scrollBy(pixel);
		List<WebElement> elements = WebDriverUtils.webDriver.findElements(By.xpath("//input[@value='修正する']"));
		elements.get(1).click();
		//所感の文字数を2000字以上にする
		String test = "test ";
		WebDriverUtils.webDriver.findElement(By.xpath("//textarea[@name='contentArray[1]']")).clear();
		WebDriverUtils.webDriver.findElement(By.xpath("//textarea[@name='contentArray[2]']")).clear();
		for (int i = 0; i <= 2000; i++) {
			WebDriverUtils.webDriver.findElement(By.xpath("//textarea[@name='contentArray[1]']")).sendKeys(test);
			WebDriverUtils.webDriver.findElement(By.xpath("//textarea[@name='contentArray[2]']")).sendKeys(test);
		}
		WebDriverUtils.scrollBy(pixel);
		WebDriverUtils.webDriver.findElement(By.xpath("//button[@type='submit']")).click();
		//スクリーンショットを取得
		WebDriverUtils.getEvidence(new Object() {
		});
	}

}
