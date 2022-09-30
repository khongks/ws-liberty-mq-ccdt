This repository contains code and configuration that shows how to make an application running on WebSphere Liberty connect to two IBM MQ queue manager using CCDT.

Instructions

1. Download WebSphere Liberty from this URL (https://www.ibm.com/support/pages/websphere-liberty-developers). Unzip the package into a `wlp` folder.

2. Run the followinng commands to run two queue managers - QM1 and QM2

   For QM1

   ```
   docker run --env LICENSE=accept --env MQ_QMGR_NAME=QM1 --publish 1414:1414 --publish 9443:9443 --detach --env MQ_APP_PASSWORD=passw0rd --name QM1 icr.io/ibm-messaging/mq:latest
   ```
   
   For QM2

   ```
   docker run --env LICENSE=accept --env MQ_QMGR_NAME=QM2 --publish 1415:1414 --publish 9444:9443 --detach --env MQ_APP_PASSWORD=passw0rd --name QM2 icr.io/ibm-messaging/mq:latest
   ```
   
3. Add a new channel in the queue managers. Execute the following command to attach to the docker container running the queue manager.

   For QM1
   
   ```
   DEFINE CHANNEL('DEV.APP.SVRCONN.1') CHLTYPE(CLNTCONN) +
       CONNAME('localhost(1414)') QMNAME('QMGRP') +
       USERID('app') PASSWORD('passw0rd') REPLACE
   ```
   
   For QM2

   ```
   DEFINE CHANNEL('DEV.APP.SVRCONN.2') CHLTYPE(CLNTCONN) +
       CONNAME('localhost(1415)') QMNAME('QMGRP') +
       USERID('app') PASSWORD('passw0rd') REPLACE
   ```
   
4. The CCDT files used are located in the `ibmmq` folder. Ensure it is reference correctly in the server.xml. Copy to the `wlp` folder.

5. The WebSphere Liberty server configuration is in the `server.xml`. Copy to the `wlp/usr/servers/defaultServer` folder.

6. Start WebSphere Liberty using the following.

   ```
   cd ${wlp_folder}
   bin/server start
   ```
   
7. Using maven to build the war file. A mdbtest.war will be generated in the `target` folder.

   ```
   ./mvnw install
   ```
   
8. Then copy the war file into the `usr/servers/defaultServer/dropins`

9. To test the message put, run the following

   ```
   curl -kv -X POST -d 'msg=test message' http://localhost:9080/mdbtest/api/enqueue
   ```
   
References:

https://www.ibm.com/support/pages/using-ccdt-file-websphere-application-server-access-websphere-mq-queue-managers

https://www.ibm.com/docs/en/ibm-mq/9.3?topic=uacrijsjee-using-automatic-client-reconnection-in-java-ee-environments

https://www.ibm.com/docs/en/ibm-mq/9.2?topic=ccdt-json-examples

https://www.ibm.com/docs/en/was-liberty/nd?topic=SSAW57_liberty/com.ibm.websphere.liberty.autogen.zos.doc/ae/rwlp_config_jmsActivationSpec.html

https://www.ibm.com/docs/en/ibm-mq/9.3?topic=environments-activation-specifications

https://www.ibm.com/docs/en/was-liberty/base?topic=SSEQTP_liberty/com.ibm.websphere.wlp.zseries.doc/ae/twlp_batch_multiJVMmq.html

https://www.ibm.com/docs/en/was-liberty/base?topic=management-liberty-features

https://www.ibm.com/docs/en/ibm-mq/9.3?topic=uacrijee-support-automatic-client-reconnection-in-java-ee-environments

https://www.ibm.com/support/pages/websphere-liberty-developers

https://askmiddlewareexpert.com/websphere-mq-how-to-easily-generate-channel-tab-alter-it/

https://www.ibm.com/support/pages/using-websphere-mq-automatic-client-reconnection-websphere-mq-classes-jms

https://www.ibm.com/support/pages/how-increase-startup-reconnect-retries-and-retry-intervals-ibm-global-mailboxing-websphere-mq-queue-manager
