from .config import std_win_height, std_win_width

"""
Klasa user
Atrybuty:
name - nazwa użytkownika
window - informacaja o oknie gry w którym ma być odpalana
gra dla danego użytkownika
level - informacja o ukończonym poziomie użtkownika
"""


class User:
    def __init__(self, name, window_width=std_win_width,
                 window_hight=std_win_height, level=0, points=0):
        self._name = name
        self._window_width = window_width
        self._window_hight = window_hight
        self._level = level
        self._points = points
        self.width_scale = window_width / std_win_width
        self.hight_scale = window_hight / std_win_height

    def name(self):
        return self._name

    def window_hight(self):
        return self._window_hight

    def window_width(self):
        return self._window_width

    def set_window_size(self, width, hight):
        self._window_width = width
        self._window_hight = hight
        self.width_scale = self._window_width / std_win_width
        self.hight_scale = self._window_hight / std_win_height

    def level(self):
        return self._level

    def points(self):
        return self._points
