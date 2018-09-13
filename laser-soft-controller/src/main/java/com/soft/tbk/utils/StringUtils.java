package com.soft.tbk.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类。
 * 
 * @author carver.gu
 * @since 1.0, Sep 12, 2009
 */
public abstract class StringUtils {

    /**
     * 手机号码
     * 移动：134[0-8],135,136,137,138,139,147,150,151,157,158,159,182,183,187,188,
     * 联通：130,131,132,152,155,156,185,186
     * 电信：133,1349,153,180,189
     */
    private static final String MOBILE_REG_EXP_STRING = "^0?1[3|4|5|8][0-9]\\d{8}$";

    public static final String EMPTY = "";

    public static final int INDEX_NOT_FOUND = -1;

    private static final Pattern PATTERN = Pattern.compile("\\s*|\t|\r|\n");

    private StringUtils() {
    }

    /**
     * 检查指定的字符串是否为空。
     * <ul>
     * <li>SysUtils.isEmpty(null) = true</li>
     * <li>SysUtils.isEmpty("") = true</li>
     * <li>SysUtils.isEmpty(" ") = false</li>
     * <li>SysUtils.isEmpty("abc") = false</li>
     * </ul>
     * 
     * @param value
     *            待检查的字符串
     * @return true/false
     */
    public static boolean isEmpty(String value) {

        return (value == null || (value.length()) == 0);
    }

