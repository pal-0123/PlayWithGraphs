/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Que;
import java.awt.CardLayout;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.lang.*;
import static Que.Graph.*;
import java.awt.HeadlessException;
import java.awt.*;
import static java.lang.Math.abs;
import static java.lang.Math.atan;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author pal_0123
 */
class myC1 implements Comparator<Integer>{
    public int compare(Integer i,Integer j){
        int i1=(int)i;
        int j1=(int)j;
        return vList.get(i1).getName().compareTo(vList.get(j1).getName());
    }
}
class myC2 implements Comparator<Integer>{
    public int compare(Integer i,Integer j){
        int i1=(int)i;
        int j1=(int)j;
        if(edgeList.get(i1).getFrom().getName().equals(edgeList.get(j1).getFrom().getName())){
            return edgeList.get(i1).getTo().getName().compareTo(edgeList.get(j1).getTo().getName());
        }
        return edgeList.get(i1).getFrom().getName().compareTo(edgeList.get(j1).getFrom().getName());
    }
}
class jP extends JPanel {
    public void paint(Graphics graphic){
        super.paint(graphic);
        Graphics2D graphic2d=(Graphics2D)graphic;
        graphic2d.setColor(Color.blue);
        for(int i=0;i<vList.size();i++){
            if(vList.get(i).getSpc()==1){
                graphic2d.setColor(Color.yellow);
            }
            graphic2d.fillOval(vList.get(i).getX()-10,vList.get(i).getY()-10,20,20);
            graphic2d.setColor(Color.black);
            graphic2d.drawString(vList.get(i).getName(),vList.get(i).getX()-15,vList.get(i).getY()-15);
            graphic2d.setColor(Color.blue);
            
        }
        graphic2d.setColor(Color.black);
        graphic2d.setStroke(new BasicStroke(4));
        for(int i=0;i<edgeList.size();i++){
            int x1,x2,y1,y2;
            x1=edgeList.get(i).getFrom().getX();
            y1=edgeList.get(i).getFrom().getY();
            x2=edgeList.get(i).getTo().getX();
            y2=edgeList.get(i).getTo().getY();
            if(edgeList.get(i).getSpc()==1){
                graphic2d.setColor(Color.red);
            }
            graphic2d.drawLine(x1, y1, x2, y2);
            graphic2d.drawString(Integer.toString(edgeList.get(i).getWeight()), (x1+2*x2)/3-8, (y1+2*y2)/3-8);
            double ang=atan(1.0*(y1-y2)/(x1-x2));
            int x3,y3,x4,y4;
            int px1,px2,px3,px4,py1,py2,py3,py4;
            x3=(int) (x2+20*cos(ang-0.55));
            y3=(int)(y2+20*sin(ang-0.55));
            x4=(int)(x2-20*cos(ang-0.55));
            y4=(int)(y2-20*sin(ang-0.55));
            if((x1-x3)*(x1-x3)+(y1-y3)*(y1-y3)<(x1-x4)*(x1-x4)+(y1-y4)*(y1-y4)){
                graphic2d.drawLine(x3, y3, x2, y2);
                px1=x3;
                py1=y3;
            }
            else{
                graphic2d.drawLine(x4, y4, x2, y2);
                px1=x4;
                py1=y4;
            }
            x3=(int) (x2+20*cos(ang+0.55));
            y3=(int)(y2+20*sin(ang+0.55));
            x4=(int)(x2-20*cos(ang+0.55));
            y4=(int)(y2-20*sin(ang+0.55));
            if((x1-x3)*(x1-x3)+(y1-y3)*(y1-y3)<(x1-x4)*(x1-x4)+(y1-y4)*(y1-y4)){
                graphic2d.drawLine(x3, y3, x2, y2);
                px2=x3;
                py2=y3;
            }
            else{
                graphic2d.drawLine(x4, y4, x2, y2);
                px2=x4;
                py2=y4;
            }
            graphic2d.drawLine(((px1+px2)/2+x2)/2, ((py1+py2)/2+y2)/2, px1, py1);
            graphic2d.drawLine(((px1+px2)/2+x2)/2, ((py1+py2)/2+y2)/2, px2, py2);
            graphic2d.setColor(Color.black);
        }
        graphic2d.setColor(Color.red);
        if(dragX!=-1 && srcX!=-1){
            graphic2d.drawLine(srcX,srcY,dragX,dragY);
        }
        if(tempx!=-1){
            graphic2d.setColor(Color.red);
            graphic2d.fillOval(tempx-8, tempy-8, 16, 16);
        }
        if(sqrx!=-1){
            graphic2d.setColor(Color.red);
//            graphic2d.fillOval(sqrx-8, sqry-8, 16, 16);
//            int x1,x2,x3,x4,y1,y2,y3,y4;
            int x[]={sqrx-8,sqrx-8,sqrx+8,sqrx+8};
            int y[]={sqry-8,sqry+8,sqry+8,sqry-8};
            graphic2d.fillPolygon(x,y,4);
        }
        if(plsx!=-1){
            graphic2d.setColor(Color.red);
//            graphic2d.fillOval(plsx-8, plsy-8, 16, 16);
            int x[] = {plsx - 4, plsx - 4, plsx + 4, plsx + 4};
            int y[] = {plsy - 4, plsy + 4, plsy + 4, plsy - 4};
            int x1[]={x[0]-5,x[1]-5,x[1],x[0],x[2],x[2],x[2]+5,x[3]+5,x[3],x[2],x[0],x[0]};
            int y1[]={y[0],y[1],y[1],y[1]+5,y[2]+5,y[2],y[2],y[3],y[3],y[3]-5,y[0]-5,y[0]};
            graphic2d.fillPolygon(x1, y1, 12);
        }
        if(crsx!=-1){
            graphic2d.setColor(Color.red);
//            graphic2d.fillOval(crsx-8, crsy-8, 16, 16);
//            int x[] = {crsx - 3, crsx - 3, crsx + 3, crsx + 3};
//            int y[] = {crsy - 3, crsy + 3, crsy + 3, crsy - 3};
//            int x1[] = {x[1] - 3, x[1] - 2,x[0], x[1]-3, x[1]-2,x[2], x[2] + 2, x[3] + 3,x[3], x[3]+3, x[0]+2,x[0]};
//            int y1[] = {y[0]-4, y[1]-4,y[0], y[1] + 4, y[2] + 4,y[2], y[2]+4, y[2]+4,y[3], y[3] - 4, y[3] - 4,y[0]};
//            graphic2d.fillPolygon(x1, y1, 12);
            graphic2d.setStroke(new BasicStroke(6));
            graphic2d.drawLine(crsx-6,crsy-6,crsx+6,crsy+6);
            graphic2d.drawLine(crsx-6,crsy+6,crsx+6,crsy-6);
        }
        if(trix!=-1){
            graphic2d.setColor(Color.red);
//            graphic2d.fillOval(trix-8, triy-8, 16, 16);
            int x[]={trix-12,trix,trix+12};
            int y[]={triy+10,triy-11,triy+10};
            graphic2d.fillPolygon(x,y,3);
        }
    }
    

    public jP() {
        this.requestFocus();
    }
    
}


public class myFrame extends javax.swing.JFrame implements Runnable{
    static boolean i,m,d;
    static int curSelect=-1;
    
