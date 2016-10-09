package com.aidar.controller;

import com.aidar.enums.ServiceType;
import com.aidar.model.Comment;
import com.aidar.model.Request;
import com.aidar.service.CommentService;
import com.aidar.service.GoogleMapsService;
import com.aidar.service.RequestService;
import com.aidar.service.SecurityService;
import com.aidar.util.RequestValidator;
import com.aidar.util.google_api.LocationBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by paradise on 24.04.16.
 */
@Controller
@RequestMapping("/requests")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private GoogleMapsService googleMapsService;

    @Autowired
    RequestValidator requestValidator;

    @RequestMapping("")
    public String getRequestsPage(Model model) {
        model.addAttribute("requests", requestService.getAll());
        return "request/requests";
    }

    @RequestMapping("/all")
    @ResponseBody
    public ModelAndView getAll(Model model) {
        model.addAttribute("requests", requestService.getAll());
        return new ModelAndView("partition/requests_part");
    }

    @RequestMapping("/my")
    @ResponseBody
    public ModelAndView getMy(Model model) {
        model.addAttribute("requests", requestService.getMy());
        return new ModelAndView("partition/requests_part");
    }

    @RequestMapping("/pending")
    @ResponseBody
    public ModelAndView getPending(Model model) {
        model.addAttribute("requests", requestService.getPending());
        model.addAttribute("type", "pending");
        return new ModelAndView("partition/requests_part");
    }

    @RequestMapping("/{id}")
    public String getInfo(@PathVariable("id") Long id, Model model) {
        model.addAttribute("request", requestService.getOne(id));
        model.addAttribute("principal", securityService.getPrincipal());
        return "request/request";
    }

    @RequestMapping("/{id}/path")
    @ResponseBody
    public LocationBody getLocation(@RequestParam("address") String address) {
        return googleMapsService.getLocation(address);
    }

    @RequestMapping("/new")
    public String getNewForm(Model model) {
        model.addAttribute("serviceTypes", ServiceType.values());
        model.addAttribute("request", new Request());
        return "request/new_request";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String sendNewForm(@ModelAttribute("request") Request request,
                              BindingResult result, Model model) {
        requestValidator.validate(request, result);
        if (result.hasErrors()) {
            model.addAttribute("serviceTypes", ServiceType.values());
            return "request/new_request";
        }
        requestService.add(request);
        return "redirect:/requests/";
    }

    @RequestMapping(value = "/{id}/help", method = RequestMethod.POST)
    @ResponseBody
    public void help(@PathVariable("id") Long id) {
        requestService.help(id);
    }

    @RequestMapping(value = "/{id}/close", method = RequestMethod.POST)
    @ResponseBody
    public void close(@PathVariable("id") Long id) {
        requestService.close(id);
    }

    @RequestMapping(value = "/{id}/comments/create", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView sendNewComment(@PathVariable("id") Long id,
                                       @RequestParam("text") String text, Model model) {
        Comment comment = commentService.add(id, text);
        model.addAttribute("comment", comment);
        return new ModelAndView("partition/comments_part");
    }

    // generate downloadable pdf document with all requests
    @RequestMapping("/pdf")
    public ModelAndView getPdf() {
        List<Request> requests = requestService.getAll();
        return new ModelAndView("requestsPdfView", "requests", requests);
    }

}