    public static boolean isBlank(String value) {

        if (isEmpty(value)) {
            return true;
        }
        for (int i = 0; i < value.length(); i++) {
            if (!Character.isWhitespace(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否全部为空
     * 
     * @param values
     * @return
     */
    public static boolean isBlankLoop(String...values) {

        return Arrays.stream(values).allMatch(v -> isBlank(v));
        /*for (String value: values) {
        	if (isNotBlank(value)) {
        	    return false;
        	}
        }
        return true;*/
    }

    /**
     * 判断传入的字符串数组是否包含空字符串
     * 
     * @see #isBlank(String)
     * @param values
     * @return
     */
    public static boolean hasBlank(String...values) {

        if (values.length == 0) {
            return true;
        }
        for (String value : values) {
            if (isBlank(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @see org.apache.commons.lang3.StringUtils#stripToEmpty(String)
     * @param str
     * @return
     */
    public static String stripToEmpty(String str) {

        return str == null ? EMPTY : strip(str, null);
    }

    /**
     * @see org.apache.commons.lang3.StringUtils#strip(String, String)
     * @param str
     * @param stripChars
     * @return
     */
    private static String strip(String str, String stripChars) {

        if (isEmpty(str)) {
            return str;
        }
        str = stripStart(str, stripChars);
        return stripEnd(str, stripChars);
    }

    /**
     * copy from
     * 
     * @see org.apache.commons.lang3.StringUtils#stripEnd(String, String)
     * @param str
     * @param stripChars
     * @return
     */
    private static String stripEnd(String str, String stripChars) {

        int end;
        if (str == null || (end = str.length()) == 0) {
            return str;
        }

        if (stripChars == null) {
            while (end != 0 && Character.isWhitespace(str.charAt(end - 1))) {
                end--;
            }
        } else if (stripChars.length() == 0) {
            return str;
        } else {
            while (end != 0 && stripChars.indexOf(str.charAt(end - 1)) != INDEX_NOT_FOUND) {
                end--;
            }
        }
        return str.substring(0, end);
    }

    /**
     * @see org.apache.commons.lang3.StringUtils#stripStart(String, String)
     * @param str
     * @param stripChars
     * @return
     */
    private static String stripStart(String str, String stripChars) {

        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }
        int start = 0;
        if (stripChars == null) {
            while (start != strLen && Character.isWhitespace(str.charAt(start))) {
                start++;
            }
        } else if (stripChars.length() == 0) {
            return str;
        } else {
            while (start != strLen && stripChars.indexOf(str.charAt(start)) != INDEX_NOT_FOUND) {
                start++;
            }
        }
        return str.substring(start);
    }

    public static boolean isNotBlank(String value) {

        return !isBlank(value);
    }

    /**
     * @see #isBlankLoop(String...)
     * @param values
     * @return
     */
    public static boolean isNotBlankLoop(String...values) {

        return !isBlankLoop(values);
    }

    /**
     * 检查对象是否为数字型字符串,包含负数开头的。
     */
    public static boolean isNumeric(Object obj) {

        if (obj == null) {
            return false;
        }
        char[] chars = obj.toString().toCharArray();
        int length = chars.length;
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (length > 1 && chars[0] == '-') {
            i = 1;
        }
        for (; i < length; i++) {
            if (!Character.isDigit(chars[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查指定的字符串列表是否不为空。
     */
    public static boolean areNotEmpty(String...values) {

        boolean result = true;
        if (values == null || values.length == 0) {
            result = false;
        } else {
            for (String value : values) {
                result &= isNotEmpty(value);
            }
        }
        return result;
    }

    /**
     * 把通用字符编码的字符串转化为汉字编码。
     */
    public static String unicodeToChinese(String unicode) {

        StringBuilder out = new StringBuilder();
        if (!isEmpty(unicode)) {
            for (int i = 0; i < unicode.length(); i++) {
                out.append(unicode.charAt(i));
            }
        }
        return out.toString();
    }

    /**
     * 过滤不可见字符
     */
    public static String stripNonValidXMLCharacters(String input) {

        if (input == null || ("".equals(input))) {
            return "";
        }
        StringBuilder out = new StringBuilder();
        char current;
        for (int i = 0; i < input.length(); i++) {
            current = input.charAt(i);
            if ((current == 0x9) || (current == 0xA) || (current == 0xD) || ((current >= 0x20) && (current <= 0xD7FF))
                            || ((current >= 0xE000) && (current <= 0xFFFD)) || ((current >= 0x10000) && (current <= 0x10FFFF))) {
                out.append(current);
            }
        }
        return out.toString();
    }

    public static String getFirstUpper(String str) {

        String newStr = "";
        if (str.length() > 0) {
            newStr = str.substring(0, 1).toUpperCase() + str.substring(1, str.length());
        }
        return newStr;
    }

    /**
     * 当前值为空则取默认值返回
     * 
     * @param value
     * @param defaultVal
     * @return
     */
    public static String defaultIfBlank(String value, String defaultVal) {

        return StringUtils.isNotBlank(value) ? value : defaultVal;
    }

    /**
     * 替换指定位置的字符
     * 
     * @param index
     *            位置
     * @param ostr
     *            源字符串
     * @param replaceChar
     *            替换的字符
     * @return
     */
    public static String replaceAtIndex(int index, String ostr, String replaceChar) {

        if (isNotBlank(ostr)) {
            try {
                return ostr.replaceFirst(ostr.charAt(index) + "", replaceChar);
            } catch (Exception e) {
                return ostr;
            }
        } else {
            return ostr;
        }
    }

    /**
     * 填充左边字符
     * 
     * @param source
     *            源字符串
     * @param fillChar
     *            填充字符
     * @param len
     *            填充到的长度
     * @return 填充后的字符串
     */
    public static String fillLeft(String source, char fillChar, int len) {

        StringBuffer ret = new StringBuffer();
        if (null == source) {
            source = "";
        }
        if (source.length() > len) {
            ret.append(source);
        } else {
            int slen = source.length();
            while (ret.toString().length() + slen < len) {
                ret.append(fillChar);
            }
            ret.append(source);
        }
        return ret.toString();
    }

    /**
     * 填充右边字符
     * 
     * @param source
     *            源字符串
     * @param fillChar
     *            填充字符
     * @param len
     *            填充到的长度
     * @return 填充后的字符串
     */
    public static String filRight(String source, char fillChar, int len) {

        StringBuffer ret = new StringBuffer();
        if (null == source) {
            source = "";
        }
        if (source.length() > len) {
            ret.append(source);
        } else {
            ret.append(source);
            while (ret.toString().length() < len) {
                ret.append(fillChar);
            }
        }
        return ret.toString();
    }

    public static String filterStr(String str) {

        if (null == str || "".equals(str)) {
            return str;
        }
        str = str.replaceAll("'", "''");
        return str;
    }

    /**
     * 字符串中没有number，全是字母
     * 
     * @param str
     * @return
     */
    public static boolean isAllChar(String str) {

        String regex = ".*[a-zA-Z]+.*";
        Matcher m = Pattern.compile(regex).matcher(str);
        return m.matches();
    }

    /**
     * 判断字符串是否相等
     * 
     * @param cs1
     * @param cs2
     * @return
     */
    public static boolean equals(CharSequence cs1, CharSequence cs2) {

        return cs1 == null ? cs2 == null : cs1.equals(cs2);
    }

    public static boolean isMapJson(String str) {

        if (StringUtils.isBlank(str)) {
            return false;
        }
        if (str.indexOf("{") == 0 && str.lastIndexOf("}") == str.length() - 1) {
            return true;
        }
        return false;
    }

    public static boolean isListJson(String str) {

        if (StringUtils.isBlank(str)) {
            return false;
        }
        if (str.indexOf("[") == 0 && str.lastIndexOf("]") == str.length() - 1) {
            return true;
        }
        return false;
    }

    public static boolean isMapOrListJson(String str) {

        if (StringUtils.isBlank(str)) {
            return false;
        }
        str = str.trim();
        if (isMapJson(str) || isListJson(str)) {
            return true;
        }
        return false;
    }

    public static boolean isMobileNO(String str) {

        if (isBlank(str)) {
            return false;
        }
        str = str.trim();
        Pattern p = Pattern.compile(MOBILE_REG_EXP_STRING);
        Matcher m = p.matcher(str);
        return m.matches();

    }

    public static String encoderByMD5(String source) {

        MessageDigest md;
        byte[] data = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(source.getBytes("UTF-8"));
            data = md.digest();
        } catch (NoSuchAlgorithmException e) {} catch (UnsupportedEncodingException e) {}
        if (data == null)
            return "";
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            String hex = Integer.toHexString(0xff & data[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static String replaceBlank(String str) {

        String dest = "";
        if (str != null) {
            Matcher m = PATTERN.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    public static boolean compareContext(String str, String string) {

        String replaceBlank = replaceBlank(str);

        String replaceBlank1 = replaceBlank(string);

        if (replaceBlank.equals(replaceBlank1)) {
            return true;
        }
        return false;
    }

    /**
     * 替换##字符串
     * {"tenantCode":"#tenantCode#","pntCode":"1","c":[{"a":"#tenantCode#"}]}
     * 
     * @param str
     * @param map
     * @return
     */
    public static String replace(String str, Map<String, Object> map) {

        if (StringUtils.isEmpty(str)) {
            return str;
        }
        //直接字符串替换，找到所有
        int first = str.indexOf("#");
        if (first < 0) {
            return str;
        }
        int second = str.indexOf("#", first + 1);
        String t = str.substring(first, second + 1);
        String key = str.substring(first + 1, second);
        str = str.replaceAll(t, (String) map.get(key));
        str = replace(str, map);
        return str;
    }

    /**
     * 除去数组中的空值和签名参数
     * 
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {

        Map<String, String> result = new HashMap<String, String>();

        if (sArray == null || sArray.size() <= 0) {
            return result;
        }

        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign") || key.equalsIgnoreCase("sign_type")) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * 
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }

    public static void main(String[] args) {

        String s = "\"dkj\"";
        System.out.println(isMapOrListJson(s));
        String s1 = "你要去除" + " 的字符串";
        String s2 = "你要去除的字符串";
        String s3 = StringUtils.replaceBlank(s1);
        String s4 = StringUtils.replaceBlank(s2);

        boolean equals = s3.equals(s4);
        System.out.println(equals);

    }

    public static boolean isNotEmpty(String value) {

        return !isEmpty(value);
    }

    public static Long convertLong(String value) {

        if (isBlank(value)) {
            return null;
        }
        if (!isNumeric(value)) {
            return null;
        }
        return Long.valueOf(value);
    }

    public static Integer convertInteger(String value) {

        if (isBlank(value)) {
            return null;
        }
        if (!isNumeric(value)) {
            return null;
        }
        return Integer.valueOf(value);
    }

}