    public void edgePath(ArrayList<Integer> pathEdge,int i,int j){
        if(i==j){
            return ;
        }
        edgePath(pathEdge,i,parent.get(j));
        for(int k=0;k<edgeList.size();k++){
            if(edgeList.get(k).getFrom().getName().equals(vertexList.get(parent.get(j)))
                    &&edgeList.get(k).getTo().getName().equals(vertexList.get(j))){
                pathEdge.add(k);
                break;
            }
        }
    }
    public void run(){
//        System.out.println("   asdfdsdfd");
ArrayList<Integer> pathEdge;    
pathEdge=new ArrayList<>();
           edgePath(pathEdge,animatei1,animatei2);
           boolean crc=false,sqr=false,pls=false,crs=false,tri=false;
           if(animateInteractiveCircle.isSelected()){
               crc=true;
           }
           if(animateInteractiveSquare.isSelected()){
               sqr=true;
           }
           if(animateInteractivePlus.isSelected()){
               pls=true;
           }
           if(animateInteractiveCross.isSelected()){
               crs=true;
           }
           if(animateInteractiveTriangle.isSelected()){
               tri=true;
           }
         while(animatei1!=-1){
            for(int i=0;i<pathEdge.size();i++){
                int temp1x,temp1y;
                temp1x=edgeList.get(pathEdge.get(i)).getFrom().getX();
                temp1y=edgeList.get(pathEdge.get(i)).getFrom().getY();
                int k=1;
                int z=1;
                double ang=atan(1.0*(edgeList.get(pathEdge.get(i)).getTo().getY()-edgeList.get(pathEdge.get(i)).getFrom().getY())/(edgeList.get(pathEdge.get(i)).getTo().getX()-edgeList.get(pathEdge.get(i)).getFrom().getX()));
                double d1,d2;
                d2=(edgeList.get(pathEdge.get(i)).getTo().getX()-temp1x)*(edgeList.get(pathEdge.get(i)).getTo().getX()-temp1x)+((edgeList.get(pathEdge.get(i)).getTo().getY()-temp1y))*((edgeList.get(pathEdge.get(i)).getTo().getY()-temp1y));
                double zzx=(edgeList.get(pathEdge.get(i)).getFrom().getX()+20*cos(ang));
                double zzy=(edgeList.get(pathEdge.get(i)).getFrom().getY()+20*sin(ang));
                
                d1=(edgeList.get(pathEdge.get(i)).getTo().getX()-zzx)*(edgeList.get(pathEdge.get(i)).getTo().getX()-zzx)+((edgeList.get(pathEdge.get(i)).getTo().getY()-zzy))*((edgeList.get(pathEdge.get(i)).getTo().getY()-zzy));
//                System.out.println(d1+" "+d2);
                if(d1>d2){
                    z=-1;              
                }
//                System.out.println(z+"    "+ang);
                while(animatei1!=-1){
                    double dist=(edgeList.get(pathEdge.get(i)).getTo().getX()-temp1x)*(edgeList.get(pathEdge.get(i)).getTo().getX()-temp1x)+((edgeList.get(pathEdge.get(i)).getTo().getY()-temp1y))*((edgeList.get(pathEdge.get(i)).getTo().getY()-temp1y));
                    if(dist<20.0){
                        break;
                    }
                    
                    temp1x=(int)(edgeList.get(pathEdge.get(i)).getFrom().getX()+z*k*cos(ang));
                    temp1y=(int)(edgeList.get(pathEdge.get(i)).getFrom().getY()+z*k*sin(ang));
                    k+=2;
                    if(crc){
                        tempx=temp1x;
                        tempy=temp1y;
                    }
                    else if(sqr){
                        sqrx=temp1x;
                        sqry=temp1y;
                    }
                    else if(tri){
                        trix=temp1x;
                        triy=temp1y;
                    }
                    else if(pls){
                        plsx=temp1x;
                        plsy=temp1y;
                    }
                    else if(crs){
                        crsx=temp1x;
                        crsy=temp1y;
                    }
                    repaint();
                    try {
                        sleep((long)20);
                    } catch (InterruptedException ex) {
                        ;
//                        Logger.getLogger(myFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            }
         }
         if (crc) {
            tempx = -1;
            tempy = -1;
        } 
        else if (sqr) {
            sqrx = -1;
            sqry = -1;
        } 
        else if (tri) {
            trix = -1;
            triy = -1;
        } 
        else if (pls) {
            plsx = -1;
            plsy = -1;
        } 
        else if (crs) {
            crsx = -1;
            crsy = -1;
        }
    }
    public void animate(){
        (new Thread(this)).start();
    }
    /**
     * Creates new form NewJFrame
     */
    public myFrame() {
        initComponents();
        Card.removeAll();
        Card.repaint();
        Card.revalidate();

        Card.add(blankCard);
        Card.repaint();
        Card.revalidate();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        interactiveBG = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        Card = new javax.swing.JPanel();
        blankCard = new javax.swing.JPanel();
        vertexInp = new javax.swing.JPanel();
        addVertexNameL = new javax.swing.JLabel();
        addVertexL = new javax.swing.JLabel();
        addVertexXL = new javax.swing.JLabel();
        addVertexYL = new javax.swing.JLabel();
        addVertexNameT = new javax.swing.JTextField();
        addVertexXT = new javax.swing.JTextField();
        addVertexYT = new javax.swing.JTextField();
        addVertexB = new javax.swing.JButton();
        edgeInp = new javax.swing.JPanel();
        addEdgeFromNameL = new javax.swing.JLabel();
        addEdgeL = new javax.swing.JLabel();
        addEdgeToNameL = new javax.swing.JLabel();
        addEdgeWeightL = new javax.swing.JLabel();
        addEdgeWeightT = new javax.swing.JTextField();
        addEdgeB = new javax.swing.JButton();
        addEdgeFromNameC = new javax.swing.JComboBox<>();
        addEdgeToNameC = new javax.swing.JComboBox<>();
        vertexSearch = new javax.swing.JPanel();
        searchVertexNameL = new javax.swing.JLabel();
        searchVertexL = new javax.swing.JLabel();
        searchVertexB = new javax.swing.JButton();
        searchVertexResultXT = new javax.swing.JTextField();
        searchVertexDetailsL = new javax.swing.JLabel();
        searchVertexResultYT = new javax.swing.JTextField();
        searchVertexResultXL = new javax.swing.JLabel();
        searchVertexResultYL = new javax.swing.JLabel();
        searchVertexResultEdgesL = new javax.swing.JLabel();
        searchVertexResultEdgesT = new javax.swing.JTextField();
        searchVertexNameC = new javax.swing.JComboBox<>();
        edgeSearch = new javax.swing.JPanel();
        searchEdgeFromNameL = new javax.swing.JLabel();
        searchVertexL1 = new javax.swing.JLabel();
        searchEdgeB = new javax.swing.JButton();
        searchEdgeResultX1T = new javax.swing.JTextField();
        searchEdgeDetailsL = new javax.swing.JLabel();
        searchEdgeResultY1T = new javax.swing.JTextField();
        searchEdgeResultX1L = new javax.swing.JLabel();
        searchEdgeResultY1L = new javax.swing.JLabel();
        searchEdgeResultWeightL = new javax.swing.JLabel();
        searchEdgeResultWeightT = new javax.swing.JTextField();
        searchEdgeFromNameC = new javax.swing.JComboBox<>();
        searchEdgeToNameL = new javax.swing.JLabel();
        searchEdgeToNameC = new javax.swing.JComboBox<>();
        searchEdgeResultX2L = new javax.swing.JLabel();
        searchEdgeResultY2L = new javax.swing.JLabel();
        searchEdgeResultX2T = new javax.swing.JTextField();
        searchEdgeResultY2T = new javax.swing.JTextField();
        vertexDelete = new javax.swing.JPanel();
        deleteVertexNameL = new javax.swing.JLabel();
        deleteVertexL = new javax.swing.JLabel();
        deleteVertexB = new javax.swing.JButton();
        deleteVertexNameC = new javax.swing.JComboBox<>();
        edgeDelete = new javax.swing.JPanel();
        deleteEdgeFromNameL = new javax.swing.JLabel();
        deleteEdgeL = new javax.swing.JLabel();
        deleteEdgeB = new javax.swing.JButton();
        deleteEdgeFromNameC = new javax.swing.JComboBox<>();
        deleteEdgeToNameL = new javax.swing.JLabel();
        deleteEdgeToNameC = new javax.swing.JComboBox<>();
        vertexModify = new javax.swing.JPanel();
        modifyVertexNameL = new javax.swing.JLabel();
        modifyVertexL = new javax.swing.JLabel();
        modifyVertexB = new javax.swing.JButton();
        modifyVertexNameC = new javax.swing.JComboBox<>();
        modifyVertexNewYT = new javax.swing.JTextField();
        modifyVertexNameNewL = new javax.swing.JLabel();
        modifyVertexNewXL = new javax.swing.JLabel();
        modifyVertexNewXT = new javax.swing.JTextField();
        modifyVertexNameNewT = new javax.swing.JTextField();
        modifyVertexNewYL = new javax.swing.JLabel();
        edgeModify = new javax.swing.JPanel();
        modifyEdgeL = new javax.swing.JLabel();
        modifyEdgeB = new javax.swing.JButton();
        modifyEdgeFromNameC = new javax.swing.JComboBox<>();
        modifyEdgeWeightNewL = new javax.swing.JLabel();
        modifyEdgeToNameC = new javax.swing.JComboBox<>();
        modifyEdgeToNameL = new javax.swing.JLabel();
        modifyEdgeWeightNewT = new javax.swing.JTextField();
        modifyEdgeFromNameL = new javax.swing.JLabel();
        importGraph = new javax.swing.JPanel();
        importGraphPathL = new javax.swing.JLabel();
        importGraphL = new javax.swing.JLabel();
        importGraphB = new javax.swing.JButton();
        importGraphPathT = new javax.swing.JTextField();
        exportGraph = new javax.swing.JPanel();
        exportGraphPathL = new javax.swing.JLabel();
        exportGraphL = new javax.swing.JLabel();
        exportGraphB = new javax.swing.JButton();
        exportGraphPathT = new javax.swing.JTextField();
        shortestPath = new javax.swing.JPanel();
        pathL = new javax.swing.JLabel();
        pathFromNameL = new javax.swing.JLabel();
        pathtoNameL = new javax.swing.JLabel();
        pathFromNameC = new javax.swing.JComboBox<>();
        pathToNameC = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        pathDistanceT = new javax.swing.JTextField();
        pathPathT = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        interactive = new javax.swing.JPanel();
        detailsUpdateP = new javax.swing.JPanel();
        blankInteractiveP = new javax.swing.JPanel();
        addEdgeInteractiveP = new javax.swing.JPanel();
        addEdgeInteractiveWeightL = new javax.swing.JLabel();
        addEdgeInteractiveWeightT = new javax.swing.JTextField();
        updateEdgeInteractiveP = new javax.swing.JPanel();
        updateEdgeInteractiveWeightL = new javax.swing.JLabel();
        updateEdgeInteractiveWeightT = new javax.swing.JTextField();
        updateEdgeInteractiveModeWeightB = new javax.swing.JButton();
        updateVertexInteractiveP = new javax.swing.JPanel();
        updateVertexInteractiveNameL = new javax.swing.JLabel();
        updateVertexInteractiveNameT = new javax.swing.JTextField();
        updateVertexInteractiveNameB = new javax.swing.JButton();
        pathInteractiveP = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        animateInteractiveCircle = new javax.swing.JRadioButton();
        animateInteractiveSquare = new javax.swing.JRadioButton();
        animateInteractivePlus = new javax.swing.JRadioButton();
        animateInteractiveCross = new javax.swing.JRadioButton();
        animateInteractiveTriangle = new javax.swing.JRadioButton();
        addVertexInteractiveP = new javax.swing.JPanel();
        addVertexInteractiveNameL = new javax.swing.JLabel();
        addVertexInteractiveNameT = new javax.swing.JTextField();
        selectInteractiveP = new javax.swing.JPanel();
        vertexInteractiveModeRB = new javax.swing.JRadioButton();
        pathInteractiveModeRB = new javax.swing.JRadioButton();
        edgeInteractiveModeRB = new javax.swing.JRadioButton();
        interactiveModeL = new javax.swing.JLabel();
        modeDetailsP = new javax.swing.JPanel();
        modeDetailsT = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jP workAreaP;
        workAreaP = new jP();
        workAreaXL = new javax.swing.JLabel();
        workAreaYL = new javax.swing.JLabel();
        myMenu = new javax.swing.JMenuBar();
        fileM = new javax.swing.JMenu();
        close = new javax.swing.JMenuItem();
        viewM = new javax.swing.JMenu();
        normalMode = new javax.swing.JMenuItem();
        interactiveMode = new javax.swing.JMenuItem();
        vertexM = new javax.swing.JMenu();
        addV = new javax.swing.JMenuItem();
        searchV = new javax.swing.JMenuItem();
        deleteV = new javax.swing.JMenuItem();
        modifyV = new javax.swing.JMenuItem();
        edgeM = new javax.swing.JMenu();
        addE = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        deleteE = new javax.swing.JMenuItem();
        modifyE = new javax.swing.JMenuItem();
        impexpM = new javax.swing.JMenu();
        loadG = new javax.swing.JMenuItem();
        exportG = new javax.swing.JMenuItem();
        pathM = new javax.swing.JMenu();
        shortestPathM = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        Card.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout blankCardLayout = new javax.swing.GroupLayout(blankCard);
        blankCard.setLayout(blankCardLayout);
        blankCardLayout.setHorizontalGroup(
            blankCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 654, Short.MAX_VALUE)
        );
        blankCardLayout.setVerticalGroup(
            blankCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 449, Short.MAX_VALUE)
        );

        Card.add(blankCard, "card11");

        addVertexNameL.setText("Name:");

        addVertexL.setText("Add Vertex");

        addVertexXL.setText("X:");

        addVertexYL.setText("Y:");

        addVertexNameT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addVertexNameTActionPerformed(evt);
            }
        });

        addVertexYT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addVertexYTActionPerformed(evt);
            }
        });

        addVertexB.setText("Add");
        addVertexB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addVertexBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout vertexInpLayout = new javax.swing.GroupLayout(vertexInp);
        vertexInp.setLayout(vertexInpLayout);
        vertexInpLayout.setHorizontalGroup(
            vertexInpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vertexInpLayout.createSequentialGroup()
                .addGroup(vertexInpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vertexInpLayout.createSequentialGroup()
                        .addGap(260, 260, 260)
                        .addComponent(addVertexL, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(vertexInpLayout.createSequentialGroup()
                        .addGap(181, 181, 181)
                        .addGroup(vertexInpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(vertexInpLayout.createSequentialGroup()
                                .addComponent(addVertexNameL, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(addVertexNameT, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(vertexInpLayout.createSequentialGroup()
                                .addGroup(vertexInpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(addVertexXL)
                                    .addComponent(addVertexYL))
                                .addGap(49, 49, 49)
                                .addGroup(vertexInpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(addVertexB)
                                    .addGroup(vertexInpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(addVertexXT)
                                        .addComponent(addVertexYT, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)))))))
                .addContainerGap(185, Short.MAX_VALUE))
        );
        vertexInpLayout.setVerticalGroup(
            vertexInpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vertexInpLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(addVertexL, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(vertexInpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addVertexNameL, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addVertexNameT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(vertexInpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addVertexXL)
                    .addComponent(addVertexXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(vertexInpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addVertexYL)
                    .addComponent(addVertexYT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(addVertexB)
                .addContainerGap(231, Short.MAX_VALUE))
        );

        Card.add(vertexInp, "card2");

        addEdgeFromNameL.setText("From Name:");

        addEdgeL.setText("Add Edge");

        addEdgeToNameL.setText("To Name:");

        addEdgeWeightL.setText("Weight:");

        addEdgeWeightT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEdgeWeightTActionPerformed(evt);
            }
        });

        addEdgeB.setText("Add");
        addEdgeB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEdgeBActionPerformed(evt);
            }
        });

        addEdgeFromNameC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        addEdgeToNameC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout edgeInpLayout = new javax.swing.GroupLayout(edgeInp);
        edgeInp.setLayout(edgeInpLayout);
        edgeInpLayout.setHorizontalGroup(
            edgeInpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(edgeInpLayout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addGroup(edgeInpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(edgeInpLayout.createSequentialGroup()
                        .addGroup(edgeInpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addEdgeToNameL)
                            .addComponent(addEdgeWeightL, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52)
                        .addGroup(edgeInpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addEdgeWeightT, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addEdgeToNameC, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addEdgeB))
                        .addContainerGap(170, Short.MAX_VALUE))
                    .addGroup(edgeInpLayout.createSequentialGroup()
                        .addComponent(addEdgeFromNameL, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addEdgeFromNameC, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(edgeInpLayout.createSequentialGroup()
                .addGap(260, 260, 260)
                .addComponent(addEdgeL, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(306, Short.MAX_VALUE))
        );
        edgeInpLayout.setVerticalGroup(
            edgeInpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(edgeInpLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(addEdgeL, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(edgeInpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addEdgeFromNameL, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addEdgeFromNameC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(edgeInpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addEdgeToNameL)
                    .addComponent(addEdgeToNameC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(edgeInpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addEdgeWeightL)
                    .addComponent(addEdgeWeightT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(addEdgeB)
                .addContainerGap(229, Short.MAX_VALUE))
        );

        Card.add(edgeInp, "card3");

        searchVertexNameL.setText("Name:");

        searchVertexL.setText("Search Vertex");

        searchVertexB.setText("Search");
        searchVertexB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchVertexBActionPerformed(evt);
            }
        });

        searchVertexDetailsL.setText("Vertex Details");

        searchVertexResultXL.setText("X:");

        searchVertexResultYL.setText("Y:");

        searchVertexResultEdgesL.setText("Edges:");

        searchVertexNameC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout vertexSearchLayout = new javax.swing.GroupLayout(vertexSearch);
        vertexSearch.setLayout(vertexSearchLayout);
        vertexSearchLayout.setHorizontalGroup(
            vertexSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vertexSearchLayout.createSequentialGroup()
                .addGroup(vertexSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vertexSearchLayout.createSequentialGroup()
                        .addGap(260, 260, 260)
                        .addComponent(searchVertexL, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vertexSearchLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(vertexSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vertexSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, vertexSearchLayout.createSequentialGroup()
                                    .addComponent(searchVertexResultEdgesL)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(searchVertexResultEdgesT))
                                .addGroup(vertexSearchLayout.createSequentialGroup()
                                    .addGroup(vertexSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(searchVertexResultXL)
                                        .addComponent(searchVertexResultYL))
                                    .addGap(49, 49, 49)
                                    .addGroup(vertexSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(searchVertexB)
                                        .addComponent(searchVertexDetailsL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(searchVertexResultXT)
                                        .addComponent(searchVertexResultYT))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vertexSearchLayout.createSequentialGroup()
                                .addComponent(searchVertexNameL, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchVertexNameC, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)))))
                .addContainerGap(260, Short.MAX_VALUE))
        );
        vertexSearchLayout.setVerticalGroup(
            vertexSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vertexSearchLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(searchVertexL, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(vertexSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchVertexNameL, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchVertexNameC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchVertexB)
                .addGap(27, 27, 27)
                .addComponent(searchVertexDetailsL)
                .addGap(18, 18, 18)
                .addGroup(vertexSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchVertexResultXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchVertexResultXL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(vertexSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchVertexResultYT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchVertexResultYL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(vertexSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchVertexResultEdgesL)
                    .addComponent(searchVertexResultEdgesT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(151, Short.MAX_VALUE))
        );

        Card.add(vertexSearch, "card4");

        searchEdgeFromNameL.setText("From Name:");

        searchVertexL1.setText("Search Edge");

        searchEdgeB.setText("Search");
        searchEdgeB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchEdgeBActionPerformed(evt);
            }
        });

        searchEdgeDetailsL.setText("Edge Details");

        searchEdgeResultX1L.setText("X1:");

        searchEdgeResultY1L.setText("Y1:");

        searchEdgeResultWeightL.setText("Weight:");

        searchEdgeFromNameC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        searchEdgeToNameL.setText("To Name:");

        searchEdgeToNameC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        searchEdgeResultX2L.setText("X2");

        searchEdgeResultY2L.setText("Y2:");

        javax.swing.GroupLayout edgeSearchLayout = new javax.swing.GroupLayout(edgeSearch);
        edgeSearch.setLayout(edgeSearchLayout);
        edgeSearchLayout.setHorizontalGroup(
            edgeSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(edgeSearchLayout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addGroup(edgeSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchEdgeResultX1L)
                    .addComponent(searchEdgeResultY1L))
                .addGap(28, 28, 28)
                .addGroup(edgeSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(edgeSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(searchEdgeToNameL)
                        .addGroup(edgeSearchLayout.createSequentialGroup()
                            .addGroup(edgeSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(searchEdgeResultWeightL)
                                .addComponent(searchEdgeFromNameL, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(edgeSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(searchVertexL1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(edgeSearchLayout.createSequentialGroup()
                                    .addGap(12, 12, 12)
                                    .addGroup(edgeSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(searchEdgeResultWeightT, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(edgeSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(searchEdgeB)
                                            .addComponent(searchEdgeFromNameC, 0, 131, Short.MAX_VALUE)
                                            .addComponent(searchEdgeToNameC, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(searchEdgeDetailsL))))))
                    .addGroup(edgeSearchLayout.createSequentialGroup()
                        .addGroup(edgeSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(searchEdgeResultY1T, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchEdgeResultX1T, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(101, 101, 101)
                        .addGroup(edgeSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(searchEdgeResultX2L)
                            .addComponent(searchEdgeResultY2L))
                        .addGap(27, 27, 27)))
                .addGap(7, 7, 7)
                .addGroup(edgeSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(searchEdgeResultX2T, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                    .addComponent(searchEdgeResultY2T))
                .addContainerGap(169, Short.MAX_VALUE))
        );
        edgeSearchLayout.setVerticalGroup(
            edgeSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(edgeSearchLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(searchVertexL1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(edgeSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchEdgeFromNameL, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchEdgeFromNameC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(edgeSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchEdgeToNameL)
                    .addComponent(searchEdgeToNameC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(searchEdgeB)
                .addGap(12, 12, 12)
                .addComponent(searchEdgeDetailsL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(edgeSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchEdgeResultX1T, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchEdgeResultX1L)
                    .addComponent(searchEdgeResultX2L)
                    .addComponent(searchEdgeResultX2T, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(edgeSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchEdgeResultY1L)
                    .addGroup(edgeSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(searchEdgeResultY1T, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(searchEdgeResultY2L)
                        .addComponent(searchEdgeResultY2T, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(edgeSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchEdgeResultWeightL)
                    .addComponent(searchEdgeResultWeightT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(157, Short.MAX_VALUE))
        );

        Card.add(edgeSearch, "card12");

        deleteVertexNameL.setText("Name:");

        deleteVertexL.setText("Delete Vertex");

        deleteVertexB.setText("Delete");
        deleteVertexB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteVertexBActionPerformed(evt);
            }
        });

        deleteVertexNameC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        deleteVertexNameC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteVertexNameCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout vertexDeleteLayout = new javax.swing.GroupLayout(vertexDelete);
        vertexDelete.setLayout(vertexDeleteLayout);
        vertexDeleteLayout.setHorizontalGroup(
            vertexDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vertexDeleteLayout.createSequentialGroup()
                .addContainerGap(188, Short.MAX_VALUE)
                .addComponent(deleteVertexNameL, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(vertexDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(deleteVertexL, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                    .addComponent(deleteVertexB)
                    .addComponent(deleteVertexNameC, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(254, 254, 254))
        );
        vertexDeleteLayout.setVerticalGroup(
            vertexDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vertexDeleteLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(deleteVertexL, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(vertexDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteVertexNameL, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteVertexNameC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(deleteVertexB)
                .addContainerGap(265, Short.MAX_VALUE))
        );

        Card.add(vertexDelete, "card5");

        deleteEdgeFromNameL.setText("From Name:");

        deleteEdgeL.setText("Delete Edge");

        deleteEdgeB.setText("Delete");
        deleteEdgeB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteEdgeBActionPerformed(evt);
            }
        });

        deleteEdgeFromNameC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        deleteEdgeToNameL.setText("To Name:");

        deleteEdgeToNameC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout edgeDeleteLayout = new javax.swing.GroupLayout(edgeDelete);
        edgeDelete.setLayout(edgeDeleteLayout);
        edgeDeleteLayout.setHorizontalGroup(
            edgeDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, edgeDeleteLayout.createSequentialGroup()
                .addContainerGap(187, Short.MAX_VALUE)
                .addGroup(edgeDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deleteEdgeToNameL)
                    .addGroup(edgeDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(deleteEdgeL, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(edgeDeleteLayout.createSequentialGroup()
                            .addComponent(deleteEdgeFromNameL, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(edgeDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(deleteEdgeB)
                                .addComponent(deleteEdgeFromNameC, 0, 134, Short.MAX_VALUE)
                                .addComponent(deleteEdgeToNameC, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(225, 225, 225))
        );
        edgeDeleteLayout.setVerticalGroup(
            edgeDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(edgeDeleteLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(deleteEdgeL, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(edgeDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteEdgeFromNameL, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteEdgeFromNameC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(edgeDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteEdgeToNameL)
                    .addComponent(deleteEdgeToNameC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(deleteEdgeB)
                .addContainerGap(206, Short.MAX_VALUE))
        );

        Card.add(edgeDelete, "card7");

        modifyVertexNameL.setText("Name:");

        modifyVertexL.setText("Modify Vertex");

        modifyVertexB.setText("Modify");
        modifyVertexB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyVertexBActionPerformed(evt);
            }
        });

        modifyVertexNameC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        modifyVertexNewYT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyVertexNewYTActionPerformed(evt);
            }
        });

        modifyVertexNameNewL.setText("Name:");

        modifyVertexNewXL.setText("X:");

        modifyVertexNameNewT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyVertexNameNewTActionPerformed(evt);
            }
        });

        modifyVertexNewYL.setText("Y:");

        javax.swing.GroupLayout vertexModifyLayout = new javax.swing.GroupLayout(vertexModify);
        vertexModify.setLayout(vertexModifyLayout);
        vertexModifyLayout.setHorizontalGroup(
            vertexModifyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vertexModifyLayout.createSequentialGroup()
                .addContainerGap(188, Short.MAX_VALUE)
                .addGroup(vertexModifyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(modifyVertexNameL, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyVertexNewXL)
                    .addComponent(modifyVertexNewYL)
                    .addComponent(modifyVertexNameNewL, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(vertexModifyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(modifyVertexNewYT, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyVertexNewXT, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyVertexNameNewT, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(vertexModifyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(modifyVertexL, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                        .addComponent(modifyVertexB)
                        .addComponent(modifyVertexNameC, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(163, 163, 163))
        );
        vertexModifyLayout.setVerticalGroup(
            vertexModifyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vertexModifyLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(modifyVertexL, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(vertexModifyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modifyVertexNameL, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyVertexNameC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(vertexModifyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modifyVertexNameNewT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyVertexNameNewL, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(vertexModifyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modifyVertexNewXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyVertexNewXL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(vertexModifyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modifyVertexNewYT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyVertexNewYL))
                .addGap(20, 20, 20)
                .addComponent(modifyVertexB)
                .addContainerGap(148, Short.MAX_VALUE))
        );

        Card.add(vertexModify, "card6");

        modifyEdgeL.setText("Modify Edge");

        modifyEdgeB.setText("Modify");
        modifyEdgeB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyEdgeBActionPerformed(evt);
            }
        });

        modifyEdgeFromNameC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        modifyEdgeWeightNewL.setText("Weight:");

        modifyEdgeToNameC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        modifyEdgeToNameL.setText("To Name:");

        modifyEdgeWeightNewT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyEdgeWeightNewTActionPerformed(evt);
            }
        });

        modifyEdgeFromNameL.setText("From Name:");

        javax.swing.GroupLayout edgeModifyLayout = new javax.swing.GroupLayout(edgeModify);
        edgeModify.setLayout(edgeModifyLayout);
        edgeModifyLayout.setHorizontalGroup(
            edgeModifyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, edgeModifyLayout.createSequentialGroup()
                .addContainerGap(266, Short.MAX_VALUE)
                .addGroup(edgeModifyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(modifyEdgeFromNameC, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyEdgeToNameC, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyEdgeWeightNewT, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyEdgeL, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyEdgeB))
                .addGap(163, 163, 163))
            .addGroup(edgeModifyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(edgeModifyLayout.createSequentialGroup()
                    .addGap(155, 155, 155)
                    .addGroup(edgeModifyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(modifyEdgeToNameL)
                        .addComponent(modifyEdgeWeightNewL, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(modifyEdgeFromNameL, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(401, Short.MAX_VALUE)))
        );
        edgeModifyLayout.setVerticalGroup(
            edgeModifyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(edgeModifyLayout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(modifyEdgeL, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(modifyEdgeFromNameC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(modifyEdgeToNameC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modifyEdgeWeightNewT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(modifyEdgeB)
                .addGap(154, 154, 154))
            .addGroup(edgeModifyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(edgeModifyLayout.createSequentialGroup()
                    .addGap(156, 156, 156)
                    .addComponent(modifyEdgeFromNameL, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(modifyEdgeToNameL)
                    .addGap(15, 15, 15)
                    .addComponent(modifyEdgeWeightNewL)
                    .addContainerGap(219, Short.MAX_VALUE)))
        );

        Card.add(edgeModify, "card8");

        importGraphPathL.setText("Path:");

        importGraphL.setText("Import Graph");

        importGraphB.setText("Import");
        importGraphB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importGraphBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout importGraphLayout = new javax.swing.GroupLayout(importGraph);
        importGraph.setLayout(importGraphLayout);
        importGraphLayout.setHorizontalGroup(
            importGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, importGraphLayout.createSequentialGroup()
                .addContainerGap(188, Short.MAX_VALUE)
                .addComponent(importGraphPathL, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(importGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(importGraphL, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                    .addComponent(importGraphB)
                    .addComponent(importGraphPathT))
                .addGap(254, 254, 254))
        );
        importGraphLayout.setVerticalGroup(
            importGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(importGraphLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(importGraphL, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(importGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(importGraphPathL, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(importGraphPathT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(importGraphB)
                .addContainerGap(265, Short.MAX_VALUE))
        );

        Card.add(importGraph, "card9");

        exportGraphPathL.setText("Path:");

        exportGraphL.setText("Export Graph");

        exportGraphB.setText("Export");
        exportGraphB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportGraphBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout exportGraphLayout = new javax.swing.GroupLayout(exportGraph);
        exportGraph.setLayout(exportGraphLayout);
        exportGraphLayout.setHorizontalGroup(
            exportGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, exportGraphLayout.createSequentialGroup()
                .addContainerGap(188, Short.MAX_VALUE)
                .addComponent(exportGraphPathL, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(exportGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(exportGraphL, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                    .addComponent(exportGraphB)
                    .addComponent(exportGraphPathT))
                .addGap(254, 254, 254))
        );
        exportGraphLayout.setVerticalGroup(
            exportGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(exportGraphLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(exportGraphL, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(exportGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exportGraphPathL, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exportGraphPathT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(exportGraphB)
                .addContainerGap(265, Short.MAX_VALUE))
        );

        Card.add(exportGraph, "card10");

        pathL.setText("Get Path");

        pathFromNameL.setText("From Name:");

        pathtoNameL.setText("To Name:");

        pathFromNameC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        pathToNameC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setText("Get Path");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        pathDistanceT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pathDistanceTActionPerformed(evt);
            }
        });

        jLabel1.setText("Distance:");

        jLabel2.setText("Path:");

        javax.swing.GroupLayout shortestPathLayout = new javax.swing.GroupLayout(shortestPath);
        shortestPath.setLayout(shortestPathLayout);
        shortestPathLayout.setHorizontalGroup(
            shortestPathLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(shortestPathLayout.createSequentialGroup()
                .addGroup(shortestPathLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(shortestPathLayout.createSequentialGroup()
                        .addGap(273, 273, 273)
                        .addComponent(pathL))
                    .addGroup(shortestPathLayout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addGroup(shortestPathLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pathFromNameL)
                            .addComponent(pathtoNameL)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(shortestPathLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1)
                            .addComponent(pathFromNameC, 0, 123, Short.MAX_VALUE)
                            .addComponent(pathToNameC, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pathPathT)
                            .addComponent(pathDistanceT))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        shortestPathLayout.setVerticalGroup(
            shortestPathLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(shortestPathLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(pathL)
                .addGap(36, 36, 36)
                .addGroup(shortestPathLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pathFromNameL)
                    .addComponent(pathFromNameC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(shortestPathLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pathtoNameL)
                    .addComponent(pathToNameC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(36, 36, 36)
                .addGroup(shortestPathLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pathDistanceT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(shortestPathLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pathPathT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(171, Short.MAX_VALUE))
        );

        Card.add(shortestPath, "card13");

        detailsUpdateP.setBackground(new java.awt.Color(0, 153, 153));
        detailsUpdateP.setLayout(new java.awt.CardLayout());

        blankInteractiveP.setBackground(new java.awt.Color(0, 204, 204));

        javax.swing.GroupLayout blankInteractivePLayout = new javax.swing.GroupLayout(blankInteractiveP);
        blankInteractiveP.setLayout(blankInteractivePLayout);
        blankInteractivePLayout.setHorizontalGroup(
            blankInteractivePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 492, Short.MAX_VALUE)
        );
        blankInteractivePLayout.setVerticalGroup(
            blankInteractivePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 33, Short.MAX_VALUE)
        );

        detailsUpdateP.add(blankInteractiveP, "card3");

        addEdgeInteractiveP.setBackground(new java.awt.Color(0, 204, 204));

        addEdgeInteractiveWeightL.setBackground(new java.awt.Color(0, 204, 204));
        addEdgeInteractiveWeightL.setText("Weight: ");

        addEdgeInteractiveWeightT.setBackground(new java.awt.Color(0, 204, 204));

        javax.swing.GroupLayout addEdgeInteractivePLayout = new javax.swing.GroupLayout(addEdgeInteractiveP);
        addEdgeInteractiveP.setLayout(addEdgeInteractivePLayout);
        addEdgeInteractivePLayout.setHorizontalGroup(
            addEdgeInteractivePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addEdgeInteractivePLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addEdgeInteractiveWeightL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addEdgeInteractiveWeightT, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(315, Short.MAX_VALUE))
        );
        addEdgeInteractivePLayout.setVerticalGroup(
            addEdgeInteractivePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addEdgeInteractivePLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(addEdgeInteractivePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addEdgeInteractiveWeightL)
                    .addComponent(addEdgeInteractiveWeightT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        detailsUpdateP.add(addEdgeInteractiveP, "card2");

        updateEdgeInteractiveP.setBackground(new java.awt.Color(0, 204, 204));

        updateEdgeInteractiveWeightL.setBackground(new java.awt.Color(0, 204, 204));
        updateEdgeInteractiveWeightL.setText("Weight: ");

        updateEdgeInteractiveWeightT.setBackground(new java.awt.Color(0, 204, 204));

        updateEdgeInteractiveModeWeightB.setBackground(new java.awt.Color(0, 204, 204));
        updateEdgeInteractiveModeWeightB.setText("Update");

        javax.swing.GroupLayout updateEdgeInteractivePLayout = new javax.swing.GroupLayout(updateEdgeInteractiveP);
        updateEdgeInteractiveP.setLayout(updateEdgeInteractivePLayout);
        updateEdgeInteractivePLayout.setHorizontalGroup(
            updateEdgeInteractivePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateEdgeInteractivePLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(updateEdgeInteractiveWeightL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updateEdgeInteractiveWeightT, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(updateEdgeInteractiveModeWeightB)
                .addContainerGap(219, Short.MAX_VALUE))
        );
        updateEdgeInteractivePLayout.setVerticalGroup(
            updateEdgeInteractivePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateEdgeInteractivePLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(updateEdgeInteractivePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateEdgeInteractiveWeightL)
                    .addComponent(updateEdgeInteractiveWeightT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateEdgeInteractiveModeWeightB))
                .addContainerGap())
        );

        detailsUpdateP.add(updateEdgeInteractiveP, "card2");

        updateVertexInteractiveP.setBackground(new java.awt.Color(0, 204, 204));

        updateVertexInteractiveNameL.setBackground(new java.awt.Color(0, 204, 204));
        updateVertexInteractiveNameL.setText("Name:");

        updateVertexInteractiveNameT.setBackground(new java.awt.Color(0, 204, 204));

        updateVertexInteractiveNameB.setBackground(new java.awt.Color(0, 204, 204));
        updateVertexInteractiveNameB.setText("Update");
        updateVertexInteractiveNameB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateVertexInteractiveNameBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout updateVertexInteractivePLayout = new javax.swing.GroupLayout(updateVertexInteractiveP);
        updateVertexInteractiveP.setLayout(updateVertexInteractivePLayout);
        updateVertexInteractivePLayout.setHorizontalGroup(
            updateVertexInteractivePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateVertexInteractivePLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(updateVertexInteractiveNameL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updateVertexInteractiveNameT, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(updateVertexInteractiveNameB)
                .addContainerGap(233, Short.MAX_VALUE))
        );
        updateVertexInteractivePLayout.setVerticalGroup(
            updateVertexInteractivePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateVertexInteractivePLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(updateVertexInteractivePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateVertexInteractiveNameL)
                    .addComponent(updateVertexInteractiveNameT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateVertexInteractiveNameB))
                .addContainerGap())
        );

        detailsUpdateP.add(updateVertexInteractiveP, "card2");

        pathInteractiveP.setBackground(new java.awt.Color(0, 204, 204));

        jButton2.setBackground(new java.awt.Color(0, 204, 204));
        jButton2.setText("Stop");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        animateInteractiveCircle.setBackground(new java.awt.Color(0, 204, 204));
        buttonGroup2.add(animateInteractiveCircle);
        animateInteractiveCircle.setText("circle");

        animateInteractiveSquare.setBackground(new java.awt.Color(0, 204, 204));
        buttonGroup2.add(animateInteractiveSquare);
        animateInteractiveSquare.setText("square");

        animateInteractivePlus.setBackground(new java.awt.Color(0, 204, 204));
        buttonGroup2.add(animateInteractivePlus);
        animateInteractivePlus.setText("plus");
        animateInteractivePlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                animateInteractivePlusActionPerformed(evt);
            }
        });

        animateInteractiveCross.setBackground(new java.awt.Color(0, 204, 204));
        buttonGroup2.add(animateInteractiveCross);
        animateInteractiveCross.setText("cross");

        animateInteractiveTriangle.setBackground(new java.awt.Color(0, 204, 204));
        buttonGroup2.add(animateInteractiveTriangle);
        animateInteractiveTriangle.setText("triangle");

        javax.swing.GroupLayout pathInteractivePLayout = new javax.swing.GroupLayout(pathInteractiveP);
        pathInteractiveP.setLayout(pathInteractivePLayout);
        pathInteractivePLayout.setHorizontalGroup(
            pathInteractivePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pathInteractivePLayout.createSequentialGroup()
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(animateInteractiveCircle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(animateInteractiveSquare)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(animateInteractivePlus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(animateInteractiveCross)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(animateInteractiveTriangle)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pathInteractivePLayout.setVerticalGroup(
            pathInteractivePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pathInteractivePLayout.createSequentialGroup()
                .addGap(0, 8, Short.MAX_VALUE)
                .addGroup(pathInteractivePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(animateInteractiveCircle)
                    .addComponent(animateInteractiveSquare)
                    .addComponent(animateInteractivePlus)
                    .addComponent(animateInteractiveCross)
                    .addComponent(animateInteractiveTriangle)))
        );

        detailsUpdateP.add(pathInteractiveP, "card7");

        addVertexInteractiveP.setBackground(new java.awt.Color(0, 204, 204));

        addVertexInteractiveNameL.setBackground(new java.awt.Color(0, 204, 204));
        addVertexInteractiveNameL.setText("Name:");

        addVertexInteractiveNameT.setBackground(new java.awt.Color(0, 204, 204));

        javax.swing.GroupLayout addVertexInteractivePLayout = new javax.swing.GroupLayout(addVertexInteractiveP);
        addVertexInteractiveP.setLayout(addVertexInteractivePLayout);
        addVertexInteractivePLayout.setHorizontalGroup(
            addVertexInteractivePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addVertexInteractivePLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addVertexInteractiveNameL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addVertexInteractiveNameT, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(329, Short.MAX_VALUE))
        );
        addVertexInteractivePLayout.setVerticalGroup(
            addVertexInteractivePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addVertexInteractivePLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(addVertexInteractivePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addVertexInteractiveNameL)
                    .addComponent(addVertexInteractiveNameT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        detailsUpdateP.add(addVertexInteractiveP, "card2");

        selectInteractiveP.setBackground(new java.awt.Color(0, 153, 153));

        vertexInteractiveModeRB.setBackground(new java.awt.Color(0, 153, 153));
        interactiveBG.add(vertexInteractiveModeRB);
        vertexInteractiveModeRB.setText("Vertex");
        vertexInteractiveModeRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vertexInteractiveModeRBActionPerformed(evt);
            }
        });

        pathInteractiveModeRB.setBackground(new java.awt.Color(0, 153, 153));
        interactiveBG.add(pathInteractiveModeRB);
        pathInteractiveModeRB.setText("Path");
        pathInteractiveModeRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pathInteractiveModeRBActionPerformed(evt);
            }
        });

        edgeInteractiveModeRB.setBackground(new java.awt.Color(0, 153, 153));
        interactiveBG.add(edgeInteractiveModeRB);
        edgeInteractiveModeRB.setText("Edge");
        edgeInteractiveModeRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edgeInteractiveModeRBActionPerformed(evt);
            }
        });

        interactiveModeL.setBackground(new java.awt.Color(0, 153, 153));
        interactiveModeL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        interactiveModeL.setText("Interactive Mode");

        javax.swing.GroupLayout selectInteractivePLayout = new javax.swing.GroupLayout(selectInteractiveP);
        selectInteractiveP.setLayout(selectInteractivePLayout);
        selectInteractivePLayout.setHorizontalGroup(
            selectInteractivePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectInteractivePLayout.createSequentialGroup()
                .addGroup(selectInteractivePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pathInteractiveModeRB)
                    .addComponent(vertexInteractiveModeRB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(interactiveModeL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edgeInteractiveModeRB)
                .addContainerGap())
        );
        selectInteractivePLayout.setVerticalGroup(
            selectInteractivePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectInteractivePLayout.createSequentialGroup()
                .addGroup(selectInteractivePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(edgeInteractiveModeRB)
                    .addComponent(vertexInteractiveModeRB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pathInteractiveModeRB))
            .addGroup(selectInteractivePLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(interactiveModeL)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        modeDetailsP.setBackground(new java.awt.Color(0, 153, 153));

        modeDetailsT.setBackground(new java.awt.Color(0, 153, 153));
        modeDetailsT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        modeDetailsT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modeDetailsTActionPerformed(evt);
            }
        });

        jRadioButton1.setBackground(new java.awt.Color(0, 153, 153));
        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Add");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setBackground(new java.awt.Color(0, 153, 153));
        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Delete");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jRadioButton3.setBackground(new java.awt.Color(0, 153, 153));
        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setText("Modify");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout modeDetailsPLayout = new javax.swing.GroupLayout(modeDetailsP);
        modeDetailsP.setLayout(modeDetailsPLayout);
        modeDetailsPLayout.setHorizontalGroup(
            modeDetailsPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modeDetailsPLayout.createSequentialGroup()
                .addGroup(modeDetailsPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton1)
                    .addGroup(modeDetailsPLayout.createSequentialGroup()
                        .addComponent(jRadioButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton2)))
                .addGap(0, 12, Short.MAX_VALUE))
            .addComponent(modeDetailsT)
        );
        modeDetailsPLayout.setVerticalGroup(
            modeDetailsPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modeDetailsPLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(modeDetailsPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(modeDetailsT, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        //workAreaP.requestFocus();
        workAreaP.setBackground(new java.awt.Color(255, 255, 255));
        workAreaP.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                workAreaPMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                workAreaPMouseMoved(evt);
            }
        });
        workAreaP.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                workAreaPFocusGained(evt);
            }
        });
        workAreaP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                workAreaPMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                workAreaPMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                workAreaPMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                workAreaPMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                workAreaPMouseReleased(evt);
            }
        });
        workAreaP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                workAreaPKeyPressed(evt);
            }
        });

        workAreaXL.setText("X:");

        workAreaYL.setText("Y:");

        javax.swing.GroupLayout workAreaPLayout = new javax.swing.GroupLayout(workAreaP);
        workAreaP.setLayout(workAreaPLayout);
        workAreaPLayout.setHorizontalGroup(
            workAreaPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(workAreaPLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(workAreaPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(workAreaYL, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(workAreaXL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        workAreaPLayout.setVerticalGroup(
            workAreaPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, workAreaPLayout.createSequentialGroup()
                .addGap(0, 323, Short.MAX_VALUE)
                .addComponent(workAreaXL, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(workAreaYL))
        );

        javax.swing.GroupLayout interactiveLayout = new javax.swing.GroupLayout(interactive);
        interactive.setLayout(interactiveLayout);
        interactiveLayout.setHorizontalGroup(
            interactiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(interactiveLayout.createSequentialGroup()
                .addComponent(modeDetailsP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(interactiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selectInteractiveP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(detailsUpdateP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(workAreaP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        interactiveLayout.setVerticalGroup(
            interactiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(interactiveLayout.createSequentialGroup()
                .addComponent(selectInteractiveP, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(workAreaP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(detailsUpdateP, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(modeDetailsP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Card.add(interactive, "card14");

        myMenu.setBackground(new java.awt.Color(204, 204, 0));

        fileM.setText("File");

        close.setText("Close");
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });
        fileM.add(close);

        myMenu.add(fileM);

        viewM.setText("View");

        normalMode.setText("Normal");
        normalMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                normalModeActionPerformed(evt);
            }
        });
        viewM.add(normalMode);

        interactiveMode.setText("Interactive");
        interactiveMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                interactiveModeActionPerformed(evt);
            }
        });
        viewM.add(interactiveMode);

        myMenu.add(viewM);

        vertexM.setText("Vertex");

        addV.setText("Add");
        addV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addVActionPerformed(evt);
            }
        });
        vertexM.add(addV);

        searchV.setText("Search");
        searchV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchVActionPerformed(evt);
            }
        });
        vertexM.add(searchV);

        deleteV.setText("Delete");
        deleteV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteVActionPerformed(evt);
            }
        });
        vertexM.add(deleteV);

        modifyV.setText("Modify");
        modifyV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyVActionPerformed(evt);
            }
        });
        vertexM.add(modifyV);

        myMenu.add(vertexM);

        edgeM.setText("Edge");

        addE.setText("Add");
        addE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEActionPerformed(evt);
            }
        });
        edgeM.add(addE);

        jMenuItem3.setText("Search");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        edgeM.add(jMenuItem3);

        deleteE.setText("Delete");
        deleteE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteEActionPerformed(evt);
            }
        });
        edgeM.add(deleteE);

        modifyE.setText("Modify");
        modifyE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyEActionPerformed(evt);
            }
        });
        edgeM.add(modifyE);

        myMenu.add(edgeM);

        impexpM.setText("Import/Export");

        loadG.setText("Load Graph");
        loadG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadGActionPerformed(evt);
            }
        });
        impexpM.add(loadG);

        exportG.setText("Export Graph");
        exportG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportGActionPerformed(evt);
            }
        });
        impexpM.add(exportG);

        myMenu.add(impexpM);

        pathM.setText("Path");

        shortestPathM.setText("Shortest Path");
        shortestPathM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shortestPathMActionPerformed(evt);
            }
        });
        pathM.add(shortestPathM);

        myMenu.add(pathM);

        setJMenuBar(myMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Card, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Card, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deleteVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteVActionPerformed
        // TODO add your handling code here:
        Card.removeAll();
        Card.repaint();
        Card.revalidate();

        String[] s=new String[vertexList.size()];
        for(int i=0;i<vertexList.size();i++){
            s[i]=vertexList.get(i);
        }
        
        deleteVertexNameC.setModel(new javax.swing.DefaultComboBoxModel<>(s));
        
        Card.add(vertexDelete);
        Card.repaint();
        Card.revalidate();
    }//GEN-LAST:event_deleteVActionPerformed

    private void addVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addVActionPerformed
        // TODO add your handling code here:
        Card.removeAll();
        Card.repaint();
        Card.revalidate();
        
        Card.add(vertexInp);
        Card.repaint();
        Card.revalidate();
    }//GEN-LAST:event_addVActionPerformed

    private void addEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEActionPerformed
        // TODO add your handling code here:
        Card.removeAll();
        Card.repaint();
        Card.revalidate();

        String[] s=new String[vertexList.size()];
        for(int i=0;i<vertexList.size();i++){
            s[i]=vertexList.get(i);
        }
        
        addEdgeFromNameC.setModel(new javax.swing.DefaultComboBoxModel<>(s));
        addEdgeToNameC.setModel(new javax.swing.DefaultComboBoxModel<>(s));
        Card.add(edgeInp);
        Card.repaint();
        Card.revalidate();
    }//GEN-LAST:event_addEActionPerformed

    private void searchVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchVActionPerformed
        // TODO add your handling code here:
        Card.removeAll();
        Card.repaint();
        Card.revalidate();
        
        String[] s=new String[vertexList.size()];
        for(int i=0;i<vertexList.size();i++){
            s[i]=vertexList.get(i);
        }
        
        searchVertexNameC.setModel(new javax.swing.DefaultComboBoxModel<>(s));
        Card.add(vertexSearch);
        Card.repaint();
        Card.revalidate();
    }//GEN-LAST:event_searchVActionPerformed

    private void modifyVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyVActionPerformed
        // TODO add your handling code here:
        Card.removeAll();
        Card.repaint();
        Card.revalidate();

        String[] s=new String[vertexList.size()];
        for(int i=0;i<vertexList.size();i++){
            s[i]=vertexList.get(i);
        }
        
        modifyVertexNameC.setModel(new javax.swing.DefaultComboBoxModel<>(s));
        
        Card.add(vertexModify);
        Card.repaint();
        Card.revalidate();
    }//GEN-LAST:event_modifyVActionPerformed

    private void deleteEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteEActionPerformed
        // TODO add your handling code here:
        Card.removeAll();
        Card.repaint();
        Card.revalidate();

         String[] s=new String[vertexList.size()];
        for(int i=0;i<vertexList.size();i++){
            s[i]=vertexList.get(i);
        }
        
        deleteEdgeFromNameC.setModel(new javax.swing.DefaultComboBoxModel<>(s));
        deleteEdgeToNameC.setModel(new javax.swing.DefaultComboBoxModel<>(s));
        Card.add(edgeDelete);
        Card.repaint();
        Card.revalidate();
    }//GEN-LAST:event_deleteEActionPerformed

    private void modifyEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyEActionPerformed
        // TODO add your handling code here:
        Card.removeAll();
        Card.repaint();
        Card.revalidate();

         String[] s=new String[vertexList.size()];
        for(int i=0;i<vertexList.size();i++){
            s[i]=vertexList.get(i);
        }
        
        modifyEdgeFromNameC.setModel(new javax.swing.DefaultComboBoxModel<>(s));
        modifyEdgeToNameC.setModel(new javax.swing.DefaultComboBoxModel<>(s));
        Card.add(edgeModify);
        Card.repaint();
        Card.revalidate();
    }//GEN-LAST:event_modifyEActionPerformed

    private void loadGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadGActionPerformed
        // TODO add your handling code here:
        Card.removeAll();
        Card.repaint();
        Card.revalidate();

        Card.add(importGraph);
        Card.repaint();
        Card.revalidate();
    }//GEN-LAST:event_loadGActionPerformed

    private void exportGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportGActionPerformed
        // TODO add your handling code here:
        Card.removeAll();
        Card.repaint();
        Card.revalidate();

        Card.add(exportGraph);
        Card.repaint();
        Card.revalidate();
    }//GEN-LAST:event_exportGActionPerformed

    private void addVertexNameTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addVertexNameTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addVertexNameTActionPerformed

    private void addVertexYTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addVertexYTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addVertexYTActionPerformed

    private void addEdgeWeightTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEdgeWeightTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addEdgeWeightTActionPerformed

    private void searchVertexBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchVertexBActionPerformed
        // TODO add your handling code here:
        int i=searchVertexNameC.getSelectedIndex();
        searchVertexResultXT.setText(Integer.toString(vList.get(i).getX()));
        searchVertexResultYT.setText(Integer.toString(vList.get(i).getY()));
        searchVertexResultEdgesT.setText(Integer.toString(numberOfEdges(i)));
        
    }//GEN-LAST:event_searchVertexBActionPerformed

    private void deleteVertexBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteVertexBActionPerformed
        // TODO add your handling code here:
        int i= deleteVertexNameC.getSelectedIndex();
        removeEdges(i);
        vList.remove(i);
        vertexList.remove(i);
        parent.remove(i);
        dist.remove(i);
        JOptionPane.showMessageDialog(null, "Vertex deleted");
        
    }//GEN-LAST:event_deleteVertexBActionPerformed

    private void deleteEdgeBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteEdgeBActionPerformed
        // TODO add your handling code here:
        int i=deleteEdgeFromNameC.getSelectedIndex();
        int j=deleteEdgeToNameC.getSelectedIndex();
        int resp=searchEdge(i,j);
        if(resp==-1){
            JOptionPane.showMessageDialog(null, "No Edge Exists");
        }
        else{
            edgeList.remove(resp);
            JOptionPane.showMessageDialog(null, "Edge Deleted");
        }
    }//GEN-LAST:event_deleteEdgeBActionPerformed

    private void modifyVertexBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyVertexBActionPerformed
        // TODO add your handling code here:
        String name=modifyVertexNameNewT.getText().trim();
        int i= (int)modifyVertexNameC.getSelectedItem();
        try{
            int x=Integer.parseInt(modifyVertexNewXT.getText().trim());
            int y=Integer.parseInt(modifyVertexNewYT.getText().trim());
            int flg=0;
            if(vertexList.contains(name)){
                JOptionPane.showMessageDialog(null, "Vertex Exists");
            }
            else {
                for (int j = 0; j < xList.size(); j++) {
                    if (xList.get(j) == x && yList.get(j) == y) {
                        JOptionPane.showMessageDialog(null, "Overlapping Vertex");
                        flg=1;
                    }
                }
            }
            
            if(flg==0){
                vList.get(i).setName(name);
                vList.get(i).setX(x);
                vList.get(i).setY(y);
                vertexList.set(i,name);
                updateEdges(i,name);
                JOptionPane.showMessageDialog(null, "Update Successful");
            }
            
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Enter Valid Details");
        }
    }//GEN-LAST:event_modifyVertexBActionPerformed

    private void modifyVertexNewYTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyVertexNewYTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_modifyVertexNewYTActionPerformed

    private void modifyVertexNameNewTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyVertexNameNewTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_modifyVertexNameNewTActionPerformed

    private void modifyEdgeBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyEdgeBActionPerformed
        // TODO add your handling code here:
        try{
            int i=(int) modifyEdgeFromNameC.getSelectedIndex();
            int j=(int) modifyEdgeToNameC.getSelectedIndex();
            int w=Integer.parseInt(modifyEdgeWeightNewT.getText().trim());

            int resp=searchEdge(i,j);
            if(resp==-1){
                JOptionPane.showMessageDialog(null, "No Edge Exists");
            }
            else{
                edgeList.get(resp).setWeight(w);
                JOptionPane.showMessageDialog(null, "Edge Updated");
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Enter Valid Weight");
        }
    }//GEN-LAST:event_modifyEdgeBActionPerformed

    private void importGraphBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importGraphBActionPerformed
        // TODO add your handling code here:
        try{
            String s=(String)importGraphPathT.getText();
            File f=new File(s);
            Scanner sc=new Scanner(f);
            int n=sc.nextInt();
            int flg=0;
            for(int i=0;i<n;i++){
                String name=sc.next();
                int x=Integer.parseInt(sc.next());
                int y=Integer.parseInt(sc.next());
                int resp=addVertex(name,x,y);
                if(resp==-1){
                    JOptionPane.showMessageDialog(null, "Multiple inputs for same Vertex");
                    flg=1;
                    break;
                }
                else if(resp==-2){
                    JOptionPane.showMessageDialog(null, "Overlapping Vertices"+name);
                    flg=1;
                    break;
                }
                else if(resp==-3){
                    JOptionPane.showMessageDialog(null, "Invalid name");
                    flg=1;
                    break;
                }
            }
            if(flg!=1){
                int m = sc.nextInt();
                for (int i = 0; i < m; i++) {
                    String from = sc.next();
                    String to = sc.next();
                    int fr, tor;
                    fr= vertexList.indexOf(from);
                    tor=vertexList.indexOf(to);
                    if(fr==-1 || tor==-1){
                        JOptionPane.showMessageDialog(null, "Vertex does not exist");
                        flg=1;
                        break;
                    }
                    int w = Integer.parseInt(sc.next());
                    int resp = addEdge(fr, tor, w);
                    if (resp ==-1) {
                        JOptionPane.showMessageDialog(null, "Multiple Inputs for same Edge");
                        flg=1;
                    }
                }
            }
            if(flg!=1){
                JOptionPane.showMessageDialog(null, "Graph import Successful");
            }
            else{
//                Scanner scc=new Scanner(f);
//                int nrem=scc.nextInt();
//                for(int i=0;i<nrem;i++){
//                    String inp=scc.next();
//                    int j=vertexList.indexOf(inp);
//                    if(j!=-1){
//                        removeEdges(j);
//                        vertexList.remove(j);
//                    }
//                }
                    vertexList.clear();
                    vList.clear();
                    edgeList.clear();
                    xList.clear();
                    yList.clear();
                    parent.clear();
                    dist.clear();
            }
            
        }
        catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null, "File not Found");
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Invalid input");
            try{
                String s = (String) importGraphPathT.getText();
                File f = new File(s);
                Scanner scc = new Scanner(f);
                int nrem = scc.nextInt();
                for (int i = 0; i < nrem; i++) {
                    String inp = scc.next();
                    int j = vertexList.indexOf(inp);
                    if (j != -1) {
                        removeEdges(j);
                        vertexList.remove(j);
                    }
                }
            }
            catch(Exception e1){
                JOptionPane.showMessageDialog(null, "File not Found");
            }
            
        }
        
        
    }//GEN-LAST:event_importGraphBActionPerformed

    private void exportGraphBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportGraphBActionPerformed
        // TODO add your handling code here:
        try{
            String pth=(String)exportGraphPathT.getText();
            FileWriter fw=new FileWriter(pth);
            fw.write("");
            fw.append(Integer.toString(vList.size())+"\n");
            ArrayList<Integer> vList1=new ArrayList<>();
//            ArrayList<String> vList2=new ArrayList<>();
            for(int i=0;i<vList.size();i++){
                vList1.add(i);
//                vList2.add(vertexList.get(i));
            }
            Collections.sort(vList1,new myC1());
            for(int i=0;i<vList.size();i++){
                fw.append(vList.get(vList1.get(i)).getName()+" "+Integer.toString(vList.get(vList1.get(vList1.get(i))).getX())+" "+Integer.toString(vList.get(vList1.get(i)).getY())+"\n");
            }
            fw.append(Integer.toString(edgeList.size())+"\n");
            ArrayList<Integer> e1=new ArrayList<>();
            for(int i=0;i<edgeList.size();i++){
                e1.add(i);
            }
            
            Collections.sort(e1,new myC2());
            for(int i=0;i<edgeList.size();i++){
                fw.append(edgeList.get(e1.get(i)).getFrom().getName()+" "+edgeList.get(e1.get(i)).getTo().getName()+" "+Integer.toString(edgeList.get(e1.get(i)).getWeight())+"\n");
            }
            JOptionPane.showMessageDialog(null, "Export Successful");
            fw.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error");
        }
    }//GEN-LAST:event_exportGraphBActionPerformed

    private void modifyEdgeWeightNewTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyEdgeWeightNewTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_modifyEdgeWeightNewTActionPerformed

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_closeActionPerformed

    private void addVertexBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addVertexBActionPerformed
        // TODO add your handling code here:
        String name=addVertexNameT.getText().trim();
        try{
            int x=Integer.parseInt(addVertexXT.getText().trim());
            int y=Integer.parseInt(addVertexYT.getText().trim());
            int resp = Graph.addVertex(name, x, y);
            switch (resp) {
                case -1:
                    JOptionPane.showMessageDialog(null, "Vertex exists");
                    break;
                case -2:
                    JOptionPane.showMessageDialog(null, "Overlapping Vertex");
                    break;
                case -3:
                    JOptionPane.showMessageDialog(null, "Enter Valid Details");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "New Vertex Added");
                    break;
            }
        }
        catch(HeadlessException | NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Enter Valid Details");
        }
        
        
    }//GEN-LAST:event_addVertexBActionPerformed

    private void addEdgeBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEdgeBActionPerformed
        // TODO add your handling code here:
        try{
            int i = (int) addEdgeFromNameC.getSelectedIndex();
            int j = (int) addEdgeToNameC.getSelectedIndex();
            int w = Integer.parseInt(addEdgeWeightT.getText().trim());
            int resp = addEdge(i, j, w);
            if (resp == -1) {
                JOptionPane.showMessageDialog(null, "Edge Already Present");
            }
            else{
                JOptionPane.showMessageDialog(null, "New Edge added");
            }
        }
        catch(HeadlessException | NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Enter Valid Details");
        }
        
    }//GEN-LAST:event_addEdgeBActionPerformed

    private void searchEdgeBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchEdgeBActionPerformed
        // TODO add your handling code here:
        int i=(int) searchEdgeFromNameC.getSelectedIndex();
        int j=(int) searchEdgeToNameC.getSelectedIndex();
        int resp=searchEdge(i,j);
        if(resp==-1){
            JOptionPane.showMessageDialog(null, "No Edge Exists");
        }
        else{
            searchEdgeResultX1T.setText(Integer.toString(edgeList.get(resp).getFrom().getX()) );
            searchEdgeResultY1T.setText(Integer.toString(edgeList.get(resp).getFrom().getY()));
            searchEdgeResultX2T.setText(Integer.toString(edgeList.get(resp).getTo().getX()) );
            searchEdgeResultY2T.setText(Integer.toString(edgeList.get(resp).getTo().getY()));
            searchEdgeResultWeightT.setText(Integer.toString(edgeList.get(resp).getWeight()));
                    
        }
    }//GEN-LAST:event_searchEdgeBActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
                Card.removeAll();
        Card.repaint();
        Card.revalidate();

         String[] s=new String[vertexList.size()];
        for(int i=0;i<vertexList.size();i++){
            s[i]=vertexList.get(i);
        }
        
        searchEdgeFromNameC.setModel(new javax.swing.DefaultComboBoxModel<>(s));
        searchEdgeToNameC.setModel(new javax.swing.DefaultComboBoxModel<>(s));
        Card.add(edgeSearch);
        Card.repaint();
        Card.revalidate();

    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void shortestPathMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shortestPathMActionPerformed
        // TODO add your handling code here:
        Card.removeAll();
        Card.repaint();
        Card.revalidate();

         String[] s=new String[vertexList.size()];
        for(int i=0;i<vertexList.size();i++){
            s[i]=vertexList.get(i);
        }
        
        pathFromNameC.setModel(new javax.swing.DefaultComboBoxModel<>(s));
        pathToNameC.setModel(new javax.swing.DefaultComboBoxModel<>(s));
        Card.add(shortestPath);
        Card.repaint();
        Card.revalidate();
    }//GEN-LAST:event_shortestPathMActionPerformed

    private void pathDistanceTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pathDistanceTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pathDistanceTActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int i=pathFromNameC.getSelectedIndex();
        int j=pathToNameC.getSelectedIndex();
