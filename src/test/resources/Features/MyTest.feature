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
    And I select 'Basket / Checkout' in basket pop-up
    And I am redirected to a "https://www.bookdepository.com/basket"
    And Basket order summary is as following:
      | Delivery cost | FREE    |
      | Total         | 85,62 € |

    And I click 'Checkout' button on 'Your basket' page
    And I checkout as a new customer with email "test@user.com"
    And Checkout order summary is as following:
      | Sub-total | 85,62 €  |
      | Delivery  | FREE     |
      | VAT       | 0,00 €   |
      | Total     |  85,62 € |

    And I fill "Full name" with "John Smith" and select "Delivery country" as "Bahamas" in delivery address information
    And I fill delivery address information manually:
      | Address line 1   | Random address 1 |
      | Address line 2   | Random address 2 |
      | Town/City        | Kyiv             |
      | County/State     | Random State     |
      | Postcode/ZIP     | 123              |

    And I enter my card details
      | Card number           | 4111111111111111 |
      | EExpiry date (MM/YY)  | 09/23            |
      | CVV                   | 123              |
    And 'Payment' section is disabled for editing
    When I press 'Continue to payment' button on checkout
    And 'Delivery Address' and 'Billing Address' sections are disabled for editing
