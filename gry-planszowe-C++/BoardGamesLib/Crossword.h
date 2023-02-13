#pragma once
#include <string>
#include <ostream>
#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
#include <random>
#include "Games.h"

#define emptySquare '_'

// -------------------------------------------------- Ewa Miazga ----------------------------------------------------------


class Crossword:public Games
{
private:
	std::pair<std::string, std::string> crosswordClue; //glowne haslo
	std::vector<std::pair<int, std::pair<std::string, std::string>>> crosswordClues; //hasla w krzyzowkce
	std::vector<std::pair<int, std::pair<std::string, std::string>>> crosswordCluesUser; // wyniki uzytkownika
	std::vector<std::pair<std::string, std::string>> clues;
    std::vector<std::vector<char>> board;
public:
	Crossword();

	~Crossword() = default;

	std::string getClue();

	std::string getClueInfo();

	void chooseClueRandomly();

	std::vector<std::pair<std::string, std::string>> getClues();

	std::vector<std::pair<int, std::pair<std::string, std::string>>> getCrosswordClues();

	std::vector<std::pair<int, std::pair<std::string, std::string>>> getCrosswordCluesUser();

	bool isEmpty(int& num);

	void addClue(std::string Clue, std::string ClueInfo);

	bool findLetter(char letter, std::pair<std::string, std::string> word);

	int findLetterPos(char letter, std::string word);

	int findMaxLetterPos();

	void chooseCrosswordClues();

	void insert(int num, std::string clueValue);

	std::string encryptClue(std::string clue);

	friend std::ostream& operator<<(std::ostream& COUT, Crossword& crossword);

	friend std::istream& operator>>(std::istream& CIN, Crossword& crossword);

	void display() override;

	void solveCrossword();

	bool isSolved();

	bool checkCorrectness();

    bool gameOver() override;

    char getValue(int column, int row) override;

	void play() override;
};

void readFromFile(std::string fileName, Crossword& crosswordTemp);
