********************************
* Double-Linked List
* CS 221
* 11/15/24
* Diego Dominguez
******************************** 

# OVERVIEW:
This java program has an implemented double linked list capable of storing data, It provides functionality to add, remove, and access elements, along with an iterator. Additionally, a tester file is included to validate its functionality by running various test scenarios

# INCLUDED FILES:
* IUDoubleLinkedList.java: Implements double-linked list.
* ListTester.java: Test cases for the lists.
* IndexedUnsortedList.java: Interface of methods for the lists.
* Node.java: Node class used by the linked list.
* README - this file

# COMPILING AND RUNNING:
1. Compile Testing class
    $ javac ListTester.java
2. Run the testing class
    $ java ListTester
The ListTester.java file will execute a series of test cases to verify the functionality of the double linked list implementations.

# PROGRAM DESIGN AND IMPORTANT CONCEPTS:
The program has three primary components:
1. IUDoubleLinkedList: This class implements the core functionality of a double linked list. It uses the Node class to store elements and manage links to both the next and previous nodes, enabling traversal in both directions. The key methods are adding, removing, and accessing elements at any position in the list.
2. Node: The Node class represents an individual element in the list and holds a reference to them, the nodes are key to managing the list.
3. ListTester: testing class that validates the functionality of IUDoubleLinkedList

# TESTING:
The ListTester class was used to validate the core functionality of the double linked list, it made sure that all methods were running as intended and all correct exceptions were thrown when needed to be. The testing strategy was to run the tester class after each method was being implemented, effectively covering any and all needed functionality. The program made sure bad inputs were handled by throwing exceptions, this made sure the program would not suddenly crash without explanations.

# DISCUSSION:
Throughout the development of this program, I struggled with the concepts of nodes in a linked list. I found the shift of thinking in terms of array-like indeces to nodes very challening and took some time to really figure out. This itself made it very challenging figuring out the iterators for the double linked list, making sure the tail and head references were always updated correctly and then using them to figure out other references made this program very hard for me. 

