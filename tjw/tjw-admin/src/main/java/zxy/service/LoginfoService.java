package zxy.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zxy.dao.LoginfoMapper;
import zxy.entity.Loginfo;
import zxy.utils.JsonUtils;
import zxy.utils.Utils;
import zxy.web.SessionManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class LoginfoService {
    private static final Logger logger = LoggerFactory.getLogger(LoginfoService.class);
    @Autowired
    private LoginfoMapper loginfoMapper;

    public void addLog(HttpServletRequest request, String code, String operation) {
        addLog(request, code, operation, null, null);
    }

    public void addLog(HttpServletRequest request, String code, String operation, String target) {
        addLog(request, code, operation, target, null);
    }

    public void addLog(HttpServletRequest request, String code, String operation, Integer target) {
        if (target == null) {
            addLog(request, code, operation, null, null);
        }
        else {
            addLog(request, code, operation, target.toString(), null);
        }
    }

    public void addLog(HttpServletRequest request, String code, String operation, String target, String extra) {
        Loginfo loginfo = new Loginfo();
        loginfo.setCode(code);
        loginfo.setOperation(operation);
        loginfo.setTarget(target);
        loginfo.setExtra(extra);
        loginfo.setUid(SessionManager.getCurrentUserId(request.getSession(false)));
        if (loginfo.getUid() == null) {
            loginfo.setUid(0);
        }
        loginfo.setIp(Utils.getRemoteIP(request));
        loginfo.setCreatetime(new Date());
        logger.debug("addLog:" + JsonUtils.toString(loginfo));
        loginfoMapper.insert(loginfo);
    }
}
