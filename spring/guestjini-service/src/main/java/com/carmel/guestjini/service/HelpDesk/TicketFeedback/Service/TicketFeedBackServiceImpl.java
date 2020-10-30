package com.carmel.guestjini.service.HelpDesk.TicketFeedback.Service;

import com.carmel.guestjini.service.HelpDesk.TicketFeedback.DTO.TicketFeedBackDTO;
import com.carmel.guestjini.service.HelpDesk.TicketFeedback.Model.TicketFeedBack;
import com.carmel.guestjini.service.HelpDesk.TicketFeedback.Repository.TicketFeedBackRepository;
import com.carmel.guestjini.service.HelpDesk.TicketFeedback.Response.TicketFeedBackResponse;
import com.carmel.guestjini.service.common.HelpDesk.TicketStatus;
import com.carmel.guestjini.service.components.UserInformation;
import com.carmel.guestjini.service.model.HelpDesk.TaskTicket;
import com.carmel.guestjini.service.model.Principal.UserInfo;
import com.carmel.guestjini.service.service.HelpDesk.TaskTicketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.*;

@Service
public class TicketFeedBackServiceImpl implements TicketFeedBackService {


    private Logger logger = LoggerFactory.getLogger(TicketFeedBackServiceImpl.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    TicketFeedBackRepository ticketFeedBackRepository;

    @Autowired
    UserInformation userInformation;

    @Autowired
    EntityManager entityManager;

    @Autowired
    TaskTicketService taskTicketService;

    @Override
    public TicketFeedBack save(TicketFeedBack ticketFeedBack) throws Exception {
        return ticketFeedBackRepository.save(ticketFeedBack);
    }


    @Override
    public Optional<TicketFeedBack> findById(String id) throws Exception {
        return ticketFeedBackRepository.findById(id);
    }

    @Override
    public TicketFeedBackResponse saveTicketFeedback(TicketFeedBack ticketFeedBack) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        TicketFeedBackResponse ticketFeedBackResponse = new TicketFeedBackResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(ticketFeedBack));
            ticketFeedBack.setUserId(userInfo.getId());
            Optional<TicketFeedBack> optionalTicketFeedBack =
                    ticketFeedBackRepository.findByUserIdAndTicketId(
                            userInfo.getId(),
                            ticketFeedBack.getTicketId()
                    );

