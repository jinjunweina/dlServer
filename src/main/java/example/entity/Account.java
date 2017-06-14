package example.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by jinjw on 2017/5/12.
 */
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(generator="generator")
    @GenericGenerator(name="generator", strategy="uuid")
    private String accountId;
    /**
     *账户名
     */
    @Column(name = "accountName")
    private String acountName;
    /**
     *别名
     */
    @Column(name = "alias")
    private String alias;
    /**
     *密码
     */
    @Column(name = "password")
    private String password;
    /**
     * PIN码
     */
    @Column(name = "pinCode")
    private String pinCode;
    /**
     * IMEI号
     */
    @Column(name = "imeiCode")
    private String imeiCode;
    /**
     * 联系电话
     */
    @Column(name = "phoneNumber")
    private String phoneNumber;
    /**
     * 验证码
     */
    @Column(name = "identifyCode")
    private String identifyCode;

    /**
     * 备注
     *
     */
    @Column(name = "bz")
    private String bz;

    /**
     * 状态
     *
     */
    @Column(name = "zt")
    private String zt;
    /**
     * 更新时间
     *
     */
    @Column(name = "updateTime")
    private Date updateTime;

    public String getAcountName() {
        return acountName;
    }

    public void setAcountName(String acountName) {
        this.acountName = acountName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getImeiCode() {
        return imeiCode;
    }

    public void setImeiCode(String imeiCode) {
        this.imeiCode = imeiCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdentifyCode() {
        return identifyCode;
    }

    public void setIdentifyCode(String identifyCode) {
        this.identifyCode = identifyCode;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
