package com.sergiocruz.mostpopularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sergio on 20/02/2018.
 */

public class ListPopupWindowAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<MainActivity.MenuOptions> menuOptions;

    public ListPopupWindowAdapter(Context context, ArrayList<MainActivity.MenuOptions> menuOptions) {
        this.context = context;
        this.menuOptions = menuOptions;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Boolean hasMenuIndicator = menuOptions.get(position).getHasIndicator();

        // Small List View, no need to recycle views
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Layout for the top row with profile picture /Avatar
        convertView = inflater.inflate(R.layout.custom_menu_item_layout, parent, false);

        RadioButton menu_indicator = convertView.findViewById(R.id.radio_popular);
        if (hasMenuIndicator) {
            menu_indicator.setChecked(true);
        } else {
            menu_indicator.setChecked(true);
        }
        RadioButton menu_icon = convertView.findViewById(R.id.radio_popular);
        menu_icon.setChecked(true);

        TextView menuTextView = convertView.findViewById(R.id.menu_textView_0);
        menuTextView.setText(menuOptions.get(position).getMenuText());

        return convertView;
    }

    @Override
    public Object getItem(int index) {
        return menuOptions.get(index);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return menuOptions.size();
    }

}