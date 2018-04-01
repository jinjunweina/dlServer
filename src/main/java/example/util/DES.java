package example.util;

/**
 * Created by stone on 2017/6/21.
 */

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by xiang.li on 2015/2/28.
 * DES �ӽ��ܹ�����
 *
 * <pre>
 * ֧�� DES��DESede(TripleDES,����3DES)��AES��Blowfish��RC2��RC4(ARCFOUR)
 * DES                  key size must be equal to 56
 * DESede(TripleDES)    key size must be equal to 112 or 168
 * AES                  key size must be equal to 128, 192 or 256,but 192 and 256 bits may not be available
 * Blowfish             key size must be multiple of 8, and can only range from 32 to 448 (inclusive)
 * RC2                  key size must be between 40 and 1024 bits
 * RC4(ARCFOUR)         key size must be between 40 and 1024 bits
 * �������� ��Ҫ��ע JDK Document http://.../docs/technotes/guides/security/SunProviders.html
 * </pre>
 */
public class DES {
    /**
     * ������ܷ�ʽ
     */
    private final static String KEY_DES = "DES";
    private final static String KEY_AES = "AES";    // ����

    /**
     * ȫ������
     */
    private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    /**
     * ��ʼ����Կ
     * @return
     */
    public static String init() {
        return init(null);
    }

    /**
     * ��ʼ����Կ
     * @param seed ��ʼ������
     * @return
     */
    public static String init(String seed) {
        SecureRandom secure = null;
        String str = "";
        try {
            if (null != secure) {
                // �������ĳ�ʼ��
                secure = new SecureRandom(decryptBase64(seed));
            } else {
                // ���������ĳ�ʼ��
                secure = new SecureRandom();
            }

            KeyGenerator generator = KeyGenerator.getInstance(KEY_DES);
            generator.init(secure);

            SecretKey key = generator.generateKey();
            str = encryptBase64(key.getEncoded());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static boolean isNullOrEmpty (String data){
        return data == null || data.length() == 0;
    }

    /**
     * ת����Կ
     * @param key ��Կ���ֽ�����
     * @return
     */
    private static Key byteToKey(byte[] key) {
        SecretKey secretKey = null;
        try {
            DESKeySpec dks = new DESKeySpec(key);
            SecretKeyFactory factory = SecretKeyFactory.getInstance(KEY_DES);
            secretKey = factory.generateSecret(dks);

            // ��ʹ�������ԳƼ����㷨ʱ����AES��Blowfish���㷨ʱ�������������滻�������д���
//            secretKey = new SecretKeySpec(key, KEY_DES);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return secretKey;
    }

    /**
     * DES ����
     * @param data ��Ҫ���ܵ��ַ���
     * @param key ��Կ
     * @return
     */
    public static String decryptDES(String data, String key) {
        // ��֤������ַ���
        if (isNullOrEmpty(data)) {
            return "";
        }
        // ���ý��ܷ�����ɽ���
        byte[] bytes = decryptDES(hexString2Bytes(data), key);
        // ���õ����ֽ��������ַ�������
        return new String(bytes);
    }

    /**
     * DES ����
     * @param data ��Ҫ���ܵ��ֽ�����
     * @param key ��Կ
     * @return
     */
    public static byte[] decryptDES(byte[] data, String key) {
        byte[] bytes = null;
        try {
            Key k = byteToKey(decryptBase64(key));
            Cipher cipher = Cipher.getInstance(KEY_DES);
            cipher.init(Cipher.DECRYPT_MODE, k);
            bytes = cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * DES ����
     * @param data ��Ҫ���ܵ��ַ���
     * @param key ��Կ
     * @return
     */
    public static String encryptDES(String data, String key) {
        // ��֤������ַ���
        if (isNullOrEmpty(data)) {
            return "";
        }
        // ���ü��ܷ�����ɼ���
        byte[] bytes = encryptDES(data.getBytes(), key);
        // ���õ����ֽ��������ַ�������
        return byteArrayToHexString(bytes);
    }

    /**
     * DES ����
     * @param data ��Ҫ���ܵ��ֽ�����
     * @param key ��Կ
     * @return
     */
    public static byte[] encryptDES(byte[] data, String key) {
        byte[] bytes = null;
        try {
            Key k = byteToKey(decryptBase64(key));
            Cipher cipher = Cipher.getInstance(KEY_DES);
            cipher.init(Cipher.ENCRYPT_MODE, k);
            bytes = cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }


    /**
     * BASE64 ����
     * @param key ��Ҫ���ܵ��ַ���
     * @return �ֽ�����
     * @throws Exception
     */
    public static byte[] decryptBase64(String key) throws Exception {
        return Base64.decode(key);      
    }

    /**
     * BASE64 ����
     * @param key ��Ҫ���ܵ��ֽ�����
     * @return �ַ���
     * @throws Exception
     */
    public static String encryptBase64(byte[] key) throws Exception {
        return Base64.encode(key);  
    }

    /**
     * ��һ���ֽ�ת����ʮ��������ʽ���ַ���
     * @param b �ֽ�����
     * @return �ַ���
     */
    private static String byteToHexString(byte b) {
        int ret = b;
        //System.out.println("ret = " + ret);
        if (ret < 0) {
            ret += 256;
        }
        int m = ret / 16;
        int n = ret % 16;
        return hexDigits[m] + hexDigits[n];
    }

    /**
     * ת���ֽ�����Ϊʮ�������ַ���
     * @param bytes �ֽ�����
     * @return ʮ�������ַ���
     */
    private static String byteArrayToHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(byteToHexString(bytes[i]));
        }
        return sb.toString();
    }


    /**
     * ת��ʮ�������ַ���Ϊ�ֽ�����
     * @param hexstr ʮ�������ַ���
     * @return
     */
    public static byte[] hexString2Bytes(String hexstr) {
        byte[] b = new byte[hexstr.length() / 2];
        int j = 0;
        for (int i = 0; i < b.length; i++) {
            char c0 = hexstr.charAt(j++);
            char c1 = hexstr.charAt(j++);
            b[i] = (byte) ((parse(c0) << 4) | parse(c1));
        }
        return b;
    }

    /**
     * ת���ַ���������Ϊ��������
     * @param c �ַ�
     * @return
     */
    private static int parse(char c) {
        if (c >= 'a')
            return (c - 'a' + 10) & 0x0f;
        if (c >= 'A')
            return (c - 'A' + 10) & 0x0f;
        return (c - '0') & 0x0f;
    }

    /**
     * ���Է���
     * @param args
     */
    public static void main(String[] args) {
        //String key = DES.init();
    	String key = "1234567890123456";
        System.out.println("DES��Կ:\n" + key);

        String word = "����ΰ,1,CHN,610581198909294115,19890929,20160701,20360701,C1,����ʡ�������ô�������ׯ����";


        String encWord = encryptDES(word, key);

        System.out.println(word + "\n���ܺ�\n" + encWord);
        System.out.println("����ǰ���ȣ�" + word.length() + "���ܺ󳤶ȣ�" + encWord.length());
        System.out.println(word + "\n���ܺ�\n" + decryptDES(encWord, key));
    }
}