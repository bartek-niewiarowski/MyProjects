import json
from .user import User
from .config import path

'''
Funkcje load_user oraz save_user odpowiadaja za wczytanie oraz zapis
postepów użytkownika, dane użytkownika są przechowywane w pliku user.json
'''


def load_user():
    with open(path, 'r') as file_handle:
        user_dict = json.load(file_handle)
        name = user_dict['name']
        width = user_dict['width']
        hight = user_dict['hight']
        level = user_dict['level']
        points = user_dict['points']
        user = User(name, width, hight, level, points)
    return user


def save_user(user):
    path = 'textures_and_game_data/user.json'
    with open(path, 'w') as file_handle:
        data = {
            'name': user.name(),
            'width': user.window_width(),
            'hight': user.window_hight(),
            'level': user.level(),
            'points': user.points()
        }
        json.dump(data, file_handle)
