# Hibernate Validator Security Contribs

## Description
This project aims at providing a set of content-checking constraint annotations, 
focused on security, using the JSR303 RI Hibernate Validation. 
(http://www.hibernate.org/subprojects/validator.html).

This JSR can be used to perform security check on input user data and then it's for this 
reason that i decided to use it as framework.

This project is implemented using Maven (v3.x) structure.

![Built on CloudBees](http://web-static-cloudfront.s3.amazonaws.com/images/badges/BuiltOnDEV.png)

![Hosted on GitHub](http://alx.github.com/gitbook/assets/images/github.png)

![Powered by Logica](http://www.logica.com/img/interface/logo.png)

## Project distribution & informations

Type command below to obtains project identity card:

<pre>mvn clean site</pre>

Type command below to obtains project binary distribution:

<pre>mvn clean package</pre>

Artefacts are published into official Maven repositories (see project documentation) but 
you can download development built versions from continuous integration server on:

https://righettod.ci.cloudbees.com/job/HibernateValidatorSecurityContribs

## Issues tracking
Issues are tracked on: 

https://github.com/righettod/hibernate-validator-security-contribs/issues

## Documentation
Project documentation is available on continuous integration server on:

https://righettod.ci.cloudbees.com/job/HibernateValidatorSecurityContribs/site

## Continuous integration
Project is built at each commit using Jenkins on CloudBees on:

https://righettod.ci.cloudbees.com/job/HibernateValidatorSecurityContribs

## Version history
### 1.0.0 (Released)

<li>- First official release.

### 1.1.0 (Released)

<li>- Add new annotations + unit tests.

<li>- Refactor documentation.

### 1.2.0 (Released)

<li>- Fix an issue in LDAP validator : Miss special character.

<li>- Fix an issue in all validators : If an exception is throwed during the validation then the validation pass.

<li>- Add an annotation to check SMTP injection.

<li>- Add an annotation to check Path Traversal injection.

<li>- Add an annotation to check OS commands chaining.

<li>- Add an base class to add bean validity check features by inheritance.

<li>- Change validation checking rules of the NoTag annotation validator.

- - -

Feel free to create a ticket for issues/remarks/features ;o)

Kind regards,

Dominique Righetto