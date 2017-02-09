package gg.mark.baselineandroid;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.DownloadListener;
import com.androidnetworking.interfaces.DownloadProgressListener;
import com.androidnetworking.interfaces.OkHttpResponseListener;

import okhttp3.Response;

/**
 * Created by Mark on 2/8/17.
 */
public class NetworkFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.network_fragment, container, false);

        rootView.findViewById(R.id.make_get_request_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AndroidNetworking.get("https://jsonplaceholder.typicode.com/posts")
                        .build()
                        .getAsOkHttpResponse(new OkHttpResponseListener() {
                            @Override
                            public void onResponse(Response response) {
                                if (response.isSuccessful()) {
                                    Snackbar.make(rootView, "onResponse - success", Snackbar.LENGTH_SHORT).show();
                                } else {
                                    Snackbar.make(rootView, "onResponse - no success", Snackbar.LENGTH_SHORT).show();
                                }
                            }
                            @Override
                            public void onError(ANError anError) {
                                Snackbar.make(rootView, "onError", Snackbar.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        rootView.findViewById(R.id.download_file_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AndroidNetworking.download("http://placehold.it/600/92c952", getActivity().getExternalFilesDir(null).toString(), "92c952")
                        .setTag("download_file_button")
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .setDownloadProgressListener(new DownloadProgressListener() {
                            @Override
                            public void onProgress(long bytesDownloaded, long totalBytes) {
                                // Log progress
                            }
                        })
                        .startDownload(new DownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                                Snackbar.make(rootView, "onDownloadComplete", Snackbar.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onError(ANError error) {
                                Snackbar.make(rootView, "onError", Snackbar.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        return rootView;
    }
}
