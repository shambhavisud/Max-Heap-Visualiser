package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import javafx.scene.text.Font;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.*;
import javafx.stage.Screen;

import javafx.stage.Stage;

class HeapButton extends Button{
    
    private final String FONT_PATH = "src/application/resources/Drifttype.ttf";
    private final String BUTTON_PRESSED_STYLE = "-fx-background-repeat: round; -fx-background-color: transparent; -fx-background-image: url('/model/heapResources/button_pressed.png'); -fx-background-size: contain;";
   
    private double width,height;
    private String text;
    
    public HeapButton(String text,double width,double height){
        this.text = text;
        this.width = width;
        this.height = height;
        setText(text);
        setHeapButtonFont();
        setPrefHeight(height);
        setPrefWidth(width);
        
    }
    
    private void setHeapButtonFont(){
    	
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), Integer.min( (int)width/text.length(),(int)height/2 ) ));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", width/text.length()));
            System.out.print("Not working");
        }
    }
    


}




public class MaxHeap extends Application { 
	private final double SceneWidth = Screen.getPrimary().getVisualBounds().getWidth();
  
    private final double HeaderHeight = 80;
	
	
  private Heap<Integer> Heap = new Heap<Integer>();
  private TextField tfKey = new TextField();
  
  private HeapView heapView = new HeapView(); 
  private HBox heapFooter;
  
 
  private HeapButton btInsert,btDelete,btFind,btPrint;

  
  
 
  
  @Override 
  public void start(Stage primaryStage) { 
	  Image image = new Image("application/resources/header.png",SceneWidth,HeaderHeight,false,true);
      BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.ROUND, BackgroundRepeat.ROUND, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
      
	  heapFooter = new HBox();
	  heapFooter.setPrefHeight(30);
	  heapFooter.setStyle("-fx-background-repeat: round;-fx-background-color: seagreen;");
	  heapFooter.setPadding(new Insets(5,5,5,5));
	   
	  btInsert = new HeapButton("INSERT", SceneWidth/15, HeaderHeight*2/3);
	  btDelete = new HeapButton("DELETE", SceneWidth/15, HeaderHeight*2/3);
	  btFind = new HeapButton("FIND", SceneWidth/15, HeaderHeight*2/3);
	  btPrint = new HeapButton("PRINT", SceneWidth/15, HeaderHeight*2/3);
	  

	  
	 

	  
	  

	  
	  
	  
	    btInsert.setStyle("-fx-background-color: GOLD; ");
	    btDelete.setStyle("-fx-background-color: GOLD; ");
	    btFind.setStyle("-fx-background-color: GOLD; ");
	    btPrint.setStyle("-fx-background-color: GOLD; ");
	  Font f = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 30);
	  
	  tfKey.setPromptText("Number to be inserted");
	  tfKey.setPrefHeight(HeaderHeight*2/3);
	  tfKey.setPrefWidth(SceneWidth/20);
	  tfKey.setLayoutY(HeaderHeight/5);
	  tfKey.setLayoutX(SceneWidth/24);
	  
    HBox hBox = new HBox();
    Pane headingpane = new Pane();

    tfKey.setPrefColumnCount(2);
    
    
    
    
    
    Label numnodes=new Label();
    Label height=new Label();
    Label search=new Label();
    Label printlabel=new Label();
    
    
    numnodes.setFont(f);
    numnodes.setTextFill(Color.ORANGE);
    
    search.setFont(f);
    search.setTextFill(Color.GOLD);
    printlabel.setFont(f);
    height.setFont(f);
    height.setTextFill(Color.GOLD);
    
    
   
    
    HBox hBox1 = new HBox(5);
    hBox1.getChildren().addAll(tfKey, btInsert,btFind,search,btDelete,btPrint,printlabel);
    hBox1.setAlignment(Pos.CENTER);
    
    heapFooter.getChildren().addAll(numnodes,height);
    
