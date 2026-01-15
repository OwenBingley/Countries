// Owen Bingley
// Date: january 10, 2026
// This class represents a country with its name, capital, primary language, and an image file.


public class Country
{
    // private instance variables
    private String name;
    private String capital;
    private String language;
    private String imageFile;

    // default constructor
    public Country() {
        name = "";
        capital = "";
        language = "";
        imageFile = "";
    }
    //precondition: parameters are valid strings
    // postcondition: initializes a Country object with the given details
    // constructor with arguments
    public Country(String name, String capital, String language, String imageFile) {
        this.name = name;
        this.capital = capital;
        this.language = language;
        this.imageFile = imageFile;
    }

    // getter methods
    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public String getLanguage() {
        return language;
    }

    public String getImageFile() {
        return imageFile;
    }
    //precondition: none
    //postcondition: returns a string representation of the country's details
    // toString method that prints the country's details
    public String toString() {
        return name + "'s capital is " + capital + " and its primary language is " + language;
    }
}