package com.java.ai.controller;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author: zhangxin
 * @date: 2025/3/11
 * @description:
 */
@RestController
//@RequiredArgsConstructor
@RequestMapping("/ai")
public class VectorController {

    @Autowired
    VectorStore vectorStore;

    @GetMapping("/write")
    public String  write() throws IOException {
        //读取文本数据
        StringBuilder text = new StringBuilder();
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("nocode1.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line);
            }
        }
        //转为Document存储
        vectorStore.write(Arrays.stream(text.toString().split("。")).map(Document::new).toList());
        return "success";
    }


}
