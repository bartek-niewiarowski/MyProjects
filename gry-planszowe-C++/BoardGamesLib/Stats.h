#pragma once
#include <iostream>
#include <utility>
#include <ctime>
#include <vector>

class stats
{
protected:
	std::string title;
	time_t total_time;
	int	points;
public:
	stats();
	stats(std::string title);
	stats(std::string title, time_t total_time, int points);
	~stats();
	std::string get_title() { return this->title; }
	time_t get_time() { return this->total_time; }
	void set_title(std::string title) { this->title = title; }
	void set_time(time_t time) { this->total_time = time; }
	int get_points() { return this->points; }
	void set_points(int points) { this->points = points; }
	void add_time(time_t time) { this->total_time += time; }
	void add_points(int points) { this->points += points; }
	friend std::ostream& operator<<(std::ostream& cout, stats& obj)
	{
		cout << obj.get_title() << ' ' << obj.get_points() << ' ' << obj.get_time();
		return cout;
	}
	std::string to_string();
};