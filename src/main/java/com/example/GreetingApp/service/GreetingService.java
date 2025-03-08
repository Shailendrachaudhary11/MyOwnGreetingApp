package com.example.GreetingApp.service;

import com.example.GreetingApp.entity.Greeting;
import com.example.GreetingApp.repository.GreetingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GreetingService {

    private final GreetingRepository greetingRepository;

    public String getSimpleGreeting() {
        return "Hello World Shailendra Chaudhary!";
    }

    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    public String getGreeting(String firstName, String lastName) {
        String message;

        if (firstName != null && lastName != null) {
            message = "Hello, " + firstName + " " + lastName + "!";
        } else if (firstName != null) {
            message = "Hello, " + firstName + "!";
        } else if (lastName != null) {
            message = "Hello, " + lastName + "!";
        } else {
            message = "Hello World!";
        }

        // Save to database
        Greeting greeting = new Greeting(message);
        Greeting savedGreeting = greetingRepository.save(greeting);

        return "Greeting saved with ID: " + savedGreeting.getId();
    }

    public String findGreetingById(Long id) {
        Optional<Greeting> greeting = greetingRepository.findById(id);
        return greeting.map(Greeting::getMessage).orElse("Greeting not found!");
    }

    public List<String> getAllGreetings() {
        List<Greeting> greetings = greetingRepository.findAll();
        return greetings.stream()
                .map(Greeting::getMessage)
                .collect(Collectors.toList());
    }

    // ✅ **New Method to Update Greeting Message**
    public String updateGreeting(Long id, String newMessage) {
        Optional<Greeting> existingGreeting = greetingRepository.findById(id);

        if (existingGreeting.isPresent()) {
            Greeting greeting = existingGreeting.get();
            greeting.setMessage(newMessage);
            greetingRepository.save(greeting);
            return "Greeting Updated Successfully with ID: " + id;
        } else {
            return "Greeting Not Found!";
        }
    }
}
