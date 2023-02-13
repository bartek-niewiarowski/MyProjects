#include <SFML/Graphics.hpp>
#include "tile.h"


Tile::Tile(
    sf::Vector2f position,
    sf::Color frameColor,
    sf::Color backgroundColor,
    sf::Color displayColor,
    sf::Font &font,
    double size,
    bool visibility,
    char value
)
{
    setPosition(position);
    setFillColor(backgroundColor);
    setSize(sf::Vector2f(size, size));
    setOutlineThickness(5.f * size * -0.01);
    setOutlineColor(frameColor);
    
    displayValue.setFont(font);
    displayValue.setString(std::string(1, value));
    displayValue.setCharacterSize(size*0.7);
    displayValue.setFillColor(displayColor);

    unsigned int x = (size - displayValue.getCharacterSize());
    unsigned int y = (size - displayValue.getCharacterSize()) / 3.0;
    displayValue.setPosition(position + sf::Vector2f(x, y));
    
    this->visibility = visibility;
    this->position = position;
    this->value = value;
}


void Tile::draw(sf::RenderWindow &window)
{
    window.draw(*this);
    window.draw(displayValue);
}

char Tile::getValue()
{
    return this->value;
}

void Tile::setValue(char character)
{
    value = character;
    displayValue.setString(std::string(1, value));
}

void Tile::setVisibility(bool visible)
{
    this->visibility = visible;
}

bool Tile::isVisible()
{
    return visibility;
}
