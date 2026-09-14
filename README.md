# Gilded Rose Refactoring Kata

This is my Java solution for the Gilded Rose Refactoring Kata.

The task was to take the existing code, make it easier to understand and change without breaking the current behaviour, and then add support for Conjured items based on the requirements.

I completed this task with incremental steps, with each stage committed separately.

## Approach

1. Added tests for the existing behaviour before changing the production code.
2. Simplified the main item loop and replaced repeated item names with constants.
3. Moved repeated checks and Quality updates into small helper methods.
4. Split the update logic by item type so the rules were easier to follow.
5. Added tests for Conjured items and then implemented the new behaviour.
6. Ran the full test suite after each step to make sure the existing behaviour still worked as expected.
7. Updated the README file.

The commit history follows the same progression.

## Tests

There are 18 tests in total.

1. Normal items: Quality changes before and after the sell-by date, including the zero limit.
2. Aged Brie: Quality increases over time and does not go above 50.
3. Backstage passes: covers the 10-day and 5-day rules, as well as what happens after the concert.
4. Sulfuras: confirms that `SellIn` and `Quality` stay unchanged.
5. Conjured items: covers the faster Quality reduction before and after the sell-by date, while still stopping at zero.

## Tools

I used Java, Maven, JUnit 5 and Cursor IDE.

I used Cursor's autocomplete and suggestions where useful, while working through the requirements, tests and refactoring step by step and reviewing the changes as I went.

## Running the tests

```bash
mvn test