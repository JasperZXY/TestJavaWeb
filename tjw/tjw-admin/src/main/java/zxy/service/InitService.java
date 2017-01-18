package zxy.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zxy.dao.AccountMapper;
import zxy.entity.Account;
import zxy.entity.User;

import javax.annotation.PostConstruct;

@Service
public class InitService {
    private static final Logger logger = LoggerFactory.getLogger(InitService.class);

    private static final String ROOT_ACCOUNT = "root";
    private static final String ROOT_PASSWORD = "zxy@123";

    @Autowired
    private UserService userService;
    @Autowired
    private AccountMapper accountMapper;

    @PostConstruct
    public void init() {
        initRootUser();
    }

    private void initRootUser() {
        Account account = accountMapper.selectByPrimaryKey(ROOT_ACCOUNT);
        if (account == null) {
            logger.info("initRootUser create root user");
            User user = new User();
            user.setAccountId(ROOT_ACCOUNT);
            user.setName(ROOT_ACCOUNT);
            userService.add(user, ROOT_PASSWORD);
        }
    }

}
