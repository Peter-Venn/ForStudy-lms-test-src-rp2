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
 * ケース07
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース07 受講生 レポート新規登録(日報) 正常系")
public class Case07 {

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
	@DisplayName("テスト03 未提出の研修日の「詳細」ボタンを押下しセクション詳細画面に遷移")
	void test03() {
		//「未提出」研修にlocate
		String pixel = "300";
		WebDriverUtils.scrollBy(pixel);
		List<WebElement> elements = WebDriverUtils.webDriver.findElements(By.xpath("//input[@value='詳細']"));
		elements.get(2).click();
		//画面内要素を確認
		assertEquals("セクション詳細 | LMS", WebDriverUtils.webDriver.getTitle(), "セクション画面に遷移する");
		//スクリーンショットを取得
		WebDriverUtils.getEvidence(new Object() {
		});

	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「提出する」ボタンを押下しレポート登録画面に遷移")
	void test04() {
		//日報記入画面に遷移
		WebDriverUtils.webDriver.findElement(By.xpath("//input[@value='日報【デモ】を提出する']")).click();
		//スクリーンショットを取得
		WebDriverUtils.getEvidence(new Object() {
		});

	}

	@Test
	@Order(5)
	@DisplayName("テスト05 報告内容を入力して「提出する」ボタンを押下し確認ボタン名が更新される")
	void test05() {
		// 日報を記入して提出する
		String text = "test";
		WebDriverUtils.webDriver.findElement(By.xpath("//textarea[@class='form-control']")).sendKeys(text);
		WebDriverUtils.webDriver.findElement(By.xpath("//button[@type='submit']")).click();
		//スクリーンショットを取得
		WebDriverUtils.getEvidence(new Object() {
		});
	}

}
