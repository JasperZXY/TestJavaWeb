package zxy.permission.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import zxy.permission.entity.UserRoleRelation;
import zxy.permission.entity.UserRoleRelationExample;

public interface UserRoleRelationMapper {
    int countByExample(UserRoleRelationExample example);

    int deleteByExample(UserRoleRelationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserRoleRelation record);

    int insertSelective(UserRoleRelation record);

    List<UserRoleRelation> selectByExampleWithRowbounds(UserRoleRelationExample example, RowBounds rowBounds);

    List<UserRoleRelation> selectByExample(UserRoleRelationExample example);

    UserRoleRelation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserRoleRelation record, @Param("example") UserRoleRelationExample example);

    int updateByExample(@Param("record") UserRoleRelation record, @Param("example") UserRoleRelationExample example);

    int updateByPrimaryKeySelective(UserRoleRelation record);

    int updateByPrimaryKey(UserRoleRelation record);
}