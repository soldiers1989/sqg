package com.soft.tbk.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.soft.tbk.base.BaseController;
import com.soft.tbk.domain.FriendDomain;
import com.soft.tbk.domain.QueryResult;
import com.soft.tbk.domain.UserSession;
import com.soft.tbk.model.TbkUser;
import com.soft.tbk.service.TbkUserService;
import com.soft.tbk.utils.BeanUtils;

@Controller
@RequestMapping("/web/friend")
public class FriendController extends BaseController {

    @Autowired
    private TbkUserService tbkUserService;

    @RequestMapping("/index")
    public String index(ModelMap model, HttpServletRequest request) {

        return "/h5/friend/index";
    }

    @ResponseBody
    @RequestMapping("/list.json")
    public QueryResult<FriendDomain> list(HttpServletRequest request) {

        UserSession userSession = getUserSession(request);
        Integer userId = userSession.getId();
        QueryResult<TbkUser> queryResult = queryResult(request, userId);
        List<FriendDomain> friendList = new ArrayList<>();
        if (queryResult != null && queryResult.getList() != null && !queryResult.getList().isEmpty()) {
            List<TbkUser> list = queryResult.getList();
            for (TbkUser tbkUser : list) {
                FriendDomain friendDomain = new FriendDomain();
                try {
                    BeanUtils.copyAllPropertys(friendDomain, tbkUser);
                } catch (Exception e) {}
                QueryResult<TbkUser> childResult = queryResult(request, tbkUser.getId());
                friendDomain.setFriendAccount(childResult.getTotal());
                friendList.add(friendDomain);
            }
        }
        QueryResult<FriendDomain> friendResult = new QueryResult<>(friendList, queryResult.getTotal());
        return friendResult;
    }

    /**
     * 根据父级用户查询
     * 
     * @param request
     * @param parentId
     * @return
     */
    private QueryResult<TbkUser> queryResult(HttpServletRequest request, Integer parentId) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("parentId", parentId);
        String pageNum = request.getParameter("pageNum");
        if (StringUtils.isBlank(pageNum)) {
            pageNum = "1";
        }
        String rows = request.getParameter("rows");
        if (StringUtils.isBlank(rows)) {
            rows = "10";
        }
        map.put("pageNum", Integer.valueOf(pageNum));
        map.put("pageSize", Integer.valueOf(rows));
        map.put("orderStr", "ID DESC");
        map.put("order", true);
        QueryResult<TbkUser> queryResult = tbkUserService.queryTbkUser(map);
        return queryResult;
    }
}
