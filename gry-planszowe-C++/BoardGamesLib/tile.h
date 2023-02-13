#pragma once
#include <iostream>
#include <SFML/Graphics.hpp>

class Tile : public sf::RectangleShape
{
public:
    Tile() = default;
    Tile(
        sf::Vector2f position,
        sf::Color frameColor,
        sf::Color backgroundColor,
        sf::Color displayColor,
        sf::Font& font,
        double size,
        bool isVisible,
        char value = 0
    );

    void draw(sf::RenderWindow& window);

    char getValue();
    void setValue(char);
    bool isVisible();
    void setVisibility(bool);

private:
    bool visibility;
    sf::Vector2f position;
    char value;
    sf::Text displayValue;
};
