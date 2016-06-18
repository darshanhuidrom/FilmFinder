package com.kangladevelopers.filmfinder.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kangladevelopers.filmfinder.R;
import com.kangladevelopers.filmfinder.pogo.Movie;
import com.kangladevelopers.filmfinder.utils.StringUtility;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HUIDROM on 6/18/2016.
 */
public class RvMovieAdapter extends RecyclerView.Adapter<RvMovieAdapter.CustomViewHolder> {

    private List<Movie> feedItemList = new ArrayList<>();
    private Context mContext;
    String LogTAG = "RvAdapter";
    ArrayList<CustomViewHolder> viewHolders = new ArrayList<>();
    private RvAdapterClickListener rvAdapterClickListener;

    public RvMovieAdapter(List<Movie> feedItemList, Context context) {
        this.feedItemList = feedItemList;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.block_move_info, parent, false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        viewHolders.add(viewHolder);
        return viewHolder;
    }

    public List<Movie> getData() {
        return feedItemList;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        final Movie movie = feedItemList.get(position);
        String name = movie.getName();
        String director = movie.getDirector();
        int year = movie.getYear();
        String imageUrl = movie.getPicUrl();
        String cast = movie.getStarring();

        holder.tvName.setText("Movie: " + name);
        holder.tvDirector.setText("Directed by: " + director);
        holder.tvCast.setText("Cast: " + cast);
        holder.tvYear.setText("Year: " + year);

        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        imageLoader.displayImage(imageUrl, holder.moveIcon, options);
    }

    @Override
    public int getItemCount() {
        return feedItemList.size();
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView cv;
        ImageView moveIcon;
        TextView tvName;
        TextView tvCast;
        TextView tvDirector;
        TextView tvYear;

        public CustomViewHolder(View view) {
            super(view);
            moveIcon = (ImageView) view.findViewById(R.id.iv_moveIcon);
            tvName = (TextView) view.findViewById(R.id.tv_name);
            tvDirector = (TextView) view.findViewById(R.id.tv_director);
            tvCast = (TextView) view.findViewById(R.id.tv_cast);
            tvYear = (TextView) view.findViewById(R.id.tv_year);
            cv = (CardView) view;
            cv.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            rvAdapterClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public interface RvAdapterClickListener {
        void onItemClick(int i, View v);
    }


    //----------------------
    public void setRvAdapterClickLIstener(RvAdapterClickListener rvAdapterClickListener) {
        this.rvAdapterClickListener = rvAdapterClickListener;
    }
}
