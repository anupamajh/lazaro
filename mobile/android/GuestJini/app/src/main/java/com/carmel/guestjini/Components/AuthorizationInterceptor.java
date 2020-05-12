package com.carmel.guestjini.Components;

public class AuthorizationInterceptor{

}
/*
implements Interceptor {
    private static Retrofit retrofit = null;
    private static String deviceToken;
    private static String accessToken;
    private static String refreshToken;
    private static TokenManager tokenManager;
    private static Context mContext;

    public AuthorizationInterceptor(Context context) {
        this.mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request modifiedRequest = null;

        tokenManager = new TokenManager() {
            final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);

            @Override
            public String getToken() {
                accessToken = sharedPreferences.getString(ACCESS_TOKEN, "");
                return accessToken;
            }

            @Override
            public boolean hasToken() {
                accessToken = sharedPreferences.getString(ACCESS_TOKEN, "");
                if (accessToken != null && !accessToken.equals("")) {
                    return true;
                }
                return false;
            }

            @Override
            public void clearToken() {
                sharedPreferences.edit().putString(ACCESS_TOKEN, "").apply();
            }

            @Override
            public String refreshToken() {
                final String accessToken = null;
                RequestBody reqbody = RequestBody.create(null, new byte[0]);
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(BASE_URL + "refresh")
                        .method("POST", reqbody)
                        .addHeader("Authorization",  refreshToken)
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    if ((response.code()) == 200) {
                        // Get response
                        String jsonData = response.body().string();

                        Gson gson = new Gson();
                        RefreshTokenResponseModel refreshTokenResponseModel = gson.fromJson(jsonData, RefreshTokenResponseModel.class);
                        if (refreshTokenResponseModel.getRespCode().equals("1")) {
                            sharedPreferences.edit().putString(ACCESS_TOKEN, refreshTokenResponseModel.getResponse()).apply();
                            return refreshTokenResponseModel.getResponse();
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return accessToken;
            }
        };

        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        deviceToken = sharedPreferences.getString(GCM_TOKEN, "");
        accessToken = sharedPreferences.getString(ACCESS_TOKEN, "");
        refreshToken = sharedPreferences.getString(REFRESH_TOKEN, "");

        Response response = chain.proceed(request);
        boolean unauthorized =false;
        if(response.code() == 401 || response.code() == 422){
            unauthorized=true;
        }

        if (unauthorized) {
            tokenManager.clearToken();
            tokenManager.refreshToken();
            accessToken = sharedPreferences.getString(ACCESS_TOKEN, "");
            if(accessToken!=null){
                modifiedRequest = request.newBuilder()
                        .addHeader("Authorization", JWT_TOKEN_PREFIX + tokenManager.getToken())
                        .build();
                return chain.proceed(modifiedRequest);
            }
        }
        return response;
    }
}

 */
