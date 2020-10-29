package com.carmel.common.dbservice.Base.User.Service;

import com.carmel.common.dbservice.Base.AppFeature.Response.AppFeaturesResponse;
import com.carmel.common.dbservice.Base.Client.Model.Client;
import com.carmel.common.dbservice.Base.User.Response.UsersResponse;
import com.carmel.common.dbservice.common.Search.SearchRequest;
import com.carmel.common.dbservice.Base.User.Model.User;
import com.carmel.common.dbservice.model.UserInfo;
import com.carmel.common.dbservice.response.GenericResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    Optional<User> findById(String id) throws Exception;

    User save(User user) throws Exception;

    Optional<User> findByUserName(String userName) throws Exception;

    UsersResponse saveUser(User user) throws Exception;

    UsersResponse guestSignUp(User user) throws Exception;

    UsersResponse moveToTrash( Map<String, String> formData) throws Exception;

    UsersResponse get( Map<String, String> formData) throws Exception;

    UsersResponse getAll() throws Exception;

    UsersResponse getDeleted() throws Exception;

    UsersResponse getPaginated( Map<String, String> formData) throws Exception;

    UsersResponse searchPaginated( Map<String, String> formData) throws Exception;

    User getDuplicate(User user) throws Exception;

    UserInfo oldUser(Principal principal) throws Exception;

    UserInfo user(Principal principal) throws Exception;

    String myPic() throws Exception;

    UserInfo resetPassword( Map<String, String> formData) throws Exception;

    UsersResponse activateAccount( Map<String, String> formData) throws Exception;

    GenericResponse changePassword( Map<String, String> formData) throws Exception;

    UsersResponse search( SearchRequest searchRequest) throws Exception;

    UsersResponse findUsersIn( List<String> userIds) throws Exception;

    UsersResponse findAllByUserName(String userName) throws Exception;

    UsersResponse findAllByUserNameAndIdIsNot(String userName, String id) throws Exception;

    UsersResponse findAllByDeletionStatus(int isDeleted, Client client) throws Exception;

    UsersResponse findAllByClient(Pageable pageable, Client client) throws Exception;

    UsersResponse findAll(Pageable pageable) throws Exception;

    UsersResponse findAll(Specification<User> textInAllColumns, Pageable pageable) throws Exception;

    UsersResponse findAllByIdIn(List<String> userIds) throws Exception;

    UsersResponse phoneNumberSignUp(User user) throws Exception;

    UsersResponse checkPhoneNumber(User user) throws Exception;

    Optional<User> findByPhone(String phone);
}
