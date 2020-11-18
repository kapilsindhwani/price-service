#Price Service

####Purpose:
Price Service will receive prices against an instrument from various vendors. It will then pass the prices to downstream systems.
The service will also provide access to view the prices for various vendors and for a given instrument.

####Prerequisites
You need to have the below installed in your pc to use.
* git
* maven
* jdk 8+

####Usage
1. The design diagram on how the price service works is described in the file /documents/Design_Document.JPG
2. At the start up the service will create a schema from schema-h2.sql. This will act as object store for prices
3. Price Service currently accepts the price details in json format however the system is loosely coupled and it can accept any format like xml, csv by plugging in new transformer and without the need to change the core logic. A new transformed needs to be written for any new format. A valid json example is shown below e.g. 
    {
         "instrumentId": "A1123",     
         "price": 29,     
         "currency": "USD",     
         "vendorId": 1234
     }
4. To view the API published by the service please visit - http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config
   
       
