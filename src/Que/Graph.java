/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Que;
import java.util.*;
import java.io.*;
import java.lang.*;
/**
 *
 * @author pal_0123
 */
class vertex{
    private String name;
    private int x,y;
    private int spc;

    public vertex(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
        spc=0;
    }

    public void setSpc(int spc) {
        this.spc = spc;
    }

    public int getSpc() {
        return spc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
}

class edge{
    private vertex from,to;
    private int weight;
    private int spc;
    public void setFrom(vertex from) {
        this.from = from;
        
    }

    public void setTo(vertex to) {
        this.to = to;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public vertex getFrom() {
        return from;
    }

    public vertex getTo() {
        return to;
    }

    public int getWeight() {
        return weight;
    }

    public void setSpc(int spc) {
        this.spc = spc;
    }

    public int getSpc() {
        return spc;
    }
    
    public edge(vertex from, vertex to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
        spc=0;
    }
    
}

class Node implements Comparator<Node>{
    public int w,from,to;
    public Node(){
        ;
    }

    public Node(int w, int from, int to) {
        this.w = w;
        this.from = from;
        this.to = to;
    }
    
    public int compare(Node n1,Node n2){
        if(n1.w-n2.w==0){
            if(n1.from-n2.from==0){
                return n1.to-n2.to;
            }
            return n1.from-n2.from;
        }
        return n1.w-n2.w;
    }
    
    
}



public class Graph {
//       static ArrayList<ArrayList <edge> > adjList;
       static ArrayList<String> vertexList=new ArrayList<>();
       static ArrayList<vertex> vList=new ArrayList<>();
       static ArrayList<edge> edgeList=new ArrayList<>(); 
       static ArrayList<Integer> xList=new ArrayList<>();
       static ArrayList<Integer> yList=new ArrayList<>();
       static ArrayList<Integer> dist=new ArrayList<>();
       static ArrayList<Integer> parent=new ArrayList<>();
       static int dragX = -1, dragY = -1;
       static int srcX = -1, srcY = -1;
       static int pathi1=-1,pathi2=-1;
       static int animatei1=-1,animatei2=-1;
       static int tempx=-1,tempy=-1;
       static int sqrx=-1,sqry=-1;
       static int trix=-1,triy=-1;
       static int plsx=-1,plsy=-1;
       static int crsx=-1,crsy=-1;
       static int getPath(int i,int j){
            PriorityQueue<Node> pq;
            pq=new PriorityQueue<>(edgeList.size(),new Node());
            pq.add(new Node(0,-1,i));
            for(int k=0;k<vertexList.size();k++){
                dist.set(k, Integer.MAX_VALUE);
                parent.set(k,-1);
            }
            ArrayList<Integer> settled=new ArrayList<>();
            while(pq.size()!=0 && settled.size()!=vertexList.size()){
                Node cur=pq.remove();
                if(settled.contains(cur.to)){
                    continue;
                }
                settled.add(cur.to);
                for(int k=0;k<edgeList.size();k++){
                    if(vertexList.indexOf(edgeList.get(k).getFrom().getName())==cur.to && (!settled.contains(vertexList.indexOf(edgeList.get(k).getTo().getName())))){
                        pq.add(new Node(edgeList.get(k).getWeight()+cur.w,cur.to,vertexList.indexOf(edgeList.get(k).getTo().getName())));
//                        if(vertexList.indexOf(edgeList.get(k).get))
                    }
                }
                
                if(cur.to!=i){
//                    System.out.println(cur.to+" "+cur.from);
                    
                    parent.set(cur.to,cur.from);
                }
                else{
//                    System.out.println(cur.to+" "+cur.from);
                    parent.set(cur.to,cur.to);
                }
                
                dist.set(cur.to, cur.w);
            }
            return parent.get(j);
            
       }
       static String makePath(int i,int j){
           if(i==j){
               return vertexList.get(i);
           }
           String s=makePath(i,parent.get(j));
           s=s+" ->"+vertexList.get(j);
           return s;
       }
       
       static void makePath1(int i,int j){
           if(i==j){
               return ;
           }
           makePath1(i,parent.get(j));
           for(int k=0;k<edgeList.size();k++){
               if(edgeList.get(k).getFrom().getName().equals(vertexList.get(parent.get(j)))
                       && edgeList.get(k).getTo().getName().equals(vertexList.get(j))){
                   edgeList.get(k).setSpc(1);
//                   System.out.println("asdfdfsadfdgf   "+k);
//                   break;
               }
               
           }
       }
       
       static int searchEdge(int i,int j){
           for (int k=0;k<edgeList.size();k++){
               if(edgeList.get(k).getFrom().getName().equals(vertexList.get(i))
                       &&edgeList.get(k).getTo().getName().equals(vertexList.get(j))){
                   return k;
               }
           }
           return -1;
       }
       
       static void updateEdges(int i,String name){
           for(int j=0;j<edgeList.size();j++){
               if(edgeList.get(j).getFrom().getName().equals(name)){
                   edgeList.get(j).setFrom(vList.get(i));
               }
               if(edgeList.get(j).getTo().getName().equals(name)){
                   edgeList.get(j).setTo(vList.get(i));
               }
           }
       }
       
       static int addEdge(int i,int j,int w){
           for (int k=0;k<edgeList.size();k++){
               if(edgeList.get(k).getFrom().getName().equals(vertexList.get(i))
                       &&edgeList.get(k).getTo().getName().equals(vertexList.get(j))){
                   return -1;
               }
//               if(edgeList.get(k).getFrom().getName().equals(vertexList.get(j))
//                       &&edgeList.get(k).getTo().getName().equals(vertexList.get(i))){
//                   return -1;
//               }
           }
           edgeList.add(new edge(vList.get(i),vList.get(j),w));
//           System.out.println(vList.get(i).getX()+" "+edgeList.get(edgeList.size()-1).getFrom().getX());
           return 1;
       }
       
       static int numberOfEdges(int i){
           int count=0;
           for (int j=0;j<edgeList.size();j++){
               if(edgeList.get(j).getFrom().getName().equals(vertexList.get(i))){
                   count++;
               }
           }
           return count;
       }
       
       static void removeEdges(int i){
           ArrayList<Integer> torem=new ArrayList<>();
           
           for(int j=0;j<edgeList.size();j++){
               if(edgeList.get(j).getFrom().getName().equals(vertexList.get(i)) 
                       || edgeList.get(j).getTo().getName().equals(vertexList.get(i))){
                   torem.add(j);
//                   System.out.println(j);
               }
               else{
                   
               }
           }
           for(int j=0;j<torem.size();j++){
               
               edgeList.remove(torem.get(j)-j);
           }
                   
       }
       
       static int addVertex(String name,int x,int y){
           if(vertexList.contains(name)){
               return -1;
           }
           for(int i=0;i<xList.size();i++){
               if(((xList.get(i)-x)*(xList.get(i)-x) + (yList.get(i)-y)*(yList.get(i)-y))<75){
                   return -2;
               }
           }
           if(name.equals("")){
               return -3;
           }
           vertex v=new vertex(name,x,y);
           vertexList.add(name);
           xList.add(x);
           yList.add(y);
           vList.add(v);
           parent.add(-1);
           dist.add(Integer.MAX_VALUE);
           return 1;
                   
           
       }
       
}
