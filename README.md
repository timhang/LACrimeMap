# CsvToSql Project

## Introduction
This project contains a Java application (`CsvToSql.java`) that reads data from a CSV file and inserts it into a MySQL database using JDBC. The application distributes data entries across three different database instances based on the modulus of an ID field.

## Prerequisites
- Java JDK 17.
- MySQL Server (three instances configured for this project).
- IntelliJ IDEA (Community or Ultimate version).
- opencsv library.
- MySQL JDBC Driver.

## Project Structure
LACrimeMap/
│

├── .gradle/ # Gradle files (automatically generated)

├── .idea/ # IntelliJ project files

├── build/ # Compiled code and other build artifacts

├── gradle/ # Gradle wrapper scripts

├── src/

│ ├── main/

│ │ ├── java/

│ │ │ └── CsvToSql.java

│ │ │ └── MultipleMySQLConnections.java

│ │ └── resources/ # Resources for the project, such as configurations

│ └── test/ # Test source directory (empty in this structure)

├── .gitignore # Specifies intentionally untracked files to ignore

├── Crime_Data_from_2020_to_Present.csv # CSV file with crime data

├── gradlew # Gradle wrapper executable script (Unix)

├── gradlew.bat # Gradle wrapper executable script (Windows)

└── settings.gradle # Gradle settings file

## Project Setup
1. **Clone the Repository:**
   - Clone this project to your local machine or download the source code.

2. **Open Project in IntelliJ IDEA:**
   - Open IntelliJ IDEA.
   - Click on `File > Open` and select the project directory.

3. **Configure JDK:**
   - In IntelliJ, go to `File > Project Structure > Project`.
   - Set the Project SDK to your installed JDK version.

4. **Add External Libraries:**
   - Download the `opencsv` library and MySQL JDBC driver (`mysql-connector-java`).
   - Add these JARs to your project:
     - Go to `File > Project Structure > Libraries`.
     - Click the `+` button and select `Java`.
     - Navigate to the downloaded JAR files, select them, and click `OK`.

## Database Configuration
Replace the placeholder values in the JDBC URL, username, and password with your actual database configurations.

```java
String user = "your_username";
String password = "your_password";
```

```java
// Update these URLs with your actual MySQL instance details
String url1 = "jdbc:mysql://{url}:3306/CrimeData";
String url2 = "jdbc:mysql://{url}:3306/CrimeData";
String url3 = "jdbc:mysql://{url}:3306/CrimeData";
```

Running the Application
Configure the CSV File Path:
Ensure the path to the CSV file in the code points to your actual CSV file location.
Download the CSV File From:
https://www.kaggle.com/datasets/chaitanyakck/crime-data-from-2020-to-present?select=Crime_Data_from_2020_to_Present.csv

```java
String csvFilePath = "E:\\Path\\To\\Your\\Crime_Data.csv";
```

Run the Application:
Right-click on CsvToSql.java in the project explorer.
Click Run 'CsvToSql.main()'.
Expected Output
The application will connect to the MySQL databases and insert rows from the CSV file into the respective databases based on the calculation of ID % 3. Console output will display progress and any errors encountered during the process.

Troubleshooting
Ensure all database instances are accessible and that the user has sufficient privileges.
Verify the CSV file path and format.
Check the console for any stack traces to diagnose errors related to database connections or data formatting.
