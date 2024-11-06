package com.gapsi.providers;

import com.gapsi.providers.beans.BeanProvider;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;

import java.util.List;

@RestController
@RequestMapping("/providers")
public class ProviderController {

    private final ProviderService providerService;

    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @GetMapping(value = "/paging", produces = MediaType.APPLICATION_JSON_VALUE)
    String getProviders(@RequestParam(defaultValue = "0") Integer page,
                        @RequestParam(defaultValue = "10") Integer size){
        return providerService.getProviders(page, size);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<BeanProvider> getProviders(){
        return providerService.getProviders();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity create(@RequestBody BeanProvider beanProvider){
        return providerService.createProvider(beanProvider);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Object> delete(@PathVariable String id){
        return providerService.deleteProvider(id);
    }

}
