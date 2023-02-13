#pragma once
#include "User.h"
#include "board.h"
#include "TicTacToe.h"
#include "Sudoku.h"
#include "Crossword.h"
#include "Menu.h"
#include "Database.h"
#include <SFML/Graphics.hpp>
#include <SFML/System.hpp>

class App
{
public:
	App(sf::Font& font);
	
	~App();
	
	//void RunApp(sf::RenderWindow& window);

	void RunStartMenu(sf::RenderWindow& window);

	void QuickGame(sf::RenderWindow& window);

	void LoadUser(sf::RenderWindow& window);

	void UserGame(sf::RenderWindow& window, user_account current_user);

	void PlaySudoku(sf::RenderWindow& window, user current_user);

	void PlayTTC(sf::RenderWindow& window, user current_user);

	void PlayCrosswords(sf::RenderWindow& window, user current_user);

	int get_sudoku_input(sf::RenderWindow& window);

	std::string get_crosswords_input(sf::RenderWindow& window);

	int move(sf::RenderWindow& window, user& current_user);

private:
	DecisionMenu start_window;

	LoginWindow load_window;

	DecisionMenu game_window;

	Menu sudoku_window;

	Menu ttc_window;

	Menu crosswords_window;

	Menu stats_window;

	database users;

	int result;

	sf::Window window;

	sf::Font& font;
};