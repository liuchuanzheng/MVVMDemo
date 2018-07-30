package com.liuchuanzheng.mvvmdemo.mvvm.model;

import android.content.Context;

import com.liuchuanzheng.mvvmdemo.mvvm.model.bean.NewsBean;
import com.liuchuanzheng.mvvmdemo.mvvm.retrofit_service.RetrofitService;
import com.liuchuanzheng.mvvmdemo.mvvm.vm.LoadListener;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author 刘传政
 * @date 2018/7/30 0030 16:10
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
public class NewsModel {
    private Context context;
    private LoadListener loadListener;
    private Retrofit retrofit;
    private static final int DEFAULT_TIMEOUT = 8; //连接 超时的时间，单位：秒
    private static final OkHttpClient client = new OkHttpClient.Builder().
            connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS).
            readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS).
            writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS).build();
    private RetrofitService retrofitService;

    public NewsModel(Context context, LoadListener loadListener) {
        this.context = context;
        this.loadListener = loadListener;
    }

    public void load() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://news-at.zhihu.com/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            retrofitService = retrofit.create(RetrofitService.class);
        }
        retrofitService.getNewsData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NewsBean newsBean) {
                        paseData(newsBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void paseData(NewsBean newsBean) {
        String s = newsBean.getOthers().get(0).getName();
        loadListener.success(s);
    }
}
