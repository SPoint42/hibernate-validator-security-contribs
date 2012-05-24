# Hibernate Validator Security Contribs

## Description
This project aims at providing a set of content-checking constraint annotations, 
focused on security, using the JSR303 RI Hibernate Validation. 
(http://www.hibernate.org/subprojects/validator.html).

This JSR can be used to perform security check on input user data and then it's for this 
reason that i decided to use it as framework.

This project is implemented using Maven (v3.x) structure.

## Project distribution & informations

Type command below to obtains project identity card:

<pre>mvn clean site</pre>

Type command below to obtains project binary distribution:

<pre>mvn clean package</pre>

Artefacts are released into official Maven repositories (see project documentation) but 
you can download night build versions from continuous integration server on:

https://buildhive.cloudbees.com/job/righettod/job/hibernate-validator-security-contribs/lastStableBuild/com.github.righettod$hibernate-validator-security-contribs/

## Issues tracking
Issues are tracked on: 

https://github.com/righettod/hibernate-validator-security-contribs/issues

## Documentation
Project documentation is available on:

http://righettod.github.com/hibernate-validator-security-contribs

## Continuous integration
Project is build at each commit using BuildHive on:

https://buildhive.cloudbees.com/job/righettod/job/hibernate-validator-security-contribs

[![Build Status](https://buildhive.cloudbees.com/job/righettod/job/hibernate-validator-security-contribs/badge/icon)](https://buildhive.cloudbees.com/job/righettod/job/hibernate-validator-security-contribs/)

## Version history
### 1.0.0 (Released)

<li>- First official release.

### 1.1.0 (Released)

<li>- Add new annotations + unit tests.

<li>- Refactor documentation.

### 1.2.0 (Snapshot - Work in progress)

<li>- Fix an issue in LDAP validator : Miss special character.

<li>- Fix an issue in all validators : If an exception is throwed during the validation then the validation pass.

<li>- Add an annotation to check SMTP injection.

<li>- Add an annotation to check Path Traversal injection.

- - -

Feel free to create a ticket for issues/remarks/features ;o)

Kind regards,

Dominique Righetto