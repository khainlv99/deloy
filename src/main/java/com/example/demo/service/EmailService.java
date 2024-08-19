package com.example.demo.service;

import com.example.demo.model.Email;
import com.example.demo.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    private static final String EMAIL_REGEX =
            "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";

    public Map<String, List<String>> processText(String text) {
        Set<String> uniqueEmails = extractEmails(text);
        List<String> newEmails = new ArrayList<>();
        List<String> duplicateEmails = new ArrayList<>();

        for (String email : uniqueEmails) {
            if (!emailRepository.existsByEmailAddress(email)) {
                newEmails.add(email);
                Email newEmail = new Email();
                newEmail.setEmailAddress(email);
                emailRepository.save(newEmail);
            } else {
                duplicateEmails.add(email);
            }
        }

        Map<String, List<String>> result = new HashMap<>();
        result.put("newEmails", newEmails.stream().sorted().collect(Collectors.toList()));
        result.put("duplicateEmails", duplicateEmails.stream().sorted().collect(Collectors.toList()));
        return result;
    }

    private Set<String> extractEmails(String text) {
        Set<String> emails = new HashSet<>();
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            emails.add(matcher.group());
        }
        return emails;
    }
}