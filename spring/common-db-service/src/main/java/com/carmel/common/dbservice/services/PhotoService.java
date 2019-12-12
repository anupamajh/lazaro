package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.Album;
import com.carmel.common.dbservice.model.Photo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface PhotoService {
    Photo save(Photo photo);

    List<Photo> findAllByClientIdAndAlbumIsNotAndName(String clientId, Album album, String name);

    List<Photo> findAllByClientIdAndAlbumIsNotAndNameAndIdIsNot(String clientId, Album album, String name, String id);

    Optional<Photo> findById(String id);

    List<Photo> findAllByIsDeletedAndClientIdAndAlbum(int isDeleted, String clientId, Album album);

    Page<Photo> findAllByIsDeletedAndClientIdAndAlbum(int isDeleted, String clientId, Album album, Pageable pageable);

    Page<Photo> findAll(Specification<Photo> textInAllColumns, Pageable pageable);
}
