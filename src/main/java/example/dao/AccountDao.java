package example.dao;

import example.entity.Account;
import example.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by jinjw on 2017/5/15.
 */
public interface AccountDao extends CrudRepository<Account, String>{
    Account findByImeiCode(String imeiCode);
    List<Account> findByPinCode(String pinCode);
    Account findByAccountNameAndPassword(String accountName, String password);

    Account findByAccountNameAndPasswordAndAccountType(String accountName, String password, String type);
}
