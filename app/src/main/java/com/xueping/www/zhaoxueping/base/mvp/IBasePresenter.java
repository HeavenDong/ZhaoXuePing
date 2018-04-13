package com.xueping.www.zhaoxueping.base.mvp;

/**
 * 基础执行者
 */
public interface IBasePresenter<V extends IBaseView> {

    /**
     * 附加页面视图
     *
     * @param mvpView 页面视图
     */
    void attachView(V mvpView);

    /**
     * 分离视图
     */
    void detachView();

}
