package cn.edu.zzti.bibased.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用途
 * <p>
 * Created by huaidou on  2018/1/11
 */
@Component
public abstract class AcquisitionDao<T> extends SqlSessionDaoSupport {

    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    protected <S> S getMapper(Class<S> clazz) {
        return getSqlSession().getMapper(clazz);
    }
}