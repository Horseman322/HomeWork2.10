package com.example.homework210.service;

import com.example.homework210.exception.IncorrectNameException;
import com.example.homework210.exception.IncorrectSecondNameException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ValidatorService {

    public String validateName(String name) throws IncorrectNameException {
        if (!StringUtils.isAlpha(name)){
            throw new IncorrectNameException();
        }
            return StringUtils.capitalize(name.toLowerCase());
        }
        public String validateSecondName(String secondName) throws IncorrectSecondNameException {
        String[]secondNames = secondName.split("-");
        for (int i = 0; i < secondNames.length; i++){
            if (StringUtils.isAlpha(secondNames[i])){
            throw new IncorrectSecondNameException();
            }
}
            return String.join("-", secondNames);
        }
}
