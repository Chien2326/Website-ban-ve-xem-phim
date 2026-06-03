package hethongwebbanvexemphim.service;

import hethongwebbanvexemphim.config.MomoConfig;
import hethongwebbanvexemphim.dto.request.MomoPaymentRequest;
import hethongwebbanvexemphim.dto.response.MomoPaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MomoPaymentService {

    private final MomoConfig momoConfig;
    private final RestTemplate restTemplate;

    public MomoPaymentResponse createPayment(Long amount, String orderInfo) {
        String orderId = UUID.randomUUID().toString();
        String requestId = UUID.randomUUID().toString();

        String requestType = "captureWallet";
        String extraData = ""; // Base64 encoded, empty for now
        String lang = "vi";

        // Đảm bảo amount là số nguyên VND không có chữ thập phân
        Long momoAmount = amount;
        if (momoAmount == null || momoAmount <= 0) {
            momoAmount = 10000L; // Giá trị test nếu không có tiền
        }

        System.out.println("=== MOMO PAYMENT DEBUG: amount = " + momoAmount);
        System.out.println("=== MOMO PAYMENT DEBUG: orderInfo = " + orderInfo);
        System.out.println("=== MOMO PAYMENT DEBUG: endpoint = " + momoConfig.getEndpoint());
        System.out.println("=== MOMO PAYMENT DEBUG: redirectUrl = " + momoConfig.getRedirectUrl());
        System.out.println("=== MOMO PAYMENT DEBUG: ipnUrl = " + momoConfig.getIpnUrl());
        System.out.println("=== MOMO PAYMENT DEBUG: partnerCode = " + momoConfig.getPartnerCode());
        System.out.println("=== MOMO PAYMENT DEBUG: accessKey = " + momoConfig.getAccessKey());

        // Build raw signature
        String rawSignature = "accessKey=" + momoConfig.getAccessKey()
                + "&amount=" + momoAmount
                + "&extraData=" + extraData
                + "&ipnUrl=" + momoConfig.getIpnUrl()
                + "&orderId=" + orderId
                + "&orderInfo=" + orderInfo
                + "&partnerCode=" + momoConfig.getPartnerCode()
                + "&redirectUrl=" + momoConfig.getRedirectUrl()
                + "&requestId=" + requestId
                + "&requestType=" + requestType;

        System.out.println("=== MOMO PAYMENT DEBUG: rawSignature = " + rawSignature);

        String signature = generateHmacSHA256(rawSignature, momoConfig.getSecretKey());
        System.out.println("=== MOMO PAYMENT DEBUG: signature = " + signature);

        MomoPaymentRequest request = MomoPaymentRequest.builder()
                .partnerCode(momoConfig.getPartnerCode())
                .partnerName("Test")
                .storeId("Test Store")
                .requestId(requestId)
                .amount(momoAmount)
                .orderId(orderId)
                .orderInfo(orderInfo)
                .redirectUrl(momoConfig.getRedirectUrl())
                .ipnUrl(momoConfig.getIpnUrl())
                .requestType(requestType)
                .extraData(extraData)
                .lang(lang)
                .signature(signature)
                .build();

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<MomoPaymentRequest> entity = new HttpEntity<>(request, headers);

            ResponseEntity<MomoPaymentResponse> response = restTemplate.postForEntity(
                    momoConfig.getEndpoint(),
                    entity,
                    MomoPaymentResponse.class
            );

            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return MomoPaymentResponse.builder()
                    .resultCode(99)
                    .message("Error: " + e.getMessage())
                    .build();
        }
    }

    private String generateHmacSHA256(String data, String key) {
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] hash = sha256_HMAC.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hash);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}
