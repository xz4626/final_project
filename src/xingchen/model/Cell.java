package xingchen.model;

public class Cell {

    //有牌true 无牌 false
    private boolean state=false;
    private Card card;

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
