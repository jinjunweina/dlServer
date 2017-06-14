package example.dao;

import example.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jinjw on 2017/5/15.
 */
public interface UserDao extends CrudRepository<User, String> {
}
