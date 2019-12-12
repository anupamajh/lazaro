package com.carmel.common.dbservice.repository;

import com.carmel.common.dbservice.model.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, String> {
    List<Album> findAllByClientIdAndTitle(String clientId, String title);

    List<Album> findAllByClientIdAndTitleAndIdIsNot(String clientId, String title, String id);

    List<Album> findAllByIsDeletedAndClientId(int isDeleted, String clientId);

    Page<Album> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable);

    Page<Album> findAll(Specification<Album> textInAllColumns, Pageable pageable);
}
