package karol.spring.webfluxrest.controllers;

import karol.spring.webfluxrest.models.Category;
import karol.spring.webfluxrest.repositories.CategoryRepository;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;


@RestController
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    //Mono - 1 or 0
    //Flux - 0 or Many

    @GetMapping("/api/v1/categories/")
    Flux<Category> list(){
        return categoryRepository.findAll();
    }

    @GetMapping("/api/v1/categories/{id}")
    Mono<Category> getById(@PathVariable String id){
        return categoryRepository.findById(id);
    }

    @PostMapping("/api/v1/categories/")
    @ResponseStatus(HttpStatus.CREATED)
    Mono<Void> createCategory(Publisher<Category> categoryStream){
        return categoryRepository.saveAll(categoryStream).then();
    }

    @PutMapping("/api/v1/categories/{id}")
    Mono<Category> updateCategory(@PathVariable String id, @RequestBody Category category){
        category.setId(id);
        return  categoryRepository.save(category);
    }

    @PatchMapping("/api/v1/categories/{id}")
    Mono<Category> patchCategory(@PathVariable String id, @RequestBody Category category){

        Mono<Category> foundCategory = categoryRepository.findById(id);

        return foundCategory.filter(found ->
            !Objects.equals(found.getDescription(), category.getDescription()))
                .flatMap(f ->{
                    f.setDescription(category.getDescription());
                    return categoryRepository.save(f);
                }).switchIfEmpty(foundCategory);

    }
}
