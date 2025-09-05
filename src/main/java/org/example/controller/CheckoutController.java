package org.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin(origins = {"http://localhost:5173", "http://192.168.1.4:5173"})
public class CheckoutController {

    @PostMapping("/product/checkout")
    public ResponseEntity<Map<String, Object>> startCheckout() {
        // Placeholder response – integrate payment provider as needed
        return ResponseEntity.ok(Map.of(
                "orderId", UUID.randomUUID().toString(),
                "status", "CREATED"
        ));
    }
}