//        JOptionPane.showMessageDialog(null, "before First clear");
        int resp=getPath(i,j);
//        JOptionPane.showMessageDialog(null, "First clear");
        if(resp==-1){
            JOptionPane.showMessageDialog(null, "No Path Exists");
        }
        else{
            String s=makePath(i,j);
            int d=dist.get(j);
            pathDistanceT.setText(Integer.toString(d));
            pathPathT.setText(s);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void normalModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_normalModeActionPerformed
        // TODO add your handling code here:
        Card.removeAll();
        Card.repaint();
        Card.revalidate();
        
        Card.add(blankCard);
        Card.repaint();
        Card.revalidate();
    }//GEN-LAST:event_normalModeActionPerformed

    private void interactiveModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_interactiveModeActionPerformed
        // TODO add your handling code here:
        Card.removeAll();
        Card.repaint();
        Card.revalidate();
        
        Card.add(interactive);
        Card.repaint();
        Card.revalidate();
//        workAreaP.requestFocus();
        repaint();
    }//GEN-LAST:event_interactiveModeActionPerformed

    private void modeDetailsTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modeDetailsTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_modeDetailsTActionPerformed

    private void vertexInteractiveModeRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vertexInteractiveModeRBActionPerformed
        // TODO add your handling code here:
        curSelect=-1;
