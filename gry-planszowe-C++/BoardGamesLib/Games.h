#pragma once
#include <iostream>
#include <string>

// -------------------------------------------------- Ewa Miazga ----------------------------------------------------------

class Games
{
public:
	Games() = default;
	~Games() = default;

	virtual void play() = 0;
	virtual void display() = 0;
	virtual bool gameOver() = 0;
	virtual char getValue(int column, int row) = 0;
};