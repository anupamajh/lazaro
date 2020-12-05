package com.carmel.guestjini.service.service.HelpDesk;


import com.carmel.guestjini.service.common.HelpDesk.TicketStatus;
import com.carmel.guestjini.service.model.DTO.HelpDesk.InboxCount;
import com.carmel.guestjini.service.model.DTO.HelpDesk.TicketCountDTO;
import com.carmel.guestjini.service.model.HelpDesk.TaskNote;
import com.carmel.guestjini.service.model.HelpDesk.TaskTicket;
import com.carmel.guestjini.service.repository.HelpDesk.TaskTicketRepository;
import com.carmel.guestjini.service.response.HelpDesk.TaskForceResponse;
import com.carmel.guestjini.service.response.HelpDesk.TaskRunnerResponse;
import com.carmel.guestjini.service.response.HelpDesk.TaskTicketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskTicketServiceImpl implements TaskTicketService {

    @Autowired
    TaskTicketRepository taskTicketRepository;

    @Autowired
    TaskNoteService taskNoteService;

    @Autowired
    TaskForceService taskForceService;

    @Autowired
    TaskRunnerService taskRunnerService;


    @Override
    public TaskTicket save(TaskTicket taskTicket) {
        return taskTicketRepository.save(taskTicket);
    }

    @Override
    public Optional<TaskTicket> findById(String id) {
        return taskTicketRepository.findById(id);
    }

    @Override
    public List<TaskTicket> findAllByIsDeletedAndRequesterId(int isDeleted, String requesterId) {
        return taskTicketRepository.findAllByIsDeletedAndRequesterId(isDeleted, requesterId);
    }

    @Override
    public Page<TaskTicket> findAll(Specification<TaskTicket> textInAllColumns, Pageable pageable) {
        return taskTicketRepository.findAll(textInAllColumns, pageable);
    }

    @Override
    public Page<TaskTicket> findAllByIsDeletedAndRequesterId(int isDeleted, String id, Pageable pageable) {
        return taskTicketRepository.findAllByIsDeletedAndRequesterId(isDeleted, id, pageable);
    }

    @Override
    public TicketCountDTO getTicketCountByStatus(String requesterId) throws Exception {
        TicketCountDTO ticketCountDTO = new TicketCountDTO();
        try {

            //Get Draft Ticket Count
            List<TaskTicket> taskTicketList =
                    taskTicketRepository.findAllByIsDeletedAndRequesterIdAndTicketStatus(0, requesterId, TicketStatus.DRAFT);
            ticketCountDTO.setDraftTicketCount(taskTicketList.size());
            taskTicketList =
                    taskTicketRepository.findAllByIsDeletedAndRequesterIdAndTicketStatus(0, requesterId, TicketStatus.WORK_IN_PROGRESS);
            ticketCountDTO.setActiveTicketCount(taskTicketList.size());
            taskTicketList = taskTicketRepository.findAllByIsDeletedAndRequesterIdAndTicketStatus(0, requesterId, TicketStatus.NOT_STARTED);
            ticketCountDTO.setActiveTicketCount(taskTicketList.size() + ticketCountDTO.getActiveTicketCount());
            taskTicketList = taskTicketRepository.findAllByIsDeletedAndRequesterIdAndTicketStatus(0, requesterId, TicketStatus.COMPLETED);
            ticketCountDTO.setActiveTicketCount(taskTicketList.size() + ticketCountDTO.getActiveTicketCount());
            taskTicketList = taskTicketRepository.findAllByIsDeletedAndRequesterIdAndTicketStatus(0, requesterId, TicketStatus.ON_HOLD);
            ticketCountDTO.setActiveTicketCount(taskTicketList.size() + ticketCountDTO.getActiveTicketCount());
            taskTicketList =
                    taskTicketRepository.findAllByIsDeletedAndRequesterIdAndTicketStatus(0, requesterId, TicketStatus.CLOSED);
            ticketCountDTO.setArchiveTicketCount(taskTicketList.size());

        } catch (Exception ex) {
            throw ex;
        }
        return ticketCountDTO;
    }

    @Override
    public List<TaskTicket> findAllByIsDeletedAndRequesterIdAndTicketStatus(int isDeleted, String id, int ticketStatus) {
        List<TaskTicket> tickets;
        tickets = taskTicketRepository.findAllByIsDeletedAndRequesterIdAndTicketStatus(0, id, ticketStatus);
        if (ticketStatus == TicketStatus.NOT_STARTED) {
            ticketStatus = TicketStatus.WORK_IN_PROGRESS;
            tickets.addAll(taskTicketRepository.findAllByIsDeletedAndRequesterIdAndTicketStatus(0, id, ticketStatus));
            ticketStatus = TicketStatus.COMPLETED;
            tickets.addAll(taskTicketRepository.findAllByIsDeletedAndRequesterIdAndTicketStatus(0, id, ticketStatus));
            ticketStatus = TicketStatus.ON_HOLD;
            tickets.addAll(taskTicketRepository.findAllByIsDeletedAndRequesterIdAndTicketStatus(0, id, ticketStatus));
        }

        return tickets;
    }

    @Override
    public Page<TaskTicket> findAllByIsDeleted(int isDeleted, Pageable pageable) {
        return taskTicketRepository.findAllByIsDeleted(isDeleted, pageable);
    }

    @Override
    public TaskTicketResponse closeTicket(String ticketId, String userId, String message) throws Exception {
        TaskTicketResponse taskTicketResponse = new TaskTicketResponse();
        try {

            Optional<TaskTicket> optionalTaskTicket = taskTicketRepository.findById(ticketId);
            if (optionalTaskTicket.isPresent()) {
                TaskTicket taskTicket = optionalTaskTicket.get();
                taskTicket.setTicketStatus(TicketStatus.CLOSED);
                taskTicket.setLastModifiedBy(userId);
                taskTicket.setLastModifiedTime(new Date());
                taskTicketRepository.save(taskTicket);
                TaskNote taskNote = new TaskNote();
                taskNote.setTicketId(ticketId);
                taskNote.setNotes("Ticket Closed \n" + message);
                taskNote.setUserId(userId);
                taskNoteService.save(taskNote);
            } else {
                throw new Exception("Ticket not found");
            }

        } catch (Exception ex) {
            throw ex;
        }

        taskTicketResponse.setSuccess(true);
        return taskTicketResponse;
    }

    @Override
    public InboxCount getInboxCount(String userId) throws Exception {
        InboxCount inboxCount = new InboxCount();
        try {
            TaskForceResponse taskForceResponse = taskForceService.findByUserId(userId);
            inboxCount.setIsGroupAdmin(taskForceResponse.getTaskForce().getIsGroupAdmin());
            String groupId = taskForceResponse.getTaskForce().getGroupId();
            List<TaskTicket> taskTicketList = taskTicketRepository
                    .findAllByIsDeletedAndTicketStatus(0, TicketStatus.NOT_STARTED);
            inboxCount.setSharedUnAssigned(taskTicketList.size());
            taskTicketList = taskTicketRepository
                    .findAllByIsDeletedAndTicketStatus(0, TicketStatus.CLOSED);
            inboxCount.setSharedClosed(taskTicketList.size());
            taskTicketList = taskTicketRepository
                    .findAllByIsDeletedAndTicketStatus(0, TicketStatus.ON_HOLD);
            inboxCount.setSharedOpen(taskTicketList.size());
            taskTicketList = taskTicketRepository
                    .findAllByIsDeletedAndTicketStatus(0, TicketStatus.WORK_IN_PROGRESS);
            inboxCount.setSharedOpen(inboxCount.getSharedOpen() + taskTicketList.size());
            taskTicketList = taskTicketRepository
                    .findAllByIsDeletedAndTicketStatus(0, TicketStatus.COMPLETED);
            inboxCount.setSharedOpen(inboxCount.getSharedOpen() + taskTicketList.size());
            taskTicketList = taskTicketRepository
                    .findAllByIsDeletedAndTicketStatus(0, TicketStatus.ON_HOLD);
            inboxCount.setSharedOpen(inboxCount.getSharedOpen() + taskTicketList.size());

            TaskRunnerResponse taskRunnerResponse =
                    taskRunnerService.findAllByUserIdAndTaskStatus(userId, TicketStatus.CLOSED);
            inboxCount.setMyClosed(taskRunnerResponse.getTaskRunnerList().size());
            taskRunnerResponse =
                    taskRunnerService.findAllByUserIdAndTaskStatusIsNot(userId, TicketStatus.CLOSED);
            inboxCount.setMyOpen(taskRunnerResponse.getTaskRunnerList().size());

            taskRunnerResponse =
                    taskRunnerService.findAllByTaskForceGroupIdAndTaskStatus(groupId, TicketStatus.CLOSED);
            inboxCount.setTeamClosed(taskRunnerResponse.getTaskRunnerList().size());
            taskRunnerResponse =
                    taskRunnerService.findAllByTaskForceGroupIdAndTaskStatusIsNot(groupId, TicketStatus.CLOSED);
            inboxCount.setTeamOpen(taskRunnerResponse.getTaskRunnerList().size());

            taskRunnerResponse =
                    taskRunnerService.findAllByTaskForceGroupIdAndUserId(groupId,null);
            inboxCount.setTeamUnassigned(taskRunnerResponse.getTaskRunnerList().size());
            inboxCount.setSuccess(true);
        } catch (Exception ex) {
            throw ex;
        }
        return inboxCount;
    }
}
