package com.carmel.guestjini.service.service.HelpDesk;


import com.carmel.guestjini.service.HelpDesk.TaskTicket.Response.TaskAssigneeResponse;
import com.carmel.guestjini.service.common.HelpDesk.TicketStatus;
import com.carmel.guestjini.service.components.UserService;
import com.carmel.guestjini.service.model.DTO.HelpDesk.TicketCountDTO;
import com.carmel.guestjini.service.model.HelpDesk.TaskTicket;
import com.carmel.guestjini.service.repository.HelpDesk.TaskTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TaskTicketServiceImpl implements TaskTicketService {

    @Autowired
    TaskTicketRepository taskTicketRepository;


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
        List<TaskTicket> tickets ;
        tickets =   taskTicketRepository.findAllByIsDeletedAndRequesterIdAndTicketStatus(0, id,ticketStatus);
        if(ticketStatus == TicketStatus.NOT_STARTED){
            ticketStatus = TicketStatus.WORK_IN_PROGRESS;
            tickets.addAll(taskTicketRepository.findAllByIsDeletedAndRequesterIdAndTicketStatus(0, id,ticketStatus));
            ticketStatus = TicketStatus.COMPLETED;
            tickets.addAll(taskTicketRepository.findAllByIsDeletedAndRequesterIdAndTicketStatus(0, id,ticketStatus));
            ticketStatus = TicketStatus.ON_HOLD ;
            tickets.addAll(taskTicketRepository.findAllByIsDeletedAndRequesterIdAndTicketStatus(0, id,ticketStatus));
        }

        return tickets;
    }

    @Override
    public Page<TaskTicket> findAllByIsDeleted(int isDeleted, Pageable pageable) {
        return taskTicketRepository.findAllByIsDeleted(isDeleted, pageable);
    }
}
