package com.aidar.controller;

import com.aidar.model.Community;
import com.aidar.model.News;
import com.aidar.service.CommunityService;
import com.aidar.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by paradise on 24.04.16.
 */
@Controller
@RequestMapping("/communities")
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "")
    public String getAllCommunities(Model model) {
        model.addAttribute("communities", communityService.getAll());
        return "community/communities";
    }

    @RequestMapping(value = "/{id}")
    public String getInfo(@PathVariable("id") Long id, Model model) {
        Community community = communityService.getOne(id);
        model.addAttribute("community", community);
        model.addAttribute("membership", communityService.isMember(id));
        model.addAttribute("news", community.getNews());
        return "community/community";
    }

    @RequestMapping(value = "/{id}/news/create", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView sendNews(@PathVariable("id") Long id,
                                 @RequestParam("text") String text, Model model) {
        News news = newsService.add(id, text);
        model.addAttribute("news", news);
        return new ModelAndView("partition/news_part");
    }

    @RequestMapping(value = "/{id}/subscribe", method = RequestMethod.POST)
    @ResponseBody
    public void subscribe(@PathVariable("id") Long id) {
        communityService.addMember(id);
    }

    @RequestMapping(value = "/{id}/unsubscribe", method = RequestMethod.POST)
    @ResponseBody
    public void unsubscribe(@PathVariable("id") Long id) {
        communityService.removeMember(id);
    }

    @RequestMapping(value = "/new")
    public String getNewCommunityForm(Model model) {
        model.addAttribute("community", new Community());
        return "community/new_community";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String addNewCommunity(@ModelAttribute("community") @Valid Community community,
                                  BindingResult result) {
        if (result.hasErrors()) {
            return "community/new_community";
        }
        communityService.add(community);
        return "redirect:/communities/";
    }

    // generate downloadable pdf document with all requests
    @RequestMapping("/pdf")
    public ModelAndView getPdf() {
        List<Community> communities = communityService.getAll();
        return new ModelAndView("communitiesPdfView", "communities", communities);
    }

}
