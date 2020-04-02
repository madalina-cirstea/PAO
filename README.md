# PAO

Repository for Advanced object-oriented programming laboratories (Java programming language).

# Project: Medical Management System

## Class schema:
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

## Actions within the system
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

