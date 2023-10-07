package github.iharuya.japanizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

/**
 * ひらがなをGoogle IMEで通常の文章に変換する 必ずしも正確な変換結果になるとは限らない
 */
public class JapaneseIME {

  private static final String GOOGLE_IME_URL =
      "https://www.google.com/transliterate?langpair=ja-Hira|ja&text=";

  public static String convert(String text) {

    if (text.length() == 0) {
      return "";
    }

    HttpURLConnection urlconn = null;
    BufferedReader reader = null;
    try {
      String baseurl = GOOGLE_IME_URL + URLEncoder.encode(text, "UTF-8");
      String encode = "UTF-8";
      URL url = new URL(baseurl);

      urlconn = (HttpURLConnection) url.openConnection();
      urlconn.setRequestMethod("GET");
      urlconn.setInstanceFollowRedirects(false);
      urlconn.connect();

      reader = new BufferedReader(new InputStreamReader(urlconn.getInputStream(), encode));

      String json = CharStreams.toString(reader);
      return parseJson(json);

    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (ProtocolException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (urlconn != null) {
        urlconn.disconnect();
      }
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e) {
        }
      }
    }

    return "";
  }

  private static String parseJson(String json) {
    StringBuilder result = new StringBuilder();
    for (JsonElement res : new Gson().fromJson(json, JsonArray.class)) {
      result.append(res.getAsJsonArray().get(1).getAsJsonArray().get(0).getAsString());
    }
    return result.toString();
  }
}
