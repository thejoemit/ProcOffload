# procoffload
This project is designed to provide offloading of process intensive tasks to a higher power server platform. The design goal is to provide an android software that will offload users submitteded files for CPU intensive activities. The results of these activities should be made avalible to the user on the same software.

# Design / Architecture

The system is comprised of a client-server architecture, where the server may be a gateway to more processing engines or a cloud platform. The android component will be used to pass files to the server and be able to recieve/display dynamic results. The server application will provide accounting as well as a processing engine. The user interaction functionality will be provided through dynamic web code and a user database. The processing engine will be done seperatley in bash to automate processing software and can be called from webcode. 
