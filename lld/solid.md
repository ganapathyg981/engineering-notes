### What are SOLID principles?

- SOLID is a set of five design principles that help you write cleaner, more easily extendable, and testable code.
- The 5 key design principles are:
    - Single Responsibility
    - Open/Closed
    - Liskov Substitution
    - Interface Segregation
    - Dependency Inversion.

### Why should I learn these principles?

- If you want your code to be:
    - Extendable (easy to add new features)
    - Maintainable (simple to update and fix)
    - Testable (able to write tests easily)

### Can I write code without following these principles?

- Yes, you can.
- But without SOLID, your code may become rigid, difficult to extend, and harder to manage as it grows.

## S (Single Responsibility Principle)

A class should have only one reason to change. It should have a single well defined responsibility.

#### What happens if we don't follow SRP?

1. Finding where to make changes becomes difficult i.e. Maintainability is lost
2. We have to write repeated code
3. Need to do modification at multiple places when there are new changes

#### Thumb Rules to Remember

1. Add methods according to the Class name
2. Ask yourself - "Will this method be used by other classes too?". If so, then think about moving it to a separate
   class.

## O (Open Closed Principle)

A class or module should be open for extension but closed for modification. In simple language, when you want to add a
new feature think more about creating new classes and modules and less about modifying existing classes/modules.

#### What happens if we don't follow Open Closed Principle?

1. Adding new features becomes chaotic because when you create a new class, you end up having to make changes in
   multiple other places, which can be hard to manage.
2. Modifying existing classes can introduce bugs.

#### Thumb Rules to Remember

1. Avoid if-else statements where you’re checking specific types (like whether an object is a car or truck). Instead,
   use a base class with subclasses, each defining its own unique behavior, allowing each type to manage its own logic
   independently.

## L(Liskov Substitution Principle)

Any instance of a subclass should be substitutable for an instance of its base class. Simply put, the subclass should
behave in a way that is consistent with the the base class.

#### What happens if we don't follow Liskov Substitution Principle?

1. If we do not follow this principle and then create subclasses which are inconsistent with parent class -> that could
   lead to unexpected runtime issues, like errors or incorrect results.

#### Thumb Rules to Remember

1. Whenever you create a new class, make sure to implement all the methods from its parent/base class.
2. Whenever you create a new class, make sure to check its consistent with the base/parent class especially the return
   value.

## I (Interface Segregation Principle)

Instead of creating one large, all-purpose interface, break it into smaller, more specific interfaces so that each class
only has methods relevant to it. A class should only have to implement methods it actually uses.

#### What happens if we don't follow Interface Segregation Principle?

1. Classes may be forced to implement methods they don’t need, leading to irrelevant code or `NotImplementedError`
   placeholders. This then leads to unexpected runtime errors.
2. Adding new features or modifying functionality becomes harder, as you have to change large interfaces and all classes
   that implement them, increasing the risk of introducing bugs.

#### Thumb Rules to Remember

1. Don’t cram all functionalities into a single interface. Instead, split them into smaller interfaces. When you are in
   a state where you are having methods which have 'NotImplementedError' that's when you should think about breaking
   into smaller interfaces.
2. When different classes share some, but not all, behaviors, consider using composition with smaller interfaces instead
   of broad inheritance.

## D (Dependency Inversion Principle)

All components should depend on abstractions and not on concrete classes. This makes the code more flexible and
decoupled.

#### What happens if we don't follow Dependency Inversion Principle?

1. We will break Open Closed Principle i.e. we will need to modify existing classes methods. There is a high chance you
   might introduce bugs when modifying existing classes.

#### Thumb Rules to Remember

1. Always have abstract classes/interfaces as dependencies. Try not to have dependencies on concrete classes.
2. Always try to pass dependencies into classes when initializing.
