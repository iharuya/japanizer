package github.iharuya.japanizer.common;

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
  public static String japanize(String text) {

    if (!shouldJapanize(text)) {
      return text;
    }

    String textWithoutUrl = text.replaceAll(REGEX_URL, " ");

    String japanizedtext = RomeToKana.convert(textWithoutUrl);
    japanizedtext = JapaneseIME.convert(japanizedtext);

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
}
