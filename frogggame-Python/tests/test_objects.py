from frogger_game.objects import Moveable, Checkpoint, Car
import pygame


def test_init_car():
    object = Car(10, 200)
    assert object.speed() == 10
    assert object.x() == 200
    assert object.lenght() == 70


def test_init_moveable():
    object = Car(10, 300)
    assert object.x() == 300
    assert object.speed() == 10


def test_set_speed():
    object = Moveable(10, 200)
    object.set_speed(20)
    assert object.speed() == 20


def test_move():
    object = Moveable(10, 200)
    object.move(800)
    assert object.x() == 210


def test_move_edge_case():
    object = Moveable(10, 800)
    object.move(800)
    assert object.x() == 10


def test_scale_image():
    object = Car(10, 200)
    object.scale_object(2)
    assert object.speed() == 20
    assert object.x() == 400


def test_init_checkpoint():
    checkpoint = Checkpoint(100, 40)
    assert checkpoint.x() == 100
    assert checkpoint.lenght() == 40
