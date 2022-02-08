package net.vtodankar.simple.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import net.vtodankar.simple.model.Password;

import org.passay.PasswordGenerator;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;

@RestController

public class APIController {
    @GetMapping("/password")
    public ResponseEntity<Password> getPassword(@RequestParam(value = "length", required = false) Integer length) {
        if(length==null) length=8;
        Password password = new Password(generatePassayPassword(length));
        return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/json")).body(password);
    }

    public String generatePassayPassword(int length) {
        PasswordGenerator gen = new PasswordGenerator();
        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(2);
    
        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(2);
    
        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(2);

    
        String password = gen.generatePassword(length, lowerCaseRule,upperCaseRule, digitRule);
        return password;
    }
}
