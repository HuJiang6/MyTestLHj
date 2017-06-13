package com.lhj.mytestlhj;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.lhj.lhjuilibrary.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    //菜单标题
    private String headers[] = {"区域", "租金", "来源", "更多"};
    private ListView listView1;
    private ListView listView2;
    private ListView listView3;
    private View viewZJ;
    private View viewGD;
    private MenuListAdapter mMenuAdapter1;
    //    private MenuListAdapter mMenuAdapter2;
    private MenuListAdapter mMenuAdapter3;
//    private MenuListAdapter mMenuAdapter4;

    private DropDownMenu mDropDownMenu;

    private String citys[] = {"不限", "云岩", "南明"};
//    private String citys[] = {"不限", "武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州"};

//    private String ages[] = {"不限", "0-500", "500-1000"};
//    private String ages[] = {"不限", "18岁以下", "18-22岁", "23-26岁", "27-35岁", "35岁以上"};

    private String sexs[] = {"不限", "个人房源", "经纪人", "其他"};
//    private String sexs[] = {"不限", "男", "女"};

    private List<View> popupViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initView();
    }

    private void initView() {

        mDropDownMenu = (DropDownMenu) findViewById(R.id.dropDownMenu);


        //init menu listview

        //这里是每个下拉菜单之后的布局,目前只是简单的listview作为展示
        listView1 = new ListView(MainActivity.this);
        listView2 = new ListView(MainActivity.this);
        listView3 = new ListView(MainActivity.this);

        listView1.setDividerHeight(0);
        listView2.setDividerHeight(0);
        listView3.setDividerHeight(0);

        mMenuAdapter1 = new MenuListAdapter(MainActivity.this, Arrays.asList(citys));
//        mMenuAdapter2 = new MenuListAdapter(MainActivity.this, Arrays.asList(ages));
        mMenuAdapter3 = new MenuListAdapter(MainActivity.this, Arrays.asList(sexs));

        listView1.setAdapter(mMenuAdapter1);
//        listView2.setAdapter(mMenuAdapter2);
        listView3.setAdapter(mMenuAdapter3);

        popupViews.add(listView1);
//        popupViews.add(listView2);
        viewzj();
        popupViews.add(listView3);
        viewgd();

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                mDropDownMenu.setTabText(citys[position]);
                mDropDownMenu.closeMenu();
            }
        });

//        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//
//                mDropDownMenu.setTabText(ages[position]);
//                mDropDownMenu.closeMenu();
//            }
//        });

        listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                mDropDownMenu.setTabText(sexs[position]);
                mDropDownMenu.closeMenu();
            }
        });


        //这里添加 内容显示区域,可以是任何布局
        TextView contentView = new TextView(this);
        contentView.setText("这里是内容区域");
        contentView.setTextSize(20);
        contentView.setGravity(Gravity.CENTER);


        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews,contentView );

    }

    private void viewzj() {
        viewZJ = LayoutInflater.from(this).inflate(R.layout.item_popupwindow_zj, null);
        final EditText editTextqs = (EditText) viewZJ.findViewById(R.id.et_qs);
        final EditText editTextjs = (EditText) viewZJ.findViewById(R.id.et_js);
        Button textView = (Button) viewZJ.findViewById(R.id.bt_sure);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editTextqs.getText().toString();
                String s1 = editTextjs.getText().toString();
                mDropDownMenu.setTabText(s+s1);
                mDropDownMenu.closeMenu();
            }
        });

        popupViews.add(viewZJ);
    }

    private void viewgd () {
        viewGD = LayoutInflater.from(this).inflate(R.layout.item_popupwindow_gd, null);
        final RadioButton radioButton = (RadioButton) viewGD.findViewById(R.id.rb_zz);
        final RadioButton radioButton1 = (RadioButton) viewGD.findViewById(R.id.rb_hz);
        TextView textView = (TextView) viewGD.findViewById(R.id.tv_qiedin);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioButton.isChecked()) {
                    mDropDownMenu.setTabText("整租");
                }
                if (radioButton1.isChecked()) {
                    mDropDownMenu.setTabText("合租");
                }
                mDropDownMenu.closeMenu();
            }
        });
        popupViews.add(viewGD);
    }
}
