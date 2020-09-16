package com.carmel.common.dbservice.Base.Photo.Service;

import com.carmel.common.dbservice.Base.Album.Controller.AlbumController;
import com.carmel.common.dbservice.Base.Album.Model.Album;
import com.carmel.common.dbservice.Base.Album.Servise.AlbumService;
import com.carmel.common.dbservice.Base.Photo.Model.Photo;
import com.carmel.common.dbservice.Base.Photo.Repository.PhotoRepository;
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

import static com.carmel.common.dbservice.Base.Photo.Specification.PhotoSpecification.textInAllColumns;

@Service
public class PhotoServiceImpl implements PhotoService {

  private   Logger logger = LoggerFactory.getLogger(PhotoServiceImpl.class);

  private  ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    UserInformation userInformation;

    @Autowired
    PhotoService photoService;

    @Autowired
    AlbumService albumService;

    @Autowired
    EntityManager entityManager;

    @Override
    public PhotoResponse savePhoto(Photo photo) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        PhotoResponse photoResponse = new PhotoResponse();
        try {
            if (photo.getId() == null) {
                photo.setId("");
            }
            if (photo.getOrgId() == null || photo.getOrgId().isEmpty()) {
                if (userInfo.getDefaultOrganization() != null) {
                    photo.setOrgId(userInfo.getDefaultOrganization().getId());
                }
            }
            if (photo.getId().equals("")) {
                photo.setCreatedBy(userInfo.getId());
                photo.setCreationTime(new Date());
            } else {
                photo.setLastModifiedBy(userInfo.getId());
                photo.setLastModifiedTime(new Date());
            }
            photo.setClientId(userInfo.getClient().getClientId());
            if (checkDuplicate(photo)) {
                photoResponse.setPhoto(photo);
                photoResponse.setSuccess(false);
                photoResponse.setError("Duplicate photo Name !");
            } else {
                photoResponse.setPhoto(photoService.save(photo));
                photoResponse.setSuccess(true);
                photoResponse.setError("");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
          throw ex;
        }
        return photoResponse;
    }

    @Override
    public PhotoResponse moveToTrash(Map<String, String> formData) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        PhotoResponse photoResponse = new PhotoResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<Photo> optionalPhoto = photoService.findById(formData.get("id"));
            if (optionalPhoto.isPresent()) {
                Photo photo = optionalPhoto.get();
                photo.setIsDeleted(1);
                photo.setDeletedBy(userInfo.getId());
                photo.setDeletedTime(new Date());
                photoResponse.setSuccess(true);
                photoResponse.setError("");
                photoResponse.setPhoto(photoService.save(photo));
            } else {
                photoResponse.setSuccess(false);
                photoResponse.setError("Error occurred while moving photo to Trash!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
           throw ex;
        }
        return photoResponse;
    }

    @Override
    public PhotoResponse get(Map<String, String> formData) throws Exception {
        logger.trace("Entering");
        PhotoResponse photoResponse = new PhotoResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<Photo> optionalPhoto = photoService.findById(formData.get("id"));
            if (optionalPhoto.isPresent()) {
                Photo photo = optionalPhoto.get();
                photoResponse.setSuccess(true);
                photoResponse.setError("");
                photoResponse.setPhoto(photo);
            } else {
                photoResponse.setSuccess(false);
                photoResponse.setError("Error occurred while Fetching amenity!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        throw ex;
        }
        logger.trace("Exiting");
        return photoResponse;
    }

    @Override
    public PhotoResponse getDeleted(Map<String, String> formData) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();

        logger.trace("Entering");
        PhotoResponse photoResponse = new PhotoResponse();
        try {
            Optional<Album> optionalAlbum = albumService.findById(formData.get("album_id"));
            if (optionalAlbum.isPresent()) {
                photoResponse.setPhotoList(photoRepository.findAllByIsDeletedAndClientIdAndAlbum(1, userInfo.getClient().getClientId(), optionalAlbum.get()));
                photoResponse.setSuccess(true);
                photoResponse.setError("");
                logger.trace("Completed Successfully");
            } else {
                photoResponse.setSuccess(false);
                photoResponse.setError("Album not found");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
           throw ex;
        }
        logger.trace("Exiting");
        return photoResponse;
    }

    @Override
    public PhotoResponse getAll(Map<String, String> formData) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        PhotoResponse photoResponse = new PhotoResponse();
        try {
            Optional<Album> optionalAlbum = albumService.findById(formData.get("album_id"));
            if (optionalAlbum.isPresent()) {
                photoResponse.setPhotoList(photoRepository.findAllByIsDeletedAndClientIdAndAlbum(0, userInfo.getClient().getClientId(), optionalAlbum.get()));
                photoResponse.setSuccess(true);
                photoResponse.setError("");
                logger.trace("Completed Successfully");
            } else {
                photoResponse.setSuccess(false);
                photoResponse.setError("Album not found");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
                throw ex;
        }
        logger.trace("Exiting");
        return photoResponse;
    }

    @Override
    public PhotoResponse getPaginated(Map<String, String> formData) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        PhotoResponse photoResponse = new PhotoResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<Album> optionalAlbum = albumService.findById(formData.get("album_id"));
            if (optionalAlbum.isPresent()) {
                int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
                int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
                Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
                Page<Photo> page = photoRepository.findAllByIsDeletedAndClientIdAndAlbum(0, userInfo.getClient().getClientId(), optionalAlbum.get(), pageable);
                photoResponse.setTotalRecords(page.getTotalElements());
                photoResponse.setTotalPages(page.getTotalPages());
                photoResponse.setPhotoList(page.getContent());
                photoResponse.setCurrentRecords(photoResponse.getPhotoList().size());
                photoResponse.setSuccess(true);
                logger.trace("Completed Successfully");
            } else {
                photoResponse.setSuccess(false);
                photoResponse.setError("Album not found");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
                throw ex;
        }
        logger.trace("Exiting");
        return photoResponse;
    }

