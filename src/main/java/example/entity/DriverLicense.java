package example.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by jinjw on 2017/5/15.
 */
@Entity
@Table(name = "driver_license")
public class DriverLicense {

    @Id
    @GeneratedValue
    private int id;
    /**
     * 用户名
     */
    @Column(name = "userName")
    private String userName;
    /**
     * 性别
     */
    @Column(name = "gender")
    private String gender;
    /**
     * 国籍
     */
    @Column(name = "nationality")
    private String nationality;
    /**
     * 家庭住址
     */
    @Column(name = "address")
    private String address;
    /**
     * 出生日期
     */
    @Column(name = "birthday")
    private String birthday;

    /**
     * 驾驶证编号
     */
    @Column(name = "dNumber")
    private String dNumber;
    /**
     * 有截止日期
     */
    @Column(name = "deadLine")
    private String deadLine;
    /**
     * 驾照状态
     */
    @Column(name = "status")
    private String status; //  0：实习；1：有效; 2:扣留
    /**
     * 准驾车型
     */
    @Column(name = "allowType")
    private String allowType;// 准驾车型
    /**
     * 备注
     *
     */
    @Column(name = "bz")
    private String bz;

    /**
     * 初次领证日期
     */
    @Column(name = "issueDate")
    private String issueDate;

    /**
     * 发证机关
     */
    @Column(name = "fzjg")
    private String fzjg;

    /**
     * 更新时间
     *
     */
    @Column(name = "updateTime")
//    @GenericGenerator(name="generator", strategy=)
    private Date updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAllowType() {
        return allowType;
    }

    public void setAllowType(String allowType) {
        this.allowType = allowType;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getdNumber() {
        return dNumber;
    }

    public void setdNumber(String dNumber) {
        this.dNumber = dNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getFzjg() {
        return fzjg;
    }

    public void setFzjg(String fzjg) {
        this.fzjg = fzjg;
    }

    @Override
    public String toString() {
        return "DriverLicense{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", gender='" + gender + '\'' +
                ", nationality='" + nationality + '\'' +
                ", address='" + address + '\'' +
                ", birthday='" + birthday + '\'' +
                ", dNumber='" + dNumber + '\'' +
                ", deadLine='" + deadLine + '\'' +
                ", status='" + status + '\'' +
                ", allowType='" + allowType + '\'' +
                ", bz='" + bz + '\'' +
                ", issueDate='" + issueDate + '\'' +
                ", fzjg='" + fzjg + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
