package com.pluvio.codecrafters.controller;

import com.pluvio.codecrafters.model.Answer;
import com.pluvio.codecrafters.model.ResponseItem;
import com.pluvio.codecrafters.model.dto.ResponseQuestions;
import com.pluvio.codecrafters.model.entity.AnswerEntity;
import com.pluvio.codecrafters.model.entity.QuestionEntity;
import com.pluvio.codecrafters.model.repository.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by salagoz on May, 2022
 */
@RestController
public class MainController {

    final String FILTER = "!.KWpexRBiM3k9UbAQ0V12uAD*y2.H";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/")
    private String hello() {
        return "hello";
    }

    @GetMapping("/parse")
    private String parse(
            @RequestParam Integer fromdate,
            @RequestParam Integer todate,
            @RequestParam String tagged
    ) throws ParseException {
        int page = 1;
        int maxPage = 2;
        List<QuestionEntity> questionEntityList = new ArrayList<>();
        List<String> tags = new ArrayList<String>() {{
            add("nodejs");
        }};

        for (String tag : tags) {
            while (maxPage >= page) {
                String URL = "https://api.stackexchange.com/2.3/questions?pagesize=100&page=" + page +
                        "&fromdate=" + fromdate + "&todate=" + todate + "&order=desc&sort=votes&tagged=" + tag +
                        "&site=stackoverflow&filter=" + FILTER;

                restTemplate.getMessageConverters()
                        .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
                ResponseEntity<ResponseItem> questionsResponseEntity = restTemplate.getForEntity(URL, ResponseItem.class);
                ResponseItem questions = questionsResponseEntity.getBody();
                assert questions != null;
                System.out.println("Remaining : " + questions.getQuota_remaining());
                questions.getItems().forEach(question -> {
                    long questionId = question.getQuestion_id();

                    /* Question */
                    long creation_date = question.getCreation_date();
                    Date date = new Date(1220227200L * 1000);

                    // new Date(1653008124L * 1000).toInstant() JAVA
                    // new Date(1653059311 * 1000).toUTCString() JS
                    if (question.getAccepted_answer_id() > 0 && question.getView_count() > 1) {
                        QuestionEntity questionEntity = modelMapper.map(question, QuestionEntity.class);
                        List<AnswerEntity> answersQuestionEntity = questionEntity.getAnswers();
                        List<Answer> answers = question.getAnswers();
                        for (int i = 0; i < answers.size(); i++) {
                            Answer answer = answers.get(i);
                            String link = answer.getOwner().getLink();
                            String name = answer.getOwner().getDisplay_name();
                            answersQuestionEntity.get(i).setOwnerLink(link);
                            answersQuestionEntity.get(i).setOwnerName(name);
                        }
                        questionEntityList.add(questionEntity);
                    }
                });

                long a = questions.getTotal() / questions.getPage_size();
                maxPage = (int) Math.ceil(a);
                page++;
            }
        }
        itemRepository.saveAll(questionEntityList);
        return "Hello";
    }

    @GetMapping("/questions")
    private ResponseEntity<ResponseQuestions> getQuestions(@RequestParam Integer page) {
        Pageable paging = PageRequest.of(--page, 10, Sort.by("createdAt").descending());

        Page<QuestionEntity> pagedResult = itemRepository.findAll(paging);
        long totalElements = pagedResult.getTotalElements();
        int totalPages = pagedResult.getTotalPages();
        return new ResponseEntity<>(
                ResponseQuestions.builder()
                        .totalPages(totalPages)
                        .totalElements(totalElements)
                        .questions(pagedResult.getContent())
                        .build(),
                HttpStatus.OK
        );
    }

    @GetMapping("/questions/{questionId}")
    private ResponseEntity<QuestionEntity> getQuestionById(@PathVariable Long questionId) {
        Optional<QuestionEntity> questionEntityOptional = itemRepository.findById(questionId);
        if (questionEntityOptional.isPresent())
            return new ResponseEntity<>(questionEntityOptional.get(), HttpStatus.OK);
        throw new RuntimeException("question not found");
    }

    @GetMapping("/category/{category}")
    private ResponseEntity<List<QuestionEntity>> byTag(
            @PathVariable String category,
            @RequestParam Integer page
    ) {
        Pageable paging = PageRequest.of(--page, 10, Sort.by("createdAt").descending());
        Page<QuestionEntity> responsePage = itemRepository.findByTags(category, paging);
        return new ResponseEntity<>(responsePage.getContent(), HttpStatus.OK);
    }
}
