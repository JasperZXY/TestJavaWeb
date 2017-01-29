package zxy.permission.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import zxy.permission.entity.Resource;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Component
public class PrivilegeContext {
    private static final Logger logger = LoggerFactory.getLogger(PrivilegeContext.class);
    public static final String RESOURCE = PrivilegeContext.class.getName() + ".RESOURCE";
    @Autowired
    private PrivilegeService privilegeService;

    public void initUserPrivilege(HttpSession session, Integer uid) {
        Set<Integer> resourceIds = privilegeService.getResourceIdsForUser(uid);
        session.setAttribute(RESOURCE, resourceIds);
    }

    public boolean pass(HttpSession session, Integer privilegeCode) {
        if (session == null) {
            return false;
        }
        Set<Integer> resourceIds = (Set<Integer>) session.getAttribute(RESOURCE);
        return privilegeService.pass(resourceIds, privilegeCode);
    }


}
