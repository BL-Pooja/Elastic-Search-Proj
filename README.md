# EJET-Leads-Services
EJET Leads Services API

## EJET API Calls. 

1. The Leads User is first authenticated with EJET Platform as Lead admin. 
2. The POST api call to authenticare is `/services/lead/authenticate`. ALong with the Lead Admin User and Password is sent in the Post call as Request Body
3. The EJET platform on successful authentication returns the User Token in the Header
4. The Token can be tested by callig GET API Call `/services/lead` which essentially validates the token and returns the Welcome Call

## Webinar Jam Orientation Reports

This Report is downloaded from Webinar Jam and Uploaded to EJET Platform using EJET Leads App.

- Below are the mappings of Webnar Jam Field with EJET Field
- **NOTE -** Need to convert DataTime to EJET Format from Webinar Jam Format. 
- **API Call to EJET -** The api POST call path is `/services/lead/orientation`. The below JSON is sent in the Request Body

    ```
    JSON Structure for Webinar Jap Upload to EJET Platform
    {  
        "firstName": "Purna", # This is First name
        "lastName":	"Chandra", # Last name
        "mobileNumber": "9492130054", # This is Phone number
        "email": "kchsekhar1999@gmail.com", # This is Email
        "isAttended": "Yes", # This is Attended live
        "eventDateTime": "2021-09-02 02:32:37", # Event
        "attendanceDateTime": "2021-09-02 02:32:37", # Attended live date
        "timeSpent": "0:00:00" # Time in live room
    }    
    ```

## ROLO CRM Upload and Reports

### ROLO CRM Upload 

- This is mainly for Slot Booking. 
- This can be also used for any User Activity Status. The slotDateTime will reflect the appropriate date time based on User Activity Status like Payment Updates. 
- **NOTE -** Need to convert DataTime from EJET Format to Rolo Format and Viceversa
- The Below JSON Structure provided by EJET and Rolo CRM Mappings is shown as a sample. 
- **API Call to EJET -** The api GET call path is `/services/lead/upload/{lastUploadDateTime}`. The Last Upload Date Time is sent as a parameter and The below JSON is received in response

    ```
    JSON for ROLO CRM Upload - EJET Slot Booking Download
    {  
        "firstName": "Purna",
        "lastName":	"Chandra",
        "mobileNumber": "9492130054", # This is Phone1
        "email": "kchsekhar1999@gmail.com", # This is Email1
        "userId": "254ae842-8ad0-448b-a96c-44038bdcd032", # This is Company
        "counsellorName": "Yatin Gandhi", # This is Assignee
        "userActivityStatus": "OrientationAttended", # This is Contact Type
        "passoutYear": "2019", # Year of Passing
        "orientationDateTime": "2021-09-02 02:32:37", # Date of Orientation
        "funnelCode": "Generic" # This is Lead Source
        "activityDateTime": "2021-09-02 02:32:37" # Calling Slot Booked
        "userRemarks": "Payment For TRIAL ENROLMENT Paid Amount: 0.0 Status: FAILURE" # Comments
    }
    ```
### ROLO CRM Reports

- The CRM Report is downloaded from Rolo and updated into EJET Platform
- **NOTE -** Need to convert DataTime from ROLO Format to EJET Format and Viceversa
- The Below JSON Structure provided by EJET and the corresponding Rolo CRM Mappings is shown as a sample.  
- **API Call to EJET -** The api POST call path is `/services/lead/report`. The below JSON is sent in the Request Body

    ```
    JSON for Rolo CRM Reports to be updated in EJET
    {  
        "userId": "254ae842-8ad0-448b-a96c-44038bdcd032", # This is Company
        "mobileNumber": "9492130054", # This is Phone1
        "counsellorName": "Yatin Gandhi", # This is mainly CRM User
        "leadStatus": "CUSTOMER 3K", # This is Type
        "isConnected": true, # This is Connected
        "userActivity": "Missed Call", # This is Activity
        "activityDateTime": "2021-09-02 02:32:37", # Time
        "talkTime": "5m 34s", # This is Talk Time
        "userRemarks": "13 July - explained the fees structure again, agreed to get back by EOD, Student paid Rs. 3k on 13th July and agreed to join new batch starting from 15th July 2021 at 9:00 am" # Details
    }    
    ```

