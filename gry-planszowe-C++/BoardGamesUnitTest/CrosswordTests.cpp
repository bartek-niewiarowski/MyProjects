//#include "pch.h"
#include "CppUnitTest.h"
#include"..\BoardGamesLib\Crossword.h"

// -------------------------------------------------- Ewa Miazga ----------------------------------------------------------

using namespace Microsoft::VisualStudio::CppUnitTestFramework;
using namespace std;

namespace CrosswordTests
{
	TEST_CLASS(CrosswordTests)
	{
	public:
		//TEST_METHOD(createCrosswordBoard) // monkeypatch
		//{
		//	Crossword crossword;
		//	Assert::IsTrue(crossword);
		//}

		TEST_METHOD(isEmptyTrue)
		{
			Crossword crossword;
			int num = 0;
			Assert::IsTrue(crossword.isEmpty(num));
		}

		TEST_METHOD(isEmptyFalse)
		{
			Crossword crossword;
			int num = 0;
			crossword.insert(1, "ewa");
			Assert::IsFalse(crossword.isEmpty(num));
		}

		TEST_METHOD(addClue)
		{
			Crossword crossword;
			int howManyClues = crossword.getClues().size();
			crossword.addClue("PIES", "Najlepszy przyjaciel czlowieka");
			int howManyCluesAfterAdd = crossword.getClues().size();
			Assert::AreEqual(howManyCluesAfterAdd, howManyClues + 1);
		}

		TEST_METHOD(findLetterTrue)
		{
			Crossword crossword;
			char letter = 'P';
			string word = "PIES";
			string wordInfo = "Najlepszy przyjaciel czlowieka";
			std::pair<std::string, std::string> test_pair = make_pair(word, wordInfo);
			Assert::IsTrue(crossword.findLetter(letter, test_pair));
		}

		TEST_METHOD(findLetterFalse)
		{
			Crossword crossword;
			char letter = 'Y';
			string word = "PIES";
			string wordInfo = "Najlepszy przyjaciel czlowieka";
			std::pair<std::string, std::string> test_pair = make_pair(word, wordInfo);
			Assert::IsFalse(crossword.findLetter(letter, test_pair));
		}

		//TEST_METHOD(findMaxLetterPosTest) //monkey patch
		//{
		//	Crossword crossword;
		//	int pos = 10;
		//	Assert::AreEqual(crossword.findMaxLetterPos(), pos);
		//}

		TEST_METHOD(insertWord)
		{
			Crossword crossword;
			string clue = "BEZTRESCIWY";
			crossword.insert(1, clue);
			Assert::AreEqual(crossword.getCrosswordCluesUser()[0].second.first, clue);
		}

		TEST_METHOD(insertLetter)
		{
			Crossword crossword;
			string clue = "B";
			crossword.insert(1, clue);
			Assert::AreEqual(crossword.getCrosswordCluesUser()[0].second.first, clue);
		}

		TEST_METHOD(encryptClue)
		{
			Crossword crossword;
			char letter = 'Y';
			string word = "PIES";
			string wordAfterEncrypt = "_ _ _ _ ";
			Assert::AreEqual(crossword.encryptClue(word), wordAfterEncrypt);
		}

		TEST_METHOD(solveCrossword)
		{
			Crossword crossword;
			crossword.solveCrossword();
			Assert::IsTrue(crossword.isSolved());
			Assert::IsTrue(crossword.checkCorrectness());
		}

		TEST_METHOD(isSolvedCorrectTrue)
		{
			Crossword crossword;
			crossword.solveCrossword();
			Assert::IsTrue(crossword.isSolved());
		}

		TEST_METHOD(isSolvedIncorrectTrue)
		{
			Crossword crossword;
			crossword.solveCrossword();
			crossword.insert(1, "ewa");
			Assert::IsTrue(crossword.isSolved());
		}

		TEST_METHOD(isSolvedFalse)
		{
			Crossword crossword;
			crossword.solveCrossword();
			crossword.insert(1, "_");
			Assert::IsFalse(crossword.isSolved());
		}

		TEST_METHOD(checkCorectnessTrue)
		{
			Crossword crossword;
			crossword.solveCrossword();
			Assert::IsTrue(crossword.isSolved());
		}

		TEST_METHOD(checkCorectnessFalse)
		{
			Crossword crossword;
			crossword.solveCrossword();
			crossword.insert(1, "_");
			Assert::IsFalse(crossword.isSolved());
		}

	};
}