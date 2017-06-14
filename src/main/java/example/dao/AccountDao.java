package example.dao;

import example.entity.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by jinjw on 2017/5/15.
 */
public interface AccountDao extends CrudRepository<Account, String>{
    List<Account> findByImeiCode(String imeiCode);
}
