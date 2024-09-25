# EBook_RestAssured_API_Automation_Framework
REST Assured API test automation framework using Java + Maven + TestNG. Framework.

Technologies/Tools used in building the framework
---------------------------------------------------------
- Rest Assured
- TestNG
- Java
- Allure Reports
- Hamcrest
- Lombok
- IntelliJ
- GitHub
- Jenkins

  
Framework implements below best practices
---------------------------------------------------------
- Scalable and extensible
- Reusable Rest Assured specifications
- Reusable Rest Assured API requests
- Separation of API layer from test layer
- POJOs for Serialization and Deserialization
- Singleton Design Pattern
- Lombok for reducing Boilerplate code
- Builder pattern for Setter methods in POJOs
- Robust reporting and logging using Allure
- Automate positive and negative scenarios
- Support parallel execution
- Automated access token renewal
- Maven command line execution
- Integration with Git
- Integration with Jenkins



To install and set up the RestAssuredTestNGFramework from the GitHub repository you mentioned, follow these instructions:
---------------------------------------------------------

Prerequisites
Java JDK: Make sure you have Java JDK installed (preferably version 8 or higher). You can verify your installation by running:

bash
Copy code
java -version

Maven: Ensure that Apache Maven is installed. You can check this by running:

bash
Copy code
mvn -version
Git: Make sure you have Git installed to clone the repository.

Step 1: Clone the Repository
Open your terminal and run the following command to clone the repository:

bash
Copy code
git clone https://github.com/Neha3290/EBook_RestAssured_API_Automation_Framework.git

Navigate into the cloned directory:

`bash
Copy code
cd RestAssuredTestNGFramework`

Step 2: Import into Your IDE
You can open this project in your favorite IDE (like IntelliJ IDEA or Eclipse).

In IntelliJ IDEA:
Open IntelliJ IDEA.
Click on "Open" and select the cloned RestAssuredTestNGFramework directory.
IntelliJ will automatically recognize it as a Maven project and import the dependencies.
In Eclipse:
Open Eclipse.
Go to File > Import.
Choose Existing Maven Projects and click Next.
Browse to the cloned RestAssuredTestNGFramework directory and click Finish.
Step 3: Build the Project
Once the project is imported, build the project using Maven. You can do this from the terminal within the project directory:

bash
Copy code
mvn clean install

Step 4: To run locally have to set VM option, for that go to "Run >> Edit Configuration >> TestNG". Here in VM option add this below parameter
   `-DBASE_URI=https://fakerestapi.azurewebsites.net`

Step 5: To Run all test cases in local, run this below command -
   `mvn clean test -DBASE_URI=https://fakerestapi.azurewebsites.net`

Step 6: To generate the Allure html report, run this below command -
    `allure serve target/allure-results`
 

Additional Notes
Sometimes, repositories have additional setup instructions in their README files, so it's a good idea to review that for any specific configurations or dependencies.
Update Dependencies: If you're using a specific version of Java or libraries, ensure that the pom.xml reflects those versions, and update if necessary.
Conclusion

