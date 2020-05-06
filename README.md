# PAO

Repository for Advanced object-oriented programming laboratories (Java programming language).

# Project: Medical Management System

## 1st Checkpoint

### Class schema:

- Main
- User - defines user actions for inserting input and confirmations
- Hospital Manager - manages actions within the hospital and holds hospital's treatment scheme
- Hospital - holds name of the hospital and lists of hospital's doctors and enrolled patients
- Person (abstract class)
- Doctor inherit from Person - includes doctor's specialization, scheduled consultations, program and available slots
- GeneralPractitioner inherit from Doctor - includes the list of the patients enrolled to the general practitioner
- Patient inherit from Person - contains the assigned doctor and the medical history
- Minor, Adult, Senior inherit from Patient
- Consultation - holds the patient and the date for a medical consult
- TimeSlot - holds start and end hour for a time slot
- Drug - contains the name of the drug and the recommended dose (measured in pills per day)
- MedicalCondition - contains the name of the illness, first date of report and a list of presciption drugs

### Actions within the system

0. Exit.
1. Enroll new patient to a general practitioner.
2. Print all enrolled patients per hospital.
3. List all enrolled patients for a given general practitioner.
4. List all doctors.
5. List all doctors for a given specialization.
6. Schedule a medical consultation.
7. List all scheduled consultations for a given doctor.
8. List all scheduled consultations per hospital.
9. Add a medical condition to a given patient.
10. Print the medical history of a given patient.

### Notes:

Input must be inserted exactly as required (input validation will be included further along with dealing with exceptions).

## 2nd Checkpoint

### Improvements

- added input validation and handled the exceptions generated by user's incorect input (InputMismatchException, IllegalArgumentException etc.)
- organised Java classes into packages :
  - **app** (app related classes: Main, User, HospitalManager)
  - **data** package contains packages for data headers, data generators, data managers, doctors data and patients data (among with individuals medical history data for each patient)
  - **logs** holds the audit service class (LoggingManager.java) and the .txt file containing the loggings
  - **medical** holds medical related classes (Doctor, Consultation, Drug etc.)
  - **patient** holds patient related classes

### Meeting the requirements

- created generic interface **DataGenerator** for data generation purposes and implemented it in classes for generating random data for patients, doctors and medical information related to patients
- created generic interface **DataManager** for csv file manipulation and implemented it in classes capable of loading, reading, writing and appending to CSV files
- implemented a Singleton audit service **LoggingManager** which logs the informations about the actions executed within the system (hith either failure or succes messages)


### Notes:

I known the deadline was a little bit overdue but the data generation process took a little more time than I expected. I hope this wont cost much, but even so I understand it.
