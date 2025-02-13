package com.meeting.api;

import com.meeting.domain.dto.response.ResponseContentVo;
import com.meeting.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/content")
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<ResponseContentVo>> findAllContents(){
        return ResponseEntity.ok(contentService.findAllContents());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/name/{name}")
    public ResponseEntity<List<ResponseContentVo>> findContentByContentName(@PathVariable String name){
        if(StringUtils.isBlank(name)){
            throw new IllegalArgumentException("name is empty.");
        }

        return ResponseEntity.ok(contentService.findContentByContentName(name));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/most-selected")
    public ResponseEntity<ResponseContentVo> findMostSelectedContent(){
        return ResponseEntity.ok(contentService.findMostSelectedContent());
    }
}
