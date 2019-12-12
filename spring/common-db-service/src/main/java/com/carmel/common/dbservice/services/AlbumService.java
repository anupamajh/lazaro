package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface AlbumService {
    List<Album> findAllByClientIdAndTitle(String clientId, String title);

    List<Album> findAllByClientIdAndTitleAndIdIsNot(String clientId, String title, String id);

    Album save(Album album);

    Optional<Album> findById(String id);

    List<Album> findAllByIsDeletedAndClientId(int isDeleted, String clientId);

    Page<Album> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable);

    Page<Album> findAll(Specification<Album> textInAllColumns, Pageable pageable);
}
