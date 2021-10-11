package com.alin.benchmark;


import android.app.Application;

import com.alin.benchmark.core.StorageBenchmarkManager;
import com.dbflow5.config.FlowConfig;
import com.dbflow5.config.FlowManager;
import com.alin.benchmark.dbflow.DBFlowBenchmark;
import com.alin.benchmark.greendao.GreenDaoBenchmark;
import com.alin.benchmark.realm.RealmBenchmark;

import io.realm.Realm;

public class AndroidApplication extends Application {
    private static AndroidApplication mobileApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        setupDbFlow();
        setupRealm();

        StorageBenchmarkManager.INSTANCE.register(new GreenDaoBenchmark());
        StorageBenchmarkManager.INSTANCE.register(new RealmBenchmark());
        StorageBenchmarkManager.INSTANCE.register(new DBFlowBenchmark());
    }

    private void setupRealm() {
        Realm.init(this);
    }

    private void setupDbFlow() {
        FlowManager.init(new FlowConfig.Builder(this)
                .openDatabasesOnInit(true)
                .build());
    }

    public static AndroidApplication getInstance() {
        return mobileApplication;
    }


}
