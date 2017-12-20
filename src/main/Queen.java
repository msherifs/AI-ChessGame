package main;

public class Queen extends Piece {

    public Queen(int x, int y, String name, String color) {
        super(x, y, name , color, 975, 1);
    }

    public boolean checkMove(int x,int y, Board b) {

        if(b.getCell(x,y).getColor().equals(this.getColor()))
            return false;

        int old_x =  super.getX();
        int old_y =  super.getY();
        int new_x =  x;
        int new_y =  y;

        if (b.hasPiece(new_x, new_y)) {
            if (b.getCell(new_x, new_y).getColor() == super.getColor()) {
                return false;
            }
        }
        //the queen moves in any place just check no pieces in the direction choosen

        if ((old_x == new_x) && (new_x >= 0 && new_x <= 7) && (new_y >= 0 && new_y <= 7)) {
            if (new_y > old_y) {
                for (int i = 1; i < new_y - old_y; i++) {
                    if (b.hasPiece(old_x, old_y + i)) {
                        return false;
                    }
                }
                return true;
            }
            if (new_y < old_y) {
                for (int i = 1; i < old_y - new_y; i++) {
                    if (b.hasPiece(old_x, new_y + i)) {
                        return false;
                    }
                }
                return true;
            }
            if (new_y == old_y) {
                return true;
            }
            return true;
        }
        if ((old_y == new_y) && (new_x >= 0 && new_x <= 7) && (new_y >= 0 && new_y <= 7)) {
            if (new_x > old_x) {
                for (int i = 1; i < new_x - old_x; i++) {
                    if (b.hasPiece(old_x + i, old_y)) {
                        return false;
                    }
                }
                return true;
            }

            if (new_x < old_x) {
                for (int i = 1; i < old_x - new_x; i++) {
                    if (b.hasPiece(new_x + i, old_y)) {
                        return false;
                    }
                }
                return true;
            }
            if (new_x == old_x) {
                return true;
            }
        }
        if (Math.abs(new_x - old_x) == Math.abs(new_y - old_y) && (new_x >= 0 && new_x <= 7) && (new_y >= 0 && new_y <= 7)) {
            if (new_x - old_x > 0 && new_y - old_y > 0) {
                for (int i = 1; i < new_x - old_x; i++) {
                    if (b.hasPiece(old_x + i, old_y + i)) {
                        return false;
                    }
                }
                return true;
            }
            if (new_x - old_x < 0 && new_y - old_y < 0) {
                for (int i = 1; i < old_x - new_x; i++) {
                    if (b.hasPiece(new_x + i, new_y + i)) {
                        return false;
                    }
                }
                return true;
            }
            if (((new_x - old_x) > 0) && ((new_y - old_y) < 0)) {
                for (int i = 1; i < new_x - old_x; i++) {
                    if (b.hasPiece(old_x + i, old_y - i)) {
                        return false;
                    }
                }
                return true;
            }
            if (new_x - old_x < 0 && new_y - old_y > 0) {
                for (int i = 1; i < Math.abs(new_x - old_x); i++) {
                    if (b.hasPiece(old_x - i, old_y + i)) {
                        return false;
                    }
                }
                return true;
            }
            return true;
        }
        return false;
    }

    public String getType() {
        return "Queen";
    }

    public String getUni() {
        if (this.getColor().equals("w")) {
            return "♕";
        } else return "♛";
    }

    public void calculateAttackAndDefense(Board b) {
        attackValue = 0;
        defenceValue = 0;

        for (int i = x + 1; i < 8; i++) {
            if (b.hasPiece(i, y)) {
                Piece piece = b.getCell(i, y);
                if (piece.getColor().equals(this.getColor())) { //if this piece is an ally
                    this.defenceValue += this.actionValue - piece.getActionValue();
                } else { //if this piece is a foe
                    this.attackValue += piece.actionValue - this.actionValue;
                }
                break;
            }
        }

        for (int i = x - 1; i >= 0; i--) {
            if (b.hasPiece(i, y)) {
                Piece piece = b.getCell(i, y);
                if (piece.getColor().equals(this.getColor())) { //if this piece is an ally
                    this.defenceValue += this.actionValue - piece.getActionValue();
                } else { //if this piece is a foe
                    this.attackValue += piece.actionValue - this.actionValue;
                }
                break;
            }
        }

        for (int i = y + 1; i < 8; i++) {
            if (b.hasPiece(x, i)) {
                Piece piece = b.getCell(x, i);
                if (piece.getColor().equals(this.getColor())) { //if this piece is an ally
                    this.defenceValue += this.actionValue - piece.getActionValue();
                } else { //if this piece is a foe
                    this.attackValue += piece.actionValue - this.actionValue;
                }
                break;
            }
        }

        for (int i = y - 1; i >= 0; i--) {
            if (b.hasPiece(x, i)) {
                Piece piece = b.getCell(x, i);
                if (piece.getColor().equals(this.getColor())) { //if this piece is an ally
                    this.defenceValue += this.actionValue - piece.getActionValue();
                } else { //if this piece is a foe
                    this.attackValue += piece.actionValue - this.actionValue;
                }
                break;
            }
        }

        for (int i = x + 1, j = y + 1; i < 8 && j < 8; i++, j++) {
            if (b.hasPiece(i, j)) {
                Piece piece = b.getCell(i, j);
                if (piece.getColor().equals(this.getColor())) { //if this piece is an ally
                    this.defenceValue += this.actionValue - piece.getActionValue();
                } else { //if this piece is a foe
                    this.attackValue += piece.actionValue - this.actionValue;
                }
                break;
            }
        }

        for (int i = x - 1, j = y + 1; i >= 0 && j < 8; i--, j++) {
            if (b.hasPiece(i, j)) {
                Piece piece = b.getCell(i, j);
                if (piece.getColor().equals(this.getColor())) { //if this piece is an ally
                    this.defenceValue += this.actionValue - piece.getActionValue();
                } else { //if this piece is a foe
                    this.attackValue += piece.actionValue - this.actionValue;
                }
                break;
            }
        }

        for (int i = x + 1, j = y - 1; i < 8 && j >= 0; i++, j--) {
            if (b.hasPiece(i, j)) {
                Piece piece = b.getCell(i, j);
                if (piece.getColor().equals(this.getColor())) { //if this piece is an ally
                    this.defenceValue += this.actionValue - piece.getActionValue();
                } else { //if this piece is a foe
                    this.attackValue += piece.actionValue - this.actionValue;
                }
                break;
            }
        }

        for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
            if (b.hasPiece(i, j)) {
                Piece piece = b.getCell(i, j);
                if (piece.getColor().equals(this.getColor())) { //if this piece is an ally
                    this.defenceValue += this.actionValue - piece.getActionValue();
                } else { //if this piece is a foe
                    this.attackValue += piece.actionValue - this.actionValue;
                }
                break;
            }
        }

    }

}


    
    
