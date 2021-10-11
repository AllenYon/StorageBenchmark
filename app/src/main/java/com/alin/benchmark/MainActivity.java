package com.alin.benchmark;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.alin.benchmark.core.StorageBenchmarkManager;
import com.alin.benchmark.ormlite.OrmHelper;
import com.alin.benchmark.ormlite.OrmliteBenchmark;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;

public class MainActivity extends OrmLiteBaseActivity<OrmHelper> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OrmliteBenchmark ormliteBenchmark = new OrmliteBenchmark(this);
        StorageBenchmarkManager.INSTANCE.register(ormliteBenchmark);

        findViewById(R.id.benchmark).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                StorageBenchmarkManager.INSTANCE.start(MainActivity.this, 1000);
            }
        });

    }


}
