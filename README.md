# code-test-engineer
Loan Summary API's

# Build the application using below maven command mvn clean install

# Command to run the application from command prompt: java -jar target/code-test-engineer.jar

# Test this application by calling following links in browser or Postman. http://localhost:8080/loanSummary?amount=50000&apr=19 http://localhost:8080/repaymentSchedule?amount=50000&apr=19

# Alternately you can also tests the functionality by running below test cases.

Integration tests: LoanControllerIntegrationTest.shouldReceiveLoanSummaryStatusBadRequestWhenInvalidLoanAmount LoanControllerIntegrationTest.shouldReceiveRepaymentScheduleStatusOKWhenZeroInterestAmount LoanControllerIntegrationTest.shouldReceiveLoanSummaryWithStatusOKWhenValidLoanAmountAndInterestAmount LoanControllerIntegrationTest.shouldReceiveRepaymentScheduleWithStatusOKWhenValidLoanAmountAndInterestAmount LoanControllerIntegrationTest.shouldReceiveRepaymentScheduleStatusBadRequestWhenInvalidLoanAmount LoanControllerIntegrationTest.shouldReceiveRepaymentScheduleStatusBadRequestWhenInvalidInterestAmount LoanControllerIntegrationTest.shouldReceiveLoanSummaryStatusBadRequestWhenInvalidInterestAmount

Unit tests: LoanControllerTest.shouldDelegateToLoanServiceToCalculateLoanSummary LoanControllerTest.shouldDelegateToRepaymentServiceToCalculateRepaymentSchedule LoanServiceTest.shouldThrowExceptionWhenZeroLoanAmount LoanServiceTest.shouldCalculateLoanSummaryForZeroPercentInterest LoanServiceTest.shouldThrowExceptionWhenNegativeLoanAmount LoanServiceTest.shouldCalculateLoanSummaryFor50000LoanAmountWith19PercentInterest LoanServiceTest.shouldThrowExceptionWhenNegativeInterest RepaymentServiceTest.shouldDelegateToLoanServiceWhenCalculateRepaymentSchedule RepaymentServiceTest.shouldCalculateRepaymentScheduleFor50000LoanAmountAnd19PercentInterest RepaymentServiceTest.shouldCalculateRepaymentScheduleForZeroPercentInterest

# Behavioral tests:

LoanSummary.feature

Feature: Loan Summary Scenario: Calculate the loan summary Given a Loan Service When I ask for the loan summary, Given loan of £50000 taken out over 52 weeks at an annual interest rate of 19% Then the weekly repayment is £1058 Then the total amount repaid is £55016 Then the total interest paid is £5016

Scenario: Calculate Repayment Schedule Given a Loan Repayment Service When I ask for the repayment breakdown, Given a loan of £50000 taken out over 52 weeks at an annual interest rate of 19% Then I am given a breakdown that includes the following values |InstallmentNumber|AmountDue|Principal|Interest| |1|50000|875|183| |2|49125|878|179| |10|42010|904|153| |26|27143|958|99| |52|1054|1054|4|