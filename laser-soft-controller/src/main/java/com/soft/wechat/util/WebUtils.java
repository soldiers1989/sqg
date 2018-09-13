package com.soft.wechat.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.alibaba.fastjson.JSON;

/**
 * 网络工具类。
 * 
 */
public abstract class WebUtils {

    public static final Integer CONNECT_TIMEOUT = 5000;

    public static final Integer READ_TIMEOUT = 5000;

    private static final String DEFAULT_CHARSET = "UTF-8";

    private static final String METHOD_POST = "POST";

    private static final String METHOD_GET = "GET";

    private static final String METHOD_DELETE = "DELETE";

    public static final String ACCEPT_JSON = "application/json";

    private static class DefaultTrustManager implements X509TrustManager {

        @Override
        public X509Certificate[] getAcceptedIssuers() {

            return null;
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }
    }

    private WebUtils() {
    }

    /**
     * 执行HTTP POST请求。
     * 
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static String doPost(String url, Map<String, Object> params) throws IOException {

        return doPost(url, params, DEFAULT_CHARSET, CONNECT_TIMEOUT, READ_TIMEOUT, null, null);
    }

    /**
     * 执行HTTP POST请求。
     * 
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static String doPost(String url, Map<String, Object> params, String user, String pwd) throws IOException {

        return doPost(url, params, DEFAULT_CHARSET, CONNECT_TIMEOUT, READ_TIMEOUT, user, pwd);
    }

    /**
     * 执行HTTP POST请求。
     * 
     * @param url
     * @param params
     * @param connectTimeout
     * @param readTimeout
     * @return
     * @throws IOException
     */
    public static String doPost(String url, Map<String, Object> params, int connectTimeout, int readTimeout, String user, String pwd)
                    throws IOException {

        return doPost(url, params, DEFAULT_CHARSET, connectTimeout, readTimeout, user, pwd);
    }

    public static String doPost(String url, Map<String, Object> params, int connectTimeout, int readTimeout) throws IOException {

        return doPost(url, params, DEFAULT_CHARSET, connectTimeout, readTimeout, null, null);
    }

    public static String doJsonPost(String url, Map<String, Object> params, int connectTimeout, int readTimeout) throws IOException {

        return doPost(url, params, DEFAULT_CHARSET, ACCEPT_JSON, connectTimeout, readTimeout);
    }

    /**
     * 执行HTTP POST请求。直接将map转换成json
     * 
     * @param url
     * @param params
     * @param connectTimeout
     * @param readTimeout
     * @return
     * @throws IOException
     */
    public static String doPostByJson(String url, Map<String, Object> params, int connectTimeout, int readTimeout) throws IOException {

        String ctype = "application/json;charset=" + DEFAULT_CHARSET;
        byte[] content = JSON.toJSONString(params).getBytes(DEFAULT_CHARSET);
        return doPost(url, ACCEPT_JSON, ctype, content, connectTimeout, readTimeout);
    }

    public static <T> T postForObject(String url, String json, Class<T> clazz, int connectTimeout, int readTimeout) throws IOException {

        if (StringUtils.hasBlank(url, json)) {
            return null;
        }
        String ctype = "application/x-www-form-urlencoded;charset=" + DEFAULT_CHARSET;
        byte[] content = json.getBytes(DEFAULT_CHARSET);
        String resultStr = doPost(url, ACCEPT_JSON, ctype, content, connectTimeout, readTimeout);
        return JSON.parseObject(resultStr, clazz);
    }

    /**
     * 执行HTTP DELETE请求。
     * 
     * @param url
     *            请求地址
     * @param params
     *            请求参数
     * @return 响应字符串
     * @throws IOException
     */
    public static String doDelete(String url, int connectTimeout, int readTimeout) throws IOException {

        return doDelete(url, DEFAULT_CHARSET, connectTimeout, readTimeout, null, null);
    }

    /**
     * 执行HTTP POST请求。
     * 
     * @param url
     *            请求地址
     * @param params
     *            请求参数
     * @param charset
     *            字符集，如UTF-8, GBK, GB2312
     * @return 响应字符串
     * @throws IOException
     */
    public static String doPost(String url, Map<String, Object> params, String charset, int connectTimeout, int readTimeout, String user,
                    String pwd) throws IOException {

        String ctype = "application/x-www-form-urlencoded;charset=" + charset;
        String query = buildQuery(params, charset);
        byte[] content = {};
        if (query != null) {
            content = query.getBytes(charset);
        }
        return doPost(url, ctype, content, connectTimeout, readTimeout, user, pwd);
    }

