import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class CsvToSql {
    public static void main(String[] args) {
//        String jdbcURL = "jdbc:sqlserver://YOUR_SERVER:PORT;databaseName=YOUR_DATABASE";
//        String username = "YOUR_USERNAME";
//        String password = "YOUR_PASSWORD";

        String databaseName = "your_database_name";
        String user = "root";
        String password = "your_password";

        // Connection URLs for three MySQL instances
        String url1 = "jdbc:mysql://{url}:3306/CrimeData";
        String url2 = "jdbc:mysql://{url}:3306/CrimeData";
        String url3 = "jdbc:mysql://{url}:3306/CrimeData";

        String csvFilePath = "E:\\SchoolWork\\DSCI551\\Project\\Crime_Data_from_2020_to_Present.csv";

        SimpleDateFormat csvDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");

        int batchSize = 20; // Number of rows to insert per batch

        try (Connection conn1 = DriverManager.getConnection(url1, user, "crimedata1");
             Connection conn2 = DriverManager.getConnection(url2, user, "crimedata2");
             Connection conn3 = DriverManager.getConnection(url3, user, "crimedata3");) {
        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            String[] nextLine;
            reader.readNext();
            int count = 0;
            while ((nextLine = reader.readNext()) != null) {
            //while (count <= 100) {
//                count++;
                // nextLine = reader.readNext();
                int dbIndex = Integer.parseInt(nextLine[0]) % 3;

                System.out.println("insert into db: " + dbIndex);
//                for (String e : nextLine) {
//
//                    System.out.print(e + " "); // Print each element separated by a space
//                }
                // Try-with-resources to automatically close connections after use

                    System.out.println("Connected to database " + dbIndex);
                    String sql = "INSERT INTO CrimeData (\n" +
                        "            DR_NO, Date_Rptd, DATE_OCC, TIME_OCC, AREA, AREA_NAME, Rpt_Dist_No, Crm_Cd, Crm_Cd_Desc, Vict_Age, Vict_Sex, Vict_Descent, Premis_Cd, Premis_Desc, Weapon_Used_Cd, Weapon_Desc, Status, Status_Desc, LOCATION, Cross_Street, LAT, LON\n" +
                        "        ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement statement = null;
                    if (dbIndex == 0) {
                        statement = conn1.prepareStatement(sql);
                    } else if (dbIndex == 1) {
                        statement = conn2.prepareStatement(sql);
                    } else {
                        statement = conn3.prepareStatement(sql);
                    }

                    // Assuming 28 columns in the CSV and table, adjust accordingly
                    statement.setString(1, nextLine[0]); // DR_NO VARCHAR(20)
                    java.util.Date date = csvDateFormat.parse(nextLine[1]);
                    statement.setDate(2, new Date(date.getTime())); // Date_Rptd DATETIME
                    date = csvDateFormat.parse(nextLine[2]);
                    statement.setDate(3, new Date(date.getTime())); // DATE_OCC DATETIME
                    if (nextLine[3].isEmpty()) {
                        nextLine[3] = "-100";
                    }
                    statement.setInt(4, Integer.parseInt(nextLine[3])); // TIME_OCC INT
                    if (nextLine[4].isEmpty()) {
                        nextLine[4] = "-100";
                    }
                    statement.setInt(5, Integer.parseInt(nextLine[4])); // AREA INT
                    statement.setString(6, nextLine[5]); // AREA_NAME TEXT
                    statement.setInt(7, Integer.parseInt(nextLine[6])); // Rpt_Dist_No TEXT
                    statement.setString(8, nextLine[8]); // Crm_Cd TEXT
                    statement.setString(9, nextLine[9]); // Crm_Cd_Desc TEXT
//                    statement.setString(11, nextLine[10]); // Mocodes VARCHAR(50)
                    if (nextLine[11].isEmpty()) {
                        nextLine[11] = "-100";
                    }
                    statement.setInt(10, Integer.parseInt(nextLine[11])); // Vict_Age INT
                    statement.setString(11, nextLine[12]); // Vict_Sex TEXT
                    statement.setString(12, nextLine[13]); // Vict_Descent TEXT
                    if (nextLine[14].isEmpty()) {
                        nextLine[14] = "-100";
                    }
                    statement.setInt(13, Integer.parseInt(nextLine[14])); // Premis_Cd INT
                    statement.setString(14, nextLine[15]); // Premis_Desc TEXT

                    statement.setString(15, nextLine[16]); // Weapon_Used_Cd TEXT
                    statement.setString(16, nextLine[17]); // Weapon_Desc TEXT
                    statement.setString(17, nextLine[18]); // Status TEXT
                    statement.setString(18, nextLine[19]); // Status_Desc TEXT
//                    if (nextLine[20].isEmpty()) {
//                        nextLine[20] = "-100";
//                    }
//                    statement.setInt(21, Integer.parseInt(nextLine[20])); // Crm_Cd_1 INT
//                    if (nextLine[21].isEmpty()) {
//                        nextLine[21] = "-100";
//                    }
//                    statement.setInt(22, Integer.parseInt(nextLine[21])); // Crm_Cd_2 INT
//                    if (nextLine[22].isEmpty()) {
//                        nextLine[22] = "-100";
//                    }
//                    statement.setInt(23, Integer.parseInt(nextLine[22])); // Crm_Cd_3 INT
//                    if (nextLine[23].isEmpty()) {
//                        nextLine[23] = "-100";
//                    }
//                    statement.setInt(24, Integer.parseInt(nextLine[23])); // Crm_Cd_4 INT
                    statement.setString(19, nextLine[24]); // LOCATION TEXT
                    statement.setString(20, nextLine[25]); // Cross_Street TEXT
                    statement.setFloat(21, Float.parseFloat(nextLine[26])); // LAT FLOAT(10,6)
                    statement.setFloat(22, Float.parseFloat(nextLine[27])); // LON FLOAT(10,6)
//                    for (int i = 0; i < 28; i++) {
//                        statement.setString(i + 1, nextLine[i]);
//                    }
                    statement.addBatch();
                    statement.executeBatch();
                    System.out.println("Data inserted into: " + dbIndex);
//                    if (count > 100) {
//                        break;
//                    }
                }
                System.out.println(); // New line for the next record
            }

        } catch (CsvValidationException | IOException | SQLException | ParseException e) {
            e.printStackTrace();
        }

//        try {
//            connection = DriverManager.getConnection(jdbcURL, username, password);
//            connection.setAutoCommit(false);
//
//            String sql = "INSERT INTO YOUR_TABLE_NAME (COLUMN1, COLUMN2, COLUMN3, ...) VALUES (?, ?, ?, ...)";
//            PreparedStatement statement = connection.prepareStatement(sql);
//
//            CSVReader reader = new CSVReader(new FileReader(csvFilePath));
//            String[] nextLine;
//            int count = 0;
//
//            reader.readNext(); // Skip header line
//
//            while ((nextLine = reader.readNext()) != null) {
//                // Set values based on the CSV line read
//                statement.setString(1, nextLine[0]);
//                statement.setString(2, nextLine[1]);
//                // ... set other parameters
//
//                statement.addBatch();
//
//                if (count % batchSize == 0) {
//                    statement.executeBatch();
//                }
//            }
//
//            statement.executeBatch(); // Insert remaining records
//            connection.commit();
//            connection.close();
//
//            System.out.println("Data has been inserted into the table.");
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            try {
//                if (connection != null) connection.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }
}
