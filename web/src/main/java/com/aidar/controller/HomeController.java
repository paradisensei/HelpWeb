package com.aidar.controller;

import com.aidar.service.AssessmentService;
import com.aidar.service.CommunityService;
import com.aidar.service.MessageService;
import com.aidar.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by paradise on 06.04.16.
 */
@Controller
public class HomeController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private CommunityService communityService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private AssessmentService assessmentService;

    @RequestMapping("/")
    public String defaultPath() {
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String home(Model model) {
        // user data
        model.addAttribute("penFriends", messageService.getMyPenFriends());
        model.addAttribute("communities", communityService.getMy());
        int rating = assessmentService.getMyRating();
        model.addAttribute("positive", rating >= 0);
        model.addAttribute("rating", Math.abs(rating));
        // admin data
        model.addAttribute("recentCommunities", communityService.getRecent());
        model.addAttribute("recentRequests", requestService.getRecent());
        return "user/home";
    }

}
