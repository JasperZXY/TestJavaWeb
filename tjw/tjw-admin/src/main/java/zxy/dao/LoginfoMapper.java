package zxy.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import zxy.entity.Loginfo;
import zxy.entity.LoginfoExample;

public interface LoginfoMapper {
    int countByExample(LoginfoExample example);

    int deleteByExample(LoginfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Loginfo record);

    int insertSelective(Loginfo record);

    List<Loginfo> selectByExampleWithRowbounds(LoginfoExample example, RowBounds rowBounds);

    List<Loginfo> selectByExample(LoginfoExample example);

    Loginfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Loginfo record, @Param("example") LoginfoExample example);

    int updateByExample(@Param("record") Loginfo record, @Param("example") LoginfoExample example);

    int updateByPrimaryKeySelective(Loginfo record);

    int updateByPrimaryKey(Loginfo record);
}