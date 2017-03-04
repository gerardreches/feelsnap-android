package com.newproject.jordi.hackupc17;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.newproject.jordi.hackupc17.Entities.UserRanking;

import java.util.ArrayList;

/**
 * Created by jordi on 4/3/17.
 */

public class AdapterRankingList extends ArrayAdapter<UserRanking> {

        public int textViewResourceId;

        AdapterRankingList(Context context, int textViewResourceId, ArrayList<UserRanking> items){
                super(context, textViewResourceId, items);
                this.textViewResourceId=textViewResourceId;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
                View v = convertView;

                if (v == null) {
                        LayoutInflater vi;
                        vi = LayoutInflater.from(getContext());
                        v = vi.inflate(R.layout.cell_user_ranking, null);
                }


                UserRanking userRanking = getItem(position);

                TextView userPosition = (TextView) v.findViewById(R.id.txt_user_position);
                TextView userScore = (TextView) v.findViewById(R.id.txt_user_score);
                TextView userName = (TextView) v.findViewById(R.id.txt_user_name);
                ImageView userFace = (ImageView) v.findViewById(R.id.img_user_face);

                /*userPosition.setText(userRanking.getPosition());
                userScore.setText(userRanking.getScore());
                userName.setText(userRanking.getName());
                userFace.setImageResource(userRanking.getFace());*/

                userPosition.setText(userRanking.getPosition());
                userName.setText(userRanking.getName());
                userScore.setText(userRanking.getScore());
                userFace.setImageResource(userRanking.getFace());

                return v;
        }
        }