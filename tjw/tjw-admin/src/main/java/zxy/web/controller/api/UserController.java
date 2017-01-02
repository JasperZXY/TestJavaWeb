package zxy.web.controller.api;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zxy.entity.User;
import zxy.service.UserService;
import zxy.web.vo.JsonResult;

@Controller("apiUserController")
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(path="/add")
    @ResponseBody
    public Object add(User user) {
        String result = userService.add(user);
        if (StringUtils.isBlank(result)) {
            return JsonResult.buildSuccess(null);
        }
        return JsonResult.buildErrorTip(result);
    }

    @RequestMapping(path="/update")
    @ResponseBody
    public Object update(User user) {
        String result = userService.update(user);
        if (StringUtils.isBlank(result)) {
            return JsonResult.buildSuccess(null);
        }
        return JsonResult.buildErrorTip(result);
    }

    @RequestMapping(path="/delete/{id}")
    @ResponseBody
    public Object delete(@PathVariable int id) {
        if (userService.delete(id)) {
            return JsonResult.buildSuccess(null);
        }
        return JsonResult.buildErrorTip("记录没找到");
    }
}
