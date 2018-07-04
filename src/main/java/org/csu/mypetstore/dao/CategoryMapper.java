package org.csu.mypetstore.dao;

import org.csu.mypetstore.domain.Category;

import java.util.List;

public interface CategoryMapper {

    List<Category> getCategoryList();

    Category getCategory(String categoryId);
}
