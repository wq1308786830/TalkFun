package com.russell.talkfun;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by qi on 2015/9/19.
 */
public class HttpData extends AsyncTask<String, Void, String> {


    private HttpDataListener httpDataListener;
    private String getURL;
    private StringBuffer sb;
//    String INFO;      //lisi Modified
//    String getURL = "http://www.tuling123.com/openapi/api?key=" + APIKEY + "&info=" + INFO;

    public HttpData(String url, HttpDataListener httpDataListener){
        this.getURL = url;
        this.httpDataListener = httpDataListener;
    }
    @Override
    protected String doInBackground(String... strings) {
        try {
//            INFO = encode("厦门今日天气", "utf-8");

            URL getUrl = new URL(getURL);
            HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
            connection.connect();

            // 取得输入流，并使用Reader读取
            BufferedReader reader = new BufferedReader(new InputStreamReader( connection.getInputStream(), "utf-8"));
            sb = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            // 断开连接
            connection.disconnect();
            return sb.toString();
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        httpDataListener.getDataUrl(s);
        super.onPostExecute(s);
    }
}
