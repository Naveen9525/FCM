package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.PushNotificationRequest;
import com.example.demo.model.PushNotificationResponse;
import com.example.demo.service.PushNotificationService;

@RestController
public class PushNotificationController {

    private PushNotificationService pushNotificationService;

    public PushNotificationController(PushNotificationService pushNotificationService) {
        this.pushNotificationService = pushNotificationService;
    }

    @PostMapping("/notification/topic")
    public ResponseEntity<PushNotificationResponse> sendNotification(@RequestBody PushNotificationRequest request) {
    	try
    	{
        pushNotificationService.sendPushNotificationWithoutData(request);
    	}
    	catch(Exception ex)
    	{
    		System.out.print("Error occured");
    		System.out.print(ex.getStackTrace());
    	}
        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }

    @PostMapping("/notification/token")
    public ResponseEntity<PushNotificationResponse> sendTokenNotification(@RequestBody PushNotificationRequest request) {
    	try
    	{
        pushNotificationService.sendPushNotificationToToken(request);
    	}catch(Exception ex)
    	{
    		System.out.print("Error occured");
    		System.out.print(ex.getStackTrace());
    	}
        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }

    @PostMapping("/notification/data")
    public ResponseEntity<PushNotificationResponse> sendDataNotification(@RequestBody PushNotificationRequest request) {
        pushNotificationService.sendPushNotification(request);
        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }

    @GetMapping("/notification")
    public ResponseEntity<PushNotificationResponse> sendSampleNotification() {
        pushNotificationService.sendSamplePushNotification();
        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }
}