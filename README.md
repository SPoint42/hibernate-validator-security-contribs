# Hibernate Validator Security Contribs

## Description
This project aims at providing a set of content-checking constraint annotations, 
focused on security, using the JSR303 RI Hibernate Validation. 
(http://www.hibernate.org/subprojects/validator.html).

This JSR can be used to perform security check on input user data and then it's for this 
reason that i decided to use it as framework.

This project is implemented using Maven (v3.x) structure.

![Built on CloudBees](http://www.cloudbees.com/sites/default/files/Button-Built-on-CB-1.png) 

![Hosted on GitHub](http://alx.github.com/gitbook/assets/images/github.png) 

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

![Build Status](https://righettod.ci.cloudbees.com/buildStatus/icon?job=HibernateValidatorSecurityContribs)

## Release notes

Details about the content of each release can be found on:

https://righettod.ci.cloudbees.com/job/HibernateValidatorSecurityContribs/site/release-notes.html

## Roadmap

* Migrate to Java 8,
* Add annotation for NoSQL injection protection.
