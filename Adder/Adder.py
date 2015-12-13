'''
Created on Apr 22, 2012

Name: Alexis Mendez
Class: CS375 Section 1
'''
# Ask the user's name
print("Hello, what is your name?");
name = input();

# Welcome Message
print("Welcome to the adder, %s" % (name));

# Input the first number
print("Input any integer: ");
num1 = int(input());

# Input the second numer
print("What would you like to add to %s?" % (num1));
num2 = int(input());

# Addition calculation
num3 = num1 + num2;

# Print the result
print("%s + %s = %s" % (num1, num2, num3));

# Loop to allow the user to multiply as many times as they want, terminates when 'q' is entered
multChoice = 'n';
while (multChoice != 'q'):
    multChoice = input("Enter 'y' if you would like to multiply, or 'q' to quit: ");
    # The user entered 'y' to multiply
    if (multChoice == 'y') :
        # Input the multiplier
        num4 = int(input("Enter a multiplier: "));
        # Set the old num3 to num2
        num2 = num3;
        # Multiply num3 to by the multiplier
        num3 = num2 * num4;
        # Print the result
        print("%s x %s = %s" % (num2, num4, num3));

# User wants to quit, print 'Goodbye'
print("Goodbye!");