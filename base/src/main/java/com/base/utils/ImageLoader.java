package com.base.utils;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.base.R;
import com.pixplicity.sharp.Sharp;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Image Loader using Picasso, okhttp3 and Sharp
 *
 * @author Odey M. Khalaf <odey@devloops.net>
 */
public class ImageLoader {
    private Context mContext = null;
    private OkHttpClient httpClient;

    private ImageLoader() {
    }

    /**
     * initializes the context
     *
     * @param context context
     * @return ImageLoader Instance
     */
    public static ImageLoader with(Context context) {
        ImageLoader loader = new ImageLoader();
        loader.mContext = context;
        return loader;
    }

    public void load(final String url, final ImageView imageView) {
        try {
            if (url.endsWith(".svg"))
                loadSVG(url, imageView);
            else loadOtherFormats(url, imageView);
        } catch (NullPointerException e) {
            Alerts.with(mContext).showToast(mContext.getString(R.string.err_server_error));
            e.printStackTrace();
        }
    }

    /**
     * loads the image from URL into imageView
     *
     * @param url       image url
     * @param imageView imageView
     */
    private void loadOtherFormats(final String url, final ImageView imageView) {
        try {
            if (url.startsWith("http"))
                Picasso.get().load(url)
                        .placeholder(R.color.colorAccent)
                        .error(R.color.colorPrimaryDark)
                        .into(imageView);
            else {
                imageView.setImageURI(Uri.parse(url));
            }
        } catch (Exception e) {
            Logger.e("DebugLog", e.getMessage());
        }
    }

    /**
     * loadPNG SVG image from URL into imageView
     *
     * @param url       image url
     * @param imageView imageView
     */
    private void loadSVG(String url, final ImageView imageView) {
        if (httpClient == null) {
            // Use cache for performance and basic offline capability
            httpClient = new OkHttpClient.Builder()
                    .cache(new Cache(mContext.getCacheDir(), 5 * 1024 * 1014))
                    .build();
        }

        Request request = new Request.Builder().url(url).build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                imageView.setBackgroundColor(mContext.getResources().getColor(R.color.colorAccent));
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                assert response.body() != null;
                InputStream stream = Objects.requireNonNull(response.body()).byteStream();
                Sharp.loadInputStream(stream).into(imageView);
                stream.close();
            }
        });
    }
}
