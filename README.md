# Password Validation Service

## Description

Write a password validation service, meant to be configurable via IoC (using dependency injection engine of your choice). The service is meant to check a text string for compliance to any number of password validation rules. The rules currently known are: 

* Must consist of a mixture of lowercase letters and numerical digits only, with at least one of each.
* Must be between 5 and 12 characters in length.
* Must not contain any sequence of characters immediately followed by the same sequence.

## Prerequisite

* Java 8 and above
* Lombok for IDE plugin [see how to install on lombok official site](https://projectlombok.org)

## Tech Stack

* Java 8
* Swagger
* Gradle
* Spring Boot

## Test Guide

1. Build the artifact

   ```bash
   // cd to the root folder of this project first
   // and run below command, this command includes clean, test and build tasks.
   ./gradlew clean build
   
   // run below if you just want to test
   ./gradlew test
   ```

2. Run the application

   ```bash
   // cd to the root folder of this project first
   cd build/libs
   java -jar pve-0.0.1-SNAPSHOT.jar
   ```

3. Open your browser and enter http://localhost:7777/pve/swagger-ui.html

4. Click password-validation-controller

5. Expand /api/v1/passwords/validate API

6. Click "Try it out" button

7. Enter any password you want to validate for password field.

8. Return 204 (No Content) if passed, or else 400 (Bad Request) with detail errors. Errors example as below:

   ```json
   {
     "timestamp": "2019-11-14T05:13:12.955+0000",
     "status": 400,
     "error": "Bad Request",
     "errors": [
       {
         "codes": [
           "AtLeastOneDigit.passwordDtoRequest.password",
           "AtLeastOneDigit.password",
           "AtLeastOneDigit.java.lang.String",
           "AtLeastOneDigit"
         ],
         "arguments": [
           {
             "codes": [
               "passwordDtoRequest.password",
               "password"
             ],
             "arguments": null,
             "defaultMessage": "password",
             "code": "password"
           }
         ],
         "defaultMessage": "must contain at least one digit.",
         "objectName": "passwordDtoRequest",
         "field": "password",
         "rejectedValue": "abcabc",
         "bindingFailure": false,
         "code": "AtLeastOneDigit"
       },
       {
         "codes": [
           "NoFollowedRepeatedSubsequence.passwordDtoRequest.password",
           "NoFollowedRepeatedSubsequence.password",
           "NoFollowedRepeatedSubsequence.java.lang.String",
           "NoFollowedRepeatedSubsequence"
         ],
         "arguments": [
           {
             "codes": [
               "passwordDtoRequest.password",
               "password"
             ],
             "arguments": null,
             "defaultMessage": "password",
             "code": "password"
           }
         ],
         "defaultMessage": "must not contain followed and repeated subsequence.",
         "objectName": "passwordDtoRequest",
         "field": "password",
         "rejectedValue": "abcabc",
         "bindingFailure": false,
         "code": "NoFollowedRepeatedSubsequence"
       }
     ],
     "message": "Validation failed for object='passwordDtoRequest'. Error count: 2",
     "path": "/pve/api/v1/passwords/validate"
   }
   ```

   