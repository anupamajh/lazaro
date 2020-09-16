package com.carmel.common.dbservice.Base.Photo.Repository;

import com.carmel.common.dbservice.Base.Album.Model.Album;
import com.carmel.common.dbservice.Base.Photo.Model.Photo;
import com.carmel.common.dbservice.Base.Photo.Responce.PhotoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, String> {
    List<Photo> findAllByClientIdAndAlbumIsNotAndNameAndIsDeleted(String clientId, Album album, String name, int i);

    List<Photo> findAllByClientIdAndAlbumIsNotAndNameAndIdIsNotAndIsDeleted(String clientId, Album album, String name, String id, int i);

    List<Photo> findAllByIsDeletedAndClientIdAndAlbum(int isDeleted, String clientId, Album album);

    Page<Photo> findAllByIsDeletedAndClientIdAndAlbum(int isDeleted, String clientId, Album album, Pageable pageable);

    Page<Photo> findAll(Specification<Photo> textInAllColumns, Pageable pageable);

    List findAllByClientIdAndAlbumIsNotAndName(String clientId, Album album, String name);

    List findAllByClientIdAndAlbumIsNotAndNameAndIdIsNot(String clientId, Album album, String name, String id);

}
