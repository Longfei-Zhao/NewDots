/*
  author: Longfei Zhao u5976992
 */
package com.example.zhaolongfei.newdots;

import android.content.Context;
import android.content.res.AssetManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class Utility {
    static Random rand = new Random();
    static int types =6;
    static public int[] creatDots(int m, int n){
        int [] dots = new int[m*n];
        for (int i = 0; i <m*n;i++){
            dots[i]= rand.nextInt(types);
        }
        return dots;
    }
    static String readJson(Context context){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();

            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open("game.json")));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
    static ArrayList<GameType> jsonParse(String json,String key){
        ArrayList<GameType> gameTypeArrayList =new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray(key);
            int length = jsonArray.length();
            for (int i =0;i<length;i++){
                GameType temp = new GameType();
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                temp.setType(jsonObject1.getString("type"));
                temp.setM(jsonObject1.getInt("m"));
                temp.setN(jsonObject1.getInt("n"));
                temp.setRemainValue(jsonObject1.getInt("remain"));
                JSONArray jsonArray1 = jsonObject1.getJSONArray("dots");
                int[] dots = new int[jsonArray1.length()];
                for (int j=0;j<jsonArray1.length();j++){
                    dots[j]=jsonArray1.getInt(j);
                }
                temp.setDots(dots);
                gameTypeArrayList.add(temp);
            }
            return gameTypeArrayList;

        } catch (JSONException e) {
            e.printStackTrace();
        }
    return gameTypeArrayList;}
}
