package util.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sf.cglib.core.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.*;
import java.net.*;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Objects;

/**
 * http工具类
 *
 * @author inspur_ict
 */
public class HttpUtils {
    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);

    private static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * 发送Get请求
     *
     * @param url
     * @param header
     * @param params
     * @return
     */
    public static String doGet(String url, Map<String, Object> header, Map<String, Object> params) {
        return doGet(url, header, params, null, null);
    }

    /**
     * 发送Get请求
     *
     * @param url
     * @param header
     * @param params
     * @param connectTimeout 单位毫秒
     * @param readTimeout 单位毫秒
     * @return
     */
    public static String doGet(String url, Map<String, Object> header, Map<String, Object> params, Integer connectTimeout, Integer readTimeout) {
        if (url.startsWith("http:")) {
            return doGetHttp(url, header, params, connectTimeout, readTimeout);
        } else if (url.startsWith("https:")) {
            return doGetHttps(url, header, params, connectTimeout, readTimeout);
        } else {
            log.info("url不合法：" + url);
            return "";
        }
    }

    /**
     * 发送get请求(http)
     *
     * @param url
     * @param header
     * @param params
     * @param connectTimeout 单位毫秒
     * @param readTimeout 单位毫秒
     * @return
     */
    public static String doGetHttp(String url, Map<String, Object> header, Map<String, Object> params, Integer connectTimeout, Integer readTimeout) {
        StringBuilder sb = new StringBuilder(url);
        try{
            url = buildRealUrlWithParams(url,params);
        } catch (UnsupportedEncodingException e) {
            log.error("调用HttpUtils.doGetHttp UnsupportedEncodingException, url=" + url, e);
        }
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            log.info("doGetHttp - {}", url);
            URL realUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            if (!Objects.isNull(connectTimeout)) {
                conn.setConnectTimeout(connectTimeout);
            }
            if (!Objects.isNull(readTimeout)) {
                conn.setReadTimeout(readTimeout);
            }
            conn.setRequestMethod("GET");
            if (header == null || !header.containsKey("Accept")) {
                conn.setRequestProperty("Accept", "*/*");
            }
            conn.setRequestProperty("connection", "Keep-Alive");
            setHeader(conn,header);
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            log.info("recv - {}", result);

        } catch (ConnectException e) {
            log.error("调用HttpUtils.doGetHttp ConnectException, url=" + url, e);
        } catch (SocketTimeoutException e) {
            log.error("调用HttpUtils.doGetHttp SocketTimeoutException, url=" + url, e);
        } catch (IOException e) {
            log.error("调用HttpUtils.doGetHttp IOException, url=" + url, e);
        } catch (Exception e) {
            log.error("调用HttpUtils.doGetHttp Exception, url=" + url, e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                log.error("调用in.close Exception, url=" + url, e);
            }
        }
        return result.toString();
    }

    /**
     * 发送get请求(https)
     *
     * @param url
     * @param header
     * @param params
     * @param connectTimeout 单位毫秒
     * @param readTimeout 单位毫秒
     * @return
     */
    public static String doGetHttps(String url, Map<String, Object> header, Map<String, Object> params, Integer connectTimeout, Integer readTimeout) {
        try{
            url = buildRealUrlWithParams(url,params);
        } catch (UnsupportedEncodingException e) {
            log.error("调用HttpUtils.doGetHttps UnsupportedEncodingException, url=" + url, e);
        }
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            log.info("doGetHttps - {}", url);
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());
            URL console = new URL(url);
            HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
            conn.setRequestMethod("GET");
            if (!Objects.isNull(connectTimeout)) {
                conn.setConnectTimeout(connectTimeout);
            }
            if (!Objects.isNull(readTimeout)) {
                conn.setReadTimeout(readTimeout);
            }
            if (header == null || !header.containsKey("Accept")) {
                conn.setRequestProperty("Accept", "*/*");
            }
            conn.setRequestProperty("connection", "Keep-Alive");
            setHeader(conn,header);
            conn.setSSLSocketFactory(sc.getSocketFactory());
            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            log.info("recv - {}", result);

        } catch (ConnectException e) {
            log.error("调用HttpUtils.doGetHttps ConnectException, url=" + url, e);
        } catch (SocketTimeoutException e) {
            log.error("调用HttpUtils.doGetHttps SocketTimeoutException, url=" + url, e);
        } catch (IOException e) {
            log.error("调用HttpUtils.doGetHttps IOException, url=" + url, e);
        } catch (Exception e) {
            log.error("调用HttpUtils.doGetHttps Exception, url=" + url, e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                log.error("调用in.close Exception, url=" + url, e);
            }
        }
        return result.toString();
    }

    /**
     * 发送post请求
     *
     * @param url
     * @param header
     * @param params
     * @param body
     * @return
     */
    public static String doPost(String url, Map<String, Object> header, Map<String, Object> params, Object body) {
        return doPost(url, header, params, body, null, null);
    }

    /**
     * 发送post请求
     *
     * @param url
     * @param header
     * @param params
     * @param body
     * @param connectTimeout 单位毫秒
     * @param readTimeout 单位毫秒
     * @return
     */
    public static String doPost(String url, Map<String, Object> header, Map<String, Object> params, Object body, Integer connectTimeout, Integer readTimeout) {
        if (url.startsWith("http:")) {
            return doPostHttp(url, header, params, body, connectTimeout, readTimeout);
        } else if (url.startsWith("https:")) {
            return doPostHttps(url, header, params, body, connectTimeout, readTimeout);
        } else {
            log.info("url不合法：" + url);
            return "";
        }
    }

    /**
     * 发送post请求(http)
     *
     * @param url
     * @param header
     * @param params
     * @param body
     * @param connectTimeout 单位毫秒
     * @param readTimeout 单位毫秒
     * @return
     */
    public static String doPostHttp(String url, Map<String, Object> header, Map<String, Object> params, Object body, Integer connectTimeout, Integer readTimeout) {
        try{
            url = buildRealUrlWithParams(url,params);
        } catch (UnsupportedEncodingException e) {
            log.error("调用HttpUtils.doPostHttp UnsupportedEncodingException, url=" + url, e);
        }
        OutputStreamWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            log.info("doPost - {},body - {}", url, body == null ? "" : objectToJson(body));
            URL realUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            conn.setRequestMethod("POST");
            if (!Objects.isNull(connectTimeout)) {
                conn.setConnectTimeout(connectTimeout);
            }
            if (!Objects.isNull(readTimeout)) {
                conn.setReadTimeout(readTimeout);
            }
            //发送POST请求必须设置为true
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            setHeader(conn,header);
            // 获取输出流
            out = new OutputStreamWriter(conn.getOutputStream());
            // 向post请求中写入body
            out.write(objectToJson(body));
            out.flush();
            if (200 == conn.getResponseCode()) {
                in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                String line;
                while ((line = in.readLine()) != null) {
                    result.append(line);
                }
                log.info("recv - {}", result);
            } else {
                log.error("recv - {}", "调用失败");
                log.error("recv - {}", conn.getResponseCode());
                log.error("recv - {}", conn.getResponseMessage());
            }
        } catch (MalformedURLException e) {
            log.error("调用HttpUtils.doPost ConnectException, url=" + url, e);
        } catch (IOException e) {
            log.error("调用HttpUtils.doPost IOException, url=" + url, e);
        } catch (Exception e) {
            log.error("调用HttpUtils.doPost Exception, url=" + url, e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                log.error("调用dopost close stream Exception, url=" + url, e);
            }
        }
        return result.toString();
    }

    /**
     * 发送post请求(https)
     *
     * @param url
     * @param header
     * @param params
     * @param body
     * @param connectTimeout 单位毫秒
     * @param readTimeout 单位毫秒
     * @return
     */
    public static String doPostHttps(String url, Map<String, Object> header, Map<String, Object> params, Object body, Integer connectTimeout, Integer readTimeout) {
        try{
            url = buildRealUrlWithParams(url,params);
        } catch (UnsupportedEncodingException e) {
            log.error("调用HttpUtils.doPostHttps UnsupportedEncodingException, url=" + url, e);
        }
        OutputStreamWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            log.info("doPost - {},body - {}", url, body == null ? "" : objectToJson(body));
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());
            URL console = new URL(url);
            HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
            conn.setRequestMethod("POST");
            if (!Objects.isNull(connectTimeout)) {
                conn.setConnectTimeout(connectTimeout);
            }
            if (!Objects.isNull(readTimeout)) {
                conn.setReadTimeout(readTimeout);
            }
            //发送POST请求必须设置为true
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            setHeader(conn,header);

            conn.setSSLSocketFactory(sc.getSocketFactory());
            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            // 获取输出流
            out = new OutputStreamWriter(conn.getOutputStream());
            // 向post请求中写入body
            out.write(objectToJson(body));
            out.flush();
            if (200 == conn.getResponseCode()) {
                in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                String line;
                while ((line = in.readLine()) != null) {
                    result.append(line);
                }
                log.info("recv - {}", result);
            } else {
                log.error("recv - {}", "调用失败");
                log.error("recv - {}", conn.getResponseCode());
                log.error("recv - {}", conn.getResponseMessage());
            }
        } catch (MalformedURLException e) {
            log.error("调用HttpUtils.doPost ConnectException, url=" + url, e);
        } catch (IOException e) {
            log.error("调用HttpUtils.doPost IOException, url=" + url, e);
        } catch (Exception e) {
            log.error("调用HttpUtils.doPost Exception, url=" + url, e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                log.error("调用dopost close stream Exception, url=" + url, e);
            }
        }
        return result.toString();
    }

    private static class TrustAnyTrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    }

    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    /**
     * 将requestParams拼接到url上
     * @param url
     * @param params
     * @return
     */
    private static String buildRealUrlWithParams(String url,Map<String,Object> params) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder(url);
        if (params != null && !params.isEmpty()) {
            sb.append("?");
            for (Map.Entry<String,Object> entry : params.entrySet()) {
                // 将requestParam中使用的参数和值进行url编码
                sb.append(URLEncoder.encode(entry.getKey(),DEFAULT_CHARSET)).append("=").append(URLEncoder.encode(entry.getValue().toString(),DEFAULT_CHARSET)).append("&");
            }
            url = sb.substring(0, sb.length() - 1);
        }
        return url;
    }

    /**
     * 向connection中填充header信息
     * @param connection
     * @param headers
     */
    private static void setHeader(HttpURLConnection connection,Map<String,Object> headers){
        if(connection==null || headers==null || headers.isEmpty()){
            return;
        }
        for (Map.Entry entry : headers.entrySet()) {
            // 添加自定义的header
            connection.setRequestProperty(entry.getKey().toString(), entry.getValue().toString());
        }
    }

    /**
     * url转码
     * + 转换成 %2B
     * 空格 转换成 %20
     * # 转换成%23
     *
     * @param url
     * @return
     */
    private static String urlEncode(String url) {
//        url = url.replaceAll("\\+", "%2B");
//        url = url.replaceAll(" ", "%20");
//        url = url.replaceAll("#", "%23");
        return url;
    }

    public static void main(String[] args) {
        String url = "http://10.110.2.83:8082/index/api/getMediaList?secret= 035c73f7-bb6b-4889-a715-d9eb2d1925cc&dd=中 文";
        System.out.println(url);
//        try {
            System.out.println(url);
            String result = doGet(url,null,null);
            System.out.println(result);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 将对象转成json字符串
     * @param obj
     * @return
     */
    private static String objectToJson(Object obj){
        if(obj instanceof String
                || obj instanceof Number){
            return (String)obj;
        }
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(obj);
    }
}
