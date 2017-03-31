package zovlzhongguanhua.demo;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.*;

import java.math.BigInteger;

public class Codec {

    public static void main(String[] args) {

        String string = "abc";
        byte[] bytes = string.getBytes();
        BigInteger bigInteger = new BigInteger("123456789123456789123456789123456789");

        /*信息摘要*/

        digest(string);
        hmac(bytes);
        md5(bytes);
        sha(bytes);
        unix(string, bytes);

        /*信息编解码*/

        base64(bytes, bigInteger);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------

    private static void digest(String string) {
        System.out.println(DigestUtils.md2(string));
        System.out.println(DigestUtils.md2Hex(string));

        System.out.println(DigestUtils.md5(string));
        System.out.println(DigestUtils.md5Hex(string));

        System.out.println(DigestUtils.sha(string));
        System.out.println(DigestUtils.shaHex(string));

        System.out.println(DigestUtils.sha1(string));
        System.out.println(DigestUtils.sha1Hex(string));

        System.out.println(DigestUtils.sha256(string));
        System.out.println(DigestUtils.sha256Hex(string));

        System.out.println(DigestUtils.sha384(string));
        System.out.println(DigestUtils.sha384Hex(string));

        System.out.println(DigestUtils.sha512(string));
        System.out.println(DigestUtils.sha512Hex(string));
    }

    private static void hmac(byte[] bytes) {
        System.out.println(HmacUtils.getHmacMd5(bytes));
        System.out.println(HmacUtils.getHmacSha1(bytes));
        System.out.println(HmacUtils.getHmacSha256(bytes));
        System.out.println(HmacUtils.getHmacSha384(bytes));
        System.out.println(HmacUtils.getHmacSha512(bytes));
    }

    private static void md5(byte[] bytes) {
        System.out.println(Md5Crypt.apr1Crypt(bytes));
        System.out.println(Md5Crypt.md5Crypt(bytes));
    }

    private static void sha(byte[] bytes) {
        System.out.println(Sha2Crypt.sha256Crypt(bytes));
        System.out.println(Sha2Crypt.sha512Crypt(bytes));
    }

    private static void unix(String string, byte[] bytes) {
        System.out.println(UnixCrypt.crypt(bytes));
        System.out.println(UnixCrypt.crypt(string));
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------

    private static void base64(byte[] bytes, BigInteger bigInteger) {
        byte[] encodeBytes = Base64.encodeBase64(bytes);
        if (Base64.isBase64(encodeBytes)) {
            byte[] decodeBytes = Base64.decodeBase64(encodeBytes);
            System.out.println(String.valueOf(decodeBytes));
        }

        byte[] encodeInt = Base64.encodeInteger(bigInteger);
        if (Base64.isBase64(encodeInt)) {
            BigInteger decodeInt = Base64.decodeInteger(encodeInt);
            System.out.println(decodeInt);
        }

        String encodeString = Base64.encodeBase64String(bytes);
        if (Base64.isBase64(encodeString)) {
            byte[] decodeString = Base64.decodeBase64(encodeString);
            System.out.println(String.valueOf(decodeString));
        }
    }
}
