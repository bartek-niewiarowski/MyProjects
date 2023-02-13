#pragma once
#include <iostream>
#include <random>
#include <vector>
#include "Games.h"

#define emptySquare 0
#define N 9
#define rows  9
#define columns 9

#define solvable 18
#define hard 40
#define medium 65

// -------------------------------------------------- Ewa Miazga ----------------------------------------------------------

class Sudoku:public Games
{
private:

    int** board;
    std::pair<int, int> difficultyLevel;
    int guessNum[9] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    int gridPos[81];

public:
    explicit Sudoku(std::string difficultyLevelValue);

    ~Sudoku();

    int** getBoard();

    std::string toString(int** tab);

    std::pair<int, int> getDifficultyLevel();

    void fillBoard(int** tab);

    void assignDiffcultyLevel(std::string difficultyLevelValue);

    int calcDifficultyLevel(int** tab);

    void createEmptyBoard(int** tab);

    bool usedInRow(int row, int value, int** tab);

    bool usedInColumn(int column, int value, int** tab);

    bool usedInBox(int row, int column, int value, int** tab);

    bool checkRepetition(int row, int column, int value, int** tab);

    bool isEmpty(int& row, int& column, int** tab);

    void insert(int row, int column, int num);

    bool isSolved(int** tab, int& i, int& j); //isFullBoard

    void generateSolvedBoard();

    void generateStartBoard();

    void countSolutions(int& number, int row, int col);

    bool solve(int row, int col, int** tab); //isSolvable

    bool gameOver() override;

    char getValue(int column, int row) override;

    void play() override;

    void display() override;
};
