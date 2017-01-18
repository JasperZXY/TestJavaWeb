package zxy.permission.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import zxy.permission.entity.UserGroupRelation;
import zxy.permission.entity.UserGroupRelationExample;

public interface UserGroupRelationMapper {
    int countByExample(UserGroupRelationExample example);

    int deleteByExample(UserGroupRelationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserGroupRelation record);

    int insertSelective(UserGroupRelation record);

    List<UserGroupRelation> selectByExampleWithRowbounds(UserGroupRelationExample example, RowBounds rowBounds);

    List<UserGroupRelation> selectByExample(UserGroupRelationExample example);

    UserGroupRelation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserGroupRelation record, @Param("example") UserGroupRelationExample example);

    int updateByExample(@Param("record") UserGroupRelation record, @Param("example") UserGroupRelationExample example);

    int updateByPrimaryKeySelective(UserGroupRelation record);

    int updateByPrimaryKey(UserGroupRelation record);
}