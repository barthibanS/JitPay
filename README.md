# JitPay


 
 INSERT INTO Users_table(USER_ID, FIRST_NAME,SECOND_NAME, EMAIL,CREATED_ON) 
VALUES ('2e3b11b0-07a4-4873-8de5-d2ae2eab26b2', 'Alex', 'Schmid', 'alex.schmid@gmail.com', '2022-02-08 11:44:00.524');    

INSERT INTO Location_table (USER_ID  ,CREATED_ON , 	LATITUDE,  	LONGITUDE )
 VALUES ('2e3b11b0-07a4-4873-8de5-d2ae2eab26b2', '2022-02-08 11:44:00.524', 52.25742342295784, 10.540583401747602);  


POST method
 
localhost:8080/info
 
 {
"userId": "2e3b11b0-07a4-4873-8de5-d2ae2eab26b2",
"createdOn": "2022-02-08T11:44:00.521",
"location": {
"latitude": 52.25742342295784,
"longitude": 10.540583401747602
}
}


GET method
 localhost:8080/user/2e3b11b0-07a4-4873-8de5-d2ae2eab26b3
 
 
 GET method
  localhost:8080/userlocation/2e3b11b0-07a4-4873-8de5-d2ae2eab26b2?fromDate=2020-02-12T11:44:00.524&toDate=2023-02-08T11:44:00.524
  
  
  POST method
   localhost:8080/user
   
 {
"userId": "2e3b11b0-07a4-4873-8de5-d2ae2eab26b3",
"createdOn": "2022-02-08T11:44:00.524",
"location": {
"latitude": 52.25742342295784,
"longitude": 10.540583401747602
}
}
