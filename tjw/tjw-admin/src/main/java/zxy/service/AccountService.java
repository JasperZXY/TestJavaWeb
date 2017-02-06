package zxy.service;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import zxy.common.ResultCode;
import zxy.common.ServiceException;
import zxy.constants.EntityStatus;
import zxy.constants.RedisKey;
import zxy.dao.AccountMapper;
import zxy.entity.Account;
import zxy.entity.AccountExample;
import zxy.redis.JedisTemplate;
import zxy.utils.DigestUtils;
import zxy.utils.HexUtils;
import zxy.utils.Utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

@Service
public class AccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    private static final int SALT_LENGTH = 16;
    private static final String MAIL_TITLE_RESET_PASSWORD = "重置密码";

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private JedisTemplate jedisTemplate;
    @Autowired
    private MailService mailService;

    public void add(Account account) {
        String oriPassword = account.getPassword();
        String salt = createSalt();
        account.setPassword(createPassword(oriPassword, salt));
        account.setSalt(salt);
        account.setStatus(EntityStatus.VALID);
        try {
            accountMapper.insert(account);
        } catch (DuplicateKeyException e) {
            throw new ServiceException(ResultCode.ACCOUNT_EXIST);
        }
    }

    public void delete(String id) {
        updateStatus(id, EntityStatus.DELETE);
    }

    public void updateStatus(String id, int status) {
        Account accountUpdate = new Account();
        accountUpdate.setId(id);
        accountUpdate.setStatus(status);
        accountMapper.updateByPrimaryKeySelective(accountUpdate);
    }

    /**
     * 是否登录验证成功
     * @param id
     * @param password
     * @return 0成功；1账号不存在；2已冻结；3密码有误
     */
    public int login(String id, String password) {
        Account accountDB = accountMapper.selectByPrimaryKey(id);
        if (accountDB == null) {
            return 1;
        }

        if (accountDB.getStatus() != EntityStatus.VALID) {
            return 2;
        }

        if (createPassword(password, accountDB.getSalt()).equals(accountDB.getPassword())) {
            return 0;
        }
        return 3;
    }

    public void changePassword(String accountId, String password) {
        Account account = new Account();
        account.setId(accountId);
        account.setSalt(createSalt());
        account.setPassword(createPassword(password, account.getSalt()));
        accountMapper.updateByPrimaryKeySelective(account);
    }

    protected String createSalt() {
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            byte[] salt = new byte[SALT_LENGTH];
            sr.nextBytes(salt);
            return HexUtils.byteToHexStr(salt);
        } catch (NoSuchAlgorithmException e) {
            logger.error("createSalt error", e);
        }
        return Utils.randomString(SALT_LENGTH * 2);
    }

    protected String createPassword(String oriPassword, String salt) {
        String md5Password = DigestUtils.md5(oriPassword);
        String shaPassword = DigestUtils.sha256Secure(md5Password, salt);
        return shaPassword;
    }

    public Account getAccount(String accountId) {
        return accountMapper.selectByPrimaryKey(accountId);
    }

    public Account getAccountbyEmail(String email) {
        AccountExample example = new AccountExample();
        example.createCriteria().andEmailEqualTo(email);
        List<Account> list = accountMapper.selectByExample(example);
        return Utils.getLast(list);
    }

    public Map<String, String> getAccountEmail(Set<String> accountIds) {
        if (CollectionUtils.isEmpty(accountIds)) {
            return Collections.emptyMap();
        }

        AccountExample example = new AccountExample();
        example.createCriteria().andIdIn(new ArrayList<>(accountIds));
        List<Account> list = accountMapper.selectByExample(example);

        Map<String, String> accountEmailMap = new HashedMap();
        for (Account account : list) {
            accountEmailMap.put(account.getId(), account.getEmail());
        }
        return accountEmailMap;
    }

    public ResultCode changeEmail(String accountId, String email) {
        if (StringUtils.isBlank(email)) {
            email = null;
        }

        Account account = getAccount(accountId);
        if (account == null) {
            return ResultCode.DATA_NO_FOUND;
        }

        account.setEmail(email);
        try {
            accountMapper.updateByPrimaryKey(account);
        }
        catch (DuplicateKeyException e) {
            logger.error("changeEmail error.", e);
            return ResultCode.EMAIL_EXIST;
        }
        return ResultCode.SUCCESS;
    }

    private String codeKey(String account) {
        return String.format(RedisKey.CODE_RESET_PASSWORD, account);
    }

    /**
     * 发送验证码，实现重置密码用
     * @param account
     * @param email
     */
    public ResultCode createCodeForResetPassword(String account, String email) {
        Account userAccount = getAccount(account);
        if (userAccount == null) {
            return ResultCode.DATA_NO_FOUND;
        }
        if (!email.equals(userAccount.getEmail())) {
            return ResultCode.RESET_PASSWORD_ACCOUNT_EMAIL_NOT_MATCH;
        }

        String code = Utils.randomString(8);
        mailService.send(MAIL_TITLE_RESET_PASSWORD, String.format("验证码：【%s】", code), email);
        jedisTemplate.setex(codeKey(account), code, 30 * 60);   // 30分钟过期时间
        return ResultCode.SUCCESS;
    }

    public ResultCode resetPassword(String account, String email, String code, String password) {
        String oriCode = jedisTemplate.get(codeKey(account));
        if (StringUtils.isBlank(oriCode) || !oriCode.equals(code)) {
            return ResultCode.RESET_PASSWORD_CODE_ERROR;
        }

        Account userAccount = getAccount(account);
        if (userAccount == null) {
            return ResultCode.DATA_NO_FOUND;
        }
        if (!email.equals(userAccount.getEmail())) {
            return ResultCode.RESET_PASSWORD_ACCOUNT_EMAIL_NOT_MATCH;
        }

        jedisTemplate.del(codeKey(account));

        userAccount.setPassword(createPassword(password, userAccount.getSalt()));
        accountMapper.updateByPrimaryKey(userAccount);
        return ResultCode.SUCCESS;
    }

}
