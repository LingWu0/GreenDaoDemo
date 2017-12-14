package com.example.xxsj.greendaodemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import bean.UserBean;

public class MainActivity extends AppCompatActivity {

    //控件
    private Button add;
    private ListView lv;

    private MyDaoHelper userDao;

    private List userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 初始化控件
        add = (Button) findViewById(R.id.id_main_btn_add);
        lv = (ListView) findViewById(R.id.id_main_lv_lv);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initBDTables();
        initViews();
        initEvents();
    }

    private void initBDTables() {
        // 初始化两个Dao类
        userDao = MyDaoHelper.getInstance(MainActivity.this);
        // 删除数据表中的所有数据
        userDao.deleteAll();
        // 向用户表中添加数据
        userDao.addData(new UserBean(1L, "张三", true, 20));
        userDao.addData(new UserBean(2L, "李四", false, 21));
        userDao.addData(new UserBean(3L, "王五", true, 22));
    }

    private void initViews() {
        // 从数据库中取出所有用户信息
        userList = userDao.getAllData();
        // 适配ListView数据
        UserAdapter adapter = new UserAdapter(MainActivity.this, userList);
        lv.setAdapter(adapter);
    }

    private void initEvents() {
        // 点击add按钮（添加）时触发的事件
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 添加一条数据
                userDao.addData(new UserBean(((UserBean) userList.get(userList.size() - 1)).getId() + 1, "新用户",
                        ((int) (Math.random() * 1000 + 1)) % 2 == 0 ? true : false, (int) (Math.random() * 20 + 20)));
                initViews();
            }
        });
        // 当长按ListView中的某一项时触发的事件
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // 删除长按的Item
                userDao.deleteData(((UserBean) userList.get(position)).getId());
                initViews();
                return true;
            }
        });
    }
}
