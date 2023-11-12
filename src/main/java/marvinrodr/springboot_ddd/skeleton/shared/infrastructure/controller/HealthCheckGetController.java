package marvinrodr.springboot_ddd.skeleton.shared.infrastructure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class HealthCheckGetController extends ApiController {

    @GetMapping("/health-check")
    public ResponseEntity<?> index() {
        return ResponseEntity.ok().build();
    }

}
