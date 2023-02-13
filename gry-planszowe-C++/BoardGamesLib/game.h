#pragma once
#include <iostream>
#include <SFML/Graphics.hpp>
#include "board.h"

class GameWindow
{
public:
    GameWindow() = default;
    virtual void draw(sf::RenderWindow& window) = 0;

protected:
    Board* gameBoard = nullptr;
    sf::RenderWindow window;
    std::pair<int, int> selected;
    double height;
    double width;
    sf::Font loadFont();
};

class SudokuWindow : public GameWindow
{
public:
    SudokuWindow() = default;
    SudokuWindow(double, double);
    ~SudokuWindow();

    void draw(sf::RenderWindow& window);
};
