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
import evahsu.simplemovieapi.web.movie.Cast;

/**
 * Created by evahsu on 2017/12/24.
 */

public class CastAdapter extends RecyclerView.Adapter {
    private ObjectKeyRecyclerViewListener recyclerViewListener;
    public final String TAG = this.getClass().getSimpleName();
    private ArrayList<Cast> list = new ArrayList<>();
    private Context context;
//    public MovieAdapter(ArrayList<Result> list) {
//        this.list = list;
//    }

    public CastAdapter(Context context) {
        this.context = context;
    }

    public void clearData(){
        list.clear();
    }

    public void setData(ArrayList<Cast> list) {
        if(list == null){
            return;
        }
        this.list = list;
    }
    public void setRecyclerViewListener(ObjectKeyRecyclerViewListener recyclerViewListener) {
        this.recyclerViewListener = recyclerViewListener;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_cast, null);
        view.setLayoutParams(lp);
        return new ReadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ReadViewHolder readViewHolder = (ReadViewHolder) holder;
        readViewHolder.position = position;
        Cast listItem = list.get(position);
//        if(simpleListItem == null){
//            return;
//        }
        readViewHolder.position = position;
        readViewHolder.nameText.setText(listItem.getName());
        readViewHolder.characterText.setText(listItem.getCharacter());
        Picasso.with(context)
                .load(String.format("%s%s", Constants.URL_POSTER_BASE,listItem.getProfile_path()))
                .placeholder(R.drawable.empty_icon)
                .error(R.drawable.btn_no_entry)
                .into(readViewHolder.profileImageView);
//        readViewHolder.stateIcon.setImageResource(listItem.getStatIconId());
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    class ReadViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        public View rootView;
        public TextView nameText;
        public TextView characterText;
        public ImageView profileImageView;
        public int position;
        public ReadViewHolder(View itemView) {//read mode
            super(itemView);
            nameText = itemView.findViewById(R.id.cast_list_name);
            characterText = itemView.findViewById(R.id.cast_list_character);
            profileImageView = itemView.findViewById(R.id.cast_list_img);
            rootView = itemView.findViewById(R.id.listitem_cast_rootview);
            rootView.setOnClickListener(this);
            rootView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (null != recyclerViewListener) {
                Cast listItem = list.get(position);
                recyclerViewListener.onItemClick(position,listItem);
            }
        }

        @Override

        public boolean onLongClick(View v) {
            if(null != recyclerViewListener){
                Cast listItem = list.get(position);
                return recyclerViewListener.onItemLongClick(position,listItem);
            }
            return false;
        }
    }
}
