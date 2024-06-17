package com.example.artchain;

import java.util.ArrayList;

//What is an interface its basically a contract for any class that
// implements it meaning that it makes sure the class has the methods listed here
public interface RecyclerViewInterface {
//    create the methods you want in here

    void onItemClick(ArrayList<String> lis, int position);

}