//        workAreaP.requestFocus();
animatei1=-1;
animatei2=-1;
        if(i){
            detailsUpdateP.removeAll();
            detailsUpdateP.repaint();
            detailsUpdateP.revalidate();

            detailsUpdateP.add(addVertexInteractiveP);
            detailsUpdateP.repaint();
            detailsUpdateP.revalidate();
        }
        if(d){
            detailsUpdateP.removeAll();
            detailsUpdateP.repaint();
            detailsUpdateP.revalidate();

            detailsUpdateP.add(blankInteractiveP);
            detailsUpdateP.repaint();
            detailsUpdateP.revalidate();
        }
        if(m){
            detailsUpdateP.removeAll();
            detailsUpdateP.repaint();
            detailsUpdateP.revalidate();

            detailsUpdateP.add(updateVertexInteractiveP);
            detailsUpdateP.repaint();
            detailsUpdateP.revalidate();
        }
    }//GEN-LAST:event_vertexInteractiveModeRBActionPerformed

    private void workAreaPMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_workAreaPMouseMoved
        // TODO add your handling code here:
//        workAreaP.requestFocus();
      
//        System.out.println("aa");
this.requestFocus();
        workAreaXL.setText("X:"+Integer.toString(evt.getX()));
        workAreaYL.setText("Y:"+Integer.toString(evt.getY()));
