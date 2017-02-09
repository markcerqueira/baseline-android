package gg.mark.baselineandroid;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Mark on 2/8/17.
 */
public class ImageLoadingFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.image_loading_fragment, container, false);

        final ImageView imageView = (ImageView) rootView.findViewById(R.id.download_image_image_view);

        rootView.findViewById(R.id.download_image_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(ImageLoadingFragment.this).load("http://goo.gl/gEgYUd").into(imageView);
            }
        });

        return rootView;
    }
}
