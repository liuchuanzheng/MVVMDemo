package com.liuchuanzheng.mvvmdemo.mvvm.vm;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.view.View;
import android.widget.Toast;

import com.liuchuanzheng.mvvmdemo.BR;
import com.liuchuanzheng.mvvmdemo.mvvm.model.NewsModel;
import com.liuchuanzheng.mvvmdemo.mvvm.view.MainActivity;

/**
 * @author 刘传政
 * @date 2018/7/30 0030 16:11
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
public class NewsVm implements LoadListener{
    private ViewDataBinding viewDataBinding;
    private Context context;

    public NewsVm(MainActivity mainActivity, ViewDataBinding viewDataBinding) {
        context = mainActivity;
        this.viewDataBinding = viewDataBinding;
        doAction();
    }

    private void doAction() {
        viewDataBinding.setVariable(BR.content,"我是初始值");
        viewDataBinding.setVariable(BR.helper,this);
    }
    //点击事件。通过databinding绑定
    public void buttonClick(View view){
        Toast.makeText(context,"点击了",Toast.LENGTH_SHORT).show();
        NewsModel newsModel = new NewsModel(context,this);
        newsModel.load();
    }

    @Override
    public void success(String s) {
        //通过databinding更新数据。
        //从头到尾没操作过view层
        viewDataBinding.setVariable(BR.content,s);
    }
}
