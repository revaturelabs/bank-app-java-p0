# Banking Console App - Project 0

## Description
The Bank app is a console-based application that simulates banking operations. 
Users can login via the command line and interact with the system.
A customer can apply for an account, view their balance, and make withdrawals and deposits. 
An employee can approve or deny accounts and view account balances for their customers.

## Purpose

We want to see that you can meet deadlines and that you can code.
The basic structure of the application has been created for you already.
You are expected to complete the requirements below and submit your code for review.
The project will be split into 2 parts and you will be given feedback on your progress after each part.

## Setup
Step 1: Use GitHub Classroom assignment link to setup your repo

Step 2: Clone the repository locally

Step 3: Import the project into workspace
* File > Open Projects From File System... (this will auto-detect which kind of project you're importing)
* Click "Directory" and find the location of the folder
* Note: your workspace directory must be different than the project directory.

Step 4: Start developing!

Now when you push to Github, a pull request will be opened and the unit tests will be run which report on the 
state of the project. In the pull request your trainer and QC member can provide feedback on your code
as you progress.

## Requirements - Part 1

You must complete these requirements by **Wednesday of Week 2** of training.

1. Implement enough of the user stories below to score 12 points in your test cases
2. Data is read from and written to a file system 
3. All user input is received using the java.util.Scanner class
4. Log4j is implemented to log events to a file. Appropriate log levels should be used.
5. Use the project structure given in your starter code. Only modify test classes if you need to refactor tests.

Additionally, test cases are implemented to score your completion of the requirements. Each passing test
gives you a certain number of points as noted in the test case. You should score at least 12 points by the
deadline for Part 1.

## Requirements - Part 2

You must complete these requirements by **Wednesday of Week 3** of training.

1. All unit tests should be passing at this point
2. Refactor your data access to read / write from a database instead of file system
3. Configuration should be externalized (e.g. read in from a file or environment variable) instead of hard-coded

## Core Requirements / User Stories
Total Points: 23 points

* As a user, I can register with a username and password
  * 1 point
* As a user, I can login.
  * 2 points
* As a customer, I can apply for a new bank account with a starting balance.
  * 3 points
* As a customer, I can view the balance of a specific account.
  * 1 point
* As a customer, I can make a withdrawal or deposit to a specific account.
  * 2 points
* As the system, I reject invalid transactions.
  * Ex:
    * A withdrawal that would result in a negative balance.
    * A deposit or withdrawal of negative money.
  * 2 points
* As an employee, I can approve or reject an account.
  * 2 points
* As an employee, I can view a customer's bank accounts.
  * 1 point
* As a user, I can register for a customer account.
  * 3 points
* As a customer, I can post a money transfer to another account.
  * 3 points
* As a customer, I can accept a money transfer from another account.
  * 2 points
* A an employee, I can view a log of all transactions.
  * 2 points

## Extra
You are welcome to add additional functionality beyond these base requirements, but you should have the core functionality finished first. Also,
don't forget to re-run your unit tests when you add new features to check that you aren't introducing regressions into the codebase. Below are 
some sample stretch goals to aim for.

### Stretch Goals
* Accounts can be Checking or Savings accounts
  * Checking accounts have no limit on transactions
  * Savings accounts have a limit of 5 transactions per month
* Employees can blacklist a customer
* Blacklisted customers cannot login or perform transactions
* Transactions can be filtered by user, account, type of transaction, or amount
* Record customer emails and send a notification when a transaction exceeds a configurable amount (HINT: look into Javamail API)

Good luck :)
