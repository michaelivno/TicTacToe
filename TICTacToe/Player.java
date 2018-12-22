package TICTacToe;

import java.util.Objects;

public class Player {
    private String name;
    private int score;
    private Type type;

    public Player(String name, Type type) {
        this.name = name;
        this.type = type;
        this.score = 0;
    }

    public int win(){
        return this.score++;
    }

    public void resetScore(){
        this.score =0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return score == player.score &&
                Objects.equals(name, player.name) &&
                type == player.type;
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, score, type);
    }
}