    @Override
    public PhotoResponse searchPaginated(Map<String, String> formData) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        PhotoResponse photoResponse = new PhotoResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String searchText = formData.get("search_text") == null ? null : String.valueOf(formData.get("search_text"));
            String albumId = formData.get("album_id") == null ? null : String.valueOf(formData.get("album_id"));
            if (albumId != null) {
                Optional<Album> optionalAlbum = albumService.findById(formData.get("album_id"));
                if (optionalAlbum.isPresent()) {
                    Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
                    Page<Photo> page;
                    if (searchText == null) {
                        page = photoRepository.findAllByIsDeletedAndClientIdAndAlbum(0, userInfo.getClient().getClientId(), optionalAlbum.get(), pageable);
                    } else {
                        page = photoRepository.findAll(textInAllColumns(searchText, userInfo.getClient().getClientId(), optionalAlbum.get()), pageable);
                    }
                    photoResponse.setTotalRecords(page.getTotalElements());
                    photoResponse.setTotalPages(page.getTotalPages());
                    photoResponse.setPhotoList(page.getContent());
                    photoResponse.setCurrentRecords(photoResponse.getPhotoList().size());
                    logger.trace("Completed Successfully");
                } else {
                    photoResponse.setSuccess(false);
                    photoResponse.setError("Album not found");
                }
            } else {
                photoResponse.setSuccess(false);
                photoResponse.setError("Album not found");
            }

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
                throw ex;
        }
        logger.trace("Exiting");
        return photoResponse;
    }

    @Override
    public PhotoResponse search(SearchRequest searchRequest) throws Exception {
        PhotoResponse photoResponse = new PhotoResponse();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Photo> criteriaQuery = criteriaBuilder.createQuery(Photo.class);
            Root<Photo> root = criteriaQuery.from(Photo.class);
            criteriaQuery = SearchBuilder.buildSearch(
                    entityManager,
                    criteriaBuilder,
                    criteriaQuery,
                    root,
                    Photo.class,
                    searchRequest
            );
            long totalRecords = SearchBuilder.getTotalRecordCount(
                    entityManager,
                    criteriaBuilder,
                    criteriaQuery,
                    root
            );
            TypedQuery<Photo> typedQuery = entityManager.createQuery(criteriaQuery);
            typedQuery.setFirstResult((searchRequest.getCurrentPage() - 1) * searchRequest.getPageSize());
            typedQuery.setMaxResults(searchRequest.getPageSize());
            List<Photo> photoList = typedQuery.getResultList();
            photoResponse.setCurrentRecords(photoList.size());
            photoResponse.setTotalRecords(totalRecords);
            photoResponse.setSuccess(true);
            photoResponse.setError("");
            photoResponse.setPhotoList(photoList);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.toString(), ex);
            photoResponse.setSuccess(false);
            photoResponse.setError(ex.getMessage());
        }
        return photoResponse;
    }

    private boolean checkDuplicate(Photo photo) {
        List<Photo> photoList;
        if (photo.getId().equals("")) {
            photoList = photoRepository.findAllByClientIdAndAlbumIsNotAndName(photo.getClientId(), photo.getAlbum(), photo.getName());
        } else {
            photoList = photoRepository.findAllByClientIdAndAlbumIsNotAndNameAndIdIsNot(
                    photo.getClientId(), photo.getAlbum(), photo.getName(), photo.getId());
        }
        if (photoList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public Photo save(Photo photo) {
        return photoRepository.save(photo);
    }

    @Override
    public PhotoResponse findAllByClientIdAndAlbumIsNotAndName(String clientId, Album album, String name) {
        return (PhotoResponse) photoRepository.findAllByClientIdAndAlbumIsNotAndNameAndIsDeleted(clientId, album, name, 0);
    }

    @Override
    public PhotoResponse findAllByClientIdAndAlbumIsNotAndNameAndIdIsNot(String clientId, Album album, String name, String id) {
        return (PhotoResponse) photoRepository.findAllByClientIdAndAlbumIsNotAndNameAndIdIsNotAndIsDeleted(clientId, album, name, id, 0);
    }

    @Override
    public Optional<Photo> findById(String id) {
        return photoRepository.findById(id);
    }

    @Override
    public PhotoResponse findAllByIsDeletedAndClientIdAndAlbum(int isDeleted, String clientId, Album album) {
        return (PhotoResponse) photoRepository.findAllByIsDeletedAndClientIdAndAlbum(isDeleted, clientId, album);
    }

    @Override
    public PhotoResponse findAllByIsDeletedAndClientIdAndAlbum(int isDeleted, String clientId, Album album, Pageable pageable) {
        return (PhotoResponse) photoRepository.findAllByIsDeletedAndClientIdAndAlbum(isDeleted, clientId, album, pageable);
    }

    @Override
    public PhotoResponse findAll(Specification<Photo> textInAllColumns, Pageable pageable) {
        return (PhotoResponse) photoRepository.findAll(textInAllColumns, pageable);
    }
}
