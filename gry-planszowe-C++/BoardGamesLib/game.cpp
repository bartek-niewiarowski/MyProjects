//#include <SFML/Graphics.hpp>
//#include "game.h"
//
//sf::Font GameWindow::loadFont()
//{
//	sf::Font font;
//	if (!font.loadFromFile("../resources/fonts/Qarolina.ttf"))
//	{
//		std::cout << "error loading the file";
//		system("pause");
//	}
//	return font;
//}
//
//SudokuWindow::SudokuWindow(double height, double width)
//{
//	this->height = height;
//	this->width = width;
//	sf::Font font = loadFont();
//	gameBoard = new Board(
//		sf::Vector2f(0, 0),
//		sf::Color::Black,
//		sf::Color::White,
//		sf::Color::Black,
//		font,
//		std::max(height, width),
//		9
//	);
//}
//
//SudokuWindow::~SudokuWindow()
//{
//	delete gameBoard;
//}
//
//void SudokuWindow::draw(sf::RenderWindow& window)
//{
//	gameBoard->draw(window);
//}
