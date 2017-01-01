package zxy.web.controller;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import zxy.constants.JspConfig;
import zxy.dao.UserMapper;
import zxy.entity.User;
import zxy.entity.UserExample;
import zxy.service.UserService;
import zxy.web.vo.PagingResult;

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
    public ModelAndView list(@RequestParam(value = "topage", defaultValue = "1") int topage,
                             @RequestParam(value = "pageSize", defaultValue="10") int pageSize) {
        ModelAndView view = new ModelAndView();
        int start = PagingResult.getStart(topage, pageSize);
        UserExample example = new UserExample();
        int count = userMapper.countByExample(example);
        RowBounds rowBounds = new RowBounds(start, pageSize);
        List<User> retList = userMapper.selectByExampleWithRowbounds(example, rowBounds);

        PagingResult<User> page = new PagingResult(start, count, pageSize, retList);
        view.setViewName("user/list");
        view.addObject("page", page);
        view.addObject(JspConfig.WINDOW_TITLE, "用户管理");
        return view;
    }

}
