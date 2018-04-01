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
    @Column(name = "identifyCode")
    private String identifyCode;
    /**
     * 护照号码
     */
    @Column(name = "passportCode")
    private String passportCode;
    /**
     * 档案编号
     */
    @Column(name = "dNumber")
    private String dNumber;
    /**
     * 性别
     */
    @Column(name = "gender")
    private String gender;
    /**
     * 照片
     */
    @Column(name = "photo")
    private String photo;
    /**
     * 家庭住址
     */
    @Column(name = "address")
    private String address;
    /**
     * 认证手机号 一般不可更改，如需更改要到车管所面签
     */
    @Column(name = "tel")
    private String tel;
    /**
     * 联系电话
     */
    @Column(name = "telephone")
    private String telephone;

    /**
     * 紧急联系人
     */
    @Column(name = "jjlxr")
    private String emergency;
    /**
     * 紧急联系人电话
     */
    @Column(name = "jjlxrdh")
    private String emergencyTel;

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

    public String getIdentifyCode() {
        return identifyCode;
    }

    public void setIdentifyCode(String identifyCode) {
        this.identifyCode = identifyCode;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getdNumber() {
        return dNumber;
    }

    public void setdNumber(String dNumber) {
        this.dNumber = dNumber;
    }

    public String getEmergency() {
        return emergency;
    }

    public void setEmergency(String emergency) {
        this.emergency = emergency;
    }

    public String getEmergencyTel() {
        return emergencyTel;
    }

    public void setEmergencyTel(String emergencyTel) {
        this.emergencyTel = emergencyTel;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassportCode() {
        return passportCode;
    }

    public void setPassportCode(String passportCode) {
        this.passportCode = passportCode;
    }

    @Override
    public String toString() {
        return userId +
                "," + userName +
                "," + alias +
                "," + nationality +
                "," + birthday +
                "," + identifyCode +
                "," + passportCode +
                "," + dNumber +
                "," + gender +
                "," + photo +
                "," + address +
                "," + tel +
                "," + telephone +
                "," + emergency +
                "," + emergencyTel +
                "," + bz +
                "," + updateTime;
    }
    public String toString1() {
        return userName +
                "," + tel +
                "," + telephone +
                "," + emergency +
                "," + emergencyTel +
                "," + address;
    }
}
