package zxy.service;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zxy.constants.EntityStatus;
import zxy.dao.AccountMapper;
import zxy.dao.UserMapper;
import zxy.entity.Account;
import zxy.entity.User;
import zxy.entity.UserExample;
import zxy.utils.Utils;

import java.util.*;

/**
 * 注：下面的处理逻辑为了方便，User跟Account的status是一致的、冗余的。
 */
@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AccountService accountService;

    /**
     * 添加用户
     *
     * @param user
     * @param password
     * @param email
     * @return 如果失败，这里返回失败的原因
     */
    public String add(User user, String password, String email) {
        if (user == null) {
            return "user不能为空";
        }
        if (StringUtils.isBlank(user.getAccountId())) {
            return "账号不能为空";
        }
        if (StringUtils.isBlank(password)) {
            return "密码不能为空";
        }

        Account account = new Account();
        account.setId(user.getAccountId());
        account.setPassword(password);
        if (StringUtils.isNotBlank(email)) {
            account.setEmail(email.trim());
        }
        accountService.add(account);

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
        // 账号跟创建时间不允许修改
        user.setAccountId(null);
        user.setCreatetime(null);
        user.setStatus(null);
//        user.setAccount(null);
        userMapper.updateByPrimaryKeySelective(user);
        return null;
    }

    // 这里直接暴露一个修改状态的方法不太好，正式开发下慎重使用，有风险，还需要根据user的状态进行修改
    public void updateStatus(int uid, int status) {
        User userInDB = userMapper.selectByPrimaryKey(uid);
        if (userInDB == null) {
            return;
        }

        User user = new User();
        user.setId(uid);
        user.setStatus(status);
        userMapper.updateByPrimaryKeySelective(user);

        accountService.updateStatus(userInDB.getAccountId(), status);
    }

    public User getValidUser(int id) {
        User user = userMapper.selectByPrimaryKey(id);
        if (user != null && user.getStatus() == EntityStatus.VALID) {
            return user;
        }
        return null;
    }

    public User getUserByAccount(String account) {
        if (StringUtils.isBlank(account)) {
            return null;
        }

        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(account).andStatusEqualTo(EntityStatus.VALID);
        List<User> userList = userMapper.selectByExample(userExample);
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        }
        if (userList.size() > 1) {
            logger.warn("getUserByAccount more than one. account:" + account);
        }
        return userList.get(0);
    }

    public Map<Integer, String> getUsersName(Set<Integer> uids) {
        Map<Integer, User> userMap = getUsers(uids);
        if (userMap == null || userMap.isEmpty()) {
            return Collections.emptyMap();
        }

        Map<Integer, String> map = new HashedMap();
        for (User user : userMap.values()) {
            map.put(user.getId(), user.getName());
        }
        return map;
    }

    public Map<Integer, User> getUsers(Set<Integer> uids) {
        if (CollectionUtils.isEmpty(uids)) {
            return Collections.emptyMap();
        }

        UserExample example = new UserExample();
        example.createCriteria().andIdIn(new ArrayList<>(uids));
        List<User> users = userMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(users)) {
            return Collections.emptyMap();
        }

        Map<Integer, User> map = new HashedMap();
        for (User user : users) {
            map.put(user.getId(), user);
        }
        return map;
    }

}
