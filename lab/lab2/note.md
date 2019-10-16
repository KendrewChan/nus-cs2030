## Loaders

- Used to serve the cruise
- As many loaders are required
- Serve until after x number of minutes
- Cannot serve another cruise while serving

e.g.
if cruise arrive 12pm && need 2 loaders && 60min:
12pm , 2 vacant loaders
time: 12pm - 1pm
can only serve afterwards

## Cruise

- id: unique
  - is String (A1234)
- timeOfArrival
  - in HHMM format (e.g. 2359)
- mac 20 cruises arriv in a day
- served by loaders immeidately

### 2 types

**Cruise**

- fixed 30 min
- requires one loader

**Big Cruise**

- starts with char B
- - T minutes to serve: 0 - 99
- X loader to load 0 to 0

### Post-lab review

- Should not actually store the BigCruise data in Cruise
- Original class should be extensible
- isRecycled property should not be inside the cruise
- One class should only do one thing

**Focus on:**

- inheritance
- polymorphism
- overriding
