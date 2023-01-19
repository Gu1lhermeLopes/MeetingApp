package api.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.model.*;
import api.service.ApiService;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api")
public class ApiController {


  @Autowired
  private ApiService service;



  @GetMapping("/meetings")
  public List<Meeting> allMeetings() {
      return service.getAllMeetings();
  }
  @GetMapping("/contacts")
  public List<Contact> allContacts() {
      return service.getAllContacts();
  }
  @PostMapping("/meetings/add")
  public Meeting addMeeting(@Validated @RequestBody Meeting meeting) {
      return service.saveMeeting(meeting);
  }
  @PostMapping("/contacts/add")
  public Contact addContact(@Validated @RequestBody Contact contact) {
      return service.saveContact(contact);
  }

  //CLONE
  @PostMapping("/meetings/{id}/duplicate")
  public Meeting cloneMeeting(@PathVariable Long id) {
      return service.cloneMeeting(id);
  }

    //DELETE
    @DeleteMapping("/meetings/{id}/delete")
    public Map<String, Boolean> deleteMeeting(@PathVariable Long id) {
        return service.deleteMeeting(id);
    }


  //ORDERBYDATE
  @GetMapping("/meetings/date")
  public List<Meeting> allMeetingsDate() {
      return service.getMeetingsByDate();
  }
  //ORDERBYDATEBETWEEN
  @GetMapping("/meetings/date/{start}/{end}")
  public List<Meeting> allMeetingsDateBetween(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
      return service.getMeetingsByDateB(start,end);
  }


  //UPDATE MEETING
  @PutMapping("/meetings/{id}")
  public Meeting updateMeet(@PathVariable Long id, @RequestBody Meeting meeting) {
      Meeting nm = service.updateMeeting(id,meeting);
      return nm;
  }


  //ADD CONTACT TO MEET
  @PutMapping("/meetings/{id}/{contactId}")
  public Contact addMeetContact(@PathVariable Long id, @PathVariable Long contactId) {
      return service.addContact(id, contactId);
  }
/* 
  @GetMapping("/quotes")
  public Contact quotes() {
      return service.getRandomQuote();
  }

  @GetMapping("/quotes/{MeetingId}")
  public Contact getRandomQuoteByMeetingId(@PathVariable(value = "MeetingId") Long MeetinglId) {
    return service.getRandomQuoteByMeetingId(MeetinglId);
  }*/

}
