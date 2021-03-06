**Simple supermarket pricing solution**

Focuses on how to price a supermarket shopping basket and in particular on how to calculate more complex prices such as
- Three cans of beans for the price of two.
- Oranges for £1.99/kilo.
- Two cans of Coke, for £1.

The following receipt was used to capture the necessary data:

    Beans                0.50
    Beans                0.50
    Beans                0.50
    Coke                 0.70
    Coke                 0.70
    Oranges
    0.200 kg @  £1.99/kg 0.40
                        -----
    Sub-total            3.30

    Savings
    Beans 3 for 2       -0.50
    Coke 2 for £1       -0.40
                        -----
    Total savings       -0.90
    -------------------------
    Total to Pay         2.40
    
    
BDD is used with DDD.

No logging, web, or persistance layer added, focusing on the created classes and responsibilities. (SOLID principles)

| REST Resource | URI           | What it does|
  | ------------- | ------------- | ------------ |
  | Product      | POST: /basket/products  | Add products to the basket, Basket#addProducts(List<Product>) will be called
  | Discount      | POST: /basket/discounts      |Add discounts to the basket, Basket#addDiscounts(Set<Discount>) will be called
  | Pricing Summary | GET: /basket/pricingsummary     |Getting the pricing summary depending on the added products and discounts, Basket#createPricingSummary() will be called
  
  
  Removing products to be priced and discounts are not implemented.
  Product Id (eg.: barcode) should be used instead of product name as unique identifier.