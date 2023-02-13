import pygame
import time
from .load_and_save_data import save_user
import os.path
from .config import text_color


"""
    Funkcja game odpowiada za rozgrywkę, trwa dopóki gracz nie ukończy
    wszystkich poziomów lub nie wyjdzie z programu
"""


def play(user, Levels):
    while user.level() < len(Levels):
        level = Levels[user.level()]
        window = pygame.display.get_surface()
        window_size = pygame.display.get_window_size()
        path = os.path.abspath('textures_and_game_data/background.gif')
        backgroung_img = path
        background = pygame.image.load(backgroung_img)
        background = pygame.transform.scale(background, window_size)
        level.scale_level(user.width_scale, user.hight_scale)
        timer = time.time()
        while True:
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    exit(0)
            clock = pygame.time.Clock()
            clock.tick(20)
            window.blit(background, (0, 0))
            points = level.play_level(window_size, timer)
            if points == 1:
                timer = time.time()
            if points > 1:
                user._points += points
                user._level += 1
                save_user(user)
                pygame.display.update()
                break
            pygame.display.update()
    load_final_screen(user)
    return user


"""
    Funkcja load_final_screen
    wyświetla się na sam koniec rozgrywki
    informuje gracza o sumie zdobytych punktów
"""


def load_final_screen(user):
    screen = pygame.display.get_surface()
    font = pygame.font.SysFont("dejavusans", 20)
    text = f"""You have passed all the levels Now, frogger is safe! Your score is {user.points()}!"""
    text_render = font.render(text, 1, text_color)
    screen.fill((0, 0, 0))
    screen.blit(text_render, (20, 200))
    pygame.display.update()
    time.sleep(5)
