package com.example;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Property;
import org.greenrobot.greendao.generator.Schema;


public class MyGenerator {
    private static Entity user;
    private static Entity article;

    public static void main(String args[]) {
        try {
            // 第一个参数：数据库版本号；第二个参数：将生成的实体类放到哪个包下
            Schema schema = new Schema(3, "bean");
            // 将生成的DaoMaster、DaoSession和Dao类放到哪个包下
            schema.setDefaultJavaPackageDao("dao");
            // 将单元测试文件放到哪个包下
            schema.setDefaultJavaPackageTest("test");
            // 初始化数据表
            initUserTable(schema);
            initArticleTable(schema);
            // 开始自动生成代码
            new DaoGenerator().generateAll(schema, "app/src/main/java-gen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initUserTable(Schema schema) {
        user = schema.addEntity("UserBean"); // 表名
        user.setTableName("user"); // 可以对表重命名
        user.addIdProperty().autoincrement();// 添加主键字段
        user.addStringProperty("name"); // 添加一个字符串类型的字段，名为name
        user.addBooleanProperty("gender"); // 添加一个布尔类型的字段，名为gender
        user.addIntProperty("age"); // 添加一个整形字段，名为age
    }

    private static void initArticleTable(Schema schema) {
        article = schema.addEntity("ArticleBean");
        article.setTableName("article");
        article.addIdProperty().autoincrement();
        article.addStringProperty("title");
        // 添加外键user_id，与user表关联，指向user表的主键字段
        Property user_id = article.addLongProperty("user_id").getProperty();
        article.addToOne(user, user_id);
    }
}
