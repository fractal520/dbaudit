package wtsd.dbaudit.Monitor.Wechat.Utils;

import io.swagger.models.HttpMethod;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import wtsd.dbaudit.Monitor.Wechat.Entity.AccessToken;
import wtsd.dbaudit.Monitor.Wechat.Exception.BizException;
import wtsd.dbaudit.Monitor.Wechat.MyX509TrustManager;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WeiXinUtil {

    private static Logger log = LoggerFactory.getLogger(WeiXinUtil.class);
    //微信的请求url
    //获取access_token的接口地址（GET） 限200（次/天）
    public final static String access_token_url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid={corpId}&corpsecret={corpsecret}";
    //存储accessToken的Map，key=corpId_agentSecret,value=accessToken
    public static volatile Map<String, AccessToken> tokenMap = new ConcurrentHashMap<String, AccessToken>();



    /**
     * 1.发起https请求并获取结果
     *
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr 提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if (HttpMethod.GET.toString().equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();

            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            inputStream = httpUrlConn.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            httpUrlConn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {
            log.error("Weixin server connection timed out.");
            throw new BizException(EnumsUtils.WEIXIN_CONNECT_FAILED);
        } catch (Exception e) {
            log.error("https request error:{}", e);
            throw new BizException(EnumsUtils.WEIXIN_HAPPEN_EXCEPTION);
        }finally {
            try {
                // 释放资源
                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();
                inputStream = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }


    /**
     * 2.发起http请求获取返回结果
     *
     * @param requestUrl 请求地址
     * @return
     */
    public static String httpRequest(String requestUrl) {
        StringBuffer buffer = new StringBuffer();
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();

            httpUrlConn.setDoOutput(false);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);

            httpUrlConn.setRequestMethod("GET");
            httpUrlConn.connect();

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            //InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);

            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();

        } catch (Exception e) {
        }
        return buffer.toString();
    }


    /**
     * 3.获取access_token
     *
     * @param corpId 凭证
     * @param appsecret 密钥
     * @return
     */
    public static AccessToken getAccessToken(String corpId, String appsecret) {
        AccessToken accessToken = null;
        //如果tokenMap已经有缓存token，直接返回，不用请求微信接口
        //注意因为token都是有失效时间的，所有返回的有可能是失败的token
        String key = corpId + "_" + appsecret;
        if(!StringUtils.isEmpty(tokenMap.get(key))){
            accessToken = tokenMap.get(key);
            return accessToken;
        }
        //如果是第一次请求token，通过corpId、appsecret请求企业微信的获取token的接口
        //拼装请求的url
        String requestUrl = access_token_url.replace("{corpId}", corpId).replace("{corpsecret}", appsecret);
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
        // 如果请求成功
        if (null != jsonObject) {
            try {
                accessToken = new AccessToken();
                accessToken.setToken(jsonObject.getString("access_token"));
                accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
                //获取token成功，缓存进tokenMap中
                tokenMap.put(key, accessToken);
            } catch (JSONException e) {
                // 获取token失败
                log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
                //解析失败原因
                throw new BizException(jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }else {
            log.error("获取token返回为空， requestUrl = " + requestUrl);
            //请求token失败
            throw new BizException(EnumsUtils.APPLY_TOKEN_FAILED);
        }
        return accessToken;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            String requestUrl = access_token_url.replace("{corpId}", "**************").replace("{corpsecret}", "**********************");
            JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
            System.out.println(jsonObject);
        }

    }

}