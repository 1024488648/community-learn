package com.yue.mycommunity.controller;

import com.yue.mycommunity.dao.QuestionMapper;
import com.yue.mycommunity.dao.UserMapper;
import com.yue.mycommunity.pojo.Question;
import com.yue.mycommunity.pojo.User;
import com.yue.mycommunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "tag", required = false) String tag,

            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "2") Integer size,
           // @RequestParam(value = "id", required = false) Long id,
            HttpServletRequest request,
            Model model) {

        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        //model.addAttribute("tags", TagCache.get());

        User user = null;
        Cookie[] cookies= request.getCookies();
        if(cookies !=null && cookies.length!=0){
            for (Cookie cookie:cookies) {
                if(cookie.getName().equals("token")){
                    String token =cookie.getValue();
                    user = userMapper.findByToken(token);
                    if(user!=null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }




        if (StringUtils.isBlank(title)) {
            model.addAttribute("error", "??????????????????");
            return "publish";
        }

        if (StringUtils.length(title) > 50) {
            model.addAttribute("error", "???????????? 50 ?????????");
            return "publish";
        }
        if (StringUtils.isBlank(description)) {
            model.addAttribute("error", "????????????????????????");
            return "publish";
        }
        if (StringUtils.isBlank(tag)) {
            model.addAttribute("error", "??????????????????");
            return "publish";
        }

//        String invalid = TagCache.filterInvalid(tag);
//        if (StringUtils.isNotBlank(invalid)) {
//            model.addAttribute("error", "??????????????????:" + invalid);
//            return "publish";
//        }
//
//        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            System.out.println("???????????????");
            model.addAttribute("error", "???????????????");
            return "publish";
        }
//
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator((long)user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(user.getGmtModified());

        questionMapper.create(question);

//        question.setCreator(user.getId());
//        question.setId(id);
//        questionService.createOrUpdate(question);


        Integer totalCount = questionMapper.count();
        page=totalCount/size;
        return "redirect:/?page="+page+1;
    }

}
