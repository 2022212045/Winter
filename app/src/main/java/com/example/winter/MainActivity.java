package com.example.winter;

import static com.google.android.material.button.MaterialButtonToggleGroup.CornerData.start;
import static java.lang.System.in;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    private EditText mEditText;
    private Button mbutton;
    private EditText mEdiText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Scanner scan = new Scanner(in);
        int number = scan.nextInt();
        Scanner scan2 = new Scanner(in);
        String key = scan.next();
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sendGetNetRequest("http://jwzx.cqupt.edu.cn/login.php");
                //注意这里的参数是以键值对的形式提交的，但网络请求的参数不仅仅只有键值对类型的，还有很多，具体去看我给你们推荐的那几篇文章和视频，由于时间的问题只讲这一种。
                HashMap<String, String> map = new HashMap<>();
                map.put("统一认证码", "number");
                map.put("密码", "key");
                sendPostNetRequest("http://jwzx.cqupt.edu.cn/kebiao/kb_stu.php?xh=" + number, map);
            }
        });

    }

    private void sendPostNetRequest(String mUrl, HashMap<String, String> params) {
        new Thread(
                () -> {
                    try {
                        URL url = new URL(mUrl);
                        HttpURLConnection connection = (HttpURLConnection)
                                url.openConnection();
                        connection.setRequestMethod("POST");//设置请求方式为POST
                        connection.setConnectTimeout(8000);//设置最大连接时间，单位为ms
                        connection.setReadTimeout(8000);//设置最大的读取时间，单位为ms
//                        connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
//                        connection.setRequestProperty("Accept-Encoding", "gzip,deflate");
                        connection.setDoOutput(true);
                        connection.setDoInput(true);
                        StringBuilder dataToWrite = new StringBuilder();//构建参数值
                        for (String key : params.keySet()) {
                            dataToWrite.append(key).append("=").append(params.get(key)).append("&");//拼接参
                        }
                        connection.connect();//正式连接
                        OutputStream outputStream =
                                connection.getOutputStream();//开
                        outputStream.write(dataToWrite.substring(0,
                                dataToWrite.length() - 1).getBytes());//去除最后一个&
                        InputStream in = connection.getInputStream();//从接口处获取输入
                        String responseData = StreamToString(in);//这里就是服务器返回的
                        Log.d("RQ", "sendPostNetRequest: " + responseData);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                        connection.connect();//正式连接
//                        InputStream in = connection.getInputStream();//从接口处获取
//                        String responseData = StreamToString(in);//这里就是服务器返回的数据
//                        Log.d("RQ", "sendGetNetRequest: " + responseData);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
        ).start();
                }

//    private String StreamToString(InputStream in) {
//        StringBuilder sb = new StringBuilder();//新建一个StringBuilder，用于一点一点
//        String oneLine;//流转换为字符串的一行
//        BufferedReader reader = new BufferedReader(new InputStreamReader(in));//
//        try {
//            while ((oneLine = reader.readLine()) != null) {//readLine方法将读取一行
//                sb.append(oneLine).append('\n');//拼接字符串并且增加换行，提高可读性
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                in.close();//关闭InputStream
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return sb.toString();//将拼接好的字符串返回出去
//    }

        private void initView () {
            mEditText = findViewById(R.id.editText);
            mbutton = findViewById(R.id.btn);
            mEdiText2 = findViewById(R.id.editText2);
        }


//        try {
//            url = new URL("http://jwzx.cqupt.edu.cn/index.php?6OL0qOaxvd6M=1668601928755#");
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        HttpURLConnection connection = null;
//        try {
//            connection = (HttpURLConnection) url.openConnection();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        connection.setConnectTimeout(8000);//设置最大连接时间，单位为ms
//        connection.setReadTimeout(8000);//设置最大的读取时间，单位为ms
//        connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
//        connection.setRequestProperty("Accept-Encoding", "gzip,deflate");


        private String StreamToString (InputStream in){
            StringBuilder sb = new StringBuilder();//新建一个StringBuilder，用于一点一点
            String oneLine;//流转换为字符串的一行
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));//
            try {
                while ((oneLine = reader.readLine()) != null) {//readLine方法将读取一行
                    sb.append(oneLine).append('\n');//拼接字符串并且增加换行，提高可读性
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();//关闭InputStream
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return sb.toString();//将拼接好的字符串返回出去
        }
    }