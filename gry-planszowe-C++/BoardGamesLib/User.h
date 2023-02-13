#pragma once
#include <iostream>
#include <utility>
#include <ctime>
#include <vector>
#include "Stats.h"

class user
{
protected:
	int x;
	int y;
	int points;

public:
	user();
	~user();
	int get_points() { return this->points; }
	void add_point() { this->points++; }
	int get_x() { return this->x; }
	int get_y() { return this->y; }
	void move(int x, int y);
	std::vector<std::string> show_my_stats();
	bool is_premium() { return false; }
};

class user_account
	:public user
{
protected:
	std::string name;
	time_t current_sesion;
	std::string current_game;
	stats sudoku_stats;
	stats ttc_stats;
	stats crosswords_stats;
public:
	user_account();
	user_account(std::string name);
	~user_account();
	user_account(std::string name, stats sudoku, stats ttc, stats crosswords);
	std::string get_name() { return this->name; }
	void set_name(std::string new_name) { this->name = new_name; }
	stats get_sudoku_stats() { return sudoku_stats; }
	void set_sudoku_stats(stats& new_stats) { this->sudoku_stats = new_stats; }
	stats get_ttc_stats() { return ttc_stats; }
	void set_ttc_stats(stats& new_stats) { this->ttc_stats = new_stats; }
	stats get_crosswords_stats() { return crosswords_stats; }
	void set_crosswords_stats(stats& new_stats) { this->crosswords_stats = new_stats; }
	time_t get_current_sesion() { return this->current_sesion; }
	void start_game(std::string title);
	void stop_game();
	std::vector<std::string> show_my_stats();
	bool update_to_premium();
};

class premium_user
	:public user_account
{
public:
	premium_user();
	premium_user(std::string name, stats sudoku, stats ttc, stats crosswords);
	~premium_user();

	bool is_premium() { return true; }
	premium_user update_to_premium(user_account& user);
};
