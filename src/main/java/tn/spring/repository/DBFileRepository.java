package tn.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.spring.entity.DBFile;

@Repository
public interface DBFileRepository  extends CrudRepository<DBFile, Long>{

}
