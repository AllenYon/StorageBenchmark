package com.alin.benchmark.greendao;

import android.content.Context;

import com.alin.benchmark.greendao.dao.DaoMaster;
import com.alin.benchmark.greendao.dao.UserDao;
import com.alin.benchmark.greendao.model.User;
import com.alin.benchmark.core.StorageBenchmark;
import com.alin.benchmark.core.TestData;

import java.util.List;

public class GreenDaoBenchmark implements StorageBenchmark {
    private UserDao userDao;

    @Override
    public void onCreate(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "greendao.db", null);
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        userDao = daoMaster.newSession().getUserDao();
    }

    @Override
    public void singleWrite(TestData testData) {
        User user = new User();
        user.setName(testData.getName());
        user.setAge(testData.getAge());
        user.setTimestamp(testData.getTimestamp());
        userDao.insert(user);
    }

    @Override
    public void singleRead(String name) {
        List<User> userList = userDao.queryBuilder().where(UserDao.Properties.Name.eq(name)).limit(1).list();
        if (userList.size() > 0) {
            User u = userList.get(0);
        }
    }

    @Override
    public void singleUpdate(String name, String newName) {
        List<User> userList = userDao.queryBuilder().where(UserDao.Properties.Name.eq(name)).limit(1).list();
        if (userList.size() > 0) {
            User u = userList.get(0);
            u.setName(newName);
            userDao.update(u);
        }
    }

    @Override
    public void singleDelete(String name) {
        List<User> userList = userDao.queryBuilder().where(UserDao.Properties.Name.eq(name)).limit(1).list();
        if (userList.size() > 0) {
            User u = userList.get(0);
            userDao.delete(u);
        }
    }

    @Override
    public void deleteAll() {
        userDao.deleteAll();
    }

    @Override
    public void batchWrite(List<TestData> testDatas) {

    }

    @Override
    public void batchRead(List<String> names) {

    }

    @Override
    public void onDestroy() {

    }

}
