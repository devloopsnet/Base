package com.base.utils;

import okhttp3.HttpUrl;

/**
 * @author Odey M. Khalaf <odey.khalaf@gmail.com>
 */
public class URLFactory {
    private static final String SCHEME = "https";
    private static final String HOST = "BASE_HOST";
    private static final String API_PATH = "api";

    private URLFactory() {

    }

    /**
     * Builds URL for API calls
     *
     * @param api api name
     * @return HttpUrl.Builder
     */
    static HttpUrl.Builder getUrlBuilderFor(API api) {
        HttpUrl.Builder builder;
        builder = new HttpUrl.Builder();
        builder.scheme(SCHEME)
                .host(HOST)
                .addPathSegments(API_PATH)
                .addPathSegments(api.getName());

        return builder;
    }

    /**
     * Build URL For API Call
     *
     * @param api API name
     * @return String
     */
    public static String getURlBuilder(API api) {
        HttpUrl.Builder builder;
        builder = new HttpUrl.Builder();
        builder.scheme(SCHEME)
                .host(HOST)
                .addPathSegments(API_PATH)
                .addPathSegments(api.getName());

        return builder.toString();
    }

    /**
     * API LIST
     */
    public enum API {

        /**
         * headers: Accept:application/json
         * headers: locale:[ar-en]
         * headers: Authorization:token
         */
        GET_USER_PROFILE("user/me"),

        /**
         * headers: locale[ar-en]
         * params: phone_number
         */
        POST_LOGIN("user/send-otp"),

        /**
         * headers: Accept:application/json
         * headers: locale:[ar-en]
         * headers: Authorization:token
         *
         * <Home>
         * params: "name"
         * params: "gender"
         * params: "user_type"
         * </Home>
         *
         * <Corporate>
         * params: "name"
         * params: "land_line"
         * params: "email"
         * params: "contact_person"
         * params: "user_type"
         * </Corporate>
         */
        PUT_SIGNUP("user/update-information"),

        /**
         * headers: Accept:application/json
         * headers: locale:[ar-en]
         * headers: Authorization:token
         */
        GET_HOME("user/categories"),

        /**
         * headers: Accept:application/json
         * headers: locale:[ar-en]
         * headers: Authorization:token
         * params:[id] (ex: categories/2)
         */
        GET_HOME_DETIALS("user/categories/"),

        /**
         * headers: Accept:application/json
         * headers: locale:[ar-en]
         * headers: Authorization:token
         * params:  CategoriesEntity.class
         */
        POST_CREATE_ORDER("user/orders/create"),

        /**
         * headers: Accept:application/json
         * headers: locale:[ar-en]
         * headers: Authorization:token
         */
        GET_ORDERS("user/orders"),

        /**
         * headers: Accept:application/json
         * headers: locale:[ar-en]
         * headers: Authorization:token
         */
        GET_APP_SETTINGS("user/application-settings"),

        /**
         * headers: Accept:application/json
         * headers: locale:[ar-en]
         * headers: Authorization:token
         * params: user/notifications?page={int} (optional)
         */
        GET_NOTIFICATIONS_LIST("user/notifications"),

        /**
         * headers: locale[ar-en], Accept:application/json; charset=utf-8, Authorization: access_token
         * Params : quality_rate(double), quantity_rate(double), behaviour_rate(double), comment(string)
         * example: user/orders/{id}/rate-driver
         */
        POST_RATING("user/orders/"),

        /**
         * headers: locale[ar-en], Accept:application/json; charset=utf-8, Authorization: access_token
         * example: user/orders/{order_id}/cancel
         */
        GET_CANCEL_ORDER("user/orders");

        private final String name;

        API(String s) {
            name = s;
        }

        public String getName() {
            return name;
        }
    }
}