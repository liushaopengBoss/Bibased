package cn.edu.zzti.bibased.dao.write;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * 用于数据的写入
 */
@Repository
public class LaGouWriteDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public void batchAdd(List<?> list){
        String sql= handleSql(list);
        BatchPreparedStatementSetter pssUpdate = new BatchPreparedStatementSetter() {
            @Override
            public int getBatchSize() {
                return list.size();
            }

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                //设置参数
                list.get(i);
               // ps.setString(1, list.get(i));
                ps.setString(2,"2");
            }
        };
        jdbcTemplate.batchUpdate(sql,pssUpdate);
    }
    public void add(){

    }
    private String handleSql(List<?> list){
        ParameterizedType parameterizedType = (ParameterizedType) list.getClass().getGenericSuperclass();//获取当前new对象的泛型的父类类型

        Class clazz = (Class) parameterizedType.getActualTypeArguments()[0];
        String name = clazz.getName();
        if("PojoTest".equals(name)){
            return "insert into test1 (id,name) values (?,?)";
        }else{
            return "";
        }
    }
}
