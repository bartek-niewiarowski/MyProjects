#include "Stats.h"
#include <string>

stats::stats()
{
	points = 0;
	total_time = 0;
	title = "";
}

stats::stats(std::string title)
{
	this->title = title;
	this->points = 0;
	this->total_time = 0;
}

stats::stats(std::string title, time_t total_time, int points)
{
	this->title = title;
	this->points = points;
	this->total_time = total_time;
}

stats::~stats()
{
}

std::string stats::to_string()
{
	std::string str_rep = this->get_title();
	str_rep += "  ";
	str_rep += std::to_string(this->get_points());
	str_rep += " points";
	str_rep += "  ";
	str_rep += std::to_string(this->get_time()/3600);
	str_rep += " hours";
	return str_rep;
}