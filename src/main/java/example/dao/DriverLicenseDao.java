package example.dao;

import example.entity.DriverLicense;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jinjw on 2017/5/15.
 */
public interface DriverLicenseDao extends CrudRepository<DriverLicense, Integer>{
    DriverLicense findByDNumber(String dNumber);
}
