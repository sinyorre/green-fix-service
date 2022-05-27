package com.pluvio.codecrafters.model.repository;

import com.pluvio.codecrafters.model.entity.QuestionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by salagoz on May, 2022
 */
@Repository
public interface ItemRepository extends PagingAndSortingRepository<QuestionEntity, Long> {
    Page<QuestionEntity> findByTags(String tags, Pageable pageable);
}
