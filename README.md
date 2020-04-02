# PAO

Repository for Advanced object-oriented programming laboratories (Java programming language).

# Project: Medical Management System

## Class schema:
- User
- Hospital Manager
- Person (abstract class)
- Patient inherit from Person
- Minor, Adult, Senior inherit from Patient
- Drug (contains the name of the drug and the recommended dose)
- MedicalCondition (contains nameOfInllness, firstDateOfReport, lastDateOfReport and a list of presciption drugs)

## Actions within the system
0. Exit.
1. Enroll new patient to a general practitioner.
2. Print all enrolled patients.
3. List all enrolled patients for a given general practitioner.
4. List all doctors.
5. List all doctors for a given specialization.
6. Schedule a medical consultation.
7. List all scheduled consultations for a given doctor.

*** Auxiliar actions (used by hospital manager) ***
- Choose a doctor.
- Print working program for a given doctor.
- Print available spots for a given doctor.

 *** Under construction: ***
- Add a new medical condition to a given patient.
- Remove a medical condition from a given patient.
- Change medication for a given patient.


