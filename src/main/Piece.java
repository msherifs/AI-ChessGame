package main;


public abstract class Piece {
  private int x , y;
  private String name ; 
  private String color;
  private boolean  selected;
  
  public Piece(int x, int y, String name , String color  ){ 
			
      this.x = x;
      this.y = y;
      this.selected = false;
      this.name = name;
	  this.color=color;
	}

  public void setSelected(boolean selected){
      this.selected=selected;
  }
  
  public boolean isSelected(){ //return if piece selcted or not
	return selected;
	}

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }
  
  public int getX(){
      return x;
  }
  
  public int getY(){
      return y;
  }

 public abstract String getType();
 public abstract boolean checkMove(int x , int y, Board b);
  
  
	
    
}
