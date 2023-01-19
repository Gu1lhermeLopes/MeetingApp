package api.service;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import api.exception.ResourceNotFoundException;
import api.model.*;
import api.repository.MeetingRepository;
import api.repository.ContactRepository;

@Service
public class ApiService {

    @Autowired
    private MeetingRepository meetingRepository;
  
    @Autowired
    private ContactRepository contactRepository;



    //GET ALL
    public List<Meeting> getAllMeetings() {
        return meetingRepository.findAll();
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }
    
    //FIND
    public Meeting findById(Long id){
        Optional<Meeting> obj = meetingRepository.findById(id);
        return obj.orElseThrow();
    }

    public Contact findContactById(Long id){
        Optional<Contact> obj = contactRepository.findById(id);
        return obj.orElseThrow();
    }

    public Meeting saveMeeting(Meeting meeting){
        return meetingRepository.save(meeting); 
    }

    public Meeting cloneMeeting(Long id){
        Meeting obj =findById(id);
        Meeting newm= new Meeting(obj.getTopic(),obj.getPass(),obj.getDate());
        return meetingRepository.save(newm); 
    }




    public Meeting updateMeeting(Long id,Meeting m){
        Meeting newM = findById(id);
        newM.setTopic(m.getTopic());
        newM.setPass(m.getPass());
        newM.setDate(m.getDate());
        return meetingRepository.save(newM); 
    }

    public Contact saveContact(Contact contact){
        return contactRepository.save(contact); 
    }



    //ADD CONTACTS
    public Contact addContact(Long id,Long contactId){
        Set<Meeting> mset=null;
        Meeting m = findById(id);
        Contact c=findContactById(contactId);

        mset =c.getMeetings();
        mset.add(m);
        c.setMeetings(mset);

        
        return contactRepository.save(c);
    }
    

    //SELECTS DATE
    public List<Meeting> getMeetingsByDate() {
        return meetingRepository.findByOrderByDateAsc();
    }

    public List<Meeting> getMeetingsByDateB(LocalDateTime start,LocalDateTime end) {
        return meetingRepository.findAllByDateBetween(start,end);
    }


    //DELETE
    public Map<String, Boolean> deleteMeeting(Long id) {
        Meeting m=findById(id);
        meetingRepository.delete(m);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;

    }





    

    /*public ResponseEntity<Contact> saveQuote(Long meetingId,Contact quoteRequest) {
        Contact quote = MeetingRepository.findById(meetingId).map(Meeting -> { quoteRequest.setMeeting(Meeting);
          return quoteRepository.save(quoteRequest);
          }).orElseThrow(() -> new ResourceNotFoundException("Not found Meeting with id = " + MeetingId));

        return new ResponseEntity<>(quote, HttpStatus.CREATED);
    }*/






}
