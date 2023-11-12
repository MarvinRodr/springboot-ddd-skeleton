package marvinrodr.springboot_ddd.skeleton.sample.infrastructure.controller.create;

import marvinrodr.springboot_ddd.skeleton.sample.application.create.SampleCreator;
import marvinrodr.springboot_ddd.skeleton.sample.application.SampleRequest;
import marvinrodr.springboot_ddd.skeleton.sample.infrastructure.controller.SampleBackofficeApiController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class PostSampleBackofficeApiController extends SampleBackofficeApiController {

    private final SampleCreator sampleCreator;

    public PostSampleBackofficeApiController(final SampleCreator sampleCreator) {
        this.sampleCreator = sampleCreator;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> index(@RequestBody SampleRequest sampleRequest) {

        this.sampleCreator.create(sampleRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
