# PlanIT-SDETAssessment
This repository is created to upload the assessment as part of PlanIT Interview

## 1.) 10 Priority test cases for Jupiter website

I have considered that for a website, priority is that any user should be able to add items and check out properly and receive the order number.

##### Tools used:

Java - version 11
Maven - version 3.8.6
Selenium- version 4.3
TestNG - version 7.6.1 
Crossbrowser Testing - Chrome & FireFox
OS- Windows 10

##### Steps: 

1.) Open command prompt ( or terminal) and clone this repository using below command
> git clone https://github.com/Shaliha280799/PlanIT-SDETAssessment.git

2.) go to jupiter-toys project 
> cd ../jupiter-toys

3.) open command line and run the below  command
> mvn clean test

4.) reports will be generated in the folder {GIT_REPO}/jupiter-toys/target/surefire-reports/emailable-report.html


## 2.) Challenges

I have taken the below challenges and written solution in the language Java ( version 11). Both the challenges can be run as separate java files and I have also written unit test case for one of the challenges.

Challenge 6:
Make an API call to https://petstore.swagger.io/v2/pet/findByStatus?status=available and display a count of
the number of pets in the result

Challenge 2:
Write a solution to find the character that has the highest number of occurrences within a certain string, ignoring
case. If there is more than one character with equal highest occurrences, return the character that appeared first
within the string.
For example:
Input: "Character"
Output: c




