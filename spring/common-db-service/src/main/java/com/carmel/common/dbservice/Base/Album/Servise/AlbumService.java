package com.carmel.common.dbservice.Base.Album.Servise;

import com.carmel.common.dbservice.Base.Album.Model.Album;
import com.carmel.common.dbservice.Base.Album.Repository.AlbumRepository;
import com.carmel.common.dbservice.Base.Album.Responce.AlbumResponse;
import com.carmel.common.dbservice.common.Search.SearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AlbumService {
    Album save(Album album) throws Exception;

    Optional<Album> findById(String id) throws Exception;

    AlbumResponse saveAlbum(Album album) throws Exception;

    AlbumResponse moveToTrash(Map<String, String> formData) throws Exception;

    AlbumResponse get(Map<String, String> formData) throws Exception;

    AlbumResponse getDeleted() throws Exception;

    AlbumResponse getAll() throws Exception;

    AlbumResponse getPaginated(Map<String, String> formData) throws Exception;

    AlbumResponse searchPaginated(Map<String, String> formData) throws Exception;

    AlbumResponse search(SearchRequest searchRequest) throws Exception;

    AlbumResponse findAllByClientIdAndTitle(String clientId, String title) throws Exception;

    AlbumResponse findAllByClientIdAndTitleAndIdIsNot(String clientId, String title, String id) throws Exception;

    AlbumResponse findAllByIsDeletedAndClientId(int isDeleted, String clientId) throws Exception;

    AlbumResponse findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable) throws Exception;

    AlbumResponse findAll(Specification<Album> textInAllColumns, Pageable pageable) throws Exception;
}
