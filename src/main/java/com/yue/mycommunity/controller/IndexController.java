package com.yue.mycommunity.controller;


import com.yue.mycommunity.dao.QuestionMapper;
import com.yue.mycommunity.dao.UserMapper;
import com.yue.mycommunity.dto.PaginationDTO;
import com.yue.mycommunity.dto.QuestionDTO;
import com.yue.mycommunity.pojo.Question;
import com.yue.mycommunity.pojo.User;
import com.yue.mycommunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.rmi.MarshalledObject;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String indexController(HttpServletRequest request,
                                    Model model,
                                    @RequestParam(name = "page", defaultValue = "1") Integer page,
                                    @RequestParam(name = "size", defaultValue = "4") Integer size,
                                    @RequestParam(name = "search", required = false) String search,
                                    @RequestParam(name = "tag", required = false) String tag,
                                    @RequestParam(name = "sort", required = false) String sort) {
        Cookie[] cookies= request.getCookies();
        if(cookies !=null && cookies.length!=0){
            for (Cookie cookie:cookies) {
                if(cookie.getName().equals("token")){
                    String token =cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if(user!=null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }

            }
        }

        PaginationDTO pagination=questionService.list(page,size);
        model.addAttribute("pagination",pagination);
        model.addAttribute("search", search);
        model.addAttribute("tag", tag);
        //model.addAttribute("tags", tags);
        model.addAttribute("sort", sort);
        return "index";
    }
}
