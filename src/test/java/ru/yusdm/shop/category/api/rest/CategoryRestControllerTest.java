package ru.yusdm.shop.category.api.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.yusdm.shop.category.domain.Category;
import ru.yusdm.shop.category.dto.CategoryDomainDtoMapper;
import ru.yusdm.shop.category.dto.CategoryDto;
import ru.yusdm.shop.category.service.CategoryService;
import ru.yusdm.shop.integration.fixer.api.rest.FixerRestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CategoryRestController.class)
public class CategoryRestControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private CategoryDomainDtoMapper categoryDomainDtoMapper;

    @MockBean
    FixerRestClient fixerRestClient;

    @Test
    public void testGetAll() throws Exception {
        List<Category> expectedDomains = getExpectedDomains();
        List<CategoryDto> expectedDtos = expectedDomains.stream().map(domain -> {
            CategoryDto dto = new CategoryDto();
            dto.setId(domain.getId());
            return dto;
        }).collect(Collectors.toList());

        BDDMockito.given(categoryService.getAll()).willReturn(expectedDomains);
        BDDMockito.given(categoryDomainDtoMapper.fromDomainsToDtos(expectedDomains)).willReturn(expectedDtos);
        MvcResult result = mockMvc.perform
                (MockMvcRequestBuilders.get(CategoryRestController.CATEGORY_BASE_REST_CONTROLLER_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String actualResponse = result.getResponse().getContentAsString();
        String expectedResponseBody = objectMapper.writeValueAsString(expectedDtos);

        assertThat(expectedResponseBody)
                .isEqualToIgnoringWhitespace(actualResponse);
    }

    private List<Category> getExpectedDomains() {
        List<Category> dtos = new ArrayList<>();
        Category category = new Category();
        category.setId(UUID.randomUUID().toString());
        dtos.add(category);

        category = new Category();
        category.setId(UUID.randomUUID().toString());
        dtos.add(category);

        return dtos;
    }

    @Test
    public void testGetById() throws Exception{
        Category expectedDomain = new Category();
        String id = UUID.randomUUID().toString();
        expectedDomain.setId(id);

        CategoryDto expectedDto = new CategoryDto();
        expectedDto.setId(UUID.randomUUID().toString());

        BDDMockito.given(categoryService.findById(id)).willReturn(Optional.of(expectedDomain));
        BDDMockito.given(categoryDomainDtoMapper.fromDomainToDto(expectedDomain)).willReturn(expectedDto);
        MvcResult result = mockMvc.perform
                (MockMvcRequestBuilders.get(CategoryRestController.CATEGORY_BASE_REST_CONTROLLER_URL + "/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String actualResponse = result.getResponse().getContentAsString();
        String expectedResponseBody = objectMapper.writeValueAsString(expectedDto);
        assertThat(expectedResponseBody)
                .isEqualToIgnoringWhitespace(actualResponse);

    }


}
