package in.sarthaktyagi.quizapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.sarthaktyagi.quizapp.service.QuestionService;
import in.sarthaktyagi.quizapp.model.Question;

@RestController
@RequestMapping("/api/v1/questions")
public class QuestionController {
    
    @Autowired
    QuestionService questionService;


    @GetMapping()
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category); 
    }

    @PostMapping()
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    @GetMapping("/query")
    public ResponseEntity<Map<String, Object>> getQueryParams(@RequestParam Map<String, Object> reqParams){
        Map<String, Object> result = new HashMap<String, Object>();
        for (Map.Entry<String,Object> entry : reqParams.entrySet()) 
            result.put(entry.getKey(), entry.getValue());
        
        result.put("size", result.size());
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }
}
