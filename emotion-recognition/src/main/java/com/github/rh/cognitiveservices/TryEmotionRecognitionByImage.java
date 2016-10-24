/**
 * 
 */
package com.github.rh.cognitiveservices;

import java.io.IOException;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Emotion APIで顔画像から感情分析を行ってみる(2016/10/24).
 * 
 * @author r-hirakawa
 */
public class TryEmotionRecognitionByImage {

    private static final String EMOTION_API_URL = "https://api.projectoxford.ai/emotion/v1.0/recognize";
    private static final String SUBSCRIPTION = "dummy";  // FIXME: correct subscription

    public static void main(String[] args) {

        // 1. HTTPクライアントを生成
        try (CloseableHttpClient client = HttpClients.createDefault()) {
 
            // 2. POST要求を生成
            //      分析対象の画像のURLを指定するJSON文字列
            String req = "{ \"url\": \"https://portalstoragewuprod.azureedge.net/media/Default/Media/EmotionAPI/Emotion.jpg\" }";
            //      POSTメソッドを生成
            HttpPost post = new HttpPost(EMOTION_API_URL);
            post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            post.setHeader("Ocp-Apim-Subscription-Key", SUBSCRIPTION);
            post.setEntity(new StringEntity(req));

            // 3. APIを呼び出し応答データを取得
            try (CloseableHttpResponse response = client.execute(post)) {
                String resp = EntityUtils.toString(response.getEntity());
                System.out.println(resp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
