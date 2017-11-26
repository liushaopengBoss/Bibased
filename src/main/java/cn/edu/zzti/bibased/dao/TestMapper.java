package cn.edu.zzti.bibased.dao;

import cn.edu.zzti.bibased.pojo.PojoTest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TestMapper {

    public void insert(PojoTest PojoTest);

    public List<PojoTest> get();
}
