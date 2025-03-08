package com.example.GreetingApp.controller;

import com.example.GreetingApp.entity.Greeting;
import com.example.GreetingApp.service.GreetingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/greetings")
public class GreetingController {

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/simple")
    public String getSimpleGreeting() {
        return greetingService.getSimpleGreeting();
    }

    @GetMapping("/personalized")
    public String getPersonalizedGreeting(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName) {
        return greetingService.getGreeting(firstName, lastName);
    }

    @GetMapping("/{id}")
    public String getGreetingById(@PathVariable Long id) {
        return greetingService.findGreetingById(id);
    }

    @GetMapping
    public List<String> getAllGreetings() {
        return greetingService.getAllGreetings();
    }

    // ✅ **NEW API to Update Greeting Message by ID**
    @PutMapping("/{id}")
    public ResponseEntity<String> updateGreeting(@PathVariable Long id, @RequestBody Greeting greeting) {
        String response = greetingService.updateGreeting(id, greeting.getMessage());
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGreeting(@PathVariable Long id) {
        String response = greetingService.deleteGreeting(id);
        return ResponseEntity.ok(response);
    }
}
