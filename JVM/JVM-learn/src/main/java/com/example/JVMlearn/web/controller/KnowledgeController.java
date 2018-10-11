package com.example.JVMlearn.web.controller;

import com.example.JVMlearn.web.dal.dataobject.KnowledgeDO;
import com.example.JVMlearn.web.service.KnowledgeService;
import com.example.JVMlearn.web.service.model.KnowledgeReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by liuzhuang on 2018/7/28.
 */
@RestController
@RequestMapping(value = "/knowledge", method = RequestMethod.POST)
public class KnowledgeController {
    private Logger log = LoggerFactory.getLogger(KnowledgeController.class);

    @Autowired
    private KnowledgeService knowledgeService;

    @RequestMapping("/test")
    public String test(HttpServletRequest httpRequest) {
        Object haha = httpRequest.getAttribute("haha");
        httpRequest.getHeader("");
        String hehe = httpRequest.getParameter("hehe");
        System.out.println(String.valueOf(haha) + " ; hehe : " + hehe);
        return "success";
    }
    
    @RequestMapping("/add")
    public String add(@RequestBody KnowledgeReq knowledgeReq) {
        log.info(knowledgeReq.toString());
        knowledgeService.addKnowledge(knowledgeReq);
        return "success";
    }

    @RequestMapping("/update")
    public String update(@RequestBody KnowledgeReq knowledgeReq) {
        log.info(knowledgeReq.toString());
        knowledgeService.updateKnowledge(knowledgeReq);
        return "success";
    }

    @RequestMapping("/delete")
    public String delete(@RequestBody KnowledgeReq knowledgeReq) {
        log.info(knowledgeReq.toString());
        knowledgeService.deleteKnowledge(knowledgeReq.getId());
        return "success";
    }

    @RequestMapping("/list")
    public List<KnowledgeDO> list(@RequestBody KnowledgeReq knowledgeReq) {
        log.info(knowledgeReq.toString());
        return knowledgeService.listKnowledge();
    }
}
