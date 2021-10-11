package com.alin.benchmark.greendao;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class GreenDaoGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.alin.benchmark.greendao.model");
        schema.setDefaultJavaPackageDao("com.alin.benchmark.greendao.dao");
        addNote(schema);
        new DaoGenerator().generateAll(schema, "../OrmTest/app/src/main/java");
    }

    /**
     * @param schema
     */
    private static void addNote(Schema schema) {
        Entity note = schema.addEntity("User");
        note.addIdProperty();
        note.addStringProperty("name").notNull();
        note.addIntProperty("age");
        note.addLongProperty("timestamp");
    }
}
