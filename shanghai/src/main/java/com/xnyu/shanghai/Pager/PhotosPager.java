package com.xnyu.shanghai.Pager;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.xnyu.shanghai.Base.BaseNewsPager;
import com.xnyu.shanghai.Bean.PhotosData;
import com.xnyu.shanghai.Global.GlobalData;
import com.xnyu.shanghai.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/2/17 0017.
 */
public class PhotosPager extends BaseNewsPager {

    private GridView mGridVIew;
    private ImageButton mListIbtn;
    private ImageButton mGridIbtn;
    private ListView mListView;
    private PhotosData mPhotosData;
    private final ImageButton mPhotosIbtn;
    private boolean isList = true;

    public PhotosPager(Context context, ImageButton photosIbtn) {
        super(context);
        mPhotosIbtn = photosIbtn;
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext,R.layout.pager_photos_menu,null);
        mListView = (ListView) view.findViewById(R.id.lv_photos);
        mGridVIew = (GridView) view.findViewById(R.id.gv_photos);
        return view;

    }


    @Override
    public void initData() {
        getDataFromNetwork();
    }

    private void getDataFromNetwork() {
        OkHttpUtils.get().url(GlobalData.PHOTOS).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                Toast.makeText(mContext, "网络错误", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                parserData(response);
            }
        });
    }

    private void parserData(String response) {
        mPhotosData = new Gson().fromJson(response,PhotosData.class);
        mListView.setAdapter(new PhotosAdapter());
        mGridVIew.setAdapter(new PhotosAdapter());
    }

    class PhotosAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mPhotosData.getData().getNews().size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if (convertView == null){
                holder = new ViewHolder();
                convertView = View.inflate(mContext,R.layout.list_photos_item,null);
                holder.mImageView = (ImageView) convertView.findViewById(R.id.photos_iv);
                holder.mTextView = (TextView) convertView.findViewById(R.id.photos_tv_title);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
                holder.mTextView.setText(mPhotosData.getData().getNews().get(position).getTitle());
            OkHttpUtils.get().url(GlobalData.replaceURL(mPhotosData.getData().getNews().get(position).getListimage()))
                    .build().execute(new BitmapCallback() {
                @Override
                public void onError(Call call, Exception e) {
                    Toast.makeText(mContext, "网络错误", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(Bitmap response) {
                    holder.mImageView.setImageBitmap(response);
                }
            });
            return convertView;
        }
    }

    static class ViewHolder{
        ImageView mImageView;
        TextView mTextView;
    }
}
