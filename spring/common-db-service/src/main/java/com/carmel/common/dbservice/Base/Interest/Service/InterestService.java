package com.carmel.common.dbservice.Base.Interest.Service;

import com.carmel.common.dbservice.Base.Interest.Model.Interest;
import com.carmel.common.dbservice.Base.Interest.Response.InterestResponse;
import com.carmel.common.dbservice.common.Search.SearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface InterestService {
    Interest save(Interest interest) throws Exception;

    Optional<Interest> findById(String id) throws Exception;

    InterestResponse saveInterest(Interest interest) throws Exception;

    InterestResponse moveToTrash(Map<String, String> formData) throws Exception;

    InterestResponse get(Map<String, String> formData) throws Exception;

    InterestResponse getDeleted() throws Exception;

    InterestResponse getAll() throws Exception;

    InterestResponse getPaginated(Map<String, String> formData) throws Exception;

    InterestResponse searchPaginated(Map<String, String> formData) throws Exception;

    InterestResponse search(SearchRequest searchRequest) throws Exception;

    InterestResponse findAllByClientIdAndIsDeleted(String clientId, int isDeleted) throws Exception;

    InterestResponse findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable) throws Exception;

    InterestResponse findAll(Specification<Interest> textInAllColumns, Pageable pageable) throws Exception;

    InterestResponse findAllByClientIdAndIsDeletedAndName(String clientId, int isDeleted, String name) throws Exception;

    InterestResponse findAllByClientIdAndIsDeletedAndNameAndIdIsNot(String clientId, int isDeleted, String name, String id) throws Exception;

    int countByIsDeleted(int i) throws Exception;
}
