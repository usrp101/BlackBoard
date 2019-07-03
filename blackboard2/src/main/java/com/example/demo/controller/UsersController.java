package com.example.demo.controller;

import com.example.demo.Domain.Users;
import com.example.demo.Service.UserService;
import com.example.demo.util.ResponseBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/save", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> save(@RequestBody Users user) {
        ResponseBean responseBean = new ResponseBean();
        //TODO: process POST request
        try {
            Users u=userService.findByUserId(user.getUserId());
            if(u==null){
                Users nu=userService.create(user);
                responseBean.setCode("");
                responseBean.setDescription("");
                responseBean.setObject(nu);
            }else{
                responseBean.setCode("");
                responseBean.setDescription("");
                responseBean.setObject(null);
            }

        } catch (Exception e) {
            //TODO: handle exception
            responseBean.setCode("");
            responseBean.setDescription("");
            responseBean.setObject(null);
        }
        return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
    }

      /**
       * find By uuid
       * @param uuid
       * @param user
       * @return
       */
      @RequestMapping(value = "/update/{uuid}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@PathVariable String uuid,@RequestBody Users user) {
        ResponseBean responseBean = new ResponseBean();
        // TODO: process POST request
        try {
            Users u = userService.findByUuid(uuid);
            if (u != null) {
                user.setId(u.getId());
                Users nu = userService.create(user);
                responseBean.setCode("");
                responseBean.setDescription("");
                responseBean.setObject(nu);
            } else {
                responseBean.setCode("");
                responseBean.setDescription("");
                responseBean.setObject(null);
            }

        } catch (Exception e) {
            // TODO: handle exception
            responseBean.setCode("");
            responseBean.setDescription("");
            responseBean.setObject(null);
        }
        return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
    }
     /**
      * Find BY UseId
      * @param id
      * @return
      */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByUserId(@PathVariable("id") String id) {
        ResponseBean responseBean = new ResponseBean();
        // TODO: process POST request
        try {
         
                responseBean.setCode("");
                responseBean.setDescription("");
                responseBean.setObject(userService.findByUserId(id));

        } catch (Exception e) {
            // TODO: handle exception
            responseBean.setCode("");
            responseBean.setDescription("");
            responseBean.setObject(null);
        }
        return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
    }


    /**
     * Find BY UseId
     * 
     * @param uuid
     * @return
     */
    @RequestMapping(value = "/{uuid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByUserUuid(@PathVariable("uuid") String uuid) {
        ResponseBean responseBean = new ResponseBean();
        // TODO: process POST request
        try {

            responseBean.setCode("");
            responseBean.setDescription("");
            responseBean.setObject(userService.findByUuid(uuid));

        } catch (Exception e) {
            // TODO: handle exception
            responseBean.setCode("");
            responseBean.setDescription("");
            responseBean.setObject(null);
        }
        return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
    }


    /**
     * Find all 
     * @return
     */
      @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findAll() {
        ResponseBean responseBean = new ResponseBean();
        // TODO: process POST request
        try {

            responseBean.setCode("");
            responseBean.setDescription("");
            responseBean.setObject(userService.findAll());

        } catch (Exception e) {
            // TODO: handle exception
            responseBean.setCode("");
            responseBean.setDescription("");
            responseBean.setObject(null);
        }
        return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
    } 

    public static class InnerUser{
        private String firstname;
        private String lastname;
        private String email;
        private String role;
        private String passcode;
        private String userId;

        public String getUserId() {
            return userId;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getPasscode() {
            return passcode;
        }

        public void setPasscode(String passcode) {
            this.passcode = passcode;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

    }
    
}