    public static String doPost(String url, Map<String, Object> params, String charset, String accept, int connectTimeout, int readTimeout)
                    throws IOException {

        String ctype = "application/x-www-form-urlencoded;charset=" + charset;
        String query = buildQuery(params, charset);
        byte[] content = {};
        if (query != null) {
            content = query.getBytes(charset);
        }
        return doPost(url, accept, ctype, content, connectTimeout, readTimeout);
    }

    /**
     * 执行HTTP DELETE请求。
     * 
     * @param url
     *            请求地址
     * @param params
     *            请求参数
     * @param charset
     *            字符集，如UTF-8, GBK, GB2312
     * @return 响应字符串
     * @throws IOException
     */
    public static String doDelete(String url, String charset, int connectTimeout, int readTimeout, String user, String pwd)
                    throws IOException {

        String ctype = "application/x-www-form-urlencoded;charset=" + charset;

        HttpURLConnection conn = null;
        String rsp = null;
        try {
            try {
                conn = getConnection(new URL(url), METHOD_DELETE, ctype, user, pwd);
                conn.setConnectTimeout(connectTimeout);
                conn.setReadTimeout(readTimeout);
            } catch (IOException e) {
                throw e;
            }
            try {
                rsp = getResponseAsString(conn);
            } catch (IOException e) {
                throw e;
            }
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return rsp;
    }

    /**
     * 执行HTTP POST请求。
     * 
     * @param url
     *            请求地址
     * @param ctype
     *            请求类型
     * @param content
     *            请求字节数组
     * @return 响应字符串
     * @throws IOException
     */
    public static String doPost(String url, String ctype, byte[] content, int connectTimeout, int readTimeout, String user, String pwd)
                    throws IOException {

        HttpURLConnection conn = null;
        OutputStream out = null;
        String rsp = null;
        try {
            try {
                conn = getConnection(new URL(url), METHOD_POST, ctype, user, pwd);
                conn.setConnectTimeout(connectTimeout);
                conn.setReadTimeout(readTimeout);
            } catch (IOException e) {
                throw e;
            }
            try {
                out = conn.getOutputStream();
                out.write(content);
                rsp = getResponseAsString(conn);
            } catch (IOException e) {
                throw e;
            }

        } finally {
            if (out != null) {
                out.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }

        return rsp;
    }

    public static String doPost(String url, String accept, String ctype, byte[] content, int connectTimeout, int readTimeout)
                    throws IOException {

        HttpURLConnection conn = null;
        OutputStream out = null;
        String rsp = null;
        try {
            try {
                conn = getConnection(new URL(url), METHOD_POST, accept, ctype);
                conn.setConnectTimeout(connectTimeout);
                conn.setReadTimeout(readTimeout);
            } catch (IOException e) {
                throw e;
            }
            try {
                out = conn.getOutputStream();
                out.write(content);
                rsp = getResponseAsString(conn);
            } catch (IOException e) {
                throw e;
            }

        } finally {
            if (out != null) {
                out.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }

        return rsp;
    }

    public static String doPostByThreadLocal(String url, Map<String, Object> params, int connectTimeout, int readTimeout,
                    boolean isThreadLocal) throws IOException {

        return doPostByThreadLocal(url, params, DEFAULT_CHARSET, connectTimeout, readTimeout, isThreadLocal, null, null);
    }

    public static String doPostByThreadLocal(String url, Map<String, Object> params, String charset, int connectTimeout, int readTimeout,
                    boolean isThreadLocal, String user, String pwd) throws IOException {

        String ctype = "application/x-www-form-urlencoded;charset=" + charset;
        String query = buildQuery(params, charset);
        byte[] content = {};
        if (query != null) {
            content = query.getBytes(charset);
        }
        return doPostByThreadLocal(url, ctype, content, connectTimeout, readTimeout, isThreadLocal, user, pwd);
    }

    public static String doPostByThreadLocal(String url, String ctype, byte[] content, int connectTimeout, int readTimeout,
                    boolean isThreadLocal, String user, String pwd) throws IOException {

        HttpURLConnection conn = null;
        OutputStream out = null;
        String rsp = null;
        try {
            try {
                conn = getConnection(new URL(url), METHOD_POST, ctype, user, pwd);
                conn.setConnectTimeout(connectTimeout);
                conn.setReadTimeout(readTimeout);
            } catch (IOException e) {
                throw e;
            }
            try {
                out = conn.getOutputStream();
                out.write(content);
                rsp = getResponseAsString(conn);
            } catch (IOException e) {
                throw e;
            }

        } finally {
            if (out != null) {
                out.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }

        return rsp;
    }

    /**
     * 执行带文件上传的HTTP POST请求。
     * 
     * @param url
     *            请求地址
     * @param textParams
     *            文本请求参数
     * @param fileParams
     *            文件请求参数
     * @return 响应字符串
     * @throws IOException
     */
    public static String doPost(String url, Map<String, Object> params, Map<String, FileItem> fileParams, int connectTimeout,
                    int readTimeout) throws IOException {

        if (fileParams == null || fileParams.isEmpty()) {
            return doPost(url, params, DEFAULT_CHARSET, connectTimeout, readTimeout, null, null);
        } else {
            return doPost(url, params, fileParams, DEFAULT_CHARSET, connectTimeout, readTimeout, null, null);
        }
    }

    /**
     * 执行带文件上传的HTTP POST请求。
     * 
     * @param url
     *            请求地址
     * @param textParams
     *            文本请求参数
     * @param fileParams
     *            文件请求参数
     * @param charset
     *            字符集，如UTF-8, GBK, GB2312
     * @return 响应字符串
     * @throws IOException
     */
    public static String doPost(String url, Map<String, Object> params, Map<String, FileItem> fileParams, String charset,
                    int connectTimeout, int readTimeout, String user, String pwd) throws IOException {

        if (StringUtils.isBlank(charset)) {
            charset = DEFAULT_CHARSET;
        }
        if (fileParams == null || fileParams.isEmpty()) {
            return doPost(url, params, charset, connectTimeout, readTimeout, user, pwd);
        }

        String boundary = System.currentTimeMillis() + ""; // 随机分隔线
        HttpURLConnection conn = null;
        OutputStream out = null;
        String rsp = null;
        try {
            try {
                String ctype = "multipart/form-data;boundary=" + boundary + ";charset=" + charset;
                conn = getConnection(new URL(url), METHOD_POST, ctype, user, pwd);
                conn.setConnectTimeout(connectTimeout);
                conn.setReadTimeout(readTimeout);
            } catch (IOException e) {
                throw e;
            }

            try {
                out = conn.getOutputStream();

                byte[] entryBoundaryBytes = ("\r\n--" + boundary + "\r\n").getBytes(charset);

                // 组装文本请求参数
                Set<Entry<String, Object>> textEntrySet = params.entrySet();
                for (Entry<String, Object> textEntry : textEntrySet) {
                    byte[] textBytes = getTextEntry(textEntry.getKey(), textEntry.getValue().toString(), charset);
                    out.write(entryBoundaryBytes);
                    out.write(textBytes);
                }

                // 组装文件请求参数
                Set<Entry<String, FileItem>> fileEntrySet = fileParams.entrySet();
                for (Entry<String, FileItem> fileEntry : fileEntrySet) {
                    FileItem fileItem = fileEntry.getValue();
                    byte[] fileBytes = getFileEntry(fileEntry.getKey(), fileItem.getFileName(), fileItem.getMimeType(), charset);
                    out.write(entryBoundaryBytes);
                    out.write(fileBytes);
                    out.write(fileItem.getContent());
                }

                // 添加请求结束标志
                byte[] endBoundaryBytes = ("\r\n--" + boundary + "--\r\n").getBytes(charset);
                out.write(endBoundaryBytes);

                rsp = getResponseAsString(conn);
            } catch (IOException e) {
                throw e;
            }

        } finally {
            if (out != null) {
                out.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }

        return rsp;
    }

    private static byte[] getTextEntry(String fieldName, String fieldValue, String charset) throws IOException {

        StringBuilder entry = new StringBuilder();
        entry.append("Content-Disposition:form-data;name=\"");
        entry.append(fieldName);
        entry.append("\"\r\nContent-Type:text/plain\r\n\r\n");
        entry.append(fieldValue);
        return entry.toString().getBytes(charset);
    }

    private static byte[] getFileEntry(String fieldName, String fileName, String mimeType, String charset) throws IOException {

        StringBuilder entry = new StringBuilder();
        entry.append("Content-Disposition:form-data;name=\"");
        entry.append(fieldName);
        entry.append("\";filename=\"");
        entry.append(fileName);
        entry.append("\"\r\nContent-Type:");
        entry.append(mimeType);
        entry.append("\r\n\r\n");
        return entry.toString().getBytes(charset);
    }

    /**
     * 执行HTTP GET请求。
     * 
     * @param url
     *            请求地址
     * @param params
     *            请求参数
     * @return 响应字符串
     * @throws IOException
     */
    public static String doGet(String url, Map<String, Object> params) throws IOException {

        return doGet(url, params, DEFAULT_CHARSET, null, null);
    }

    /**
     * 执行HTTP GET请求。
     * 
     * @param url
     *            请求地址
     * @param params
     *            请求参数
     * @param charset
     *            字符集，如UTF-8, GBK, GB2312
     * @return 响应字符串
     * @throws IOException
     */
    public static String doGet(String url, Map<String, Object> params, String charset, String user, String pwd) throws IOException {

        HttpURLConnection conn = null;
        String rsp = null;

        try {
            String ctype = "application/x-www-form-urlencoded;charset=" + charset;
            String query = buildQuery(params, charset);
            try {
                conn = getConnection(buildGetUrl(url, query), METHOD_GET, ctype, user, pwd);
            } catch (IOException e) {
                throw e;
            }

            try {
                rsp = getResponseAsString(conn);
            } catch (IOException e) {
                throw e;
            }

        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return rsp;
    }

    /**
     * 执行HTTP GET请求。
     * 
     * @param url
     *            请求地址
     * @param params
     *            请求参数
     * @param charset
     *            字符集，如UTF-8, GBK, GB2312
     * @return 响应字符串
     * @throws IOException
     */
    public static String doGet(String url, Map<String, Object> params, String charset) throws IOException {

        return doGet(url, params, charset, null, null);
    }

    private static HttpURLConnection getConnection(URL url, String method, String ctype, String user, String pwd) throws IOException {

        HttpURLConnection conn = null;
        if ("https".equals(url.getProtocol())) {
            SSLContext ctx = null;
            try {
                ctx = SSLContext.getInstance("TLS");
                ctx.init(new KeyManager[0], new TrustManager[] { new DefaultTrustManager() }, new SecureRandom());
            } catch (Exception e) {
                throw new IOException(e);
            }
            HttpsURLConnection connHttps = (HttpsURLConnection) url.openConnection();
            connHttps.setSSLSocketFactory(ctx.getSocketFactory());
            connHttps.setHostnameVerifier(new HostnameVerifier() {

                @Override
                public boolean verify(String hostname, SSLSession session) {

                    return false;// 默认认证不通过，进行证书校验。
                }
            });
            conn = connHttps;
        } else {
            conn = (HttpURLConnection) url.openConnection();
        }
        if (StringUtils.isNotBlank(user) && StringUtils.isNotBlank(pwd)) {
            // 账号密码
            String auth = user + ":" + pwd;
            byte[] authEncBytes = org.apache.commons.codec.binary.Base64.encodeBase64(auth.getBytes());
            String res = new String(authEncBytes);
            //设置认证属性
            conn.setRequestProperty("Authorization", "Basic " + res);
        }
        conn.setRequestMethod(method);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        //conn.setRequestProperty("Accept", "text/xml,text/javascript,text/html");
        //		conn.setRequestProperty("User-Agent", "aop-sdk-java");
        conn.setRequestProperty("Content-Type", ctype);
        return conn;
    }

    private static HttpURLConnection getConnection(URL url, String method, String accept, String ctype) throws IOException {

        HttpURLConnection conn = null;
        if ("https".equals(url.getProtocol())) {
            SSLContext ctx = null;
            try {
                ctx = SSLContext.getInstance("TLS");
                ctx.init(new KeyManager[0], new TrustManager[] { new DefaultTrustManager() }, new SecureRandom());
            } catch (Exception e) {
                throw new IOException(e);
            }
            HttpsURLConnection connHttps = (HttpsURLConnection) url.openConnection();
            connHttps.connect();

            connHttps.setSSLSocketFactory(ctx.getSocketFactory());
            connHttps.setHostnameVerifier(new HostnameVerifier() {

                @Override
                public boolean verify(String hostname, SSLSession session) {

                    return false;// 默认认证不通过，进行证书校验。
                }
            });
            conn = connHttps;
        } else {
            conn = (HttpURLConnection) url.openConnection();
        }

        conn.setRequestMethod(method);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestProperty("Accept", accept);
        conn.setRequestProperty("Content-Type", ctype);
        return conn;
    }

    private static URL buildGetUrl(String strUrl, String query) throws IOException {

        URL url = new URL(strUrl);
        if (StringUtils.isEmpty(query)) {
            return url;
        }

        if (StringUtils.isEmpty(url.getQuery())) {
            if (strUrl.endsWith("?")) {
                strUrl = strUrl + query;
            } else {
                strUrl = strUrl + "?" + query;
            }
        } else {
            if (strUrl.endsWith("&")) {
                strUrl = strUrl + query;
            } else {
                strUrl = strUrl + "&" + query;
            }
        }

        return new URL(strUrl);
    }

    public static String buildQuery(Map<String, Object> params, String charset) throws IOException {

        if (params == null || params.isEmpty()) {
            return null;
        }

        StringBuilder query = new StringBuilder();
        Set<Entry<String, Object>> entries = params.entrySet();
        boolean hasParam = false;

        for (Entry<String, Object> entry : entries) {
            String name = entry.getKey();
            String value = entry.getValue().toString();
            // 忽略参数名或参数值为空的参数
            if (StringUtils.areNotEmpty(name, value)) {
                if (hasParam) {
                    query.append("&");
                } else {
                    hasParam = true;
                }

                query.append(name).append("=").append(URLEncoder.encode(value, charset));
            }
        }

        return query.toString();
    }

    protected static String getResponseAsString(HttpURLConnection conn) throws IOException {

        String charset = getResponseCharset(conn.getContentType());
        InputStream es = conn.getErrorStream();
        if (es == null) {
            return getStreamAsString(conn.getInputStream(), charset);
        } else {
            String msg = getStreamAsString(es, charset);
            if (StringUtils.isEmpty(msg)) {
                throw new IOException(conn.getResponseCode() + ":" + conn.getResponseMessage());
            } else {
                throw new IOException(msg);
            }
        }
    }

    private static String getStreamAsString(InputStream stream, String charset) throws IOException {

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset));
            StringWriter writer = new StringWriter();

            char[] chars = new char[256];
            int count = 0;
            while ((count = reader.read(chars)) > 0) {
                writer.write(chars, 0, count);
            }

            return writer.toString();
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }

    private static String getResponseCharset(String ctype) {

        String charset = DEFAULT_CHARSET;

        if (!StringUtils.isEmpty(ctype)) {
            String[] params = ctype.split(";");
            for (String param : params) {
                param = param.trim();
                if (param.startsWith("charset")) {
                    String[] pair = param.split("=", 2);
                    if (pair.length == 2) {
                        if (!StringUtils.isEmpty(pair[1])) {
                            charset = pair[1].trim();
                        }
                    }
                    break;
                }
            }
        }

        return charset;
    }

    /**
     * 使用默认的UTF-8字符集反编码请求参数值。
     * 
     * @param value
     *            参数值
     * @return 反编码后的参数值
     */
    public static String decode(String value) {

        return decode(value, DEFAULT_CHARSET);
    }

    /**
     * 使用默认的UTF-8字符集编码请求参数值。
     * 
     * @param value
     *            参数值
     * @return 编码后的参数值
     */
    public static String encode(String value) {

        return encode(value, DEFAULT_CHARSET);
    }

    /**
     * 使用指定的字符集反编码请求参数值。
     * 
     * @param value
     *            参数值
     * @param charset
     *            字符集
     * @return 反编码后的参数值
     */
    public static String decode(String value, String charset) {

        String result = null;
        if (!StringUtils.isEmpty(value)) {
            try {
                result = URLDecoder.decode(value, charset);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    /**
     * 使用指定的字符集编码请求参数值。
     * 
     * @param value
     *            参数值
     * @param charset
     *            字符集
     * @return 编码后的参数值
     */
    public static String encode(String value, String charset) {

        String result = null;
        if (!StringUtils.isEmpty(value)) {
            try {
                result = URLEncoder.encode(value, charset);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    public static Map<String, String> getParamsFromUrl(String url) {

        Map<String, String> map = null;
        if (url != null && url.indexOf('?') != -1) {
            map = splitUrlQuery(url.substring(url.indexOf('?') + 1));
        }
        if (map == null) {
            map = new HashMap<String, String>();
        }
        return map;
    }

    /**
     * 从URL中提取所有的参数。
     * 
     * @param query
     *            URL地址
     * @return 参数映射
     */
    public static Map<String, String> splitUrlQuery(String query) {

        Map<String, String> result = new HashMap<String, String>();

        String[] pairs = query.split("&");
        if (pairs != null && pairs.length > 0) {
            for (String pair : pairs) {
                String[] param = pair.split("=", 2);
                if (param != null && param.length == 2) {
                    result.put(param[0], param[1]);
                }
            }
        }

        return result;
    }

}
