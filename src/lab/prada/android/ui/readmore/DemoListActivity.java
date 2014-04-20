package lab.prada.android.ui.readmore;

import lab.prada.android.ui.readmore.adapter.ReadMoreAdapter;
import lab.prada.android.ui.readmore.adapter.ReadMoreAdapter.PullDownToRefreshListener;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.LinearLayout.LayoutParams;

public class DemoListActivity extends Activity implements PullDownToRefreshListener {

    private ReadMoreAdapter<SampleAdapter> mAdapter;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_list);

        RelativeLayout progress = new RelativeLayout(this);
        progress.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, 100));
        progress.setGravity(Gravity.CENTER);
        progress.addView(new ProgressBar(this));

        mAdapter = new ReadMoreAdapter<SampleAdapter>(this,
                new SampleAdapter(this), progress);
        mAdapter.addListener(this);
        mHandler = new Handler();

        ((ListView)this.findViewById(R.id.listView)).setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.demo_list, menu);
        return true;
    }

    @Override
    public void pullDownToRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.getAdapter().addCount(5);
                mAdapter.handledRefresh();
            }
        }, 3000);
    }

}
