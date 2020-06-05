package com.carmel.common.dbservice.controller;

import com.carmel.common.dbservice.common.Search.SearchBuilder;
import com.carmel.common.dbservice.common.Search.SearchRequest;
import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.model.Album;
import com.carmel.common.dbservice.model.Photo;
import com.carmel.common.dbservice.model.UserInfo;
import com.carmel.common.dbservice.response.PhotoResponse;
import com.carmel.common.dbservice.services.AlbumService;
import com.carmel.common.dbservice.services.PhotoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.carmel.common.dbservice.specifications.PhotoSpecification.textInAllColumns;

@RestController
@RequestMapping(value = "/photos")
public class PhotoController {
    Logger logger = LoggerFactory.getLogger(AlbumController.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    PhotoService photoService;

    @Autowired
    AlbumService albumService;

    @Autowired
    EntityManager entityManager;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public PhotoResponse save(@Valid @RequestBody Photo photo) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
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
            photoResponse.setSuccess(false);
            photoResponse.setError(ex.getMessage());
        }
        return photoResponse;
    }

    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public PhotoResponse moveToTrash(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
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
            photoResponse.setSuccess(false);
            photoResponse.setError(ex.getMessage());
        }
        return photoResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public PhotoResponse get(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
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
            photoResponse.setSuccess(false);
            photoResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return photoResponse;
    }

    @RequestMapping(value = "/get-deleted", method = RequestMethod.POST)
    public PhotoResponse getDeleted(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        PhotoResponse photoResponse = new PhotoResponse();
        try {
            Optional<Album> optionalAlbum = albumService.findById(formData.get("album_id"));
            if (optionalAlbum.isPresent()) {
                photoResponse.setPhotoList(photoService.findAllByIsDeletedAndClientIdAndAlbum(1, userInfo.getClient().getClientId(), optionalAlbum.get()));
                photoResponse.setSuccess(true);
                photoResponse.setError("");
                logger.trace("Completed Successfully");
            } else {
                photoResponse.setSuccess(false);
                photoResponse.setError("Album not found");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            photoResponse.setSuccess(true);
            photoResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return photoResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public PhotoResponse getAll(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        PhotoResponse photoResponse = new PhotoResponse();
        try {
            Optional<Album> optionalAlbum = albumService.findById(formData.get("album_id"));
            if (optionalAlbum.isPresent()) {
                photoResponse.setPhotoList(photoService.findAllByIsDeletedAndClientIdAndAlbum(0, userInfo.getClient().getClientId(), optionalAlbum.get()));
                photoResponse.setSuccess(true);
                photoResponse.setError("");
                logger.trace("Completed Successfully");
            } else {
                photoResponse.setSuccess(false);
                photoResponse.setError("Album not found");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            photoResponse.setSuccess(true);
            photoResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return photoResponse;
    }

    @RequestMapping(value = "/get-photos", method = RequestMethod.POST)
    public PhotoResponse getPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        PhotoResponse photoResponse = new PhotoResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<Album> optionalAlbum = albumService.findById(formData.get("album_id"));
            if (optionalAlbum.isPresent()) {
                int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
                int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
                Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
                Page<Photo> page = photoService.findAllByIsDeletedAndClientIdAndAlbum(0, userInfo.getClient().getClientId(), optionalAlbum.get(), pageable);
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
            photoResponse.setSuccess(false);
            photoResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return photoResponse;
    }

    @RequestMapping(value = "/search-photos", method = RequestMethod.POST)
    public PhotoResponse searchPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
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
                        page = photoService.findAllByIsDeletedAndClientIdAndAlbum(0, userInfo.getClient().getClientId(), optionalAlbum.get(), pageable);
                    } else {
                        page = photoService.findAll(textInAllColumns(searchText, userInfo.getClient().getClientId(), optionalAlbum.get()), pageable);
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
            photoResponse.setSuccess(false);
            photoResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return photoResponse;
    }

    private boolean checkDuplicate(Photo photo) {
        List<Photo> photoList;
        if (photo.getId().equals("")) {
            photoList = photoService.findAllByClientIdAndAlbumIsNotAndName(photo.getClientId(), photo.getAlbum(), photo.getName());
        } else {
            photoList = photoService.findAllByClientIdAndAlbumIsNotAndNameAndIdIsNot(
                    photo.getClientId(), photo.getAlbum(), photo.getName(), photo.getId());
        }
        if (photoList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public PhotoResponse search(@RequestBody SearchRequest searchRequest) {
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

}
