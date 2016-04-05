package com.qicfix.qicfixapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import com.qicfix.qicfixapp.R;
import com.qicfix.qicfixapp.entity.Serviceman;

import java.util.List;

/**
 * Created by Juan on 2/9/2016.
 */
public class ServicemanAdapter extends ArrayAdapter<Serviceman> {
    Context context;
    int layoutResourceId;
    List<Serviceman> data;

    public ServicemanAdapter(Context context, int layoutResourceId, List<Serviceman> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    static class ViewHolder {
        CheckBox checkBox;
        TextView txtId;
        TextView txtInfo;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layoutResourceId, parent, false);

            holder = new ViewHolder();
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.chbNearest);
            holder.txtId = (TextView) convertView.findViewById(R.id.txtIdserviceman);
            holder.txtInfo = (TextView) convertView.findViewById(R.id.txtInfo);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (data.size() > 0) {
            if (holder.checkBox != null) {
                Serviceman obj = data.get(position);
                holder.txtId.setText(obj.getIdserviceman());
                holder.checkBox.setText(obj.getName() + " - " + obj.getAddress());
                holder.txtInfo.setText("Info");
            }

        }

        return convertView;
    }

}
