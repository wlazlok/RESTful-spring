package karol.spring.rest.api.v1.mapper;

import karol.spring.rest.api.v1.model.CategoryDTO;
import karol.spring.rest.models.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {


    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    void categoryToCategoryDTO() {

        Category category = new Category();
        category.setName("Test");
        category.setId(1L);

        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        assertEquals(Long.valueOf(1L), categoryDTO.getId());
        assertEquals("Test", categoryDTO.getName());
    }
}