//        curSelect=-1;
        if(!pathInteractiveModeRB.isSelected()){
            int x = evt.getX();
            int y = evt.getY();
            int i1=-1;
            for (int i = 0; i < xList.size(); i++) {
                if (((xList.get(i) - x) * (xList.get(i) - x) + (yList.get(i) - y) * (yList.get(i) - y)) < 75) {
                    vList.get(i).setSpc(1);
                    break;
                }
                else if(!pathInteractiveModeRB.isSelected()){
                    vList.get(i).setSpc(0);
                }
            }
            for(int i=0;i<edgeList.size();i++){
                if(edgeList.get(i).getFrom().getX()==edgeList.get(i).getTo().getX()){
                    if(abs(edgeList.get(i).getFrom().getX()-x)<5
                                && abs(edgeList.get(i).getFrom().getY()-edgeList.get(i).getTo().getY())+5>abs(edgeList.get(i).getFrom().getY()+edgeList.get(i).getTo().getY()-2*y) 
                                && abs(edgeList.get(i).getFrom().getX()-edgeList.get(i).getTo().getX())+5>abs(edgeList.get(i).getFrom().getX()+edgeList.get(i).getTo().getX()-2*x)){
                        edgeList.get(i).setSpc(1);
    //                    edgeList.add(edgeList.remove(i));
                    }
                    else if(!pathInteractiveModeRB.isSelected()){
                        edgeList.get(i).setSpc(0);
                    }
                }
                else{
                    double m=(double)(edgeList.get(i).getFrom().getY()-edgeList.get(i).getTo().getY());
                    m/=(edgeList.get(i).getFrom().getX()-edgeList.get(i).getTo().getX());
                    double c=m*edgeList.get(i).getFrom().getX()-edgeList.get(i).getFrom().getY();
                    c*=-1;
                    double num=(y-(m*x)-c);
                    num*=num;
                    num/=(1+m*m);
                    if(num<100.0
                                && abs(edgeList.get(i).getFrom().getY()-edgeList.get(i).getTo().getY())+10>abs(edgeList.get(i).getFrom().getY()+edgeList.get(i).getTo().getY()-2*y) 
                                && abs(edgeList.get(i).getFrom().getX()-edgeList.get(i).getTo().getX())+5>abs(edgeList.get(i).getFrom().getX()+edgeList.get(i).getTo().getX()-2*x)){
    //                    System.out.println(num);

                        edgeList.get(i).setSpc(1);
    //                    edgeList.add(edgeList.remove(i));
                    }
                    else if(!pathInteractiveModeRB.isSelected()){
    //                    System.out.println(num+" "+edgeList.get(i).getFrom().getName()+" "+m+" "+c);
                        edgeList.get(i).setSpc(0);
                    }
                }
            }
        }
        
        repaint();
    }//GEN-LAST:event_workAreaPMouseMoved

    private void workAreaPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_workAreaPKeyPressed
        // TODO add your handling code here:
        curSelect=-1;
