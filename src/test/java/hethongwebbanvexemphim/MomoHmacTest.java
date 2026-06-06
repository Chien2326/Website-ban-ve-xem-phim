package hethongwebbanvexemphim;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class MomoHmacTest {
    public static void main(String[] args) throws Exception {
        // Test data from the error message
        String accessKey = "F8BBA842ECF85";
        String amount = "215000";
        String extraData = "";
        String ipnUrl = "http://localhost:8080/payment/ipn";
        String orderId = "70ee4000-5ad9-47ff-b709-b83a76cffaf8";
        String orderInfo = "Thanh toan ve xem phim - Thoát Khỏi Tận Thế";
        String partnerCode = "MOMO";
        String redirectUrl = "http://localhost:8080/payment/success";
        String requestId = "13456842-184c-4434-8ecf-0d59b5c96ddc";
        String requestType = "captureWallet";
        String secretKey = "K9518500473836474708";

        // Build raw signature exactly as per Momo's spec
        String rawSignature = "accessKey=" + accessKey
                + "&amount=" + amount
                + "&extraData=" + extraData
                + "&ipnUrl=" + ipnUrl
                + "&orderId=" + orderId
                + "&orderInfo=" + orderInfo
                + "&partnerCode=" + partnerCode
                + "&redirectUrl=" + redirectUrl
                + "&requestId=" + requestId
                + "&requestType=" + requestType;

        System.out.println("Raw Signature: " + rawSignature);

        // Compute HMAC-SHA256
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        byte[] hash = sha256_HMAC.doFinal(rawSignature.getBytes(StandardCharsets.UTF_8));
        String signature = bytesToHex(hash);

        System.out.println("Computed Signature: " + signature);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}
