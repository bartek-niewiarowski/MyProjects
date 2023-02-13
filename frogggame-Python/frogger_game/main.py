import pygame
import argparse
from .level_factory import levelFactory
from .load_and_save_data import save_user, load_user
from .run_application import play
from .user import User
from .config import std_win_width, std_win_height
"""
    Funkcja main
    można podać dwa argumenty
    restart: resetowanie rozgrywki, mozliwe podanie nazwy uzytkownika
    change window size: zmiana rozmiaru okna gry,
    konieczne 2 argumenty bedace liczbami
"""


def frogger_main(arguments):
    parser = argparse.ArgumentParser()
    parser.add_argument("--restart", nargs="?", const="Player1")
    parser.add_argument("--change_window_size", nargs=2, type=int)
    args = parser.parse_args(arguments[1:])
    user = load_user()
    Levels = levelFactory.get_level()
    if args.restart:
        user = User(args.restart, std_win_width, std_win_height)
        save_user(user)
    if args.change_window_size:
        data = args.change_window_size
        user.set_window_size(int(data[0]), int(data[1]))
    pygame.display.set_mode((user.window_width(), user.window_hight()))
    pygame.display.set_caption("Frog")
    new_user = play(user, Levels)
    save_user(new_user)
