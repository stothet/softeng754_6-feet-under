Feature: Market Comprehension
A few keywords should be extracted from the associated documents of a category in order
to form a concise and informative label for the category.

	Scenario: A user has performed a search
	Given a User has a set of weighted keywords to search for
	When the User performs a search
	Then there will be a label for each category in the results
	And the label will be concise