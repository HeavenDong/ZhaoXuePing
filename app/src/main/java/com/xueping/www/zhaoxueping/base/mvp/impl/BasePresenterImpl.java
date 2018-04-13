package com.xueping.www.zhaoxueping.base.mvp.impl;


import com.xueping.www.zhaoxueping.base.mvp.IBasePresenter;
import com.xueping.www.zhaoxueping.base.mvp.IBaseView;

import io.reactivex.disposables.CompositeDisposable;


/**
 * 基础实现的执行者
 */
public class BasePresenterImpl<V extends IBaseView> implements IBasePresenter<V> {

    private V mView;
    public CompositeDisposable mCompositeDisposable;

    public BasePresenterImpl() {
        this.mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void attachView(V mView) {
        this.mView = mView;
    }

    @Override
    public void detachView() {
        this.mView = null;
        if (this.mCompositeDisposable != null) {
            this.mCompositeDisposable.clear();
        }
        this.mCompositeDisposable = null;
    }

    public boolean viewIsAttached() {
        return mView != null;
    }

    public V getView() {
        return mView;
    }

    public void checkViewAttached() {
        if (!viewIsAttached()) {
            throw new MvpViewNotAttachedException();
        }
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call IPresenter.attachView(IView) before requesting data to the IPresenter");
        }
    }
}
