package ru.netology.manager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Player;
import ru.netology.exeption.NotRegisteredException;

import java.util.ArrayList;
import java.util.Collection;

public class GameTest {
    Player player1 = new Player(1, "Anya", 250);
    Player player2 = new Player(2, "Lena", 380);
    Player player3 = new Player(3, "Anton", 170);
    Player player4 = new Player(4, "Maksim", 200);
    Player player5 = new Player(5, "Egor", 180);
    Player player6 = new Player(6, "Vadim", 200);

    @Test
    public void roundWinnerPlayer1() {
        Game game = new Game();
        Collection<Player> players = new ArrayList<>();

        game.register(player1);
        game.register(player2);
        game.register(player3);
        game.register(player4);
        game.register(player5);
        game.register(player6);

        game.registerAll(players);
        game.findAll();
        game.findByName("Lena");
        game.round("Lena", "Anton");

        int actual = game.round("Lena", "Anton");
        int expected = 1;

        assertEquals(expected, actual);

    }

    @Test
    public void roundWinnerPlayer2() {
        Game game = new Game();
        Collection<Player> players = new ArrayList<>();

        game.register(player1);
        game.register(player2);
        game.register(player3);
        game.register(player4);
        game.register(player5);
        game.register(player6);

        game.registerAll(players);
        game.findAll();
        game.findByName("Anya");
        game.round("Egor", "Anya");

        int actual = game.round("Egor", "Anya");
        int expected = 2;

        assertEquals(expected, actual);

    }


    @Test
    public void roundDraw() {
        Game game = new Game();

        game.register(player1);
        game.register(player2);
        game.register(player3);
        game.register(player4);
        game.register(player5);
        game.register(player6);

        game.findAll();
        game.findByName("Vadim");

        int actual = game.round("Vadim", "Maksim");
        int expected = 0;


        assertEquals(expected, actual);
    }

    @Test
    public void exceptionPlayer1() {
        Game game = new Game();

        game.findAll();
        game.findByName("Lena");

        assertThrows(NotRegisteredException.class, () -> {
            game.round("Lena", "Vadim");
        });
    }

    @Test
    public void exceptionPlayer2() {
        Game game = new Game();

        game.register(player1);

        game.findAll();

        assertThrows(NotRegisteredException.class, () -> {
            game.round("Anya", "Vadim");
        });
    }


    @Test
    public void findByStrength() {
        Game game = new Game();
        game.register(player1);
        game.register(player2);
        game.register(player3);
        game.register(player4);
        game.register(player6);

        game.findAll();
        game.findByName("Egor");

        int actual = game.findByStrength("Anton");
        int expected = 170;
        assertEquals(expected, actual);

    }
}
