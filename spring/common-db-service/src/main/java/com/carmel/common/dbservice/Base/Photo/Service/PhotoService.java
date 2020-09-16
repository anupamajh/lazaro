package com.carmel.common.dbservice.Base.Photo.Service;

import com.carmel.common.dbservice.Base.Album.Model.Album;
import com.carmel.common.dbservice.Base.Photo.Model.Photo;
import com.carmel.common.dbservice.Base.Photo.Responce.PhotoResponse;
import com.carmel.common.dbservice.common.Search.SearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PhotoService {
    Photo save(Photo photo) throws Exception;

    Optional<Photo> findById(String id)throws Exception;

    PhotoResponse savePhoto(Photo photo) throws Exception;

    PhotoResponse moveToTrash(Map<String, String> formData) throws Exception;

    PhotoResponse get(Map<String, String> formData) throws Exception;

    PhotoResponse getDeleted(Map<String, String> formData) throws Exception;

    PhotoResponse getAll(Map<String, String> formData) throws Exception;

    PhotoResponse getPaginated(Map<String, String> formData) throws Exception;

    PhotoResponse searchPaginated(Map<String, String> formData) throws Exception;

    PhotoResponse search(SearchRequest searchRequest) throws Exception;

    PhotoResponse findAllByClientIdAndAlbumIsNotAndName(String clientId, Album album, String name)throws Exception;

    PhotoResponse findAllByClientIdAndAlbumIsNotAndNameAndIdIsNot(String clientId, Album album, String name, String id) throws Exception;

    PhotoResponse findAllByIsDeletedAndClientIdAndAlbum(int isDeleted, String clientId, Album album)throws Exception;

    PhotoResponse findAllByIsDeletedAndClientIdAndAlbum(int isDeleted, String clientId, Album album, Pageable pageable)throws Exception;

    PhotoResponse findAll(Specification<Photo> textInAllColumns, Pageable pageable)throws Exception;
}
