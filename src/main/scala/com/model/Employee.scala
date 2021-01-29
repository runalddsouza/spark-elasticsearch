package com.model

case class Employee(id: Long, name: Name, personalDetails: PersonalDetails, employmentInfo: EmploymentInfo, location: Location)

case class Name(prefix: String, firstName: String, lastName: String, fatherName: String, motherName: String)

case class PersonalDetails(gender: Char, dateOfBirth: String, email: String)

case class EmploymentInfo(dateOfJoining: String, salary: BigDecimal)

case class Location(city: String, state: String, zip: String)