//        System.out.println("asdf");
        if(evt.getKeyChar()=='i'){
            i = true;
            d = false;
            m = false;
            modeDetailsT.setText("---Insert---");
            if (vertexInteractiveModeRB.isSelected()) {
                detailsUpdateP.removeAll();
                detailsUpdateP.repaint();
                detailsUpdateP.revalidate();

                detailsUpdateP.add(addVertexInteractiveP);
                detailsUpdateP.repaint();
                detailsUpdateP.revalidate();
            } else if (edgeInteractiveModeRB.isSelected()) {
                detailsUpdateP.removeAll();
                detailsUpdateP.repaint();
                detailsUpdateP.revalidate();

                detailsUpdateP.add(addEdgeInteractiveP);
                detailsUpdateP.repaint();
                detailsUpdateP.revalidate();
            }
            repaint();
        }
        else if(evt.getKeyChar()=='d'){
            i = false;
            d = true;
            m = false;
            modeDetailsT.setText("---Delete---");
            if (vertexInteractiveModeRB.isSelected()) {
                detailsUpdateP.removeAll();
                detailsUpdateP.repaint();
                detailsUpdateP.revalidate();

                detailsUpdateP.add(blankInteractiveP);
                detailsUpdateP.repaint();
                detailsUpdateP.revalidate();
            } else if (edgeInteractiveModeRB.isSelected()) {
                detailsUpdateP.removeAll();
                detailsUpdateP.repaint();
                detailsUpdateP.revalidate();

                detailsUpdateP.add(blankInteractiveP);
                detailsUpdateP.repaint();
                detailsUpdateP.revalidate();
            }
            repaint();
        }
        else if(evt.getKeyChar()=='m'){
            i = false;
            d = true;
            m = false;
            modeDetailsT.setText("---Modify---");
            if (vertexInteractiveModeRB.isSelected()) {
                detailsUpdateP.removeAll();
                detailsUpdateP.repaint();
                detailsUpdateP.revalidate();

                detailsUpdateP.add(updateVertexInteractiveP);
                detailsUpdateP.repaint();
                detailsUpdateP.revalidate();
            } else if (edgeInteractiveModeRB.isSelected()) {
                detailsUpdateP.removeAll();
                detailsUpdateP.repaint();
                detailsUpdateP.revalidate();

                detailsUpdateP.add(updateEdgeInteractiveP);
                detailsUpdateP.repaint();
                detailsUpdateP.revalidate();
            }
            repaint();
        }
        
    }//GEN-LAST:event_workAreaPKeyPressed

    private void workAreaPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_workAreaPMouseClicked
        // TODO add your handling code here:
        curSelect=-1;
