from frogger_game.frog import Frog
from frogger_game.objects import Car, Log
import pygame
frogIMG = pygame.image.load('textures_and_game_data/frog.png')


def test_init_frog():
    frog = Frog(5, 200, 300)
    assert frog.lives() == 5
    assert frog.x() == 200
    assert frog.y() == 300
    assert frog.points() == 0
    assert frog.lenght() == 50


def add_points():
    frog = Frog(5, 100, 200)
    frog.add_points(20)
    assert frog.points() == 20


def add_points_move_down():
    frog = Frog(5, 100, 200)
    frog.add_points(20)
    frog.add_points(-10)
    assert frog.points() == 10


def test_move_frog_left(monkeypatch):
    frog = Frog(5, 200, 300)

    def abstract_key_pressed():
        keys = [False] * 300
        keys[pygame.K_a] = True
        return keys
    monkeypatch.setattr('pygame.key.get_pressed', abstract_key_pressed)
    frog.move(800, 450)
    assert frog.x() == 150


def test_move_frog_right(monkeypatch):
    frog = Frog(5, 200, 300)

    def abstract_key_pressed():
        keys = [False] * 300
        keys[pygame.K_d] = True
        return keys
    monkeypatch.setattr('pygame.key.get_pressed', abstract_key_pressed)
    frog.move(800, 450)
    assert frog.x() == 250


def test_move_frog_up(monkeypatch):
    frog = Frog(5, 200, 300)

    def abstract_key_pressed():
        keys = [False] * 300
        keys[pygame.K_w] = True
        return keys
    monkeypatch.setattr('pygame.key.get_pressed', abstract_key_pressed)
    frog.move(800, 450)
    assert frog.x() == 200
    assert frog.y() == 250
    assert frog.points() == 10


def test_move_frog_down(monkeypatch):
    frog = Frog(5, 200, 300)

    def abstract_key_pressed():
        keys = [False] * 300
        keys[pygame.K_s] = True
        return keys
    monkeypatch.setattr('pygame.key.get_pressed', abstract_key_pressed)
    frog.move(800, 450)
    assert frog.x() == 200
    assert frog.y() == 350


def test_move_impossible_down(monkeypatch):
    frog = Frog(5, 200, 400)

    def abstract_key_pressed():
        keys = [False] * 300
        keys[pygame.K_s] = True
        return keys
    monkeypatch.setattr('pygame.key.get_pressed', abstract_key_pressed)
    frog.move(800, 450)
    assert frog.x() == 200
    assert frog.y() == 400
    assert frog.points() == 0


def test_move_impossible_left(monkeypatch):
    frog = Frog(5, 0, 200)

    def abstract_key_pressed():
        keys = [False] * 300
        keys[pygame.K_a] = True
        return keys
    monkeypatch.setattr('pygame.key.get_pressed', abstract_key_pressed)
    frog.move(800, 450)
    assert frog.x() == 0
    assert frog.y() == 200
    assert frog.points() == 0


def test_move_impossible_right(monkeypatch):
    frog = Frog(5, 750, 200)

    def abstract_key_pressed():
        keys = [False] * 300
        keys[pygame.K_d] = True
        return keys
    monkeypatch.setattr('pygame.key.get_pressed', abstract_key_pressed)
    frog.move(800, 450)
    assert frog.x() == 750
    assert frog.y() == 200
    assert frog.points() == 0


def test_move_impossible_up(monkeypatch):
    frog = Frog(5, 100, 0)

    def abstract_key_pressed():
        keys = [False] * 300
        keys[pygame.K_w] = True
        return keys
    monkeypatch.setattr('pygame.key.get_pressed', abstract_key_pressed)
    frog.move(800, 450)
    assert frog.x() == 100
    assert frog.y() == 0
    assert frog.points() == 0


def test_move_with_object():
    frog = Frog(5, 100, 200)
    log = Log(5, 200)
    frog.move_with_object(log)
    assert frog.x() == 250


def test_die():
    frog = Frog(2, 100, 200)
    frog.die((800, 450))
    assert frog.lives() == 1
    assert frog.x() == 750
    assert frog.y() == 400

def test_scale():
    frog = Frog(5, 100, 200)
    frog.scale_frog(2, 2)
    assert frog.x() == 200
    assert frog.y() == 400
    assert frog.step() == 100
    assert frog.lenght() == 100


def test_is_overlaping_TRUE():
    frog = Frog(2, 100, 250)
    car = Car(10, 80)
    result = frog.is_overlaping(car.x(), car.lenght(), 250, 50)
    assert result is True


def test_is_overlaping_FALSE():
    frog = Frog(2, 100, 250)
    car = Car(10, 80)
    result = frog.is_overlaping(car.x(), car.lenght(), 100, 50)
    assert result is False
