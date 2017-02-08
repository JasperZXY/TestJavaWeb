package zxy.web.controller.page;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import zxy.commons.PermissionCode;
import zxy.commons.JspConfig;
import zxy.dao.UserMapper;
import zxy.entity.Account;
import zxy.entity.User;
import zxy.entity.UserExample;
import zxy.permission.support.PermissionAnnotation;
import zxy.service.AccountService;
import zxy.service.UserService;
import zxy.commons.PagingCriteria;
import zxy.commons.PagingResult;
import zxy.web.controller.vo.UserVo;

import java.util.*;

/**
 * 用户管理
 */
@Controller
@RequestMapping("/user")
public class UserController extends BasePageController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AccountService accountService;

    @PermissionAnnotation(code = PermissionCode.USER_ACCESS)
    @RequestMapping(path = "/list")
    public ModelAndView list(PagingCriteria pagingCriteria, String name, String email, Integer status) {
        ModelAndView view = new ModelAndView();

        List<UserVo> retList = new ArrayList<>();
        int count = 0;

        if (StringUtils.isNotBlank(email)) {
            Account account = accountService.getAccountbyEmail(email);
            if (account != null) {
                User user = userService.getUserByAccount(account.getId());
                UserVo userVo = new UserVo();
                BeanUtils.copyProperties(user, userVo);
                userVo.setEmail(email);
                retList.add(userVo);
                count = 1;
            }
        }
        else {
            UserExample example = new UserExample();
            UserExample.Criteria exampleCriteria = example.createCriteria();
            if (StringUtils.isNoneBlank(name)) {
                exampleCriteria.andNameLike("%" + name + "%");
            }
            if (status != null) {
                exampleCriteria.andStatusEqualTo(status);
            }
            count = userMapper.countByExample(example);
            List<User> users = userMapper.selectByExampleWithRowbounds(example, pagingCriteria.createRowBounds());

            Set<String> accountIds = new HashSet<>();
            for (User user : users) {
                accountIds.add(user.getAccountId());
            }
            Map<String, String> emailMap = accountService.getAccountEmail(accountIds);

            for (User user : users) {
                UserVo userVo = new UserVo();
                BeanUtils.copyProperties(user, userVo);
                userVo.setEmail(emailMap.get(user.getAccountId()));
                retList.add(userVo);
            }
        }

        PagingResult<UserVo> page = new PagingResult(pagingCriteria.getStart(), count, pagingCriteria.getPageSize(), retList);
        view.setViewName("user/list");
        view.addObject("page", page);
        view.addObject("name", name);
        view.addObject("status", status);
        view.addObject("email", email);
        view.addObject(JspConfig.WINDOW_TITLE, "用户管理");
        return view;
    }

    @PermissionAnnotation(code = PermissionCode.USER_UPDATE)
    @RequestMapping(path = "/to_update/{id}")
    public ModelAndView toUpdate(@PathVariable int id) {
        User user = userService.getValidUser(id);
        if (user == null) {
            return toErrorView("没有找到该用户");
        }

        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/update");
        mv.addObject("user", user);
        return mv;
    }

    @PermissionAnnotation(code = PermissionCode.USER_ADD)
    @RequestMapping(path = "/to_add")
    public String toAdd() {
        return "user/add";
    }

}
