package ru.kurs.hr_turnover.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kurs.hr_turnover.service.HrEventService;

@Controller
@RequestMapping("/hr-events")
@RequiredArgsConstructor
public class HrEventsPageController {

    private static final Logger log = LoggerFactory.getLogger(HrEventsPageController.class);
    private final HrEventService hrEventService;

    @GetMapping({"", "/"})
    public String page(Model model) {
        log.info(">>> /hr-events page called");
        model.addAttribute("events", hrEventService.getAllEvents());
        return "hr-events/list";
    }
}
