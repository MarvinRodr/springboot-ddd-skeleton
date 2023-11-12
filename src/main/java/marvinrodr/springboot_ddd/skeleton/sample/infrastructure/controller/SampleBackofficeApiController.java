package marvinrodr.springboot_ddd.skeleton.sample.infrastructure.controller;

import marvinrodr.springboot_ddd.skeleton.shared.infrastructure.controller.BackofficeApiController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BackofficeApiController.ROOT_PATH + SampleBackofficeApiController.ROOT_PATH)
public class SampleBackofficeApiController extends BackofficeApiController {

    public static final String ROOT_PATH = "/sample";

}
