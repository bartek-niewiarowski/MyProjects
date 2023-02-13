import pygame
import time
from .objects import Car, Log, Alligator, Turtle
from .frog import Frog
from .config import const_division, checkpoint_shift,const_points, text_color, text_shift, text_size
pygame.init()


'''
Klasa Level reprezentuje pojedynczy poziom w grze,
atrybuty klasy Level to:
- frog, instancja klasy Frog, gracz którym się poruszamy
- roads, 3 intsancje klasy Road
- rivers, 3 intsancje klasy River
- checkpoints, wszystkie checkpointy w danym poziomie
- time, czas który gracz ma na przejście poziomu
'''


class Level:
    def __init__(self, frog, roads, rivers, checkpoints, time):
        self._frog = frog
        self._roads = roads
        self._rivers = rivers
        self._checkpoints = checkpoints
        self._time = time

    def frog(self):
        return self._frog

    def roads(self):
        return self._roads

    def rivers(self):
        return self._rivers

    def checkpoints(self):
        return self._checkpoints

    def time(self):
        return self._time

    '''
    Move_all_elements odpowiada za przemieszczenie wszystkich
    elementów na mapie
    '''

    def move_all_elements(self, window_size):
        for road in self.roads():
            road.move_cars(window_size[0])
        for river in self.rivers():
            river.move_objects(window_size[0])

    '''
    Gracz ma możliwość zmiany rozmiaru okna gry, funkcja scale_level
    odpowiada za wyskalowanie każdego elementu na mapie, oraz obrazów
    które reprezentują je na mapie
    '''

    def scale_level(self, width, hight):
        Car.scale_image(Car, width=width, hight=hight)
        Alligator.scale_image(Alligator, width=width, hight=hight)
        Log.scale_image(Log, width=width, hight=hight)
        Turtle.scale_image(Turtle, width=width, hight=hight)
        Frog.scale_image(Frog, width=width, hight=hight)
        self._frog.scale_frog(width, hight)
        for road in self.roads():
            road.scale_road(width, hight)
        for river in self.rivers():
            river.scale_river(width, hight)
        for checkpoint in self.checkpoints():
            checkpoint.set_x(checkpoint.x() * width)

    '''
    Funkcja check_colisions_with_cars sprawdza czy występuje kolizja
    miedzy froggerem a jakimkolwiek samochodem poruszającym się po 3 drogach.
    Gdy występuje kolizja funkcja zwraca wyniki funkcji forg.die(),
    gdy frogger jest bezpieczny zwracany jest fałsz.
    '''

    def check_colisions_with_cars(self, window_size):
        width = window_size[0]
        hight = window_size[1]
        for road in self.roads():
            if self.frog().is_overlaping(0, width, road.y(), hight / const_division):
                if road.check_safety_on_the_road(self.frog(), hight):
                    return self.frog().die(window_size)
        return False

    '''
    Funkcja check_safety_in_river sprawdza czy frogger będąc w wodzie
    znajduje się na jednym z obiektów bezpiecznych (log lub turtle)
    gdy frogger jest bezpieczny wywyoływana jest funkcja move_with_object,
    w przeciwnym przyoadku wywoływana jest funkcja frog.die()
    '''

    def check_safety_in_river(self, window_size):
        width = window_size[0]
        hight = window_size[1]
        for river in self.rivers():
            if self.frog().is_overlaping(0, width, river.y(), hight / const_division):
                safe_object = river.check_safety_in_river(self.frog(), hight)
                if safe_object is None:
                    return self.frog().die(window_size)
                else:
                    self.frog().move_with_object(safe_object)
                    return False
        return False

    '''
    Funkcja check_win sprawdza czy frogger znajduje sie na
    jednym z checkpointów, kiedy znajduje sie, funkcja zwraca punkty
    uzyskane przez żabę w czasie rozgrywki.
    '''

    def check_win(self, window_size, timer):
        for checkpoint in self.checkpoints():
            x = checkpoint.x()
            y = checkpoint.lenght()
            if self.frog().is_overlaping(x, y, 0, window_size[1] / const_division):
                remaining_time = self.time() - round(time.time() - timer, 0)
                self.frog().add_points(remaining_time * const_points)
                self.frog().set_x(checkpoint.x() + checkpoint_shift)
                self.frog().set_y(checkpoint_shift)
                return self.frog().points()
        return 0

    '''
    Funkcja check_loose sprawdza czy gracz nie przekroczył dozwolonego czasu,
    kiedy czas zostanie przekroczony postęp poziomu jest resetowany.
    '''

    def check_loose(self, timer, window_size):
        if self.time() <= time.time() - timer:
            self.frog().set_lives(1)
            self.frog().die(window_size)
            return True
        return False

    '''
    Funkcja print_subtitles odpowiada za wyświetlanie napisów na
    ekranie, napis składa się z żyć oraz punktów froggera, oraz czas pozostały
    na rozegranie poziomu.
    '''

    def print_subtitles(self, window_size, timer):
        screen = pygame.display.get_surface()
        font = pygame.font.SysFont("dejavusans", text_size)
        time_to_end = self.time() - round(time.time() - timer, 0)
        points = self.frog().points()
        lives = self.frog().lives()
        text = f"Points: {points}, Lives: {lives}, Time: {time_to_end}"
        text_render = font.render(text, 1, text_color)
        screen.blit(text_render, (0, window_size[1] - text_shift))

    '''
    Funkcja draw_all_elements odpowiada za nanoszenie na ekran
    wszystkich elementów na ekranie gry.
    '''

    def draw_all_elements(self):
        screen = pygame.display.get_surface()
        for road in self.roads():
            road.draw_road(screen)
        for river in self.rivers():
            river.draw_river(screen)
        self.frog().draw_frog(screen)

    def play_level(self, window_size, timer):
        self.frog().move(window_size[0], window_size[1])
        if self.check_safety_in_river(window_size):
            return 1
        if self.check_colisions_with_cars(window_size):
            return 1
        self.move_all_elements(window_size)
        result = self.check_win(window_size, timer)
        if result > 0:
            self.print_subtitles(window_size, timer)
            self.draw_all_elements()
            pygame.display.update()
            time.sleep(2)
            return result
        if self.check_loose(timer, window_size):
            return 1
        self.draw_all_elements()
        self.print_subtitles(window_size, timer)
        return 0
