# Web Engineering 2022-23 Project Description  

## 1 Introduction
This is a group project for 2-3 students to practice web engineering concepts discussed in lectures and tutorials. The project aims to develop a web application based on the Spotify 1921-2020 dataset, specifically the songs statistics set available at Kaggle. The dataset is available in CSV format and is relatively large, so it needs to be processed before incorporation into the application. The project has three milestones with associated deliverables and deadlines.

## 2 Case Study Description
The goal of the project is to deliver basic and advanced features based on the Spotify dataset as a web application. The app developers are encouraged to identify further features to propose and implement.

## 3 Milestones
There are three milestones for the project. The first milestone is common to all groups, while the second and third milestones are specific to the design decisions taken by each group. The following are the requirements for each milestone:

### 3.1 M1: API Design

* Design and document a RESTful API with six functionalities
* Each API endpoint must support both JSON and CSV representations of the resources
* The documentation of the API must cover at least its name, functionality, how to access it, representations it accepts and returns, and what errors it returns
* Consider using an appropriate tool for API specification/documentation

### 3.2 M2: API Implementation

* Implement the API as the back-end of the app using the technologies and programming languages of your choice
* Document the implementation code appropriately
* Update the REST API documentation and specification if necessary in case of deviations from the initial API design

### 3.3 M3: User Interface Implementation, Deployment, and Project Reporting
* Implement a front-end for the web app which builds on the implemented API to offer a user interface (UI)
* All functionalities designed in M1 and implemented in M2 must be easily accessible through it
* Provide all the necessary documentation for your application, including a short report documenting your efforts on the project, the outcomes of all milestones, and the distribution of work among group members.
* All documentation artifacts must be navigable and visible by browser alone
* Provide a docker-compose.yml file that will start your complete application after just running docker-compose up in the root of your repository, using ".env" files to configure the ports on which the application will be made available on the Docker host where necessary

## 4 Deliverables & Assessment

The project has four deliverables with associated deadlines. All reports and software are to be delivered through private GitHub repositories. The following are the assessment points for the project:

* Software: The web app satisfies the requirements as described in Section 3. The endpoints designed for milestone M1 are consistent in their style and interaction with the API consumer and follow good design principles.
* Documentation: The documentation of the web app, in terms of code comments, API documentation, and project report is sufficient for any developer with a web engineering background to easily understand how the delivered software works.
* Demonstrable functionality: The web app can be independently deployed using only the provided documentation and scripts, without interventions to its code or configurations beyond what is absolutely essential.
* Grades are given per project since they reflect the team effort, except where and when it can be established clearly that there has actually been none. Projects that ensure that all requirements for the milestones discussed in Section 3 are fulfilled receive a maximum of 7.0. The remaining 3.0 points will be given to projects providing the users with added value.

The assignment scored a 7/10.
