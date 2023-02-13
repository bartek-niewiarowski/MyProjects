#pragma once
#include <iostream>
#include <SFML/Graphics.hpp>
#include "tile.h"
#include "Games.h"

class Board
{
public:
    Board(
        sf::Vector2f position,
        sf::Color frameColor,
        sf::Color backgroundColor,
        sf::Color displayColor,
        sf::Font& font,
        double size,
        int elemNum,
        sf::RenderWindow& window
    );
    ~Board();

    sf::RenderWindow& m_window;

    virtual std::pair<int, int> getIndex(sf::Vector2i);
    virtual Tile& getTile(std::pair<int, int> index);

    virtual void draw();
    virtual void update();
    virtual void display(Games& game);

protected:
    sf::Vector2f position;
    double size;
    Tile** board;
    std::pair<int, int> selected = std::make_pair(-1, -1);

private:
    int elemNum;
};
