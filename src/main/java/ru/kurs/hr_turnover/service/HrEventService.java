package ru.kurs.hr_turnover.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kurs.hr_turnover.model.HrEvent;
import ru.kurs.hr_turnover.repo.HrEventRepository;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HrEventService {

    private final HrEventRepository hrEventRepository;

    public List<HrEvent> getAllEvents() {
        List<HrEvent> list = hrEventRepository.findAll();
        list.sort(Comparator.comparing(HrEvent::getEventDate).reversed());
        return list;
    }
}
