package com.carmel.common.dbservice.controller;

import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.model.Album;
import com.carmel.common.dbservice.model.UserInfo;
import com.carmel.common.dbservice.response.AlbumResponse;
import com.carmel.common.dbservice.services.AlbumService;
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

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.carmel.common.dbservice.specifications.AlbumSpecification.textInAllColumns;

@RestController
@RequestMapping(value = "/album")
public class AlbumController {
    Logger logger = LoggerFactory.getLogger(AlbumController.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    AlbumService albumService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AlbumResponse save(@Valid @RequestBody Album album) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AlbumResponse albumResponse = new AlbumResponse();
        try {
            if (album.getId() == null) {
                album.setId("");
            }
            if(album.getOrgId() == null || album.getOrgId().isEmpty()){
                album.setOrgId(userInfo.getDefaultOrganization().getId());
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
            albumResponse.setSuccess(false);
            albumResponse.setError(ex.getMessage());
        }
        return albumResponse;
    }

    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public AlbumResponse moveToTrash(@RequestBody Map<String, String> formData) {
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
            albumResponse.setSuccess(false);
            albumResponse.setError(ex.getMessage());
        }
        return albumResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public AlbumResponse get(@RequestBody Map<String, String> formData) {
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
            albumResponse.setSuccess(false);
            albumResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return albumResponse;
    }

    @RequestMapping(value = "/get-deleted", method = RequestMethod.POST)
    public AlbumResponse getDeleted() {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AlbumResponse albumResponse = new AlbumResponse();
        try {
            albumResponse.setAlbumList(albumService.findAllByIsDeletedAndClientId(1, userInfo.getClient().getClientId()));
            albumResponse.setSuccess(true);
            albumResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            albumResponse.setSuccess(true);
            albumResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return albumResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public AlbumResponse getAll() {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AlbumResponse albumResponse = new AlbumResponse();
        try {
            albumResponse.setAlbumList(albumService.findAllByIsDeletedAndClientId(0, userInfo.getClient().getClientId()));
            albumResponse.setSuccess(true);
            albumResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            albumResponse.setSuccess(true);
            albumResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return albumResponse;
    }

    @RequestMapping(value = "/get-albums", method = RequestMethod.POST)
    public AlbumResponse getPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AlbumResponse albumResponse = new AlbumResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("title"));
            Page<Album> page = albumService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 0, pageable);
            albumResponse.setTotalRecords(page.getTotalElements());
            albumResponse.setTotalPages(page.getTotalPages());
            albumResponse.setAlbumList(page.getContent());
            albumResponse.setCurrentRecords(albumResponse.getAlbumList().size());
            albumResponse.setSuccess(true);
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

    @RequestMapping(value = "/search-albums", method = RequestMethod.POST)
    public AlbumResponse searchPaginated(@RequestBody Map<String, String> formData) {
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
                page = albumService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(),0,pageable);
            } else {
                page = albumService.findAll(textInAllColumns(searchText, userInfo.getClient().getClientId()), pageable);
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

    private boolean checkDuplicate(Album album) {
        List<Album> albumList;
        if (album.getId().equals("")) {
            albumList = albumService.findAllByClientIdAndTitle(album.getClientId(), album.getTitle());
        } else {
            albumList = albumService.findAllByClientIdAndTitleAndIdIsNot(
                    album.getClientId(), album.getTitle(), album.getId());
        }
        if (albumList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }


}
