package com.alan.community.controller;

import com.alan.community.dto.CommentDTO;
import com.alan.community.dto.ResultDTO;
import com.alan.community.exception.CustomizeErrorCode;
import com.alan.community.mapper.CommentMapper;
import com.alan.community.model.Comment;
import com.alan.community.model.User;
import com.alan.community.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author alan
 * @date 2020/3/22 9:43
 */
@Controller
public class CommentController {

   @Autowired
   private CommentService commentService;

    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    @ResponseBody
    public Object post(@RequestBody CommentDTO commentDTO, HttpServletRequest request){
        User currentUser = (User) request.getSession().getAttribute("CurrentUser");
        if (currentUser==null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDTO,comment);
        commentService.addComment(comment);
        return ResultDTO.successOf();
    }

}
