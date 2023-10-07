package github.iharuya.japanizer;

import java.util.HashMap;
import java.util.Map;

/**
 * 音訳を行う
 */
public class Transliterator {
  private static final String REGEX_URL = "https?://[\\w/:%#\\$&\\?\\(\\)~\\.=\\+\\-]+";

  /**
   * 日本語の音訳をする（ローマ字->普通の日本語）
   * 
   * @param text
   * @param dictionary 変換対象外リスト
   * @return
   */
  public static String japanize(String text, Map<String, String> dictionary) {

    if (!shouldJapanize(text)) {
      return "";
    }

    String textWithoutUrl = text.replaceAll(REGEX_URL, " ");

    // キーワードをロック 辞書にある部分は変換対象外にするため
    HashMap<String, String> keywordMap = new HashMap<String, String>();
    int index = 0;
    String keywordLockedtext = textWithoutUrl;
    for (String dickey : dictionary.keySet()) {
      if (keywordLockedtext.contains(dickey)) {
        index++;
        String key = "＜" + makeMultibytesDigit(index) + "＞";
        keywordLockedtext = keywordLockedtext.replace(dickey, key);
        keywordMap.put(key, dictionary.get(dickey));
      }
    }

    String japanizedtext = RomeToKana.convert(keywordLockedtext);
    japanizedtext = JapaneseIME.convert(japanizedtext);

    // キーワードのアンロック
    for (String key : keywordMap.keySet()) {
      japanizedtext = japanizedtext.replace(key, keywordMap.get(key));
    }

    return japanizedtext.trim();
  }

  /**
   * 日本語化が必要かどうかを判定する
   * 
   * @param text
   * @return
   */
  private static boolean shouldJapanize(String text) {
    return (text.getBytes().length == text.length() && !text.matches("[ \\uFF61-\\uFF9F]+"));
  }

  /**
   * 数値を、全角文字の文字列に変換して返す
   * 
   * @param digit
   * @return
   */
  private static String makeMultibytesDigit(int digit) {

    String half = Integer.toString(digit);
    StringBuilder result = new StringBuilder();
    for (int index = 0; index < half.length(); index++) {
      switch (half.charAt(index)) {
        case '0':
          result.append("０");
          break;
        case '1':
          result.append("１");
          break;
        case '2':
          result.append("２");
          break;
        case '3':
          result.append("３");
          break;
        case '4':
          result.append("４");
          break;
        case '5':
          result.append("５");
          break;
        case '6':
          result.append("６");
          break;
        case '7':
          result.append("７");
          break;
        case '8':
          result.append("８");
          break;
        case '9':
          result.append("９");
          break;
      }
    }
    return result.toString();
  }
}
