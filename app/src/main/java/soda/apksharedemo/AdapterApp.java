package soda.apksharedemo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;

/**
 * Created by soda on 2016/5/3.
 */
public class AdapterApp extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<AppModel> mAppModelList;


    public AdapterApp(Context context, List<AppModel> appModelList) {
        mContext = context;
        mAppModelList = appModelList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_app, null, false);
        return new MViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MViewHolder mViewHolder = (MViewHolder) holder;
        final AppModel appModel = mAppModelList.get(position);
        mViewHolder.ivIcon.setImageDrawable(appModel.getAppIcon());
        mViewHolder.tvName.setText(appModel.getAppName());
        mViewHolder.tvTime.setText(appModel.getLastUpdatetime());
        mViewHolder.tvSize.setText(appModel.getAppSize());
        mViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File apkFile = appModel.getAppFile();
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("*/*");
                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(apkFile));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAppModelList.size();
    }

    static class MViewHolder extends RecyclerView.ViewHolder {
        ImageView ivIcon;
        TextView tvName;
        TextView tvTime;
        TextView tvSize;

        public MViewHolder(View itemView) {
            super(itemView);
            ivIcon = (ImageView) itemView.findViewById(R.id.iv_icon);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            tvSize = (TextView) itemView.findViewById(R.id.tv_size);
        }
    }

}
