package zxy.permission.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import zxy.permission.entity.RoleResourceRelation;
import zxy.permission.entity.RoleResourceRelationExample;

public interface RoleResourceRelationMapper {
    int countByExample(RoleResourceRelationExample example);

    int deleteByExample(RoleResourceRelationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RoleResourceRelation record);

    int insertSelective(RoleResourceRelation record);

    List<RoleResourceRelation> selectByExampleWithRowbounds(RoleResourceRelationExample example, RowBounds rowBounds);

    List<RoleResourceRelation> selectByExample(RoleResourceRelationExample example);

    RoleResourceRelation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RoleResourceRelation record, @Param("example") RoleResourceRelationExample example);

    int updateByExample(@Param("record") RoleResourceRelation record, @Param("example") RoleResourceRelationExample example);

    int updateByPrimaryKeySelective(RoleResourceRelation record);

    int updateByPrimaryKey(RoleResourceRelation record);
}