package evahsu.simplemovieapi.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import evahsu.simplemovieapi.R;
import evahsu.simplemovieapi.intf.ObjectKeyRecyclerViewListener;
import evahsu.simplemovieapi.util.Constants;
import evahsu.simplemovieapi.web.movie.Result;

/**
 * Created by evahsu on 2017/12/24.
 */

public class MovieAdapter extends RecyclerView.Adapter {
    private ObjectKeyRecyclerViewListener recyclerViewListener;
    public final String TAG = this.getClass().getSimpleName();
    private ArrayList<Result> list = new ArrayList<>();
    private Context context;
    //for loadmore
    private int totalPages = 0;
    private int lastRequestPages = 0;
    private int lastFinishRequestPages = 0;
//    public MovieAdapter(ArrayList<Result> list) {
//        this.list = list;
//    }

    public MovieAdapter(Context context) {
        this.context = context;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getNextLastRequestPages() {
        lastRequestPages++;
        return lastRequestPages;
    }

    public int getLastRequestPages() {
        return lastRequestPages;
    }

    public boolean isReadyToLoad() {
        return lastRequestPages == lastFinishRequestPages;
    }
    public boolean isMorePageToLoad() {
        return lastRequestPages < totalPages;
    }

    public void setLastRequestPages(int lastRequestPages) {
        this.lastRequestPages = lastRequestPages;
    }

    public void resetPages(){
        totalPages = 0;
        lastRequestPages = 0;
        lastFinishRequestPages = 0;
    }

    public int getLastFinishRequestPages() {
        return lastFinishRequestPages;
    }

    public void setLastFinishRequestPages(int lastFinishRequestPages) {
        this.lastFinishRequestPages = lastFinishRequestPages;
    }

    public void clearData() {
        list.clear();
    }
    public void addData(ArrayList<Result> list) {
        if(list == null){
            return;
        }
        this.list.addAll(list);
    }
    public void setRecyclerViewListener(ObjectKeyRecyclerViewListener recyclerViewListener) {
        this.recyclerViewListener = recyclerViewListener;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_movie, null);
        view.setLayoutParams(lp);
        return new ReadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ReadViewHolder readViewHolder = (ReadViewHolder) holder;
        readViewHolder.position = position;
        Result listItem = list.get(position);
//        if(simpleListItem == null){
//            return;
//        }
        readViewHolder.position = position;
        readViewHolder.titleText.setText(listItem.getTitle());
        readViewHolder.dateText.setText(listItem.getRelease_date());
        readViewHolder.overviewText.setText(listItem.getOverview());
        Picasso.with(context)
                .load(String.format("%s%s", Constants.URL_POSTER_BASE,listItem.getPoster_path()))
                .placeholder(R.drawable.empty_icon)
                .error(R.drawable.btn_no_entry)
                .into(readViewHolder.posterImageView);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    class ReadViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        public View rootView;
        public TextView titleText;
        public TextView dateText;
        public TextView overviewText;
        public ImageView posterImageView;
        public int position;
        public ReadViewHolder(View itemView) {//read mode
            super(itemView);
            titleText = itemView.findViewById(R.id.movie_list_title);
            dateText = itemView.findViewById(R.id.movie_list_date);
            overviewText = itemView.findViewById(R.id.movie_list_overview);
            posterImageView = itemView.findViewById(R.id.movie_list_img);
            rootView = itemView.findViewById(R.id.listitem_movie_rootview);
            rootView.setOnClickListener(this);
            rootView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (null != recyclerViewListener) {
                Result listItem = list.get(position);
                recyclerViewListener.onItemClick(position,listItem);
            }
        }

        @Override

        public boolean onLongClick(View v) {
            if(null != recyclerViewListener){
                Result listItem = list.get(position);
                return recyclerViewListener.onItemLongClick(position,listItem);
            }
            return false;
        }
    }
}
