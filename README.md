# Project Name

## Team Information
We created our UI mock-up on Figma: https://www.figma.com/design/YSTlU7VrgzNDMusT0dzmcn/Study-Nook?node-id=1-2&t=zdmx0CAQMoOZQGze-1
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
Name: Study Timer

Primary actor: User starting study session

Preconditions: 
- Timer hasn't started

Trigger: 
- The student sets timer, selects drinks and starts timer

Success outcome: Study session finishes and is recorded into study history

Main success scenario: 
1. User sets duration of their study
2. User selects a drink from a menu of drinks
3. User click starts
4. Drink decreases relative to timer
5. Timer reaches zero
6. Study session recorded into history

Extensions: 
- Invalid time: prompt user to set time again
- Pause: User pauses the time and animation. Pause time lasts for 15 minutes, and user must choose to resume their study session within this time or let the pause time out.
- Resume: User restarts the timer after pausing
- Early termination: User ends the study session before set time and session is recorded as interrupted
- Pause timeout: User chooses to pause study session and the pause timer runs out so the study session automatically terminates and saves
### Use Case 2
Name: Study goal

Primary actor: User setting/viewing goals

Preconditions:
- Application running

Trigger: User opens daily goal section

Success outcome: Progress of daily goal completion is displayed

Main success scenario:
1. User opens daily goal section
2. User enters daily or weekly target number of study hours
3. System validates input
4. System saves input
5. System retrieves record of study session
6. System calculates progress towards goal
7. System displays progress to user

Extensions:
- Invalid input: prompt user to input again
- Edit goal: user changes the target
- Completed goal: user reaches the goal
### Use Case 3
Name: Start timer

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
| Theint Thin Zar Thu | Study Goals |
| Onyx | Weather-based Environment |
| Melody Luo | Study Timer |
| Rolly Gao | Study Analytics |

---

## Entities

### Entity 1

### Entity 2

(Add additional entities as needed.)

---

## API Usage

**API Name:** Weather API

**Documentation:** https://www.weatherapi.com/docs/

Example call: Current Weather London

{ "location": { "name": "London", "region": "City of London, Greater London", "country": "United Kingdom", "lat": 51.5171, "lon": -0.1062, "tz_id": "Europe/London", "localtime_epoch": 1794846341, "localtime": "2026-11-16 16:25" }, "current": { "last_updated_epoch": 1794845700, "last_updated": "2026-11-16 16:15", "temp_c": 6, "temp_f": 42.8, "is_day": 0, "condition": { "text": "Partly Cloudy", "icon": "//cdn.weatherapi.com/weather/64x64/night/116.png", "code": 1003 }, "wind_mph": 2.2, "wind_degree": 279, "pressure_mb": 1005, "precip_mm": 0, "humidity": 70, "cloud": 26, "feelslike_c": 3.6, "windchill_c": 6, "heatindex_c": 6, "vis_miles": 6, "uv": 0, "gust_mph": 6.4, "will_it_rain": 0, "chance_of_rain": 8, "will_it_snow": 0, "chance_of_snow": 0 } }

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