            if (optionalTicketFeedBack.isPresent()) {
                TicketFeedBack savedTicketFeedback = optionalTicketFeedBack.get();
                ticketFeedBack.setId(savedTicketFeedback.getId());
            }
            ticketFeedBack.setClientId(userInfo.getClient().getClientId());
            if (ticketFeedBack.getId() != null) {
                if (!ticketFeedBack.getId().trim().equals("")) {
                    ticketFeedBack.setLastModifiedBy(userInfo.getId());
                    ticketFeedBack.setLastModifiedTime(new Date());
                } else {
                    ticketFeedBack.setCreatedBy(userInfo.getId());
                    ticketFeedBack.setCreationTime(new Date());
                }
            } else {
                ticketFeedBack.setCreatedBy(userInfo.getId());
                ticketFeedBack.setCreationTime(new Date());
            }
            ticketFeedBack.setClientId(userInfo.getClient().getClientId());
            ticketFeedBackResponse.setTicketFeedBack(
                    new TicketFeedBackDTO(this.save(ticketFeedBack)));
            Optional<TaskTicket> optionalTaskTicket = taskTicketService.findById(ticketFeedBack.getTicketId());
            if(optionalTaskTicket.isPresent()){
                TaskTicket taskTicket = optionalTaskTicket.get();
                taskTicket.setTicketStatus(TicketStatus.CLOSED);;
                taskTicketService.save(taskTicket);
            }
            ticketFeedBackResponse.setSuccess(true);
            ticketFeedBackResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        logger.trace("Exiting");
        return ticketFeedBackResponse;
    }


    @Override
    public TicketFeedBackResponse moveToTrash(Map<String, String> formData) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        TicketFeedBackResponse ticketFeedBackResponse = new TicketFeedBackResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<TicketFeedBack> optionalTicketFeedBack = this.findById(formData.get("id"));
            if (optionalTicketFeedBack.isPresent()) {
                TicketFeedBack ticketFeedBack = optionalTicketFeedBack.get();
                ticketFeedBack.setIsDeleted(1);
                ticketFeedBack.setDeletedBy(userInfo.getId());
                ticketFeedBack.setDeletedTime(new Date());
                ticketFeedBackResponse.setSuccess(true);
                ticketFeedBackResponse.setError("");
                ticketFeedBackResponse.setTicketFeedBack(
                        new TicketFeedBackDTO(
                                this.save(ticketFeedBack)
                        ));
            } else {
                ticketFeedBackResponse.setSuccess(false);
                ticketFeedBackResponse.setError("Error occurred while moving TicketFeedBack to Trash!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            ticketFeedBackResponse.setSuccess(false);
            ticketFeedBackResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return ticketFeedBackResponse;
    }

    @Override
    public TicketFeedBackResponse get(Map<String, String> formData) throws Exception {
        logger.trace("Entering");
        TicketFeedBackResponse ticketFeedBackResponse = new TicketFeedBackResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<TicketFeedBack> optionalTicketFeedBack = this.findById(formData.get("id"));
            if (optionalTicketFeedBack.isPresent()) {
                TicketFeedBack ticketFeedBack = optionalTicketFeedBack.get();
                ticketFeedBackResponse.setSuccess(true);
                ticketFeedBackResponse.setError("");
                ticketFeedBackResponse.setTicketFeedBack(
                        new TicketFeedBackDTO(ticketFeedBack)
                );
            } else {
                ticketFeedBackResponse.setSuccess(false);
                ticketFeedBackResponse.setError("Error occurred while fetching TicketFeedBack!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            ticketFeedBackResponse.setSuccess(false);
            ticketFeedBackResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return ticketFeedBackResponse;
    }

    @Override
    public TicketFeedBackResponse getByTicketId(Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        TicketFeedBackResponse ticketFeedBackResponse = new TicketFeedBackResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<TicketFeedBack> optionalTicketFeedBack =
                    ticketFeedBackRepository.findByUserIdAndTicketId(userInfo.getId(), formData.get("ticketId"));
            if (optionalTicketFeedBack.isPresent()) {
                TicketFeedBack ticketFeedBack = optionalTicketFeedBack.get();
                ticketFeedBackResponse.setSuccess(true);
                ticketFeedBackResponse.setError("");
                ticketFeedBackResponse.setTicketFeedBack(
                        new TicketFeedBackDTO(ticketFeedBack)
                );
            } else {
                ticketFeedBackResponse.setSuccess(true);
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            ticketFeedBackResponse.setSuccess(false);
            ticketFeedBackResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return ticketFeedBackResponse;
    }

    @Override
    public TicketFeedBackResponse getDeleted() throws Exception {
        logger.trace("Entering");
        UserInfo userInfo = userInformation.getUserInfo();
        TicketFeedBackResponse ticketFeedBackResponse = new TicketFeedBackResponse();
        try {
            ticketFeedBackResponse.setTicketFeedBackList(
                    getTicketFeedbackDTOS(ticketFeedBackRepository
                            .findAllByClientIdAndIsDeleted(
                                    userInfo.getClient().getClientId(),
                                    1
                            )
                    ));
            ticketFeedBackResponse.setSuccess(true);
            ticketFeedBackResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            ticketFeedBackResponse.setSuccess(true);
            ticketFeedBackResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return ticketFeedBackResponse;
    }

    @Override
    public TicketFeedBackResponse getAll() throws Exception {
        logger.trace("Entering");
        UserInfo userInfo = userInformation.getUserInfo();
        TicketFeedBackResponse ticketFeedBackResponse = new TicketFeedBackResponse();
        try {
            ticketFeedBackResponse.setTicketFeedBackList(
                    getTicketFeedbackDTOS(ticketFeedBackRepository
                            .findAllByClientIdAndIsDeleted(
                                    userInfo.getClient().getClientId(),
                                    0
                            )
                    ));
            ticketFeedBackResponse.setSuccess(true);
            ticketFeedBackResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            ticketFeedBackResponse.setSuccess(true);
            ticketFeedBackResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return ticketFeedBackResponse;
    }

    @Override
    public TicketFeedBackResponse getPaginated(Map<String, String> formData) throws Exception {
        logger.trace("Entering");
        UserInfo userInfo = userInformation.getUserInfo();
        TicketFeedBackResponse ticketFeedBackResponse = new TicketFeedBackResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("creationTime"));
            Page<TicketFeedBack> page = ticketFeedBackRepository
                    .findAllByClientIdAndIsDeleted(
                            userInfo.getClient().getClientId(),
                            0,
                            pageable
                    );
            ticketFeedBackResponse.setTotalRecords(page.getTotalElements());
            ticketFeedBackResponse.setTotalPages(page.getTotalPages());
            ticketFeedBackResponse.setTicketFeedBackList(
                    getTicketFeedbackDTOS(page.getContent())
            );
            ticketFeedBackResponse.setCurrentRecords(ticketFeedBackResponse.getTicketFeedBackList().size());
            ticketFeedBackResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            ticketFeedBackResponse.setSuccess(false);
            ticketFeedBackResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return ticketFeedBackResponse;
    }

    @Override
    public TicketFeedBackResponse searchPaginated(Map<String, String> formData) throws Exception {
        logger.trace("Entering");
        UserInfo userInfo = userInformation.getUserInfo();
        TicketFeedBackResponse ticketFeedBackResponse = new TicketFeedBackResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String searchText = formData.get("search_text") == null ? null : String.valueOf(formData.get("search_text"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("creationTime"));
            Page<TicketFeedBack> page;
            page = ticketFeedBackRepository
                    .findAllByClientIdAndIsDeleted(
                            userInfo.getClient().getClientId(),
                            0,
                            pageable
                    );

            ticketFeedBackResponse.setTotalRecords(page.getTotalElements());
            ticketFeedBackResponse.setTotalPages(page.getTotalPages());
            ticketFeedBackResponse.setTicketFeedBackList(
                    getTicketFeedbackDTOS(page.getContent())
            );
            ticketFeedBackResponse.setCurrentRecords(ticketFeedBackResponse.getTicketFeedBackList().size());
            ticketFeedBackResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            ticketFeedBackResponse.setSuccess(false);
            ticketFeedBackResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return ticketFeedBackResponse;
    }


    private List<TicketFeedBackDTO> getTicketFeedbackDTOS(List<TicketFeedBack> ticketFeedBacks) {
        List<TicketFeedBackDTO> ticketFeedBackDTOS = new ArrayList<>();
        for (TicketFeedBack ticketFeedBack : ticketFeedBacks) {
            ticketFeedBackDTOS.add(new TicketFeedBackDTO(ticketFeedBack));
        }
        return ticketFeedBackDTOS;
    }

    private boolean checkDuplicate(TicketFeedBack ticketFeedBack) {
        return false;
    }
}
