package com.evo.springboot.app.Controllers;

import Arrangement.Ticket;
import Model.Employee.Employee;
import com.evo.springboot.app.Converters.EmployeeConverter;
import com.evo.springboot.app.DTO.Incoming.NewEmployeeDTO;
import com.evo.springboot.app.DTO.Incoming.TicketDTO;
import com.evo.springboot.app.DTO.Outgoing.GenericResponseDTO;
import com.evo.springboot.app.DTO.Outgoing.NewEmployeeResponseDTO;
import com.evo.springboot.bl.BusinessLogic;
import com.evo.springboot.db.Users.UsersRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Api(value = "", tags = {"ticket", ""})
public class TicketController {

    @Autowired
    BusinessLogic businessLogic;

    @Autowired
    EmployeeConverter employeeConverter;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ApiOperation(value = "", nickname = "addTicket")
    @PostMapping(value = "addTicket")
    public @ResponseBody
    GenericResponseDTO addTicket(@RequestBody TicketDTO ticket, HttpServletRequest request) {
        try {
            logger.info("[TicketController][api/addTicket] received new request to add new ticket");
            List<Employee> employees = businessLogic.getAllEmployees(BusinessLogic.staticCompName);
            Employee emp = employees.stream().filter(employee -> employee.getFullName().equals(ticket.getEmpName())).findFirst().orElse(null);
            businessLogic.createTicket(BusinessLogic.staticCompName, emp, ticket.getMsg());

            logger.info("[TicketController][api/addTicket] add new ticket completed successfully");
            return new GenericResponseDTO("ticket added", true);

        } catch (Exception err) {
            logger.error("[TicketController][api/addTicket] add new ticket failed");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("add new ticket '%s' failed", ticket.getMsg()),
                    err
            );
        }
    }

    @ApiOperation(value = "", nickname = "getAllTickets")
    @PostMapping(value = "getAllTickets")
    public @ResponseBody
    List<TicketDTO> getAllTickets(HttpServletRequest request) {
        try {
            logger.info("[TicketController][api/getAllTickets] received new request to get all Tickets");
            Map<String, Ticket> tickets = businessLogic.getAllTickets(BusinessLogic.staticCompName);

            List<TicketDTO> ticketToReturn = new ArrayList<>();
            tickets.forEach(
                    (key, ticket) -> {
                        TicketDTO newTicket = new TicketDTO();
                        newTicket.setMsg(ticket.getComment());
                        newTicket.setEmpName(ticket.getEmployee().getFirstName());
                        ticketToReturn.add(newTicket);
                    }
            );

            logger.info("[TicketController][api/getAllTickets] get all Tickets completed successfully");
            return ticketToReturn;

        } catch (Exception err) {
            logger.error("[TicketController][api/addTicket] get all tickets failed");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "could not get all tickets",
                    err
            );
        }
    }
}