    BorderPane pane = new BorderPane();
    pane.setCenter(heapView);
    pane.setTop(hBox1);
    pane.setBottom(heapFooter);
    pane.setBackground(new Background(backgroundImage));
    pane.setPadding(new Insets(5,5,5,5));
    
    
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 340, 180);
    primaryStage.setTitle("Max Heap"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    btInsert.setOnAction(e -> {
      int key = Integer.parseInt(tfKey.getText());
      Heap.insert(key); // Insert a new key
      heapView.repaint(); // Redisplay the tree
      numnodes.setText(" Nodes: "+Heap.getSize()+"  ");
      height.setText(" Height: "+Heap.height(Heap.getSize()));
      search.setText(key+" inserted");
    });

    btDelete.setOnAction(e -> {
    	Heap.remove(); // Delete t
      heapView.repaint(); // Redisplay the tree
      numnodes.setText(" Nodes: "+Heap.getSize());
      height.setText(" Height: "+Heap.height(Heap.getSize()));
      search.setText(" root deleted ");
    });
    
    
    
    btFind.setOnAction(e->{
    	
    	int key = Integer.parseInt(tfKey.getText());
    	if(Heap.search(key)!=-1 && Heap.search(key)!=0) {
    		search.setText("**KEY FOUND** child of "+Heap.list.get((Heap.search(key)-1)/2));
    		 
    		       
    		    
    		
    		
    	}else if(Heap.search(key)!=-1 && Heap.search(key)==0) {
    		search.setText("**KEY FOUND** root node");
    		
    		
    	}else
    		search.setText("**KEY NOT FOUND**");
    		
    
    });
//    btPrint.setOnAction(e->{
//    	
//    	String s =Heap.preorder(0);
//    	printlabel.setText(s);
//    	//printlabel.setText();
//    });
    
   
    
    
    
  }
    
  class Heap<E extends Comparable<E>> {
	  int flag=0;
	  String r ="";
	  String preOrderResult = "";
    java.util.ArrayList<E> list = new java.util.ArrayList<E>();
    java.util.ArrayList<Integer> intlist = new java.util.ArrayList<Integer>();

    /** Create a default heap */
    public Heap() {
    }

    /** Create a heap from an array of objects */
    public Heap(E[] objects) {
      for (int i = 0; i < objects.length; i++)
    	  insert(objects[i]);
    }
    


    /**
     * This inner class is static, because it does not access any instance
     * members defined in its outer class
     */
    

//    public String preorder(int n) {
//    	
//    	
//    	if(Heap.list.get(n)==null) {
//    		return r;
//    	}
//    	
//    	
//    	
//    	int currentIndex=-1;
//    	 while(currentIndex<Heap.list.size()) {
//    		 currentIndex=n;
//    		 r=r.concat((String)String.valueOf(Heap.list.get(currentIndex))+" "); 
//             int parentIndex=currentIndex;
//             currentIndex=(currentIndex*2)+1;
//             if(currentIndex>Heap.list.size() && flag!=0) {
//            	 flag=0;
//            	 preorder(parentIndex+1);
//    			 
//    		 }else if(currentIndex>Heap.list.size() && flag!=0) {
//    			 flag++;
//    			 preorder(parentIndex/2);
//    			 
//    		 }
//             flag++;
//    		 preorder(currentIndex);
//    		 
//    		 
//    		 //preorder((currentIndex)+1);
//    		 
//    		 
//    	      }
//    	
//    	
//    	return r;
//    }
//    
    

    
    public int height(int N) 
    { 
        return (int)Math.ceil(Math.log(N +  
                    1) / Math.log(2)) - 1; 
    } 
    
    public int search(int n) {
    	int r=-1;
    	for(int i=0;i<list.size();i++) {
    		int k=(int)list.get(i);
    		if(n==k){
    			r=i;
//    			heap.list.get(i);
//    			 circle.setFill(Color.LIME);
   			
    		}
    	}
		return r;
    }
    

    
    public String print() {
    	
    	String s ="";
    	for(int i=0;i<list.size();i++) {
    		s=s.concat((String)String.valueOf(Heap.list.get(i))+" ");	
    	}
		return s;
    }

    /** Add a new object into the heap */
    public void insert(E newObject) {
      list.add(newObject); // Append to the heap
      //intlist.add(Integer.parseInt((String) newObject));
      int currI= list.size() - 1; // The index of the last node

      while (currI > 0) {
        int pi = (currI - 1) / 2;
        // Swap if the current object is greater than its parent
        if (list.get(currI).compareTo(
            list.get(pi)) > 0) {
          E temp = list.get(currI);
          list.set(currI, list.get(pi));
          list.set(pi, temp);
        }
        else
          break; // the tree is a heap now

        currI = pi;
      }
    }

    /** Remove the root from the heap */
    public E remove() {
      if (list.size() == 0) return null;

      E removedObject = list.get(0);
      list.set(0, list.get(list.size() - 1));
      list.remove(list.size() - 1);

      int currI = 0;
      while (currI < list.size()) {
        int leftChildIndex = 2 * currI + 1;
        int rightChildIndex = 2 * currI + 2;

        // Find the maximum between two children
        if (leftChildIndex >= list.size()) break; // The tree is a heap
        int maxIndex = leftChildIndex;
        if (rightChildIndex < list.size()) {
          if (list.get(maxIndex).compareTo(
              list.get(rightChildIndex)) < 0) {
            maxIndex = rightChildIndex;
          }
        }      

        // Swap if the current node is less than the maximum 
        if (list.get(currI).compareTo(
            list.get(maxIndex)) < 0) {
          E temp = list.get(maxIndex);
          list.set(maxIndex, list.get(currI));
          list.set(currI, temp);
          currI = maxIndex;
        }   
        else 
          break; // The tree is a heap
      }

      return removedObject;
    }

    /** Get the number of nodes in the tree */
    public int getSize() {
      return list.size();
    }
  }

  // Inner class PaintHeap for displaying a tree on a panel
  class HeapView extends Pane {   
    private double radius = 30; // Tree node radius
    private double vGap = 60; // Gap between two levels in a tree

    protected void repaint() {
      this.getChildren().clear();
      
      // Display the nodes in heap recursively    
      displayTree(0, getWidth() / 2, 30, getWidth() / 4); 
    }
    
   

    /** Display a subtree root at position (x, y) */
    private void displayTree(int index, double x, double y, double hGap) {
      if (index < Heap.getSize()) {
        // Display root
        Circle circle = new Circle(x, y, radius);
        circle.setFill(Color.LIME);
        circle.setStrokeWidth(5);
        circle.setStroke(Color.YELLOW);
        getChildren().add(circle);
        this.getChildren().add(new Text(x - 8, y + 6, Heap.list.get(index) + ""));

        // Draw a line to the left node
        int indexOfLeft = 2 * index + 1;
        if (indexOfLeft < Heap.getSize())
          connectLeftChild(x - hGap, y + vGap, x, y);

        // Draw the left subtree
        displayTree(indexOfLeft, x - hGap, y + vGap, hGap / 2);

        // Draw a line to the right node
        int indexOfRight = 2 * index + 2;        
        if (indexOfRight < Heap.getSize())
          connectRightChild(x + hGap, y + vGap, x, y);

        // Draw the right subtree
        displayTree(indexOfRight, x + hGap, y + vGap, hGap / 2);      
      }
    }
    
    

    /** Connect a parent at (x2, y2) with 
     * its left child at (x1, y1) */
    private void connectLeftChild(double x1, double y1, double x2, double y2) { 
      double d = Math.sqrt(vGap * vGap + (x2 - x1) * (x2 - x1));
      double x11 = (int)(x1 + radius * (x2 - x1) / d);
      double y11 = (int)(y1 - radius * vGap / d);
      double x21 = (int)(x2 - radius * (x2 - x1) / d);
      double y21 = (int)(y2 + radius * vGap / d);
      getChildren().add(new Line(x11, y11, x21, y21));
    }

    /** Connect a parent at (x2, y2) with 
     * its right child at (x1, y1) */
    private void connectRightChild(double x1, double y1, double x2, double y2) {
      double d = Math.sqrt(vGap * vGap + (x2 - x1) * (x2 - x1));
      double x11 = (int)(x1 - radius * (x1 - x2) / d);
      double y11 = (int)(y1 - radius * vGap / d);
      double x21 = (int)(x2 + radius * (x1 - x2) / d);
      double y21 = (int)(y2 + radius * vGap / d);
      getChildren().add(new Line(x11, y11, x21, y21));
    }
  }
  
  
  
  public static void main(String[] args) {
    launch(args);
  }   
}