package example.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by jinjw on 2017/5/15.
 */
@Entity
@Table(name = "d_user")
public class User {
    @Id
    @GeneratedValue
    private String userId;
    /**
     * 用户名
     */
    @Column(name = "userName")
    private String userName;

    /**
     * 别名
     */
    @Column(name = "alias")
    private String alias;

    /**
     * 国籍
     */
    @Column(name = "nationality")
    private String nationality;
    /**
     * 出生日期
     */
    @Column(name = "birthday")
    private String birthday;
    /**
     * 身份证号
     */
    @Column(name = "identityCode")
    private String identityCode;
    /**
     * 性别
     */
    @Column(name = "gender")
    private String gender;
    /**
     * 家庭住址
     */
    @Column(name = "address")
    private String address;

    /**
     * 备注
     *
     */
    @Column(name = "bz")
    private String bz;

    /**
     * 更新时间
     *
     */
    @Column(name = "updateTime")
    private Date updateTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }
}
