package in.hideandseek.vaxvision.common.lib;

import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceManager {

    private static ServiceManager sInstance;

    private ServiceManager() {
    }

    public static ServiceManager getManager() {
        if (sInstance == null)
            sInstance = new ServiceManager();
        return sInstance;
    }

    public <T> T createService(Class<T> tClass) {
        return createService(tClass, HttpUrl.parse(ApiConstants.URL_API));
    }

    public <T> T createService(Class<T> tClass, HttpUrl httpURl) {
        return getRetrofit(httpURl).create(tClass);
    }

    private Retrofit getRetrofit(HttpUrl httpUrl) {
        return new Retrofit.Builder()
                .baseUrl(httpUrl)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private OkHttpClient getClient() {
        return new OkHttpClient.Builder().connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .addInterceptor(new RequestInterceptor())
                .build();
    }
}
