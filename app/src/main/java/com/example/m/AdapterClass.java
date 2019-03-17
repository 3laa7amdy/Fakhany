package com.example.m;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.m.Model;
import com.example.m.R;

import java.util.ArrayList;

public class AdapterClass extends ArrayAdapter {
    Context context;
    ArrayList<Model> arrayList3;

    public AdapterClass(@NonNull Context context, ArrayList<Model> arrayList) {
        super(context, 0);
        this.arrayList3=arrayList;
        this.context=context;

    }

    @NonNull
    @Override
    public int getCount() {
        return arrayList3.size();

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;
        ListHolder3 listHolder3;
        if(convertView==null){
            LayoutInflater layoutInflater =(LayoutInflater )context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater .inflate(R.layout.singel_row3,parent,false);
            listHolder3 =new ListHolder3(view);
            view.setTag(listHolder3);
        } else {
            listHolder3 =(ListHolder3) view.getTag();
        }
        Model model = arrayList3.get(position);
        listHolder3.image.setImageResource(model.getImage());
        listHolder3.name.setText(model.getName());
        listHolder3.text2.setText(model.getText2());


        return view;

    }

}

class ListHolder3{

    TextView name,text2;
    ImageView image;

    ListHolder3( View view) {
        name=view.findViewById(R.id.text23);
        image=view.findViewById(R.id.image23);
        text2 =view.findViewById(R.id.text24);


    }
}

