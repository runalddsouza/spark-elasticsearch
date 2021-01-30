package com.model

case class Employee(id: Long, updateDate: Long, name: Name, personalDetails: PersonalDetails, employmentInfo: EmploymentInfo,
                    address: Address)

case class Name(prefix: String, firstName: String, lastName: String, fatherName: String, motherName: String)

case class PersonalDetails(gender: String, dateOfBirth: String, email: String, phone: String)

case class EmploymentInfo(userId: String, dateOfJoining: String, salary: String)

case class Address(city: String, state: String, zip: String)