//        workAreaP.requestFocus();
requestFocus();
        if(vertexInteractiveModeRB.isSelected()){
            if(i){
                int x=evt.getX();
                int y=evt.getY();
                String name=(String)addVertexInteractiveNameT.getText().trim();
//                System.out.println(name);
                if(name.equals("")){
                    addVertexInteractiveNameL.setForeground(Color.red);
                    addVertexInteractiveNameL.setText("*Name:");
                }
                else{
                    addVertexInteractiveNameL.setForeground(Color.black);
                    addVertexInteractiveNameL.setText("Name:");
                    int resp=addVertex(name,x,y);
                }
                
                
            }
            else if(d){
                int x=evt.getX();
                int y=evt.getY();
                for(int i=0;i<xList.size();i++){
                    if(((xList.get(i)-x)*(xList.get(i)-x) + (yList.get(i)-y)*(yList.get(i)-y))<75){
                        removeEdges(i);
                        vList.remove(i);
                        vertexList.remove(i);
                        xList.remove(i);
                        yList.remove(i);
                        
                        break;
                    }
                }
            }
            else if(m){
                int x=evt.getX();
                int y=evt.getY();
                for(int i=0;i<xList.size();i++){
                    if(((xList.get(i)-x)*(xList.get(i)-x) + (yList.get(i)-y)*(yList.get(i)-y))<75){
                        updateVertexInteractiveNameT.setText(vertexList.get(i));
                        curSelect=i;
                        break;
                    }
                }
            }
        }
        if(edgeInteractiveModeRB.isSelected()){
            
            if(d){
                int x = evt.getX();
                int y = evt.getY();
                int i1=-1,i2=-1;
                for (int i = 0; i < edgeList.size(); i++) {
                    if (edgeList.get(i).getFrom().getX() == edgeList.get(i).getTo().getX()) {
                        if ( abs(edgeList.get(i).getFrom().getX() - x) < 5 
                                && abs(edgeList.get(i).getFrom().getY()-edgeList.get(i).getTo().getY())+10>abs(edgeList.get(i).getFrom().getY()+edgeList.get(i).getTo().getY()-2*y) 
                                && abs(edgeList.get(i).getFrom().getX()-edgeList.get(i).getTo().getX())+5>abs(edgeList.get(i).getFrom().getX()+edgeList.get(i).getTo().getX()-2*x)) {
//                            edgeList.get(i).setSpc(1);
//                            edgeList.add(edgeList.remove(i));
                              if(i1==-1){
                                  i1=i;
                              }
                              else{
                                  i2=i;
                              }
                            
                        } else {
                            ;
//                            edgeList.get(i).setSpc(0);
                        }
                    } else {
                        double m = (double) (edgeList.get(i).getFrom().getY() - edgeList.get(i).getTo().getY());
                        m /= (edgeList.get(i).getFrom().getX() - edgeList.get(i).getTo().getX());
                        double c = m * edgeList.get(i).getFrom().getX() - edgeList.get(i).getFrom().getY();
                        c *= -1;
                        double num = (y - (m * x) - c);
                        num *= num;
                        num /= (1 + m * m);
                        if (num < 100.0 
                                && abs(edgeList.get(i).getFrom().getY()-edgeList.get(i).getTo().getY())+10>abs(edgeList.get(i).getFrom().getY()+edgeList.get(i).getTo().getY()-2*y)/2.0 
                                && abs(edgeList.get(i).getFrom().getX()-edgeList.get(i).getTo().getX())+10>abs(edgeList.get(i).getFrom().getX()+edgeList.get(i).getTo().getX()-2*x)/2.0) {
//                    System.out.println(num);
                           if(i1==-1){
                                  i1=i;
                              }
                              else{
                                  i2=i;
                              }
//                            edgeList.get(i).setSpc(1);
//                             edgeList.add(edgeList.remove(i));
                        } else {
                            ;
//                    System.out.println(num+" "+edgeList.get(i).getFrom().getName()+" "+m+" "+c);
//                            edgeList.get(i).setSpc(0);
                        }
                    }
                }
                if(i1!=-1){
                    if(i2!=-1){
                        double d1,d2;
                        d1=(edgeList.get(i1).getFrom().getY()-y)*(edgeList.get(i1).getFrom().getY()-y)+((edgeList.get(i1).getFrom().getX()-x)*(edgeList.get(i1).getFrom().getX()-x));
                        d2=(edgeList.get(i1).getTo().getY()-y)*(edgeList.get(i1).getTo().getY()-y)+((edgeList.get(i1).getTo().getX()-x)*(edgeList.get(i1).getTo().getX()-x));
                        if(d1>d2){
                            edgeList.remove(i1);
                        }
                        else{
                            edgeList.remove(i2);
                        }
                    }
                    else{
                        edgeList.remove(i1);
                    }
                }
            }
        }
        if(pathInteractiveModeRB.isSelected()){
            if(pathi1!=-1){
                int x = evt.getX();
                int y = evt.getY();
                for (int i = 0; i < xList.size(); i++) {
                    if (i!=pathi1&& ((xList.get(i) - x) * (xList.get(i) - x) + (yList.get(i) - y) * (yList.get(i) - y)) < 75) {
                        pathi2=i;
                        vList.get(i).setSpc(1);
                        break;
                    }
                    if(i!=pathi1 && i!=pathi2){
                        vList.get(i).setSpc(0);
                    }
                }
                
                if(pathi2!=-1 && pathi2!=pathi1){
                    int resp=getPath(pathi1,pathi2);
                    if(resp==-1){
                        pathi1=-1;
                        pathi2=-1;
                        JOptionPane.showMessageDialog(null, "No Path Exists");
                    }
                    else{
                        makePath1(pathi1,pathi2);
                        animatei1=pathi1;
                        animatei2=pathi2;
                        
//                        if(workAreaP instanceof jP){
//                            System.out.println("aaaaaaaaaaaa");
//                        }
                        animate();
                        
//                        ((jP)this.workAreaP).animate();
                        
                    }
                }
                pathi1=-1;
                pathi2=-1;
            }
            else{
                int x=evt.getX();
                int y=evt.getY();
                for(int i=0;i<xList.size();i++){
                    if(((xList.get(i)-x)*(xList.get(i)-x) + (yList.get(i)-y)*(yList.get(i)-y))<75){
//                        updateVertexInteractiveNameT.setText(vertexList.get(i));
                        pathi1=i;
//                        System.out.println(pathi1+"asdfsd");
                        vList.get(i).setSpc(1);
                        break;
                    }
                    else{
                        vList.get(i).setSpc(0);
                    }
                }
            }
        }
        repaint();
    }//GEN-LAST:event_workAreaPMouseClicked

    private void deleteVertexNameCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteVertexNameCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteVertexNameCActionPerformed

    private void updateVertexInteractiveNameBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateVertexInteractiveNameBActionPerformed
        // TODO add your handling code here:
        if(curSelect!=-1){
            String s=(String)updateVertexInteractiveNameT.getText();
            for(int i=0;i<edgeList.size();i++){
                if(vertexList.indexOf(edgeList.get(i).getFrom().getName())==curSelect){
                    edgeList.get(i).getFrom().setName(s);
                }
                if(vertexList.indexOf(edgeList.get(i).getTo().getName())==curSelect){
                    edgeList.get(i).getTo().setName(s);
                }
            }
            vertexList.set(curSelect,s);
            vList.get(curSelect).setName(s);
        }
        
    }//GEN-LAST:event_updateVertexInteractiveNameBActionPerformed

    private void edgeInteractiveModeRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edgeInteractiveModeRBActionPerformed
        // TODO add your handling code here:
        animatei1=-1;
        animatei2=-1;
        curSelect=-1;
        if(i){
            detailsUpdateP.removeAll();
            detailsUpdateP.repaint();
            detailsUpdateP.revalidate();

            detailsUpdateP.add(addEdgeInteractiveP);
            detailsUpdateP.repaint();
            detailsUpdateP.revalidate();
        }
        if(m){
            detailsUpdateP.removeAll();
            detailsUpdateP.repaint();
            detailsUpdateP.revalidate();

            detailsUpdateP.add(updateEdgeInteractiveP);
            detailsUpdateP.repaint();
            detailsUpdateP.revalidate();
        }
        if(d){
            detailsUpdateP.removeAll();
            detailsUpdateP.repaint();
            detailsUpdateP.revalidate();

            detailsUpdateP.add(blankInteractiveP);
            detailsUpdateP.repaint();
            detailsUpdateP.revalidate();
        }
        repaint();
    }//GEN-LAST:event_edgeInteractiveModeRBActionPerformed

    private void workAreaPMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_workAreaPMousePressed
        // TODO add your handling code here:
        curSelect=-1;
//        workAreaP.requestFocus();
requestFocus();
        if(vertexInteractiveModeRB.isSelected()){
            int x = evt.getX();
            int y = evt.getY();
            for (int i = 0; i < xList.size(); i++) {
                if (((xList.get(i) - x) * (xList.get(i) - x) + (yList.get(i) - y) * (yList.get(i) - y)) < 75) {
                    updateVertexInteractiveNameT.setText(vertexList.get(i));
                    curSelect = i;
                    break;
                }
            }
        }
        if(edgeInteractiveModeRB.isSelected()){
            if(i){
                int x = evt.getX();
                int y = evt.getY();
                for (int i = 0; i < xList.size(); i++) {
                    if (((xList.get(i) - x) * (xList.get(i) - x) + (yList.get(i) - y) * (yList.get(i) - y)) < 75) {
                        srcX=x;
                        srcY=y;
                        curSelect=i;
                        break;
                    }
                }
            }
        }
        repaint();
    }//GEN-LAST:event_workAreaPMousePressed

    private void workAreaPMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_workAreaPMouseReleased
        // TODO add your handling code here:
        requestFocus();
        if(edgeInteractiveModeRB.isSelected()){
            if(i){
                for(int i=0;i<xList.size();i++){
                    if(i!=curSelect && (xList.get(i)-evt.getX())*(xList.get(i)-evt.getX())+(yList.get(i)-evt.getY())*(yList.get(i)-evt.getY())<75){
                        try{
                            int w = Integer.parseInt(addEdgeInteractiveWeightT.getText());
                            addEdge(curSelect,i,w);
                        }
                        catch(Exception e){
                            ;
                        }
                        
                    }
                }
                srcX=-1;
                srcY=-1;
                dragX=-1;
                dragY=-1;
            }
        }
        else if(curSelect!=-1){
//            System.out.println(Integer.toString(vList.get(curSelect).getX()));
            updateEdges(curSelect,vList.get(curSelect).getName());
            vList.get(curSelect).setSpc(0);
        }
        curSelect=-1;
        repaint();
//        workAreaP.requestFocus();
    }//GEN-LAST:event_workAreaPMouseReleased

    private void workAreaPMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_workAreaPMouseDragged
        // TODO add your handling code here:
//        workAreaP.requestFocus();
        requestFocus();
        if (vertexInteractiveModeRB.isSelected()) {
            if(m){
                int x = evt.getX();
                int y = evt.getY();
                String name="";
//                for (int i = 0; i < xList.size(); i++) {
//                    if (((xList.get(i) - x) * (xList.get(i) - x) + (yList.get(i) - y) * (yList.get(i) - y)) < 75) {
//                        name=vertexList.get(i);
//                        vList.get(i).setX(x);
//                        vList.get(i).setY(y);
//                        updateEdges(i,name);
//                        break;
//                    }
//                }
                int flg=0;
                for (int j = 0; j < xList.size(); j++) {
                    if (j!=curSelect && xList.get(j) == x && yList.get(j) == y) {
                        flg=1;
                    }
                }
                if(flg==0){
                    vList.get(curSelect).setX(x);
                    vList.get(curSelect).setY(y);
                    xList.set(curSelect, x);
                    yList.set(curSelect, y);
                    updateEdges(curSelect, vList.get(curSelect).getName());
                }
                
            }
            
        }
        if(edgeInteractiveModeRB.isSelected()){
            if(i){
                if(curSelect!=-1){
                    int x = evt.getX();
                    int y = evt.getY();
                    dragX=x;
                    dragY=y;
                }
            }
        }
        repaint();
    }//GEN-LAST:event_workAreaPMouseDragged

    private void workAreaPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_workAreaPMouseEntered
        // TODO add your handling code here:
//        workAreaP.requestFocus();
        requestFocus();
    }//GEN-LAST:event_workAreaPMouseEntered

    private void workAreaPMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_workAreaPMouseExited
        // TODO add your handling code here:
