package org.mallfoundry.catalog;

import org.mallfoundry.keygen.PrimaryKeyHolder;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.util.CastUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class InternalCategoryService implements CategoryService {

    private static final String CATALOG_CATEGORY_ID_VALUE_NAME = "catalog.category.id";

    private final CategoryRepository categoryRepository;

    private final ApplicationEventPublisher eventPublisher;

    public InternalCategoryService(CategoryRepository categoryRepository,
                                   ApplicationEventPublisher eventPublisher) {
        this.categoryRepository = categoryRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public CategoryId createCategoryId(String categoryId) {
        return new InternalCategoryId(categoryId);
    }

    @Override
    public Category createCategory() {
        return new InternalCategory(PrimaryKeyHolder.next(CATALOG_CATEGORY_ID_VALUE_NAME));
    }

    @Override
    public Optional<Category> getCategory(String id) {
        return CastUtils.cast(categoryRepository.findById(id));
    }

    @Override
    public List<Category> getCategories() {
        return null;
    }

    @Override
    public List<Category> getCategories(String parentId) {
        return null;
    }

    @Transactional
    @Override
    public Category saveCategory(Category category) {
        return null;
    }

    @Transactional
    @Override
    public Category addChildCategory(String id, Category category) {
        this.getCategory(id).orElseThrow().addChildCategory(category);
        return category;
    }

    @Transactional
    @Override
    public void deleteCategory(String categoryId) {
        var category = this.categoryRepository.findById(categoryId).orElseThrow();
        this.eventPublisher.publishEvent(new InternalCategoryDeletedEvent(category));
        this.categoryRepository.delete(category);
    }

}
