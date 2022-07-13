
###  Framework: FINCO

# Introduction
A simple FINCO framework, default support and extensible functionality for party and account.

## Package Structure

### 1. framework

```
├── project.framework.context
│   - FactoryServiceRetriever.java
│   - FrameworkApplicationBootstrapper.java
│   ├── config
│       - FrameworkContextConfigurer.java
├── project.framework.support
    - Subject.java
    - Observer.java
├── project.framework.core
    ├── accountdetails
    |   ├── model
    |   |   ├── party
    |   |       - IParty.java
    |   |       - IPerson.java
    |   |       - IOrganization.java
    |   |       - Party.java
    |   |       - Organization.java
    |   |       - Person.java
    |   |   ├── account.java
    |   |       - IAccount.java
    |   |       - Account.java
    |   |       - IEntry.java
    |   |       - Entry.java
    |   - AbstractAccountService
    |   - IEmailPartyService
    |   - IInterestCalculationStrategy
    |   - IReportingStrategy
    |   ├── service
    |   |   - DefaultEmailToPartyService
    |   |   - DefaultInterestCalculationStrategy
    |   |   - DefaultReportingStrategy
    |   |   - DefaultAccountService
    ├── storage
    |   - AbstractStorageService
    |       ├── service
    |           - DefaultInMemoryStorageService
    ├── util
├── project.framework.gui
   ├── defaults
       |  - ... // defaults
    |  - AbstractDefaultFrameworkGUI
    |  - GenericJTableModel
    |  - TableModelRowMapper


```

## Structure
 - __"project.framework.context"__ provides a way to configure services and bootstrap client application.
 - __"project.framework.support"__ any generalized classes that adds incrementing functionality and acts as project.framework.support classes.
 - __"project.framework.core"__ contains all the project.framework.core functionalities
    - __"accountDetails"__ : contains models, and services for account related features
        - "model": IParty and IAccount
        - "service" default implementation for the abstractions
        - ... all abstractions of "accountDetails"
    - __"storage"__ : Extensible and default storage service 
    - __"util"__ any static utility methods or utility classes, that needs to be reused.
 -  __"project.framework.gui"__ contains all the gui helper and default classes


| <a target="_blank" href="#">![Framework](./docs/v3/FrameworkV444.PNG)</a> |
|------------------------------------------------------------------------------|
| Framework                                                                    |


| <a target="_blank" href="#">![GUI](./docs/v3/GUIV444.PNG)</a> |
|---------------------------------------------------------------|
| GUI                                                           |

# Development

Note: Currently All Modules Under Development 

1. framework
2. bank
3. ccard


### # Documentation

### # Running unit tests

### # Build

### # Running application:

