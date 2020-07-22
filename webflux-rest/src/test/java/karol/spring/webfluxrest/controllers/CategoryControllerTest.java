package karol.spring.webfluxrest.controllers;

import karol.spring.webfluxrest.models.Category;
import karol.spring.webfluxrest.repositories.CategoryRepository;
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

class CategoryControllerTest {

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryController controller;

    WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        webTestClient = WebTestClient.bindToController(controller).build();
    }

    @Test
    void list() {

        Category category = new Category();
        category.setDescription("test1");
        Category category1 = new Category();
        category1.setDescription("test2");

        BDDMockito.given(categoryRepository.findAll())
                .willReturn(Flux.just(category, category1));

        webTestClient.get().uri("/api/v1/categories/")
                .exchange()
                .expectBodyList(Category.class)
                .hasSize(2);
    }

    @Test
    void getById() {

        Category category = new Category();
        category.setId("1qwe");
        category.setDescription("Test");

        BDDMockito.given(categoryRepository.findById("someId"))
                .willReturn(Mono.just(category));

        webTestClient.get().uri("/api/v1/categories/1qwe")
                .exchange()
                .expectBody(Category.class);
    }
}