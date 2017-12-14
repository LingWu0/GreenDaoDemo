package com.example.xxsj.greendaodemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import bean.UserBean;

/**
 * Created by xxsj on 2017/12/11.
 */

public class UserAdapter extends BaseAdapter {

    private List list;
    private LayoutInflater inflater;

    public UserAdapter(Context context, List list) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.listitem_users, parent, false);
            holder.name = (TextView) convertView.findViewById(R.id.id_useritem_name);
            holder.gender = (TextView) convertView.findViewById(R.id.id_useritem_gender);
            holder.age = (TextView) convertView.findViewById(R.id.id_useritem_age);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        UserBean user = (UserBean) getItem(position);
        holder.name.setText(user.getName());
        holder.gender.setText(user.getGender() == true ? "男" : "女");
        holder.age.setText(user.getAge() + "");
        return convertView;
    }

    private static class ViewHolder {
        TextView name;
        TextView gender;
        TextView age;
    }
}
