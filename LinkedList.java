/*
Programmer: Daniel Rojas
Class: Comp 182, Summer 2013
*/

   import java.util.*; 
   public class LinkedList<T> { 
      private class Node<T> { 
         private T value; 
         private Node<T> next;
      	
         public Node(T v) {
            value = v;
            next = null;
         	 
         } 
      	
         public T getValue() { 
            return value; 
         } 
      	
         public void setValue(T v) {
            value = v;
         } 
      	
         public void setNext(Node<T> n) { 
            next = n;
         } 
      	
         public Node<T> getNext() { 
            return next; 
         } 
      		
         public String toString() { 
            return ""; 
         } 
      } 
   	
      private Node<T> head; 
      private int sz; //size of node
   
   	//infrastructure
      public LinkedList() {
         head = null; 
         sz = 0;
      } 
   	
      public String toString() {
         String s;
      	 
         if(head == null)
            return "empty"; 
         else 
            s = "";
      		
         Node<T> m = head;
      	
         while(m != null){
            s = s + m.getValue().toString();
            s = s + m.toString();
            s = s + " ";
            m = m.getNext();
         }
         return s;				
      } 
   	
      public boolean isValidIntervalPosition(int p) { 
         return p >= 0 && p <= size();
      } 
      public boolean isValidNodePosition(int p) { 
         return p >= 0 && p < size();
      } 
      public boolean isEmpty() { 
         if(head == null)
            return true; 
         else
            return false;
      } 
   	
      public int size() { 
         return sz; 
      }
   	
      public void add(int index, T v) {
         if(isValidIntervalPosition(index)) {
            Node<T> n = new Node<T>(v);
            if(index == 0) {
               n.setNext(head);
               head = n;
            }
            else {
               Node<T> m = head;
               int count = 1;
               while(count < index) {
                  m = m.getNext();
                  count++;
               }
            	
               n.setNext(m.getNext());
               m.setNext(n);
            } 
         
            sz++;
         }
      }
   	
      public void add(T v) {
         add(size(), v);
      } 
   	
      public T get(int index) {  
         Node<T> m = head;
         if(isValidNodePosition(index)) {
            int count = 0;
            while(count < index) {
               m = m.getNext();
               count++;
            }
         	
         }
         return m.getValue();
      } 
   	
      public void set(int index, T v) {
         Node<T> m = head;
         if(isValidNodePosition(index)) {
            int count = 0;
            while(count < index) {
               m = m.getNext();
               count++;
            }
         	
            m.setValue(v);
         }
      } 
       
      public T remove(int index) { 
         Node<T> curr = head;
         Node<T> prev = null;
      	
         if(isValidNodePosition(index)) {
            if(index == 0){
               prev = curr;
               curr = curr.next;
               head = curr;
               sz--;
               return prev.value;
            }
            else {
               int count = 0;
               while(count < index) {
                  prev = curr;
                  curr = curr.next;
                  count++;
               }
            }
         	
            prev.next = prev.next.next;
            sz--;
         
         }
         return curr.getValue();
      } 
   	
       
      public LinkedList<T> remove(int start, int stop) { 
         LinkedList<T> temp = new LinkedList<T>();
         if(isValidNodePosition(start) && isValidNodePosition(stop) && stop >= start){
            int count = start;
            while(count <= stop){
               temp.set(count, remove(start));
					count++;
            }
         	
         }
         return temp; 
      }
   
      public LinkedList<T> sublist() {
			LinkedList<T> temp = sublist(0, size() - 1);
			return temp;
      	
      } 
   	
      public LinkedList<T> sublist(int start, int stop) {
         LinkedList<T> temp = new LinkedList<T>();
         if(isValidNodePosition(start) && isValidNodePosition(stop)){
            for(int i = start; i <= stop; i++){
               T x = get(i);
               temp.add(x);
            }
         }
         return temp; 
      } 
   	
      public void add(LinkedList<T> list) {
         add(size(), list);
      
      } 
   	
      public void add(int position, LinkedList<T> list) {
			if(isValidIntervalPosition(position)) {
         	for(int i = 0; i < list.size(); i++) {
        			add(position++, list.get(i));

    			}
			}
			
			
      } 
   	
   	//test methods
      public static void intTestOne() { 
         int n = 20; 
         int range = 1000; 
      //----------------------- 
      // testing add(int value) 
      //----------------------- 
         LinkedList<Integer> list = new LinkedList<Integer>(); 
         Random r = new Random(); 
         for (int i=1; i<=n; i++) { 
            int x = r.nextInt(range); list.add(x); 
         } 
         System.out.println(list); 
      //----------------------------- 
      // testing remove(int position) 
      //----------------------------- 
         Random random = new Random(); 
         for (int i=1; i<=n; i++) { 
            int sz = list.size(); 
            int x = 0; 
            if (sz>1) x = random.nextInt(list.size()); 
            int v = list.remove(x);
            System.out.print("Removed " + v + " from position " + (x+1) + "/" + sz); 
         
            System.out.println(": " + list); 
         } 
         
      //------------------------------------- 
      // testing add(int index, int value) 
      //------------------------------------- 
         list = new LinkedList<Integer>(); 
         for (int i=1; i<=n; i++) { 
            int x = random.nextInt(range); 
            int sz = list.size(); 
            int p = random.nextInt(sz+1); 
            System.out.print("a " + x  + " " + p + "/" + sz + ": "); 
            list.add(p,x); 
            System.out.println(list); 
         } 
      //-------------------------- 
      // testing get(int position) 
      //-------------------------- 
         for (int i=0; i<n; i++) System.out.println("g " + i + ": " + list.get(i)); 
      //------------------------------------- 
      // testing set(int position, int value) 
      //------------------------------------- 
         for (int i=0; i<n; i++) { 
            int x = random.nextInt(range); 
            System.out.print("s " + i + " " + x + ": "); 
            list.set(i,x); 
            System.out.println(list); 
         } 
      }
   	
      public static void intTestTwo() { 
         int n1 = 10; 
         int range1 = 1000; 
         Random random = new Random(); 
         LinkedList<Integer> list1 = new LinkedList<Integer>();
      	 
         for (int i=1; i<=n1; i++){
            list1.add(random.nextInt(range1)); 
         }
      	
         int n2 = 5; 
         int range2 = 500; 
         LinkedList<Integer> list2 = new LinkedList<Integer>(); 
      	
         for (int i=1; i<=n2; i++){
            list2.add(random.nextInt(range2));
         }
      	 
         System.out.println("list1: " + list1);  
         System.out.println("list2: " + list2); 
			
      	
         for (int i=0; i<=n1; i++) { 
            LinkedList<Integer> list1copy = list1.sublist(); 
            list1copy.add(i,list2); 
            System.out.println(list1copy); 
         } 
      } 
   }