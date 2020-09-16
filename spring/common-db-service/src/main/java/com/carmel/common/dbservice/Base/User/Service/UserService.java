package com.carmel.common.dbservice.Base.User.Service;

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

    List<User> findAllByUserName(String userName);

    List<User> findAllByUserNameAndIdIsNot(String userName, String id);

    List<User> findAllByDeletionStatus(int isDeleted, Client client);

    Page<User> findAllByClient(Pageable pageable, Client client);

    Page<User> findAll(Specification<User> textInAllColumns, Pageable pageable);

    List<User> findAllByIdIn(List<String> userIds);
}
