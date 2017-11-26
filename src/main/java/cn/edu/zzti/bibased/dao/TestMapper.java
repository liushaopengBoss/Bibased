package cn.edu.zzti.bibased.dao;

import cn.edu.zzti.bibased.pojo.PojoTest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {
    public void insert(PojoTest PojoTest);
    public List<PojoTest> get();
}
