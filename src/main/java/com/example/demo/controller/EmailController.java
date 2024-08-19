package com.example.demo.controller;

import com.example.demo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/process")
    public String processText(@RequestParam String text, @RequestParam(required = false) boolean sort, Model model) {
        Map<String, List<String>> result = emailService.processText(text);
        model.addAttribute("newEmails", result.get("newEmails"));
        model.addAttribute("duplicateEmails", result.get("duplicateEmails"));
        return "index"; // Đảm bảo tên của trang kết quả khớp với tên template của bạn
    }
}
