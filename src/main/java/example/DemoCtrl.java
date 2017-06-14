package example;

import ch.qos.logback.core.net.SyslogOutputStream;
import example.dao.AccountDao;
import example.dao.DriverLicenseDao;
import example.dao.UserDao;
import example.entity.Account;
import example.entity.DriverLicense;
import example.entity.User;
import example.util.AES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by jinjw on 2017/5/12.
 */
@RestController
@RequestMapping(value = "/demo1")
public class DemoCtrl {
    @Autowired
    private UserDao userDao;
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private DriverLicenseDao driverLicenseDao;

    private String t;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    @RequestMapping("/register")
    @ResponseBody
    public Account register(@RequestBody Account account){
        List<Account> re = accountDao.findByImeiCode(account.getImeiCode());
        DriverLicense driverLicense = new DriverLicense();
//        driverLicense.setId("001");
        driverLicense.setdNumber("610581198909294115");
        driverLicense.setDeadLine("2036-07-01");
        driverLicense.setAllowType("C1 C2 B1");
        driverLicense.setStatus("1");
//        driverLicense.setuTime();

        driverLicenseDao.save(driverLicense);
        if (re.size() == 0){
            account.setBz("true");
            return accountDao.save(account);
        }else {
            account.setBz("false");
            return account;
        }
    }
    @RequestMapping("/update")
    public Account update(@RequestBody Account account){
        return accountDao.save(account);
    }
    @RequestMapping("/login")
    @ResponseBody
    public DriverLicense login(@RequestBody Account account){
        Account a= accountDao.findOne(account.getAccountId());
        List<Account> re = accountDao.findByImeiCode(account.getImeiCode());
        DriverLicense d = driverLicenseDao.findOne(160);
        User user = userDao.findOne("0001");
        d.setdNumber(user.getIdentityCode());
        d.setUserName(user.getUserName());
        d.setAddress(user.getAddress());
        d.setGender(user.getGender());
        d.setBirthday(user.getBirthday());
        d.setNationality(user.getNationality());
        d.setBz("true");
        return d;
    }
    @RequestMapping("/refresh")
    public String refresh(@RequestBody Account account){
        List<Account> re = accountDao.findByImeiCode(account.getImeiCode());
        DriverLicense d = driverLicenseDao.findOne(160);
        User user = userDao.findOne("0001");
        d.setUserName(user.getUserName());
        d.setAddress(user.getAddress());
        d.setGender(user.getGender());
//        d.setBirthday(user.getBirthday());
        d.setNationality(user.getNationality());
        d.setBz("true");
        String content = d.toString();
        String password = "123";
        System.out.println("content:" + content);
        byte[] encrypt = AES.encrypt(content, password);
        String hex = AES.parseByte2HexStr(encrypt);
        System.out.println(hex);
        byte[] dhex = AES.parseHexStr2Byte(hex);
        byte[] decrypt = AES.decrypt(dhex, password);
        System.out.println("decrypt:" + new String(decrypt));
        return hex;
    }
    @RequestMapping("getDeviceInfo")
    public String getDeviceInfo(){
        return "getDeviceInfo";
    }
}
