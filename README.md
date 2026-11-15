# Project Name

## Team Information

### Team Members
- Theint Thin Zar Thu
- Onyx Chavanond
- Melody Luo
- Rolly Gao

---

## Project Description

### Domain
Productivity

Inspired by the "study until I finish this drink" concept, this application allows user to select a drink and set a timer. The drink will gradually decrease until the timer ends. The background of the timer will change according to the weather so that user can keep track of the weather outside when they are indoors. This application aims to increase productivity of users.

---

## User Stories

### User Story 1
As a user, I want to be able to set a timer and select a drink. The drink will decrease as the timer starts. This will provide a visualization of my progress which will help increase my productivity.
### User Story 2
As a user, I want to set a study-time goal and monitor my progress so I can accomplish my study goals.
### User Story 3
As a user, I want to be able to view my past study statistics so I can keep track of my productivity.
### User Story 4
As a user, I want the background to change according to the weather of my chosen city, so I have a personalized study environment.

(Add additional user stories as needed.)

---

## Use Cases

### Use Case 1
Name: Set a timer

Primary actor: User inputting time

Preconditions: 
- Timer hasn't started

Trigger: 

Success outcome: 

Main success scenario: 

Extensions: 
### Use Case 2
Name: Choose drink

Primary actor: User selecting drink

Preconditions: 

Trigger:

Success outcome:

Main success scenario:

Extensions:

### Use Case 3
Name: View Study History and Analytics

Primary actor: User clicking start timer

Preconditions: Application running

Trigger: User opens the Study History screen.

Success outcome: User can review saved study sessions and their study patterns.

Main success scenario:
1. User opens the Study History screen.
2. System retrieves previously saved study sessions.
3. System displays session dates, durations, and completion statuses.
4. User selects a date range.
5. System filters the session records.
6. System calculates and shows statistics such as total focus time and completed session count.
7. System displays the results in a clean way, such as a simple chart.

Extensions:
- No saved sessions: System displays an empty message.
- No matching dates: System indicates that no records match the selected filter.
- Data loading failure: System displays an error message.

### Use Case 4
Name: Weather-based Study Environment

Primary actor: User

Preconditions: Application running

Trigger: User enters or selects a city and requests a weather update.

Success outcome: System displays weather information and related background.

Main success scenario:
1. User inputs a city. 
2. System finds geographic coordinates of the city.
3. System sends a request to an external weather API.
4. System receives and interprets the weather information.
5. System categorizes the weather into "sunny", "cloudy", "rainy" and "snowy".
6. Application updates the background to match the weather conditions.
7. Application displays the weather description and temperature.

Extensions:
- Invalid city: System asks the user to enter another location.
- API unavailable: System keeps a default background and displays an error message.
- Unexpected weather: System uses a neutral background.
- Slow API response: The timer remains responsive while weather data is loading.

---

## Minimal Viable Product (MVP)

| Team Member | MVP Use Case |
|-------------|--------------|
| Theint Thin Zar Thu | Use Case 2|
| Onyx | Use Case 4|
| Melody Luo | Use Case 1|
| Rolly Gao | Use Case 3|

---

## Entities

### Entity 1

### Entity 2

(Add additional entities as needed.)

---

## API Usage

**API Name:** Weather API

**Documentation:** [Link]

**How it is used in the program:**

---

## Design Diagrams

### UML Class Diagram


### Sequence Diagrams

---

## How to Run

[Instructions for running the program.]

---

## Notes
