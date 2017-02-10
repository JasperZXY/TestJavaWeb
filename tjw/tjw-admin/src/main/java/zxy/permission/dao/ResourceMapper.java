package zxy.permission.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import zxy.permission.entity.Resource;
import zxy.permission.entity.ResourceExample;

public interface ResourceMapper {
    int countByExample(ResourceExample example);

    int deleteByExample(ResourceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Resource record);

    int insertSelective(Resource record);

    List<Resource> selectByExampleWithRowbounds(ResourceExample example, RowBounds rowBounds);

    List<Resource> selectByExample(ResourceExample example);

    Resource selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Resource record, @Param("example") ResourceExample example);

    int updateByExample(@Param("record") Resource record, @Param("example") ResourceExample example);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);
}