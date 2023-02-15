//3.8.3
//https://www.w3schools.com/colors/colors_names.asp

import java.awt.Color;

/**
* Main method for testing the ColorChooser
*/
class Main{
  public static void main(String[] args) {

    /*
    Color pickedColor = ColorChooser.pickAColor();
    System.out.println(pickedColor);

    */

    
    //Picture beach2 = new Picture("beach.jpg");
    //beach2.explore();
    //Picture copy2 = testSetLow(beach2, Color.PINK);
    //copy2.explore();


    //Picture copy3 = revealPicture(copy2);
    //copy3.explore();

    

    
    Picture pic1 = new Picture("swan.jpg");
    Picture pic2 = new Picture("gorge.jpg");

    if(!canHide(pic1,pic2)){
      System.out.println("Not same.");
      System.exit(0);
    }

    //pic1.explore();
    Picture combined = hidePicture(pic1,pic2);
    combined.explore();

    Picture revCombined = revealPicture(combined);
    revCombined.explore();
    
    
    
  }

  /*
  Clear the lower (rightmost) two bits in a pixel.
  */
  
  public static void clearLow(Pixel p){

    p.setRed(p.getRed()/4*4);
    p.setGreen(p.getGreen()/4*4);
    p.setBlue(p.getBlue()/4*4);
    
  } //Steganography.clearLow(Pixel p);

  public static Picture testClearLow(Picture pic){

    Picture hidden = new Picture(pic);
    Pixel[][] pixels = hidden.getPixels2D();
    for(int r = 0; r < pixels.length; r++){
      for(int c = 0; c < pixels[0].length; c++){

        clearLow(pixels[r][c]);
        
      }
    }

    return hidden;

  }

  /*
  Set the lower 2 bits in a pixel to the highest 2 bits in c
  */
  public static void setLow(Pixel p, Color c){

    /*
    p.setRed((p.getRed()/4)*4);
    p.setRed(p.getRed() + c.getRed()/64);

    p.setGreen((p.getGreen()/4)*4);
    p.setGreen(p.getGreen() + c.getGreen()/64);

    p.setBlue((p.getBlue()/4)*4);
    p.setBlue(p.getBlue() + c.getBlue()/64);
    */

    clearLow(p);
    Color oldColor = p.getColor();
    int rAdd = c.getRed()/64;
    int gAdd = c.getGreen()/64;
    int bAdd = c.getBlue()/64;
    p.setColor(new Color(oldColor.getRed()+rAdd,
                         oldColor.getGreen()+gAdd,
                         oldColor.getBlue()+bAdd));
    //p.setColor(new Color(rAdd << 6, (gAdd << 6), (bAdd << 6)));
  }

  public static Picture testSetLow(Picture pic, Color color){

    Picture hidden = new Picture(pic);
    Pixel[][] pixels = hidden.getPixels2D();
    for(int r = 0; r < pixels.length; r++){
      for(int c = 0; c < pixels[0].length; c++){

        //System.out.println(Integer.toBinaryString(pixels[r][c].getRed()));
        //System.out.println(Integer.toBinaryString(pixels[r][c].getGreen()));
        //System.out.println(Integer.toBinaryString(pixels[r][c].getBlue()));
        
        setLow(pixels[r][c], color);
        System.out.println("SetLow: " + Integer.toBinaryString(pixels[r][c].getRed()));
        //System.out.println();
        
      }
    }

    return hidden;
  }

  /*
  Sets the highest two bits of each pixel's colors to the lower two bits of each pixel's colors.
  */
  public static Picture revealPicture(Picture hidden){
    Picture copy = new Picture(hidden);
    Pixel[][] pixels = copy.getPixels2D();
    
    System.out.println(pixels[230][217].getRed());
    Pixel[][] source = hidden.getPixels2D();
    for(int r = 0; r < pixels.length; r++){
      for(int c = 0; c < pixels[0].length; c++){
        Color col = source[r][c].getColor();
        Pixel p = pixels[r][c];
        //System.out.println(Integer.toBinaryString(col.getRed()));

        //int ans2 = (c2/64) * (64);
        
        //p.setRed((col.getRed()/64) * 64);
        //p.setGreen((col.getGreen()/64) * 64);
        //p.setBlue((col.getBlue()/64) * 64);

        
        p.setRed((col.getRed()%4) * 64);
        p.setGreen((col.getGreen()%4) * 64);
        p.setBlue((col.getBlue()%4) * 64);
        
        //System.out.println(Integer.toBinaryString(p.getRed()));

        //pixels[r][c].setRed((pixels[r][c].getRed()-Integer.valueOf((Double.valueOf(pixels[r][c].getRed()/4)*3))) * 64);
        //pixels[r][c].setGreen((pixels[r][c].getGreen()-Integer.valueOf((Double.valueOf(pixels[r][c].getGreen()/4)*3))) * 64);
        //pixels[r][c].setBlue((pixels[r][c].getBlue()-Integer.valueOf((Double.valueOf(pixels[r][c].getBlue()/4)*3))) * 64);
        
      }
    }
    return copy;
  }

 
  /*
  Determines whether secret can be hidden in source, which is
  true if source and secret are the same dimension.
  @param source is not null
  @param is not null
  @return true if secret can be hidden in source, false otherwise.
  */
  public static boolean canHide(Picture source, Picture secret){
    if(source.getHeight() >= secret.getHeight() && source.getWidth() >= secret.getWidth()){
      return true;
    }
    return false;
  }
  
  /*
  Determines whether secret can be hidden in source, which is
  true if source and secret are the same dimension.
  @param source is not null
  @param is not null
  @return combined Picture with secret hidden in source
  precondition: source is same width and height as secret
  */

  
  public static Picture hidePicture(Picture source, Picture secret){

    Picture sourcePic = new Picture(source);

    Pixel[][] sourceP = sourcePic.getPixels2D();
    Pixel[][] secretP = secret.getPixels2D();
    for(int r = 0; r < sourceP.length; r++){
      for(int c = 0; c < sourceP[0].length; c++){
        //Color col = secretP[r][c].getColor();

        // combine
        //int ans = ((c1/4)*4) + (c2/64);

        //sourceP[r][c].setRed((sourceP[r][c].getRed()/4*4) + (secretP[r][c].getRed()/64));
        //sourceP[r][c].setGreen((sourceP[r][c].getGreen()/4*4) + (secretP[r][c].getGreen()/64));
        //sourceP[r][c].setBlue((sourceP[r][c].getBlue()/4*4) + (secretP[r][c].getBlue()/64));
        setLow(sourceP[r][c],secretP[r][c].getColor());

        //System.out.println(Integer.toBinaryString(sourceP[r][c].getColor().getRed()));

        // reveal
        //int ans2 = (c2/64) * (64);
        
        //sourceP[r][c].setRed((secretP[r][c].getRed()/64) * 64);
        //sourceP[r][c].setGreen((secretP[r][c].getGreen()/64) * 64);
        //sourceP[r][c].setBlue((secretP[r][c].getBlue()/64) * 64);
        
        
      }
    }
    
    return sourcePic;
  }
  

  /*
  public static Picture hidePicture(Picture source, Picture secret){

    
    
  }
  */
  
}