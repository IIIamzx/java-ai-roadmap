package com.java.langchain4j.service;

import com.java.langchain4j.func.Person;

/**
 * @author: zhangxin
 * @date: 2025/3/13
 * @description:
 */
public interface PersonService {

    Person extractPerson(String msg);

}
