// User.cpp : Definiuje funkcje biblioteki statycznej.
//

#include <SFML/System.hpp>
#include <stdexcept>
#include <string>
#include <fstream>
#include "User.h"
#include "Stats.h"

// USER
user::user()
{
	this->x = 0;
	this->y = 0;
	this->points = 0;
}

user::~user()
{

}

void user::move(int x, int y)
{
	this->x += x;
	this->y += y;
}

std::vector<std::string> user::show_my_stats()
{
	std::vector<std::string> user_stats = { "You start a Quick Game, in this option show statistics is not possible" };
	return user_stats;
}

// USER ACCOUNT

user_account::user_account()
{
	x = 0;
	y = 0;
	points = 0;
	current_sesion = 0;
	name = "user_1";
	sudoku_stats = stats("Sudoku");
	ttc_stats = stats("TTC");
	crosswords_stats = stats("Crosswords");
}

user_account::~user_account()
{

}

user_account::user_account(std::string name)
{
	x = 0;
	y = 0;
	points = 0;
	current_sesion = 0;
	this->name = name;
	sudoku_stats = stats("Sudoku");
	ttc_stats = stats("TTC");
	crosswords_stats = stats("Crosswords");
}

user_account::user_account(std::string name, stats sudoku, stats ttc, stats crosswords)
{
	x = 0;
	y = 0;
	points = 0;
	current_sesion = 0;
	current_game = "";
	this->name = name;
	sudoku_stats = sudoku;
	ttc_stats = ttc;
	crosswords_stats = crosswords;
}

void user_account::start_game(std::string title)
{
	this->current_game = title;
	time(&current_sesion);
}

void user_account::stop_game()
{
	time_t stop, add_time;
	add_time = time(&stop) - this->get_current_sesion();
	if (current_game == "Sudoku")
	{
		sudoku_stats.add_points(this->get_points());
		sudoku_stats.add_time(add_time);
	}

	if (current_game == "TicTacToe")
	{
		ttc_stats.add_points(this->get_points());
		ttc_stats.add_time(add_time);
	}

	if (current_game == "Crosswords")
	{
		crosswords_stats.add_points(this->get_points());
		crosswords_stats.add_time(add_time);
	}
	current_sesion = 0;
	current_game = "";
	points = 0;
}

std::vector<std::string> user_account::show_my_stats()
{
	std::vector<std::string> user_stats;
	std::string s = "You can see your statisctics";

	user_stats.push_back(s);

	s = "Game Points Time";
	user_stats.push_back(s);

	user_stats.push_back(sudoku_stats.to_string());

	user_stats.push_back(ttc_stats.to_string());

	user_stats.push_back(crosswords_stats.to_string());

	return user_stats;
}

bool user_account::update_to_premium()
{
	if ((sudoku_stats.get_points() > 10 && ttc_stats.get_points() > 10) && crosswords_stats.get_points() > 10)
		return true;
	else
		return false;
}

// Premium user

premium_user::premium_user()
{
}

premium_user::premium_user(std::string name, stats sudoku, stats ttc, stats crosswords)
{
	x = 0;
	y = 0;
	points = 0;
	current_sesion = 0;
	current_game = "";
	this->name = name;
	sudoku_stats = sudoku;
	ttc_stats = ttc;
	crosswords_stats = crosswords;
}

premium_user::~premium_user()
{
}

premium_user premium_user::update_to_premium(user_account& user)
{
	premium_user updated(user.get_name(), user.get_sudoku_stats(), user.get_ttc_stats(), user.get_crosswords_stats());
	return updated;
}