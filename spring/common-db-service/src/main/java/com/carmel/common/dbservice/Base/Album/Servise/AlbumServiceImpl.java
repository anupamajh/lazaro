package com.carmel.common.dbservice.Base.Album.Servise;

import com.carmel.common.dbservice.Base.Album.Controller.AlbumController;
import com.carmel.common.dbservice.Base.Album.Model.Album;
import com.carmel.common.dbservice.Base.Album.Repository.AlbumRepository;
import com.carmel.common.dbservice.Base.Album.Responce.AlbumResponse;
import com.carmel.common.dbservice.Base.Photo.Model.Photo;
import com.carmel.common.dbservice.Base.Photo.Responce.PhotoResponse;
import com.carmel.common.dbservice.common.Search.SearchBuilder;
import com.carmel.common.dbservice.common.Search.SearchRequest;
import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.model.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.carmel.common.dbservice.Base.Album.Specification.AlbumSpecification.textInAllColumns;

@Service
public class AlbumServiceImpl implements AlbumService {

   private Logger logger = LoggerFactory.getLogger(AlbumController.class);


    @Autowired
    AlbumRepository albumRepository;


    @Autowired
    UserInformation userInformation;

    @Autowired
    AlbumService albumService;

    @Autowired
    EntityManager entityManager;

