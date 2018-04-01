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
     * 档案编号
     */
    @Column(name = "dNumber")
    private String dNumber;
    /**
     * 照片信息
     */
    @Column(name = "image")
    private String image;

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
     * 有效起始日期
     */
    @Column(name = "startDate")
    private String startDate;

    /**
     * 发证机关
     */
    @Column(name = "fzjg")
    private String fzjg;
    /**
     * 记分分值
     */
    @Column(name = "jffz")
    private int score;

    /**
     * 违章说明
     */
    @Column(name = "wzsm")
    private String scoreDescription;
    /**
     * 事故记录
     */
    @Column(name = "sgjl")
    private String sgjl;
    /**
     * 违法记录
     */
    @Column(name = "wfjl")
    private String wfjl;

    /**
     * 更新时间
     *
     */
    @Column(name = "updateTime")
//    @GenericGenerator(name="generator", strategy=)
    private String updateTime;

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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getFzjg() {
        return fzjg;
    }

    public void setFzjg(String fzjg) {
        this.fzjg = fzjg;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getdNumber() {
        return dNumber;
    }

    public void setdNumber(String dNumber) {
        this.dNumber = dNumber;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSgjl() {
        return sgjl;
    }

    public void setSgjl(String sgjl) {
        this.sgjl = sgjl;
    }

    public String getWfjl() {
        return wfjl;
    }

    public void setWfjl(String wfjl) {
        this.wfjl = wfjl;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getScoreDescription() {
        return scoreDescription;
    }

    public void setScoreDescription(String scoreDescription) {
        this.scoreDescription = scoreDescription;
    }

    //    @Override
//    public String toString() {
//        return userName  +
//                "," + gender +
//                "," + nationality +
//                "," + dNumber +
//                "," + birthday +
//                "," + issueDate +
//                "," + startDate +
//                "," + deadLine +
//                "," + allowType +
//                "," + fzjg +
//                "," + image +
//                "," + status +
//                "," + bz +
//                "," + updateTime;
//    }


    @Override
    public String toString() {
        return userName +
                "," + gender +
                "," + nationality +
                "," + dNumber +
                "," + birthday +
                "," + issueDate +
                "," + startDate +
                "," + deadLine +
                "," + allowType +
                "," + fzjg +
                "," + image +
                "," + status +
                "," + bz +
                "," + updateTime +
                "," + score +
                "," + sgjl +
                "," + wfjl +
                "," + scoreDescription;
    }
    public String toString1() {
        return userName +
                "," + gender +
                "," + nationality +
                "," + dNumber +
                "," + birthday +
                "," + issueDate +
                "," + startDate +
                "," + deadLine +
                "," + allowType +
                "," + fzjg +
                "," + updateTime;
    }
}
