package zxy.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import zxy.entity.Account;
import zxy.entity.AccountExample;

public interface AccountMapper {
    int countByExample(AccountExample example);

    int deleteByExample(AccountExample example);

    int deleteByPrimaryKey(String id);

    int insert(Account record);

    int insertSelective(Account record);

    List<Account> selectByExampleWithRowbounds(AccountExample example, RowBounds rowBounds);

    List<Account> selectByExample(AccountExample example);

    Account selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Account record, @Param("example") AccountExample example);

    int updateByExample(@Param("record") Account record, @Param("example") AccountExample example);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
}