    @Override
    public AlbumResponse saveAlbum(Album album) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AlbumResponse albumResponse = new AlbumResponse();
        try {
            if (album.getId() == null) {
                album.setId("");
            }
            if (album.getOrgId() == null || album.getOrgId().isEmpty()) {
                if (userInfo.getDefaultOrganization() != null) {
                    album.setOrgId(userInfo.getDefaultOrganization().getId());
                }
            }
            if (album.getId().equals("")) {
                album.setCreatedBy(userInfo.getId());
                album.setCreationTime(new Date());
            } else {
                album.setLastModifiedBy(userInfo.getId());
                album.setLastModifiedTime(new Date());
            }
            album.setClientId(userInfo.getClient().getClientId());
            if (checkDuplicate(album)) {
                albumResponse.setAlbum(album);
                albumResponse.setSuccess(false);
                albumResponse.setError("Duplicate album Name name!");
            } else {
                albumResponse.setAlbum(albumService.save(album));
                albumResponse.setSuccess(true);
                albumResponse.setError("");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        return albumResponse;
    }

    @Override
    public AlbumResponse moveToTrash(Map<String, String> formData) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AlbumResponse albumResponse = new AlbumResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<Album> optionalAlbum = albumService.findById(formData.get("id"));
            if (optionalAlbum.isPresent()) {
                Album album = optionalAlbum.get();
                album.setIsDeleted(1);
                album.setDeletedBy(userInfo.getId());
                album.setDeletedTime(new Date());
                albumResponse.setSuccess(true);
                albumResponse.setError("");
                albumResponse.setAlbum(albumService.save(album));
            } else {
                albumResponse.setSuccess(false);
                albumResponse.setError("Error occurred while moving album to Trash!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        return albumResponse;
    }

    @Override
    public AlbumResponse get(Map<String, String> formData) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AlbumResponse albumResponse = new AlbumResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<Album> optionalAlbum = albumService.findById(formData.get("id"));
            if (optionalAlbum.isPresent()) {
                Album album = optionalAlbum.get();
                albumResponse.setSuccess(true);
                albumResponse.setError("");
                albumResponse.setAlbum(album);
            } else {
                albumResponse.setSuccess(false);
                albumResponse.setError("Error occurred while Fetching amenity!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        logger.trace("Exiting");
        return albumResponse;
    }

    @Override
    public AlbumResponse getDeleted() throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AlbumResponse albumResponse = new AlbumResponse();
        try {
            albumResponse.setAlbumList(albumRepository.findAllByIsDeletedAndClientId(1, userInfo.getClient().getClientId()));
            albumResponse.setSuccess(true);
            albumResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
          throw ex;
        }
        logger.trace("Exiting");
        return albumResponse;
    }

    @Override
    public AlbumResponse getAll() throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AlbumResponse albumResponse = new AlbumResponse();
        try {
            albumResponse.setAlbumList(albumRepository.findAllByIsDeletedAndClientId(0, userInfo.getClient().getClientId()));
            albumResponse.setSuccess(true);
            albumResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        logger.trace("Exiting");
        return albumResponse;
    }

    @Override
    public AlbumResponse getPaginated(Map<String, String> formData) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AlbumResponse albumResponse = new AlbumResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("title"));
            Page<Album> page = albumRepository.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 0, pageable);
            albumResponse.setTotalRecords(page.getTotalElements());
            albumResponse.setTotalPages(page.getTotalPages());
            albumResponse.setAlbumList(page.getContent());
            albumResponse.setCurrentRecords(albumResponse.getAlbumList().size());
            albumResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
          throw ex;
        }
        logger.trace("Exiting");
        return albumResponse;
    }

    @Override
    public AlbumResponse searchPaginated(Map<String, String> formData) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AlbumResponse albumResponse = new AlbumResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String searchText = formData.get("search_text") == null ? null : String.valueOf(formData.get("search_text"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("title"));
            Page<Album> page;
            if (searchText == null) {
                page = albumRepository.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 0, pageable);
            } else {
                page = albumRepository.findAll(textInAllColumns(searchText, userInfo.getClient().getClientId()), pageable);
            }
            albumResponse.setTotalRecords(page.getTotalElements());
            albumResponse.setTotalPages(page.getTotalPages());
            albumResponse.setAlbumList(page.getContent());
            albumResponse.setCurrentRecords(albumResponse.getAlbumList().size());
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            albumResponse.setSuccess(false);
            albumResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return albumResponse;
    }

    @Override
    public AlbumResponse search(SearchRequest searchRequest) throws Exception {
        AlbumResponse albumResponse = new AlbumResponse();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Album> criteriaQuery = criteriaBuilder.createQuery(Album.class);
            Root<Album> root = criteriaQuery.from(Album.class);
            criteriaQuery = SearchBuilder.buildSearch(
                    entityManager,
                    criteriaBuilder,
                    criteriaQuery,
                    root,
                    Album.class,
                    searchRequest
            );
            long totalRecords = SearchBuilder.getTotalRecordCount(
                    entityManager,
                    criteriaBuilder,
                    criteriaQuery,
                    root
            );
            TypedQuery<Album> typedQuery = entityManager.createQuery(criteriaQuery);
            typedQuery.setFirstResult((searchRequest.getCurrentPage() - 1) * searchRequest.getPageSize());
            typedQuery.setMaxResults(searchRequest.getPageSize());
            List<Album> albumList = typedQuery.getResultList();
            albumResponse.setCurrentRecords(albumList.size());
            albumResponse.setTotalRecords(totalRecords);
            albumResponse.setSuccess(true);
            albumResponse.setError("");
            albumResponse.setAlbumList(albumList);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.toString(), ex);
            albumResponse.setSuccess(false);
            albumResponse.setError(ex.getMessage());
        }
        return albumResponse;
    }
    private boolean checkDuplicate(Album album) {
        List<Album> albumList;
        if (album.getId().equals("")) {
            albumList = albumRepository.findAllByClientIdAndTitle(album.getClientId(), album.getTitle());
        } else {
            albumList = albumRepository.findAllByClientIdAndTitleAndIdIsNot(
                    album.getClientId(), album.getTitle(), album.getId());
        }
        if (albumList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public AlbumResponse findAllByClientIdAndTitle(String clientId, String title) {
        return (AlbumResponse) albumRepository.findAllByClientIdAndTitle(clientId, title);
    }

    @Override
    public Album save(Album album) {
        return albumRepository.save(album);
    }

    @Override
    public AlbumResponse findAllByClientIdAndTitleAndIdIsNot(String clientId, String title, String id) {
        AlbumResponse albumResponse = new AlbumResponse();
        albumResponse.setSuccess(true);
        albumResponse.setAlbumList(albumRepository.findAllByClientIdAndTitleAndIdIsNot(clientId,title,id));
        return albumResponse;
    }

    @Override
    public Optional<Album> findById(String id) {
        return albumRepository.findById(id);
    }

    @Override
    public AlbumResponse findAllByIsDeletedAndClientId(int isDeleted, String clientId) {
        AlbumResponse albumResponse = new AlbumResponse();
        albumResponse.setSuccess(true);
        albumResponse.setAlbumList(albumRepository.findAllByIsDeletedAndClientId(isDeleted,clientId));
        return albumResponse;
    }

    @Override
    public AlbumResponse findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable) {
        AlbumResponse albumResponse = new AlbumResponse();
        albumResponse.setSuccess(true);
        Page<Album> page = albumRepository.findAllByClientIdAndIsDeleted(clientId,isDeleted, pageable);
        albumResponse.setAlbumList(page.getContent());
        return albumResponse;
    }

    @Override
    public AlbumResponse findAll(Specification<Album> textInAllColumns, Pageable pageable) {
        AlbumResponse albumResponse = new AlbumResponse();
        albumResponse.setSuccess(true);
        Page<Album> page = albumRepository.findAll(textInAllColumns,pageable);
        albumResponse.setAlbumList(page.getContent());
        return albumResponse;
    }
}