//        workAreaP.requestFocus();
         requestFocus();
    }//GEN-LAST:event_workAreaPMouseExited

    private void workAreaPFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_workAreaPFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_workAreaPFocusGained

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
//        requestFocus();
        curSelect=-1;
        i=true;
        d=false;
        m=false;
        modeDetailsT.setText("---Insert---");
        if(vertexInteractiveModeRB.isSelected()){
            detailsUpdateP.removeAll();
            detailsUpdateP.repaint();
            detailsUpdateP.revalidate();

            detailsUpdateP.add(addVertexInteractiveP);
            detailsUpdateP.repaint();
            detailsUpdateP.revalidate();
        }
        else if(edgeInteractiveModeRB.isSelected()){
            detailsUpdateP.removeAll();
            detailsUpdateP.repaint();
            detailsUpdateP.revalidate();

            detailsUpdateP.add(addEdgeInteractiveP);
            detailsUpdateP.repaint();
            detailsUpdateP.revalidate();
        }
        repaint();
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
        curSelect=-1;
        i=false;
        d=true;
        m=false;
        modeDetailsT.setText("---Delete---");
        if(vertexInteractiveModeRB.isSelected()){
            detailsUpdateP.removeAll();
            detailsUpdateP.repaint();
            detailsUpdateP.revalidate();

            detailsUpdateP.add(blankInteractiveP);
            detailsUpdateP.repaint();
            detailsUpdateP.revalidate();
        }
        else if(edgeInteractiveModeRB.isSelected()){
            detailsUpdateP.removeAll();
            detailsUpdateP.repaint();
            detailsUpdateP.revalidate();

            detailsUpdateP.add(blankInteractiveP);
            detailsUpdateP.repaint();
            detailsUpdateP.revalidate();
        }
        repaint();
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
        curSelect = -1;
        i = false;
        d = false;
        m = true;
        modeDetailsT.setText("---Modify---");
        if (vertexInteractiveModeRB.isSelected()) {
            detailsUpdateP.removeAll();
            detailsUpdateP.repaint();
            detailsUpdateP.revalidate();

            detailsUpdateP.add(updateVertexInteractiveP);
            detailsUpdateP.repaint();
            detailsUpdateP.revalidate();
        } else if (edgeInteractiveModeRB.isSelected()) {
            detailsUpdateP.removeAll();
            detailsUpdateP.repaint();
            detailsUpdateP.revalidate();

            detailsUpdateP.add(updateEdgeInteractiveP);
            detailsUpdateP.repaint();
            detailsUpdateP.revalidate();
        }
        repaint();
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
         curSelect=-1;
    
//        System.out.println("asdf");
//        if(evt.getKeyChar()=='p'){
//            if(pathInteractiveModeRB.isSelected()){
//                System.out.println("aaaaaaaaaaa");
//                animate();
//            }
//        }
        if(evt.getKeyChar()=='i'){
            i = true;
            d = false;
            m = false;
            modeDetailsT.setText("---Insert---");
            if (vertexInteractiveModeRB.isSelected()) {
                detailsUpdateP.removeAll();
                detailsUpdateP.repaint();
                detailsUpdateP.revalidate();

                detailsUpdateP.add(addVertexInteractiveP);
                detailsUpdateP.repaint();
                detailsUpdateP.revalidate();
            } else if (edgeInteractiveModeRB.isSelected()) {
                detailsUpdateP.removeAll();
                detailsUpdateP.repaint();
                detailsUpdateP.revalidate();

                detailsUpdateP.add(addEdgeInteractiveP);
                detailsUpdateP.repaint();
                detailsUpdateP.revalidate();
            }
            repaint();
        }
        else if(evt.getKeyChar()=='d'){
            i = false;
            d = true;
            m = false;
            modeDetailsT.setText("---Delete---");
            if (vertexInteractiveModeRB.isSelected()) {
                detailsUpdateP.removeAll();
                detailsUpdateP.repaint();
                detailsUpdateP.revalidate();

                detailsUpdateP.add(blankInteractiveP);
                detailsUpdateP.repaint();
                detailsUpdateP.revalidate();
            } else if (edgeInteractiveModeRB.isSelected()) {
                detailsUpdateP.removeAll();
                detailsUpdateP.repaint();
                detailsUpdateP.revalidate();

                detailsUpdateP.add(blankInteractiveP);
                detailsUpdateP.repaint();
                detailsUpdateP.revalidate();
            }
            repaint();
        }
        else if(evt.getKeyChar()=='m'){
            i = false;
            d = true;
            m = false;
            modeDetailsT.setText("---Modify---");
            if (vertexInteractiveModeRB.isSelected()) {
                detailsUpdateP.removeAll();
                detailsUpdateP.repaint();
                detailsUpdateP.revalidate();

                detailsUpdateP.add(updateVertexInteractiveP);
                detailsUpdateP.repaint();
                detailsUpdateP.revalidate();
            } else if (edgeInteractiveModeRB.isSelected()) {
                detailsUpdateP.removeAll();
                detailsUpdateP.repaint();
                detailsUpdateP.revalidate();

                detailsUpdateP.add(updateEdgeInteractiveP);
                detailsUpdateP.repaint();
                detailsUpdateP.revalidate();
            }
            repaint();
        }
        
    }//GEN-LAST:event_formKeyPressed

    private void pathInteractiveModeRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pathInteractiveModeRBActionPerformed
        // TODO add your handling code here:
        detailsUpdateP.removeAll();
        detailsUpdateP.repaint();
        detailsUpdateP.revalidate();
        
        detailsUpdateP.add(pathInteractiveP);
        detailsUpdateP.repaint();
        detailsUpdateP.revalidate();        
    }//GEN-LAST:event_pathInteractiveModeRBActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
//        if(pathi1!=-1&& pathi2!=-1){
//            animate();
//        }
pathi1=-1;
pathi2=-1;
animatei1=-1;
animatei2=-1;
        for (vertex vList1 : vList) {
            vList1.setSpc(0);
        }
        for(edge e:edgeList){
            e.setSpc(0);
        }
        repaint();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void animateInteractivePlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_animateInteractivePlusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_animateInteractivePlusActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(myFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(myFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(myFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(myFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new myFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Card;
    private javax.swing.JMenuItem addE;
    private javax.swing.JButton addEdgeB;
    private javax.swing.JComboBox<String> addEdgeFromNameC;
    private javax.swing.JLabel addEdgeFromNameL;
    private javax.swing.JPanel addEdgeInteractiveP;
    private javax.swing.JLabel addEdgeInteractiveWeightL;
    private javax.swing.JTextField addEdgeInteractiveWeightT;
    private javax.swing.JLabel addEdgeL;
    private javax.swing.JComboBox<String> addEdgeToNameC;
    private javax.swing.JLabel addEdgeToNameL;
    private javax.swing.JLabel addEdgeWeightL;
    private javax.swing.JTextField addEdgeWeightT;
    private javax.swing.JMenuItem addV;
    private javax.swing.JButton addVertexB;
    private javax.swing.JLabel addVertexInteractiveNameL;
    private javax.swing.JTextField addVertexInteractiveNameT;
    private javax.swing.JPanel addVertexInteractiveP;
    private javax.swing.JLabel addVertexL;
    private javax.swing.JLabel addVertexNameL;
    private javax.swing.JTextField addVertexNameT;
    private javax.swing.JLabel addVertexXL;
    private javax.swing.JTextField addVertexXT;
    private javax.swing.JLabel addVertexYL;
    private javax.swing.JTextField addVertexYT;
    private javax.swing.JRadioButton animateInteractiveCircle;
    private javax.swing.JRadioButton animateInteractiveCross;
    private javax.swing.JRadioButton animateInteractivePlus;
    private javax.swing.JRadioButton animateInteractiveSquare;
    private javax.swing.JRadioButton animateInteractiveTriangle;
    private javax.swing.JPanel blankCard;
    private javax.swing.JPanel blankInteractiveP;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JMenuItem close;
    private javax.swing.JMenuItem deleteE;
    private javax.swing.JButton deleteEdgeB;
    private javax.swing.JComboBox<String> deleteEdgeFromNameC;
    private javax.swing.JLabel deleteEdgeFromNameL;
    private javax.swing.JLabel deleteEdgeL;
    private javax.swing.JComboBox<String> deleteEdgeToNameC;
    private javax.swing.JLabel deleteEdgeToNameL;
    private javax.swing.JMenuItem deleteV;
    private javax.swing.JButton deleteVertexB;
    private javax.swing.JLabel deleteVertexL;
    private javax.swing.JComboBox<String> deleteVertexNameC;
    private javax.swing.JLabel deleteVertexNameL;
    private javax.swing.JPanel detailsUpdateP;
    private javax.swing.JPanel edgeDelete;
    private javax.swing.JPanel edgeInp;
    private javax.swing.JRadioButton edgeInteractiveModeRB;
    private javax.swing.JMenu edgeM;
    private javax.swing.JPanel edgeModify;
    private javax.swing.JPanel edgeSearch;
    private javax.swing.JMenuItem exportG;
    private javax.swing.JPanel exportGraph;
    private javax.swing.JButton exportGraphB;
    private javax.swing.JLabel exportGraphL;
    private javax.swing.JLabel exportGraphPathL;
    private javax.swing.JTextField exportGraphPathT;
    private javax.swing.JMenu fileM;
    private javax.swing.JMenu impexpM;
    private javax.swing.JPanel importGraph;
    private javax.swing.JButton importGraphB;
    private javax.swing.JLabel importGraphL;
    private javax.swing.JLabel importGraphPathL;
    private javax.swing.JTextField importGraphPathT;
    private javax.swing.JPanel interactive;
    private javax.swing.ButtonGroup interactiveBG;
    private javax.swing.JMenuItem interactiveMode;
    private javax.swing.JLabel interactiveModeL;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JMenuItem loadG;
    private javax.swing.JPanel modeDetailsP;
    private javax.swing.JTextField modeDetailsT;
    private javax.swing.JMenuItem modifyE;
    private javax.swing.JButton modifyEdgeB;
    private javax.swing.JComboBox<String> modifyEdgeFromNameC;
    private javax.swing.JLabel modifyEdgeFromNameL;
    private javax.swing.JLabel modifyEdgeL;
    private javax.swing.JComboBox<String> modifyEdgeToNameC;
    private javax.swing.JLabel modifyEdgeToNameL;
    private javax.swing.JLabel modifyEdgeWeightNewL;
    private javax.swing.JTextField modifyEdgeWeightNewT;
    private javax.swing.JMenuItem modifyV;
    private javax.swing.JButton modifyVertexB;
    private javax.swing.JLabel modifyVertexL;
    private javax.swing.JComboBox<String> modifyVertexNameC;
    private javax.swing.JLabel modifyVertexNameL;
    private javax.swing.JLabel modifyVertexNameNewL;
    private javax.swing.JTextField modifyVertexNameNewT;
    private javax.swing.JLabel modifyVertexNewXL;
    private javax.swing.JTextField modifyVertexNewXT;
    private javax.swing.JLabel modifyVertexNewYL;
    private javax.swing.JTextField modifyVertexNewYT;
    private javax.swing.JMenuBar myMenu;
    private javax.swing.JMenuItem normalMode;
    private javax.swing.JTextField pathDistanceT;
    private javax.swing.JComboBox<String> pathFromNameC;
    private javax.swing.JLabel pathFromNameL;
    private javax.swing.JRadioButton pathInteractiveModeRB;
    private javax.swing.JPanel pathInteractiveP;
    private javax.swing.JLabel pathL;
    private javax.swing.JMenu pathM;
    private javax.swing.JTextField pathPathT;
    private javax.swing.JComboBox<String> pathToNameC;
    private javax.swing.JLabel pathtoNameL;
    private javax.swing.JButton searchEdgeB;
    private javax.swing.JLabel searchEdgeDetailsL;
    private javax.swing.JComboBox<String> searchEdgeFromNameC;
    private javax.swing.JLabel searchEdgeFromNameL;
    private javax.swing.JLabel searchEdgeResultWeightL;
    private javax.swing.JTextField searchEdgeResultWeightT;
    private javax.swing.JLabel searchEdgeResultX1L;
    private javax.swing.JTextField searchEdgeResultX1T;
    private javax.swing.JLabel searchEdgeResultX2L;
    private javax.swing.JTextField searchEdgeResultX2T;
    private javax.swing.JLabel searchEdgeResultY1L;
    private javax.swing.JTextField searchEdgeResultY1T;
    private javax.swing.JLabel searchEdgeResultY2L;
    private javax.swing.JTextField searchEdgeResultY2T;
    private javax.swing.JComboBox<String> searchEdgeToNameC;
    private javax.swing.JLabel searchEdgeToNameL;
    private javax.swing.JMenuItem searchV;
    private javax.swing.JButton searchVertexB;
    private javax.swing.JLabel searchVertexDetailsL;
    private javax.swing.JLabel searchVertexL;
    private javax.swing.JLabel searchVertexL1;
    private javax.swing.JComboBox<String> searchVertexNameC;
    private javax.swing.JLabel searchVertexNameL;
    private javax.swing.JLabel searchVertexResultEdgesL;
    private javax.swing.JTextField searchVertexResultEdgesT;
    private javax.swing.JLabel searchVertexResultXL;
    private javax.swing.JTextField searchVertexResultXT;
    private javax.swing.JLabel searchVertexResultYL;
    private javax.swing.JTextField searchVertexResultYT;
    private javax.swing.JPanel selectInteractiveP;
    private javax.swing.JPanel shortestPath;
    private javax.swing.JMenuItem shortestPathM;
    private javax.swing.JButton updateEdgeInteractiveModeWeightB;
    private javax.swing.JPanel updateEdgeInteractiveP;
    private javax.swing.JLabel updateEdgeInteractiveWeightL;
    private javax.swing.JTextField updateEdgeInteractiveWeightT;
    private javax.swing.JButton updateVertexInteractiveNameB;
    private javax.swing.JLabel updateVertexInteractiveNameL;
    private javax.swing.JTextField updateVertexInteractiveNameT;
    private javax.swing.JPanel updateVertexInteractiveP;
    private javax.swing.JPanel vertexDelete;
    private javax.swing.JPanel vertexInp;
    private javax.swing.JRadioButton vertexInteractiveModeRB;
    private javax.swing.JMenu vertexM;
    private javax.swing.JPanel vertexModify;
    private javax.swing.JPanel vertexSearch;
    private javax.swing.JMenu viewM;
    private javax.swing.JPanel workAreaP;
    private javax.swing.JLabel workAreaXL;
    private javax.swing.JLabel workAreaYL;
    // End of variables declaration//GEN-END:variables
}
