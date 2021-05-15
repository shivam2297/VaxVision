package in.hideandseek.vaxvision.common.lib;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();

        Request.Builder builder = originalRequest.newBuilder();

        // Add api token for each request
//        builder.header(ApiConstants.HEADER_KEY_TOKEN, getAPIToken(originalRequest.url().encodedPath().replaceFirst("/", "")));


        return chain.proceed(builder.build());
    }

    private String getAPIToken(String endpoint) {
        String messageToEncode = "ApiConstants.API_ACCESS_KEY + endpoint;";
        byte[] hmacSha256 = null;
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec("ApiConstants.API_SECRET_KEY".getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            mac.init(secretKeySpec);
            hmacSha256 = mac.doFinal(messageToEncode.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException("Failed to calculate hmac-sha256", e);
        }
        return String.format("%0" + (hmacSha256.length*2) + "X", new BigInteger(1, hmacSha256)).toLowerCase();
    }
}
