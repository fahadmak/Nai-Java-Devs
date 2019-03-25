package com.example.myapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class UsersAdapter extends ArrayAdapter {

    private final Activity context;

    private final String[] userNameArray;

    private final Integer[] imageIdArray;

    public UsersAdapter(Activity context, String[] userNameArray, Integer[] imageIdArray){
        super(context, R.layout.list_item, userNameArray);

        this.context=context;
        this.imageIdArray = imageIdArray;
        this.userNameArray = userNameArray;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View row = inflater.inflate(R.layout.list_item, null, true);

        TextView usernameTextField = row.findViewById(R.id.profile_name);
        ImageView imageView = row.findViewById(R.id.profile_image);

        usernameTextField.setText(userNameArray[position]);
        imageView.setImageResource(imageIdArray[position]);

        return row;

    }


}
