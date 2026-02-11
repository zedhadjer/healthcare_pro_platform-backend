/**
 * 
 */
package com.medical_platform.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medical_platform.entity.Message;
import com.medical_platform.entity.User;

/**
 * 
 */
@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:4200")//Autorise Angular
public class UserController {
 @GetMapping("/test")
 public String test() {
	 return "Hello from Spring boot!";
 
 }
 @GetMapping("/user")
 public User getUser() {
     return new User("John Doe", "john@example.com");
 }
 @GetMapping("/alluser")
 public User [] getAllUser() {
	 User users []= {new User("John Doe", "john@example.com"),
			 new User("John1 Doe1", "john1@example.com"),
			 new User("John2 Doe2", "john2@example.com")};
     return users;
 }
 @PostMapping("/message")
 public Message sendMessage(@RequestBody Message message) {
     message.setResponse("Received: " + message.getText());
     System.out.println(message.getResponse());
     return message;
 }
}
