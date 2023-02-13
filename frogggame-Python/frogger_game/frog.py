import pygame
import time
import os.path
from .config import frog_step, frog_lenght, standard_height, std_win_width, std_win_height

pygame.init()


"""
Klasa Frog
reprezentuje główną postać, którą mamy bezpiecznie
przeprowadzić przez przeszkody
Atrybuty:
image - wczytana tekstura
lives - życia, szanse które nam pozotają
x - współrzędna x, nie może być większa niż szerokość okna
y - współrzędna y, nie może być większa niż wysokość okna
"""


class Frog:
    def __init__(self, lives, x, y):
        self._lives = lives
        self._x = x
        self._y = y
        self._lenght = frog_lenght
        self._step = frog_step
        self._points = 0

    def image(self):
        return self._image

    def lives(self):
        return self._lives

    def x(self):
        return self._x

    def y(self):
        return self._y

    def lenght(self):
        return self._lenght

    def points(self):
        return self._points

    def step(self):
        return self._step

    def set_x(self, new_x):
        self._x = new_x

    def set_y(self, new_y):
        self._y = new_y

    def set_lives(self, new_lives):
        self._lives = new_lives

    def add_points(self, points):
        self._points += points

    '''
    Funkcja move umożlwia pruszanie się postacią, ruch odbywa się za pomocą
    przycisków w,a,s,d.
    '''

    def move(self, width, hight):
        step = self._step
        keys = pygame.key.get_pressed()
        if keys[pygame.K_a]:
            if self.x() - step >= 0:
                self._x -= step
                return
        if keys[pygame.K_d]:
            if self.x() + step <= width - self.lenght():
                self._x += step
                return
        if keys[pygame.K_s]:
            if self.y() + step <= hight - self.lenght():
                self._y += step
                self.add_points(-10)
                return
        if keys[pygame.K_w]:
            if self.y() - step >= 0:
                self._y -= step
                self.add_points(10)
                return

    '''
    Funkcja move_with_object jest przeznaczona do ruchu kiedy
    to frogger jest w wodzie, jeśli frogger znajduje sie na jednym
    z dwóch bezpiecznych elementów na wodzie, (turtle oraz log).
    '''

    def move_with_object(self, object):
        self._x = object.x() + object.lenght() / 2

    def draw_frog(self, surface):
        surface.blit(self.image(), (self.x(), self.y()))

    '''
    Funkcja is_overlaping sprawdza czy obiekt
    Frog koliduje z przekazanym elementem.
    '''

    def is_overlaping(self, object_x, object_lenght, track_y, hight):
        f_rect = pygame.Rect(self.x(), self.y(), self._lenght, hight)
        c_rect = pygame.Rect(object_x, track_y, object_lenght, hight)
        if f_rect.colliderect(c_rect):
            return True
        return False

    '''
    Funkcja die zmniejsza ilość żyć, resetuje punkty oraz współrzędne na mapie,
    w granicznym przypadku gdy frog.lives()==1,
    funkcja resetuje level, i rozpozcyna dany poziom od początku
    '''

    def die(self, window_size):
        width = window_size[0]
        hight = window_size[1]
        self._points = 0
        if self.lives() > 1:
            self._lives -= 1
            self._x = width - standard_height * (width / std_win_width)
            self._y = hight - standard_height * (hight / std_win_height)
            return False
        else:
            pygame.display.update()
            time.sleep(1)
            self.set_lives(5)
            self._points = 0
            self._x = width - standard_height * (width / std_win_width)
            self._y = hight - standard_height * (hight / std_win_height)
            return True

    def scale_frog(self, width, hight):
        self._x = self._x * width
        self._lenght = self._lenght * width
        self._y = self._y * hight
        self._step = self._step * hight

    def load_image(cls):
        path = os.path.abspath("textures_and_game_data/frog.png")
        frogIMG = pygame.image.load(path)
        cls._image = frogIMG

    def scale_image(cls, width, hight):
        new_dimensions = (standard_height * width, standard_height * hight)
        cls._image = pygame.transform.scale(cls._image, new_dimensions)
