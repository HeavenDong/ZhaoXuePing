package com.xueping.www.zhaoxueping.db;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Id;

import com.xueping.www.zhaoxueping.db.gen.DaoSession;
import com.xueping.www.zhaoxueping.db.gen.ActionsDao;

import java.io.Serializable;

/**
 * 作者：Created by BarryDong on 2017/9/11.
 * 邮箱：barry.dong@tianyitechs.com
 *
 * @Generated 这个是build后greendao自动生成的，这个注解理解为防止重复，每一块代码生成后会加个hash作为标记。
 * 官方不建议你去碰这些代码，改动会导致里面代码与hash值不符。
 */
@Entity(//@Entity 用于标识这是一个需要Greendao帮我们生成代码的bean

        //schema = "myschema",// schema 名，多个 schema 时设置关联实体。插件产生不支持，需使用产生器

        active = true,// 标记一个实体是否处于活动状态，活动实体有 update、delete、refresh 方法。默认为 false

        nameInDb = "action", // 表名，默认为类名

        //indexes = {@Index(value = "uid ASC", unique = true)}, // 定义多列索引

        createInDb = true,// 标记是否创建表，默认 true。多实体对应一个表或者表已创建，不需要 greenDAO 创建时设置 false

        generateConstructors = true,// 是否产生所有参数构造器。默认为 true。无参构造器必定产生

        generateGettersSetters = true// 如果没有 get/set 方法，是否生成。默认为 true
)
public class Actions implements Parcelable {
    private long ActionID;
    private String ActionName;
    private String ActionTime;
    private String ActionContent;


    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 130110194)
    private transient ActionsDao myDao;
    @Generated(hash = 2067711624)
    public Actions(long ActionID, String ActionName, String ActionTime,
            String ActionContent) {
        this.ActionID = ActionID;
        this.ActionName = ActionName;
        this.ActionTime = ActionTime;
        this.ActionContent = ActionContent;
    }
    @Generated(hash = 661962658)
    public Actions() {
    }

    protected Actions(Parcel in) {
        ActionID = in.readLong();
        ActionName = in.readString();
        ActionTime = in.readString();
        ActionContent = in.readString();
    }

    public static final Creator<Actions> CREATOR = new Creator<Actions>() {
        @Override
        public Actions createFromParcel(Parcel in) {
            return new Actions(in);
        }

        @Override
        public Actions[] newArray(int size) {
            return new Actions[size];
        }
    };

    public long getActionID() {
        return this.ActionID;
    }
    public void setActionID(long ActionID) {
        this.ActionID = ActionID;
    }
    public String getActionName() {
        return this.ActionName;
    }
    public void setActionName(String ActionName) {
        this.ActionName = ActionName;
    }
    public String getActionTime() {
        return this.ActionTime;
    }
    public void setActionTime(String ActionTime) {
        this.ActionTime = ActionTime;
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 539748487)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getActionsDao() : null;
    }
    public String getActionContent() {
        return this.ActionContent;
    }
    public void setActionContent(String ActionContent) {
        this.ActionContent = ActionContent;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(ActionID);
        dest.writeString(ActionName);
        dest.writeString(ActionTime);
        dest.writeString(ActionContent);
    }
}