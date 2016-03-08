package com.russell.talkfun;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements HttpDataListener, View.OnClickListener {

    private HttpData httpData;
    private List<ListData> list;
    private String APIKEY = "d257baf9ce7cfafd71bc69e5dfff9968";
    private String INFO = "";
    private EditText editText;
    private Button button;
    private ListView listView;
    private TextAdapter textAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        System.out.println(sHA1(getApplicationContext()));
    }

    @Override
    public void getDataUrl(String data) {
        parseJson(data);
    }

    public void parseJson(String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
//            System.out.println(jsonObject.getString("code"));
            System.out.println(jsonObject.getString("text"));
            ListData listData;
            listData = new ListData(jsonObject.getString("text"), ListData.RECEIVE);
            list.add(listData);
            textAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        list = new ArrayList<ListData>();
        listView = (ListView) findViewById(R.id.lv);
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        textAdapter = new TextAdapter(list, this);
        listView.setAdapter(textAdapter);
        textAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        INFO = editText.getText().toString();
        if(INFO.equals("高德")){
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        }
        editText.setText("");
        ListData listData;
        listData = new ListData(INFO, ListData.SEND);
        list.add(listData);
        textAdapter.notifyDataSetChanged();
        httpData = (HttpData) new HttpData("http://www.tuling123.com/openapi/api?key=" + APIKEY + "&info=" + INFO, this).execute();
    }

    public static String sHA1(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_SIGNATURES);

            byte[] cert = info.signatures[0].toByteArray();

            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i])
                        .toUpperCase(Locale.US);
                if (appendString.length() == 1)
                    hexString.append("0");
                hexString.append(appendString);
                hexString.append(":");
            }
            return hexString.toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
