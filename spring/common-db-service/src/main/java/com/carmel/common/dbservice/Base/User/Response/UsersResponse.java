package com.carmel.common.dbservice.Base.User.Response;

import com.carmel.common.dbservice.Base.User.DTO.UserDTO;
import com.carmel.common.dbservice.Base.User.Model.User;

import java.util.ArrayList;
import java.util.List;

public class UsersResponse {
    private UserDTO user;
    private List<UserDTO> userList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = new UserDTO(user);
    }

    public List<UserDTO> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = new ArrayList<>();
        userList.forEach(user1 -> {
            this.userList.add(new UserDTO((user1)));
        });
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public long getCurrentRecords() {
        return currentRecords;
    }

    public void setCurrentRecords(long currentRecords) {
        this.currentRecords = currentRecords;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
