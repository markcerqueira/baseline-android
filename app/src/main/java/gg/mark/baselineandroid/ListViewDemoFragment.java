package gg.mark.baselineandroid;

import android.app.Fragment;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import gg.mark.baselineandroid.models.Photo;

/**
 * Created by Mark on 2/12/17.
 */
public class ListViewDemoFragment extends Fragment {

    protected ListView mListView;
    protected View mLoadingView;

    protected List<Photo> mPhotoList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_view_demo_fragment, container, false);

        mListView = (ListView) rootView.findViewById(R.id.demo_list_view);
        mLoadingView = rootView.findViewById(R.id.demo_list_view_loading_view);

        getPhotos();

        return rootView;
    }

    private void getPhotos() {
        AndroidNetworking.get("https://jsonplaceholder.typicode.com/photos")
                .setTag("/photos")
                .build()
                .getAsObjectList(Photo.class, new ParsedRequestListener<List<Photo>>() {
                    @Override
                    public void onResponse(List<Photo> photos) {
                        mPhotoList.clear();
                        mPhotoList.addAll(photos);

                        mListView.setAdapter(mListAdapter);

                        mLoadingView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    protected ListAdapter mListAdapter = new ListAdapter() {
        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override
        public boolean isEnabled(int i) {
            return false;
        }

        @Override
        public void registerDataSetObserver(DataSetObserver dataSetObserver) {

        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

        }

        @Override
        public int getCount() {
            return mPhotoList.size();
        }

        @Override
        public Object getItem(int i) {
            return mPhotoList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = getActivity().getLayoutInflater().inflate(android.R.layout.activity_list_item, viewGroup, false);
            }

            ImageView imageView = (ImageView) view.findViewById(android.R.id.icon);
            TextView textView = (TextView) view.findViewById(android.R.id.text1);

            textView.setText(mPhotoList.get(i).title);

            // If recycled we may be loading an image into this view already, so cancel that request before we make
            // the call to load another image below.
            Glide.clear(imageView);

            Glide.with(ListViewDemoFragment.this).load(mPhotoList.get(i).thumbnailUrl).into(imageView);

            return view;
        }

        @Override
        public int getItemViewType(int i) {
            return 0;
        }

        @Override
        public int getViewTypeCount() {
            // If return value is less than 1 will throw an IllegalArgumentException
            return 1;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }
    };
}
