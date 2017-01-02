package zxy.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zxy.constants.EntityStatus;
import zxy.dao.UserMapper;
import zxy.entity.User;
import zxy.entity.UserExample;
import zxy.utils.Utils;

import java.util.Date;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    /**
     * 添加用户
     * @param user
     * @return 如果失败，这里返回失败的原因
     */
    public String add(User user) {
        if (user == null) {
            return "user不能为空";
        }
        if (StringUtils.isBlank(user.getAccount())) {
            return "账号不能为空";
        }

        UserExample example = new UserExample();
        example.createCriteria().andAccountEqualTo(user.getAccount());
        if (userMapper.countByExample(example) > 0) {
            return "账号已存在";
        }
        user.setStatus(EntityStatus.VALID);
        user.setCreatetime(new Date());
        userMapper.insert(user);

        return null;
    }

    public String update(User user) {
        if (user == null) {
            return "user不能为空";
        }
        if (!Utils.validateId(user.getId())) {
            return "id不合法";
        }
        user.setCreatetime(null);
        user.setAccount(null);
        userMapper.updateByPrimaryKeySelective(user);
        return null;
    }

    public boolean delete(int id) {
        User user = new User();
        user.setId(id);
        user.setStatus(EntityStatus.DELETE);
        return userMapper.updateByPrimaryKeySelective(user) > 0;
    }

    public User getById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

}
