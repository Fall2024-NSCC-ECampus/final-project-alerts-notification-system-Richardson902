# SafetyNet Alerts System

## Overview
The **SafetyNet Alerts System** is a Spring Boot application that provides notifications and information about individuals and households under specific circumstances. It supports features like child alerts, flood alerts, person details, phone contact lists, and firestation jurisdiction details.

## Key Features
- Retrieve child-specific data for an address.
- Group and report households affected by floods.
- Fetch personal details and medical information for individuals.
- Access phone numbers of residents within a firestation’s jurisdiction.
- Manage data for firestations and their associated areas.

## Technologies
- **Java**: Primary programming language.
- **Spring Boot**: Framework for building RESTful APIs.
- **Maven**: Dependency and project management.
- **MySQL**: Database for storing application data.

## Project Structure
The application uses the **Model-View-Controller (MVC)** design pattern, ensuring modularity and separation of concerns.

## Service Descriptions
### Child Alert Service
Retrieves information about children and family members residing at a specific address.

### Flood Alert Service
Groups persons by households affected within firestations’ jurisdictions.

### Person Information Service
Fetches personal information like name, age, address, email, and medical records.

### Phone Alert Service
Provides phone numbers of residents within a specific firestation’s area.

### Firestation Service
Handles firestation-related queries, such as listing individuals under its jurisdiction.

## API Endpoints
### Child Alert
- **`GET /childAlert`**
    - **Query Parameters**:
        - `address`: Address to search.
        - `city`: City of the address.
    - **Description**: Retrieves a list of children (under 18) living at the specified address.

### Flood Alert
- **`GET /flood/stations`**
    - **Query Parameters**:
        - `stations`: Comma-separated list of firestation numbers.
    - **Description**: Returns grouped household data for areas covered by specified firestations.

### Person Information
- **`GET /personInfo`**
    - **Query Parameters**:
        - `firstName`: First name of the person.
        - `lastName`: Last name of the person.
    - **Description**: Retrieves detailed information about a person, including medical records.

- **`GET /communityEmail`**
    - **Query Parameters**:
        - `city`: City to search.
    - **Description**: Returns email addresses of residents in the specified city.

### Phone Alert
- **`GET /phoneAlert`**
    - **Query Parameters**:
        - `firestation`: Firestation number.
    - **Description**: Retrieves phone numbers of residents covered by the specified firestation.

### Firestation Service
- **`GET /firestation`**
    - **Query Parameters**:
        - `stationNumber`: Firestation number.
    - **Description**: Lists individuals covered under the specified firestation, grouped by adults and children.

- **`GET /fire`**
    - **Query Parameters**:
        - `address`: Address to search.
        - `city`: City of the address.
    - **Description**: Retrieves details about a firestation and its associated persons based on the provided address.

## How to Use
1. Clone the repository and set up the application locally.
2. Configure the database connection in `application.properties`.
3. Run the application using your preferred IDE or through the command line with `mvn spring-boot:run`.
4. Use tools like Postman or a web browser to test the endpoints listed above.

## Notes
- Ensure the database schema matches the expected structure to avoid runtime issues.
- The application will throw meaningful errors (e.g., `ResourceNotFoundException`) for invalid queries or missing data.

## Output
Here are some examples hitting the urls and receiving the json via postman

### Child Alert
![Child-alert](https://github.com/Fall2024-NSCC-ECampus/final-project-alerts-notification-system-Richardson902/blob/main/output/child-alert.JPG)

### Community Email
![Email](https://github.com/Fall2024-NSCC-ECampus/final-project-alerts-notification-system-Richardson902/blob/main/output/email.JPG)

### Fire Alert
![Fire](https://github.com/Fall2024-NSCC-ECampus/final-project-alerts-notification-system-Richardson902/blob/main/output/fire-alert.JPG)

### Flood Alert
![Flood](https://github.com/Fall2024-NSCC-ECampus/final-project-alerts-notification-system-Richardson902/blob/main/output/flood.JPG)

### Person Info
![Person](https://github.com/Fall2024-NSCC-ECampus/final-project-alerts-notification-system-Richardson902/blob/main/output/person-info.JPG)

### Phone Alert
![Phone](https://github.com/Fall2024-NSCC-ECampus/final-project-alerts-notification-system-Richardson902/blob/main/output/phone-alert.JPG)

### Firestation Alert
![Firestation](https://github.com/Fall2024-NSCC-ECampus/final-project-alerts-notification-system-Richardson902/blob/main/output/station-number.JPG)
