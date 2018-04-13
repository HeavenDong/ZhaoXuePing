/*
 * Copyright 2016 Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xueping.www.zhaoxueping.base.swipelist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xueping.www.zhaoxueping.R;
import com.xueping.www.zhaoxueping.db.User;

import java.util.List;

/**
 * Created by YOLANDA on 2016/7/22.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<User> titles;
    private static int [] colors;
    public MainAdapter(List<User> titles,Context context) {
        this.titles = titles;
        colors=new int[]{context.getResources().getColor(R.color.imback_1),
                context.getResources().getColor(R.color.imback_2),
                context.getResources().getColor(R.color.imback_3),
                context.getResources().getColor(R.color.imback_4)};
    }

    @Override
    public int getItemCount() {
        return titles == null ? 0 : titles.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_main, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(titles.get(position),colors[position%(colors.length)]);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvShenfen;
        View tag;
        public ViewHolder(View itemView) {
            super(itemView);
            tag=itemView.findViewById(R.id.tag);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvShenfen = (TextView) itemView.findViewById(R.id.tv_shenfen);
        }

        public void setData(User user, int color) {
            this.tvName.setText("姓名："+user.getName());
            this.tvShenfen.setText("学号："+user.getCode());
            tag.setBackgroundColor(color);
        }
    }

}
