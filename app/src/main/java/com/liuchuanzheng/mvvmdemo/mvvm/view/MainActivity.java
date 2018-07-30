package com.liuchuanzheng.mvvmdemo.mvvm.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.liuchuanzheng.mvvmdemo.R;
import com.liuchuanzheng.mvvmdemo.mvvm.vm.NewsVm;


/**
 * 参考https://www.jianshu.com/p/4830912f5162相关文章
 */
public class MainActivity extends AppCompatActivity {

    private ViewDataBinding viewDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initVM(viewDataBinding);

    }

    /**
     * 初始化vm
     * @param viewDataBinding
     */
    private void initVM(ViewDataBinding viewDataBinding) {
        new NewsVm(this,viewDataBinding);
    }
}
