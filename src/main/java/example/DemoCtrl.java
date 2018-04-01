package example;

import example.dao.AccountDao;
import example.dao.DriverLicenseDao;
import example.dao.UserDao;
import example.entity.Account;
import example.entity.DriverLicense;
import example.entity.Result;
import example.entity.User;
import example.util.AES;
import example.util.DES;
import example.util.ImgInfo;
import example.util.HashAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by jinjw on 2017/5/12.
 */
@Controller
@RequestMapping(value = "/demo1")
public class DemoCtrl {
    @Autowired
    private UserDao userDao;
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private DriverLicenseDao driverLicenseDao;

    private String t;
    private static String rootKey = "1234567890123456";

    @RequestMapping("/index")
    public String index(){
        System.out.println("index");
        return "index";
    }
    @RequestMapping("/upload")
    @ResponseBody
    public String upload (HttpServletRequest request, HttpServletResponse response,@RequestParam int id, @RequestParam String imgAddress){
//        ImgInfo imgInfo = new ImgInfo(name, id, sa);
        char[] result = new char[100];
        char[] src = imgAddress.toCharArray();

        try {
            int index = 0;
            byte[] receive = new byte[1024];
            int k=2;
            while(k>0){
                Socket sock = new Socket("192.168.88.137", 5050);
                System.out.println("send :  id="+id+"src="+src);
                byte[] t = new ImgInfo(id, src, result).getbuf();
                sock.getOutputStream().write(t);
                sock.getInputStream().read(receive);
                ImgInfo ee = ImgInfo.getEmployee(receive);
                System.out.println("response: id="+ee.getId()+"imgAddress="+ee.getImgAddress());
                k--;
                sock.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "true";
    }

    @RequestMapping("/register")
    @ResponseBody
    public String  register(@RequestBody Account account) throws Exception {
        Account re = accountDao.findByImeiCode(account.getImeiCode());
        if (re != null){
            return "false";
        }else {
            DriverLicense driverLicense = new DriverLicense();
            driverLicense.setDeadLine("2036-07-01");
            driverLicense.setAllowType("C1");
            driverLicense.setStatus("1");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            driverLicense.setUpdateTime(simpleDateFormat.format(new Date()).toString());
            driverLicenseDao.save(driverLicense);
            account.setPassword(HashAlgorithm.sha1_text(account.getPassword()));
            account.setBz("true");
            account.setUpdateTime(simpleDateFormat.format(new Date()).toString());
            accountDao.save(account);
            return "true";
        }

    }

    @RequestMapping("/updateAccount")
    @ResponseBody
    public String updateAccount(@RequestBody Account account) throws Exception {
        Account result = accountDao.findByImeiCode(account.getImeiCode());
            if (result != null){
                result.setPassword(HashAlgorithm.sha1_text(account.getBz()));
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                result.setUpdateTime(simpleDateFormat.format(new Date()).toString());
                accountDao.save(result);
                return "true";
            }else {
                return "false";
            }
    }

    @RequestMapping("/getDriverLicenseInfo")
    @ResponseBody
    public String getDriverLicenseInfo(@RequestBody Account account) throws Exception {
        Account account1 = accountDao.findByImeiCode(account.getImeiCode());
        User user = userDao.findByIdentifyCode(account1.getIdentifyCode());
        DriverLicense driverLicense = driverLicenseDao.findByDNumber(user.getdNumber());
        System.out.println(driverLicense.toString());

        String idA = String.valueOf(driverLicense.getId());
        String str = driverLicense.toString();
//        String DESkey = "1234567890123456";
        String encryptedTitleStr = DES.encryptDES(idA, rootKey);
//        String rootKey = "1234567890123456";//根秘钥
        String diversifyKey = AES.encrypt(idA,rootKey);//分散秘钥
        System.out.println(rootKey);
        System.out.println(diversifyKey);
        String encryptedStr = AES.encrypt(str,diversifyKey);
        System.out.println(encryptedTitleStr + "," + encryptedStr);
        return encryptedTitleStr + "," + encryptedStr;
    }

    @RequestMapping("/getPeccancyInfo")
    @ResponseBody
    public String getPeccancyInfo(@RequestBody Account account) throws Exception {
        String password = HashAlgorithm.sha1_text(account.getPassword());
        Account result = accountDao.findByAccountNameAndPassword(account.getAccountName(), password);
        System.out.println(result.getIdentifyCode());
        User user = userDao.findByIdentifyCode(result.getIdentifyCode());
        DriverLicense driverLicense = driverLicenseDao.findByDNumber(user.getdNumber());
        System.out.println(driverLicense.toString());
        return driverLicense.toString();
    }

    @RequestMapping("/getUserInfo")
    @ResponseBody
    public String getUserInfo(@RequestBody Account account) throws Exception {
        String password = HashAlgorithm.sha1_text(account.getPassword());
        System.out.println(password);
//        Account result = accountDao.findByAccountNameAndPassword("A00000491B777A"); // account.getAccountName(), password
        Account result = accountDao.findByImeiCode("A00000491B777A");
        System.out.println(result.getIdentifyCode());
        User user = userDao.findByIdentifyCode(result.getIdentifyCode());
        System.out.println(user.toString());
        return user.toString();
    }

    @RequestMapping("/updateUserInfo")
    @ResponseBody
    public String updateUserInfo(@RequestBody User user) throws Exception {
        User temp = userDao.findByIdentifyCode(user.getIdentifyCode());
        if (temp != null){
            if(null != user.getTelephone()){
                temp.setTelephone(user.getTelephone());
            }else if(null != user.getEmergency()){
                temp.setEmergency(user.getEmergency());
            }else if(null != user.getEmergencyTel()){
                temp.setEmergencyTel(user.getEmergencyTel());
            }else if(null != user.getAddress()){
                temp.setAddress(user.getAddress());
            }
            userDao.save(temp);
            return "true";
        }else {
            return "false";
        }
    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestBody Account account) throws Exception {
        String sf  = HashAlgorithm.sha1_text(account.getPassword());
        Account re = accountDao.findByAccountNameAndPasswordAndAccountType(account.getAccountName(), sf, account.getAccountType());
        if (re !=null){
            System.out.println("验证成功！");
            return "true";
        }else{
            System.out.println("验证失败！");
            return "false";
        }
    }

    @RequestMapping("/getLicense")
    @ResponseBody
    public Result checkInfo(String content) throws Exception {
        Result result = new Result();
        result.setResultId("00");
        String resultMsg = "";
        String resultStr = "resultId:00";
        StringBuilder status = new StringBuilder("status:");
        StringBuilder pictureUrl = new StringBuilder("pictureUrl:");
        StringBuilder license = new StringBuilder("license:");
        StringBuilder licenseStatus = new StringBuilder("licenseStatus:");
        StringBuilder userInfo = new StringBuilder("userInfo:");
        String[] strings = content.split(",");
/*
        if (strings.length == 3){
            String idA = DES.decryptDES(strings[0], rootKey);
            String timeA = DES.decryptDES(strings[1], rootKey);
//            String rootKey = "1234567890123456";//根秘钥
            String diversifyKey = AES.encrypt(idA,rootKey);//分散秘钥
            String dStr = AES.decrypt(strings[2], diversifyKey);
            String[] strs = dStr.split(",");
            //解析二维码字符串
            //"idAStr（DES,DESKey）,timeAStr(DES,DESKey),encodeStr（AES,diversifyKey）"
            //如果timeA超过阀值，验证未通过，存在截图可能 2
            //判断license字符串跟后台是否一致，不一致验证不通过 3
            //全一致的情况下验证通过 1
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
            String now = (df.format(new Date())).toString();
            if (Integer.parseInt(now) - Integer.parseInt(timeA) < 60){
                DriverLicense driverLicense = driverLicenseDao.findOne(Integer.valueOf(idA));
                license.append(driverLicense.toString1());
                if (driverLicense != null){
                    status.append(1);
                    User user = userDao.findByDNumber(driverLicense.getdNumber());
                    pictureUrl.append(user.getPhoto());
                    userInfo.append(user.toString1());
                }else { //不存在对应的驾驶证信息
                    status.append(3);
                }
            }else {// 超过阀值；可能是截图
                status.append(2);
            }
        }
        */
        resultMsg = status + "," + pictureUrl +","+ license +","+ licenseStatus +","+ userInfo;
        result.setSultMsg(resultMsg);
        return result;
    }

    @RequestMapping("/peccancy")
    @ResponseBody
    public String doPeccancy(String dNumber, String score) throws Exception {
        DriverLicense driverLicense = driverLicenseDao.findOne(Integer.valueOf(dNumber));
        String count = score.substring(1,2);
        if (driverLicense != null){
            if((driverLicense.getScore() + Integer.valueOf(count)) <12){
                driverLicense.setScore(driverLicense.getScore() + Integer.valueOf(count));
            }else {
                driverLicense.setScore(12);
            }
            driverLicense.setScoreDescription(driverLicense.getScoreDescription() +";"+score);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            driverLicense.setUpdateTime(simpleDateFormat.format(new Date()).toString());
            driverLicenseDao.save(driverLicense);
            return "true";
        }else {
            return  "false";
        }
    }

    @RequestMapping("/loginAccount")
    @ResponseBody
    public String loginAccount(@RequestBody Account account) throws Exception {
        String sf  = HashAlgorithm.sha1_text(account.getPassword());
        Account re = accountDao.findByAccountNameAndPasswordAndAccountType(account.getAccountName(), sf, account.getAccountType());
        if (re !=null){ //re.getAcountName().equals(account.getAcountName()) && re.getPassword().equals(sf)
            return "true";
        }else{
            return "false";
        }
    }


    /**
     * 根据id获取数据
     * @param id
     * @return
     */
    @RequestMapping("/getData")
    @ResponseBody
    public String getData(@RequestBody Account account){
        DriverLicense d = driverLicenseDao.findOne(160); // driverLicenseId
        return d.toString();

    }
    @RequestMapping("/refresh")
    @ResponseBody
    public String refresh(@RequestBody Account account){
        List<Account> re = accountDao.findByPinCode(account.getPinCode());
        DriverLicense d = driverLicenseDao.findOne(160);
        User user = userDao.findOne("0001");
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        String time = (df.format(new Date())).toString();
        d.setUserName(user.getUserName());
        d.setGender(user.getGender());
        d.setNationality(user.getNationality());
        d.setBirthday(user.getBirthday().replace("-", ""));
        d.setIssueDate(d.getIssueDate().replace("-", ""));
        d.setStartDate(d.getStartDate().replace("-", ""));
        d.setDeadLine(d.getDeadLine().replace("-", ""));
        d.setUpdateTime(time);
        d.setBz("true");
        String content = d.toString();

        String idA = "160";
        String str = content;
//        String DESkey = "1234567890123456";
        String encryptedTitleStr = DES.encryptDES(idA, rootKey);
//        String rootKey = "1234567890123456";//根秘钥
        String diversifyKey = AES.encrypt(idA,rootKey);//分散秘钥
        String encryptedStr = AES.encrypt(str,diversifyKey);
        return encryptedTitleStr + "," + encryptedStr;
    }
    @RequestMapping("getDeviceInfo")
    @ResponseBody
    public String getDeviceInfo(){
        return "getDeviceInfo";
    }
}