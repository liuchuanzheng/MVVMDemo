package com.liuchuanzheng.mvvmdemo.mvvm.retrofit_service;

import com.liuchuanzheng.mvvmdemo.mvvm.model.bean.NewsBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author 刘传政
 * @date 2018/7/30 0030 16:47
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
public interface RetrofitService {

    //获取“分类中搜索商品”的数据
    @GET("api/4/themes")
    Observable<NewsBean> getNewsData();
}
