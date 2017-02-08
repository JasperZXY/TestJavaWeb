package zxy.weixin.qyh.support;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zxy.common.utils.JsonUtils;
import zxy.weixin.WeixinException;
import zxy.weixin.qyh.domain.WeixinDepartment;
import zxy.weixin.qyh.domain.WeixinResult;
import zxy.weixin.qyh.domain.WeixinUser;
import zxy.weixin.qyh.utils.WeixinReturnCode;

import java.util.*;

@Component
public class ApiContactDelegate {
    private static final Logger logger = LoggerFactory.getLogger(ApiSendMessageDelegate.class);

    private static final Integer SUPER_DEPARTMENT_ID = 1;
    private static final String urlGetUserDetailFormat = "https://qyapi.weixin.qq.com/cgi-bin/user/get?userid=%s";
    private static final String urlGetDepartmentMember = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?department_id=%s&fetch_child=%s&status=%s";
    private static final String urlListDepartment = "https://qyapi.weixin.qq.com/cgi-bin/department/list";
    private static final String urlAddUser = "https://qyapi.weixin.qq.com/cgi-bin/user/create";

    @Autowired
    private ApiBaseDelegate apiBaseDelegate;

    public WeixinUser getUserDetail(String myappid, String userid) throws WeixinException {
        if (StringUtils.isBlank(userid)) {
            return null;
        }

        String data = apiBaseDelegate.httpGet(myappid, String.format(urlGetUserDetailFormat, userid));
        WeixinUser weixinUser = JsonUtils.toObject(data, WeixinUser.class);
        if (weixinUser == null) {
            return null;
        }

        if (WeixinReturnCode.SUCCESS.equals(weixinUser.getErrcode())) {
            return weixinUser;
        }
        throw new WeixinException(weixinUser.getErrcode(), weixinUser.getErrmsg());
    }

    public List<WeixinDepartment> listDepartment(String myappid) {
        String data = apiBaseDelegate.httpGet(myappid, urlListDepartment);
        Map<String, Object> map = JsonUtils.toObject(data, Map.class);
        if (!apiBaseDelegate.isSuccess(map)) {
            return Collections.emptyList();
        }

        Object departmentObject = map.get("department");
        if (departmentObject instanceof List) {
            List list = (List)departmentObject;
            List<WeixinDepartment> departments = new ArrayList<>();

            for (Object object : list) {
                if (object instanceof Map) {
                    departments.add(JsonUtils.convertValue(object, WeixinDepartment.class));
                }
            }

            return departments;
        }

        return Collections.emptyList();
    }

    public List<WeixinUser> getDepartmentMember(String myappid, Integer departmentId) {
        return getDepartmentMember(myappid, departmentId, true, 0);
    }

    /**
     * 查询部门成员，只能获取到成员的userid跟name字段
     *
     * @param myappid
     * @param departmentId 获取的部门id
     * @param fetchChild   是否递归获取子部门下面的成员
     * @param status       0获取全部成员，1获取已关注成员列表，2获取禁用成员列表，4获取未关注成员列表。
     * @return
     */
    public List<WeixinUser> getDepartmentMember(String myappid, Integer departmentId, boolean fetchChild, Integer status) {
        Integer fetchChildInt = fetchChild ? 1 : 0;
        String data = apiBaseDelegate.httpGet(myappid, String.format(urlGetDepartmentMember, departmentId, fetchChildInt, status));
        Map<String, Object> map = JsonUtils.toObject(data, Map.class);
        if (!apiBaseDelegate.isSuccess(map)) {
            return null;
        }

        Object userObject = map.get("userlist");
        if (userObject instanceof List) {
            List list = (List)userObject;
            List<WeixinUser> users = new ArrayList<>();

            for (Object object : list) {
                if (object instanceof Map) {
                    users.add(JsonUtils.convertValue(object, WeixinUser.class));
                }
            }

            return users;
        }

        return Collections.emptyList();
    }

    /**
     *
     * @param myappid
     * @param userid
     * @param name
     * @param department
     * @param mobile
     * @return
     */
    public String addUser(String myappid, String userid, String name, Integer department, String mobile) {
        if (department == null) {
            department = SUPER_DEPARTMENT_ID;
        }
        Map<String, Object> param = new HashMap<>();
        param.put("userid", userid);
        param.put("name", name);
        param.put("department", department);
        param.put("mobile", mobile);

        String data = apiBaseDelegate.httpPost(myappid, urlAddUser, param);
        WeixinResult result = JsonUtils.toObject(data, WeixinResult.class);
        if (result == null) {
            return "未知错误";
        }
        if (apiBaseDelegate.isSuccess(result)) {
            return null;
        }
        return result.getErrmsg();
    }

}
