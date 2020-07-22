package karol.spring.webfluxrest.controllers;

import karol.spring.webfluxrest.models.Vendor;
import karol.spring.webfluxrest.repositories.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

class VendorControllerTest {

    WebTestClient webTestClient;

    @Mock
    VendorRepository vendorRepository;

    @InjectMocks
    VendorController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        webTestClient = WebTestClient.bindToController(controller).build();
    }

    @Test
    void list() {
        Vendor vendor = new Vendor();
        vendor.setFirstName("Karol");
        Vendor vendor1 = new Vendor();
        vendor1.setFirstName("Patrycja");

        BDDMockito.given(vendorRepository.findAll())
                .willReturn(Flux.just(vendor, vendor1));

        webTestClient.get().uri("/api/v1/vendors/")
                .exchange()
                .expectBodyList(Vendor.class)
                .hasSize(2);
    }

    @Test
    void getById() {
        Vendor vendor = new Vendor();
        vendor.setId("1qwe");
        vendor.setFirstName("Karol");

        BDDMockito.given(vendorRepository.findById("someId"))
                .willReturn(Mono.just(vendor));

        webTestClient.get().uri("/api/v1/vendors/1qwe")
                .exchange()
                .expectBody(Vendor.class);
    }
}