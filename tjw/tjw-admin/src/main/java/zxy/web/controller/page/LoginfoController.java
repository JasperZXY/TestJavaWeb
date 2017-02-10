package zxy.web.controller.page;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import zxy.commons.PagingCriteria;
import zxy.commons.PagingResult;
import zxy.commons.PermissionCode;
import zxy.dao.LoginfoMapper;
import zxy.entity.Loginfo;
import zxy.entity.LoginfoExample;
import zxy.permission.support.PermissionAnnotation;
import zxy.service.UserService;
import zxy.web.controller.vo.LogInfoVo;

import java.util.*;

@Controller
@RequestMapping("/log")
public class LoginfoController extends BasePageController {

    @Autowired
    private LoginfoMapper loginfoMapper;
    @Autowired
    private UserService userService;

    @PermissionAnnotation(code = PermissionCode.LOGINFO_ACCESS)
    @RequestMapping(path = "/list")
    public ModelAndView list(PagingCriteria pagingCriteria) {
        LoginfoExample example = new LoginfoExample();
        int count = loginfoMapper.countByExample(example);

        example.setOrderByClause(" id desc");
        List<Loginfo> loginfos = loginfoMapper.selectByExampleWithRowbounds(
            example, pagingCriteria.createRowBounds());

        PagingResult page = new PagingResult(pagingCriteria.getStart(),
            count, pagingCriteria.getPageSize());

        Set<Integer> uids = new HashSet<>();
        for (Loginfo loginfo : loginfos) {
            uids.add(loginfo.getUid());
        }
        Map<Integer, String> userNameMap = userService.getUsersName(uids);

        List<LogInfoVo> retList = new ArrayList<>();
        for (Loginfo loginfo : loginfos) {
            LogInfoVo logInfoVo = new LogInfoVo();
            BeanUtils.copyProperties(loginfo, logInfoVo);
            logInfoVo.setUserName(userNameMap.get(loginfo.getUid()));
            retList.add(logInfoVo);
        }

        page.setResult(retList);

        ModelAndView view = new ModelAndView();
        view.setViewName("log/list");
        view.addObject("page", page);
        return view;
    }
}
