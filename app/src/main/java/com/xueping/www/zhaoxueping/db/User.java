package com.xueping.www.zhaoxueping.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;

import com.xueping.www.zhaoxueping.db.gen.DaoSession;
import com.xueping.www.zhaoxueping.db.gen.UserDao;

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

        nameInDb = "dafeng", // 表名，默认为类名

        //indexes = {@Index(value = "uid ASC", unique = true)}, // 定义多列索引

        createInDb = true,// 标记是否创建表，默认 true。多实体对应一个表或者表已创建，不需要 greenDAO 创建时设置 false

        generateConstructors = true,// 是否产生所有参数构造器。默认为 true。无参构造器必定产生

        generateGettersSetters = true// 如果没有 get/set 方法，是否生成。默认为 true
)
public class User {
//    @Id(autoincrement = true)//@Id 标明主键，括号里可以指定是否自增(这里有问题，放开后，一直是替换)

private long id;
    @Property(nameInDb = "USERNAME")//@Property 用于设置属性在数据库中的列名（默认不写就是保持一致）
    @NotNull //非空
    private String name;
    @Id
    private String code;
    private String time;
    private String avatarUrl;

    private int gender;
    private String mobile;
    private String identity;
    private String description;
    @Transient  //标识这个字段是自定义的不会创建到数据库表里
    private String test;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1507654846)
    private transient UserDao myDao;
    @Generated(hash = 1030981996)
    public User(long id, @NotNull String name, String code, String time, String avatarUrl,
            int gender, String mobile, String identity, String description) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.time = time;
        this.avatarUrl = avatarUrl;
        this.gender = gender;
        this.mobile = mobile;
        this.identity = identity;
        this.description = description;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getGender() {
        return this.gender;
    }
    public void setGender(int gender) {
        this.gender = gender;
    }
    public String getMobile() {
        return this.mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getIdentity() {
        return this.identity;
    }
    public void setIdentity(String identity) {
        this.identity = identity;
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
    @Generated(hash = 2059241980)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUserDao() : null;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getCode() {
        return this.code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getAvatarUrl() {
        return this.avatarUrl;
    }
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}