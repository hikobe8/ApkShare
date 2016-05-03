package soda.apksharedemo;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private TextView tvLable;
    private List<AppModel> mAppModels = new ArrayList<>();
    private AdapterApp mAdapterApp;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            tvLable.setVisibility(View.INVISIBLE);
            mAdapterApp.notifyDataSetChanged();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recView);
        tvLable = (TextView) findViewById(R.id.tv_lable);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapterApp = new AdapterApp(this, mAppModels);
        mRecyclerView.setAdapter(mAdapterApp);
        getApps();
    }

    /**
     * 线程中获取本机所有已安装应用
     */
    private void getApps() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                PackageManager manager = getPackageManager();
                List<PackageInfo> installedPackages = manager.getInstalledPackages(0);
                for (PackageInfo packageInfo : installedPackages) {
                    String name = (String) packageInfo.applicationInfo.loadLabel(manager);
                    Drawable drawable = packageInfo.applicationInfo.loadIcon(manager);
                    String time = formatTime(packageInfo.lastUpdateTime);
                    String path = packageInfo.applicationInfo.sourceDir;
                    File file = new File(path);
                    String size = formetFileSize(file.length());
                    AppModel appModel = new AppModel(name, drawable, size, time, file);
                    mAppModels.add(appModel);
                }
                mHandler.sendEmptyMessage(0);
            }
        }).start();
    }


    public String formetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }


    private String formatTime(long mills){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        String format = dateFormat.format(mills);
        return format;
    }
}
