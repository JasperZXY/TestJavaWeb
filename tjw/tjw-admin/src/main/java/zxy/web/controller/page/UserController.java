package zxy.web.controller.page;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import zxy.constants.JspConfig;
import zxy.dao.UserMapper;
import zxy.entity.User;
import zxy.entity.UserExample;
import zxy.service.UserService;
import zxy.common.PagingCriteria;
import zxy.common.PagingResult;

import java.util.List;

/**
 * 用户管理
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @RequestMapping(path="/list")
    public ModelAndView list(PagingCriteria pagingCriteria, String name, Integer status) {
        ModelAndView view = new ModelAndView();
        UserExample example = new UserExample();
        UserExample.Criteria exampleCriteria = example.createCriteria();
        if (StringUtils.isNoneBlank(name)) {
            exampleCriteria.andNameLike("%" + name + "%");
        }
        if (status != null) {
            exampleCriteria.andStatusEqualTo(status);
        }
        int count = userMapper.countByExample(example);
        List<User> retList = userMapper.selectByExampleWithRowbounds(example, pagingCriteria.createRowBounds());

        PagingResult<User> page = new PagingResult(pagingCriteria.getStart(), count, pagingCriteria.getPageSize(), retList);
        view.setViewName("user/list");
        view.addObject("page", page);
        view.addObject("name", name);
        view.addObject("status", status);
        view.addObject(JspConfig.WINDOW_TITLE, "用户管理");
        return view;
    }

    @RequestMapping(path="/to_update/{id}")
    public ModelAndView toUpdate(@PathVariable int id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/update");
        mv.addObject("user", userService.getById(id));
        return mv;
    }

    @RequestMapping(path="/to_add")
    public String toAdd() {
        return "user/add";
    }

}
