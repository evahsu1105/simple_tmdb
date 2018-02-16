package evahsu.simplemovieapi.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import evahsu.simplemovieapi.R;
import evahsu.simplemovieapi.util.Util;

public class MainActivity extends AppCompatActivity {
    @OnClick(R.id.main_now_showing)
    public void goNowShowing(){
        Util.startActivity(MainActivity.this,MovieListActivity.class);
    }
    @OnClick(R.id.main_by_date)
    public void goByDate(){
        Util.startActivity(MainActivity.this,MovieFilterListActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}
