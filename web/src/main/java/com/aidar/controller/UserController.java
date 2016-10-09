package com.aidar.controller;

import com.aidar.enums.AssessmentType;
import com.aidar.model.Assessment;
import com.aidar.model.Message;
import com.aidar.model.User;
import com.aidar.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paradise on 17.04.16.
 */
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RequestService requestService;

    @Autowired
    CommunityService communityService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private AssessmentService assessmentService;

    @RequestMapping("")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "user/users";
    }

    @RequestMapping("/edit")
    public String getEditPage(Model model) {
        model.addAttribute("user", userService.getCurrent());
        return "user/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/home";
    }

    @RequestMapping(value = "/ban", method = RequestMethod.POST)
    @ResponseBody
    public void banUser(@RequestParam("email") String email) {
        userService.ban(email);
    }

    @RequestMapping(value = "/pardon", method = RequestMethod.POST)
    @ResponseBody
    public void pardonUser(@RequestParam("email") String email) {
        userService.pardon(email);
    }

    @RequestMapping("/{id}/profile")
    public String getUserProfile(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getOne(id));
        model.addAttribute("volunteerReq", requestService.getClosedAsVolunteer(id));
        model.addAttribute("needyReq", requestService.getClosedAsNeedy(id));
        model.addAttribute("communities", communityService.getByUser(id));
        int rating = assessmentService.getUserRating(id);
        model.addAttribute("positive", rating >= 0);
        model.addAttribute("rating", Math.abs(rating));
        Assessment myAssessment = assessmentService.getMyAssessmentOfUser(id);
        model.addAttribute("haveAssessment", myAssessment != null);
        if (myAssessment != null) {
            model.addAttribute("havePositiveAssessment",
                    myAssessment.getAssessmentType() == AssessmentType.LIKE);
        }
        return "user/profile";
    }

    @RequestMapping("/{id}/dialog")
    public String getDialog(@PathVariable("id") Long id, Model model) {
        List<Message> messages = messageService.getDialog(id);
        model.addAttribute("messages", messages);
        model.addAttribute("friend", userService.getOne(id));
        return "user/dialog";
    }

    @RequestMapping("/{id}/dialog/new")
    @ResponseBody
    public ModelAndView getNewMessages(@PathVariable("id") Long id, Model model) {
        model.addAttribute("messages", messageService.getNew(id));
        return new ModelAndView("partition/messages_part");
    }

    @RequestMapping(value = "/{id}/dialog", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView sendNewMessage(@PathVariable("id") Long id,
                                       @RequestParam("text") String text, Model model) {
        List<Message> messages = new ArrayList<>();
        messages.add(messageService.add(id, text));
        model.addAttribute("messages", messages);
        // to determine message appearance
        model.addAttribute("my", true);
        return new ModelAndView("partition/messages_part");
    }

    @RequestMapping(value = "/{id}/assess", method = RequestMethod.POST)
    @ResponseBody
    public String assessUser(@PathVariable("id") Long id,
                             @RequestParam("assessment") String assessment) {
        assessmentService.assess(id, assessment);
        return String.valueOf(assessmentService.getUserRating(id));
    }

    // generate downloadable pdf document with all users
    @RequestMapping("/pdf")
    public ModelAndView getPdf() {
        List<User> users = userService.getAll();
        return new ModelAndView("usersPdfView", "users", users);
    }

}
