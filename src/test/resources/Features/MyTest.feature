Feature: Desktop Checkout for Guest User
  As a customer
  I want to be able proceed to checkout
  So that I can specify my delivery and payment details and place the order

  Scenario: Proceed to checkout, final review and place order as guest user
    Given I am an anonymous customer with clear cookies
    And I open the "https://www.bookdepository.com/"
    And I search for "Thinking in Java"
    And I am redirected to a "https://www.bookdepository.com/search?searchTerm=Thinking+in+Java&search=Find+book"
    And Search results contain the following products list
      | Thinking in Java       |
      | Thinking Java Part I   |
      | Core Java Professional |
    And I apply the following search filters
      | Price range  | 30 € +         |
      | Availability | In Stock (5)   |
      | Language     | English (17)   |
      | Format       | Paperback (22) |
    And Search results contain only the following products list
      | Thinking in Java                                                     |
      | Think Java                                                           |
      | Thinking Recursively - A 20th Anniversary Edition with Java (WSE)    |
      | Think Data Structures                                                |
    And I click 'Add to basket' button for product with name "Thinking in Java"
    And I select 'Basket Checkout' in basket pop-up
    And I am redirected to a "Basket page"
    And Basket order summary is as following:
      | Delivery cost | Total   |
      | FREE          | 85,29 € |
    And I click 'Checkout' button on 'Basket' page
    And I checkout as a new customer with email "test@user.com"
    And Checkout order summary is as following:
      | Sub-total | Delivery | VAT    | Total   |
      | 85,29 €   | FREE     | 0,00 € | 85,29 € |
    And I fill delivery address information manually:
      | Full name | Delivery country | Address line 1   | Address line 2   | Town/City | Country/State | Postcode |
      | John      | Ukraine          | Random address 1 | Random address 2 | Kyiv      | Random State  | 123      |
    And 'Payment' section is disabled for editing
    When I press 'Continue to payment' button on checkout
    And 'Delivery Address' and 'Billing Address' sections are disabled for editing
    And I enter my card details
      | Card Type    | Visa             |
      | Name On Card | RandomName       |
      | cardNumber   | 4111111111111111 |
      | Expiry Year  | 2022             |
      | Expiry Month | 03               |
      | Cvv          | 123              |